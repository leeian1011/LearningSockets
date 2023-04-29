import java.io.*;
import java.net.*;
//This is still heavily abstracted as the ServerSocket and Socket objects comes with the java API.
//will experiment with C to improve understanding.

public class Server {
    public static void main(String[] args) throws Exception {
        int portNumber = Integer.parseInt(args[0]);
        System.out.println("Host Address: " + Inet4Address.getLocalHost().getHostName() + ":" + portNumber);
        System.out.println("Awaiting Client Connection");

        //use a try-with-resources to handle exceptions easily.

        try(
        //initializes a socket for our server to listen in.
        //the server listens in on the port number that we specific in the runtime argument
        ServerSocket ss = new ServerSocket(portNumber);
        Socket client = ss.accept();

        //PrintWriter is a Writer class that accepts an output char stream class
        //in this case we are using the client's output stream (essentially whatever is being sent to the server)
        PrintWriter serverSideOutput = new PrintWriter(client.getOutputStream(), true);
        
        //InputStreamReader is a byte stream class that reads accepts an inputstream.
        //we initialize an anonymous InputStreamReader with the client's input stream (essentially the server's output stream)
        //and initialize a buffered reader with the anonymous inputstreamreader.
        //InputStreamReader is a bridge from byte to char streams. it takes byte encoded data and decodes it into characters
        //using a charset.
        BufferedReader serverSideInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
        ){
            System.out.println("Client has made a socket connection");
            String inputLine, outputLine;
            //The Simple Protocol is a self made protocol that handles server ouput and socket connection with clients.
            // it essentially determines the server's "response".
            SimpleProtocol communicationProtocol = new SimpleProtocol();

            serverSideOutput.println("Input a name");
            System.out.println(serverSideInput.readLine() + " has connected");
            //we set the outputLine (the server's response) to the protocol's reaction to the user's input
            outputLine = communicationProtocol.process(null);
            serverSideOutput.println(outputLine);

            while((inputLine = serverSideInput.readLine()) != null){
                outputLine = communicationProtocol.process(inputLine);
                if(outputLine.equals("Adios")){
                    serverSideOutput.println(outputLine);
                    System.out.println("Client has requested termination of server.");
                    break;
                }
                serverSideOutput.println(outputLine);
            }
            
        }
    }
}
