package webSocketMessages.serverMessages;

public class ErrorMessage extends ServerMessage{

    private ServerMessageType type;
    private String errorMessage;
    public ErrorMessage(ServerMessageType type, String errorMessage) {

        super(type);
        this.errorMessage = errorMessage;
        this.type = type;
    }

    public ServerMessageType getType() {
        return type;
    }


    public String getErrorMessage() {
        return errorMessage;
    }


}
