package com.swami.swamimvc.models;

import lombok.Data;
import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(schema = "Interview")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String commentFrom;
    private String commentTo;
    private String message;
    @Column(columnDefinition = "TIMESTAMPTZ")
    private ZonedDateTime commentDatetime;
    /*private Integer postedByUserID;*/
    @ManyToOne
    @JoinColumn(name = "postedByUserID")
    private User user;

    @PrePersist
    public void prePersist() {
        this.commentDatetime = ZonedDateTime.now();
    }
}
