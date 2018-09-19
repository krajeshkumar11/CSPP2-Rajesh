import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * Class for show.
 */
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
    public YourShow[] bookedShows;
    public int bookedShowsCount;
    public BookYourShow(){
        this.allShows = new Show[10];
        this.allShowsCount = 0;
        this.bookedShows = new YourShow[10];
        this.bookedShowsCount = 0;
    }
    public void addAShow(Show show){
        this.allShows[allShowsCount++] = show;
    }
    public Show[] getAShow(String movieName, String showTime){
        Show[] presentShows = new Show[allShowsCount];
        int count = 0;
        for (Show eachShow: allShows) {
            if(eachShow.movieName.equals(movieName) && eachShow.showTime.equals(showTime)){
                presentShows[count++] = eachShow;
            }
        }
        Show[] finalpresentShows = new Show[count];
        for (int i = 0; i < presentShows.length; i++) {
            finalpresentShows[i] = presentShows[i];
        }
        return finalpresentShows;
    }
    public void bookAShow(Patron pa, Show sh){

    }
    public void printTickets(String mobileNumber, String showName){
        // for (Patron eachShow: allShows) {
        //     if(eachShow.movieName.equals(movieName) && eachShow.showTime.equals(showTime)){
        //         presentShows[count++] = eachShow;
        //     }
        // }
    }
    public void removeAMovie(){

    }
}
public class Menu {
    public static String[] stringArray(String tickets){
        tickets = tickets.substring(0, tickets.length() - 1);
        String[] stringArr = tickets.split(",");
        System.out.println(stringArr[0] + " " + stringArr[1]);
        return stringArr;
    }
    public static void main(String[] args) {
        Scanner stdin = new Scanner(new BufferedInputStream(System.in));
        BookYourShow bus = new BookYourShow();
        while (stdin.hasNext()) {
            String line = stdin.nextLine();
            // split the line using space
            String[] tokens = line.split(" ");
            switch (tokens[0]) {
            case "add":
                String[] details = tokens[1].split(",");
                stringArray(tokens)
                // bus.addAShow();
                break;
            case "get":
                // bus.getAShow();
                break;
            case "book":
                // bus.bookAShow();
                break;
            case "print":
                // bus.printTickets();
                break;
            case "removeAMovie":
                // bus.removeAMovie();
                break;
            default:
                break;
            }
        }
    }
}
