package com.example.financial_products_management.domain.shared;

import com.example.financial_products_management.exception.InsufficientFundsException;

/**
 * Interfaz que define las operaciones bancarias básicas.
 * Implementa el principio de segregación de interfaces (ISP).
 */
public interface BankingOperations {
    
    /**
     * Realiza una operación de depósito.
     *
     * @param amount Monto a depositar
     * @return Mensaje resultado de la operación
     * @throws IllegalArgumentException Si el monto es inválido
     */
    String deposit(double amount) throws IllegalArgumentException;
    
    /**
     * Realiza una operación de retiro.
     *
     * @param amount Monto a retirar
     * @return Mensaje resultado de la operación
     * @throws IllegalArgumentException Si el monto es inválido
     * @throws InsufficientFundsException Si no hay fondos suficientes
     */
    String withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException;
}
