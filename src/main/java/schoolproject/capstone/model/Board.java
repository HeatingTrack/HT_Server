package schoolproject.capstone.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.Query;
import schoolproject.capstone.model.value.Domain;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "board")
public class Board extends BaseEntity implements Persistable<String> {

    @Column(name = "num")
    private Long num;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Board() {
        super(Domain.BOARD);
    }

    @Builder
    protected Board(Long num, String title, String content, User user) {
        this();
        this.num = num;
        this.title = title;
        this.content = content;
        this.user = user;
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
