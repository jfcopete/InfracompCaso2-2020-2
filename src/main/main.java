package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class main {
	
	/**
	 * M�todo que retorna el hash criptografico MD5 del input
	 * @param input, String que ser� usado en la funci�n hash MD5
	 * @return el string del hash criptografico correspondiente
	 */
	public static String getMd5(String input)
	{
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length()<32) {
				hashtext = "0"+ hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * M�todo que retorna el hash criptografico SHA-256 del input
	 * @param input, String que ser� usado en la funci�n hash SHA-256
	 * @return el string del hash criptografico correspondiente
	 */
	public static byte[] getSHA256(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			return md.digest(input.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * M�todo que retorna el hash criptografico SHA-384 del input
	 * @param input, String que ser� usado en la funci�n hash SHA-384
	 * @return el string del hash criptografico correspondiente
	 * @throws NoSuchAlgorithmException si se genera alguna exception.
	 */
	public static String getSHA384(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-384");
		byte[] messageDigest = md.digest(input.getBytes());
		BigInteger no = new BigInteger(1, messageDigest);
		String hastext = no.toString(16);
		while (hastext.length() < 32) {
			hastext= "0"+hastext;
		}
		return hastext;
	}
	
	/**
	 * M�todo que retorna el hash criptografico SHA-512 del input
	 * @param input, String que ser� usado en la funci�n hash SHA-512
	 * @return el string del hash criptografico correspondiente
	 * @throws NoSuchAlgorithmException si se genera alguna exception.
	 */
	public static String getSHA512(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] messageDigest = md.digest(input.getBytes());
		BigInteger no = new BigInteger(1, messageDigest);
		String hastext = no.toString(16);
		while (hastext.length() < 32) {
			hastext= "0"+hastext;
		}
		return hastext;
	}
	
	/**
	 * M�todo auxiliar que transforma HexToString, para ayudar a SHA-256
	 * @param hash Array de bytes
	 * @return String convertido.
	 */
	public static String bytesToHex(byte[] hash ) {
		BigInteger number = new BigInteger(1,hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		while (hexString.length()<32) {
			hexString.insert(0, '0');
		}
		return hexString.toString();
		
	}
	
	/**
	 * M�todo que se encarga de controlar la l�pgica del programa
	 * @param cadena, String que ser� transformado
	 * @param alg, Algoritmo a usar
	 * @return Los codigos criptograficos.
	 * @throws NoSuchAlgorithmException si se tiene alguna exception.
	 */
	public static String generar_codigo(String cadena,String alg) throws NoSuchAlgorithmException
	{
		String codigo="";
		if (alg.equals("MD5")) {
			codigo = getMd5(cadena);
		}
		else if (alg.equals("SHA-256")) {
			codigo = bytesToHex(getSHA256(cadena));
		}
		else if (alg.equals("SHA-384")) {
			codigo = getSHA384(cadena);
		}else if (alg.contentEquals("SHA-512")) {
			codigo= getSHA512(cadena);
		}else if (alg.equals("TODOS")) {
			System.out.println("El hash para la cadena "+ cadena+" en TODOS los algoritmos es:");
			System.out.println("MD5: "+getMd5(cadena));
			System.out.println("SHA-256: "+bytesToHex(getSHA256(cadena)) );
			System.out.println("SHA-384: "+getSHA384(cadena));
			System.out.println("SHA-512: "+getSHA512(cadena));
			
		}
		return codigo;
	}

	/**
	 * Metodo Principal que sirve como CLI o GUI para la ejecuci�n del programa.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println("Escriba el nombre del algoritmo :");
		System.out.println("1: MD5");
		System.out.println("2: SHA-256");
		System.out.println("3: SHA-384");
		System.out.println("4: SHA-512");
		System.out.println("5: TODOS");
		String alg = br.readLine();
		System.out.println("Intregar Cadena");
		String cadena = br.readLine();
		System.out.println("El c�digo criptografico hash para la cadena segun el algoritmo "+ alg +" es: ");
		System.out.println(""+cadena+" "+alg);
		String output="";
		try {
			output = generar_codigo(cadena, alg);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" "+output);
		
		
	}

}
