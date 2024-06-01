import java.util.Map;

public class encryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";
	
	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		//Do initialization for map,key and text
		this.map = _map;	
		this.key = _key;
		this.plain_text = text;
	}
	
	public void encrypt() {
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}
	
	private void generate_keystream() {

		//Taking the lenghts for key and plaintext
		int keySize = key.length();
		int plainTextSize = plain_text.length();

		//If the length are same, then the key directly assign to keystream
		if(keySize == plainTextSize)
		{
			this.keystream = key;
		}
		else //If the length are not same, then run loop to generate keystream
		{
			int i=0;
			while( i < plainTextSize)	//until reach the length of plaintext the loop will run (keystream and plain_text's lengths should be same)
			{
				int index = i % keySize;		//do modulo
				char ch = key.charAt(index);	//Taking character from the key
				this.keystream += ch;			//and then the character append to the keystream
				i++;
			}
		}
		


	}
	
	private void generate_cipher_text() {
		//Taking the lenght of key 
		int size = keystream.length();
		int i=0;
		while(i < size) //until reach the length of key  (this function also work like generate_keystream method)
		{
			//Taking the character in keystream and get according to it from map, then store in temp
			Map<Character, Character> temp = map.get(keystream.charAt(i));
            char ch = temp.get(plain_text.charAt(i));	//Do like above
			this.cipher_text += ch;	//Found character is appended to cipher_text
			i++;
		}
	}

	 public String get_keystream() {
		return keystream;	//Getter for keystream
	 }
	
	 public String get_cipher_text() {
		return cipher_text;	//Getter for cipher_text
	 }
}
