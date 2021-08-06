package com.kimi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.AssociationOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author 郭富城
 */
@Getter
@Setter
@Entity
@Table(name = "course")
@EntityListeners(AuditingEntityListener.class)
public class Course implements Serializable {


    private static final long serialVersionUID = 2974131352028184171L;
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course user = (Course) o;
        return Objects.equals(id, user.id);
    }

    /**
     * 重写hashcode防止死循环
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
