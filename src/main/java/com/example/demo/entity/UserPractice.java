package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserPractice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String userSolution;

    @Column(nullable = false)
    private String score;

    @Column(nullable = false)
    private Date date;

    @OneToOne
    private User user;

    @ManyToOne
    private Problem problem;

}
