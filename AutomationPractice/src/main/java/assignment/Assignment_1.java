package assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Assignment_1 {
	static void convertString(int rows, String name, List<String> list, List<Integer> considerRows) {

		if (considerRows.size() == 0) {

			for (int i = 0; i < rows; i++) {

				char ch = name.charAt(i);
				String token = list.get(i) + ch;
				list.set(i, token);
				if (i > 0 && i < rows - 1) {
					considerRows.add(i);
				}

			}
			Collections.reverse(considerRows);
			name = name.substring(rows);
		} else {
			for (int i = 0; i < considerRows.size(); i++) {
				int considerRowsIndex = considerRows.get(i);
				char ch = name.charAt(i);
				String token = list.get(considerRowsIndex) + ch;
				list.set(considerRowsIndex, token);

			}
			name = name.substring(considerRows.size());
			considerRows.clear();

		}

		if (name.length() > 0) {

			convertString(rows, name, list, considerRows);
		}

	}

	public static void main(String[] args) {
		int rows =3;
		String name = "PROKARMAISHIRING";
		List<String> list = new ArrayList<String>(rows);
		for (int i = 0; i < rows; i++) {
			list.add("");
			
		}
		convertString(rows, name, list, new ArrayList<Integer>());
		System.out.println(list);

	}

}
