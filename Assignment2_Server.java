import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;  

public class Server 
{ 
    private Socket          socket   = null; //initialize socket and input stream 
    private ServerSocket    server   = null; 
    private DataInputStream in       = null; 
    public Server(int port) // constructor with port 
    {
        try	// starts server and waits for a connection 
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started\nWaiting for a client ..."); 
            socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream())); //takes input from the client socket 

            String line = ""; 
  
            while (!line.equals("Over")) // reads message from client until "Over" is sent 
            { 
                try
                { 
                    line = in.readUTF(); 
                    System.out.println(line);
                }
                catch(IOException i) { System.out.println(i); } 
            } 
            System.out.println("Closing connection"); 
            
            socket.close(); // close connection 
            in.close();
        } 
        catch(IOException i) { System.out.println(i);} 
    } 
  
    public static void main(String args[]) 
    { 
        Server server = new Server(5000); 
    } 
} 
