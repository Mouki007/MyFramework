package assignment;

public class Problem_1 {
	

	public static void convert(String str, int rows) {
		 String expected = "";
		if (rows > 1) {
			// Print for 1st Row characters, String starts with 2 and gap between the characters is 3
			for (int i = 0; i < str.length(); i = i + (rows - 1) * 2) {
                 expected+=str.charAt(i);
			}

			// Print for 2nd row, string starts with 1st letter and gap between letters are 1
			
			//we can simply use all the base number characters are printed 
			 for(int line=1; line<rows-1;line++) {
					 for (int j = line;  j< str.length(); ) {
							expected+=str.charAt(j); 
							 if(line%2!=0) {
								 j+=(rows-1) * 2 - (rows - line - 1) * 2;
				               }else {
				            	  
				            	   j+=(rows-line-1)*2; 
				            	   
				               }
				 }
			 }
			// Print for 3rd Row characters, String starts with (rows-1) and gap between the characters is 3
			for (int k = rows-1; k < str.length(); k = k + (rows - 1) * 2) {
				 expected+=str.charAt(k);
			}
			System.out.println(expected);
			
		}
	}

	public static void main(String[] args) {
		convert("PROKARMAISHIRING", 3);
		convert("PROKARMAISHIRING", 4);

	}

}
