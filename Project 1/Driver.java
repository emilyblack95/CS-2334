import java.util.ArrayList;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 * @author Emily Black CS 2334
 * @version 1.0
 */
public class Driver 
{
	
	private static Component frame;


	/** This STATIC method fills the ArrayList with data. The data is filled in order by the Person constructor.
	 * @param String fileName 
	 * @throws IOException 
	 */
	public static ArrayList<Person> fillArray(String fileName) throws IOException 
	{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<Person> listOfData = new ArrayList<Person>();
		String[] splitData;
		String word = br.readLine();
		while(word != null)  //while there are lines to be read 
		{
			String singlePerson = word;  //stores all data into string
			splitData = singlePerson.split(","); //string of data goes into array, splits with ","
			if(splitData.length == 4) //if they dont have date of death
			{
				try
				{
				Date DOB = Date.toDate(splitData[1]);
				int age = Person.calculateCurrentAge(DOB);
				Person random1 = new Person(splitData[0], DOB, splitData[2], splitData[3], age);
				listOfData.add(random1);
				} catch(Exception e)
				{
					System.out.println("ERRORS FOUND IN CSV FILE: " + e);
				}
			}
			else if(splitData.length == 5)  //if they have date of death
			{
				try
				{
				Date DOB = Date.toDate(splitData[1]);
				Date DOD = Date.toDate(splitData[4]);
				int age = Person.calculateDeathAge(DOB, DOD);
				Person random2 = new Person(splitData[0], DOB, splitData[2], splitData[3], DOD, age);
				listOfData.add(random2);
				} catch(Exception e)
				{
					System.out.println("ERRORS FOUND IN CSV FILE: " + e);
				}
			}
			word = br.readLine();
		}
		Person.getNumberOfErrors();
		br.close();
		return listOfData;
	}
	
	/** This STATIC method searches for a specific person in the ArrayList. The user SEARCHES BY NAME.
	 * @param ArrayList<Person> listOfData
	 */
	public static ArrayList<Person> searchByName(ArrayList<Person> listOfData, Scanner input)
	{
		ArrayList<Person> peopleFound = new ArrayList<Person>();
		
		String s = (String)JOptionPane.showInputDialog(frame, "Enter a name to search for!", "QUESTION", JOptionPane.PLAIN_MESSAGE, null, null, null);
		for(int i = 0; i < listOfData.size(); i++)  //goes through the arraylist
		{
			String name = listOfData.get(i).getFullName();
			if(name.equalsIgnoreCase(s) || name.contains(s))  //if target equals name of specified index
			{
				peopleFound.add(listOfData.get(i));  //add data at specified index to peopleFound arraylist
			}
		}
		return peopleFound;
	}
	
	
	/** The main method is the king of method callers (it will call methods that call methods).
	 * @param String[] args
	 */
	public static void main(String[] args) throws IOException
	{
		Scanner input = new Scanner(System.in);
		boolean keepRunning = true;
		String fileName = "people.csv";
		ArrayList<Person> listOfData = Driver.fillArray(fileName);
		ArrayList<Person> foundPeople;
		while(keepRunning == true)
		{
			
			foundPeople = Driver.searchByName(listOfData, input);
			JOptionPane.showMessageDialog(frame, foundPeople);
			Object[] options = {"YES", "EXIT"};
			int n = JOptionPane.showOptionDialog(frame, "Would you like to search again?", "QUESTION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
			null, options, options[0]); 
			if(n == 1)
			{
				keepRunning = false;
			}
			else if(n == 0)
			{
				keepRunning = true;
			}
		}
		input.close();

	}

}
