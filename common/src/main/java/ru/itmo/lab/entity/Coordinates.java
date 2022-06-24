package ru.itmo.lab.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("coordinates")
public class Coordinates {

    private long x;

    private float y;

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
