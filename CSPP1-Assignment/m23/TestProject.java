import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;

class TextFiles{
    private List<File> f;
    private ArrayList<String> dataStrings;
    private ArrayList<ArrayList<String>> words = new ArrayList<>();
    private ArrayList<HashMap<String, Integer>> map = new ArrayList<>();
    private ArrayList<ArrayList<Long>> hashValues = new ArrayList<>();

    public TextFiles(List<File> f) {
        this.f = f;// the list of text files is stored in the local variable f
        dataStrings = getDataStrings();// calls the getDataStrings method which gets the entire data in a file into a string for each file in the list
        getWords();// for each file in the list the dataString is taken and from this a list of words in the string is made
        createFrequencyOfWords();// for each list of words a frequency of words list is made
        getHashValues();// gets the list of hash values for each dataString

    }

    private void createFrequencyOfWords() {
        /* This method is for creating lsit of frequency for each list of words*/
        for(ArrayList<String>wordList: words)
        {
            HashMap<String , Integer>wordFrequency = new HashMap<>();// creates a new hashmap for a word and its frequency
            for(String word: wordList)
            {
                if(!wordFrequency.containsKey(word))// if the hashmap does not contain the word it puts the word and its count is made to  1
                    wordFrequency.put(word, 1);
                else// if the hashmap contains the word its count is increased by 1
                    wordFrequency.put(word, (Integer)wordFrequency.get(word)+1);
            }
            map.add(wordFrequency);// the hashmap for a list of words is added to the list of hashmaps
        }
    }

    private ArrayList<String> getDataStrings() {
        ArrayList<String> textStrings = new ArrayList<>();
        for (int i = 0; i < f.size(); i++) {
            StringBuilder sb = new StringBuilder("");//creates a variable of type StringBulder
            Scanner in;// creates a variable of type Scanner
            try {
                in = new Scanner(f.get(i));// scans eac file in the list of files
                while (in.hasNextLine()) {
                    sb.append(in.nextLine());//appends each line in the file to the StringBuilder variable
                }
                for(int j = 0 ; j < sb.length() ; j++) {
                    if (sb.charAt(j) >= 'a' && sb.charAt(j) <= 'z' || sb.charAt(j) >= 'A' && sb.charAt(j) <= 'Z' || sb.charAt(j) == '_' || sb.charAt(j) >= '0' && sb.charAt(j) <= '9') {
                        /* if the character in a SringBuilder is alpha numeric iteration continues*/
                        continue;
                    } else {
                        /*if the character is not alpha numeric a space is inserted in place of that character*/
                        sb.setCharAt(j, ' ');
                    }
                }
                String sbString = sb.toString();// converts the StringBuilder type to a string
                sbString = sbString.toLowerCase();// converts all the upper case characters if any to lower case
                textStrings.add(sbString);// adds the the resulting dataString to the list of dataStrings
            } catch (Exception e) {
                System.out.println(e.getMessage());// if there is any exception this catch block catches it and displays the message
            }

        }
        return textStrings;// returns the list of dataStrings

    }

    private void getWords() {
        ArrayList<String>word;
        for(String s: dataStrings)
        {
            word = new ArrayList<>();
            word.addAll(Arrays.asList(s.split(" ")));// creates a list of words for each dataString in the list of dataStrings
            words.add(word);// adds the list to the list of list of words
        }
    }

