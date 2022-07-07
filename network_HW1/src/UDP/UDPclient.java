package UDP;
import java.io.*; 
import java.net.*; 
public class UDPclient {

	public static void main(String[] args) throws IOException {
		System.out.println("****Client Side**** "); 
		System.out.println("Enter Your Request : "); 
		while(true) { 
		// Read input from Keybord and store it in buffer
		BufferedReader Input = new BufferedReader
				(new InputStreamReader(System.in)); 
		// Create s socket in the local device
		DatagramSocket s = new DatagramSocket();
		//Get IP add from name
		InetAddress DNS_Address = InetAddress.getByName("localhost");
		byte[] send_Data = new byte[1024]; 
		byte[] receive_Data = new byte[1024]; 
		String request = Input.readLine(); 
		//Convert input data from user to byte
		send_Data = request.getBytes();
		//Create datagrame to send to server
		DatagramPacket sendReq = new DatagramPacket(send_Data, 
				send_Data.length, DNS_Address , 9845); 
		//send the packet to server during socket
		s.send(sendReq); 
		//Create Datagram To receive response from server
		DatagramPacket receive_Responce = new DatagramPacket 
				(receive_Data, receive_Data.length);
		//Read Response that coming From Server
		s.receive(receive_Responce); 
		//Convert packet from Byte To String
		String StudentName = new String(receive_Responce.getData()); 
		//print Student name
		System.out.println("The Student Name is: " +StudentName);
		//close Datagram Socket
		s.close(); 
		
		
		
		}
	}

}
