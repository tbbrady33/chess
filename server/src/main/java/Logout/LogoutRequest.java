package Logout;

import java.util.Objects;

public record LogoutRequest(String authtoken) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogoutRequest that)) return false;
        return Objects.equals(authtoken, that.authtoken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authtoken);
    }
}
