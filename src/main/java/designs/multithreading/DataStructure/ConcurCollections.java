package designs.multithreading.DataStructure;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class ConcurCollections {

	public static void main(String[] args) {
		
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>(10);
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		Map<String, Integer> map3 = Collections.synchronizedMap(map2); //slower than the concurrent Solution
		
		LinkedBlockingDeque<String> blockQue = new LinkedBlockingDeque<String>();
	
	}
}
