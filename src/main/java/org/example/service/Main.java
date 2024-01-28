package org.example.service;

import org.example.model.User;
import org.example.service.FileHandler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler("userData.txt");

        System.out.println("--Welcome to the user management system!--");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1 - View data");
            System.out.println("2 - Add new user");
            System.out.println("3 - Delete user by ID");
            System.out.println("4 - Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear the scanner buffer

            switch (choice) {
                case 1:
                    fileHandler.readData();
                    break;
                case 2:
                    addNewUser(scanner, fileHandler);
                    break;
                case 3:
                    deleteUserById(scanner, fileHandler);
                    break;
                case 4:
                    System.out.println("System terminated. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addNewUser(Scanner scanner, FileHandler fileHandler) {
        System.out.println("Enter the full name: ");
        String fullName = scanner.nextLine();

        System.out.println("Enter the age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the profession: ");
        String profession = scanner.nextLine();

        User newUser = new User(fullName, age, profession);
        fileHandler.writeData(newUser);
        System.out.println("New user added successfully!");
    }

    private static void deleteUserById(Scanner scanner, FileHandler fileHandler) {
        System.out.println("Enter the ID of the user to delete: ");
        int userIdToDelete = scanner.nextInt();
        scanner.nextLine();

        if (fileHandler.deleteUserById(userIdToDelete)) {
            System.out.println("User with ID " + userIdToDelete + " deleted successfully.");
        } else {
            System.out.println("User with ID " + userIdToDelete + " not found.");
        }
    }
}
