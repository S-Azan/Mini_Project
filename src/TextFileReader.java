import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Azan S
 *
 */
public class TextFileReader {
		
	public static void readfile(File userfile) {
		//File userfile = new File("Users.txt");
		
		try {
		Scanner sc = null;
		sc = new Scanner(userfile);	
		
		List<String> lines = new ArrayList<String>();
		
		while(sc.hasNextLine()) {
			lines.add(sc.nextLine());
		
		
		String[] lineArr = lines.toArray(new String[0]);
		//String[] infoArr = lineArr.
		
		for(int u = 0; u < lineArr.length; u++) {
			System.out.println("Name: " + lineArr[u] + "\n");
		}

		
		//Old Working Code commented
//		while(sc.hasNextLine()) {
//			
//			List<String> firstName = new ArrayList<String>();
//			List<String> surName = new ArrayList<String>();
//			List<String> userName = new ArrayList<String>();
//			List<String> Age = new ArrayList<String>();
//			
//			String line = sc.nextLine();
//			String lineSec[] = line.split("\\s");
			
//			firstName.add(lineSec[0]);
//			surName.add(lineSec[1]);
//			userName.add(lineSec[2]);
//			Age.add(lineSec[3]);
//			System.out.println(firstName + " " + surName + " " + " " + userName + " " + Age);
			//System.out.println(firstName.remove(0));	//.remove(0) gets rid of [square brackets]
			
//			String userFirstName = lineSec[0];
//			String userSurname = lineSec[1];
//			String userUserName = lineSec[2];
//			String userAge = lineSec[3];		
//			System.out.println(userFirstName + " " + userSurname + " " + " " + userUserName + " " + userAge);
			
		}
		sc.close();
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
