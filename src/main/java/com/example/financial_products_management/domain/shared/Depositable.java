package com.example.financial_products_management.domain.shared;

/**
 * Interfaz que define la capacidad de realizar depósitos.
 * Implementa el principio de segregación de interfaces (ISP).
 */
public interface Depositable {
    
    /**
     * Realiza una operación de depósito.
     *
     * @param amount Monto a depositar
     * @return Mensaje resultado de la operación
     * @throws IllegalArgumentException Si el monto es inválido
     */
    String deposit(double amount) throws IllegalArgumentException;
}
