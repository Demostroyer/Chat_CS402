import java.io.*;
import java.net.*;
/**
*
* @author Ian Dempsey
* @date 10/4/17
*/
public class MulticastReceiver implements Runnable {
		
	@Override
	public void run() {
		// TODO Auto-generated method stub
		MulticastSocket socket = null;
		DatagramPacket inPacket = null;
		//the allowed size of the buffer, it will store the message
		byte[] inBuf = new byte[256];
		try {
			//Prepare to join multicast group
			socket = new MulticastSocket(8888);
			//address we are joining, multiple people can be on 
			InetAddress address = InetAddress.getByName("224.2.2.3");
			//attempt to join, if fail we output why
			socket.joinGroup(address);
			while (true) {//if all is good
				//create the packet we are taking in. Made from the received buffer, then it's length
				inPacket = new DatagramPacket(inBuf, inBuf.length);
				//recieve the packet
				socket.receive(inPacket);
				//now create the message from the user inputted message. Use inBuf, get it's length too. 
				String msg = new String(inBuf, 0, inPacket.getLength());
				//Now print out what it is: Get the address of the person, then print their message
				System.out.println("From " +msg);
			}
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	
	}
}
	