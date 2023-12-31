package schoolproject.capstone.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Persistable;
import schoolproject.capstone.model.value.Domain;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "comment")
public class Comment extends BaseEntity implements Persistable<String> {

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    protected Comment() {
        super(Domain.COMMENT);
    }

    @Builder
    public Comment(String comment, User user, Board board) {
        this();
        this.comment = comment;
        this.user = user;
        this.board = board;
    }

    public void modify(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }
}
