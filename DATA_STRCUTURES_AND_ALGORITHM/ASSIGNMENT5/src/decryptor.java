import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;
	
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		//Do initializations
		this.map = _map;
		this.key = _key;
		this.cipher_text = text; 
	}

	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}
	
	private void generate_keystream() {
		//Finding the lengths of key and ciphertext
		int keySize = key.length();
		int cipherTextSize = cipher_text.length();

		if(keySize == cipherTextSize) //If key length and ciphertext length are same
		{
			this.keystream = key;	//Key directly assignt to keystream
		}
		else 
		{
			int i=0;
			while( i < cipherTextSize)	//Until reach the length of cipher text
			{
				int index = i % keySize;	//Finding the remainder according to keySize and assigning the value to index
				char ch = key.charAt(index);	//Then finding the character according to index
				this.keystream += ch;			//Found character is appended to keystream
				i++;
			}
		}
	}
	
	private void generate_plain_text() {
		// You must use map.get(x).keySet() with an iterator in this method

		char ctCh,ksCh;
		int i=0;
		while (i < keystream.length()) 
		{
			//Taking the keystream character
			ksCh = keystream.charAt(i);
			//System.out.println(ksCh);

		    //Taking the cipher_text character
			ctCh = cipher_text.charAt(i);
			//System.out.println(ctCh);
			
			char ch = ' '; // Initialization

			//Using map.get(x).keySet() with an iterator 
			Iterator<Character> iteratorForMap = map.get(ksCh).keySet().iterator();

			
			while (iteratorForMap.hasNext()) {
				char key = iteratorForMap.next();		//Taking the next one
				if (map.get(ksCh).get(key) == ctCh) {	//Map<Character, Map<Character, Character>> --> If the outer map key value i same as cipher_text character
					ch = key;						//When condition is occured, then key assign to ch			
					break;		//When they are matched, then the loop is finished 
				}
       		 }
        
			// Appending the finding letter to plain_text
			plain_text += ch;
			i++;
    	}	
	}

	public String get_keystream() {
		return keystream;	//Getter for keystream
	}
	
	public String get_plain_text() {
		return plain_text;	//Getter for plain_text
	}
}
