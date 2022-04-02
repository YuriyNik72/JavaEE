package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Scanner scn = new Scanner(System.in);

        CartService cart = null;
        while (true) {
            System.out.print("Enter command: ");
            String cmd = scn.nextLine().trim().toUpperCase();
            switch (cmd) {
                case "NEW":
                    cart = context.getBean(CartService.class);
                    System.out.println("Cart created");
                    break;

                case "ADD":
                    if (cart == null) {
                        System.out.println("Please create a new Cart");
                        break;
                    }
                    System.out.print("Enter id: ");
                    long id = scn.nextLong();
                    System.out.print("Enter count: ");
                    int count = scn.nextInt();
                    cart.addProduct(id, count);
                    break;

                case "SHOW":
                    if (cart == null) {
                        System.out.println("Please create a new Cart");
                        break;
                    }
                    cart.getAll().forEach(System.out::println);
                    break;

                case "REM":
                    if (cart == null) {
                        System.out.println("This Cart not found");
                        break;
                    }
                    System.out.print("Enter id: ");
                     id = scn.nextLong();
                    cart.removeProduct(id);
                    break;

                case "EXIT":
                    return;
            }
        }
    }
}
