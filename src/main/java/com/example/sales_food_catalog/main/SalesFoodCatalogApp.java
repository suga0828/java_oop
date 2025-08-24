package com.example.sales_food_catalog.main;

import com.example.sales_food_catalog.model.Company;
import com.example.sales_food_catalog.model.NonPerishableProduct;
import com.example.sales_food_catalog.model.NonPerishableType;
import com.example.sales_food_catalog.model.PerishableProduct;

/**
 * Clase principal de la aplicación de Catálogo de Ventas de Productos Alimenticios (Company).
 */
public class SalesFoodCatalogApp {

    /**
     * @param message Mensaje a registrar.
     */
    protected static void log(String message) {
        System.out.println("[" + new java.util.Date() + "] " + message);
    }

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        // Create perishable products
        PerishableProduct perishableProduct1 = new PerishableProduct("7 702177 022733", "Leche", 4700, 9400.0, 2);
        PerishableProduct perishableProduct2 = new PerishableProduct("P002", "Queso", 7500.0, 15000.0, 1);
        PerishableProduct perishableProduct3 = new PerishableProduct("P003", "Carne", 7500.0, 15000.0, 3);
        PerishableProduct perishableProduct4 = new PerishableProduct("P004", "Pollo", 6000.0, 12000.0, 4);

        // Create non-perishable products
        NonPerishableProduct nonPerishableProduct1 = new NonPerishableProduct("7 702084 137520", "Harina de maíz", 1950, 3900.0, NonPerishableType.B);
        NonPerishableProduct nonPerishableProduct2 = new NonPerishableProduct("7 707061 202208", "Sal", 3150.0, 6300.0, null);
        NonPerishableProduct nonPerishableProduct3 = new NonPerishableProduct("7 702511 000748", "Arroz", 7700.0, 15400.0, NonPerishableType.A);
        NonPerishableProduct nonPerishableProduct4 = new NonPerishableProduct("7 702020 011228", "Harina de trigo", 1375.0, 2750.0, NonPerishableType.C);


        // Create company
        log("Inicializando empresa...\n");
        Company company = new Company();

        // Add perishable products to company
        log("Agregando productos perecederos a la empresa...\n");
        company.addProduct(perishableProduct1);
        company.addProduct(perishableProduct2);
        company.addProduct(perishableProduct3);
        company.addProduct(perishableProduct4);

        // Add non-perishable products to company
        log("Agregando productos no perecederos a la empresa...\n");
        company.addProduct(nonPerishableProduct1);
        company.addProduct(nonPerishableProduct2);
        company.addProduct(nonPerishableProduct3);
        company.addProduct(nonPerishableProduct4);

        // Show catalog
        log("=== PROCESO DE MOSTRAR CATÁLOGO ===");
        log("Catálogo original:");
        company.showCatalog();

        // Liquidate sale prices
        log("=== PROCESO DE LIQUIDACIÓN DE PRECIOS DE VENTA ===\n");
        company.liquidateSalePrice();

        // Show catalog
        log("=== PROCESO DE MOSTRAR CATÁLOGO ===");
        log("Catálogo actualizado:");
        company.showCatalog();
    }
}

