package com.example.financial_products_management.exception;

/**
 * Excepción personalizada para casos donde no se encuentra un producto.
 */
public class ProductNotFoundException extends Exception {
    
    /**
     * Crea una nueva excepción de producto no encontrado.
     *
     * @param message Mensaje descriptivo del error
     */
    public ProductNotFoundException(String message) {
        super(message);
    }

}
