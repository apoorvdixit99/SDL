import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client 
{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
  
    // constructor to put ip address and port 
    public Client(String address, int port) 
    { 
        // establish a connection
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected");
            input  = new DataInputStream(System.in); 					// takes input from terminal
            out    = new DataOutputStream(socket.getOutputStream()); 	// sends output to the socket
        } 
        catch(Exception e){System.out.println(e);}
  
        String line = ""; 							     			    // string to read message from input 
 
        while (!line.equals("Over")) 									// keep reading until "Over" is input
        { 
            try
            { 
                line = input.readLine(); 
                out.writeUTF(line); 
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
        } 
  
        // close the connection 
        try
        { 
            input.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Client client = new Client("10.10.10.22", 5000); 
    } 
} 
