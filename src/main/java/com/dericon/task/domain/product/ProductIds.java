package com.dericon.task.domain.product;


import java.util.Objects;

public class ProductIds {
    private String isin;
    private String wkn;
    private String vwd;

    public ProductIds() {
    }

    public ProductIds(String isin, String wkn, String vwd) {
        this.isin = isin;
        this.wkn = wkn;
        this.vwd = vwd;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getWkn() {
        return wkn;
    }

    public void setWkn(String wkn) {
        this.wkn = wkn;
    }

    public String getVwd() {
        return vwd;
    }

    public void setVwd(String vwd) {
        this.vwd = vwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductIds that = (ProductIds) o;
        return Objects.equals(isin, that.isin) &&
                Objects.equals(wkn, that.wkn) &&
                Objects.equals(vwd, that.vwd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(isin, wkn, vwd);
    }
}
