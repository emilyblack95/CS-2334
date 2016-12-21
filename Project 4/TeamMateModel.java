import java.awt.event.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * Project 4
 * CS 2334 - Section 010
 * 4/20/15
 *
 */

public class TeamMateModel implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -9166075574485841428L;
    
    /*
    private Team teamModel;
    private City cityModel;
    private State stateModel;
    private Date dateModel;
    private Person personModel;
    private Name nameModel;
    */
    
    private ArrayList<Team> listOfTeams;
    private ArrayList<City> listOfCities;
    private ArrayList<State> states = new ArrayList<State>();
    private ArrayList<Person> listOfPlayers;
    private LinkedHashMap<String, Team> teamMap;
    private ArrayList<ActionListener> actionListenerList = new ArrayList<ActionListener>();
    
    /**
     * Main Constructor.
     */
    TeamMateModel(){
        listOfTeams = new ArrayList<Team>();
        listOfCities = new ArrayList<City>();
        listOfPlayers = new ArrayList<Person>();
        teamMap = new LinkedHashMap<String, Team>();
    }
    
    /**
     * Secondary Constructor.
     * @param listOfTeams
     * @param listOfCities
     * @param listOfPlayers
     */
    TeamMateModel(ArrayList<Team> listOfTeams, ArrayList<City> listOfCities, ArrayList<Person> listOfPlayers){
        this.listOfTeams = listOfTeams;
        this.listOfCities = listOfCities;
        this.listOfPlayers = listOfPlayers;
    }
    
    /**
     * Adds team to arraylist listofteams.
     * @param team
     */
    public void addTeam(Team team){
    	this.listOfTeams.add(team);
    	this.teamMap.put(team.getID(), team);
    }
    /**
     * Adds person to arraylist listofplayers.
     * @param person
     */
    public void addPerson(Person person){
    	this.listOfPlayers.add(person);
    }
    /**
     * Adds place to arraylist listofcities.
     * @param cityName
     * @param state
     */
    public void addPlace(String cityName, State state){
    	this.listOfCities.add(new City(cityName, state));
    }
    
    /**
     * getters and setters
     * @return
     */
    public ArrayList<Team> getTeams(){
        return listOfTeams;
    }
    
    public ArrayList<City> getCities(){
        return listOfCities;
    }
    
    public ArrayList<State> getStates(){
        return states;
    }
    
    public ArrayList<Person> getPeople(){
        return listOfPlayers;
    }
     
    public void setTeams(ArrayList<Team> teams){
        this.listOfTeams = teams;
        processEvent( new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Team"));
    }
    
    public void setCities(ArrayList<City> cities){
        this.listOfCities = cities;
        processEvent( new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add City"));
    }
    
    public void setStates(ArrayList<State> states){
        this.states = states;
        processEvent( new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "states entered"));
    }

    
    public void setPeople(ArrayList<Person> people){
        this.listOfPlayers = people;
        processEvent( new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add Person"));
    }
    
    
    /** Register an action event listener */
    public synchronized void addActionListener(ActionListener l) {
        if (actionListenerList == null)
            actionListenerList = new ArrayList<ActionListener>();

        actionListenerList.add(l);
    }

    /** Remove an action event listener */
    public synchronized void removeActionListener(ActionListener l) {
        if (actionListenerList != null && actionListenerList.contains(l))
            actionListenerList.remove(l);
    }

    /** Fire TickEvent */
    private void processEvent(ActionEvent e) {
        ArrayList<ActionListener> list;

        synchronized (this) {
            if (actionListenerList == null) return;
            list = (ArrayList<ActionListener>)actionListenerList.clone();
        }

        for (int i = 0; i < list.size(); i++) {
            ActionListener listener = list.get(i);
            listener.actionPerformed(e);
        }
    }
    
    /**
     * Takes data from the players file and puts it into an ArrayList of type Person
     * @param fileName            name of file entered by user
     * @return listOfPlayers    ArrayList of Person objects
     * @throws IOException 
     */
    public void fillArrayList(String fileName) throws IOException
    {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String[] splitData;
        String word = br.readLine();
        while(word != null)  //while there are lines to be read 
        {
            String singlePerson = word;  //stores all data into string
            splitData = singlePerson.split(", "); //string of data goes into array, splits with ", "
            if(splitData.length == 4 || splitData.length == 5) 
            {
                Name personsName;
                String[] splitName = splitData[0].split(" ");  //Splitting up the person's name by a space
                if(splitName.length == 2)
                {
                    personsName = new Name(splitName[0], splitName[1]);
                }
                else if(splitName.length == 3)  //If they have 1 middle name
                {
                    personsName = new Name(splitName[0], splitName[1], splitName[2]);
                }
                else if(splitName.length == 4)  //If they have 2 middle names
                {
                    personsName = new Name(splitName[0], splitName[1], splitName[2], splitName[3]);
                }
                else if(splitName.length == 5)  //If they have 3 middle names
                {
                    personsName = new Name(splitName[0], splitName[1], splitName[2], splitName[3], splitName[4]);
                }
                else
                {
                    System.out.println("Person's name is null");
                    personsName = null;
                }
                if(personsName != null)
                {
                    int latitude = 0;
                    int longitude = 0;
                    
                    double doubleLat = 0;
                    double doubleLon = 0;
                    
                    State state = new State(splitData[3]);  //creates State object
                    City city = new City(splitData[2], state);  //creates City object
                    for(int i = 0; i < listOfCities.size(); i++){
                        if(listOfCities.get(i).equals(city))
                        {
                            latitude = listOfCities.get(i).getLatitude();
                            longitude = listOfCities.get(i).getLongitude();
                            
                            doubleLat = listOfCities.get(i).getDoubleLat();
                            doubleLon = listOfCities.get(i).getDoubleLon();
                        }
                    }
                    city.setLatitude(latitude);
                    city.setLongitude(longitude);
                    
                    city.setDoubleLat(doubleLat);
                    city.setDoubleLon(doubleLon);
                    
                    Date DOB = Date.stringToDate(splitData[1]);
                    Person random1 = new Person(personsName, DOB, city, state);
                    listOfPlayers.add(random1);
                }
            }
            word = br.readLine();
        }
        br.close();
        
        processEvent( new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "people entered"));
    }
    
    /**
     * Takes data from the teams file and puts it into a LinkedHashMap of type Team with String keys (team IDs)
     * @param fileName        file name chosen by the user
     * @return teamMap        LinkedHashMap of all the Team objects from the file
     * @throws IOException 
     */
    public void fillTeamMap(String fileName) throws IOException
    {
        teamMap = new LinkedHashMap<String, Team>();
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String[] splitTeamData;
        String word = br.readLine();
        String currentID = ""; 

        Team currentTeam = null; //the current team based on what the current ID is
        while(word != null)  //while there are lines to be read 
        {
        	String line = word; //stores all data into string

        	String[] splitID = line.split(":"); //string of data goes into array, splits with ":"
        	if (splitID.length == 1) //the line was a team's ID

        		currentID = splitID[0]; //stores the most recent team ID given in the file

        	else if (splitID.length == 0) //the line was a set of season data
        	{

        		splitTeamData = line.split("; "); //string of season data goes into array, splits with "; "
        		String teamYear = splitTeamData[0]; //assigns first data entry as season year
        		String teamName = splitTeamData[1]; //assigns second data entry as team name
        		String teamCityName = splitTeamData[2]; //assigns third data entry as name of team's city

        		String teamStateName = splitTeamData[3]; //assigns fourth data entry as abbreviation of team's state

        		int seasonYear = Integer.parseInt(teamYear); 
        		State teamState = new State(teamStateName);
        		City teamCity = new City(teamCityName, teamState);
        		ArrayList<Person> seasonRoster = new ArrayList<Person>();

        		String player = "";

        		for (int i=4; i < splitTeamData.length; i++) //runs through the list of data that will be player names
        		{

        			player = splitTeamData[i];
        			Person thisPlayer = null; 
        			int j = 0;

        			while (j < listOfPlayers.size()) //goes through list of players to find person with this name
        			{
                        if (listOfPlayers.get(j).getFullName().toString().equalsIgnoreCase(player))
                            thisPlayer = listOfPlayers.get(j);
                        j++;
        			}
        			if (thisPlayer != null && thisPlayer.getFullName() != null)
        			{ 
        				seasonRoster.add(thisPlayer);
        			}
        			player = "";
        		}

        		boolean teamAdded = false;
        		for (int j=0; j < listOfTeams.size(); j++){
        			if (listOfTeams.get(j).getID().equals(currentID)) //checks current list of teams 

        				teamAdded = true; //if this team has been added yet, it will be on the list
        		}

        		if (teamAdded == false){
        			currentTeam = new Team(currentID, teamCity, teamState); //adds the team to the list only if it's not on there yet
        			listOfTeams.add(currentTeam);
        		}
        		currentTeam.addSeason(teamName, seasonYear, seasonRoster);
        	}
        	word = br.readLine();
        }
        br.close();
        listOfTeams.trimToSize();
        Collections.sort(listOfTeams); //sorts the list of teams alphabetically

        for(int i=0; i < listOfTeams.size(); i++){  //adds each team to the LinkedHashMap
        	teamMap.put(listOfTeams.get(i).getID(), listOfTeams.get(i));
        }
        processEvent( new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "teams entered"));
    }


    /**
     * Takes data from the cities file and puts it into an ArrayList of type City
     * @param fileName            file name chosen by the user    
     * @return listOfCities        ArrayList of all City objects from the file
     * @throws IOException 
     */
    public void fillCityArrayList(String fileName) throws IOException
    {
    	FileReader fr = new FileReader(fileName);
    	BufferedReader br = new BufferedReader(fr);
    	String[] splitData;
    	String word = br.readLine();
    	while(word != null)  //while there are lines to be read 
    	{
    		String singleCity = word;  //stores all data into string
    		splitData = singleCity.split("; "); //string of data goes into array, splits with "; "
    		if (splitData.length == 4)
    		{
    			String cityName = splitData[0]; //assigns first data entry as city name
    			String stateName = splitData[1]; //assigns second data entry as state name
    			String cityLat = splitData[2]; //assigns third data entry as latitude
    			double lat = Double.parseDouble(cityLat); //converts latitude from String to double
    			String cityLon = splitData[3]; //assigns fourth data entry as longitude
    			double lon = Double.parseDouble(cityLon); 

    			City newCity = new City(cityName, new State(stateName), lat, lon);
    			listOfCities.add(newCity); 
    		}
    		word = br.readLine();
    	}
    	br.close();
    	listOfCities.trimToSize();

    	processEvent( new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add City"));
    }

    /**
     * If the user wants to write out the last thing in Console to a file, you call this method.
     * @param lastQweryLine2
     * @throws IOException 
     */
    public void writeOut(ArrayList<Object> lastQweryLine1, String fileNameUserChooses) throws IOException
    {
    	FileOutputStream x = new FileOutputStream(fileNameUserChooses);
    	ObjectOutputStream y = new ObjectOutputStream(x);
    	if(lastQweryLine1 != null)   //if last qwery line has content of type person, city, or team
    	{
    		y.writeObject(lastQweryLine1);
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Error writing out file.", "Save TeamMate Data", JOptionPane.WARNING_MESSAGE); 
    	}
    	y.close();

    	processEvent( new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "wrote to file"));
    }

    /**
     * Call this method to display a plate carree map of the players on a given team
     * @param lastQweryLine1
     * @param foundTeam
     */
    public void displayMap(ArrayList<Person> lastQweryLine1, SelectionView selectionView){
    	ArrayList<MapCoord> points = new ArrayList<MapCoord>();
    	HashMap<String,MapCoord> teams = new HashMap<String,MapCoord>();
    	HashMap<String,ArrayList<MapCoord>> memberLocs = new HashMap<String,ArrayList<MapCoord>>();

    	for(int j = 0; j < lastQweryLine1.size(); j++)  //creates a MapPoint object for each Player's city/lat/lon
    	{
    		Person p = lastQweryLine1.get(j);
    		String name = p.getFullName().toString();
    		//String teamName = foundTeam.getName().toString();
    		//int n = selectionView.getTeamList().size();

    		String teamID = selectionView.getTeamList().getSelectedValue().getID();

    		double x = p.getCity().getDoubleLat();
    		double y = p.getCity().getDoubleLon();

    		System.out.println("latitude: " + x);
    		System.out.println("longitude: " + y);

    		MapCoord calculatedCoord = new MapCoord(name, x, y);
    		points.add(calculatedCoord);       //AFTER EXITING THIS FOR LOOP, WE HAVE ALL MAP COORDS
    		teams.put(teamID, calculatedCoord);
    		memberLocs.put(teamID, points);
    	}
    	new Map(points, teams, memberLocs);

    	processEvent( new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Displayed Map"));
    }

}