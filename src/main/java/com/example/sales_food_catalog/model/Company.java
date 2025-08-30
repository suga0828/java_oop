package com.example.sales_food_catalog.model;

import java.util.ArrayList;

/**
 * La clase Company gestiona un catálogo de productos alimenticios (perecederos y no perecederos).
 * Proporciona métodos para agregar productos, mostrar el catálogo y actualizar los precios de venta.
 */
public class Company {
    private final ArrayList<Product> products = new ArrayList<>();

    /**
     * adicionarProducto()
     *
     * @param product The product to add.
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * mostrarCatalogo()
     */
    public void showCatalog() {
        for (Product product : products) {
            System.out.println(product.getCode() + ": " + product.getDescription() + ", Compra: $" + product.getPurchasePrice() + ", Venta: $" + product.getSalePrice());
        }
    }

    /**
     * liquidarPreciosVenta()
     */
    public void liquidateSalePrice() {
        for (Product product : products) {
            double newPrice = product.calculateSalePrice();
            product.setSalePrice(newPrice);
        }
    }
}
