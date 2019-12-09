import java.util.ArrayList;


public class ProbeHashMap<K,V,C> extends AbstractHashMap<K,V,C> {
    public MapEntry<K,V,C>[] table;        // a fixed array of entries (all initially null)
    public MapEntry<K,V,C> DEFUNCT = new MapEntry<>(null, null,null);   //sentinel
    public int count=0;
   
    // provide same constructors as base class
    /** Creates a hash table with capacity 17 and prime factor 109345121. */
    public ProbeHashMap() { super(); }

    /** Creates a hash table with given capacity and prime factor 109345121. */
    public ProbeHashMap(int cap) { super(cap); }

    /** Creates a hash table with the given capacity and prime factor. */
    public ProbeHashMap(int cap, int p) { super(cap, p); }

    /** Creates an empty table having length equal to current capacity. */
    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
        table = (MapEntry<K,V,C>[]) new MapEntry[capacity];   // safe cast
    }

    /** Returns true if location is either empty or the "defunct" sentinel. */
    private boolean isAvailable(int j) {
        return (table[j] == null || table[j] == DEFUNCT);
    }
    
    

    private int findSlot(int h, K k) {
        int avail = -1;                               // no slot available (thus far)
        int j = h;                                    // index while scanning table
        do {
            if (isAvailable(j)) {                       // may be either empty or defunct
                if (avail == -1) avail = j;               // this is the first available slot!
                if (table[j] == null) break;              // if empty, search fails immediately
            } else if (table[j].getKey().equals(k))
                return j;                                 // successful match
            j = (j+1) % capacity;                       // keep looking (cyclically)
        } while (j != h);                             // stop if we return to the start
        return -(avail + 1);                          // search has failed
    }


    @Override
    protected V bucketGet(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0) return null;                   // no match found
        
        return table[j].getValue();
    }
  


    @Override
    protected V bucketPut(int h, K k, V v, C c) {
    	int intCount=0;
    	C count=null;
    	String strCount="";
            int j = findSlot(h, k);
        if (j >= 0)   {                            // this key has an existing entry
        	
            return table[j].setValue(v) ;
            
            }
        table[-(j+1)] = new MapEntry<>(k, v, c);     // convert to proper index
        n++;
        return null;
    }


    private int convertCountToInt(C count, Class<Integer> class1) {
		return class1.cast(count);
		
	}
   

	@Override
    protected V bucketRemove(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0) return null;                   // nothing to remove
        V answer = table[j].getValue();
        table[j] = DEFUNCT;                       // mark this slot as deactivated
        n--;
        return answer;
    }


    @Override
    public Iterable<Entry<K,V,C>> entrySet() {
        ArrayList<Entry<K,V,C>> buffer = new ArrayList<>();
        for (int h=0; h < capacity; h++)
            if (!isAvailable(h)) buffer.add(table[h]);
        return buffer;
    }

	
    
    

	
}