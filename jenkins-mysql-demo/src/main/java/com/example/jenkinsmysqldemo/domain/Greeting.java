package com.example.jenkinsmysqldemo.domain;

import java.util.Objects;

public class Greeting {
    private int id;
    private String Greeting;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGreeting() {
        return Greeting;
    }

    public void setGreeting(String greeting) {
        Greeting = greeting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greeting greeting = (Greeting) o;
        return getId() == greeting.getId() &&
                getGreeting().equals(greeting.getGreeting());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGreeting());
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", Greeting='" + Greeting + '\'' +
                '}';
    }
}
