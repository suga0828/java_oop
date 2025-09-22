package com.example.financial_products_management.domain.financial_company;

import com.example.financial_products_management.domain.customer.Customer;
import com.example.financial_products_management.domain.financial_product.model.FinancialProduct;
import com.example.financial_products_management.domain.financial_product.model.account.*;
import com.example.financial_products_management.domain.repository.ProductRepository;
import com.example.financial_products_management.domain.shared.*;
import com.example.financial_products_management.exception.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase de servicio que gestiona las operaciones de negocio de productos financieros.
 * Utiliza el patrón Repository para el acceso a datos y se enfoca únicamente en la lógica de negocio.
 */
public class FinancialCompany {
    private final ProductRepository repository;

    /**
     * Crea una nueva instancia de FinancialCompany.
     *
     * @param repository Repositorio de productos financieros
     */
    public FinancialCompany(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Crea una nueva cuenta de ahorro.
     *
     * @param productNumber Número del producto
     * @param customer Cliente titular
     * @param initialBalance Saldo inicial
     * @return Producto creado
     * @throws IllegalArgumentException Si los parámetros son inválidos
     */
    public SavingsAccount createSavingsAccount(String productNumber, Customer customer, double initialBalance) 
            throws IllegalArgumentException {
        validateProductCreation(productNumber, customer, initialBalance);
        
        SavingsAccount account = new SavingsAccount(productNumber, LocalDate.now(), customer, initialBalance);
        repository.addProduct(account);
        return account;
    }

    /**
     * Crea una nueva cuenta corriente.
     *
     * @param productNumber Número del producto
     * @param customer Cliente titular
     * @param initialBalance Saldo inicial
     * @param overdraftLimit Límite de sobregiro
     * @return Producto creado
     * @throws IllegalArgumentException Si los parámetros son inválidos
     */
    public CheckingAccount createCheckingAccount(String productNumber, Customer customer, 
            double initialBalance, double overdraftLimit) throws IllegalArgumentException {
        validateProductCreation(productNumber, customer, initialBalance);
        
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("El límite de sobregiro no puede ser negativo");
        }
        
        CheckingAccount account = new CheckingAccount(productNumber, LocalDate.now(), customer, initialBalance, overdraftLimit);
        repository.addProduct(account);
        return account;
    }

    /**
     * Crea una nueva cuenta de ahorro programado para vivienda.
     *
     * @param productNumber Número del producto
     * @param customer Cliente titular
     * @param initialBalance Saldo inicial
     * @param housingValue Valor total de la vivienda
     * @param classification Clasificación de la vivienda
     * @return Producto creado
     * @throws IllegalArgumentException Si los parámetros son inválidos
     */
    public HousingSavingsAccount createHousingSavingsAccount(String productNumber, Customer customer, 
            double initialBalance, double housingValue, HousingClassification classification) 
            throws IllegalArgumentException {
        validateProductCreation(productNumber, customer, initialBalance);
        
        if (housingValue <= 0) {
            throw new IllegalArgumentException("El valor de la vivienda debe ser positivo");
        }
        
        if (classification == null) {
            throw new IllegalArgumentException("La clasificación de vivienda es obligatoria");
        }
        
        HousingSavingsAccount account = new HousingSavingsAccount(productNumber, LocalDate.now(), 
                customer, initialBalance, housingValue, classification);
        repository.addProduct(account);
        return account;
    }

    /**
     * Crea una nueva cuenta especial para pago de salario.
     *
     * @param productNumber Número del producto
     * @param customer Cliente titular
     * @param initialBalance Saldo inicial
     * @param salaryAmount Valor del salario
     * @return Producto creado
     * @throws IllegalArgumentException Si los parámetros son inválidos
     */
    public SalaryAccount createSalaryAccount(String productNumber, Customer customer, 
            double initialBalance, double salaryAmount) throws IllegalArgumentException {
        validateProductCreation(productNumber, customer, initialBalance);
        
        if (salaryAmount <= 0) {
            throw new IllegalArgumentException("El valor del salario debe ser positivo");
        }
        
        SalaryAccount account = new SalaryAccount(productNumber, LocalDate.now(), customer, initialBalance, salaryAmount);
        repository.addProduct(account);
        return account;
    }

