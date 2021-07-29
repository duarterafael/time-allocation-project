package com.time.allocation.project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(unique = true, nullable = false)
    private String login;
    
    @Column(nullable = false)
    private String password;
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Time> times;
}
