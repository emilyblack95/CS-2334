import java.awt.*;
import javax.swing.*;

/**
 * Project 4
 * CS 2334 - Section 010
 * 4/20/15
 *
 */

public class AddView extends JFrame{

	private static final long serialVersionUID = 1L;
	private TeamMateModel model;
	private String dataType; //Type of data to be added (Person, Place, or Team); determined by the event source of the action listener
	private JTextField jtfTeamID;
	private JTextField jtfPersonName;
	private JTextField jtfPersonDOB;
	private JTextField jtfPersonCity;
	private JTextField jtfPersonState;
	private JFrame frame;
	private JPanel panel;
	private JPanel buttonPanel = new JPanel(new GridLayout(1,2));
	private JButton jbtOK = new JButton("OK");
	private JButton jbtCancel = new JButton("Cancel");
	private JButton jbtSelectState = new JButton("Select State");
	private JButton jbtSelectCity = new JButton("Select City");
	private JList<City> jlSelectedCity = new JList<City>();
	private JList<Person> jlSelectedPlayers = new JList<Person>();
	private JList<State> jlSelectedState = new JList<State>();
	private SelectCityView selectCityView = new SelectCityView();
	private SelectStateView selectStateView = new SelectStateView();

	/**
	 * Main Constructor.
	 * @param type
	 * @param model
	 */
	public AddView(String type, TeamMateModel model){
		this.dataType = type;
		this.model = model;

		if (dataType.equals("Add Place"))
		{
			//"Add Place View"
			frame = new JFrame("Add Place");
			panel = new JPanel(new GridLayout(3,1));
			buttonPanel.add(jbtOK);
			buttonPanel.add(jbtCancel);
			JLabel jlState = new JLabel("State (two-letter abbreviation): ");
			JLabel jlCity = new JLabel("City: ");
			jtfPersonCity = new JTextField();
			jtfPersonState = new JTextField(2);
			panel.add(jlCity);
			panel.add(jtfPersonCity);
			panel.add(jlState);
			panel.add(jtfPersonState);
			panel.add(buttonPanel);

			frame.add(panel);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}


		else if (type.equals("Add Person")){
			//"Add Person View"
			frame = new JFrame("Add Person");
			panel = new JPanel(new GridLayout(5,2));
			JLabel jlName = new JLabel("Name: ");
			JLabel jlDOB = new JLabel("Birthdate (DD/MM/YYYY): ");
			JLabel jlCity = new JLabel("City of birth: ");
			JLabel jlState = new JLabel("State of birth (two-letter abbreviation): ");
			jtfPersonName = new JTextField();
			jtfPersonDOB = new JTextField(10);
			buttonPanel.add(jbtOK);
			buttonPanel.add(jbtCancel);
			panel.add(jlName);
			panel.add(jtfPersonName);
			panel.add(jlDOB);
			panel.add(jtfPersonDOB);
			panel.add(jlCity);
			panel.add(jbtSelectCity);
			panel.add(jlState);
			panel.add(jbtSelectState);
			panel.add(buttonPanel); 
			frame.add(panel);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);

			//TODO: code to update AddView to show what value they chose after City/State Selection View has run
			
		}
		else if (type.equals("Add Team")){
			//"Add Team View"
			frame = new JFrame("Add Team");
			panel = new JPanel(new GridLayout(2,2));
			buttonPanel.add(jbtOK);
			buttonPanel.add(jbtCancel);
			JLabel jlID = new JLabel("Team ID: ");
			jtfTeamID = new JTextField(); 
			panel.add(jlID);
			panel.add(jtfTeamID);
			panel.add(buttonPanel);

			frame.add(panel);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);			

		}
	}

	/**
	 * Mutator - sets the model for this view class
	 * @param 	newModel	TeamMateModel for this view
	 */
	public void setModel(TeamMateModel model){
		this.model = model;
		if (this.model != null) {
			//this.model.addActionListener(this); 
		}
	}
	
	/**
	 * Accessor for OK button
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTOK(){
		return this.jbtOK;
	}

	/**
	 * Accessor for Cancel button
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTCancel(){
		return this.jbtCancel;
	}

	/**
	 * Accessor for Select City button
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTSelectCity(){
		return this.jbtSelectCity;
	}

	/**
	 * Accessor for Select State button
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTSelectState(){
		return this.jbtSelectState;
	}

	/**
	 * Accessor for Selected Cities list
	 * @return the current list of cities in City Selection View
	 */
	public JList<City> getJLSelectedCityList(){
		return this.jlSelectedCity;
	}

	/**
	 * Accessor for Selected Players list 
	 * @return the current list of players of type Person in Add Season View
	 */
	public JList<Person> getJLSelectedPlayersList(){
		return this.jlSelectedPlayers;
	}

	/**
	 * Accessor for Selected State list 
	 * @return the current list of states in Add State View
	 */
	public JList<State> getJLSelectedStateList(){
		return this.jlSelectedState;
	}
	/**
	 * Adds a view to the model
	 */
	public void addToModel(){
		if (this.dataType.equals("Add Person")){
			String name = jtfPersonName.getText();
			String[] nameArray = name.split(" ");
			Name newName;
			if (nameArray.length == 2)
				newName = new Name(nameArray[0], nameArray[1]);
			else if (nameArray.length == 3)
				newName = new Name(nameArray[0], nameArray[1], nameArray[2]);
			else if (nameArray.length == 4)
				newName = new Name(nameArray[0], nameArray[1], nameArray[2], nameArray[3]);
			else if (nameArray.length >= 5)
				newName = new Name(nameArray[0], nameArray[1], nameArray[2], nameArray[3], nameArray[4]);
			else
				newName = null;
			String dob = jtfPersonDOB.getText();
			Date birthdate = Date.stringToDate(dob); 
			City newCity = selectCityView.getJLSelectedCity();
			State newState = newCity.getState();
			//TODO: ^^^FIX THIS CODE - get data from other views
			Person added = new Person(newName, birthdate, newCity, newState);
			model.addPerson(added);
		}
		else if (this.dataType.equals("Add Place")){
			String otherCityName = selectCityView.getJLSelectedCity().toString();
			State otherState = selectStateView.getJLSelectedState();
			model.addPlace(otherCityName, otherState);
		}
		else if (this.dataType.equals("Add Team")){
			City city = selectCityView.getJLSelectedCity();
			State state = selectStateView.getJLSelectedState();
			Team newTeam = new Team(this.jtfTeamID.getText(), city, state);
			model.addTeam(newTeam);
			//TODO: update model
			
		}
	}
	/**
	 * Sets to visible if visible is true, else clears selection.
	 * @param visible
	 */
	public void setAsVisible(boolean visible){
		if(visible == true)
			this.setVisible(true);
		else
		{
			this.setVisible(false);
			jlSelectedCity.clearSelection();
			jlSelectedPlayers.clearSelection();
			jlSelectedState.clearSelection();
			jtfTeamID.setText("");
			jtfPersonName.setText("");
			jtfPersonDOB.setText("");
			jtfPersonCity.setText("");
			jtfPersonState.setText("");
		}
	}

}

