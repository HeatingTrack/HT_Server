package schoolproject.capstone.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import schoolproject.capstone.model.value.Domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {

    @Id
    @Column(name = "id", updatable = false, unique = true, nullable = false, length = 50)
    private String id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private BaseEntity() {

    }

    protected BaseEntity(Domain domain) {
        id = domain.toString().toLowerCase() + "_" +
                UUID.randomUUID().toString().replace("-", "");
    }
}
