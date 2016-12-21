import java.awt.*;
import javax.swing.*;

/**
 * Project 4
 * CS 2334 - Section 010
 * 4/20/15
 *
 */
public class EditView extends JFrame {

	private static final long serialVersionUID = 1L;
	private TeamMateModel model;
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
	private JButton jbtEditSeason = new JButton("Add Season");
	private JButton jbtSelectState = new JButton("Select State");
	private JButton jbtSelectCity = new JButton("Select City");
	private JList<City> jlSelectedCity = new JList<City>();
	private JList<State> jlSelectedState = new JList<State>();
	private JList<Person> jlSelectedPlayers = new JList<Person>();
	
	/**
	 * Main Constructor.
	 * @param editCity
	 * @param model
	 */
	public EditView(City editCity, TeamMateModel model) //TODO: model param or setModel?
	{	//"Edit City View"
		
		this.model = model;
		//if (this.model != null) 
			//this.model.addActionListener(this); //TODO: ??
			
		frame = new JFrame("Edit Place");
		panel = new JPanel(new GridLayout(3,1));
		jbtOK.setActionCommand("OK");
		jbtCancel.setActionCommand("Cancel");
		buttonPanel.add(jbtOK);
		buttonPanel.add(jbtCancel);
		JLabel jlState = new JLabel("State (two-letter abbreviation): ");
		JLabel jlCity = new JLabel("City: ");
		jtfPersonCity = new JTextField(editCity.toString());
		jtfPersonState = new JTextField(editCity.getState().toString());
		panel.add(jlCity);
		panel.add(jtfPersonCity);
		panel.add(jlState);
		panel.add(jtfPersonState);
		panel.add(buttonPanel);

		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//city & state entry/data handling
	}
	
	/**
	 * Secondary Constructor.
	 * @param editPerson
	 * @param model
	 */
	public EditView(Person editPerson, TeamMateModel model)
	{
		//"Edit Person View"
		this.model = model;
		//if (this.model != null) 
			//this.model.addActionListener(this); 
		
		frame = new JFrame("Edit Person");
		panel = new JPanel(new GridLayout(5,2));
		JLabel jlName = new JLabel("Name: ");
		JLabel jlDOB = new JLabel("Birthdate (DD/MM/YYYY): ");
		JLabel jlCity = new JLabel("City of birth: ");
		JLabel jlState = new JLabel("State of birth (two-letter abbreviation): ");
		jtfPersonName = new JTextField(editPerson.getFullName().toString());
		jtfPersonDOB = new JTextField(editPerson.getDOB().toString());
		jbtOK.setActionCommand("OK");
		jbtCancel.setActionCommand("Cancel");
		jbtSelectCity.setActionCommand("Select City");
		jbtSelectState.setActionCommand("Select State");
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

		//TODO: code to update EditView to show what value they chose after City/State Selection View has run?
	}
	
	/**
	 * Secondary Constructor.
	 * @param editTeam
	 * @param model
	 */
	public EditView(Team editTeam, TeamMateModel model)
	{	//"Edit Team View"
		this.model = model;
		//if (this.model != null) 
			//this.model.addActionListener(this); 
		
		frame = new JFrame("Edit Team");
		panel = new JPanel(new GridLayout(3,2));
		jbtOK.setActionCommand("OK");
		jbtCancel.setActionCommand("Cancel");
		jbtEditSeason.setActionCommand("Add Season");
		buttonPanel.add(jbtOK);
		buttonPanel.add(jbtCancel);
		JLabel jlID = new JLabel("Team ID: ");
		jtfTeamID = new JTextField(editTeam.getID());  //TODO: method like this in Team
		JLabel jlEditSeason = new JLabel("Seasons: " + editTeam.getSeasons().toString()); 
		panel.add(jlID);
		panel.add(jtfTeamID);
		panel.add(jlEditSeason);
		panel.add(jbtEditSeason);
		panel.add(buttonPanel);

		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);			
	}

	
	/**
	 * Mutator - sets the model for this view class
	 * @param 	newModel	TeamMateModel for this view
	 */
	/*
	public void setModel(TeamMateModel model){
		this.model = model;
		if (this.model != null) {
			//this.model.addActionListener(this); 
		}
	}
	*/

	/**
	 * Accessor for OK button
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTOK() {
		return jbtOK;
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
	 * Accessor for Edit Season button
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTEditSeason(){
		return this.jbtEditSeason;
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
	 * Method that sets visible if visible is true or clears if its not true.
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

