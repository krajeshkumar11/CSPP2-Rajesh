import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Class for set.
 * @author     :
 */
class Set {
    //your code goes here...
    //Good luck :-)
    /**
     * Constructs the object.
     */
    protected int[] set;
    /**
     * Constructs the object.
     */
    protected int size;
    /**
     * Constructs the object.
     */
    private static final int DEFAULTSIZE = 10;
    /**
     * Constructs the object.
     */

    Set() {
        set = new int[DEFAULTSIZE];
        size = 0;
    }

    /**
     * Gets the set.
     *
     * @return     The set.
     */
    // public int[] getSet(){
    //     return set;
    // }

    // public void increaseSize(){
    //     size++;
    // }

    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
        return size;
    }
    /**
     * { function_description }.
     *
     * @param      item  The item
     *
     * @return     { description_of_the_return_value }
     */
    public boolean contains(final int item) {
        if (size() > 0) {
            for (int i = 0; i < size(); i++) {
                if (item == this.get(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        String str = "{";
        for (int i = 0; i < size(); i++) {
            str += set[i];
            if (i < size() - 1) {
                str += ", ";
            }
        }
        str += "}";
        return str;
    }
    /**
     * { item_description }.
     *
     * @param      item  The item
     */
    public void add(final int item) {
        if (size() == set.length) {
            resize();
        }
        if (!this.contains(item)) {
            set[size++] = item;
        }
    }

    /**
     * { function_description }.
     */
    public void resize() {
        int[] newset = new int[2 * set.length];
        System.arraycopy(set, 0, newset, 0, size());
        set = newset;
    }

    /**
     * { function_description }.
     *
     * @param      items  The items
     */
    public void add(final int[] items) {
        for (int item : items) {
            add(item);
        }
    }

    /**
     * { function_description }.
     *
     * @param      index  The index
     *
     * @return     { description_of_the_return_value }
     */
    public int get(final int index) {
        if (index < size()) {
            return set[index];
        }
        return -1;
        // for (int i = 0; i < size(); i++) {
        //     if (set[i] == item){
        //         return set[i];
        //     }
        // }
        // return -1;
    }

    /**
     * { function_description }.
     *
     * @param      other  The other
     *
     * @return     { description_of_the_return_value }
     */
    public Set intersection(final Set other) {
        Set newSet = new Set();
        for (int i = 0; i < size(); i++) {
            if (other.contains(this.get(i))) {
                newSet.add(this.get(i));
            }
        }
        return newSet;
    }

    /**
     * { function_description }.
     *
     * @param      items  The items
     *
     * @return     { description_of_the_return_value }
     */
    public Set retainAll(final int[] items) {
        Set newSet = new Set();
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < items.length; j++) {
                if (items[j] == this.get(i)) {
                    newSet.add(this.get(i));
                }
            }
        }
        return newSet;
    }

    /**
     * { function_description }.
     *
     * @param      other  The other
     *
     * @return     { description_of_the_return_value }
     */
    public int[][] cartesianProduct(final Set other) {
        if (this.size() > 0 && other.size() > 0) {
            int[][] cp = new int[this.size() * other.size()][2];
            int row = 0;
            int col = 0;
            for (int i = 0; i < this.size(); i++) {
                // cp[i][j] = this.get(i);
                for (int j = 0; j < other.size(); j++) {
                    // System.out.println(i + "I");
                    // System.out.println(j + "J");
                    col = 0;
                    cp[row][col++] = this.get(i);
                    cp[row++][col++] = other.get(j);
                }
            }
            return cp;
        }
        return null;
    }
}

class SortedSet extends Set{
    public int[] subSet(int fromElement, int toElement){
        int[] data = new int[size()];
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if(set[i] >= fromElement && set[i] < toElement){
                data[count++] = set[i];
            }
        }
        int[] finaldata = new int[count];
        System.arraycopy(data, 0, finaldata, 0, count);
        return finaldata;
    }
    public int[] headSet(int toElement){
        int[] data = new int[size()];
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if(set[i] < toElement){
                data[count++] = set[i];
            }
        }
        int[] finaldata = new int[count];
        System.arraycopy(data, 0, finaldata, 0, count);
        return finaldata;
    }
    public int last(){
        if(size() > 0){
            return set[size() - 1];
        }else{
            return -1;
        }
    }
    public void add(int item){
        if (size() == set.length){
            resize();
        } else if(size() == 0){
            set[size++] = item;
        }else{
            if(item < 0 && !contains(item)){
                for (int i = 0; i < size(); i++) {
                    if(item < set[i]){
                        rightShift(i);
                        set[i] = item;
                        size++;
                        break;
                    }
                }
            }else if(!contains(item)){
                for (int i = size(); i > 0; i--) {
                    if(item > set[i - 1]){
                        rightShift(i);
                        set[i] = item;
                        size++;
                        break;
                    }
                }
            }
        }
    }
    public void rightShift(int position){
        if(size() == set.length){
            resize();
        }
        for (int i = size(); i > position; i--) {
            set[i] = set[i - 1];
        }
    }
    public void addAll(int[] items){
        for (int item : items) {
            add(item);
        }
    }
}

