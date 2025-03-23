import ui.UserUi;
import user.User;
import user.UserService;

import java.util.Scanner;
import java.util.UUID;


public class Main {
    public static Scanner scnInt = new Scanner(System.in);
    public static Scanner scnStr = new Scanner(System.in);
    public static UserService userService = new UserService();

    public static void main(String[] args) {
        boolean isExited= false;

        while (!isExited){
            System.out.print("""
                    1 Register
                    2 Login
                    \s
                    0 Exit
                    >>>\s""");

            switch (scnInt.nextInt()){
                case 1: {
                    register();
                    break;
                }
                case 2: {
                    login();
                    break;
                }
                case 0: {
                    isExited = true;
                    break;
                }
                default: {
                    System.out.println("Invalid command");
                }
            }
        }
    }

    public static void register(){
        System.out.print("Enter your name: ");
        String name = scnStr.nextLine();

        System.out.print("Enter your email: ");
        String email = scnStr.nextLine();

        System.out.print("Enter your password: ");
        String password = scnStr.nextLine();

        if (userService.isAlreadyExist(email)){
            System.out.println("The email already exist!");
            return;
        }
        User user = new User(name, UUID.randomUUID(), email, password);
        userService.add(user);
        System.out.println("Successfull Registration");
        UserUi.userUi(user);
    }

    public static void login(){
        System.out.print("Enter your email: ");
        String email = scnStr.nextLine();

        System.out.print("Enter your password: ");
        String password = scnStr.nextLine();

        if (userService.login(email, password)){
            User user = userService.getUserByEmail(email);
            UserUi.userUi(user);
        }
    }
}