package com.trelloiii;

import java.util.Objects;

public class Node {
    private String represent;
    private boolean alive;
    public Node() {
        kill();
    }
    public void alive(){
        this.represent=" * ";
        this.alive=true;
    }
    public void kill(){
        this.represent=" ֎ ";
        this.alive=false;
    }

    public String getRepresent() {
        return represent;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return alive == node.alive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(represent, alive);
    }
}
