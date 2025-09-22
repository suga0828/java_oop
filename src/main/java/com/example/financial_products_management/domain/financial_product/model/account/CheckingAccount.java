package com.example.financial_products_management.domain.financial_product.model.account;

import com.example.financial_products_management.domain.customer.Customer;
import com.example.financial_products_management.domain.financial_product.model.FinancialProduct;
import com.example.financial_products_management.domain.shared.Depositable;
import com.example.financial_products_management.domain.shared.Withdrawable;
import com.example.financial_products_management.domain.shared.OperationType;
import com.example.financial_products_management.exception.InsufficientFundsException;
import java.time.LocalDate;

/**
 * Representa una cuenta corriente.
 * Incluye valor de sobregiro, fecha de la última operación, valor de la última operación y tipo de operación.
 * Permite sobregiro cuando el monto a retirar supera al saldo actual.
 * Implementa las interfaces Depositable y Withdrawable para operaciones bancarias completas.
 */
public class CheckingAccount extends FinancialProduct implements Depositable, Withdrawable {
    private final double overdraftLimit;
    private LocalDate lastOperationDate;
    private double lastOperationAmount;
    private OperationType lastOperationType;

    /**
     * Crea una nueva instancia de Cuenta Corriente.
     *
     * @param productNumber   Número único del producto
     * @param openingDate     Fecha de apertura
     * @param customer        Datos del titular
     * @param balance         Saldo inicial
     * @param overdraftLimit  Límite de sobregiro permitido
     */
    public CheckingAccount(String productNumber, LocalDate openingDate, Customer customer, double balance, double overdraftLimit) {
        super(productNumber, openingDate, customer, balance);
        this.overdraftLimit = overdraftLimit;
        this.lastOperationDate = null;
        this.lastOperationAmount = 0.0;
        this.lastOperationType = null;
    }

    /**
     * Obtiene el límite de sobregiro.
     *
     * @return Límite de sobregiro
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * Obtiene la fecha de la última operación.
     *
     * @return Fecha de la última operación
     */
    public LocalDate getLastOperationDate() {
        return lastOperationDate;
    }

    /**
     * Obtiene el valor de la última operación.
     *
     * @return Valor de la última operación
     */
    public double getLastOperationAmount() {
        return lastOperationAmount;
    }

    /**
     * Obtiene el tipo de la última operación.
     *
     * @return Tipo de la última operación
     */
    public OperationType getLastOperationType() {
        return lastOperationType;
    }

    @Override
    public String getProductType() {
        return "Cuenta Corriente";
    }

    /**
     * Realiza una operación de depósito.
     *
     * @param amount Monto a depositar
     * @return Mensaje resultado de la operación
     */
    @Override
    public String deposit(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero");
        }

        setBalance(getBalance() + amount);
        updateLastOperation(amount, OperationType.DEPOSIT);

        return String.format("Depósito exitoso. Nuevo saldo: $%.2f", getBalance());
    }

    /**
     * Realiza una operación de retiro.
     * Valida que el monto a retirar no supere al saldo más el sobregiro.
     *
     * @param amount Monto a retirar
     * @return Mensaje resultado de la operación
     */
    @Override
    public String withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero");
        }

        // Validar que el monto no supere el saldo
        if (amount > getBalance()) {
            // Validar que el monto no supere el saldo más el sobregiro
            if (amount > (getBalance() + overdraftLimit)) {
                throw new InsufficientFundsException(getBalance(), overdraftLimit, amount);
            }
        }

        setBalance(getBalance() - amount);
        updateLastOperation(amount, OperationType.WITHDRAWAL);

        return String.format("Retiro exitoso. Nuevo saldo: $%.2f", getBalance());
    }

    /**
     * Actualiza la información de la última operación.
     *
     * @param amount        Monto de la operación
     * @param operationType Tipo de operación
     */
    private void updateLastOperation(double amount, OperationType operationType) {
        this.lastOperationDate = LocalDate.now();
        this.lastOperationAmount = amount;
        this.lastOperationType = operationType;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; Sobregiro: $%.2f; Última Operación: %s el %s por $%.2f", 
                overdraftLimit,
                lastOperationType != null ? lastOperationType : "N/A",
                lastOperationDate != null ? lastOperationDate : "N/A", 
                lastOperationAmount);
    }
}
