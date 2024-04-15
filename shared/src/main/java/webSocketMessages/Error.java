package webSocketMessages;

public class Error extends ServerMessage{

    private String errorMessage;
    public Error(ServerMessageType type, String errorMessage) {

        super(type);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
