package schoolproject.capstone.model;

import lombok.Builder;
import lombok.Getter;
import schoolproject.capstone.model.value.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
public class User extends BaseEntity {

    @Column(name = "email")
    private String email;

    private String password;

    protected User() {
        super(Domain.USER);
    }

    @Builder
    protected User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }

    public static User signUp(String email, String password) {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}