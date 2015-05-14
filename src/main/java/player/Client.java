package player;

public class Client extends Player{

	public Result accept(String command) {
		Result ret = null;
    	if(command.startsWith("ERROR")){
    		ret = Result.TERMINATE;
    	} else if (command.startsWith("HELLO")){
    		ret = Result.CONTINUE;
    	} else {
    		throw new IllegalArgumentException();
    	}
    	return ret;
	}
}
