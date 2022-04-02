package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.persist.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean("userService", UserService.class);

        CartService cartService = context.getBean("cartService", CartService.class);
        cartService = context.getBean("cartService", CartService.class);
        cartService = context.getBean("cartService", CartService.class);

        Scanner sc = new Scanner(System.in);
        for (;;) {
            System.out.print("Enter user name: ");
            String name = sc.nextLine();

            System.out.print("Enter user role: ");
            String role = sc.nextLine();

            try {
                userService.insert(new User(name, role));
            } catch (IllegalArgumentException ex) {
                System.out.println("Incorrect role name");
            }

            System.out.println("New user added. Now " + userService.getCount() + " users in repository");
        }
    }
}
