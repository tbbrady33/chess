package Server;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class register {

    public register(Request Req, Response Res){
        var serializer = new Gson();
        // how do I get the header from the request
        registerRequest data = serializer.fromJson(Req.headers(), registerRequest.class);

    }
}
