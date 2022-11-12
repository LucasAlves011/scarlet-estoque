package com.scarlet.backscarlet.model.beans;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "numerico")
@Getter
@Setter
public class Numerico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(updatable = false,insertable = false)
    private int quantidade;

    @Column(name = "`36`")
    private int t36;

    @Column(name = "`38`")
    private int t38;

    @Column(name = "`40`")
    private int t40;

    @Column(name = "`42`")
    private int t42;

    @Column(name = "`44`")
    private int t44;

    @Column(name = "`46`")
    private int t46;

    @Column(name = "`48`")
    private int t48;

    @Column(name = "`50`")
    private int t50;

}
