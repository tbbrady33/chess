package Register;

import java.util.Objects;

public record RegisterResponce(String username, String authToken, String message ) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterResponce that)) return false;
        return Objects.equals(username, that.username) && Objects.equals(authToken, that.authToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authToken);
    }
}
