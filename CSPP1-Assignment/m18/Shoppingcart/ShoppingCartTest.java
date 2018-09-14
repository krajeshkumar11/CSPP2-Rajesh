import java.util.ArrayList;
import java.util.Scanner;

class Item{
    private String name;
    private int quantity;
    private double price;
    Item(String name, int quantity, double price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getPrice(){
        return price;
    }
}

class ShoppingCart{
    public Item[] storeitems;
    public int storeitemscount;
    public Item[] cartitems;
    public int cartitemscount;
    public double couponcode;
    public double tax;
    public double cartTotal;
    ShoppingCart(){
        storeitems = new Item[20];
        cartitems = new Item[10];
        cartitemscount = 0;
        couponcode = 0;
        tax = 15;
        cartTotal = 0;
    }

    public void addToStore(Item item){
        int flag = 0;
        for (int j = 0; j < storeitemscount; j++) {
            if(item.getName().equals(storeitems[j].getName())){
                storeitems[j].setQuantity(item.getQuantity());
                flag = 1;
            }
        }
        if(flag != 1){
            storeitems[storeitemscount++] = item;
        }
    }

    public void addToCart(Item item){
        int flag = 0, position = 0;
        for (int i = 0; i < cartitemscount; i++) {
            for (int j = 0; j < storeitemscount; j++) {
                if(cartitems[i].getName().equals(storeitems[j].getName()) && item.getName().equals(cartitems[i].getName())){
                    if(cartitems[i].getQuantity() > item.getQuantity()){
                        flag = 1;
                        position = 1;
                    }
                }
            }
        }
        if(flag == 1){
            cartitems[position].setQuantity(cartitems[position].getQuantity() + item.getQuantity());
        } else {
            cartitems[cartitemscount++] = item;
        }
    }

    public void showCatalog(){
        for (int i = 0; i < storeitemscount; i++) {
            System.out.println(storeitems[i].getName() + " " + storeitems[i].getQuantity() + " " + storeitems[i].getPrice());
        }
    }

    public void showCart(){
        for (int i = 0; i < cartitemscount; i++) {
            System.out.println(cartitems[i].getName() + " " + cartitems[i].getQuantity());
        }
    }

    public void removeFromCart(Item item){
        // System.out.println("RAJESH" + item.getName()+ " "+ item.getQuantity());
        int position = 0;
        int flag = 0;
        for (int i = 0; i < cartitemscount; i++) {
            if (cartitems[i].getName().equals(item.getName())) {
                cartitems[i].setQuantity(cartitems[i].getQuantity() - item.getQuantity());
                if(cartitems[i].getQuantity() == 0){
                    flag = 1;
                    position = i;
                }
            }
        }
        if (flag == 1) {
            for (int i = position; i < cartitemscount; i++) {
                cartitems[i] = cartitems[i + 1];
            }
            cartitems[cartitemscount - 1] = null;
            cartitemscount--;
        }
    }

    public double getTotalAmount(){
        cartTotal = 0;
        for (int i = 0; i < cartitemscount; i++) {
            for (int j = 0; j < storeitemscount; j++) {
                if(cartitems[i].getName().equals(storeitems[j].getName())){
                    cartTotal += storeitems[j].getPrice() * cartitems[i].getQuantity();
                }
            }
        }
        return cartTotal;
    }

    public double getPayableAmount(){
        double payableTotal = getTotalAmount();
        // total -= couponcode; // Reducing coupen amount discount
        payableTotal -= (payableTotal / 100) * couponcode;
        payableTotal += (payableTotal / 100 ) * tax; // Adding tax
        return payableTotal;
    }

    public void applyCoupon(String coupon){
        if (coupon.equals("IND10") || coupon.equals("IND20") || coupon.equals("IND30") || coupon.equals("IND50")) {
            this.couponcode = Double.parseDouble(coupon.substring(3, coupon.length()));
        }
    }

    public void printInvoice(){
        // System.out.println("HI");
        System.out.println("Name   quantity   Price");
        for (int i = 0; i < cartitemscount; i++) {
            for (int j = 0; j < storeitemscount; j++) {
                if(cartitems[i].getName().equals(storeitems[j].getName())){
                   System.out.println(cartitems[i].getName() + " " + cartitems[i].getQuantity() + " " + storeitems[j].getPrice());
                }
            }
        }
        System.out.println("totalAmount: " + getTotalAmount());
        System.out.println("Total:" + getTotalAmount());
        System.out.println("Disc%:" + (getTotalAmount() / 100) * couponcode);
        // System.out.println(getTotalAmount() + " " + couponcode + "COUPEN");
        System.out.println("Tax:" + ((getTotalAmount() - ((getTotalAmount() / 100) * couponcode)) / 100) * tax);
        System.out.println("Payable amount: " + getPayableAmount());
    }
}
public class ShoppingCartTest{

