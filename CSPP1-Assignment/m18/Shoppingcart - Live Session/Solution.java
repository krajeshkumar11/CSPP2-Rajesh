import java.util.Scanner;

/**
 * Class for item.
 */
class Item{
    private String name;
    private int quantity;
    private double price;
    public Item(String name, int quantity, Double price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Item(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
        this.price = 0;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getName(){
        return this.name;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public double getPrice(){
        return this.price;
    }
}

class ShoppingCart{

    private Item[] catalog;
    private int catalogcount;
    private Item[] cart;
    private int cartcount;
    private double discount;
    public ShoppingCart(){
        catalog = new Item[10];
        catalogcount = 0;
        cart = new Item[10];
        cartcount = 0;
        discount = 0;
    }

    public void addToCatalog(Item item){
        catalog[catalogcount++] = item;
    }

    public void addToCart(Item item){
        int flag = 0;
        for (int i = 0; i < cartcount; i++) {
            for (int j = 0; j < catalogcount; j++) {
                if(catalog[j].getName().equals(item.getName()) && cart[i].getName().equals(item.getName())){
                    cart[i].setQuantity(cart[i].getQuantity() + item.getQuantity());
                    flag = 1;
                }
            }
        }
        if(flag != 1){
            for (int j = 0; j < catalogcount; j++) {
                if(catalog[j].getName().equals(item.getName())){
                    cart[cartcount++] = item;
                }
            }
        }
    }

    public void removeFromCart(Item item){
        int position = 0, flag = 0;
        for (int i = 0; i < cartcount; i++) {
            if (cart[i].getName().equals(item.getName())) {
                cart[i].setQuantity(cart[i].getQuantity() - item.getQuantity());
                if(cart[i].getQuantity() == 0){
                    position = i;
                    flag = 1;
                    break;
                }
            }
        }
        if(flag == 1){
            for (int i = position; i < cartcount; i++) {
                cart[i] = cart[i + 1];
            }
            cart[cartcount - 1] = null;
            cartcount--;
        }
    }

    public void showCart(){
        for (int i = 0;  i < cartcount; i++) {
            System.out.println(cart[i].getName() + " " + cart[i].getQuantity());
        }
    }

    public void showCatalog(){
        for (int i = 0; i < catalogcount; i++) {
            System.out.println(catalog[i].getName() + " " + catalog[i].getQuantity() + " " + catalog[i].getPrice());
        }
    }

    public double getTotalAmount(){
        double total = 0;
        for (int i = 0; i < cartcount; i++) {
            for (int j = 0; j < catalogcount; j++) {
                if (catalog[j].getName().equals(cart[i].getName())) {
                    total += catalog[j].getPrice() * cart[i].getQuantity();
                }
            }
        }
        return total;
    }

    public double getPayableAmount(){
        double amount = getTotalAmount();
        amount -= (amount / 100) * this.discount;
        amount += (amount / 100) * 15;
        return amount;
    }

    public void applyCoupon(String coupon){
        if(this.discount == 0.0){
            if(coupon.equals("IND10") || coupon.equals("IND20") || coupon.equals("IND30") || coupon.equals("IND50")){
                this.discount = Double.parseDouble(coupon.substring(3, coupon.length()));
            } else {
                System.out.println("Invalid coupon");
            }
        }
    }

    public void printInvoice(){
        System.out.println("Name   quantity   Price");
        for (int i = 0; i < cartcount; i++) {
            for (int j = 0; j < catalogcount; j++) {
                if(cart[i].getName().equals(catalog[j].getName())){
                    System.out.println(cart[i].getName() + " " + cart[i].getQuantity() + " " + catalog[j].getPrice());
                }
            }
        }
        System.out.println("Total:" + getTotalAmount());
        System.out.println("Disc%:" + (getTotalAmount() / 100) * discount);
        System.out.println("Tax:" + (getTotalAmount() - (getTotalAmount() / 100) * discount) * 0.15);
        System.out.println("Payable amount: " + getPayableAmount());
    }
}

public class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingCart scart = new ShoppingCart();
        while(sc.hasNext()){
            String input = sc.nextLine();
            String[] tokens = input.split(" ");
            switch(tokens[0]){
                case "Item":
                String[] data = tokens[1].split(",");
                scart.addToCatalog(new Item(data[0], Integer.parseInt(data[1]), Double.parseDouble(data[2])));
                break;
                case "catalog":
                scart.showCatalog();
                break;
                case "add":
                data = tokens[1].split(",");
                scart.addToCart(new Item(data[0], Integer.parseInt(data[1])));
                break;
                case "show":
                scart.showCart();
                break;
                case "totalAmount":
                System.out.println("totalAmount: " + scart.getTotalAmount());
                break;
                case "remove":
                data = tokens[1].split(",");
                scart.removeFromCart(new Item(data[0], Integer.parseInt(data[1])));
                break;
                case "payableAmount":
                System.out.println("Payable amount: " + scart.getPayableAmount());
                break;
                case "print":
                scart.printInvoice();
                break;
                case "coupon":
                scart.applyCoupon(tokens[1]);
                break;
            }
        }
    }
}
