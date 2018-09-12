import java.io.BufferedInputStream;
import java.util.Scanner;

class Show{
    public String movieName;
    public String showTime;
    public String[] seats;
    public Show(String movieName, String showTime, String[] seats){
        this.movieName = movieName;
        this.showTime = showTime;
        this.seats = seats;
    }
}
class Patron{
    public String patronName;
    public String patronMobileNo;
    public Patron(String patronName, String patronMobileNo){
        this.patronName = patronName;
        this.patronMobileNo = patronMobileNo;
    }
}
class YourShow{
    Patron patron;
    Show show;
    public YourShow(Patron patron, Show show){
        this.patron = patron;
        this.show = show;
    }
}
class BookYourShow{
    public Show[] allShows;
    public int allShowsCount;
    public Patron[] bookedShows;
    public int bookedShowsCount;
    public BookYourShow(){
        this.allShows = new Show[10];
        this.allShowsCount = 0;
        this.bookedShows = new Patron[10];
        this.bookedShowsCount = 0;
    }
    public void addAShow(Show show){
        // System.out.println("HI");
        this.allShows[allShowsCount++] = show;
        // showAll();
    }
    public Show getAShow(String movieName, String showTime){
        // Show[] presentShows = new Show[allShowsCount];
        // int count = 0;
        for (int i = 0; i < allShowsCount; i++) {
            // System.out.println(allShows[i]);
            if(allShows[i].movieName.equals(movieName) && allShows[i].showTime.equals(showTime)){
                // presentShows[count++] = eachShow;
                return allShows[i];
            }
        }
        return null;
        // Show[] finalpresentShows = new Show[count];
        // for (int i = 0; i < presentShows.length; i++) {
        //     finalpresentShows[i] = presentShows[i];
        // }
        // return finalpresentShows;
    }
    public void bookAShow(String movieName, String showTime, Patron pa, String[] seats){
        Show show = getAShow(movieName, showTime);
        if(show != null){
            int count = 0;
            for (int p = 0; p < allShowsCount; p++) {
                for (String ticket: seats) {
                    for (int i = 0;  i < show.seats.length; i++) {
                        if(allShows[p].seats[i].equals(ticket)){
                            // allShows[p].seats[i] = "N/A";
                            count++;
                        }
                    }
                }
            }
            if(count == 0){
                System.out.println("No show");
            }else{
                bookedShows[bookedShowsCount++] = pa;
            }
        }else{
            System.out.println("No show");
        }
    }
    public void printTicket(String movieName, String showTime, String mobileNumber){
        // showAll();
        Show checkShow = getAShow(movieName, showTime);
        if(checkShow == null){
            System.out.println("Invalid");
        }else{
            // System.out.println("RAJESH");
            // showAll();
            int flag = 0;
            for (int i = 0; i < bookedShowsCount; i++) {
                if(bookedShows[i].patronMobileNo.equals(mobileNumber)){
                    flag = 1;
                    break;
                }
            }
            if(flag == 1){
                System.out.println(mobileNumber + " " + movieName + " " + showTime);
            }else{
                System.out.println("Invalid");
            }
        }
    }
    public void removeAMovie(){

    }

    public void showAll(){
        for (int i = 0; i < allShowsCount; i++) {
            String st = allShows[i].movieName + "," + allShows[i].showTime+",";
            String seatsonly = "[";
            for (int j = 0; j < allShows[i].seats.length; j++) {
                seatsonly += allShows[i].seats[j];
                if(j < allShows[i].seats.length - 1){
                    seatsonly += ",";
                }
            }
            seatsonly += "]";
            st += seatsonly;
            System.out.println(st);
        }
    }
}
public class Solution {
    public static String[] stringArray(String tickets){
        tickets = tickets.substring(0, tickets.length() - 1);
        // System.out.println(tickets);
        String[] stringArr = tickets.split(",");
        // System.out.println(stringArr[0] + " " + stringArr[1]);
        return stringArr;
    }
    public static void main(final String[] args) {
        BookYourShow bys = new BookYourShow();
        Scanner scan = new Scanner(System.in);
        int testCases = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < testCases; i++) {
            String[] tokens = scan.nextLine().
                replace("[", "").replace("]", "").split(",");
            String[] check = tokens[0].split(" ");
            switch (check[0]) {
                case "add":
                    int k = 2;
                    String[] seats = new String[tokens.length - 2];
                    for (int j = 0; j < seats.length; j++) {
                        seats[j] = tokens[k++];
                    }
                    bys.addAShow(new Show(check[1], tokens[1], seats));
                break;

                case "book":
                    k = 2 + 2;
                    seats = new String[tokens.length - 2 - 2];
                    for (int j = 0; j < seats.length; j++) {
                        seats[j] = tokens[k++];
                    }
                    bys.bookAShow(check[1], tokens[1],
                        new Patron(tokens[2], tokens[2 + 1]), seats);
                break;

                case "get":
                    Show show = bys.getAShow(check[1], tokens[1]);
                    if (show != null) {
                       System.out.println(show.movieName+","+show.showTime);
                    } else {
                        System.out.println("No show");
                    }
                break;

                case "print":
                    // System.out.println(check[1]+ " " + tokens[1]+ " " + tokens[2] +"HELLO");
                    bys.printTicket(check[1], tokens[1], tokens[2]);
                break;

                case "showAll":
                    bys.showAll();
                break;

                default:
                break;
            }
        }
    }
    // public static void main(String[] args) {
    //     Scanner stdin = new Scanner(new BufferedInputStream(System.in));
    //     BookYourShow bus = new BookYourShow();
    //     while (stdin.hasNext()) {
    //         String line = stdin.nextLine();
    //         // split the line using space
    //         String[] tokens = line.split("-");
    //         switch (tokens[0]) {
    //         case "add":
    //             // System.out.println(tokens[1]);
    //             String[] details = tokens[1].split("\\[");
    //             // System.out.println(details[]);
    //             String[] showTickets = stringArray(details[1]);
    //             details = details[0].split(",");
    //             bus.addAShow(new Show(details[0], details[1], showTickets));
    //             break;
    //         case "get":
    //             // bus.getAShow();
    //             break;
    //         case "book":
    //             // bus.bookAShow();
    //             break;
    //         case "print":
    //             // bus.printTickets();
    //             break;
    //         case "removeAMovie":
    //             // bus.removeAMovie();
    //             break;
    //         default:
    //             break;
    //         }
    //     }
    // }

}
