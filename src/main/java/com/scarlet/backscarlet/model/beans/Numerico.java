package com.scarlet.backscarlet.model.beans;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "numerico")
@Getter
@Setter
@NoArgsConstructor
public class Numerico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(updatable = false, insertable = false)
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

    public Numerico(int t36, int t38, int t40, int t42, int t44, int t46, int t48, int t50) {
        this.t36 = t36;
        this.t38 = t38;
        this.t40 = t40;
        this.t42 = t42;
        this.t44 = t44;
        this.t46 = t46;
        this.t48 = t48;
        this.t50 = t50;
    }
}
