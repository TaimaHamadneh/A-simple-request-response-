package network_HW1;
import java.io.*;
import java.net.*;
import java.util.Scanner;

 class Server {
	public static void main(String[] args) throws IOException {
		
		String clientRequest; 
		String flag = "false"; 
		String line ="";
		//Create Socket at the same Port Num
		ServerSocket sc = new ServerSocket(6789);
		while(true) { 
			// wait for connection with client and accept it
			Socket acceptConniction = sc.accept();
			// Store data come from client over socket in buffer 
			InputStreamReader in = new InputStreamReader(acceptConniction.getInputStream());
			BufferedReader inData = new BufferedReader(in);
			// To create output stream 
			DataOutputStream outToClient =new DataOutputStream(acceptConniction.getOutputStream()); 
			//Read request from client
			clientRequest = inData.readLine(); 
			System.out.print( "The Request MSG is:"+clientRequest+"\n"); 
			//Read request from client
			Scanner file = new Scanner(new File("C:\\Users\\Dell\\eclipse-workspace\\network_HW1\\src\\network_HW1\\StudentName.txt"));
			// Way to search in file on reg. number
			while(file.hasNextLine()) { 
				line = file.nextLine(); 
				if(line.contains(clientRequest)) { 
					flag = "True"; break; 
				}
				else { 
					flag = "false"; continue; 
					} 
			} 
			 
			 if(flag.equals("True")) { 
			 String responce[]=line.split(" "); 
			 //convert the result to byte and send it to clients
			 outToClient.writeBytes(responce[1]+'\n'); 
			 }
			 else { 
				 // if reg. number not found the server will send "Not Found"
				 outToClient.writeBytes("Student id Not Found"+ '\n'); 
			 }
		}
			 

			
	}

}
