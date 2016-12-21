import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Project 2
 * CS 2334 - Section 010
 * 2/26/15
 *
 */
public class Driver
{
	private static ArrayList<Person> listOfData = new ArrayList<Person>();
	private static String lastQweryLine;
	private static String fileNameUserChooses;
	
	/**
	 * Takes data from the file and puts it into an ArrayList of type Person
	 * @param fileName
	 * @return listOfData
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
			splitData = singlePerson.split(","); //string of data goes into array, splits with ","
			if(splitData.length == 4 || splitData.length == 5) 
			{
				Name personsName;
				String[] splitName = splitData[0].split(" ");  //Spliting up the person's name by a space
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
					System.out.println("Persons name is null");
					personsName = null;
				}
				if(personsName != null)
				{
					Person random1 = new Person(personsName, splitData[1], splitData[2], splitData[3]);
					listOfData.add(random1);
				}
			}
			word = br.readLine();
		}
		br.close();
		return listOfData;
	}
	
	/**
	 * If the user wants to write out the last thing in Console to a file, you call this method.
	 * @param lastQweryLine
	 * @throws IOException 
	 */
	public static void writeOut(String lastQweryLine, String fileNameUserChooses) throws IOException
	{
		FileWriter outfile = new FileWriter(fileNameUserChooses);
		BufferedWriter bw = new BufferedWriter(outfile);
		bw.write(lastQweryLine);
		bw.newLine();
		bw.close();
	}
	
