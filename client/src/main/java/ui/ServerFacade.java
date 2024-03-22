package ui;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.*;
import java.net.*;

import ClearApp.ClearAppResponce;
import CreateGame.CreateGameRequest;
import CreateGame.CreateGameResponce;
import JoinGame.JoinGameRequest;
import JoinGame.JoinGameResponce;
import ListGames.ListGamesRequest;
import ListGames.ListGamesResponce;
import Login.LoginRequest;
import Login.LoginResponce;
import Logout.LogoutRequest;
import Logout.LogoutResponce;
import Register.RegisterRequest;
import Register.RegisterResponce;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;


public class ServerFacade {

    private final String serverUrl;

    public ServerFacade(String url){
        serverUrl = url;
    }

    public RegisterResponce register(RegisterRequest req) throws DataAccessException{
        var path = "/user";
        return this.makeRequest("POST", path, req, RegisterResponce.class, null);
    }

    public ClearAppResponce clear() throws DataAccessException{
        var path = "/db";
        return this.makeRequest("DELETE", path,null, ClearAppResponce.class, null);
    }

    public LoginResponce login(LoginRequest req) throws DataAccessException{
        var path = "/session";
        return this.makeRequest("POST", path, req, LoginResponce.class, null);
    }

    public LogoutResponce logout(LogoutRequest req) throws DataAccessException{
        var path = "/session";
        return this.makeRequest("DELETE", path, req, LogoutResponce.class, req.authtoken());
    }

    public ListGamesResponce listGames(ListGamesRequest req, String authToken) throws DataAccessException{
        var path = "/game";
        return this.makeRequest("GET", path, req, ListGamesResponce.class, authToken);
    }

    public CreateGameResponce createGame(CreateGameRequest req, String authToken) throws DataAccessException{
        var path = "/game";
        return this.makeRequest("POST", path, req, CreateGameResponce.class,authToken);
    }

    public JoinGameResponce joinGame(JoinGameRequest req, String authToken) throws DataAccessException{
        var path = "/game";
        return this.makeRequest("PUT",path, req, JoinGameResponce.class, authToken);
    }

    private <T> T makeRequest(String method, String path, Object request, Class<T> responseClass, String authToken) throws DataAccessException{
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);
            writeBody(request, http, authToken);
            http.connect();
            var status = http.getResponseCode();
            if (status != 200){
                return readError(http,responseClass);
            }
            return readBody(http, responseClass);
        }catch (IOException | URISyntaxException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }



    private static void writeBody(Object request, HttpURLConnection http, String authToken) throws IOException {
        if (request != null) {

            String reqData = new Gson().toJson(request);
            if(authToken != null) {
                http.addRequestProperty("Authorization",authToken);
            }
            try (OutputStream reqBody = http.getOutputStream()) {
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

    private static <T> T readError(HttpURLConnection http, Class<T> responseClass) throws IOException{
        T response = null;
        if (http.getContentLength() < 0) { // This might be reversed I am not sure yet
            try (InputStream respBody = http.getErrorStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);

                response = new Gson().fromJson(reader, responseClass);

            }
        }
        return response;
    }
    protected static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

}
