package com.wei.worm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
    @Id
    private long id;
    @Column
    private String title;//标题
    @Column
    private String imageUrl;
    @Column
    private String localUrl;
    @Column
    private String imageName;
}