    public void comparisonByBagOfWords() {
        /* This method is for computing plagiarism percentages for a list of given files using bag of words procedure*/
        double [][]plagPercentages = new double[map.size()][map.size()];
        for(int i = 0 ; i < map.size() ; i++)
        {
            for(int j = 0 ; j < map.size() ; j++)
            {
                if(i == j)
                    plagPercentages[i][j] = -1.0d;// puts a -1 when a file is compared with itself
                else if (i > j)
                    plagPercentages[i][j] = plagPercentages[j][i];// avoids calculating again for a pair of files
                else
                {
                    double dotProduct = computeDotProduct(map.get(i), map.get(j));// gets the dot product of two files from their respective frequency of words hashmap
                    double mod1 = computeMod(map.get(i));// computes mod for the first file from its frequency of words
                    double mod2 = computeMod(map.get(j));// computes mod for the second file from its frequency of words
                    plagPercentages[i][j] = (dotProduct/(mod1*mod2))*100;// computes the plagiarism percentage

                }

            }
        }
        System.out.println("\n****Plagiarism check through Bag of Words*****");
        printMatrix(plagPercentages);// calls the printMatrix method to print the 2d array in matrix form
    }

    private double computeDotProduct(HashMap<String, Integer>map1,HashMap<String, Integer>map2) {
        /* This method computes the dot product for the given two files*/
        double dotProduct = 0.0d;// the total count initially is zero
        for(String word: map1.keySet())
        {
            if(map2.containsKey(word))
                dotProduct += map1.get(word)*map2.get(word);// for a word present in two frequency lists the count in the two frequency lists is multiplied and added to the total count
        }
        return dotProduct;//returns the total count
    }

    private double computeMod(HashMap<String , Integer>map) {
        /* This method creates a mod for a given frequecy of words*/
        double mod = 0.0;// Initially the mod i zero
        for(Integer i: map.values())
        {
            mod += Math.pow(i.doubleValue(), 2);// for each count for a word the count squared and added to the mod
        }
        return Math.sqrt(mod);//square root of the mod is returned
    }

    public void comparisonByLcs() {
        /* This method is for computing plagiarism percentages for a list of given files using LCS procedure*/
        double [][]plagPercentages = new double[map.size()][map.size()];
        for(int i = 0 ; i < map.size() ; i++)
        {
            for(int j = 0 ; j < map.size() ; j++)
            {
                if(i == j)
                    plagPercentages[i][j] = -1.0d;// puts a -1 when a file is compared with itself
                else if (i > j)
                    plagPercentages[i][j] = plagPercentages[j][i];// avoids calculating again for a pair of files
                else
                {
                    double lcsLength = computeLcs(words.get(i), words.get(j));// gets the length of the longest common sub string
                    plagPercentages[i][j] = ((lcsLength*2)/((""+ words.get(i)).length()+(""+words.get(j)).length()))*100;// computes plagiarism percentage

                }

            }
        }
        System.out.println("\n****Plagiarism check through LCS*****");
        printMatrix(plagPercentages);// calls the printMatrix method to print the 2d array in matrix form
    }

    private double computeLcs(ArrayList<String>word1, ArrayList<String>word2) {
        /*This method calculates the length of the lcs for two given text files*/
        double lcsLen = 0;// intitially the length of the lcs is 0
        double len = 0;
        for(int i = 0 ; i < word1.size() ; i++)
        {
            int itemp = i;
            for(String word: word2)
            {
                if(itemp < word1.size()) {
                    if (word1.get(itemp).equals(word)) {
                        len += word1.get(itemp).length();
                        itemp += 1;
                    } else {
                        if (lcsLen < len) {
                            lcsLen = len;
                            len = 0;
                        }
                        itemp = i;
                    }
                }
                else
                {
                    break;
                }

            }
            if(lcsLen <len) {
                lcsLen = len;
                len = 0;
            }
            if (itemp == word1.size())
                break;
        }
        return  lcsLen;// returns the length of the lcs
    }

    private void getHashValues() {
        ArrayList<Long>hashList;
        for(String data: dataStrings)
        {
            data = data.replaceAll("\\s", "");//removes space with empty string
            hashList = new ArrayList<>();//creates a new arraylist
            long sum;
            for(int i = 0 ; i < data.length() - 4 ; i++)
            {
                sum = 0;
                // calculates sum for 5 consecutive characters in the string and does the sam for next 5 consecutive characters and so on
                sum += ((int)data.charAt(i))*Math.pow(4, 4)+((int)data.charAt(i+1))*Math.pow(4, 3)+((int)data.charAt(i+2))*Math.pow(4, 2)+((int)data.charAt(i+3))*Math.pow(4, 1);
                sum = sum % 10007;//gets the sum modulo 10^4+7
                hashList.add(sum);// adds the sum to the hashlist
            }
            hashValues.add(hashList);// adds the hashlist to the list of list of hashvalues
        }
    }

