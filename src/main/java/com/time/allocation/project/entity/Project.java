package com.time.allocation.project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;
    
    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Time> times;
}
