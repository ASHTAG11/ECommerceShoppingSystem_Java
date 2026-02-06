package com.wipro.ecs.service;

import com.wipro.ecs.entity.CartItem;
import com.wipro.ecs.entity.User;
import com.wipro.ecs.entity.Product;
import com.wipro.ecs.entity.Order;
import com.wipro.ecs.util.InvalidUserException;
import com.wipro.ecs.util.OrderOperationException;
import com.wipro.ecs.util.OutOfStockException;
import com.wipro.ecs.util.ProductNotFoundException;

import com.wipro.ecs.util.OrderNotFoundException;


import java.util.ArrayList;


public class ShoppingService {

    private ArrayList<User> users;
    private ArrayList<Product> products;
    private ArrayList<Order> orders;

    // 1st method (constructor)
    public ShoppingService(ArrayList<User> users, ArrayList<Product> products, ArrayList<Order> orders){

        this.users = users;
        this.products = products;
        this.orders = orders;
    }

    //2nd method
    public boolean validateUser(String userId) throws InvalidUserException{
        //logic

        for( int i=0 ; i<= users.size(); i++ ){
            User u = users.get(i);

            if(userId.equals(u.getUserId())){
                return true;
            }
        }
        throw new InvalidUserException();
    }

    //3rd method
    public Product findProduct(String productId) throws ProductNotFoundException{

        //logic

        for(int i=0; i<products.size() ; i++){
            Product p = products.get(i);

            if(productId.equals(p.getProductId())){
                return  p;
            }

        }

        throw new  ProductNotFoundException();
    }

    //4th method
    public void checkStock(String productId, int quantity) throws OutOfStockException, ProductNotFoundException{

        Product p = findProduct(productId);

        if(p.getStock() < quantity){
            throw new OutOfStockException();
        }

    }

    //5th method (important logic in this project)
    public Order placeOrder(String userId, ArrayList<CartItem> cart)
            throws OrderOperationException, InvalidUserException, ProductNotFoundException, OutOfStockException {

        //check the cart is empty
        if(cart.isEmpty()){
            throw new OrderOperationException();
        }
        // validate user (check)
        validateUser(userId);

        double total = 0;

        //process cart
        for (int i=0; i < cart.size(); i++){
            //object creation and calling
            CartItem item =  cart.get(i);

            Product p = findProduct(item.getProductId());

            checkStock(item.getProductId(), item.getQuantity());

            //calculate price
            total = total + (p.getPrice() * item.getQuantity());

            //reduce stock
            p.setStock(p.getStock() - item.getQuantity());
        }

        // create order id
        String orderId = "ORD" + (orders.size() + 1);

        //  create order
        Order order = new Order(orderId, userId, cart, total);

        //  save order
        orders.add(order);

        // return order
        return order;

    }


    // Print all orders of a user
    public void printUserOrders(String userId) {

        for (int i = 0; i < orders.size(); i++) {

            Order o = orders.get(i);

            if (o.getUserId().equals(userId)) {

                System.out.println("Order ID: " + o.getOrderId());
                System.out.println("Total Amount: " + o.getTotalAmount());
                System.out.println("------------------------");
            }
        }
    }

    // Cancel order
    public void cancelOrder(String orderId) throws OrderNotFoundException {

        Order found = null;

        // Find order
        for (int i = 0; i < orders.size(); i++) {

            Order o = orders.get(i);

            if (o.getOrderId().equals(orderId)) {
                found = o;
                break;
            }
        }

        // If not found
        if (found == null) {
            throw new OrderNotFoundException();
        }

        // Restore stock
        for (int i = 0; i < found.getItems().size(); i++) {

            CartItem item = found.getItems().get(i);

            for (int j = 0; j < products.size(); j++) {

                Product p = products.get(j);

                if (p.getProductId().equals(item.getProductId())) {
                    p.setStock(p.getStock() + item.getQuantity());
                }
            }
        }

        // Remove order
        orders.remove(found);
    }





}