    public void comparisonByFingerPrinting() {
        /* This method is for computing plagiarism percentages for a list of given files using bag of words procedure*/
        double [][]plagPercentages = new double[map.size()][map.size()];
        for(int i = 0 ; i < map.size() ; i++)
        {
            for(int j = 0 ; j < map.size() ; j++)
            {
                if(i == j)
                    plagPercentages[i][j] = -1.0d;// puts a -1 when a file is compared with itself
                else if (i > j)
                    plagPercentages[i][j] = plagPercentages[j][i];// avoids calculating again for a pair of files
                else
                {
                    double hashLength = computeHashLength(hashValues.get(i), hashValues.get(j));// computes the number of matching hash values
                    plagPercentages[i][j] = ((hashLength*2)/(hashValues.get(i).size()+hashValues.get(j).size()))*100;//computes plagiarism percentage

                }

            }
        }
        System.out.println("\n****Plagiarism check through Finger Printing*****");
        printMatrix(plagPercentages);// calls the printMatrix method to print the 2d array in matrix form
    }

    private double computeHashLength(ArrayList<Long>hashList1, ArrayList<Long>hashList2) {
        double count = 0;// initially the count is 0
        for(Long l1: hashList1)
        {
            for(Long l2: hashList2)
            {
                if(l1.longValue() == l2.longValue())// if the two lists ave the same value the count is incremented
                    count++;
            }
        }
        return count;// returns the count
    }

    private void printMatrix(double [][] percent) {
        System.out.print("File Name\t");// "prints file name"
        for(File file : f)
        {
            System.out.print(file.getName()+"\t");// prints the name of each file on a row
        }
        System.out.println();
        for(int i = 0 ; i < f.size() ; i++) {
            System.out.print(f.get(i).getName()+"\t");// prints the name of each file on a column
            for (int j = 0; j < f.size(); j++) {
                DecimalFormat df = new DecimalFormat("#00.00");//formats the number to have three digits with 4th digit being optional
                System.out.print(df.format(percent[i][j]) + "\t\t");//prints the formatted number

            }
            System.out.println();// prints the new line for next row to be printed
        }
    }
}

public class TestProject {
    public static void main(String[] args) throws Exception {

        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));// This creates a new print stream, without automatic line flushing, with the specified file
        System.setOut(out);                                                         // reassigns the standard output stream to output.txt

        JFileChooser chooser = new JFileChooser();                                  // creates a new instance of JFileChooser class
        chooser.setMultiSelectionEnabled(true);                                     // enables the user to select multiple files
        chooser.showOpenDialog(null);                                        // opens a prompt in which we can select multiple files
        File[] textFilesArray = chooser.getSelectedFiles();                         // gets the selected files into an array

        List<File> textFiles = Arrays.asList(textFilesArray);                       // takes the array of files and puts them into a list
        TextFiles tf = new TextFiles(textFiles);                                    // creates a new object for TextFiles class by passing a list of textfiles

        tf.comparisonByBagOfWords();                                                // calls comparisonByBagOfWords method
        tf.comparisonByLcs();                                                       // calls comparisonByLcs method
        tf.comparisonByFingerPrinting();                                            // calls comparisonByFingerPrinting method

        /*This line and the below line
        opens the "output.txt" file in which output is being printed*/

        Runtime load = Runtime.getRuntime();
        Process p = load.exec("notepad " + "C:\\Users\\LENOVO\\IdeaProjects\\TestProject\\output.txt");

    }
}