/**
 * Solution class for code-eval.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * helper function to convert string input to int array.
     *
     * @param      s     { string input from test case file }
     *
     * @return     { int array from the given string }
     */
    public static int[] intArray(final String s) {
        String input = s;
        if (input.equals("[]")) {
            return new int[0];
        }
        if (s.contains("[")) {
            input = s.substring(1, s.length() - 1);
        }
        String[] data = input.split(",");
        int[] intarr = new int[data.length];
        for (int i = 0; i < intarr.length; i++) {
            intarr[i] = Integer.parseInt(data[i]);
        }
        return intarr;
        // return Arrays.stream(input.split(","))
        //        .mapToInt(Integer::parseInt)
        //        .toArray();
    }
    /**
     * main function to execute test cases.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // instantiate this set
        SortedSet s = new SortedSet();
        // code to read the test cases input file
        Scanner stdin = new Scanner(new BufferedInputStream(System.in));
        // check if there is one more line to process
        while (stdin.hasNext()) {
            // read the line
            String line = stdin.nextLine();
            // split the line using space
            String[] tokens = line.split(" ");
            // based on the list operation invoke the corresponding method
            switch (tokens[0]) {
            case "size":
                System.out.println(s.size());
                break;
            case "contains":
                System.out.println(s.contains(Integer.parseInt(tokens[1])));
                break;
            case "print":
                System.out.println(s);
                break;
            case "add":
                int[] intArray = intArray(tokens[1]);
                if (intArray.length == 1) {
                    s.add(intArray[0]);
                } else {
                    s.add(intArray);
                }
                break;
            case "addAll":
                intArray = intArray(tokens[1]);
                s.addAll(intArray);
                break;
            case "subSet":
                intArray = intArray(tokens[1]);
                if(intArray[0] > intArray[1]){
                    System.out.println("Invalid Arguments to Subset Exception");
                    break;
                }
                int[] data = s.subSet(intArray[0], intArray[1]);
                String str = "{";
                for (int i = 0; i < data.length; i++) {
                    str += data[i];
                    if (i < data.length - 1) {
                        str += ", ";
                    }
                }
                str += "}";
                System.out.println(str);
                break;
            case "headSet":
                intArray = intArray(tokens[1]);
                data = s.headSet(intArray[0]);
                str = "{";
                for (int i = 0; i < data.length; i++) {
                    str += data[i];
                    if (i < data.length - 1) {
                        str += ", ";
                    }
                }
                str += "}";
                System.out.println(str);
                break;
            case "last":
                if(s.size() == 0){
                    System.out.println("Set Empty Exception");
                }
                System.out.println(s.last());
                break;
            case "intersection":
                s = new SortedSet();
                SortedSet t = new SortedSet();
                intArray = intArray(tokens[1]);
                s.add(intArray);
                intArray = intArray(tokens[2]);
                t.add(intArray);
                System.out.println(s.intersection(t));
                break;
            case "retainAll":
                s = new SortedSet();
                intArray = intArray(tokens[1]);
                s.add(intArray);
                intArray = intArray(tokens[2]);
                System.out.println(s.retainAll(intArray));
                break;
            case "cartesianProduct":
                s = new SortedSet();
                t = new SortedSet();
                intArray = intArray(tokens[1]);
                s.add(intArray);
                intArray = intArray(tokens[2]);
                t.add(intArray);
                System.out.println(Arrays.deepToString(s.cartesianProduct(t)));
                break;
            default:
                break;
            }
        }
    }
}

