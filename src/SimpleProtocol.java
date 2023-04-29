public class SimpleProtocol {
    private final int WAITING = 0;
    private final int SENTRESPONSE = 1;
    
    private int state = WAITING;

    private String response = "Connected";

    public String process(String input){
        String output = null;

        if(state == WAITING){
            output = "Successfully connected";
            state = SENTRESPONSE;
        }else{
            output = "Acknowledged. Input is " + input;

            state = WAITING;
        }
        return output;
    }



    
}
