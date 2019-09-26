package com.dericon.task.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Product {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("derived")
    private Derived derived;
    @JsonProperty("ids")
    private ProductIds productIds;
    @JsonProperty("figures")
    private Figure figures;

    private Product() {}

    public Product(String id, Derived derived, ProductIds productIds, Figure figures) {
        this.id = id;
        this.derived = derived;
        this.productIds = productIds;
        this.figures = figures;
    }

    public String getId() {
        return id;
    }

    public Derived getDerived() {
        return derived;
    }

    public ProductIds getProductIds() {
        return productIds;
    }

    public Figure getFigures() {
        return figures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(derived, product.derived) &&
                Objects.equals(productIds, product.productIds) &&
                Objects.equals(figures, product.figures);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, derived, productIds, figures);
    }
}
