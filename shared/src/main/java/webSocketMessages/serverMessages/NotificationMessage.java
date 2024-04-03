package webSocketMessages.serverMessages;

public class NotificationMessage extends ServerMessage{

    private ServerMessageType type;
    private String message;
    public NotificationMessage(ServerMessageType type, String message) {
        super(type);
        this.message = message;
        this.type = type;
    }

    public ServerMessageType getType() {
        return type;
    }


    public String getMessage() {
        return message;
    }


}
