package com.example.financial_products_management.infrastructure.validation;

import com.example.financial_products_management.domain.repository.ProductRepository;
import java.util.Scanner;

/**
 * Clase utilitaria para validación de entrada de datos.
 * Centraliza la lógica de validación y simplifica el código.
 */
public class InputValidator {
    private final Scanner scanner;
    
    /**
     * Crea un nuevo validador de entrada.
     *
     * @param scanner Scanner para leer entrada del usuario
     */
    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }
    
    /**
     * Obtiene una cadena válida (no vacía) del usuario.
     *
     * @param prompt Mensaje a mostrar al usuario
     * @return Cadena válida ingresada por el usuario
     */
    public String getValidString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Este campo no puede estar vacío.");
            }
        } while (input.isEmpty());
        return input;
    }
    
    /**
     * Obtiene un número entero válido del usuario.
     *
     * @param prompt Mensaje a mostrar al usuario
     * @return Número entero válido
     */
    public int getValidInteger(String prompt) {
        int value;
        do {
            try {
                System.out.print(prompt);
                value = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        } while (true);
        return value;
    }
    
    /**
     * Obtiene un número entero positivo válido del usuario.
     *
     * @param prompt Mensaje a mostrar al usuario
     * @return Número entero positivo válido
     */
    public int getValidPositiveInteger(String prompt) {
        int value;
        do {
            value = getValidInteger(prompt);
            if (value <= 0) {
                System.out.println("El número debe ser positivo.");
            }
        } while (value <= 0);
        return value;
    }
    
    /**
     * Obtiene un monto válido (positivo) del usuario.
     *
     * @param prompt Mensaje a mostrar al usuario
     * @return Monto válido ingresado por el usuario
     */
    public double getValidAmount(String prompt) {
        double amount;
        do {
            try {
                System.out.print(prompt);
                amount = Double.parseDouble(scanner.nextLine().trim());
                if (amount < 0) {
                    System.out.println("El monto debe ser un número positivo.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        } while (true);
        return amount;
    }
    
    /**
     * Obtiene un número de producto único válido.
     *
     * @param prompt Mensaje a mostrar al usuario
     * @param repository Repositorio para verificar unicidad
     * @return Número de producto válido y único
     */
    public String getValidUniqueProductNumber(String prompt, ProductRepository repository) {
        String productNumber;
        do {
            productNumber = getValidString(prompt);
            
            try {
                Integer.parseInt(productNumber);
            } catch (NumberFormatException e) {
                System.out.println("El número del producto debe ser un número entero positivo.");
                continue;
            }
            
            if (repository.existsByProductNumber(productNumber)) {
                System.out.println("Ya existe un producto con ese número. Ingrese uno diferente.");
                continue;
            }
            
            break;
        } while (true);
        
        return productNumber;
    }
}
