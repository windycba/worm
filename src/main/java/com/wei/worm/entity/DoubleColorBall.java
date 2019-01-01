package com.wei.worm.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "double_color_ball")
public class DoubleColorBall {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "month_num")
    private int monthNum;

    @Column(name = "red1")
    private int red1;

    @Column(name = "red2")
    private int red2;

    @Column(name = "red3")
    private int red3;

    @Column(name = "red4")
    private int red4;

    @Column(name = "red5")
    private int red5;

    @Column(name = "red6")
    private int red6;

    @Column(name = "blue")
    private int blue;

    @Column(name = "prize1_count")
    private int prize1Count;

    @Column(name = "prize1_amount")
    private int prize1Amount;

    @Column(name = "prize2_count")
    private int prize2Count;

    @Column(name = "prize2_amount")
    private int prize2Amount;

    @Column(name = "notice_date")
    private Date noticeDate;

    @Column(name = "pool_amount")
    private int poolAmount;//奖池金额

    @Column(name = "put_amount")
    private int putAmount;//投注金额


    @Column(name = "createDate")
    private Date created = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(int monthNum) {
        this.monthNum = monthNum;
    }

    public int getRed1() {
        return red1;
    }

    public void setRed1(int red1) {
        this.red1 = red1;
    }

    public int getRed2() {
        return red2;
    }

    public void setRed2(int red2) {
        this.red2 = red2;
    }

    public int getRed3() {
        return red3;
    }

    public void setRed3(int red3) {
        this.red3 = red3;
    }

    public int getRed4() {
        return red4;
    }

    public void setRed4(int red4) {
        this.red4 = red4;
    }

    public int getRed5() {
        return red5;
    }

    public void setRed5(int red5) {
        this.red5 = red5;
    }

    public int getRed6() {
        return red6;
    }

    public void setRed6(int red6) {
        this.red6 = red6;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getPrize1Count() {
        return prize1Count;
    }

    public void setPrize1Count(int prize1Count) {
        this.prize1Count = prize1Count;
    }

    public int getPrize1Amount() {
        return prize1Amount;
    }

    public void setPrize1Amount(int prize1Amount) {
        this.prize1Amount = prize1Amount;
    }

    public int getPrize2Count() {
        return prize2Count;
    }

    public void setPrize2Count(int prize2Count) {
        this.prize2Count = prize2Count;
    }

    public int getPrize2Amount() {
        return prize2Amount;
    }

    public void setPrize2Amount(int prize2Amount) {
        this.prize2Amount = prize2Amount;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getPoolAmount() {
        return poolAmount;
    }

    public void setPoolAmount(int poolAmount) {
        this.poolAmount = poolAmount;
    }

    public int getPutAmount() {
        return putAmount;
    }

    public void setPutAmount(int putAmount) {
        this.putAmount = putAmount;
    }

}
