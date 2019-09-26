package com.dericon.task.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Derived {

    @JsonProperty("issuer")
    private Issuer issuer;
    @JsonProperty("underlying")
    private Underlying underlying;

    public Derived() {}

    public Derived(Issuer issuer, Underlying underlying) {
        this.issuer = issuer;
        this.underlying = underlying;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public Underlying getUnderlying() {
        return underlying;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }

    public void setUnderlying(Underlying underlying) {
        this.underlying = underlying;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Derived derived = (Derived) o;
        return Objects.equals(issuer, derived.issuer) &&
                Objects.equals(underlying, derived.underlying);
    }

    @Override
    public int hashCode() {

        return Objects.hash(issuer, underlying);
    }
}
