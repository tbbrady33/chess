package Server;

import java.util.Objects;

public record registerResponce(String username, String authToken) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof registerResponce that)) return false;
        return Objects.equals(username, that.username) && Objects.equals(authToken, that.authToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authToken);
    }
}
