package Server;

import java.util.Objects;

public record AuthData(String authToken, String username) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthData authData)) return false;
        return Objects.equals(authToken, authData.authToken) && Objects.equals(username, authData.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authToken, username);
    }
}
