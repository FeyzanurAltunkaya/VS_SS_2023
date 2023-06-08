package com.example.projectFiler.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EnableAutoConfiguration
public class UserGroupJoinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UserEntitiy")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "GroupEntitiy")
    private GroupEntity group;

}
