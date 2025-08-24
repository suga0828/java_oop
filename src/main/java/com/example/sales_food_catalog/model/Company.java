package com.example.sales_food_catalog.model;

import java.util.ArrayList;

/**
 * Company manages a catalog of food products (perishable and non-perishable).
 * Provides methods to add products, display the catalog, and update sale prices.
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
