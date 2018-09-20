import java.io.*;
import java.util.HashMap;
import java.util.Arrays;
import java.lang.Math;

public class BOW {
    public static String readFile(String fname)throws Exception {

        File f1 = new File(fname);
        BufferedReader br = new BufferedReader(new FileReader(f1));
        String data = "";
        String st;
        while ((st = br.readLine()) != null){
            data += st;
        }
        return data;
    }

    public static HashMap<String, Integer> generateHashMap(String st) {
        HashMap<String, Integer> HM = new HashMap<>();
        String[] stArr = st.split(" ");
        for (String each : stArr) {
            int count = 1;
            if (HM.containsKey(each)) {
                count = HM.get(each) + 1;
                HM.remove(each);
            }
            HM.put(each, count);
        }
        return HM;
    }

    public static HashMap<String, Integer> getDotProduct(HashMap<String, Integer> hm1, HashMap<String, Integer> hm2) {
        HashMap<String, Integer> dp = new HashMap<String, Integer>();
        String[] f1 = hm1.keySet().toArray(new String[0]);
        for (String each : f1) {
            int one = hm1.get(each);
            int two = 0;
            if (hm2.containsKey(each)) {
                two = hm2.get(each);
                dp.put(each, one * two);
            }
        }
        return dp;
    }

    public static double getSumOfDP(HashMap<String, Integer> hm) {
        Integer[] hmvals = hm.values().toArray(new Integer[0]);
        int total = 0;
        for (Integer each : hmvals) {
            total += each;
        }
        return total;
    }

    public static double getEuclideanNorm (HashMap<String, Integer> hm) {
        Integer[] hmvals = hm.values().toArray(new Integer[0]);
        double total = 0;
        if (hmvals.length > 0) {
            for (Integer each: hmvals) {
                total += each * each;
            }
        }
        return Math.sqrt(total);
    }

    public static void main(String[] args)throws Exception {

        String s1 = readFile("f1.txt");
        String s2 = readFile("f1.txt");

        File folder=new File(args[0]);
        int k=0;

        File[] listoffiles=folder.listFiles();
        long Matrix[][]=new long[listoffiles.length][listoffiles.length];
        File[] file_name=new File[listoffiles.length];
        for (int i=0;i<listoffiles.length ;++i )
         {
            File file=listoffiles[i];
            if(file.getName().endsWith(".txt"))
            {
                 file_name[k]=file;
                k++;
            }
        }
        if(k==0)
        {
            System.out.println("empty directory");
        }
        for (int i=0;i<listoffiles.length ;++i )
         {
            System.out.println(file_name[k]);
        }
        // s1 = s1.replaceAll("[0-9-+.^:,!@#$%^&*()_]","").toLowerCase();
        // s2 = s2.replaceAll("[0-9-+.^:,!@#$%^&*()_]","").toLowerCase();
        // HashMap<String, Integer> f1HashMap = generateHashMap(s1);
        // HashMap<String, Integer> f2HashMap = generateHashMap(s2);
        // double e1 = getEuclideanNorm(f1HashMap);
        // double e2 = getEuclideanNorm(f2HashMap);
        // HashMap<String, Integer> dotProductHM = getDotProduct(f1HashMap, f2HashMap);
        // double dotProductValue = getSumOfDP(dotProductHM);
        // System.out.println("Distance between given files is: " + dotProductValue/(e1 * e2) * 100);
    }
}
