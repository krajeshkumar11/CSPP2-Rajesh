import java.util.Scanner;

class Log {
    private Date logTime;
    Log (Date logTime) {
        this.logTime = logTime;
    }
    public Date getLogTime () {
        return logTime;
    }
    public String log() {
        return null;
    }
}

class Food{
    private String itemName:
    private int itemQuantity;
    private String time;
    Food (String name, int quantity, String time) {
        this.itemName = name;
        this.itemQuantity = quantity;
        this.time = time;
    }
    public String getTime() {
        return time;
    }
    public String getItemName () {
        return itemName;
    }
    public int getQuantity () {
        return itemQuantity;
    }
    public String log() {
        return null;
    }
}

class Water{
    private int waterQuantity;
    private String time;
    Water (int waterQuantity, String time) {
        this.waterQuantity = waterQuantity;
        this.time = time;
    }
    public String getTime() {
        return time;
    }
    public int getQuantity () {
        return waterQuantity;
    }
    public String log() {
        return null;
    }
}

class PhysicalActivity{
    private String activityName:
    private String activityNotes:
    private String activityEndDate;
    private String time;
    PhysicalActivity (String activityName, String activityNotes, String activityEndDate, String time) {
        this.activityName = activityName;
        this.activityNotes = activityNotes;
        this.activityEndDate = activityEndDate;
        this.time = time;
    }
    public String getTime() {
        return time;
    }
    public String getActivityName () {
        return activityName;
    }
    public String getActivityNotes () {
        return activityNotes;
    }
    public String getActivityEndDate () {
        return activityEndDate;
    }
    public String log() {
        return null;
    }
}

class Weight {
    private int fat;
    private double weight;
    private String time;
    Weight (int fat, double weight, String time) {
        this.fat = fat;
        this.weight = weight;
        this.time = time;
    }
    public String getTime() {
        return time;
    }
    public int getFat () {
        return fat;
    }
    public double getWeight () {
        return weight;
    }
    public String log() {
        return null;
    }
}

class Sleep {
    private String startTime;
    private String endTime;
    Sleep (String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String getStartTime () {
        return startTime;
    }
    public String getEndTime () {
        return endTime;
    }
    public String log() {
        return null;
    }
}

class FitByte{

}

public class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FitByte scart = new FitByte();
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
