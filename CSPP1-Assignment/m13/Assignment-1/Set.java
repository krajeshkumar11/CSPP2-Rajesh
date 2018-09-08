/**
 * Class for set.
 */
public final class Set{

    private int[] set;
    private int size;
    /**
     * Constructs the object.
     */
    public Set(){
        set = new int[10];
        size = 0;
    }

    /**
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int size(){
        return size;
    }
    /**
     * { function_description }
     *
     * @param      item  The item
     *
     * @return     { description_of_the_return_value }
     */
    public boolean contains(int item){
        if(size() > 0){
            for (int i = 0; i < size(); i++) {
                if(item == this.get(i)){
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
    public String toString(){
        String str = "{";
        for (int i = 0; i < size(); i++) {
            str += set[i];
            if(i < size() - 1){
                str += ", ";
            }
        }
        str += "}";
        return str;
    }
    /**
     * { item_description }
     *
     * @param      item  The item
     */
    public void add(int item){
        if(size() == set.length){
            resize();
        }
        set[size++] = item;
    }

    /**
     * { function_description }
     */
    public void resize(){
        int[] newset = new int[2 * set.length];
        System.arraycopy(set, 0, newset, 0, size());
        set = newset;
    }

    /**
     * { function_description }
     *
     * @param      items  The items
     */
    public void add(int[] items){
        for (int item : items) {
            add(item);
        }
    }

    /**
     * { function_description }
     *
     * @param      index  The index
     *
     * @return     { description_of_the_return_value }
     */
    public int get(int index){
        if(index < size()){
            return set[index];
        }
        return -1;
        // for (int i = 0; i < size(); i++) {
        //     if(set[i] == item){
        //         return set[i];
        //     }
        // }
        // return -1;
    }

    /**
     * { function_description }
     *
     * @param      set   The set
     *
     * @return     { description_of_the_return_value }
     */
    public Set intersection(Set other){
        Set newSet = new Set();
        for (int i = 0; i < size(); i++) {
            if(other.contains(this.get(i)) == true){
                newSet.add(this.get(i));
            }
        }
        return newSet;
    }

    /**
     * { function_description }
     *
     * @param      items  The items
     *
     * @return     { description_of_the_return_value }
     */
    public Set retainAll(int[] items){
        Set newSet = new Set();
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < items.length; j++) {
                if(items[j] == this.get(i)){
                    newSet.add(this.get(i));
                }
            }
        }
        return newSet;
    }

    /**
     * { function_description }
     *
     * @param      set   The set
     *
     * @return     { description_of_the_return_value }
     */
    public int[][] cartesianProduct(Set set){
        return null;
    }
}
