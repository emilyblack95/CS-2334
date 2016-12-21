import javax.swing.*;

import java.awt.*;

/**
 * Project 5
 * CS 2334 - Section 010
 * 4/29/15
 * This class sets up the JFRAME/BUTTONS/MENU ITEMS
 * This class also has accessors for instance data like the buttons or menu options
 */
public class SelectionView extends JFrame 
{
	private static final long serialVersionUID = 1L; 
	@SuppressWarnings("unused")
	private TeamMateModel model;
	private JFrame frame;
	private JList<City> jlPlaceList; 
	private JList<Person> jlPersonList;
	private JList<Team> jlTeamList;
	private JScrollPane jspPlace;
	private JScrollPane jspPerson;
	private JScrollPane jspTeam;
	private JButton jbtAddPlace;
	private JButton jbtAddPerson;
	private JButton jbtAddTeam;
	private JButton jbtEditPlace;
	private JButton jbtEditPerson;
	private JButton jbtEditTeam;
	private JButton jbtDeletePlace;
	private JButton jbtDeletePerson;
	private JButton jbtDeleteTeam;
	private JMenuBar jmbMenuBar = new JMenuBar();
	private JMenu jmFileMenu = new JMenu("File");
	private JMenuItem jmiLoadTeamMateData = new JMenuItem("Load TeamMate Data");
	private JMenuItem jmiSaveTeamMateData = new JMenuItem("Save TeamMate Data");
	private JMenuItem jmiImportTeamMateData = new JMenuItem("Import TeamMate Data");
	private JMenuItem jmiExportTeamMateData = new JMenuItem("Export TeamMate Data");
	private JMenu jmGraphMenu = new JMenu("Graph");
	private JMenuItem jmiPieChart = new JMenuItem("Pie Chart");
	private JMenuItem jmiMap = new JMenuItem("Map");
	private JPanel jpPlaceListPanel;
	private JPanel jpPersonListPanel;
	private JPanel jpTeamListPanel;
	private JPanel jpPlaceButtons;
	private JPanel jpPersonButtons;
	private JPanel jpTeamButtons;
	private JLabel placeLabel = new JLabel("Places");
	private JLabel personLabel = new JLabel("People");
	private JLabel teamLabel = new JLabel("Teams");
	//private ArrayList<Person> personList = new ArrayList<Person>();
	//private ArrayList<City> cityList = new ArrayList<City>();
	//private ArrayList<Team> teamList = new ArrayList<Team>();
	
