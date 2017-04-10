/*
 * 
 * This is the running class
 * Just used to start the chat, send the message
 * 
 */
import java.util.*;

public class Run {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("What is your username? ");
		String name = s.nextLine();
		System.out.println("Please enter your message");
		
		//Start the sender
		MulticastSender sender = new MulticastSender(name);
		sender.start();
		
		//start the receiever
		MulticastReceiver rec = new MulticastReceiver();
		rec.run();
	}
}