package com.example.sales_food_catalog.model;

import java.util.Objects;

/**
 * Representa un producto no perecedero.
 * El precio de venta se ajusta según el tipo de producto (A, B o C).
 */
public class NonPerishableProduct extends Product {
    /**
     * Tipo de producto no perecedero
     */
    private final NonPerishableType type;

    /**
     * Constructor para producto no perecedero.
     *
     * @param code          Código del producto
     * @param description   Descripción
     * @param purchasePrice Precio de compra
     * @param salePrice     Precio de venta inicial
     * @param type          Tipo de producto (A, B o C)
     */
    public NonPerishableProduct(String code, String description, double purchasePrice, double salePrice, NonPerishableType type) {
        super(code, description, purchasePrice, salePrice);
        this.type = Objects.requireNonNullElse(type, NonPerishableType.B); // Si el tipo es null, se asigna B
    }

    /**
     * Obtiene el tipo de producto no perecedero.
     *
     * @return Tipo (A, B o C)
     */
    public NonPerishableType getType() {
        return type;
    }

    /**
     * Calcula el precio de venta según el tipo de producto.
     * Los tipos A, B y C incrementan el precio en distintos porcentajes.
     *
     * @return Precio de venta ajustado
     */
    @Override
    public double calculateSalePrice() {
        // Lógica de ejemplo: aumenta el precio según el tipo
        double factor = switch (type) {
            case A -> 1.3;
            case B -> 1.15;
            case C -> 1.05;
        };
        return getPurchasePrice() * factor;
    }
}
