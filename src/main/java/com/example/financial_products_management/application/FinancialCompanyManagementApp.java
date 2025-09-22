package com.example.financial_products_management.application;

import com.example.financial_products_management.domain.customer.Customer;
import com.example.financial_products_management.domain.financial_company.FinancialCompany;
import com.example.financial_products_management.domain.financial_product.model.FinancialProduct;
import com.example.financial_products_management.domain.repository.FinancialCompanyRepository;
import com.example.financial_products_management.domain.repository.ProductRepository;
import com.example.financial_products_management.domain.shared.HousingClassification;
import com.example.financial_products_management.exception.InsufficientFundsException;
import com.example.financial_products_management.exception.ProductNotFoundException;
import com.example.financial_products_management.infrastructure.validation.InputValidator;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal de la aplicación de Gestión de Productos Financieros.
 * Maneja la interfaz de usuario.
 *
 * @author 200582 Alexander Sandoval
 * @since 2025-09-20
 */
public class FinancialCompanyManagementApp {
    private final FinancialCompany financialCompany;
    private final InputValidator validator;

    /**
     * Crea una nueva instancia de la aplicación.
     */
    public FinancialCompanyManagementApp() {
        ProductRepository repository = new FinancialCompanyRepository();
        this.financialCompany = new FinancialCompany(repository);
        Scanner scanner = new Scanner(System.in);
        this.validator = new InputValidator(scanner);
    }

    /**
     * Registra un mensaje con timestamp.
     *
     * @param message Mensaje a registrar
     */
    protected static void log(String message) {
        System.out.println("[" + new java.util.Date() + "] " + message);
    }

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        log("Inicializando sistema de la Entidad Financiera...\n");

        FinancialCompanyManagementApp app = new FinancialCompanyManagementApp();
        
        app.createInitialProducts();

        app.executeInitialOperations();

        app.showInteractiveMenu();

