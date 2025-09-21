package com.example.financial_products_management.domain.repository;

import com.example.financial_products_management.domain.financial_product.model.FinancialProduct;
import java.util.List;

/**
 * Interfaz que define las operaciones de repositorio para productos financieros.
 */
public interface ProductRepository {
    
    /**
     * Agrega un producto financiero al repositorio.
     *
     * @param product Producto a agregar
     * @throws IllegalArgumentException Si el producto es nulo o inválido
     */
    void addProduct(FinancialProduct product) throws IllegalArgumentException;
    
    /**
     * Busca un producto por su número.
     *
     * @param productNumber Número del producto
     * @return Producto encontrado o null si no existe
     */
    FinancialProduct findByProductNumber(String productNumber);
    
    /**
     * Busca el producto de un cliente por su identificación.
     * Cada cliente puede tener solo un producto.
     *
     * @param customerIdentification Identificación del cliente
     * @return Producto del cliente o null si no existe
     */
    FinancialProduct findByCustomerIdentification(String customerIdentification);
    
    /**
     * Verifica si un cliente ya tiene un producto registrado.
     *
     * @param customerIdentification Identificación del cliente
     * @return true si el cliente ya tiene un producto, false en caso contrario
     */
    boolean existsByCustomerIdentification(String customerIdentification);
    
    /**
     * Obtiene todos los productos.
     *
     * @return Lista de todos los productos
     */
    List<FinancialProduct> getAllProducts();
    
    /**
     * Verifica si existe un producto con el número dado.
     *
     * @param productNumber Número del producto
     * @return true si existe, false en caso contrario
     */
    boolean existsByProductNumber(String productNumber);
}
