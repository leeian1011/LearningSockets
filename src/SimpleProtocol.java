public class SimpleProtocol {
    private final int WAITING = 0;
    private final int SENTRESPONSE = 1;
    private final int END = 2;
    private int state = WAITING;


    public String process(String input){
        String output = "default_server_response";

        if(state == WAITING){
            output = "Successfully connected";
            state = SENTRESPONSE;
        }else if(state == SENTRESPONSE){
            output = "Acknowledged. Input is " + input;
            if(input.toLowerCase().equals("/quit")){
                output = "Adios";
                state = END;
            }
        }
        return output;
    }
}

