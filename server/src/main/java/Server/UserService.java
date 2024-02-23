package Server;

public class UserService {
    private registerRequest request;

    public UserService(registerRequest request) {
        request = this.request;
    }

    public registerResponce register() {
        return new registerResponce("hi","000879");
    }
}