	/**
	 * Main interface of program. (Method that asks all the questions). No return type.
	 * @param listOfData
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		boolean fileFound = false;
		while (fileFound == false)
		{
			System.out.println("Please enter the name of the file where data is stored(Example: myFile.txt):"); //prompts user for name where file is stored
			String fileName = inputReader.readLine();
			try
			{
				listOfData = Driver.fillArrayList(fileName);
				listOfData.trimToSize(); //removes excess blank space
				fileFound = true;
			} catch (IOException FileNotFoundException)
			{ //catches bad input if the name of the file isn't found
				System.out.println("File " + fileName + " not found.");
				fileFound = false;
			}
		}
			
		ArrayList<String> nameList = new ArrayList<String>(); 
		ArrayList<Person> newList = new ArrayList<Person>();
		ArrayList<Person> newList1 = new ArrayList<Person>();
		boolean keepRunning = true;
		
		while(keepRunning == true)
		{
				System.out.println("People or Place?");
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
							Collections.sort(listOfData);
							for (int i = 0; i < listOfData.size(); i++)
								System.out.println(listOfData.get(i).toString()); //prints each out on a new line
							lastQweryLine = listOfData.toString();
						}
						else if(answer3.equalsIgnoreCase("last"))
						{
							Collections.sort(listOfData, new CompareLastName());
							for (int i = 0; i < listOfData.size(); i++)
								System.out.println(listOfData.get(i).toString()); //prints each out on a new line
							lastQweryLine = listOfData.toString();
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
							Collections.sort(listOfData); //sorts list of data using compareTo() method, by first name
							for (int i=0; i < listOfData.size(); i++)
							{
								nameList.add(listOfData.get(i).getFullName().toString());//creates an ArrayList of names of people so they can be searched for
							}
							nameList.trimToSize(); //removes excess blank space
							
							int index = Collections.binarySearch(nameList, answer4);
							
							if (index >= 0)
								foundPerson = listOfData.get(index);
							
							if(foundPerson != null)
							{
								System.out.println("Exact name match for \"" + answer4 + "\":");
								System.out.println(foundPerson.toString());
								lastQweryLine = foundPerson.toString();
							}
							else
							{
								System.out.println("No person with name \"" + answer4 + "\" found in file. Please try a different search.");
								lastQweryLine = null;
							}
						}
						else if(answer3.equalsIgnoreCase("partial"))
						{
							System.out.println("Enter a PARTIAL name to search for:");
							String answer4 = inputReader.readLine();
							for(int i = 0; i < listOfData.size(); i++)
							{
								if((listOfData.get(i).getFullName().toString().toLowerCase().contains(answer4.toLowerCase())))  //If name was found
								{
									newList.add(listOfData.get(i));  //add to new list
								}
							}
							if(newList.size() == 0)
							{
								System.out.println("No people found with name " + answer4.toLowerCase() + ". Please try a different search."); 
								lastQweryLine = null;
							}
							else if(newList.size() != 0)
							{
								System.out.println("Partial name match for \"" + answer4.toLowerCase() + "\": ");
								for (int i=0; i<newList.size(); i++)
								{
									System.out.println(newList.get(i).toString());
								}
								lastQweryLine = newList.toString();
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
						ArrayList<City> cityList = state1.fillCityList(listOfData); //fills state's cityList with data from the file
						Collections.sort(cityList); //sorts list alphabetically using State's compareTo method
						
						if(cityList.size() == 0)
						{
							System.out.println("No people found in state " + answer3 + ". Please try a different search."); 
						}
						System.out.println("Please enter the name of a city in state " + answer3 + " to search for: "); 
						String answer4 = inputReader.readLine();
						City city1 = new City(answer4, state1); //creates city object based on user input
						ArrayList<Person> peopleInCity = city1.fillResidentList(listOfData); //fills the city's resident list with data from the file
						try{
						Collections.sort(peopleInCity); //sorts city's resident list alphabetically based on Person's compareTo method
						
						if(peopleInCity.size() != 0)
						{
							System.out.println(state1.toString() + ":"); //prints "STATE:"
							System.out.println(city1.toString() + ":"); //prints "CITY:"
							for (int i=0; i < peopleInCity.size(); i++){
								System.out.println(peopleInCity.get(i).toString()); //prints each Person in the list
							}
							lastQweryLine = peopleInCity.toString(); 
						}
						else if(newList1.size() == 0)
						{
							System.out.println("No people found in " + answer4 + ", " + answer3 + ". Please try a different search."); 
							lastQweryLine = null; 
						}
						} catch(NullPointerException e)
						{
							System.out.println("Null Pointer Exception");
						}
					}
					else if(answer2.equalsIgnoreCase("state"))
					{
						System.out.println("Please enter the two-letter abbreviation of the state you'd like to search for:"); 
						String answer3 = inputReader.readLine();
						State state2 = new State(answer3);
						state2.fillCityList(listOfData); //fills city list with data from the file
						ArrayList<City> listOfCities2 = state2.getCityList(); 
						Collections.sort(listOfCities2); //sorts cities alphabetically
						
						if (state2.getCityList().size() == 0)
						{
							System.out.println("No cities found in state" + answer3 + ". Please try a different search."); 
							lastQweryLine = null;
						}
						else if (state2.getCityList().size() != 0)
						{
							System.out.println(state2.toString() + ":");
							for (int i = 0; i < listOfCities2.size(); i++)
							{
								System.out.println(listOfCities2.get(i).toString() + ":");
								ArrayList<Person> resList = listOfCities2.get(i).fillResidentList(listOfData);
								for (int j = 0; j < resList.size(); j++)
								{
									System.out.println(resList.get(j).toString());
								}
							}
							lastQweryLine = newList.toString();
						}
					}
				}
				else
				{
					System.out.println(answer + " is an unknown input!");
				}
				System.out.println("Save or Skip?");
				String lastQuestion1 = inputReader.readLine();
				if(lastQuestion1.equalsIgnoreCase("save"))
				{
					if(lastQweryLine == null) //if nothing was found during most recent search
					{
						System.out.println("No results found; nothing to save."); //DO WE WANT THIS, or can they save a blank file?
																				 //IF SO, go back to old lastQweryLine stuff :)
					}
					else //writes to a file if last search was successful
					{
						System.out.println("Please enter the name of a file: "); 
						fileNameUserChooses = inputReader.readLine() + ".txt"; 
						Driver.writeOut(lastQweryLine, fileNameUserChooses);   //writes to file output
					}
					
					System.out.println("Continue or Exit?");
					String finalAnswer = inputReader.readLine();
					if(finalAnswer.equalsIgnoreCase("continue"))
					{
						keepRunning = true;
					}
					else if(finalAnswer.equalsIgnoreCase("exit"))
					{
						System.out.println("Thank you for using PeoplAce!"); 
						keepRunning = false;	
					}
					else
					{
						System.out.println(finalAnswer + " is an unknown input!");
					}
				}
				
				else if(lastQuestion1.equalsIgnoreCase("skip"))
				{
					System.out.println("Continue or Exit?");
					String finalAnswer = inputReader.readLine();
					if(finalAnswer.equalsIgnoreCase("continue"))
					{
						keepRunning = true;
					}
					else if(finalAnswer.equalsIgnoreCase("exit"))
					{
						System.out.println("Thank you for using PeoplAce!"); 
						keepRunning = false;	
					}
					else
					{
						System.out.println(finalAnswer + " is an unknown input!");
					}
				}
				else
				{
					System.out.println(lastQuestion1 + " is an unknown input!");
				}
				
				newList = new ArrayList<Person>(); 
				newList1 = new ArrayList<Person>();	
		}
		inputReader.close();
	}
}
