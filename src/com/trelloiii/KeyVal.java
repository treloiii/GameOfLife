package com.trelloiii;

public class KeyVal {
    int i;
    int j;

    public KeyVal(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @Override
    public String toString() {
        return "KeyVal{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
