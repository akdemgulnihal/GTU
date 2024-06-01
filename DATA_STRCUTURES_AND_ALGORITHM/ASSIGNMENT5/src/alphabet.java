import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
	private Set<Character> english_alphabet = new LinkedHashSet<Character>();
	private Map<Character, Map<Character, Character>> map = new HashMap<Character, Map<Character, Character>>();

	public alphabet() {
		// do not edit this method
		fill_english_alphabet();
		fill_map();
	}

	private void fill_english_alphabet() {
		// do not edit this method
		for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
			english_alphabet.add(c);
			//System.out.println(c);  BUNU SİL!!!  ,NE PRINT ETTİĞİNİ GÖRMEK İÇİN YAZDIN
		}
	}

	private void fill_map() {
		// You must use the "english_alphabet" variable in this method, to fill the "map" variable.
		// You can define 1 or 2 iterators to iterate through the set items.


		//Iterator for english_alphabet
		Iterator<Character> englishAlphabetIterator = english_alphabet.iterator();
    	int be=0;

		// Iterating for each character in the English alphabet in this loop
		while (englishAlphabetIterator.hasNext()) {

			// Taking the character from the iterator
			char key = englishAlphabetIterator.next();
			
			// Creating a map to store the row and its contents
			Map<Character, Character> temp = new HashMap<>();

			// Iterating for each character in the English alphabet
			int i = 0;
			for (char innerKey : english_alphabet) {

				// the number of shift 
				int shiftNumber = i + be ;
				
		
				if (25 < shiftNumber ) {
					shiftNumber = shiftNumber % 26;
				}
			
				
				// According the shift number get the character and stored in valeu
				char value = (char) ('A' + shiftNumber);
				
				//Putting the key and value to Inner map
				temp.put(innerKey, value);
				i++;
			}
	
			// Putting temp to map  --  temp is inner map
			map.put(key, temp);
			
			be = be + 1 ;
			i = 0;
		}
		
	}

	public void print_map() {
		// do not edit this method
		System.out.println("*** Viegenere Cipher ***\n\n");
		System.out.println("    " + english_alphabet);
		System.out.print("    ------------------------------------------------------------------------------");
		for (Character k : map.keySet()) {
			System.out.print("\n" + k + " | ");
			System.out.print(map.get(k).values());
		}
		System.out.println("\n");

	}

	public Map get_map() {
		return map;	//Getter for map
	}
}