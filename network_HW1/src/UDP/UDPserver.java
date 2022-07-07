package UDP;
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class UDPserver {
	public static void main(String[] args) throws Exception {
		String flag = "false";
		String line =""; 
		String Inputsentence  =""; 
		//Create Datagram Socket At The same port number to client
		DatagramSocket SC = new DatagramSocket(9845);
		byte[] receive_Data = new byte[1024]; 
		byte[] send_Data = new byte[1024]; 
		while(true) 
		{
			// to recive packet coming from client
			DatagramPacket Packet = new DatagramPacket(
					receive_Data, receive_Data.length);
			SC.receive(Packet); 
			// read incoming packet convert packet to string
			Inputsentence  = new String(Packet.getData());
			String t=new String(Inputsentence .trim()); 
			System.out.println( "The Request MSG is:"+t); 
			 // open database file
			Scanner file = new Scanner(new File("C:\\Users\\Dell\\eclipse-workspace\\network_HW1\\bin\\UDP\\StudentName.txt"));
			//Way to search in file
			while(file.hasNextLine()) { 
				line = file.nextLine(); 
				if(line.contains(t)) { 
					flag = "True"; 
					System.out.println(line); 
					break;
				}
				else {
					flag = "false"; 
					continue;
				}
				}
			// get  client IP Address
			InetAddress IPAddress = Packet.getAddress(); 
			// get client port number
			int port = Packet.getPort(); 
			if(flag.equals("True")) { 
				String []name=line.split(" "); 
				// convert response to byte
				send_Data = name[1].getBytes();
				// create output packet
				DatagramPacket sendPacket = new DatagramPacket (send_Data, send_Data.length, IPAddress,port); 
				// send response to client
				SC.send(sendPacket);
			}
			else {
				String errorMSG="Student id Not Found"; 
				send_Data = errorMSG.getBytes(); 
				// if student number not found server will send error
				DatagramPacket sendPacket = new DatagramPacket(send_Data, send_Data.length, IPAddress,port); 
				SC.send(sendPacket); 	
			}					
		}
	}
}