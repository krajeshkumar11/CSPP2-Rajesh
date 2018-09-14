import java.util.ArrayList;
import java.util.Scanner;

class Item{
    public String name;
    public int quantity;
    public double price;
    Item(String name, int quantity, double price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

class ShoppingCart{
    public Item[] storeitems;
    public int storeitemscount;
    public Item[] cartitems;
    public int cartitemscount;
    public double couponcode;
    public double tax;
    ShoppingCart(){
        storeitems = new Item[20];
        cartitems = new Item[10];
        cartitemscount = 0;
        couponcode = 0;
        tax = 15;
    }

    public void addToStore(Item item){
        int flag = 0;
        for (int j = 0; j < storeitemscount; j++) {
            if(item.name.equals(storeitems[j].name)){
                storeitems[j].quantity += item.quantity;
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
                if(cartitems[i].name.equals(storeitems[j].name) && item.name.equals(cartitems[i].name)){
                    if(cartitems[i].quantity > item.quantity){
                        flag = 1;
                        position = 1;
                    }
                }
            }
        }
        if(flag == 1){
            cartitems[position].quantity += item.quantity;
        } else {
            cartitems[cartitemscount++] = item;
        }
    }

    public void showCatalog(){
        for (int i = 0; i < storeitemscount; i++) {
            System.out.println(storeitems[i].name + " " + storeitems[i].quantity + " " + storeitems[i].price);
        }
    }

    public void showCart(){
        for (int i = 0; i < cartitemscount; i++) {
            System.out.println(cartitems[i].name + " " + cartitems[i].quantity);
        }
    }

    public void removeFromCart(Item item){
        // System.out.println("RAJESH" + item.name+ " "+ item.quantity);
        int position = 0;
        int flag = 0;
        for (int i = 0; i < cartitemscount; i++) {
            if (cartitems[i].name.equals(item.name)) {
                cartitems[i].quantity -= item.quantity;
                if(cartitems[i].quantity == 0){
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
        double total = 0;
        for (int i = 0; i < cartitemscount; i++) {
            for (int j = 0; j < storeitemscount; j++) {
                if(cartitems[i].name.equals(storeitems[j].name)){
                    total += storeitems[j].price * cartitems[i].quantity;
                }
            }
        }
        return total;
    }

    public double getPayableAmount(){
        double total = getTotalAmount();
        total -= couponcode; // Reducing coupen amount discount
        total += (total / 100 ) * tax; // Adding tax
        return total;
    }

    public void applyCoupon(String coupon){
        this.couponcode = Double.parseDouble(coupon.substring(3, coupon.length() - 1));
    }

    public void printInvoice(){
        // System.out.println("HI");
        System.out.println("Name   quantity   Price");
        for (int i = 0; i < cartitemscount; i++) {
            for (int j = 0; j < storeitemscount; j++) {
                if(cartitems[i].name.equals(storeitems[j].name)){
                   System.out.println(cartitems[i].name + " " + cartitems[i].quantity + " " + storeitems[j].price);
                }
            }
        }
        System.out.println("totalAmount: " + getTotalAmount());
        System.out.println("Total:" + getTotalAmount());
        System.out.println("Disc%:" + couponcode);
        System.out.println("Tax:" + ((getTotalAmount()-couponcode) / 100) * tax);
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
