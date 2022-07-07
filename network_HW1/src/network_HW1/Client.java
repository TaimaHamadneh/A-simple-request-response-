package network_HW1;
import java.io.*;
import java.net.*;
 class Client {
	 public static void main(String[] args) throws Exception { 
		 System.out.println("******Client Side******"); 
		 System.out.println("Enter Your Request : "); 
		 String Request; 
		 String StudentName; 
		 while(true) { 
		 // Read inputFrom KeyBord and store it in buffer

		 BufferedReader StudentNumber = new BufferedReader(new
				 InputStreamReader(System.in)); 
		 //Create a Socket in the local Device
		 Socket socket = new Socket("localhost", 6789);
		 // Create Data Output Stream 
		 DataOutputStream writedata =new DataOutputStream (socket.getOutputStream());
		 //To read output of server code
		InputStreamReader in = new InputStreamReader(socket.getInputStream());
		BufferedReader request = new BufferedReader(in);
		 Request = StudentNumber.readLine(); 
		 // Convert Input Data To Byte Before Sinding It To Server 
		 writedata.writeBytes(Request + '\n'); 
		 //Read Data come from Server During Socket 
		 StudentName = request.readLine(); 
		 // print data coming from server
		 System.out.println("The Student Name is: " + StudentName);
		 //close socket
		 socket.close();
		 }
	 }
 }
 
