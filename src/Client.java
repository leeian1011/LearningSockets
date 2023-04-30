import java.io.*;
import java.net.*;

public class Client {
   public static void main(String[] args) {
    String hostName = args[0];
    int portNumber = Integer.parseInt(args[1]);

    try(Socket clientSocket = new Socket(hostName, portNumber);
        PrintWriter clientSideOutput = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader clientSideInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader clientStandardInput = new BufferedReader(new InputStreamReader(System.in));
    ){
        String userInput;
        System.out.println(clientSideInput.readLine());

       while((userInput = clientStandardInput.readLine()) != null){
        clientSideOutput.println(userInput);
        System.out.println(clientSideInput.readLine());
       } 

    }catch(IOException e){
        System.out.println("Error Client could not connect");
    }
   } 
}
