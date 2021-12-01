package designs.RandomizedSet;

import java.util.*;

class RandomizedSet {

    LinkedHashSet<Integer> set;
    Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        set = new LinkedHashSet<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {

        if(!set.contains(val)){
            set.add(val);
            return true;
        }

        return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {

        if(set.contains(val)){
            set.remove(val);
            return true;
        }

        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {

        int rand = random.nextInt(set.size());
        Iterator<Integer> iterator = set.iterator();
        int index=0;
        while(iterator.hasNext()&&index<rand){
            iterator.next();
        }
        return iterator.next();
    }
}
