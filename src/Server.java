import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        int portNumber = Integer.parseInt(args[0]);

        //initializes a socket for our server to listen in.
        //the server listens in on the port number that we specific in the runtime argument
        
        ServerSocket ss = new ServerSocket(portNumber);
        Socket client = ss.accept();

        //PrintWriter is a Writer class that accepts an output char stream class
        //in this case we are using the client's output stream (essentially whatever is being sent to the server)
        PrintWriter serverSideOutput = new PrintWriter(client.getOutputStream(), true, null);
 
    }
}
