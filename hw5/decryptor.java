import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;
	/**
	 * Constructor for decryptor class which initializes the map, key and text members of the decryptor class.
	 * @param _map: Map<Character, Map<Character, Character>> - map
	 * @param _key: String - key
	 * @param text: String - cipher text
	 */
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		map = _map;
		key = _key;
		cipher_text = text;
	}

	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}

	/**
	 * method to create a keystream from the key and the cipher text
	 * keystream string will always be the same length as the cipher text
	 */
	private void generate_keystream() {
		if(key.length() < cipher_text.length()){
			int step = 0 ;
			for(Character x : cipher_text.toCharArray()){
				if(step == key.length()){
					step = 0;
				}
				keystream += key.charAt(step);
				step++;
			}
		}else if(key.length() > cipher_text.length()){
			int step = 0;
			for(Character x : cipher_text.toCharArray()){
				keystream += key.charAt(step);
				step++;
			}
		}
		else{
			keystream = key;
		}
	}

	/**
	 * method to create a plain text from the keystream and the cipher text
	 * plain text string will always be the same length as the cipher text
	 * first we traverse the caharacters of the keystream and for each character we find the corresponding inner map in the outer map
	 * then we traverse the inner map and find the character that corresponds to the cipher text character
	 * we add this character to the plain text
	 *
	 */
	private void generate_plain_text() {
		// You must use map.get(x).keySet() with an iterator in this method
		int index = 0;
		for(Character X : keystream.toCharArray()){
			Map<Character, Character> cypherColumn = map.get(X);
			Iterator<Character> it = cypherColumn.keySet().iterator();
			while(it.hasNext()){
				Character key = it.next();
				if(cypherColumn.get(key).equals(cipher_text.charAt(index))){
					plain_text += key;
					break;
				}
			}

			index++;

		}

	}
	/**
	 * getter method for the cipher text
	 * @return String - cipher text
	 */
	public String get_keystream() {
		return keystream;
	}
	/**
	 * getter method for the plain text
	 * @return String - plain text
	 */
	public String get_plain_text() {
		return plain_text;
	}
}
