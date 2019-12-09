import java.io.IOException;
import java.util.*;
public class test {

    public static <K,V,C> void main(String[] args) throws IOException {
    	Scanner sc=new Scanner(System.in);
    	int key=0; int count=0; int index=0; String search=""; int handledNumber=0;
        //ProbeHashMap pht=new ProbeHashMap(5000);
        BrentHashMap pht=new BrentHashMap(5000);
        pht.loadTextWordsToWordsArray();
        pht.convertWordAndLoadToIntsArray();
        boolean isExist=false;
        int getCount=0;  //this' just to control the counter function
        int a=0;
 
        
      //--------------------------------------------THE CODE-----------------------------------///
        for (int i = 0; i < pht.intsArray.length; i++) {
    		pht.put(pht.intsArray[i], pht.wordsArray[i]);}
       
        for (int j = 0; j < 5000; j++) {            //// calculate the counts of words in hash map...

            for (int i = 0; i < pht.wordsArray.length; i++) {
            	try {
            		if(pht.table[j].getValue().equals(pht.wordsArray[i])) { 
            			a++;
            			pht.table[j].setCount(a);
            		}
    			} catch (NullPointerException e) {   // handling exception
    					e.getMessage();
    			}
            		
    			}
            a=0;
    			
    		}                 // counts are set!
        
        
        while(true) {
        	
        	isExist=false;
        System.out.println("Enter a word to search");
        String inp=sc.nextLine();
        for (int i = 0; i < pht.intsArray.length; i++) {
         	if(inp.equals(pht.wordsArray[i]))
         		getCount++;
 			
         }
        
        
        for (int i = 0; i < pht.wordsArray.length; i++) {  //scan all the words; if user enter a non-exist word return error message!
    		if(inp.equals(pht.wordsArray[i])) {
    			search=pht.wordsArray[i];
    			handledNumber=i;
    			key=pht.intsArray[handledNumber];
    			isExist=true;
    			break;
    		}
    		
        }
        if(isExist) {
        index=pht.search(pht.intsArray[handledNumber], search);  //search to word in hash table with key and value (keys stored in intsArray)
        
        System.out.println("------ Brent’s Method Hashing ------");
        System.out.println("Search: " + search);
        System.out.println("Key: "+ key);				 
        System.out.println("Count: "+  pht.table[pht.search(key, search)].getCount() );       //+ "," + getCount (to control) 
       		 
        System.out.println("Index: "+ index);  				 
        }
        else { 
        	System.out.println("no word!");
        }
        
                                                                                                                                                                                 getCount=0;
        }
        // ----------------------------------------------------- THE CODE ---------------------------------------//
         
        
        
      

  
        
        
     
       

    	
        
        
        
        
     //see all words in wordsArray
     /*for (int i = 0; i < pht.intsArray.length; i++) {
 		System.out.println(pht.wordsArray[i]);
 	  } */
   
      //----------------------------CONTROL PUT AND SEARCH!!-------------------------------------
        /*
        for (int i = 0; i < 200; i++) {
			pht.put(pht.intsArray[i], pht.wordsArray[i]);
		}
        
      
        
        for (int i = 0; i < 200; i++) {
            System.out.println(pht.wordsArray[i]+","+pht.intsArray[i] +" : " + pht.search(pht.intsArray[i], pht.wordsArray[i])); 
		}
        */
        //----------------------------CONTROL PUT AND SEARCH!!-------------------------------------

        /*
        pht.put(4000, "ahmet");
       System.out.println(pht.get(4000)); 
        pht.put(5000, "ali");
        pht.put(5000, "haydar"); 

        
        System.out.println(pht.search(5000,"haydar")); 
        */
      

    }

}