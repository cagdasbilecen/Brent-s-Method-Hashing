import java.io.*;
import java.util.*;

public class BrentHashMap<K,V,C> extends ProbeHashMap<K,V,C> {

	String wordsArray[] = new String[14458]; // words of story.txt
	int intsArray[] = new int[14458]; //ints of words
	
	public BrentHashMap(int cap) {
		
		super(cap);
		
	}
	
		
    public void loadTextWordsToWordsArray() throws IOException {  //load words of story.txt to wordsArray
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("C://Users//cadob//OneDrive//Masaüstü//story.txt"),"UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        }
        int i=0;
        while (sc2.hasNextLine()) {
                Scanner s2 = new Scanner(sc2.nextLine());
            while (s2.hasNext()) {
                String s = s2.next();
                if(s.length()==1) { 
                	wordsArray[i]="."+s.toLowerCase()+".";
                }
                else {
                wordsArray[i]=s.toLowerCase(); }
                i++; 
            } } 
        
      
    }
        
      public void convertWordAndLoadToIntsArray() { //create Keys of words..
    	 
    	
    	  for (int i = 0; i < wordsArray.length; i++) {
    		  String strint="";
    		  for (int j = 0; j < wordsArray[i].length(); j++) {
    			  int intgr = (int)wordsArray[i].charAt(j); //wordun ilk karakterini int olarak tuttuk
    			  String str=Integer.toString(intgr); //wordun ilk karakterini string olarak tuttuk
    			  strint=(strint+str); //string olarak tutulan sayýsal karakterleri yan yana getirdik
    			  if(strint.length()>=6) {strint=strint.substring(0, 6); } //6 karakterden büyük olanlarý ilk 6 karakterini key yap! 
    			 
    			  } 
    		  intsArray[i]=Integer.parseInt(strint); 
    	
		}  
    	  
      }
      
      public void run() { 
    	  
      }
        
        
        
}
		  
		 
		
	
	


	


