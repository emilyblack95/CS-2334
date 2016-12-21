import java.util.ArrayList;

/**
 * Project 3
 * CS 2334, section 010
 * 2/26/15
 * 
 * This class models a sports team. Each team has a name, a location (city, state), and a roster of players.
 */
public class Team implements Comparable<Team>
{
	private ArrayList<Person> roster;
	private String teamName;
	private City teamCity;
	private State teamState;
	
	/**
	 * Default constructor - defaults to null/empty values when no parameters are specified.
	 */
	public Team (){
		roster = null;
		teamName = "";
		teamCity = null;
		teamState = null;
		
	}
	
	/**
	 * Parameterized constructor - represents a Team with the given information
	 * 
	 * @param 	name			the name of the team
	 * @param	city			the City object where the team is located
	 * @param	state			the State object where the team is located
	 * @param	teamMembers		the list of members on the team as Person objects in an ArrayList
	 */
	public Team(String name, City city, State state, ArrayList<Person> teamMembers){
		teamName = name;
		teamCity = city; 
		teamState = state;
		roster = teamMembers;
	}
	
	/**
	 * Returns the name of the team as a string.
	 * @return the team's name
	 */
	
	public String getName(){
		return teamName;
	}
	
	/**
	 * Returns the location of the team as a string in the format "City, ST".
	 * @return	string representation of the location
	 */
	public String getLocation(){
		return teamCity.toString() + " " + teamState.toString();
	}
	
	/**
	 * Returns the city of the team as a City object.
	 * @return	City where team is located
	 */
	public City getCity(){
		return teamCity;
	}
	
	/**
	 * Returns the state of the team as a State object.
	 * @return	State where team is located
	 */
	public State getState(){
		return teamState;
	}
	
	/**
	 * Returns the team roster as an ArrayList of Person objects.
	 * @return	list of people on the team
	 */
	public ArrayList<Person> getRoster(){
		return roster;
	}
	
	/**
	 * Returns the team as a String in the format teamName, city, ST, roster.
	 * @return	team as a String
	 */
	public String toString(){
		return teamName + "/n " + teamCity.toString() + " " + teamState.toString() + " /n" + roster.toString();
	}
	
	/**
	 * Compares one team to another team alphabetically by team name.
	 * @param 	other 	the team being compared to
	 * @return			0 if the team names are the same, a negative integer if this team's name comes before the other 
	 * 					team's name alphabetically, and a positive integer if this team's name comes after the other's 
	 */
	public int compareTo(Team other){
		String otherName = other.getName();
		int nameOrder = teamName.compareTo(otherName);
		return nameOrder;
	}

}
