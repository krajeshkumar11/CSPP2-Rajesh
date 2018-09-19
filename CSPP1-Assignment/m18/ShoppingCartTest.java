import java.util.ArrayList;

class Item{
    public String name;
    public int quantity;
    public double price;
    public Item(String name, int quantity, double price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

class ShoppingCart{
    public Item[] items;
    public int itemscount;
    public double couponcode;
    public double tax;
    public ShoppingCart(){
        items = new Item[10];
        itemscount = 0;
        couponcode = 0;
        tax = 15;
    }

    public void addToCart(Item item){
        items[itemscount++] = item;
    }

    public void showCart(){
        for (int i = 0; i < itemscount; i++) {
            System.out.println(items[i].name + " : " + items[i].quantity);
        }
    }

    public void removeFromCart(Item item){
        int position = 0;
        for (int i = 0; i < itemscount; i++) {
            if (items[i].name.equals(item.name)) {
                position = i;
            }
        }
        for (int i = position; i < itemscount; i++) {
            items[i] = items[i + 1];
        }
        items[itemscount - 1] = null;
        itemscount--;
    }

    public double getTotalAmount(){
        double total = 0;
        for (int i = 0; i < itemscount; i++) {
            total += items[i].price * items[i].quantity;
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
        System.out.println("Name    quantity     Price");
        for (int i = 0; i < itemscount; i++) {
            System.out.println(items[i].name + " " + items[i].quantity + " " + items[i].price + " " + items[i].price * items[i].quantity);
        }
        System.out.println("Total: " + getTotalAmount());
        System.out.println("Disc%: " + couponcode);
        System.out.println("Tax: " + ((getTotalAmount()-couponcode) / 100) * tax);
        System.out.println("Total: " + getPayableAmount());
    }
}
public class ShoppingCartTest{

  public static void main(String[] args){
    // creates new items with product name, quantity and unit price
    Item i1 = new Item("Olive Oil 1l", 3, 100.0);
    Item i2 = new Item("Cheese Slices", 2, 50.0);
    Item i3 = new Item("Bread", 1, 75.0);
    Item i4 = new Item("Eggs", 50, 10.0);
    Item i5 = new Item("Chicken Salami", 2, 100.0);

    ShoppingCart cart = new ShoppingCart();

    /* add the item to the cart */
    cart.addToCart(i1);
    cart.addToCart(i2);
    cart.addToCart(i3);
    cart.addToCart(i4);
    cart.addToCart(i5);

    /*
     * shows the list of items in the cart with quantity
     * Expected Output to the console:
     * Olive Oil 1l: 3
     * Cheese Slices: 2
     * Bread: 1
     * Eggs: 50
     * Chicken Salami: 2
     */
    cart.showCart();
    System.out.println("Show Cart");
    cart.removeFromCart(i3);

    /*
     * shows the list of items in the cart with quantity
     * Expected Output to the console:
     * Olive Oil 1l: 3
     * Cheese Slices: 2
     * Eggs: 50
     * Chicken Salami: 2
     */
    cart.showCart();
    System.out.println("Show Cart");
    /*
     * gets the total amount for the items in the cart
     * Expected Output:
     * 1100
     */
    double totalAmount = cart.getTotalAmount();
    System.out.println(totalAmount);
    System.out.println("Total Amount");
    /*
     * Gets the payable amount,
     * it deduct discount and add tax to the total amount of items in cart
     * Expected Output:
     * 1254 //1144
     */
     double payableAmount = cart.getPayableAmount();
     System.out.println(payableAmount);
     System.out.println("Payable Amount");
    /* apply a coupon code */
    cart.applyCoupon("IND50");


    /* print the items with the quantity, unit price, total amount
     * apply coupon if valid
     * add tax to the total amount after discount
     * Expected Output to the console:
     * Olive Oil 1l      3    100.00    300.00
     * Cheese Slices     2     50.00    100.00
     * Eggs             50     10.00    500.00
     * Chicken Salami    2    100.00    200.00
     *                          Total: 1100.00
     *                          Disc%:    0.00
     *                          Tax:    154.00
     *                          Total: 1254.00
     */

    cart.printInvoice();
    System.out.println("Invoice");
    cart.applyCoupon("IND10");

    /*
     * print the items with the quantity, unit price, total amount
     * apply coupon if valid
     * add tax to the total amount after discount
     * Expected Output to the console:
     * Olive Oil 1l      3    100.00    300.00
     * Cheese Slices     2     50.00    100.00
     * Eggs             50     10.00    500.00
     * Chicken Salami    2    100.00    200.00
     *                          Total: 1100.00
     *                          Disc%:  110.00
     *                          Tax:    154.00
     *                          Total: 1128.60
     *
     */
    cart.printInvoice();
    System.out.println("Invoice");
    cart.addToCart(new Item("Milk", 5, 30.00));
    cart.showCart();
    System.out.println("Show Card");
    /*
     * print the items with the quantity, unit price, total amount
     * apply coupon if valid
     * add tax to the total amount after discount
     * Expected Output to the console:
     * Olive Oil 1l      3    100.00    300.00
     * Cheese Slices     2     50.00    100.00
     * Eggs             50     10.00    500.00
     * Chicken Salami    2    100.00    200.00
     * Milk              5     30.00    150.00
     *                          Total: 1250.00
     *                          Disc%:  125.00
     *                          Tax:    157.50
     *                          Total: 1282.50
     *
     */
    cart.printInvoice();
  }
}
