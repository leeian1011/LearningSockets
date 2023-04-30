public class SimpleProtocol {
    private final int WAITING = 0;
    private final int GREETED = 1;
    private final int READING = 3;
    private final int END = 2;
    private int state = WAITING;


    public String process(String input){
        String output = null;

        if(input == null){
            System.out.println(state);
            output = "Successfully connected";
        }
        if(state == WAITING){
            System.out.println(state);
            output = "GREETINGS";
            state = GREETED;
        }else if(state == GREETED){
            System.out.println(state);
            output = "How can the server assist you?";
            state = READING;
        }else if(state == READING){
            System.out.println(state);
            if(input.toLowerCase().equals("/quit")){
                output = "adios";
                state = END;
            }else if(input.toLowerCase().equals("request data")){
                output = "data is being sent";
            }else{
                output = "hahahahhahahaha";
            }
        }
        return output;
    }
}

