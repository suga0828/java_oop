package com.example.financial_products_management.domain.financial_product.model.account;

import com.example.financial_products_management.domain.customer.Customer;
import com.example.financial_products_management.domain.financial_product.model.FinancialProduct;
import com.example.financial_products_management.exception.InsufficientFundsException;
import java.time.LocalDate;

/**
 * Representa una cuenta especial para pago de salario.
 * Incluye valor pagado como salario, fecha del último retiro y valor del último retiro.
 * Los retiros no deben superar al valor pagado como salario.
 *
 * @author 200582 Alexander Sandoval
 * @since 2025-09-20
 */
public class SalaryAccount extends FinancialProduct {
    private double salaryAmount;
    private LocalDate lastWithdrawalDate;
    private double lastWithdrawalAmount;

    /**
     * Crea una nueva instancia de Cuenta Especial para Pago de Salario.
     *
     * @param productNumber Número único del producto
     * @param openingDate   Fecha de apertura
     * @param customer      Datos del titular
     * @param balance       Saldo inicial
     * @param salaryAmount  Valor pagado como salario
     */
    public SalaryAccount(String productNumber, LocalDate openingDate, Customer customer, double balance, double salaryAmount) {
        super(productNumber, openingDate, customer, balance);
        this.salaryAmount = salaryAmount;
        this.lastWithdrawalDate = null;
        this.lastWithdrawalAmount = 0.0;
    }

    /**
     * Obtiene el valor pagado como salario.
     *
     * @return Valor del salario
     */
    public double getSalaryAmount() {
        return salaryAmount;
    }

    /**
     * Establece el valor pagado como salario.
     *
     * @param salaryAmount Nuevo valor del salario
     */
    public void setSalaryAmount(double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    /**
     * Obtiene la fecha del último retiro.
     *
     * @return Fecha del último retiro
     */
    public LocalDate getLastWithdrawalDate() {
        return lastWithdrawalDate;
    }

    /**
     * Obtiene el valor del último retiro.
     *
     * @return Valor del último retiro
     */
    public double getLastWithdrawalAmount() {
        return lastWithdrawalAmount;
    }

    @Override
    public String getProductType() {
        return "Cuenta Especial para Pago de Salario";
    }

    /**
     * Realiza una operación de depósito.
     *
     * @param amount Monto a depositar
     * @return Mensaje resultado de la operación
     */
    @Override
    public String deposit(double amount) {
        if (amount <= 0) {
            return "El monto a depositar debe ser mayor a cero.";
        }

        setBalance(getBalance() + amount);

        return String.format("Depósito exitoso. Nuevo saldo: $%.2f", getBalance());
    }

    /**
     * Realiza una operación de retiro.
     * Valida que el retiro no supere al valor pagado como salario y que no supere al saldo.
     *
     * @param amount Monto a retirar
     * @return Mensaje resultado de la operación
     */
    @Override
    public String withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero");
        }

        // Validar que el retiro no supere al saldo
        if (amount > getBalance()) {
            throw new InsufficientFundsException();
        }

        // Validar que el retiro no supere al valor pagado como salario (regla de negocio)
        if (amount > salaryAmount) {
            throw new IllegalArgumentException(
                String.format("El retiro ($%.2f) no puede superar el límite del salario ($%.2f)", 
                    amount, salaryAmount));
        }

        setBalance(getBalance() - amount);
        lastWithdrawalDate = LocalDate.now();
        lastWithdrawalAmount = amount;

        return String.format("Retiro exitoso. Nuevo saldo: $%.2f", getBalance());
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; Salario: $%.2f; Último Retiro: %s por $%.2f", 
                salaryAmount,
                lastWithdrawalDate != null ? lastWithdrawalDate : "N/A", 
                lastWithdrawalAmount);
    }
}
