package com.example.financial_products_management.domain.repository;

import com.example.financial_products_management.domain.financial_product.model.FinancialProduct;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del repositorio de productos financieros.
 * Gestiona el almacenamiento y consulta de productos financieros de manera simplificada.
 * Implementa el patrón Repository para separar la lógica de acceso a datos.
 *
 * @author 200582 Alexander Sandoval
 * @since 2025-09-20
 */
public class FinancialCompanyRepository implements ProductRepository {
    private final List<FinancialProduct> products = new ArrayList<>();

    /**
     * Agrega un producto financiero al repositorio.
     * Valida que el cliente no tenga ya un producto registrado.
     *
     * @param product Producto a agregar
     * @throws IllegalArgumentException Si el producto es nulo, ya existe, o el cliente ya tiene un producto
     */
    @Override
    public void addProduct(FinancialProduct product) throws IllegalArgumentException {
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        
        if (existsByProductNumber(product.getProductNumber())) {
            throw new IllegalArgumentException("Ya existe un producto con el número: " + product.getProductNumber());
        }
        
        if (existsByCustomerIdentification(product.getCustomer().getIdentification())) {
            throw new IllegalArgumentException("El cliente ya tiene un producto registrado. Cada cliente puede tener solo un producto.");
        }
        
        products.add(product);
    }

    /**
     * Busca un producto por su número.
     *
     * @param productNumber Número del producto
     * @return Producto encontrado o null si no existe
     */
    @Override
    public FinancialProduct findByProductNumber(String productNumber) {
        if (productNumber == null || productNumber.trim().isEmpty()) {
            return null;
        }
        
        return products.stream()
                .filter(product -> product.getProductNumber().equals(productNumber.trim()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca el producto de un cliente por su identificación.
     * Cada cliente puede tener solo un producto.
     *
     * @param customerIdentification Identificación del cliente
     * @return Producto del cliente o null si no existe
     */
    @Override
    public FinancialProduct findByCustomerIdentification(String customerIdentification) {
        if (customerIdentification == null || customerIdentification.trim().isEmpty()) {
            return null;
        }
        
        return products.stream()
                .filter(product -> product.getCustomer().getIdentification().equals(customerIdentification.trim()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Verifica si un cliente ya tiene un producto registrado.
     *
     * @param customerIdentification Identificación del cliente
     * @return true si el cliente ya tiene un producto, false en caso contrario
     */
    @Override
    public boolean existsByCustomerIdentification(String customerIdentification) {
        return findByCustomerIdentification(customerIdentification) != null;
    }

    /**
     * Obtiene todos los productos.
     *
     * @return Lista de todos los productos
     */
    @Override
    public List<FinancialProduct> getAllProducts() {
        return new ArrayList<>(products);
    }

    /**
     * Verifica si existe un producto con el número dado.
     *
     * @param productNumber Número del producto
     * @return true si existe, false en caso contrario
     */
    @Override
    public boolean existsByProductNumber(String productNumber) {
        return findByProductNumber(productNumber) != null;
    }

}
