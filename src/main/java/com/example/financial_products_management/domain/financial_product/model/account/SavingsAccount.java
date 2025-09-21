package com.example.financial_products_management.domain.financial_product.model.account;

import com.example.financial_products_management.domain.customer.Customer;
import com.example.financial_products_management.domain.financial_product.model.FinancialProduct;
import com.example.financial_products_management.exception.InsufficientFundsException;
import java.time.LocalDate;

/**
 * Representa una cuenta de ahorro.
 * Incluye valor acumulado por intereses, fecha del último depósito y valor del último depósito.
 * Calcula interés del 3% sobre el saldo en cada depósito.
 */
public class SavingsAccount extends FinancialProduct {
    private double accumulatedInterest;
    private LocalDate lastDepositDate;
    private double lastDepositAmount;

    /**
     * Crea una nueva instancia de Cuenta de Ahorro.
     *
     * @param productNumber Número único del producto
     * @param openingDate   Fecha de apertura
     * @param customer      Datos del titular
     * @param balance       Saldo inicial
     */
    public SavingsAccount(String productNumber, LocalDate openingDate, Customer customer, double balance) {
        super(productNumber, openingDate, customer, balance);
        this.accumulatedInterest = 0.0;
        this.lastDepositDate = null;
        this.lastDepositAmount = 0.0;
    }

    /**
     * Obtiene el valor acumulado por intereses.
     *
     * @return Valor acumulado por intereses
     */
    public double getAccumulatedInterest() {
        return accumulatedInterest;
    }

    /**
     * Obtiene la fecha del último depósito.
     *
     * @return Fecha del último depósito
     */
    public LocalDate getLastDepositDate() {
        return lastDepositDate;
    }

    /**
     * Obtiene el valor del último depósito.
     *
     * @return Valor del último depósito
     */
    public double getLastDepositAmount() {
        return lastDepositAmount;
    }

    @Override
    public String getProductType() {
        return "Cuenta de Ahorro";
    }

    /**
     * Realiza una operación de depósito.
     * Calcula un interés del 3% sobre el saldo y lo suma al valor acumulado por intereses.
     *
     * @param amount Monto a depositar
     * @return Mensaje resultado de la operación
     * @throws IllegalArgumentException Si el monto es inválido
     */
    @Override
    public String deposit(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero");
        }

        setBalance(getBalance() + amount);
        
        // Calcular interés del 3% sobre el saldo
        double interest = getBalance() * 0.03;
        accumulatedInterest += interest;
        
        lastDepositDate = LocalDate.now();
        lastDepositAmount = amount;

        return String.format("Depósito exitoso. Nuevo saldo: $%.2f. Interés generado: $%.2f", 
                getBalance(), interest);
    }

    /**
     * Realiza una operación de retiro.
     * Valida que el monto a retirar no supere al saldo.
     *
     * @param amount Monto a retirar
     * @return Mensaje resultado de la operación
     * @throws IllegalArgumentException Si el monto es inválido
     * @throws InsufficientFundsException Si no hay fondos suficientes
     */
    @Override
    public String withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero");
        }

        if (amount > getBalance()) {
            throw new InsufficientFundsException();
        }

        setBalance(getBalance() - amount);
        return String.format("Retiro exitoso. Nuevo saldo: $%.2f", getBalance());
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; Intereses Acumulados: $%.2f; Último Depósito: %s por $%.2f", 
                accumulatedInterest, 
                lastDepositDate != null ? lastDepositDate : "N/A", 
                lastDepositAmount);
    }
}
