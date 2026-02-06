package com.wipro.ecs.main;

import java.util.ArrayList;
import com.wipro.ecs.entity.*;
import com.wipro.ecs.service.ShoppingService;
import com.wipro.ecs.util.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("U001", "Akash", "9876543210"));
        users.add(new User("U002", "Riya", "9123456780"));

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("P101", "Laptop", 55000.0, 5));
        products.add(new Product("P202", "Wireless Earbuds", 2500.0, 10));

        ArrayList<Order> orders = new ArrayList<>();

        ShoppingService service = new ShoppingService(users, products, orders);

        try {
            ArrayList<CartItem> cart = new ArrayList<>();
            cart.add(new CartItem("P101", 1));
            cart.add(new CartItem("P202", 2));

            Order o1 = service.placeOrder("U001", cart);
            System.out.println("Order Placed! ID: " + o1.getOrderId());

            System.out.println("\n--- User Orders (U001) ---");
            service.printUserOrders("U001");
            //
            System.out.println("Please Type here to View your products: ");
            String cont2 = s.nextLine();

            if(cont2.equals("view")){
                System.out.println(products);
            }
            //
            System.out.println("Please Type here to Cancel The Order : ");
            String cont = s.nextLine();

            if(cont.equals("cancel")){

                System.out.println("\nCancelling Order...");
                service.cancelOrder(o1.getOrderId());
                System.out.println("Order Cancelled!");
            }


        } catch (InvalidUserException | ProductNotFoundException |
                 OutOfStockException | OrderNotFoundException |
                 OrderOperationException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println("Unexpected Error: " + ex);

        }

    }

}
