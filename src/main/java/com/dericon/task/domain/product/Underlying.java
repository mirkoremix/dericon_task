package com.dericon.task.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Underlying {
    @JsonProperty("name")
    private String name;

    public Underlying() {}

    public Underlying(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Underlying that = (Underlying) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
