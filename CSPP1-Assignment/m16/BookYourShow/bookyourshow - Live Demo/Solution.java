import java.util.Scanner;

/**
 * Class for solution.
 */

class Show{
    public String movie_name;
    public String date;
    public String[] seats;
    public Show(String movie_name, String date, String[] seats){
        this.movie_name = movie_name;
        this.date = date;
        this.seats = seats;
    }
}

class Patron{
    public String person_name;
    public String phoneNo;
    public Patron(String person_name, String phoneNo){
        this.person_name = person_name;
        this.phoneNo = phoneNo;
    }
}

class BookYourShow{
    public Show[] allshows;
    public int allshowscount;
    public Patron[] allpatrons;
    public int allpatronscount;
    public BookYourShow(){
        allshows = new Show[10];
        allshowscount = 0;
        allpatrons = new Patron[10];
        allpatronscount = 0;
    }

    public void addAShow(Show show){
        allshows[allshowscount++] = show;
    }

    public void bookAShow(String movie_name, String date, Patron pa, String[] seats){
        Show checkshow = getAShow(movie_name, date);
        if(allshowscount == 0){
            System.out.println("No show");
        }else{
            if(checkshow != null){
                int flag = 0;
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < checkshow.seats.length; j++) {
                        if(seats[i].equals(checkshow.seats[j])){
                            checkshow.seats[j] = "N/A";
                            flag = 1;
                        }
                    }
                }
                for (int p = 0;  p < allshowscount; p++) {
                    if(allshows[p].movie_name.equals(checkshow.movie_name) && allshows[p].date.equals(checkshow.date)){
                        allshows[p] = checkshow;
                    }
                }
                if (flag == 1) {
                    allpatrons[allpatronscount++] = pa;
                }
            } else {
                System.out.println("No show");
            }
        }
    }

    public Show getAShow(String movie_name, String date){
        for (int i = 0; i < allshowscount; i++) {
            if(allshows[i].movie_name.equals(movie_name) && allshows[i].date.equals(date)){
                return allshows[i];
            }
        }
        return null;
    }

    public void printTicket(String movie_name,  String date, String phoneNo){
        Show checkshow = getAShow(movie_name, date);
        if(checkshow != null){
            int flag = 0;
            for (int i = 0; i < allpatronscount; i++) {
                if(allpatrons[i].phoneNo.equals(phoneNo)){
                    flag = 1;
                    break;
                }
            }
            if(flag == 1){
                System.out.println(phoneNo + " " +movie_name + " " + date);
            }else{
                System.out.println("Invalid");
            }
        }else{
            System.out.println("Invalid");
        }
    }

    public void showAll(){
        for (int i = 0; i < allshowscount; i++) {
            String st = allshows[i].movie_name + "," + allshows[i].date + ",";
            String onlyseats = "[";
            for (int j = 0; j < allshows[i].seats.length; j++) {
                onlyseats += allshows[i].seats[j];
                if(i < allshows[i].seats.length - 1){
                    onlyseats += ",";
                }
            }
            onlyseats += "]";
            st += onlyseats;
            System.out.println(st);
        }
    }
}

public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * main method to drive program.
     *
     * @param      args  The arguments
     */
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
                       System.out.println(show.movie_name + "," + show.date);
                    } else {
                        System.out.println("No show");
                    }
                break;

                case "print":
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
}
