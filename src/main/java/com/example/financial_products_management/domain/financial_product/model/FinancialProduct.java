package com.example.financial_products_management.domain.financial_product.model;

import com.example.financial_products_management.domain.customer.Customer;
import java.time.LocalDate;

/**
 * Clase concreta que representa un producto financiero genérico.
 * Provee atributos y métodos comunes para todos los productos financieros.
 * Las operaciones bancarias específicas son implementadas por las clases derivadas
 * mediante las interfaces Depositable y Withdrawable según corresponda.
 */
public class FinancialProduct {
    private final String productNumber;
    private final LocalDate openingDate;
    private final Customer customer;
    private double balance;

    /**
     * Crea una nueva instancia de Producto Financiero.
     *
     * @param productNumber Número único que identifica el producto
     * @param openingDate   Fecha de apertura del producto
     * @param customer      Datos del titular del producto
     * @param balance       Saldo inicial del producto
     */
    public FinancialProduct(String productNumber, LocalDate openingDate, Customer customer, double balance) {
        this.productNumber = productNumber;
        this.openingDate = openingDate;
        this.customer = customer;
        this.balance = balance;
    }

    /**
     * Obtiene el número del producto.
     *
     * @return Número único del producto
     */
    public String getProductNumber() {
        return productNumber;
    }

    /**
     * Obtiene la fecha de apertura del producto.
     *
     * @return Fecha de apertura
     */
    public LocalDate getOpeningDate() {
        return openingDate;
    }

    /**
     * Obtiene los datos del titular del producto.
     *
     * @return Datos del cliente titular
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Obtiene el saldo actual del producto.
     *
     * @return Saldo actual
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Establece el saldo del producto.
     *
     * @param balance Nuevo saldo
     */
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Obtiene el tipo de producto financiero.
     * Las clases derivadas pueden sobrescribir este método para proporcionar
     * un tipo específico.
     *
     * @return Tipo de producto
     */
    public String getProductType() {
        return "Producto Financiero Genérico";
    }


    @Override
    public String toString() {
        return String.format("Número: %s; Fecha Apertura: %s; Tipo: %s; Saldo: $%.2f", 
                productNumber, openingDate, getProductType(), balance);
    }
}
