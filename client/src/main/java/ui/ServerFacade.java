package ui;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.*;
import java.net.*;

import CreateGame.CreateGameRequest;
import CreateGame.CreateGameResponce;
import JoinGame.JoinGameRequest;
import JoinGame.JoinGameResponce;
import ListGames.ListGamesRequest;
import ListGames.ListGamesResponce;
import Login.LoginRequest;
import Login.LoginResponce;
import Logout.LogoutResponce;
import Register.RegisterRequest;
import Register.RegisterResponce;
import com.google.gson.Gson;
import dataAccess.DataAccessException;

public class ServerFacade {

    private final String serverUrl;

    public ServerFacade(String url){
        serverUrl = url;
    }

    public RegisterResponce register(RegisterRequest req) throws DataAccessException{
        var path = "/user";
        return this.makeRequest("POST", path, req, RegisterResponce.class);
    }

    public LoginResponce login(LoginRequest req) throws DataAccessException{
        var path = "/session";
        return this.makeRequest("POST", path, req, LoginResponce.class);
    }

    public LogoutResponce logout(LogoutResponce req) throws DataAccessException{
        var path = "/session";
        return this.makeRequest("DELETE", path, req, LogoutResponce.class);
    }

    public ListGamesResponce listGames(ListGamesRequest req) throws DataAccessException{
        var path = "/game";
        return this.makeRequest("GET", path, req, ListGamesResponce.class);
    }

    public CreateGameResponce createGame(CreateGameRequest req) throws DataAccessException{
        var path = "/game";
        return this.makeRequest("POST", path, req, CreateGameResponce.class);
    }

    public JoinGameResponce joinGame(JoinGameRequest req) throws DataAccessException{
        var path = "/game";
        return this.makeRequest("PUT",path, req, JoinGameResponce.class);
    }

    private <T> T makeRequest(String method, String path, Object request, Class<T> responseClass) throws DataAccessException{
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);
            writeBody(request, http);
            http.connect();
            throwIFNotSuccess(http);
            return readBody(http, responseClass);
        }catch (Exception ex) {
        throw new DataAccessException("Didnt work");


        }

    }

    private void throwIFNotSuccess(HttpURLConnection http) throws IOException, DataAccessException {
        var status = http.getResponseCode();
        if (status != 200) {
            throw new DataAccessException("didnt work");
        }
    }

    private static void writeBody(Object request, HttpURLConnection http) throws IOException{
        if(request != null){
            http.addRequestProperty("Content-Type", "applicatio/json");
            String reqData = new Gson().toJson(request);
            try(OutputStream reqBody = http.getOutputStream()) {
                reqBody.write(reqData.getBytes());
            }

        }

    }

    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException{
        T response = null;
        if (http.getContentLength() < 0) { // This might be reversed I am not sure yet
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    response = new Gson().fromJson(reader, responseClass);
                }
            }
        }
        return response;
    }

}
