package app;

class SocketServer {
    static enum Result {
        CONTINUE, TERMINATE;
    }

    public Result accept(final String command) {
        return Result.TERMINATE;
    }
}
