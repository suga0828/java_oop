package com.example.financial_products_management.exception;

/**
 * Excepción personalizada para casos de fondos insuficientes.
 * Maneja internamente los mensajes de error según el contexto.
 */
public class InsufficientFundsException extends Exception {
    
    private static final String DEFAULT_MESSAGE = "SALDO INSUFICIENTE";
    private static final String OVERDRAFT_MESSAGE = "SALDO INSUFICIENTE - Límite de sobregiro excedido";
    
    /**
     * Crea una excepción de fondos insuficientes con mensaje por defecto.
     */
    public InsufficientFundsException() {
        super(DEFAULT_MESSAGE);
    }
    
    /**
     * Crea una excepción de fondos insuficientes para cuentas corrientes con sobregiro.
     *
     * @param currentBalance Saldo actual
     * @param overdraftLimit Límite de sobregiro
     * @param attemptedAmount Monto que se intentó retirar
     */
    public InsufficientFundsException(double currentBalance, double overdraftLimit, double attemptedAmount) {
        super(String.format("%s (Saldo: $%.2f, Sobregiro: $%.2f, Intentado: $%.2f)", 
                OVERDRAFT_MESSAGE, currentBalance, overdraftLimit, attemptedAmount));
    }
    
    
    /**
     * Crea una excepción de fondos insuficientes con mensaje personalizado.
     * Usar solo cuando los constructores específicos no sean apropiados.
     *
     * @param customMessage Mensaje personalizado
     */
    public InsufficientFundsException(String customMessage) {
        super(customMessage);
    }
    
    /**
     * Crea una excepción de fondos insuficientes con causa.
     *
     * @param cause Causa original de la excepción
     */
    public InsufficientFundsException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
