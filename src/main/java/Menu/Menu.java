package Menu;

import model.User;
import service.*;
import util.ApplicationContext;
import util.Validation;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    BrandService brandService = ApplicationContext.getBrandService();
    CategoryService categoryService = ApplicationContext.getCategoryService();
    ProductService productService = ApplicationContext.getProductService();
    ShareholderService shareholderService = ApplicationContext.getShareholderService();
    UserService userService = ApplicationContext.getUserService();

    public void publicMenu() throws SQLException {
        boolean repeat = true;
        while (repeat) {
            System.out.println("====Welcome===");
            System.out.println("1- signUp");
            System.out.println("2- signIn");
            System.out.println("3- exit");
            System.out.println("==============");
            System.out.print("enter the number: ");
            int number = sc.nextInt();
            sc.nextLine();

            switch (number) {
                case 1:
                    repeat = false;
                    signUp();

                case 2:
                    repeat = false;
                    signIn();

                case 3:
                    repeat = false;
                default:
                    System.out.println("Enter a valid number");
            }
        }
    }

    public void signUp() throws SQLException {
        boolean flag = false;
        boolean validEmail = false;
        boolean validPassword = false;
        while (!flag && !validEmail && !validPassword) {
            System.out.print("Please enter your name: ");
            String name = sc.nextLine();
            System.out.print("Please enter your username: ");
            String username = sc.nextLine();
            System.out.print("please enter your email: ");
            String email = sc.nextLine();
            if (Validation.isValidEmail(email)) {
                validEmail = true;
                System.out.print("please enter your password: ");
                String password = sc.nextLine();
                if (Validation.isValidPassword(password)) {
                    validPassword = true;
                    User user1 = new User(name, username, email, password);
                    if (userService.register(user1)) {
                        flag = true;
                    }
                } else {
                    System.out.println("\n\n\n\n!Invalid password");
                }
            } else {
                System.out.println("\n\n\n\n!Invalid email");
            }
        }
    }

    public void signIn() throws SQLException {
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        User user = userService.login(username);
        if (user != null && password.equals(user.getPassword())) {
            mainMenu(user);
        } else {
            System.out.println("\n\n\n\nUsername or password is incorrect");
        }
    }

    public void mainMenu(User user) throws SQLException {
        boolean repeat=true;
        while (repeat) {
            System.out.println("=== Welcome back " + user.getUsername() + " ===");
            System.out.println("1- Category");
            System.out.println("2- Brand");
            System.out.println("3- Products");
            System.out.println("4- Shareholder");
            System.out.println("5- Edit");
            System.out.println("6- Exit");
            System.out.print("Enter a number: ");
            int number = sc.nextInt();
            sc.nextLine();


        switch (number) {
            case 1 -> System.out.println();
            case 2 -> System.out.println();
            case 3 -> System.out.println();
            case 4 -> System.out.println();
            case 5 -> userEditMenu(user);
            case 6 -> repeat=false;
            default -> System.out.println("...");
        }
        }
    }

    public void userEditMenu(User user) throws SQLException {
        boolean repeat = true;
        while (repeat) {
            System.out.println("================");
            System.out.println("1- Edit name");
            System.out.println("2- Edit userName");
            System.out.println("3- Edit Email");
            System.out.println("4- Edit Password");
            System.out.println("5- Delete Account");
            System.out.println("6- Exit");
            System.out.print("Enter your selection: ");
            int number = sc.nextInt();
            sc.nextLine();
            switch (number) {
                case 1 -> userService.changeName(user.getId());
                case 2 -> userService.changeUsername();
                case 3 -> userService.changePassword(user.getId());
                case 4 -> userService.changeEmail();
                case 5 -> userService.deleteAccount(user.getId());
                case 6 -> repeat = false;
                default -> System.out.println("...");
            }
        }
    }
}
