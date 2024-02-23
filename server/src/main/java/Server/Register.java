package Server;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class Register {

    public String register(Request Req, Response Res){
        var serializer = new Gson();
        // how do I get the header from the request
        registerRequest data = serializer.fromJson(Req.body(), registerRequest.class);
        return new Gson().toJson(new UserService(data).register());
    }
}
