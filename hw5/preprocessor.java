public class preprocessor {
	private String initial_string;
	private String preprocessed_string;

	/**
	 * preprocessor constructor to initialize the initial_string and preprocessed_string variables
	 * @param str : String, the string to be preprocessed
	 */
	public preprocessor(String str) {
		initial_string = str;
		preprocessed_string = "";
	}
    /**
	 * preprocess method to call the capitalize and clean methods
	 */
	public void preprocess() {
		// do not edit this method
		capitalize();
		clean();
	}
	/**
	 * capitalize method to capitalize the initial_string
	 * then assign it to the preprocessed_string variable

	 */
	private void capitalize() {
		preprocessed_string = initial_string.toUpperCase();
	}

	/**
	 * clean method to clean the preprocessed_string from non-alphabetic characters
	 * and assign the cleaned string to the preprocessed_string variable
	 */
	private void clean() {
		boolean atleastOneValid = false;
		String cleanString ="";
		for(Character c : preprocessed_string.toCharArray()){
			if(c < 'A' || c > 'Z'){

				continue;
			}else if(c=='İ'){
				c = 'I';
				cleanString += c;
				atleastOneValid = true;
			}
			else{
				atleastOneValid = true;
				cleanString += c;
			}
		}
		if(atleastOneValid){
			preprocessed_string = cleanString;
		}else{
			System.err.println("Given input is not proper. Please try again.");
			System.exit(-1);
		}
	}

	/**
	 * get_preprocessed_string a getter method to return the preprocessed_string
	 * @return preprocessed_string : String, the preprocessed string
	 */
	public String get_preprocessed_string() {
		return preprocessed_string;
	}
}