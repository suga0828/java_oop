package com.example.financial_products_management.domain.shared;

import com.example.financial_products_management.exception.InsufficientFundsException;

/**
 * Interfaz que define la capacidad de realizar retiros.
 * Implementa el principio de segregación de interfaces (ISP).
 */
public interface Withdrawable {
    
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
