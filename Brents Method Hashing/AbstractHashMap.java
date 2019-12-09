import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;


public abstract class AbstractHashMap<K,V,C> extends AbstractMap<K,V,C> {
    protected int n = 0;                 // number of entries in the dictionary
    protected int capacity;              // length of the table // ALWAYS 5000. "5000" is used instead of this variable...
    private int prime;                   // prime factor
    private long scale, shift;           // the shift and scaling factors
    private int newIndex=0; 	//the new index that will determined when collision is up...
    private C count;
    public boolean doNotPut;
    private int pathCount=0;
    private int oldIndex=0;
    

    /** Creates a hash table with the given capacity and prime factor. */
    public AbstractHashMap(int cap, int p) {
        prime = p;
        capacity = cap;
        Random rand = new Random();
        scale = rand.nextInt(prime-1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }

    /** Creates a hash table with given capacity and prime factor 109345121. */
    public AbstractHashMap(int cap) { this(cap,109345121); }  // default prime

    /** Creates a hash table with capacity 17 and prime factor 109345121. */
    public AbstractHashMap() { this(17); }                     // default capacity

    // public methods


    
    @Override
    public int size() { return n; }


    @Override
    public V get(K key) { return bucketGet(hashValue(key), key); }

    //get method with two parameters to check that true value
    public V get(K key,V value) throws ArrayIndexOutOfBoundsException {  // get method which takes two parameters key and value
    	newIndex=(int)hashValue(key);
    	if(bucketGet(hashValue(key), key).equals(value)) {
    		return bucketGet(hashValue(key), key) ;
    	}
    	else if (bucketGet(hashValue(key), key)!=(value)) {         //search algorithm. this equals to search(..,..)
    		try {
    			while(bucketGet(newIndex,key)!=value) {
    			newIndex=(int)(newIndex+increment(key));
    		}      	
}

    		catch(ArrayIndexOutOfBoundsException e) {
    		System.out.println("ERROR: There is no value such as following");
			return value;
    			
    		}
        	return bucketGet(newIndex,key);

    	}
    	else {
    		return value;
    	}
    	
    }
    

    ///implemented from bucketGet's..
    public int search(K key,V value) throws ArrayIndexOutOfBoundsException {   //search with key and value
    	int retValue=0;
    	newIndex=hashValue(key);
    	if(bucketGet(hashValue(key),key).equals(value)) {
    	    retValue= /*"word : " + value + " index: " +*/ hashValue(key);
    	}
    	else {
    		try {
    		while(bucketGet(newIndex,key)!=value) {
    			if(newIndex<=5000) {
    			newIndex=(int)newIndex+increment(key);}
    			else if(newIndex>5000) {
    				newIndex=newIndex+increment(key)-5000;
    			}
    			if(bucketGet(newIndex,key).equals(value)) {
    				break;
    			}
    			
    		}
    		retValue= newIndex ; 
    		}
    		catch(ArrayIndexOutOfBoundsException e) {
    			//System.out.print("ERROR: There is no word such as a " + value);
    			
    		}
    	}
    
    	return retValue;	
    }
    
    		
    	
  

    @Override
    public V remove(K key) { return bucketRemove(hashValue(key), key); }

    //for casting!
    private int convertInstanceOfObject(C count, Class<Integer> class1) {
		return class1.cast(count);
		
	}
    
    //put funcion implemented from bucketGet() function in super class of this one...(probehashmap)
    @Override
    public V put(K key, V value) {
        doNotPut=false;
        
    	V answer = null;
        //System.out.println(key);
	    newIndex=(int)hashValue(key);

    	if(bucketGet(hashValue(key), key)==null) { //if there is no collision 
        answer = bucketPut(hashValue(key), key, value,count); 
        //System.out.println(value+"cakisma olmadan ekledi");
        }
    	else if(bucketGet(hashValue(key), key)!=null) {  //if collision!
    	while(bucketGet(newIndex, key)!=null) {       // shift counter of new key
    		if(bucketGet(newIndex,key).equals(value))
    		{doNotPut=true;
    		
    		//System.out.println(value+"zaten var oldugu için eklemeyecek");
    		
    		break;
    		}
    		if(newIndex+increment(key)<=5000) {
    		newIndex=(int)(newIndex+increment(key)) ; }
    		else if(newIndex+increment(key)>5000) {
    			newIndex=newIndex+increment(key)-5000;
    		}
    		
    		
    	}
    	
    	
    	// probe and second chain controlls are gonna be here...    	
    	
    	if(!doNotPut) {  //sadece ayný value deðilse ekle !!!
    	answer = bucketPut(newIndex,key,value,count); 
    	//System.out.println(value+"cakismaya ragmen basarýyla ekledi");
    	}
    	else if(doNotPut) {
    		//System.out.println(value+"ayný kelime oldugu icin eklemedi");
    	}
    	
    	} 
    	
       // System.out.println(key);
       // if (n > capacity / 2)              // keep load factor <= 0.5
         //   resize(2 * capacity - 1);        // (or find a nearby prime)
        return answer;
    }


    	//if(!doNotPut) {
    	
    	
    	//} 
    	
       // System.out.println(key);
       // if (n > capacity / 2)              // keep load factor <= 0.5
         //   resize(2 * capacity - 1);        // (or find a nearby prime)
        
    

    // private utilities
    /** Hash function applying MAD method to default hash code. */
    //hash and increment functions to put to the hash map
    private int hashValue(K key) {

        return ((int)key%5000);
    }
    
    private int increment(K key) {
    	return  ((int)key / 5000 ) % 5000;
    }
    
   
    //resize will not use!
    /** Updates the size of the hash table and rehashes all entries. */
   /* private void resize(int newCap) {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
        for (Entry<K,V> e : entrySet())
            buffer.add(e);
        capacity = newCap;
        createTable();                     // based on updated capacity
        n = 0;                             // will be recomputed while reinserting entries
        for (Entry<K,V> e : buffer)
            put(e.getKey(), e.getValue());
    } */

    // protected abstract methods to be implemented by subclasses
    /** Creates an empty table having length equal to current capacity. */
    
    //abstract methods... overrided in probehashmap class....
    protected abstract void createTable();


    protected abstract V bucketGet(int h, K k);


    protected abstract V bucketPut(int h, K k, V v, C c );


    protected abstract V bucketRemove(int h, K k);
    
    

}