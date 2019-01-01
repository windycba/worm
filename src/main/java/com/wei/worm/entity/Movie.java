package com.wei.worm.entity;


import javax.persistence.*;

@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    private String location;

    private String actor;

    private String label;
}