    public static void main(String[] args) {
        ShoppingCart scart = new ShoppingCart();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String input = sc.nextLine();
            String[] options = input.split(" ");
            switch(options[0]){
                case "Item":
                String[] itemdetails = options[1].split(",");
                scart.addToStore(new Item(itemdetails[0], Integer.parseInt(itemdetails[1]), Double.parseDouble(itemdetails[2])));
                break;
                case "catalog":
                scart.showCatalog();
                break;
                case "add":
                itemdetails = options[1].split(",");
                scart.addToCart(new Item(itemdetails[0], Integer.parseInt(itemdetails[1]), 0));
                break;
                case "show":
                scart.showCart();
                break;
                case "totalAmount":
                System.out.println("totalAmount: " + scart.getTotalAmount());
                break;
                case "remove":
                itemdetails = options[1].split(",");
                scart.removeFromCart(new Item(itemdetails[0], Integer.parseInt(itemdetails[1]), 0));
                break;
                case "payableAmount":
                System.out.println("Payable amount: " + scart.getPayableAmount());
                break;
                case "print":
                scart.printInvoice();
                break;
                case "coupon":
                scart.applyCoupon(options[1]);
                break;
            }
        }
    }
}
  // public static void main(String[] args){
  //   // creates new cartitems with product name, quantity and unit price
  //   Item i1 = new Item("Olive Oil 1l", 3, 100.0);
  //   Item i2 = new Item("Cheese Slices", 2, 50.0);
  //   Item i3 = new Item("Bread", 1, 75.0);
  //   Item i4 = new Item("Eggs", 50, 10.0);
  //   Item i5 = new Item("Chicken Salami", 2, 100.0);

  //   ShoppingCart cart = new ShoppingCart();

  //   /* add the item to the cart */
  //   cart.addToCart(i1);
  //   cart.addToCart(i2);
  //   cart.addToCart(i3);
  //   cart.addToCart(i4);
  //   cart.addToCart(i5);

  //   /*
  //    * shows the list of cartitems in the cart with quantity
  //    * Expected Output to the console:
  //    * Olive Oil 1l: 3
  //    * Cheese Slices: 2
  //    * Bread: 1
  //    * Eggs: 50
  //    * Chicken Salami: 2
  //    */
  //   cart.showCart();
  //   System.out.println("Show Cart");
  //   cart.removeFromCart(i3);

  //   /*
  //    * shows the list of cartitems in the cart with quantity
  //    * Expected Output to the console:
  //    * Olive Oil 1l: 3
  //    * Cheese Slices: 2
  //    * Eggs: 50
  //    * Chicken Salami: 2
  //    */
  //   cart.showCart();
  //   System.out.println("Show Cart");
  //   /*
  //    * gets the total amount for the cartitems in the cart
  //    * Expected Output:
  //    * 1100
  //    */
  //   double totalAmount = cart.getTotalAmount();
  //   System.out.println(totalAmount);
  //   System.out.println("Total Amount");
  //   /*
  //    * Gets the payable amount,
  //    * it deduct discount and add tax to the total amount of cartitems in cart
  //    * Expected Output:
  //    * 1254 //1144
  //    */
  //    double payableAmount = cart.getPayableAmount();
  //    System.out.println(payableAmount);
  //    System.out.println("Payable Amount");
  //   /* apply a coupon code */
  //   cart.applyCoupon("IND50");


  //    print the cartitems with the quantity, unit price, total amount
  //    * apply coupon if valid
  //    * add tax to the total amount after discount
  //    * Expected Output to the console:
  //    * Olive Oil 1l      3    100.00    300.00
  //    * Cheese Slices     2     50.00    100.00
  //    * Eggs             50     10.00    500.00
  //    * Chicken Salami    2    100.00    200.00
  //    *                          Total: 1100.00
  //    *                          Disc%:    0.00
  //    *                          Tax:    154.00
  //    *                          Total: 1254.00


  //   cart.printInvoice();
  //   System.out.println("Invoice");
  //   cart.applyCoupon("IND10");

  //   /*
  //    * print the cartitems with the quantity, unit price, total amount
  //    * apply coupon if valid
  //    * add tax to the total amount after discount
  //    * Expected Output to the console:
  //    * Olive Oil 1l      3    100.00    300.00
  //    * Cheese Slices     2     50.00    100.00
  //    * Eggs             50     10.00    500.00
  //    * Chicken Salami    2    100.00    200.00
  //    *                          Total: 1100.00
  //    *                          Disc%:  110.00
  //    *                          Tax:    154.00
  //    *                          Total: 1128.60
  //    *
  //    */
  //   cart.printInvoice();
  //   System.out.println("Invoice");
  //   cart.addToCart(new Item("Milk", 5, 30.00));
  //   cart.showCart();
  //   System.out.println("Show Card");
  //   /*
  //    * print the cartitems with the quantity, unit price, total amount
  //    * apply coupon if valid
  //    * add tax to the total amount after discount
  //    * Expected Output to the console:
  //    * Olive Oil 1l      3    100.00    300.00
  //    * Cheese Slices     2     50.00    100.00
  //    * Eggs             50     10.00    500.00
  //    * Chicken Salami    2    100.00    200.00
  //    * Milk              5     30.00    150.00
  //    *                          Total: 1250.00
  //    *                          Disc%:  125.00
  //    *                          Tax:    157.50
  //    *                          Total: 1282.50
  //    *
  //    */
  //   cart.printInvoice();
  // }
