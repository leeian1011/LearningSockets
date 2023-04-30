import java.io.*;
import java.net.*;
//When running the Client you may notice that after every server response it awaits for a client response,
//this is because the code utilizes a while loop that gets the client's input, prints it on the socket's output stream and then 
//print out the server response all this only happens once.
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
        System.out.println("Server is closed.");
    }
   } 
}
