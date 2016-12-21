import java.awt.Component;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

/**
 * **
 * Project 5
 * CS 2334 - Section 010
 * 4/29/15
 *
 * PLEASE READ THE TODOS
 * Stuff that is preventing me from finishing this project:
 * 1) listOfTeams isnt filling properly.
 * 2) Buttons under people isn't even showing in JFrame.
 * 3) SelectionView doesn't even show (due to buttons/duplicate frames).
 * 4) Broken code is very broken. We weren't given example code whatsoever.
 */
public class MVCDriver
{
	
	static TeamMateModel theModel = new TeamMateModel();
	static SelectionView theSelectionView;
	static TeamMateController theController = new TeamMateController();
	static ArrayList<Person> listOfPeople = new ArrayList<Person>();
	static ArrayList<Team> listOfTeams = new ArrayList<Team>();
	static ArrayList<Season> listOfSeasons = new ArrayList<Season>();
	static ArrayList<Person> x = new ArrayList<Person>();
	static Person playerOne;
	static Person playerTwo;
	//static AddSeasonView theAddSeasonView = new AddSeasonView();
	//static SelectStateView theSelectStateView = new SelectStateView();
	//static DeleteView theDeleteView = new DeleteView();
	//static EditView theEditView = new EditView();
	//static SelectCityView theSelectCityView = new SelectCityView();
	//static AddView theAddView = new AddView();
	
	public static Stack<Person> runDegreesOfSep() throws IOException
	{
		TeamMateModel.fillArrayList("players0.csv");  //fills array
		TeamMateModel.fillTeamMap("teams2.csv");  //fills array
		listOfPeople = TeamMateModel.getPeople(); 
		listOfTeams = TeamMateModel.getTeams(); //TODO: ISNT FILLING CORRECTLY
		Stack<Person> results = null;
		
		for(int k = 0; k < listOfPeople.size(); k++)  //go thru list of teams
		{
			Person.addNeighbor(listOfPeople.get(k));  //TODO: READ: had to add all the people to neighbors list because 
													  // something with reading in the file was messed up.
		}
		
		Scanner reader = new Scanner(System.in);
		Component frame = null;
		String fullName1 = (String)JOptionPane.showInputDialog(frame, "Please enter the FULL name of the first player",
        "DEGREES OF SEPARATION", JOptionPane.PLAIN_MESSAGE, null, null, null);
		for(int i = 0; i < listOfPeople.size(); i++)
		{
			if(listOfPeople.get(i).getFullName().toString().equalsIgnoreCase(fullName1))
			{
				playerOne = new Person(listOfPeople.get(i).getFullName(), listOfPeople.get(i).getDOB(), listOfPeople.get(i).getCity(), listOfPeople.get(i).getState());
				System.out.println("PLAYER ONE VERIFIED");
			}
		}
		
		String fullName2 = (String)JOptionPane.showInputDialog(frame, "Please enter the FULL name of the second player",
		"DEGREES OF SEPARATION", JOptionPane.PLAIN_MESSAGE, null, null, null);
		for(int j = 0; j < listOfPeople.size(); j++)
		{
			if(listOfPeople.get(j).getFullName().toString().equalsIgnoreCase(fullName2))
			{
				playerTwo = new Person(listOfPeople.get(j).getFullName(), listOfPeople.get(j).getDOB(), listOfPeople.get(j).getCity(), listOfPeople.get(j).getState());
				System.out.println("PLAYER TWO VERIFIED");
			}
		}
		
		results = Person.shortestPath(playerOne, playerTwo);
		reader.close();
		return results;
	}

	/**
	 * Main method. Sets up MVC.
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{

		theSelectionView = new SelectionView(theModel);
		theSelectionView.setVisible(true);
		theController.setModel(theModel);
		theController.setSelectionView(theSelectionView);
		listOfTeams = TeamMateModel.getTeams();
		
		Stack<Person> results = runDegreesOfSep();  //IOEXCEPTION DUE TO METHOD.
		System.out.println(results);
		/** TODO:
		 * I got the results I did because I set everyone as a neighbor to everyone. Thus, the shortest path
		 * would be 1. This method proves that my algorithm works. I can also test entering the same player
		 * twice and get good code back for the base case. Rather than that, I can't test any other case because
		 * my listOfTeams isnt properly filling.
		 */
		
	}
}
