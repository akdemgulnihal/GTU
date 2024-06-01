public class preprocessor {
	private String initial_string;
	private String preprocessed_string;
		
	public preprocessor(String str) {
		this.initial_string = str; //String from the user entry is assign to inital_string (initialization)
	}

	public void preprocess() {
		// do not edit this method
		capitalize();
		clean();
	}
	
	private void capitalize() {

		String str = "";	//str variable
		//Iterating the whole initial_string
		for (int i = 0; i < initial_string.length(); i++) {
			//Taking the character
			char ch = initial_string.charAt(i);		
			//If this charter is not 'ı'
			if (ch != 'ı') {
				str += ch;	//Then appending the character to str string
			}
		}
		//When dont do above ,lower 'ı' convert like I , but it's problem ,that's why above loop is used for this probelm"	

		this.preprocessed_string = str.toUpperCase(); //str is changed to upper case version 
																 //and this updated value assign to preprocessed_string
	}

	private void clean() {
		this.preprocessed_string  = preprocessed_string.replaceAll("[^A-Z]", ""); //If the preprocessed_string does any charactter except A-Z
																				  //Then replace with "" 
	
	}
	
	public String get_preprocessed_string() {
		return preprocessed_string;	//Getter for preprocessed_string
	}
}