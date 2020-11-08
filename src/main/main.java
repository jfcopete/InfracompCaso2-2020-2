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
	 * 
	 * @param input
	 * @return
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
	 * 
	 * @param input
	 * @return
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
	 * 
	 * @param hash
	 * @return
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
	 * 
	 * @param cadena
	 * @param alg
	 * @return
	 * @throws NoSuchAlgorithmException 
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
		}
		return codigo;
	}

	/**
	 * 
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
		System.out.println("2: SHA-384");
		String alg = br.readLine();
		System.out.println("Intregar Cadena");
		String cadena = br.readLine();
		System.out.println("El código criptografico hash para la cadena segun el algoritmo "+ alg +" es: ");
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
