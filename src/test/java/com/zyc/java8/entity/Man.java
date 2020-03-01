package com.zyc.java8.entity;

/**
 * Created with IntelliJ IDEA.
 * User: zyc
 * Date: 2020/3/1
 * Time: 17:53
 * Description:
 */
public class Man {
    private Godness god;

    public Man() {
    }

    public Man(Godness god) {
        this.god = god;
    }

    public Godness getGod() {
        return god;
    }

    public void setGod(Godness god) {
        this.god = god;
    }

    @Override
    public String toString() {
        return "Man [god=" + god + "]";
    }
}