        log("\n¡Gracias por usar el Sistema de Gestión de Productos Financieros!");
    }

    /**
     * Crea productos financieros de ejemplo.
     */
    private void createInitialProducts() {
        try {
            // Create clients
            Customer customer1 = new Customer("12345678", "Juan Carlos", "Pérez García", "3001234567");
            Customer customer2 = new Customer("87654321", "María Elena", "González López", "3009876543");
            Customer customer3 = new Customer("11223344", "Carlos Alberto", "Rodríguez Martín", "3005551234");
            Customer customer4 = new Customer("44332211", "Ana Sofía", "Hernández Ruiz", "3007778888");

            log("Creando productos financieros...\n");

            // Create financial products (accounts)
            financialCompany.createSavingsAccount("001", customer1, 500000.0);
            financialCompany.createCheckingAccount("002", customer2, 1000000.0, 200000.0);
            financialCompany.createHousingSavingsAccount("003", customer3, 2000000.0, 150000000.0, HousingClassification.VIS);
            financialCompany.createSalaryAccount("004", customer4, 1500000.0, 2500000.0);

        } catch (Exception e) {
            System.err.println("Error creando datos de ejemplo: " + e.getMessage());
        }
    }

    /**
     * Ejecuta una demostración de las operaciones del sistema.
     */
    private void executeInitialOperations() {
        try {
            log("=== OPERACIONES INICIALES ===\n");

            // Savings Account operations
            log("--- Operaciones en Cuenta de Ahorro ---");
            FinancialProduct savings = financialCompany.consultByProductNumber("001");
            System.out.println("Estado inicial: " + savings);

            String result1 = financialCompany.performDeposit("001", 100000.0);
            System.out.println("Depósito de $100,000: " + result1);
            System.out.println("Estado actual: " + financialCompany.consultByProductNumber("001"));

            String result2 = financialCompany.performWithdrawal("001", 50000.0);
            System.out.println("Retiro de $50,000: " + result2);
            System.out.println("Estado final: " + financialCompany.consultByProductNumber("001") + "\n");

            // Checking Account operations
            log("--- Operaciones en Cuenta Corriente ---");
            FinancialProduct checking = financialCompany.consultByProductNumber("002");
            System.out.println("Estado inicial: " + checking);

            String result3 = financialCompany.performWithdrawal("002", 1100000.0);
            System.out.println("Retiro de $1,100,000 (con sobregiro): " + result3);
            System.out.println("Estado actual: " + financialCompany.consultByProductNumber("002"));

            // Try to exceed limit
            try {
                financialCompany.performWithdrawal("002", 200000.0);
            } catch (InsufficientFundsException e) {
                System.out.println("Intento de retiro de $200,000 adicionales: " + e.getMessage());
            }
            System.out.println("Estado final: " + financialCompany.consultByProductNumber("002") + "\n");

            // Housing Savings Account operations
            log("--- Operaciones en Cuenta de Ahorro Vivienda ---");
            FinancialProduct housing = financialCompany.consultByProductNumber("003");
            System.out.println("Estado inicial: " + housing);

            // Deposit in Housing Savings Account
            String result4 = financialCompany.performDeposit("003", 5000000.0);
            System.out.println("Depósito de $5,000,000: " + result4);
            System.out.println("Estado actual: " + financialCompany.consultByProductNumber("003"));

            // Withdrawal from Housing Savings Account
            try {
                financialCompany.performWithdrawal("003", 1000000.0);
            } catch (UnsupportedOperationException e) {
                System.out.println("Intento de retiro en Cuenta de Ahorro Vivienda: " + e.getMessage());
            }
            System.out.println("Estado final: " + financialCompany.consultByProductNumber("003") + "\n");
        } catch (Exception e) {
            System.err.println("Error en la demostración: " + e.getMessage());
        }
    }

    /**
     * Muestra el menú interactivo y gestiona la navegación.
     */
    public void showInteractiveMenu() {
        log("=== INICIANDO MENÚ INTERACTIVO ===\n");

        int option;
        do {
            System.out.println("\n=== MENU INTERACTIVO ===");
            System.out.println("1. Crear nueva cuenta");
            System.out.println("2. Realizar operación (depósito/retiro)");
            System.out.println("3. Consultar producto por número");
            System.out.println("4. Consultar producto por titular");
            System.out.println("5. Lista de todos los titulares");
            System.out.println("0. Salir");

            option = validator.getValidInteger("Seleccione una opción: ");

            try {
                switch (option) {
                    case 1 -> createAccountMenu();
                    case 2 -> performOperationMenu();
                    case 3 -> consultByProductNumberMenu();
                    case 4 -> consultByCustomerMenu();
                    case 5 -> listAllCustomersMenu();
                    case 0 -> System.out.println("¡Gracias por usar nuestros servicios!");
                    default -> System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }

    /**
     * Menú para crear nuevas cuentas.
     */
    private void createAccountMenu() {
        try {
            System.out.println("\n=== CREAR NUEVA CUENTA ===");
            System.out.println("1. Cuenta de Ahorro");
            System.out.println("2. Cuenta Corriente");
            System.out.println("3. Cuenta de Ahorro Programado para Vivienda");
            System.out.println("4. Cuenta Especial para Pago de Salario");

            int accountType = validator.getValidPositiveInteger("Seleccione el tipo de cuenta: ");

            // Datos comunes
            ProductRepository repository = new FinancialCompanyRepository();
            String productNumber = validator.getValidUniqueProductNumber("Ingrese el número del producto: ", repository);
            Customer customer = createCustomerMenu();
            double initialBalance = validator.getValidAmount("Ingrese el saldo inicial: ");

            FinancialProduct product = switch (accountType) {
                case 1 -> financialCompany.createSavingsAccount(productNumber, customer, initialBalance);
                case 2 -> {
                    double overdraftLimit = validator.getValidAmount("Ingrese el límite de sobregiro: ");
                    yield financialCompany.createCheckingAccount(productNumber, customer, initialBalance, overdraftLimit);
                }
                case 3 -> {
                    double housingValue = validator.getValidAmount("Ingrese el valor total de la vivienda: ");
                    HousingClassification classification = getHousingClassificationMenu();
                    yield financialCompany.createHousingSavingsAccount(productNumber, customer, initialBalance, housingValue, classification);
                }
                case 4 -> {
                    double salaryAmount = validator.getValidAmount("Ingrese el valor del salario: ");
                    yield financialCompany.createSalaryAccount(productNumber, customer, initialBalance, salaryAmount);
                }
                default -> {
                    System.out.println("Tipo de cuenta no válido.");
                    yield null;
                }
            };

            if (product != null) {
                System.out.println("Cuenta creada exitosamente:");
                System.out.println(product);
            }

        } catch (Exception e) {
            System.err.println("Error al crear la cuenta: " + e.getMessage());
        }
    }

    /**
     * Menú para realizar operaciones bancarias.
     */
    private void performOperationMenu() {
        try {
            System.out.println("\n=== REALIZAR OPERACIÓN ===");
            String productNumber = validator.getValidString("Ingrese el número del producto: ");

            FinancialProduct product = financialCompany.consultByProductNumber(productNumber);

            // Mostrar datos del producto
            System.out.println("\nDatos del producto:");
            System.out.println("Cliente: " + product.getCustomer());
            System.out.println("Producto: " + product);

            System.out.println("\n1. Depósito");
            System.out.println("2. Retiro");
            int operationType = validator.getValidPositiveInteger("Seleccione la operación: ");

            if (operationType != 1 && operationType != 2) {
                System.out.println("Operación no válida.");
                return;
            }

            double amount = validator.getValidAmount("Ingrese el monto: ");
            String result = switch (operationType) {
                case 1 -> financialCompany.performDeposit(productNumber, amount);
                case 2 -> financialCompany.performWithdrawal(productNumber, amount);
                default -> throw new IllegalStateException("Tipo de operación inválido: " + operationType);
            };
            System.out.println("Resultado: " + result);

        } catch (ProductNotFoundException e) {
            System.err.println("Producto no encontrado: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.err.println("Fondos insuficientes: " + e.getMessage());
        } catch (UnsupportedOperationException e) {
            System.err.println("Operación no soportada: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Datos inválidos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al realizar la operación: " + e.getMessage());
        }
    }

    /**
     * Menú para consultar producto por número.
     */
    private void consultByProductNumberMenu() {
        try {
            System.out.println("\n=== CONSULTAR POR NÚMERO DE PRODUCTO ===");
            String productNumber = validator.getValidString("Ingrese el número del producto: ");

            FinancialProduct product = financialCompany.consultByProductNumber(productNumber);

            System.out.println("\nDatos del titular:");
            System.out.println(product.getCustomer());
            System.out.println("\nDatos del producto:");
            System.out.println(product);

        } catch (ProductNotFoundException e) {
            System.err.println("Producto no encontrado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al consultar el producto: " + e.getMessage());
        }
    }

    /**
     * Menú para consultar productos por titular.
     */
    private void consultByCustomerMenu() {
        try {
            System.out.println("\n=== CONSULTAR POR TITULAR ===");
            String identification = validator.getValidString("Ingrese la identificación del titular: ");

            FinancialProduct product = financialCompany.consultByCustomer(identification);

            System.out.println("\nProducto encontrado:");
            System.out.println("\nDatos del titular:");
            System.out.println(product.getCustomer());
            System.out.println("Datos del producto:");
            System.out.println(product);

        } catch (Exception e) {
            System.err.println("Error al consultar por titular: " + e.getMessage());
        }
    }

    /**
     * Menú para listar todos los clientes.
     */
    private void listAllCustomersMenu() {
        try {
            System.out.println("\n=== LISTA DE TODOS LOS TITULARES ===");

            List<FinancialProduct> products = financialCompany.getAllProducts();

            if (products.isEmpty()) {
                System.out.println("No hay productos registrados en la financiera.");
                return;
            }

            System.out.printf("%-40s %-15s %-15s %-35s %-15s%n",
                    "Datos del Titular", "Número Producto", "Fecha Apertura", "Tipo de Producto", "Saldo");
            System.out.println("=".repeat(120));

            for (FinancialProduct product : products) {
                System.out.printf("%-40s %-15s %-15s %-35s $%-14.2f%n",
                        product.getCustomer().toString(),
                        product.getProductNumber(),
                        product.getOpeningDate(),
                        product.getProductType(),
                        product.getBalance());
            }

        } catch (Exception e) {
            System.err.println("Error al listar los titulares: " + e.getMessage());
        }
    }

    /**
     * Menú para crear un nuevo cliente.
     */
    private Customer createCustomerMenu() {
        System.out.println("\n--- Datos del titular ---");

        String identification = validator.getValidString("Ingrese la identificación: ");
        String firstName = validator.getValidString("Ingrese los nombres: ");
        String lastName = validator.getValidString("Ingrese los apellidos: ");
        String phone = validator.getValidString("Ingrese el teléfono: ");

        return new Customer(identification, firstName, lastName, phone);
    }

    /**
     * Menú para seleccionar clasificación de vivienda.
     */
    private HousingClassification getHousingClassificationMenu() {
        System.out.println("\nClasificaciones de vivienda:");
        System.out.println("1. VIS (Vivienda de Interés Social)");
        System.out.println("2. VIP (Vivienda de Interés Propietario)");
        System.out.println("3. VIS Renovación (Viviendas de Interés Social de Renovación)");
        System.out.println("4. No VIS (No de Interés Social)");

        int option = validator.getValidPositiveInteger("Seleccione la clasificación: ");

        return switch (option) {
            case 1 -> HousingClassification.VIS;
            case 2 -> HousingClassification.VIP;
            case 3 -> HousingClassification.VIS_RENOVACION;
            case 4 -> HousingClassification.NO_VIS;
            default -> {
                System.out.println("Opción no válida. Se asignará VIS por defecto.");
                yield HousingClassification.VIS;
            }
        };
    }
}
