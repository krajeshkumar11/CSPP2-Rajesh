import java.io.BufferedInputStream;
import java.util.Scanner;

public class List {
	//Implement all the methods mentioned to build a ListADT

    /*
     * The goal for the list is to store items.
     * How are we going to store the items in the list?
     * An array would be good. Right?
     * So, assume we are only going to have ints in the list
     * We need to create an array of ints to store the items
     * added to the list.
     *
     * Create a variable of the type int[]
     * Use the private access specifier
     * Why private access specifier and why not public?
     * Well, we don't want the array to be manipulated by
     * methods that are outside the List class.
     * If you allow methods outside the List class to manipulate
     * the array then there is a possibility of having a corrupted
     * list i.e., a list with incorrect items.
     * This is not desirable and so having private access specifer
     * will protect the array such corruption.
     * This is a hard concept to understand. Discuss with your mentor.
     *
    */

    // declare a private int[]
    // don't create the array yet using new
    // that's the job of the List constructor
    public final int DEFAULT = 10;
    private int[] list;

    /*
     * What are the other class variables needed for creating a list?
     * How about keeping track of the size of the list?
     * If you add 2 items to the list then the size should be 2.
     * Let's think about the size of the list by comparing it to the
     * length of the array. Do they mean the same?
     * No, Array length signifies the maximum number of items
     * you can store in the list. Whereas, the size of the list
     * denotes the number of items in the list. Makes sense?
     * Here is an example:
     * array = [1,2,3,0,0,0,0,0,0,0]
     * The length of the array is DEFAULT and size is 3.
     * This means you can store DEFAULT items in the list and
     * currently it has 3 items.
     * So, to keep track of the size we need a variable called size
     * Again, we use private as we don't want that size variable
     * to be accessed by the methods that are outside of the List class.
     *
     */

    // declare a private int size
    // again, don't initialize it here
    // variable initialization should be done in the constructor
    private int size;

    /*
     * The purpose of the constructor is to initialize the class variables with
     * some default values.
     */
    public List() {

        // what are the two variables to be initialized here?
        // think about the private variables described above.
        // What should be the default values?
        // In the case of the list, it should be empty but
        // it should be initialized with an array size like DEFAULT

        // Think about the initial value for size.
        // How many items do we have in the list when you create it?
        // An empty list has how many items?
        // That is the initial value to use for size.
        list = new int[DEFAULT];
        size = 0;

    }

    /*
     * Overloaded constructor with list capacity as argument The default
     * constructor sets the list capacity to DEFAULT So, adding an item when the list
     * size is DEFAULT raises a Index Out of Bounds Exception There will be some
     * clients of the ADT that will require the list to contain n elements which
     * is known at the time of creating the list.
     *
     * The overloaded constructor is a way to initialize a list with a list
     * capacity of n items where n is given as an argument to constructor.
     *
     * @param      capacity  The capacity
     */
    public List(int capacity) {
        size = 0;
        list = new int[capacity];
    }

    /*
     * The add method does what the name suggests. Add an int item to the list.
     * The assumption is to store the item at the end of the list What is the
     * end of the list? Is it the same as the end of the array? Think about how
     * you can use the size variable to add item to the list.
     *
     * The method returns void (nothing)
     *
     * @param      item  The item
     */
    public void add(int item) {
        //Inserts the specified element at the end of the list.
        if (size == list.length) {
            resize();
            add(item);
            // list[size++] = item;
        } else {
            list[size++] = item;
        }
    }

    /*
     *
     * Resize the list
     * Sometimes the clients of the ADT won't know the expected list capacity
     * To solve this the list has to grow dynamically
     * when the maximum capacity is reached and there is no room to add items.
     * So, how do we dynamically resize the list?
     * Java doesn't support resize of array. Here are some options.
     *
     * Option 1
     * Create a new array of the desired size,
     * and copy the contents from the original array to the new array,
     * using java.lang.System.arraycopy(...);
     *
     * Option 2
     * Use java.util.Arrays.copyOf(...) methods which returns a bigger array,
     * with the contents of the original array.
     *
     * TODO
     * Create a method called resize(). Resize should create an new array that is
     * double the size of the old array.
     * Then copy the contents of the old array to the new one.
     *
     * When should the resize method be invoked and from where?
     * Will the client invoke resize or is it internal to List class?
     * Should the resize be public method or private?
     * Should the resize method return any values?
     * You know enough of Object Oriented Programming to answer these questions :-)
     *
     */

