package Server;

import DataAccess.UserInterfaceTests;
import DataAccess.memoryUserDAO;
import DataAccess.userDAO;

public class UserService {
    private registerRequest request;

    public UserService(registerRequest request) {
        request = this.request;
    }

    public registerResponce register() {
        userDAO thing = new memoryUserDAO();
        boolean exists = false;
        try {
            if (thing.getUser(request.username()) == null){

            }
            else {
                exists = true;
                //throw(Exception);
                // how do I deal with errors
            }
            thing.insertUser(new UserData(request.username(),request.password(),request.email()));
        }
        catch (dataAccess.DataAccessException e){
            System.out.println("Data access exeption");
        }

        return new registerResponce("hi","000879");
    }
}
