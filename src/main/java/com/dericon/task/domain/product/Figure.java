package com.dericon.task.domain.product;

import java.util.Objects;

public class Figure {
    private double sideYield;
    private double sideYieldPa;

    public Figure() {}

    public Figure(double sideYield, double sideYieldPa) {
        this.sideYield = sideYield;
        this.sideYieldPa = sideYieldPa;
    }

    public double getSideYield() {
        return sideYield;
    }

    public void setSideYield(double sideYield) {
        this.sideYield = sideYield;
    }

    public double getSideYieldPa() {
        return sideYieldPa;
    }

    public void setSideYieldPa(double sideYieldPa) {
        this.sideYieldPa = sideYieldPa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Double.compare(figure.sideYield, sideYield) == 0 &&
                Double.compare(figure.sideYieldPa, sideYieldPa) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(sideYield, sideYieldPa);
    }
}