    //
    // todo create resize method
    //
    private void resize() {
        int[] newlist = new int[2 * list.length];
        System.arraycopy(list, 0, newlist, 0, size);
        list = newlist;
    }

    /*
     * The size method returns the value of the size. The purpose of the method
     * is to announce the size of the list to the objects outside the list
     *
     * The method returns an int. Empty list should return 0.
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
        // replace the code below to implement the size method
        return size;
    }

    /*
     * The remove method does what the name suggests.
     * Removes an int item, specified by the index argument, from the list
     * It also does an additional step.
     * Think about what happens when
     * an item is removed from the middle of the list
     * It creates a hole in the list, right?
     * This would mean, all the items that are
     * to the right side of the removed item should be
     * moved to the left by one position.
     * Here is an example:
     * array = [1,2,3,0,0,0,0,0,0,0]
     * remove(1) would remove the item 2 which is at index position 1.
     * But how do you remove the item from an array?
     * Well, the way to remove it is to move all
     * the items, that are to the right of the removed item, to the left
     * So, the new array looks like this.
     * array = [1,3,0,0,0,0,0,0,0,0]
     * The method returns void (nothing)
     */
    //
    // size = DEFAULT 8 < 9 [1,2,3,0,0,0,0,0,11,14]
    //
    // @param      index  The index
    //
    public void remove(int index) {
        // write the logic for remove here.
        // Think about what to do to the size variable.
        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                list[i] = list[i + 1];
            }
            list[size - 1] = 0;
            size--;
        } else {
            System.out.println("Invalid Position Exception");
        }
    }

    /*
     * Get method has to return the items that is at the index position passed
     * as an argument to the method. If the item doesn't exist then return a -1
     * to indicate that there is no element at that index. How can an element
     * not be there at a given position? Well, if the position is greater than
     * the number of items in the list then that would mean the item doesn't
     * exist. How do we check if the position is greater than the number of
     * items in the list? Would size variable be useful?
     *
     * @param      index  The index
     *
     * @return     { description_of_the_return_value }
     */
    public int get(int index) {
        // Replace the code below to write the code for get
        if (index > -1 && index < size) {
            return list[index];
        }
        return -1;
    }

    /*
     * What happens when you print an object using println? Java provides a
     * method named toString that is internally invoked when an object variable
     * is used in println. For example: List l = new List();
     * System.out.println(l); This statement is a shortcut for
     * System.out.println(l.toString());
     *
     * So, implement the toString method to display the items in the list in the
     * square brackets notation. i.e., if the list has numbers 1, 2, 3 return
     * the string [1,2,3] Caution: The array may be having other elements
     * Example: [1,2,3,0,0,0,0,0,0,0] toString should only return the items in
     * the list and not all the elements of the array.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        // Replace the code below
        String str = "[";
        String cmm = ",";
        for ( int i = 0; i < size; i++) {
            str += Integer.toString(list[i]);
            if (i < size - 1) {
                str += cmm;
            }
        }
        str += "]";
        return str;
    }

    /*
     * Contains return true if the list has the item passed as an argument to
     * the method So, iterate through the list and return true if the item
     * exists and otherwise false
     *
     * @param      item  The item
     *
     * @return     { description_of_the_return_value }
     */
    public boolean contains(int item) {
        // Replace the code below
        return indexOf(item) >= 0;
    }

    /*
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element.
     *
     * @param      item  The item
     *
     * @return     { description_of_the_return_value }
     */
    public int indexOf(int item) {
        // Replace the code below
        for (int i = 0; i < size; i++) {
            if (list[i] == item) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds all.
     *
     * @param      arr   The arr
     */
    public void addAll(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }
    }

    /**
     * { function_description }
     *
     * @param      str   The string
     *
     * @return     { description_of_the_return_value }
     */
    public int[] convertToInt(String str) {
        String[] strArr = str.split(",");
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    /**
     * { function_description }
     *
     * @param      item  The item
     *
     * @return     { description_of_the_return_value }
     */
    public int count(int item) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (list[i] == item) {
                count++;
            }
        }
        return count;
    }

    /**
     * { function_description }
     *
     * @param      index  The index
     * @param      item   The item
     */
    public void add(int index, int item) {
        if (index >= 0) {
            for (int i = size; i > index ; i--) {
                list[i] = list[i - 1];
            }
            list[index] = item;
            size++;
        } else {
            System.out.println("Negative Index Exception");
        }
    }
    /**
     * Removes all.
     *
     * @param      items  The items
     */
    public void removeAll(int[] items) {
        // write the logic
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < size; j++) {
                if (items[i] == list[j]) {
                    remove(j);
                    // System.out.println(toString());
                }
            }
        }
    }

    /*
     Returns a list containing elements, including startIndex and excluding
     endIndex. The first parameter indicates the startIndex and the second
     parameter indicates the endIndex.

     @param      startIndex  The start index
     @param      endIndex    The end index

     @return     { description_of_the_return_value }
    */
    public List subList(int startIndex, int endIndex) {
        // write the logic for subListpl
        List newlist = new List();
        if (startIndex >= 0 && endIndex >= 0 && startIndex < endIndex && endIndex <= size()) {
            // E[] newint = ((E[])new Object[endIndex - startIndex]);
            int count = 0;
            for (int i = startIndex; i < endIndex; i++) {
                newlist.add(get(i));
                // System.out.println(i + " " + endIndex + " " + newint[i] + " " + size());
            }
            return newlist;
        }else if (startIndex < 0 || endIndex < 0 || endIndex > size()) {
            System.out.println("Index Out of Bounds Exception");
            return null;
        } else {
            return newlist;
        }
    }
    /*Returns a boolean indicating whether the parameter
      i.e a List object is exactly matching with the given list or not.
     */
    public boolean equals(List listdata) {
        for (int i = 0; i < listdata.size(); i++) {
            if (!contains(listdata.get(i))) {
                return false;
            }
        }
        return true;
    }
    /*Removes all the elements from list*/
    public void clear() {
        // write the logic for clear.
        list = new int[DEFAULT];
        size = 0;
    }

	public static void main(String[] args) {
        // create an object of the list to invoke methods on it
        List l = new List();

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
                case "add":
                String[] position = tokens[1].split(",");
                if (position.length == 1) {
                    l.add(Integer.parseInt(position[0]));
                } else {
                    l.add(Integer.parseInt(position[0]), Integer.parseInt(position[1]));
                }

                break;
                case "size":
                // invoke size method and print the list size
                // BTW, list size is not the array size
                // it is the number of items in the list
                System.out.println(l.size());
                break;
                case "print":
                // print the list (implement toString for this to work)
                // expected format is [item-1,item-2,...,item-n]
                // review the output testcase file
                System.out.println(l);
                break;
                case "remove":
                l.remove(Integer.parseInt(tokens[1]));
                break;
                case "indexOf":
                System.out.println(l.indexOf(Integer.parseInt(tokens[1])));
                break;
                case "get":
                System.out.println(l.get(Integer.parseInt(tokens[1])));
                break;
                case "contains":
                System.out.println(l.contains(Integer.parseInt(tokens[1])));
                break;
                case "addAll":
                if (tokens.length == 2) {
                    l.addAll(l.convertToInt(tokens[1]));
                }
                break;
                case "count":
                System.out.println(l.count(Integer.parseInt(tokens[1])));
                break;
                case "removeAll":
                    if (tokens.length == 2) {
                        String[] t2 = tokens[1].split(",");
                        int[] a = new int[t2.length];
                        for (int i = 0; i < t2.length; i++) {
                            a[i] = Integer.parseInt(t2[i]);
                        }
                        l.removeAll(a);
                    }
                break;
                case "subList":
                    if (tokens.length != 2) {
                        break;
                    }
                    String[] arrstring3 = tokens[1].split(",");
                    List object = l.subList(Integer.parseInt(arrstring3[0]),
                            Integer.parseInt(arrstring3[1]));
                    if (object != null) {
                        System.out.println(object);
                    }
                    break;
                case "equals":
                    if (tokens.length == 2) {
                        String[] lt = tokens[1].split(",");
                        List l2 = new List();
                        for (int k = 0; k < lt.length; k++) {
                            l2.add(Integer.parseInt(lt[k]));
                        }
                        System.out.println(l.equals(l2));
                    }
                break;
                case "clear":
                    l.clear();
                break;
                default:
                break;
            }
        }
	}
}
