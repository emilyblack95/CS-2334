import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;  
import java.util.LinkedHashMap;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Project 3
 * CS 2334 - Section 010
 * 2/26/15
 *
 * This class provides the interface with the user. The user can search for people, places, and team from the
 * text files they provide and view graphical representations of age and location of these people.
 * 
 */
public class Driver implements Serializable 
{
    
    private static final long serialVersionUID = -8426247695356071672L; 
    private static ArrayList<Person> lastQweryLine1 = new ArrayList<Person>();
    private static String lastQweryLine2;
    private static String fileNameUserChooses;
    private static LinkedHashMap<String, Team> teamMap; 
    private static String citiesFileName;
    private static String playersFileName;
    private static String teamsFileName;
    private static ArrayList<City> listOfCities = new ArrayList<City>();
    private static ArrayList<Person> listOfPlayers = new ArrayList<Person>();
    private static ArrayList<Team> listOfTeams = new ArrayList<Team>();


    
    /**
     * Takes data from the players file and puts it into an ArrayList of type Person
     * @param fileName			name of file entered by user
     * @return listOfPlayers	ArrayList of Person objects
     * @throws IOException 
     */
    public static ArrayList<Person> fillArrayList(String fileName) throws IOException
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
                	State state = new State(splitData[3]);  //creates State object
                	City city = new City(splitData[2], state);  //creates City object
                	for(int i = 0; i < listOfCities.size(); i++){
                		if(listOfCities.get(i).equals(city))
                		{
                			latitude = listOfCities.get(i).getLatitude();
                			longitude = listOfCities.get(i).getLongitude();
                		}
                	}
                	city.setLatitude(latitude);
                	city.setLongitude(longitude);
                    Date DOB = Date.stringToDate(splitData[1]);
                    Person random1 = new Person(personsName, DOB, city, state);
                    listOfPlayers.add(random1);
                }
            }
            word = br.readLine();
        }
        br.close();
        return listOfPlayers;
    }
    
    /**
     * Takes data from the teams file and puts it into a LinkedHashMap of type Team with String keys (team names)
     * @param fileName		file name chosen by the user
     * @return teamMap		LinkedHashMap of all the Team objects from the file
     * @throws IOException 
     */
    public static LinkedHashMap<String, Team> fillTeamMap(String fileName) throws IOException
    {
        teamMap = new LinkedHashMap<String, Team>();
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String[] splitData;
        String word = br.readLine();
        while(word != null)  //while there are lines to be read 
        {
            String singleTeam = word;  //stores all data into string
            splitData = singleTeam.split("; "); //string of data goes into array, splits with "; "
            String teamName = splitData[0]; //assigns first data entry as team name
            String teamCityName = splitData[1]; //assigns second data entry as name of team's city
            String teamStateName = splitData[2]; //assigns third data entry as abbreviation of team's state
            State teamState = new State(teamStateName);
            
            City teamCity = new  City();
            int k = 0;
            while (k < listOfCities.size()) //passes through list of cities to find the city object matching the Team's city
            {    
                if (listOfCities.get(k).toString().equalsIgnoreCase(teamCityName) == true && 
                        listOfCities.get(k).getState().toString().equalsIgnoreCase(teamStateName) == true)
                    teamCity = listOfCities.get(k);
                k++;
            }
                        
            ArrayList<String> splitRoster = new ArrayList<String>();  //creates an ArrayList for the names of people on the roster
            for (int m=3; m < splitData.length; m++){ //adds the names of each team member to the ArrayList
                splitRoster.add(splitData[m]);
            }
            String player;
            ArrayList<Person> teamRoster = new ArrayList<Person>(); //creates an ArrayList for the roster as Person objects
            for (int i=0; i < splitRoster.size(); i++)
            {
                player = splitRoster.get(i);
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
                    teamRoster.add(thisPlayer);
                }
                player = "";
            }
           
            Team newTeam = new Team(teamName, teamCity, teamState, teamRoster);
            listOfTeams.add(newTeam);
           
            word = br.readLine();

        }
        br.close();
        listOfTeams.trimToSize();
        Collections.sort(listOfTeams); //sorts the list of teams alphabetically
        
        for(int i=0; i < listOfTeams.size(); i++){  //adds each team to the LinkedHashMap
            teamMap.put(listOfTeams.get(i).getName(), listOfTeams.get(i));
        }
        return teamMap;
    }
    
    /**
     * Takes data from the cities file and puts it into an ArrayList of type City
     * @param fileName			file name chosen by the user	
     * @return listOfCities		ArrayList of all City objects from the file
     * @throws IOException 
     */
    public static ArrayList<City> fillCityArrayList(String fileName) throws IOException
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
        return listOfCities;
    }

    
    /**
     * If the user wants to write out the last thing in Console to a file, you call this method.
     * @param lastQweryLine2
     * @throws IOException 
     */
    public static void writeOut(ArrayList<Person> lastQweryLine1, String lastQweryLine2, String fileNameUserChooses) throws IOException
    {
        FileOutputStream x = new FileOutputStream(fileNameUserChooses);
        ObjectOutputStream y = new ObjectOutputStream(x);
        if(lastQweryLine1 != null)   //if last qwery line has content of type person
        {
            y.writeObject(lastQweryLine1);
            System.out.println("Wrote the file out with an ArrayList<Person>");
        }
        else if(lastQweryLine2 != null)  //if last qwery line has content of type strings
        {
            y.writeObject(lastQweryLine2);
            System.out.println("Wrote the file out with STRINGS!");
        }
        else
        {
            System.out.println("Error writing out file");
        }
        y.writeObject(lastQweryLine2);
        y.close();
    }
        
    /**
     * Main interface of program. (Method that asks all the questions). No return type.
     * @param args			program arguments	
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException 
    {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        boolean fileFound = false;
        boolean fileFound1 = false;
        boolean fileFound2 = false;
        
        while (fileFound == false)
        {
              System.out.println();
              System.out.println("Please enter the name of the file containing cities (Example: cities.txt): ");
               citiesFileName = inputReader.readLine();
               try
               {
                   listOfCities = Driver.fillCityArrayList(citiesFileName); 
                   listOfCities.trimToSize(); //removes excess blank space
                   fileFound = true;
               } catch (IOException FileNotFoundException) //catches bad input if the name of the file isn't found
               { 
                   System.out.println("File " + citiesFileName + " not found.");
                   fileFound = false;
               }
        }
        while (fileFound1 == false)
        {
            System.out.println("Please enter the name of the file containing players (Example: players.txt): ");
            playersFileName = inputReader.readLine();
            try
            {
                listOfPlayers = Driver.fillArrayList(playersFileName);
                listOfPlayers.trimToSize(); //removes excess blank space
                fileFound1 = true;
            } catch (IOException FileNotFoundException)  //catches bad input if the name of the file isn't found
            { 
                System.out.println("File " + playersFileName + " not found.");
                fileFound1 = false;
            }
        }
        while (fileFound2 == false)
        {
            System.out.println("Please enter the name of the file containing teams (Example: teams.txt): ");
            teamsFileName = inputReader.readLine();
            try
            {
                teamMap = Driver.fillTeamMap(teamsFileName); 
                fileFound2 = true;
            } catch (IOException FileNotFoundException)  //catches bad input if the name of the file isn't found
            { 
                System.out.println("File " + teamsFileName + " not found.");
                fileFound2 = false;
            }
        }
                    
            
        ArrayList<String> nameList = new ArrayList<String>(); 
        ArrayList<Person> newList = new ArrayList<Person>();
        ArrayList<Person> newList1 = new ArrayList<Person>();
        boolean keepRunning = true;
       
        while(keepRunning == true)
        {
                System.out.println("People, Place, or Team?");
                String answer = inputReader.readLine();
                if(answer.equalsIgnoreCase("people"))
                {
                    System.out.println("Sort or Search?");
                    String answer2 = inputReader.readLine();
                    if(answer2.equalsIgnoreCase("sort"))
                    {
                        System.out.println("First or Last?");
                        String answer3 = inputReader.readLine();
                        if(answer3.equalsIgnoreCase("first"))
                        {
                            Collections.sort(listOfPlayers);
                            for (int i = 0; i < listOfPlayers.size(); i++)
                                System.out.println(listOfPlayers.get(i).toString()); //prints each out on a new line
                            lastQweryLine1.addAll(listOfPlayers);
                        }
                        else if(answer3.equalsIgnoreCase("last"))
                        {
                            Collections.sort(listOfPlayers, new CompareLastName());
                            for (int i = 0; i < listOfPlayers.size(); i++)
                                System.out.println(listOfPlayers.get(i).toString()); //prints each out on a new line
                            lastQweryLine1.addAll(listOfPlayers);
                        }
                        else
                        {
                            System.out.println(answer3 + " is an unknown input!");
                        }
                    }
                    else if(answer2.equalsIgnoreCase("search"))
                    {
                        System.out.println("Exact or Partial?");
                        String answer3 = inputReader.readLine();
                        if(answer3.equalsIgnoreCase("exact"))
                        {
                            System.out.println("Enter an EXACT FULL name to search for:");
                            String answer4 = inputReader.readLine();
                            Person foundPerson = null;
                            Collections.sort(listOfPlayers); //sorts list of data using compareTo() method, by first name
                            for (int i=0; i < listOfPlayers.size(); i++)
                            {
                                nameList.add(listOfPlayers.get(i).getFullName().toString());   //creates an ArrayList of names of people so they can be searched for
                            }
                            nameList.trimToSize(); //removes excess blank space
                            
                            int index = Collections.binarySearch(nameList, answer4);
                            
                            if (index >= 0)
                                foundPerson = listOfPlayers.get(index);
                            
                            if(foundPerson != null)
                            {
                                System.out.println("Exact name match for \"" + answer4 + "\":");
                                System.out.println(foundPerson.toString());
                                lastQweryLine1.add(foundPerson);
                            }
                            else
                            {
                                System.out.println("No person with name \"" + answer4 + "\" found in file. Please try a different search.");
                                lastQweryLine2 = null;
                            }
                        }
                        else if(answer3.equalsIgnoreCase("partial"))
                        {
                            System.out.println("Enter a PARTIAL name to search for:");
                            String answer4 = inputReader.readLine();
                            for(int i = 0; i < listOfPlayers.size(); i++)
                            {
                                if((listOfPlayers.get(i).getFullName().toString().toLowerCase().contains(answer4.toLowerCase())))  //If name was found
                                {
                                    newList.add(listOfPlayers.get(i));  //add to new list
                                }
                            }
                            if(newList.size() == 0)
                            {
                                System.out.println("No people found with name " + answer4.toLowerCase() + ". Please try a different search."); 
                                lastQweryLine2 = null;
                            }
                            else if(newList.size() != 0)
                            {
                                System.out.println("Partial name match for \"" + answer4.toLowerCase() + "\": ");
                                for (int i=0; i < newList.size(); i++)
                                {
                                    System.out.println(newList.get(i).toString());
                                }
                               lastQweryLine1.addAll(newList); 
                            }
                        }
                        else
                        {
                            System.out.println(answer3 + " is an unknown input!");
                        }
                    }
                    else
                    {
                        System.out.println(answer2 + " is an unknown input!");
                    }
                }
                else if(answer.equalsIgnoreCase("place"))
                {
                    System.out.println("City or State?");
                    String answer2 = inputReader.readLine();
                    if(answer2.equalsIgnoreCase("city"))  //Asks user for state to search for, then city
                    {
                        System.out.println("Please enter the two-letter abbreviation of the state in which the city you are looking for exists:"); 
                        String answer3 = inputReader.readLine();
                        State state1 = new State(answer3); //creates state object based on user input
                        ArrayList<City> cityList = state1.fillCityList(listOfPlayers); //fills state's cityList with data from the file
                        Collections.sort(cityList); //sorts list alphabetically using State's compareTo method
                        
                        if(cityList.size() == 0)
                        {
                            System.out.println("No people found in state " + answer3 + ". Please try a different search."); 
                        }
                        System.out.println("Please enter the name of a city in state " + answer3 + " to search for: "); 
                        String answer4 = inputReader.readLine();
                        City city1 = new City(answer4, state1); //creates city object based on user input
                        ArrayList<Person> peopleInCity = city1.fillResidentList(listOfPlayers); //fills the city's resident list with data from the file
                        
                        Collections.sort(peopleInCity); //sorts city's resident list alphabetically based on Person's compareTo method
                        
                        if(peopleInCity.size() != 0)
                        {
                            System.out.println(state1.toString() + ":"); //prints "STATE:"
                            System.out.println(city1.toString() + ":"); //prints "CITY:"
                            for (int i=0; i < peopleInCity.size(); i++){
                                System.out.println(peopleInCity.get(i).toString()); //prints each Person in the list
                            } 
                            lastQweryLine1.addAll(peopleInCity);
                        }
                        else if(newList1.size() == 0)
                        {
                            System.out.println("No people found in " + answer4 + ", " + answer3 + ". Please try a different search."); 
                            lastQweryLine2 = null; 
                        }
                  
                        
                    }
                    else if(answer2.equalsIgnoreCase("state"))
                    {
                        System.out.println("Please enter the two-letter abbreviation of the state you'd like to search for:"); 
                        String answer3 = inputReader.readLine();
                        State state2 = new State(answer3);
                        state2.fillCityList(listOfPlayers); //fills city list with data from the file
                        ArrayList<City> listOfCities2 = state2.getCityList(); 
                        Collections.sort(listOfCities2); //sorts cities alphabetically
                        
                        if (state2.getCityList().size() == 0)
                        {
                            System.out.println("No cities found in state" + answer3 + ". Please try a different search."); 
                            lastQweryLine2 = null;
                        }
                        else if (state2.getCityList().size() != 0)
                        {
                            System.out.println(state2.toString() + ":");
                            for (int i = 0; i < listOfCities2.size(); i++)
                            {
                                System.out.println(listOfCities2.get(i).toString() + ":");
                                ArrayList<Person> resList = listOfCities2.get(i).fillResidentList(listOfPlayers);
                                for (int j = 0; j < resList.size(); j++)
                                {
                                    System.out.println(resList.get(j).toString());
                                }
                            }
                            lastQweryLine1.addAll(newList);
                        }
                    }
                }
                else if(answer.equalsIgnoreCase("team")) 
                {                   
                    System.out.println("What is the name of the team you are searching for?(case sensitive)");
                    String answerForTeam = inputReader.readLine(); 
                    
                    Team foundTeam = teamMap.get(answerForTeam);
                    if (foundTeam == null)
                    {
                        System.out.println("Team " + answerForTeam + " not found or is an unknown input!");
                        lastQweryLine2 = null;
                    }
                    else if(foundTeam != null)
                    {
                        ArrayList<Person> foundRoster = foundTeam.getRoster();
                        Collections.sort(foundRoster);
                        lastQweryLine1 = foundRoster;
                        for (int i=0; i < foundRoster.size(); i++) //prints the name of each person on the team's roster
                        {
                            System.out.println(foundRoster.get(i).getFullName());
                        }
                    }
                }
                
                System.out.println("Save or Skip?");
                String lastQuestion1 = inputReader.readLine();
                if(lastQuestion1.equalsIgnoreCase("save"))
                {
                    if(lastQweryLine2 == null && lastQweryLine1 == null) //if nothing was found during most recent search
                    {
                        System.out.println("No results found; nothing to save."); 
                    }
                    else if(lastQweryLine1 != null || lastQweryLine2 != null) //writes to a file if last search was successful
                    {
                        System.out.println("Please enter the name of a file: "); 
                        fileNameUserChooses = inputReader.readLine() + ".txt"; 
                        Driver.writeOut(lastQweryLine1, lastQweryLine2, fileNameUserChooses);   //writes to file output
                    }
                    else
                    {
                        System.out.println("Error in saving last qwery line");
                    }
                    
                    System.out.println("Graph, Continue, or Exit?");
                    String finalAnswer = inputReader.readLine();
                    if(finalAnswer.equalsIgnoreCase("continue"))
                    {
                        keepRunning = true;
                    }
                    else if(finalAnswer.equalsIgnoreCase("exit"))
                    {
                        System.out.println("Thank you for using TeamMate!"); 
                        keepRunning = false;    
                    }
                    else if(finalAnswer.equalsIgnoreCase("graph"))
                    {
                        System.out.println("Age or Location?");
                        String graphQuestion = inputReader.readLine();
                        if(graphQuestion.equalsIgnoreCase("age"))
                        {

                            if(lastQweryLine1 != null)
                            {
                                PieChartSample.findAgesWithTypeArrayListPerson(lastQweryLine1);
                                PieChartSample.main(args);
                            }
                            else if(lastQweryLine2 != null)
                            {
                                PieChartSample.findAgesWithTypeStrings(lastQweryLine2);
                                PieChartSample.main(args);
                            }
                        }
                        else if(graphQuestion.equalsIgnoreCase("location"))
                        {
                        	ArrayList<MapPoint> points = new ArrayList<MapPoint>();
                         
                        
                        	for(int j = 0; j < lastQweryLine1.size(); j++)  //creates a MapPoint object for each Player's city/lat/lon
                        	{
                        		Person p = lastQweryLine1.get(j);
                        		int y = p.getCity().getLatitude();
                        		int x = p.getCity().getLongitude();
                        		MapPoint newPoint = new MapPoint(x, y, p.getCity().toString());
                        		points.add(newPoint);
                        	}
                        	TeamMapFrame carreeMap = new TeamMapFrame(points);
                        	carreeMap.repaint();
                        }
                        System.out.println("Continue or Exit?");
                        String thisAnswer = inputReader.readLine();
                        if(thisAnswer.equalsIgnoreCase("continue"))
                        {
                            keepRunning = true;
                        }
                        else if(thisAnswer.equalsIgnoreCase("exit"))
                        {
                            System.out.println("Thank you for using TeamMate!"); 
                            keepRunning = false;
                        }
                    }
                    else
                    {
                        System.out.println(finalAnswer + " is an unknown input!");
                    }
                }
                
                else if(lastQuestion1.equalsIgnoreCase("skip"))
                {
                    System.out.println("Graph, Continue or Exit?");
                    String finalAnswer = inputReader.readLine();
                    if(finalAnswer.equalsIgnoreCase("continue"))
                    {
                        keepRunning = true;
                    }
                    else if(finalAnswer.equalsIgnoreCase("exit"))
                    {
                        System.out.println("Thank you for using TeamMate!"); 
                        keepRunning = false;    
                    }
                    else if(finalAnswer.equalsIgnoreCase("graph"))
                    {
                        System.out.println("Age or Location?");
                        String graphQuestion = inputReader.readLine();
                        if(graphQuestion.equalsIgnoreCase("age"))
                        {
                            if(lastQweryLine1 != null)
                            {
                                PieChartSample.findAgesWithTypeArrayListPerson(lastQweryLine1);
                                PieChartSample.main(args);
                            }
                            else if(lastQweryLine2 != null)
                            {
                                PieChartSample.findAgesWithTypeStrings(lastQweryLine2);
                                PieChartSample.main(args);
                            }
                            
                        }
                        else if(graphQuestion.equalsIgnoreCase("location"))
                        {
                        	ArrayList<MapPoint> points = new ArrayList<MapPoint>();
                        	
                        	for(int j = 0; j < lastQweryLine1.size(); j++)  //creates a MapPoint object for each Player's city/lat/lon
                        	{
                        		Person p = lastQweryLine1.get(j);
                        		int y = p.getCity().getLatitude(); 
                        		int x = p.getCity().getLongitude();
           
                        		MapPoint newPoint = new MapPoint(x, y, p.getCity().toString());
                        		points.add(newPoint);
                        	}
                        	TeamMapFrame carreeMap = new TeamMapFrame(points);
                        	carreeMap.repaint();
                        }
                        System.out.println("Continue or Exit?");
                        String thisAnswer = inputReader.readLine();
                        if(thisAnswer.equalsIgnoreCase("continue"))
                        {
                            keepRunning = true;
                        }
                        else if(thisAnswer.equalsIgnoreCase("exit"))
                        {
                            System.out.println("Thank you for using TeamMate!"); 
                            keepRunning = false;
                        }
                    }
                    else
                    {
                        System.out.println(lastQuestion1 + " is an unknown input!");
                    }
                
                newList = new ArrayList<Person>(); 
                newList1 = new ArrayList<Person>();    
        }  
    }
inputReader.close();
}
}
