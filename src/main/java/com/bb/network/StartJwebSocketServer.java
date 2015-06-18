/**
 * 
 */
package com.bb.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Enzo
 *
 */
public class StartJwebSocketServer {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		
		callProcessBuilder();
	}
	
	private static void callProcessBuilder() throws IOException, InterruptedException{

        ProcessBuilder pb = new ProcessBuilder( "java", "-jar",  "D:/My Documents/EnzoDocuments/Coding/jWebSocketServer-1.0-b30518/jWebSocket-1.0/libs/jWebSocketServer-1.0.jar");
        pb.redirectErrorStream(true);
        Process p = pb.start();
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println( line ); // Or just ignore it
        }
        p.waitFor();
	
	}
}
