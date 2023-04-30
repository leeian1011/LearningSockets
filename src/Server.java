import java.io.*;
import java.net.*;
//This is still heavily abstracted as the ServerSocket and Socket objects comes with the java API.
//will experiment with C to improve understanding.

public class Server {
    public static void main(String[] args) throws Exception {
        int portNumber = Integer.parseInt(args[0]);
        System.out.println("Host Address: " + Inet4Address.getLocalHost().getHostName() + ":" + portNumber);
        System.out.println("Host ip: " + Inet4Address.getLocalHost());
        System.out.println("Awaiting Client Connection");

        //use a try-with-resources to handle exceptions easily.

        try(
        //initializes a Server Socket that the program listens and waits for a connection request.
        //the server listens in on the port number that we specific in the runtime argument

        ServerSocket ss = new ServerSocket(portNumber);
        Socket client = ss.accept();

       //We use a PrintWriter object to write data to the client's socket.
       //Server-side output will be the client-side input and vice versa!
        PrintWriter serverSideOutput = new PrintWriter(client.getOutputStream(), true);
        
        //We use a BufferedReader Object to read from the client socket's input stream (client's sent data).
        //to do this we have to connect a InputStreamReader (a byte to char reader bridge) to the Buffered Reader to read from a buffer.
        BufferedReader serverSideInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
        ){
            System.out.println("Client has made a socket connection");
            String inputLine, outputLine;

            //The Simple Protocol is a self made protocol that handles server ouput and socket connection with clients.
            // it essentially determines the server's "response".
            SimpleProtocol communicationProtocol = new SimpleProtocol();

            serverSideOutput.println("Input a name");
            String id = serverSideInput.readLine();
            System.out.println(id + " has connected");
            
            //we set the outputLine (the server's response) to the protocol's reaction to the user's input
            outputLine = communicationProtocol.process(null);
            serverSideOutput.println(outputLine);
            
            while((inputLine = serverSideInput.readLine()) != null){
                outputLine = communicationProtocol.process(inputLine);
                if(outputLine.equals("adios")){
                    serverSideOutput.println(outputLine);
                    serverSideOutput.println("Thank you for contacting the server");
                    System.out.println("Client has requested termination of server.");
                    client.close();
                }
                serverSideOutput.println(outputLine);                
            }   
            System.out.println("server is now ending");
        }catch(SocketException e){
            System.out.println("Connection has ended");
        }
    }
}
