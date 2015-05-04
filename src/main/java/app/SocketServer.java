package app;

class SocketServer {
    static enum Result {
        CONTINUE, TERMINATE;
    }

    public Result accept(final String command) {
    	Result ret = null;
    	if(command.startsWith("ERROR")){
    		ret = Result.TERMINATE;
    	} else if (command.startsWith("FIRE")){
    		ret = Result.CONTINUE;
    	} else {
    		throw new IllegalArgumentException();
    	}
    	return ret;
    }
}
