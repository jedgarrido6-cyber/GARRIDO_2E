package ui;

import dao.UserDao;
import model.User;
import java.util.Scanner;

public class MenuUI {
    private final UserDao userDao = new UserDao();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n===============================");
            System.out.println("   PARKSMART - AUTHENTICATION");
            System.out.println("===============================");
            System.out.println("1. Register Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleRegister();
                    break;
                case "2":
                    handleLogin();
                    break;
                case "3":
                    System.out.println("Exiting application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void handleRegister() {
        System.out.println("\n--- REGISTER ACCOUNT ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        System.out.println("Select Role:");
        System.out.println("1. TEACHER");
        System.out.println("2. STUDENT");
        System.out.print("Choice (1/2): ");
        String roleChoice = scanner.nextLine().trim();

        String role = roleChoice.equals("1") ? "TEACHER" : "STUDENT";

        boolean success = userDao.registerUser(username, password, role);
        if (success) {
            System.out.println("Registration successful! Account status is PENDING.");
        }
    }

    private void handleLogin() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        User user = userDao.loginUser(username, password);
        if (user != null) {
            System.out.println("\nLogin Successful! Welcome, " + user.getUsername());
            System.out.println("Role: " + user.getRoles() + " | Status: " + user.getStatus());
            loggedInMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void loggedInMenu() {
        while (true) {
            System.out.println("\n--- USER DASHBOARD ---");
            System.out.println("1. View Profile Info");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.println("You are successfully logged into your session.");
            } else if (choice.equals("2")) {
                System.out.println("Logged out successfully.");
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}