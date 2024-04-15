package Register;

import java.util.Objects;

public record RegisterRequest(String username,
                              String password,
                              String email) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterRequest that)) return false;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email);
    }
}
