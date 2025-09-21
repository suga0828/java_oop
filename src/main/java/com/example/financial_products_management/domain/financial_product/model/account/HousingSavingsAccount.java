package com.example.financial_products_management.domain.financial_product.model.account;

import com.example.financial_products_management.domain.customer.Customer;
import com.example.financial_products_management.domain.financial_product.model.FinancialProduct;
import com.example.financial_products_management.domain.shared.HousingClassification;
import com.example.financial_products_management.exception.InsufficientFundsException;
import java.time.LocalDate;

/**
 * Representa una cuenta de ahorro programado para vivienda.
 * Incluye valor total de la vivienda, clasificación comercial, fecha y valor del último pago.
 * Valida que el valor total pagado no supere el valor total de la vivienda.
 *
 * @author 200582 Alexander Sandoval
 * @since 2025-09-20
 */
public class HousingSavingsAccount extends FinancialProduct {
    private final double totalHousingValue;
    private final HousingClassification classification;
    private LocalDate lastPaymentDate;
    private double lastPaymentAmount;
    private double totalPaid;

    /**
     * Crea una nueva instancia de Cuenta de Ahorro Programado para Vivienda.
     *
     * @param productNumber      Número único del producto
     * @param openingDate        Fecha de apertura
     * @param customer           Datos del titular
     * @param balance            Saldo inicial
     * @param totalHousingValue  Valor total de la vivienda
     * @param classification     Clasificación comercial de la vivienda
     */
    public HousingSavingsAccount(String productNumber, LocalDate openingDate, Customer customer, 
                                double balance, double totalHousingValue, HousingClassification classification) {
        super(productNumber, openingDate, customer, balance);
        this.totalHousingValue = totalHousingValue;
        this.classification = classification;
        this.lastPaymentDate = null;
        this.lastPaymentAmount = 0.0;
        this.totalPaid = 0.0;
    }

    /**
     * Obtiene el valor total de la vivienda.
     *
     * @return Valor total de la vivienda
     */
    public double getTotalHousingValue() {
        return totalHousingValue;
    }

    /**
     * Obtiene la clasificación comercial de la vivienda.
     *
     * @return Clasificación comercial
     */
    public HousingClassification getClassification() {
        return classification;
    }

    /**
     * Obtiene la fecha del último pago.
     *
     * @return Fecha del último pago
     */
    public LocalDate getLastPaymentDate() {
        return lastPaymentDate;
    }

    /**
     * Obtiene el valor del último pago.
     *
     * @return Valor del último pago
     */
    public double getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    /**
     * Obtiene el total pagado hasta el momento.
     *
     * @return Total pagado
     */
    public double getTotalPaid() {
        return totalPaid;
    }

    @Override
    public String getProductType() {
        return "Cuenta de Ahorro Programado para Vivienda";
    }

    /**
     * Realiza una operación de depósito (pago).
     * Valida que el valor total pagado no supere el valor total de la vivienda.
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
        totalPaid += amount;
        lastPaymentDate = LocalDate.now();
        lastPaymentAmount = amount;

        // Validar si el valor total pagado supera el valor de la vivienda
        if (totalPaid > totalHousingValue) {
            return String.format("Depósito exitoso. Nuevo saldo: $%.2f. VALOR TOTAL PAGADO SUPERA EL VALOR DE LA VIVIENDA.", 
                    getBalance());
        }

        return String.format("Depósito exitoso. Nuevo saldo: $%.2f. Total pagado: $%.2f de $%.2f", 
                getBalance(), totalPaid, totalHousingValue);
    }

    /**
     * Realiza una operación de retiro.
     * En cuentas de ahorro programado para vivienda, los retiros están restringidos.
     *
     * @param amount Monto a retirar
     * @return Mensaje resultado de la operación
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
        return super.toString() + String.format("; Valor Vivienda: $%.2f; Clasificación: %s; Total Pagado: $%.2f; Último Pago: %s por $%.2f", 
                totalHousingValue,
                classification,
                totalPaid,
                lastPaymentDate != null ? lastPaymentDate : "N/A", 
                lastPaymentAmount);
    }
}
