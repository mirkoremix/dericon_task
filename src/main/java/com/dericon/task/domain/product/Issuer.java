package com.dericon.task.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


public class Issuer {
    @JsonProperty("name")
    private String name;

    public Issuer() {}

    public Issuer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issuer issuer = (Issuer) o;
        return Objects.equals(name, issuer.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
