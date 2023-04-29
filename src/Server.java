import java.io.*;
import java.net.*;
//This is still heavily abstracted as the ServerSocket and Socket objects comes with the java API.
//will experiment with C to improve understanding.

public class Server {
    public static void main(String[] args) throws Exception {
        int portNumber = Integer.parseInt(args[0]);

        //use a try-with-resources to handle exceptions easily.

        try(
        //initializes a socket for our server to listen in.
        //the server listens in on the port number that we specific in the runtime argument
        ServerSocket ss = new ServerSocket(portNumber);
        Socket client = ss.accept();

        //PrintWriter is a Writer class that accepts an output char stream class
        //in this case we are using the client's output stream (essentially whatever is being sent to the server)
        PrintWriter serverSideOutput = new PrintWriter(client.getOutputStream(), true, null);
        
        //InputStreamReader is a byte stream class that reads accepts an inputstream.
        //we initialize an anonymous InputStreamReader with the client's input stream (essentially the server's output stream)
        //and initialize a buffered reader with the anonymous inputstreamreader.
        //InputStreamReader is a bridge from byte to char streams. it takes byte encoded data and decodes it into characters
        //using a charset.
        BufferedReader serverSideInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
        ){
            String inputLine, outputLine;

            SimpleProtocol communicationProtocol = new SimpleProtocol();

            outputLine = communicationProtocol.process(null);
            serverSideOutput.println(outputLine);

            while((inputLine = serverSideInput.readLine()) != null){
                outputLine = communicationProtocol.process(inputLine);
                serverSideOutput.println(outputLine);
                if(outputLine.equals("Adios")){
                    serverSideOutput.println(outputLine);
                    break;
                }
            }
            
        }


    }
}
