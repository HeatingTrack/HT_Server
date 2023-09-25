package schoolproject.capstone.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Persistable;
import schoolproject.capstone.model.value.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity implements Persistable<String> {

    @Column(name = "email", updatable = false)
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

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }

//    public static User signUp(String email, String password) {
//        return User.builder()
//                .email(email)
//                .password(password)
//                .build();
//    }
}
