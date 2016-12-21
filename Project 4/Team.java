import java.util.ArrayList;

/**
 * Project 4
 * CS 2334, section 010
 * 4/20/15
 * 
 * This class models a sports team. Each team has a name, a location (city, state), and a roster of players.
 */
public class Team implements Comparable<Team>
{
	private String teamID;
	private ArrayList<Season> seasonsList;
	private City teamCity;
	private State teamState; 
	
	/**
	 * Parameterized constructor - represents a Team with the given ID
	 * 
	 * @param 	ID			the three-letter ID of this team
	 */
	public Team(String ID, City city, State state){
		this.teamID = ID;
		this.teamCity = city;
		this.teamState = state;
	}
	
	/**
	 * Creates a new season to add to the team's list of seasons
	 * 
	 * @param 	name			the name of the team for that season
	 * @param 	year			the year that season existed
	 * @param	teamMembers		the array list of players (Person objects) for that season
	 */
	public void addSeason(String name, int year, ArrayList<Person> teamMembers){
		Season newSeason = new Season(this, name, year, teamMembers);
		this.seasonsList.add(newSeason);
		
	}
	
	/**
	 * Returns the ID of the team as a string.
	 * @return the team's ID
	 */
	
	public String getID(){
		return teamID;
	}
	
	public ArrayList<Season> getSeasons(){
		return this.seasonsList;
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
	 * Compares one team to another team alphabetically by team ID.
	 * @param 	other 	the team being compared to
	 * @return			0 if the team IDs are the same, a negative integer if this team's ID comes before the other 
	 * 					team's name alphabetically, and a positive integer if this team's ID comes after the other's 
	 */
	public int compareTo(Team other){
		String otherID = other.getID();
		int orderOfID = this.teamID.compareTo(otherID);
		return orderOfID;
	}
	
	/**
	 * gets available years from the teams2.csv file.
	 * @return
	 */
	public ArrayList<Integer> getAvailableYears(){
		ArrayList<Integer> noSeasonYears = new ArrayList<Integer>();
		for (int i = 1964; i < Date.getCurrentDate().getYear(); i++){
			noSeasonYears.add(i); //adds all years from 1964 to present to the list
		}
		noSeasonYears.trimToSize();
		for (int j = 0; j < this.getSeasons().size(); j++){
			int yesYear = this.getSeasons().get(j).getSeasonYear();
			
			for(int k = 0; k < this.getSeasons().size(); k++){
				if (yesYear == noSeasonYears.get(k)){ //if the team has a season that year
					noSeasonYears.remove(k); //removes the year from noSeasonYears if the team had a season that year
					k++; //increment twice to account for the size of the list decreasing
				}
			}
		}
		return noSeasonYears;
	}
}