    /**
     * Realiza una operación de depósito en un producto.
     *
     * @param productNumber Número del producto
     * @param amount Monto a depositar
     * @return Mensaje resultado de la operación
     * @throws ProductNotFoundException Si el producto no existe
     * @throws IllegalArgumentException Si los parámetros son inválidos
     */
    public String performDeposit(String productNumber, double amount) 
            throws ProductNotFoundException, IllegalArgumentException {
        FinancialProduct product = findProduct(productNumber);
        return product.deposit(amount);
    }

    /**
     * Realiza una operación de retiro en un producto.
     *
     * @param productNumber Número del producto
     * @param amount Monto a retirar
     * @return Mensaje resultado de la operación
     * @throws ProductNotFoundException Si el producto no existe
     * @throws IllegalArgumentException Si los parámetros son inválidos
     * @throws InsufficientFundsException Si no hay fondos suficientes
     */
    public String performWithdrawal(String productNumber, double amount) 
            throws ProductNotFoundException, IllegalArgumentException, InsufficientFundsException {
        FinancialProduct product = findProduct(productNumber);
        return product.withdraw(amount);
    }

    /**
     * Realiza una operación bancaria usando el enum OperationType.
     *
     * @param productNumber Número del producto
     * @param operationType Tipo de operación
     * @param amount Monto de la operación
     * @return Mensaje resultado de la operación
     * @throws ProductNotFoundException Si el producto no existe
     * @throws IllegalArgumentException Si los parámetros son inválidos
     * @throws InsufficientFundsException Si no hay fondos suficientes
     */
    public String performOperation(String productNumber, OperationType operationType, double amount) 
            throws ProductNotFoundException, IllegalArgumentException, InsufficientFundsException {
        
        return switch (operationType) {
            case DEPOSIT -> performDeposit(productNumber, amount);
            case WITHDRAWAL -> performWithdrawal(productNumber, amount);
        };
    }

    /**
     * Método auxiliar para encontrar un producto y manejar la excepción.
     *
     * @param productNumber Número del producto
     * @return Producto encontrado
     * @throws ProductNotFoundException Si el producto no existe
     */
    private FinancialProduct findProduct(String productNumber) throws ProductNotFoundException {
        FinancialProduct product = repository.findByProductNumber(productNumber);
        
        if (product == null) {
            throw new ProductNotFoundException("No se encontró un producto con el número: " + productNumber);
        }
        
        return product;
    }

    /**
     * Consulta un producto por su número.
     *
     * @param productNumber Número del producto
     * @return Producto encontrado
     * @throws ProductNotFoundException Si el producto no existe
     */
    public FinancialProduct consultByProductNumber(String productNumber) throws ProductNotFoundException {
        FinancialProduct product = repository.findByProductNumber(productNumber);
        
        if (product == null) {
            throw new ProductNotFoundException("No se encontró un producto con el número: " + productNumber);
        }
        
        return product;
    }

    /**
     * Consulta el producto de un cliente por su identificación.
     * Cada cliente puede tener solo un producto.
     *
     * @param customerIdentification Identificación del cliente
     * @return Producto del cliente
     * @throws ProductNotFoundException Si el cliente no tiene productos
     */
    public FinancialProduct consultByCustomer(String customerIdentification) throws ProductNotFoundException {
        FinancialProduct product = repository.findByCustomerIdentification(customerIdentification);
        
        if (product == null) {
            throw new ProductNotFoundException("No se encontró un producto para el cliente con identificación: " + customerIdentification);
        }
        
        return product;
    }

    /**
     * Obtiene todos los productos registrados.
     *
     * @return Lista de todos los productos
     */
    public List<FinancialProduct> getAllProducts() {
        return repository.getAllProducts();
    }

    /**
     * Valida los parámetros comunes para la creación de productos.
     *
     * @param productNumber Número del producto
     * @param customer Cliente titular
     * @param initialBalance Saldo inicial
     * @throws IllegalArgumentException Si algún parámetro es inválido
     */
    private void validateProductCreation(String productNumber, Customer customer, double initialBalance) 
            throws IllegalArgumentException {
        if (productNumber == null || productNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El número del producto es obligatorio");
        }
        
        if (customer == null) {
            throw new IllegalArgumentException("Los datos del cliente son obligatorios");
        }
        
        if (initialBalance < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        
        try {
            Integer.parseInt(productNumber.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El número del producto debe ser un número entero válido");
        }
    }

}
