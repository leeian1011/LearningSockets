import java.io.*;
import java.net.*;

public class Client {
   public static void main(String[] args) {
    String hostName = args[0];
    int portNumber = Integer.parseInt(args[1]);

    try(Socket clientSocket = new Socket(hostName, portNumber);
        PrintWriter clientSideOutput = new PrintWriter(clientSocket.getOutputStream());
        BufferedReader clientSideInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader clientStandardInput = new BufferedReader(new InputStreamReader(System.in));
    ){
        
    }catch(IOException e){
        TODO ...
    }
   } 
}
