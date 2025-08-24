package com.example.sales_food_catalog.model;

/**
 * Representa un producto perecedero.
 * El precio de venta se ajusta según los días restantes para caducar.
 */
public class PerishableProduct extends Product {
    private int daysToExpire;

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
        if (daysToExpire <= 3) {
            factor = 0.5;
        } else if (daysToExpire <= 7) {
            factor = 0.8;
        }
        return getPurchasePrice() * factor;
    }
}
