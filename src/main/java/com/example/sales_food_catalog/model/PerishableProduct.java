package com.example.sales_food_catalog.model;

/**
 * Representa un producto perecedero.
 * El precio de venta se ajusta según los días restantes para caducar.
 */
public class PerishableProduct extends Product {
    private final int daysToExpire;

    /**
     * Crea una nueva instancia de Producto Perecedero.
     *
     * @param code          Código del producto
     * @param description   Descripción
     * @param purchasePrice Precio de compra
     * @param salePrice     Precio de venta inicial
     * @param daysToExpire  Días para caducar
     */
    public PerishableProduct(String code, String description, double purchasePrice, double salePrice, int daysToExpire) {
        super(code, description, purchasePrice, salePrice);
        this.daysToExpire = daysToExpire;
    }

    /**
     * @return Días para caducar
     */
    public int getDaysToExpire() {
        return daysToExpire;
    }

    /**
     * Calcula el precio de venta considerando la proximidad de la caducidad.
     * Si faltan pocos días, el precio baja.
     *
     * @return Precio de venta ajustado
     */
    @Override
    public double calculateSalePrice() {
        double factor = 1.0;
        if (daysToExpire == 1) {
            // Descuento del 4%
            factor = 0.96;
        } else if (daysToExpire == 2) {
            // Descuento del 3%
            factor = 0.97;
        } else if (daysToExpire == 3) {
            // Descuento del 2%
            factor = 0.98;
        }
        return getSalePrice() * factor;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; Días para caducar: %d", daysToExpire);
    }
}
