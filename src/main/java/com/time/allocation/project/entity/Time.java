package com.time.allocation.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"time\"")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_at", nullable = false)
    private Date startAt;

    @Temporal(TemporalType.TIMESTAMP)  
    @Column(name = "ended_at", nullable = false)
    private Date endedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