	/**
	 * Main constructor.
	 * @param model
	 */
	public SelectionView(TeamMateModel model)
	{
		this.model = model;
		
		jbtAddPlace = new JButton("Add");
		jbtAddPlace.setActionCommand("Add Place");
		jbtAddPerson = new JButton("Add");
		jbtAddPerson.setActionCommand("Add Person");
		jbtAddTeam = new JButton("Add");
		jbtAddTeam.setActionCommand("Add Team");	
		jbtEditPlace = new JButton("Edit");
		jbtEditPlace.setActionCommand("Edit Place");		
		jbtEditPerson = new JButton("Edit");
		jbtEditPerson.setActionCommand("Edit Person");	
		jbtEditTeam = new JButton("Edit");
		jbtEditTeam.setActionCommand("Edit Team");		
		jbtDeletePlace = new JButton("Delete");
		jbtDeletePlace.setActionCommand("Delete Team");
		jbtDeletePerson = new JButton("Delete");
		jbtDeletePerson.setActionCommand("Delete Person");
	    jbtDeleteTeam = new JButton("Delete");
	    jbtDeleteTeam.setActionCommand("Delete Team");
	    
	    //if Load or Import TeamMateData has been called
	    if (model.getCities() != null && model.getPeople() != null && model.getTeams() != null) 
	    {
	    	int cityNum = model.getCities().size();
	 	    int personNum = model.getPeople().size();
	 	    int teamNum = model.getTeams().size();
	 	    
	 	    //creates arrays to use when passing data from the model's array lists into the view's JLists
	 	    City[] cityArray = new City[cityNum];
	 	    Person[] personArray = new Person[personNum];
	 	    Team[] teamArray = new Team[teamNum];
	 	
	 		jlPlaceList = new JList<City>(model.getCities().toArray(cityArray));
	 		jlPersonList = new JList<Person>(model.getPeople().toArray(personArray)); 
	 		jlTeamList = new JList<Team>(model.getTeams().toArray(teamArray)); 
	    }
	    
	    //if SelectionView is being implemented for the first time
	    else{
	    	jlPlaceList = new JList<City>();
	    	jlPersonList = new JList<Person>();
	    	jlTeamList = new JList<Team>();
	    }
 		jspPlace = new JScrollPane(jlPlaceList);
 		jspPerson = new JScrollPane(jlPersonList);
 		jspTeam = new JScrollPane(jlTeamList);
 		
 		/**
		 * Set to true if you want buttons to be clickable
		 */
		jbtEditPlace.setEnabled(true);
		jbtDeletePlace.setEnabled(true);
		jbtEditPerson.setEnabled(true);
		jbtDeletePerson.setEnabled(true);
		jbtEditTeam.setEnabled(true);
		jbtDeleteTeam.setEnabled(true);
		
		/**
		 * Bottom bar of Place section
		 */
		jpPlaceListPanel = new JPanel();
		jpPlaceListPanel.setLayout(new BorderLayout());
		jpPlaceListPanel.add(jspPlace, BorderLayout.CENTER);
		jpPlaceListPanel.add(placeLabel, BorderLayout.NORTH);
		jpPlaceButtons = new JPanel(new GridLayout(1,3));
		jpPlaceButtons.add(jbtAddPlace, BorderLayout.WEST);
		jpPlaceButtons.add(jbtEditPlace, BorderLayout.CENTER);
		jpPlaceButtons.add(jbtDeletePlace, BorderLayout.EAST);
		jpPlaceListPanel.add(jpPlaceButtons, BorderLayout.SOUTH);
		
		/**
		 * Bottom bar of Person section
		 */
		jpPersonListPanel = new JPanel(); 
		jpPersonListPanel.setLayout(new BorderLayout());
		jpPersonListPanel.add(jspPerson, BorderLayout.CENTER);
		jpPersonListPanel.add(personLabel, BorderLayout.NORTH);  
		jpPersonButtons = new JPanel(new GridLayout(1,3));
		jpPersonButtons.add(jbtAddPerson, BorderLayout.WEST);
		jpPersonButtons.add(jbtEditPerson, BorderLayout.CENTER);
		jpPersonButtons.add(jbtDeletePerson, BorderLayout.EAST);
		jpPersonListPanel.add(jpPersonButtons, BorderLayout.SOUTH); //TODO: WHY DON'T BUTTONS SHOW UP?
		
		/**
		 * Bottom bar of Team section
		 */
		jpTeamListPanel = new JPanel();
		jpTeamListPanel.setLayout(new BorderLayout());
		jpTeamListPanel.add(jspTeam, BorderLayout.CENTER);
		jpTeamListPanel.add(teamLabel, BorderLayout.NORTH);
		jpTeamButtons = new JPanel(new GridLayout(1,3));
		jpTeamButtons.add(jbtAddPerson, BorderLayout.WEST);
		jpTeamButtons.add(jbtEditPerson, BorderLayout.CENTER);
		jpTeamButtons.add(jbtDeletePerson, BorderLayout.EAST);
		jpTeamListPanel.add(jpTeamButtons, BorderLayout.SOUTH);  
		
		/**
		 * Sets new JFRAME. Lets user exit on close.
		 */
		frame = new JFrame("TeamMate");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//TODO: change enabled to true/false & adjust tool tips as needed
		jmiImportTeamMateData.setToolTipText("No data to import");
		jmiImportTeamMateData.setEnabled(false);
		jmiExportTeamMateData.setToolTipText("No data to export");
		jmiExportTeamMateData.setEnabled(false);
		jmiPieChart.setToolTipText("No data to graph");
		jmiPieChart.setEnabled(true);
		jmiMap.setToolTipText("No data to graph");
		jmiMap.setEnabled(true); 

		jmFileMenu.add(jmiLoadTeamMateData);
		jmFileMenu.add(jmiSaveTeamMateData);
		jmFileMenu.add(jmiImportTeamMateData);
		jmFileMenu.add(jmiExportTeamMateData);
		jmGraphMenu.add(jmiPieChart);
		jmGraphMenu.add(jmiMap);
		jmbMenuBar.add(jmFileMenu); //creates the File Menu
		jmbMenuBar.add(jmGraphMenu); //creates the Graph Menu
		frame.setJMenuBar(jmbMenuBar);
		
		frame.add(jpPlaceListPanel, BorderLayout.WEST);
		frame.add(jpPersonListPanel, BorderLayout.CENTER);
		frame.add(jpTeamListPanel, BorderLayout.EAST);

		frame.pack(); 
		frame.setLocation(300,100); 
		frame.setVisible(true);
	}
		
