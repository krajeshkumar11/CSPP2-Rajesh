
import java.util.*;
import java.lang.*;
import java.io.*;
interface BOW
{
	public void dict(String s[]);
	public int euclidian_norm(HashMap<String,Integer> h);

}
class creating_dict implements BOW
{

	HashMap<String,Integer> h=new HashMap<String,Integer>();
	public void dict(String s[])
	{
		for(int i=0;i<s.length;i++)
		{
			if(h.containsKey(s[i]))
			{
				h.put(s[i],(h.get(s[i]))+1);
			}
			else
			{
				h.put(s[i],1);
			}
		}
	}
	public int euclidian_norm(HashMap<String,Integer> h)
	{
		int sum=0;
		for(String str:h.keySet())
		{
			sum=sum+(h.get(str)*h.get(str));
		}
		return sum;
	}
}
public class Bag_of_words
{
	public static String Fileopen(File f)throws FileNotFoundException
	{
		String s=String.valueOf(f);
		int c=0;
		File  file = new File(s);
		String s1="";
		try
		{
			Scanner sc= new Scanner(file);
		    while(sc.hasNextLine())
		    {
		      s1+=sc.nextLine();
		      s1=s1.replace("\n"," ");
		      c=c+1;
		    }
		    sc.close();
		}
		catch(Exception e)
		{
		    e.printStackTrace();
		}

		return s1;
  }
	public static void main(String[] args)throws FileNotFoundException
	 {

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
		else{
		System.out.printf("%6s"," ");
		for (int i=0;i<k;++i )
		{
			//System.out.print(file_name[i].getName());
			for (int j=0;j<k;++j)
			{

				String s=Fileopen(file_name[i]).toLowerCase();
				String s1=Fileopen(file_name[j]).toLowerCase();
				// System.out.println(s);
				// System.out.println(s1);
	 // 	String s="what is your name to be or not to be";
		// String s1="what is your name doubt truth to be a liar";
			 	String array1[]=s.split(" ");
			 	String array2[]=s1.split(" ");
			 	creating_dict c1=new creating_dict();
			 	creating_dict c2=new creating_dict();
			 	Bag_of_words bag=new Bag_of_words();
			 	c1.dict(array1);
			 	c2.dict(array2);
			 	int sum=bag.dotproduct(c1.h,c2.h);
			 	int p1=c1.euclidian_norm(c1.h);
			 	int p2=c1.euclidian_norm(c2.h);
				// System.out.println(sum+":"+p1+":"+p2);
				double denominator=(Math.sqrt(p1)*Math.sqrt(p2));
				double percentage=(sum/denominator)*100;
		//		System.out.printf("      "+Math.round(percentage));
				Matrix[i][j]=Math.round(percentage);
				Matrix[j][i]=Math.round(percentage);
			}
			//System.out.println("\n");
		}

		for (int i=0;i<k;++i )
		 {
			System.out.printf("    "+file_name[i].getName());	// For Matrix printing in columns
		}
		System.out.println("");
		int i=0;
		 	for(long[] row : Matrix) {
		 		System.out.print(file_name[i].getName()+"\t");
           		 for (long column : row) {
              	  System.out.print(column + "\t");
            }i++;
            System.out.println();
    	}
    	int d1=0, d2=0;
    	long max=0;
    	for(i=0; i<Matrix.length; i++){
          //  System.out.println("max "+max);
            for(int j=0; j<Matrix[i].length; j++){
                if(max<Matrix[i][j]&&i!=j){
              //  	System.out.println(max+" "+Matrix[i][j]);
                    max= Matrix[i][j];
                    d1=i;
                    d2=j;
                }
            }
		}
		 System.out.println("Maximum similarity in "+file_name[d1].getName()+" and "+file_name[d2].getName());
		}
	}
	  	public int dotproduct(HashMap<String,Integer> h1,HashMap<String,Integer> h2)
	{

		int sum=0;
	 	for(String str1:h1.keySet())
	 	{
	 		for (String str2:h2.keySet())
	 		{
	 			if(str1.equals(str2))
	 			{
	 				sum=sum+(h1.get(str1)*h2.get(str2));
	 			}

	 		}
	 	}
	 	return sum;
	}

}
