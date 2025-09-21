package com.example.financial_products_management.domain.financial_product.model;

import com.example.financial_products_management.domain.customer.Customer;
import com.example.financial_products_management.domain.shared.BankingOperations;
import com.example.financial_products_management.exception.InsufficientFundsException;
import java.time.LocalDate;

/**
 * Clase abstracta que representa un producto financiero genérico.
 * Provee atributos y métodos comunes para todos los productos financieros.
 * Implementa la interfaz BankingOperations para operaciones bancarias.
 *
 * @author 200582 Alexander Sandoval
 * @since 2025-09-20
 */
public abstract class FinancialProduct implements BankingOperations {
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
     *
     * @return Tipo de producto
     */
    public abstract String getProductType();

    /**
     * Realiza una operación de depósito en el producto.
     *
     * @param amount Monto a depositar
     * @return Mensaje resultado de la operación
     * @throws IllegalArgumentException Si el monto es inválido
     */
    @Override
    public abstract String deposit(double amount) throws IllegalArgumentException;

    /**
     * Realiza una operación de retiro del producto.
     *
     * @param amount Monto a retirar
     * @return Mensaje resultado de la operación
     * @throws IllegalArgumentException Si el monto es inválido
     * @throws InsufficientFundsException Si no hay fondos suficientes
     */
    @Override
    public abstract String withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException;

    @Override
    public String toString() {
        return String.format("Número: %s; Fecha Apertura: %s; Tipo: %s; Saldo: $%.2f", 
                productNumber, openingDate, getProductType(), balance);
    }
}