	/**
	 * Accessor for button to add person
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTAddPerson() 
	{
		return jbtAddPerson;
	}
	
	/**
	 * Accessor for button to add place
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTAddPlace() 
	{
		return jbtAddPlace;
	}
	
	/**
	 * Accessor for button to add team
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTAddTeam() 
	{
		return jbtAddTeam;
	}
	
	/**
	 * Accessor for button to edit person
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTEditPerson() 
	{
		return jbtEditPerson;
	}
	
	/**
	 * Accessor for button to edit place
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTEditPlace() 
	{
		return jbtEditPlace;
	}
	
	/**
	 * Accessor for button to edit team
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTEditTeam() 
	{
		return jbtEditTeam;
	}
	
	/**
	 * Accessor for button to delete person
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTDeletePerson() 
	{
		return jbtDeletePerson;
	}
	
	/**
	 * Accessor for button to delete place
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTDeletePlace() 
	{
		return jbtDeletePlace;
	}
	
	/**
	 * Accessor for button to delete team
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTDeleteTeam() 
	{
		return jbtDeleteTeam;
	}
	
	 /**
     * Accessor for menu item to load teammate data
     * @return the JMenuItem that can be pressed by the user
     */
    public JMenuItem getJMILoadTeamMateData() 
    {
        return jmiLoadTeamMateData;
    }
    
    /**
     * Accessor for menu item to save teammate data
     * @return the JMenuItem that can be pressed by the user
     */
    public JMenuItem getJMISaveTeamMateData() 
    {
        return jmiSaveTeamMateData;
    }
    
    /**
     * Accessor for menu item to import teammate data
     * @return the JMenuItem that can be pressed by the user
     */
    public JMenuItem getJMIImportTeamMateData() 
    {
        return jmiImportTeamMateData;
    }
    
    /**
     * Accessor for menu item to export teammate data
     * @return the JMenuItem that can be pressed by the user
     */
    public JMenuItem getJMIExportTeamMateData() 
    {
        return jmiExportTeamMateData;
    }
    
    /**
     * Accessor for menu item to display the pie chart
     * @return the JMenuItem that can be pressed by the user
     */
    public JMenuItem getJMIPieChart() 
    {
        return jmiPieChart;
    }
    
    /**
     * Accessor for menu item to display the map
     * @return the JMenuItem that can be pressed by the user
     */
    public JMenuItem getJMIMap() 
    {
        return jmiMap;
    }
	
	/**
	 * Accessor for the People
	 * @return the ArrayList of Person objects currently in scroll list
	 */
	public JList<Person> getPersonList()
	{
		return this.jlPersonList;
	}
	
	/**
	 * Accessor for the Place
	 * @return the ArrayList of City objects currently in scroll list
	 */
	public JList<City> getPlaceList()
	{
		return this.jlPlaceList;
	}
	
	/**
	 * Accessor for the Teams
	 * @return the ArrayList of Team objects currently in scroll list 
	 */
	public JList<Team> getTeamList()
	{
		return this.jlTeamList;
	}
	
	/**
	 * Empties the list of Person objects.
	 * the previously filled list. No return type.
	 */
	public void clearPeopleList()
	{
		jlPersonList.clearSelection();
	}
	
	/**
	 * Empties the list of City objects.
	 * the previously filled list. No return type.
	 */
	public void clearPlaceList()
	{
		jlPlaceList.clearSelection();
	}
	
	/**
	 * Empties the list of Team objects.
	 * the previously filled list. No return type.
	 */
	public void clearTeamList()
	{
		jlTeamList.clearSelection();
	}
	
}
