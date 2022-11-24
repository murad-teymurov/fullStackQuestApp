package com.company.questApp.entites;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
            @JoinColumn(name = "user_id", nullable = false)
            @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;                                     //    private Long userId;

    private String title;

    @Column(columnDefinition = "text")
    private String text;
}
