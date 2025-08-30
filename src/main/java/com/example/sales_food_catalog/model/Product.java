package com.example.sales_food_catalog.model;

/**
 * Clase abstracta que representa un producto alimenticio genérico.
 * Provee atributos y métodos comunes para productos.
 */
public abstract class Product {
    private final String code;
    private final String description;
    private final double purchasePrice;
    private double salePrice;

    /**
     * Crea una nueva instancia de Producto.
     *
     * @param code          Código único del producto
     * @param description   Descripción del producto
     * @param purchasePrice Precio de compra
     */
    public Product(String code, String description, double purchasePrice, double salePrice) {
        this.code = code;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
    }

    /**
     * @return Código del producto
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Descripción del producto
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Precio de compra
     */
    public double getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * @return Precio de venta
     */
    public double getSalePrice() {
        return salePrice;
    }

    /**
     * @param price Nuevo precio de venta
     */
    public void setSalePrice(double price) {
        this.salePrice = price;
    }

    @Override
    public String toString() {
        return String.format("Código: %s; Descripción: %s; Compra: $%.2f; Venta: $%.2f", code, description, purchasePrice, salePrice);
    }

    /**
     * @return Precio de venta calculado
     */
    public abstract double calculateSalePrice();
}
