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
@Table(name = "board")
public class Board extends BaseEntity implements Persistable<String> {

    @Column(name = "num", unique = true, updatable = false)
    private Long num;

    @Column(name = "type")
    private int type;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    protected Board() {
        super(Domain.BOARD);
    }

    @Builder
    protected Board(String title, int type, String content, User user) {
        this();
        this.title = title;
        this.content = content;
        this.user = user;
        this.type = type;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }
}
