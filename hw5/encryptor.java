import java.util.Map;

public class encryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";

	/**
	 * Constructor for encryptor class which will initialize the map, key and text variables of the class
	 * @param _map: map that is generated in alphabet class
	 * @param _key: key that is read from the user
	 * @param text: text that is read from the user
	 */
	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		map = _map;
		key = _key;
		plain_text = text;
	}
	/**
	 * ecnrpyt method which makes calles to generate_keystream and generate_cipher_text methods
	 */
	public void encrypt() {
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}

	/**
	 * generate_keystream method which generates the keystream based on the key and plain_text
	 * first we compare the length of the key and plain_text
	 * if the length of the plain_text is greater than the key, we will repeat the key until the length of the key is equal to the length of the plain_text
	 * else if the length of the plain_text is less than the key, we will take the first n characters of the key where n is the length of the plain_text
	 * else if the length of the plain_text is equal to the key, we will take the key as it is
	 * this means that the length of the key and plain_text will be equal everytime
	 */
	private void generate_keystream() {
		if(plain_text.length() > key.length())
		{
			int step = 0;
			for(Character c : plain_text.toCharArray()){
				if(step == key.length()){
					step = 0;
				}

				keystream += key.charAt(step);
				step++;
			}
		} else if (plain_text.length() < key.length()) {
			int step = 0;
			for(Character c : plain_text.toCharArray()){
				keystream += key.charAt(step);
				step++;
			}
		}
		else{
			keystream = key;
		}

	}
	/**
	 * generate_cipher_text method which generates the cipher_text based on the keystream and plain_text
	 * for each character in keystream and plaintext we will look for the corresponding character in the map
	 * using the plain_text character as the key of outermap(in the table a row) and the keystream character as the key of innermap(in the table a column)
	 * then we add the corresponding character to the cipher_text
	 */
	private void generate_cipher_text() {
		for ( int i = 0 ; i < keystream.length(); i++){
			Character keyChar = keystream.charAt(i);
			Character plainChar = plain_text.charAt(i);
			Character cipherChar = map.get(plainChar).get(keyChar);
			cipher_text += cipherChar;
		}

	}
	/**
	 * get_keystream is a getter method to return keystream
	 * @return String keystream: the keystream generated in the generate_keystream method
	 */
	public String get_keystream() {
		return keystream;
	}
	/**
	 * get_cipher_text  is a getter method to return cipher_text
	 * @return String cipher_text: the cipher_text generated in the generate_cipher_text method
	 */
	public String get_cipher_text() {
		return cipher_text;
	}
}
