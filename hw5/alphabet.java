import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
	private Set<Character> english_alphabet = new LinkedHashSet<Character>();
	private Map<Character, Map<Character, Character>> map = new HashMap<Character,  Map<Character, Character>>();
	/**
	 * alphabet constructor to fill the english alphabet and the map
	 */
	public alphabet() {
		// do not edit this method
		fill_english_alphabet();
		fill_map();
	}
	/**
	 * fill_english_alphabet method to fill the english alphabet set
	 */
	private void fill_english_alphabet() {
		// do not edit this method
		for(char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
		    english_alphabet.add(c);
		}
	}

	/**
	 * fill_map method to fill the map using 2 iterators of english_alphabet set,
     *  it uses some char operations to calculate the difference between the characters to make shifting
	 */
	private void fill_map() {
		// You must use the "english_alphabet" variable in this method, to fill the "map" variable.
		// You can define 1 or 2 iterators to iterate through the set items.


		Iterator<Character> it = english_alphabet.iterator();
		Iterator<Character> it2;
		/**
		 * In this loop first iterator goes 26 rows and takes the values as first key
		 * the inneriterator will start from the first element of the alphabet set and will go to the end
         * when traversing it will take the characters as second key for the innermap of the first key then
         * the second value will be calculated as the absolute value of the sum of the first key and the second key modulo 26
         * the result will be taken as absolute value and added to 'A' to get the second value which will be the innermaps value for the secondKey
		 */
		while(it.hasNext()){
			Character firstKey = it.next();
			it2 = english_alphabet.iterator();
			Map<Character,Character> innerMap = new HashMap<Character,Character>();
			while(it2.hasNext()){
				Character secondKey = it2.next();
				Character secondVal = (char) (((Math.abs(secondKey + firstKey)) % 26) + 'A');
				innerMap.put(secondKey, secondVal);
			}

			map.put(firstKey,innerMap);
		}


	}
    /**
     * print map method to print the map
     */
	public void print_map() {
		// do not edit this method
		System.out.println("*** Viegenere Cipher ***\n\n");
		System.out.println("    " + english_alphabet);
		System.out.print("    ------------------------------------------------------------------------------");
		for(Character k: map.keySet()) {
			System.out.print("\n" + k + " | ");
			System.out.print(map.get(k).values());
		}
		System.out.println("\n");
		
	}

    /**
     * getter method for the map
     * @return map
     */

	public Map get_map() {
		return map;
	}
}