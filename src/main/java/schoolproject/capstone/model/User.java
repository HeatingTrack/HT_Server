package schoolproject.capstone.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Persistable;
import schoolproject.capstone.model.value.Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity implements Persistable<String> {

    @Column(name = "email", updatable = false)
    private String email;

    @Column(name = "type")
    private int type = 0;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    protected User() {
        super(Domain.USER);
    }

    @Builder
    protected User(String email, String password, String name, String phone) {
        this();
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public void updateUser(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }
}
