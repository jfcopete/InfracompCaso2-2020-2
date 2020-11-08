package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class main {
	
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
	
	public static String generar_codigo(String cadena,String alg)
	{
		String codigo="";
		if (alg.equals("MD5")) {
			codigo = getMd5(cadena);
		}
		return codigo;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println("Escriba el nombre del algoritmo :");
		System.out.println("1: MD5");
		String alg = br.readLine();
		System.out.println("Intregar Cadena");
		String cadena = br.readLine();
		System.out.println("El código criptografico hash para la cadena segun el algoritmo "+ alg +" es: ");
		System.out.println(""+cadena+" "+alg);
		String  output = generar_codigo(cadena, alg);
		System.out.println(" "+output);
		
		
	}

}
