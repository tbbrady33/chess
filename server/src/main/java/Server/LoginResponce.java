package Server;

import java.util.Objects;

public record LoginResponce(String username, String authtoken) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginResponce that)) return false;
        return Objects.equals(username, that.username) && Objects.equals(authtoken, that.authtoken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authtoken);
    }
}
