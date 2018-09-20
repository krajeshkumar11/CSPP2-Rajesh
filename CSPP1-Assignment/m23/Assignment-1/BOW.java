import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;

class Document {
	private String document;
	private HashMap<String, Integer> hashMap;
	private double euclideanNormValue;

	public Document () {
		this.document = "";
		this.hashMap = new HashMap<String, Integer>();
		this.euclideanNormValue = 0;
	}

	public String getDocument() {
		return this.document;
	}

	public HashMap<String, Integer> getHashMap() {
		return this.hashMap;
	}

	public double getEuclideanNormValue () {
		return euclideanNormValue;
	}

	public void cleanString() {
		this.document = this.document.replaceAll("[0-9_]","").toLowerCase();
	}

	public void readFile(File f1)throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(f1));
        String data = "";
        String st;
        while ((st = br.readLine()) != null){
            data += st;
        }
        this.document = data;
    }

    public void generateHashMap() {
        HashMap<String, Integer> HM = new HashMap<>();
        String[] stArr = this.document.split(" ");
        for (String each : stArr) {
            int count = 1;
            if (HM.containsKey(each)) {
                count = HM.get(each) + 1;
                HM.remove(each);
            }
            HM.put(each, count);
        }
        this.hashMap = HM;
    }

    public void generateEuclideanNorm () {
        Integer[] hmvals = this.hashMap.values().toArray(new Integer[0]);
        double total = 0;
        if (hmvals.length > 0) {
            for (Integer each: hmvals) {
                total += each * each;
            }
        }
        this.euclideanNormValue = Math.sqrt(total);
    }
}

public class BOW {
    // public static String readFile(File f1)throws Exception {
    //     BufferedReader br = new BufferedReader(new FileReader(f1));
    //     String data = "";
    //     String st;
    //     while ((st = br.readLine()) != null){
    //         data += st;
    //     }
    //     return data;
    // }

    // public static HashMap<String, Integer> generateHashMap(String st) {
    //     HashMap<String, Integer> HM = new HashMap<>();
    //     String[] stArr = st.split(" ");
    //     for (String each : stArr) {
    //         int count = 1;
    //         if (HM.containsKey(each)) {
    //             count = HM.get(each) + 1;
    //             HM.remove(each);
    //         }
    //         HM.put(each, count);
    //     }
    //     return HM;
    // }

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

    // public static double getEuclideanNorm (HashMap<String, Integer> hm) {
    //     Integer[] hmvals = hm.values().toArray(new Integer[0]);
    //     double total = 0;
    //     if (hmvals.length > 0) {
    //         for (Integer each: hmvals) {
    //             total += each * each;
    //         }
    //     }
    //     return Math.sqrt(total);
    // }

    public static void main(String[] args)throws Exception {
        Scanner sc = new Scanner(System.in);
        String st = "";
        while (sc.hasNext()) {
        	st = sc.nextLine();
        }
        File folder=new File(st);
        int k=0;
        File[] listoffiles = null;
        File[] file_name = null;
        if(!st.equals("")){
        	listoffiles=folder.listFiles();
	        file_name=new File[listoffiles.length];
	        for (int i=0;i<listoffiles.length ;++i )
	        {
	            File file=listoffiles[i];
	            if(file.getName().endsWith(".txt"))
	            {
	                file_name[k]=file;
	                k++;
	            }
	        }
        }
        if(k==0)
        {
            System.out.println("empty directory");
        }
        else{
        	int p = 0, q = 0;
        	long max = 0;
            System.out.print("      	\t");
            for (int i=0;i<file_name.length ;++i ) {
                System.out.print(file_name[i].getName() + "\t");
            }
            System.out.println();
            for (int i=0;i<file_name.length ;++i )
            {
                System.out.print(file_name[i].getName() + "\t");
                for (int j=0;j<file_name.length ;++j )
                {
                	Document documentOne = new Document();
                	Document documentTwo = new Document();
                	documentOne.readFile(file_name[i]);
                	documentTwo.readFile(file_name[j]);
                	documentOne.cleanString();
                    documentTwo.cleanString();
                    documentOne.generateHashMap();
                    documentTwo.generateHashMap();
                    documentOne.generateEuclideanNorm();
                    documentTwo.generateEuclideanNorm();
                    HashMap<String, Integer> dotProductHM = getDotProduct(documentOne.getHashMap(), documentTwo.getHashMap());
                    double dotProductValue = getSumOfDP(dotProductHM);
                    long cosine = Math.round(dotProductValue/(documentOne.getEuclideanNormValue() * documentTwo.getEuclideanNormValue()) * 100);
                    System.out.print(cosine + "\t\t");
                    if (max < cosine && i != j) {
                    	p = i;
                    	q = j;
                    	max = cosine;
                    }
                }
                System.out.println();
            }
            System.out.println("Maximum similarity is between " + file_name[p].getName() + " and " + file_name[q].getName());
        }
    }
}
