package com.dericon.task.domain.product.dto;

import com.dericon.task.domain.product.Product;

import java.util.Objects;

public class ProductDTO {
    private final String issuerName;
    private final String underlyingName;
    private final String isIn;
    private final double sideYieldPa;

    public ProductDTO(Product product) {
        this.issuerName = product.getDerived().getIssuer().getName();
        this.underlyingName = product.getDerived().getUnderlying().getName();
        this.isIn = product.getProductIds().getIsin();
        this.sideYieldPa = product.getFigures().getSideYieldPa();
    }

    public String getIssuerName() {
        return issuerName;
    }

    public String getUnderlyingName() {
        return underlyingName;
    }

    public String getIsIn() {
        return isIn;
    }

    public double getSideYieldPa() {
        return sideYieldPa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Double.compare(that.sideYieldPa, sideYieldPa) == 0 &&
                Objects.equals(issuerName, that.issuerName) &&
                Objects.equals(underlyingName, that.underlyingName) &&
                Objects.equals(isIn, that.isIn);
    }

    @Override
    public int hashCode() {

        return Objects.hash(issuerName, underlyingName, isIn, sideYieldPa);
    }
}