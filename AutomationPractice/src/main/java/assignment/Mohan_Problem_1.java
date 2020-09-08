package assignment;

public class Mohan_Problem_1 {

	
	 public String convert(String s, int numRows) {
	     
	        if (numRows == 1) {
	            return s;
	        }
	        StringBuilder result = new StringBuilder();
	     
	        for (int i = 0; i < numRows; i++) {
	            int j = 0;
	            while (j + i < s.length()) {
	                result.append(s.charAt(j + i));
	                if (i != 0 && i != numRows - 1 && j + numRows * 2 - 2 - i < s.length())               {
	                    result.append(s.charAt(j + numRows * 2 - 2 - i));
	                }
	 
	                j += numRows * 2 - 2;
	            }
	        }
	        return result.toString();
	    }
	 
}
