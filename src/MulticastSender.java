import java.io.*;
import java.net.*;
import java.util.*;
/**
* @author Ian Dempsey
* @date 10/4/17
*/
public class MulticastSender implements Runnable {
	private Thread t;
	private String name;
	//port you are connecting on 
	final int PORT = 8888;
	//just putting the name in of the user
	public MulticastSender(String name){
		
		this.name = name;
	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		DatagramSocket socket = null;
		DatagramPacket outPacket = null;
		
		//the string user types in, the message
		byte[] outBuf;
		
		Scanner s = new Scanner(System.in);
		
		try {
			//MAKING A SOCKET
			socket = new DatagramSocket();
			//not sure why this has to be here, strange
			String msg;
			while (true) {	
				msg= s.nextLine();
				//creating the message to be a nice format
				msg= name + " :" + msg;
				//getting the message contents
				outBuf=msg.getBytes();
				//closing the scanner
				//s.close();
				//Send to multicast IP address and port
				InetAddress address = InetAddress.getByName("224.2.2.3");
				
				//building the actual packet which will be sent. The message, length of the message, where to send it and the port. 
				outPacket = new DatagramPacket(outBuf, outBuf.length, address,PORT);
				//sending it
				socket.send(outPacket);
				
			}
			
		} 
		
		catch (IOException ioe) {
			System.out.println(ioe);
		}
		
	}
	
	
	public void start(){
		//if the thread has not already been created
		System.out.println("\nStarting Sender Thread"+"\n");
		if(t==null){//so start it
			t = new Thread(this);
			t.start();
		}
		//Announcing the user has joined the chatroom
		String message = "'" + name + "' has entered the chat server";
		byte [] outBuff = message.getBytes();//getting the message contents
		//use try catch , incase it doesn't work
		try{
			//address to send it too
			InetAddress address = InetAddress.getByName("224.2.2.3");
			
			//building the packet to send
			DatagramPacket outPacket = new DatagramPacket(outBuff,outBuff.length, address, PORT);
			//creating a socket
			DatagramSocket socket = new DatagramSocket();
			//send it
			socket.send(outPacket);
		}catch(IOException e){
			
			System.out.println(e);
			
		}
	}	
}
	
	
	
	
