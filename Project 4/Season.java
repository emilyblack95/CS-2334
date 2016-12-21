import java.util.ArrayList;

/**
 *   Seasons class for the teams file.
 *   Project 4
 *   CS 2334 - Section 010
 *   4/20/15
 *
 */
public class Season {
	
	private Team myTeam;
	private String myName;
	private int myYear;
	private ArrayList<Person> myRoster;
	
	/**
	 * Main constructor
	 * @param team
	 * @param name
	 * @param year
	 * @param roster
	 */
	public Season(Team team, String name, int year, ArrayList<Person> roster){
		this.myTeam = team;
		this.myName = name;
		this.myYear = year;
		this.myRoster = roster;
	}
	
	/**
	 * Accessor.
	 * @return
	 */
	public int getSeasonYear(){
		return this.myYear;
	}
	
	/**
	 * Accessor.
	 * @return
	 */
	public ArrayList<Person> getSeasonRoster(){
		return this.myRoster;
	}
	
	/**
	 * Accessor.
	 * @return
	 */
	public String getSeasonName(){
		return this.myName;
	}
	
	/**
	 * Accessor.
	 * @return
	 */
	public String getTeamID(){
		return this.myTeam.getID();
	}
	
	/**
	 * Converts the int year to a string.
	 */
	public String toString(){
		return this.myYear + "";
	}

}
