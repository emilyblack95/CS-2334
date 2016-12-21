import java.awt.*;
import javax.swing.*;

/**
 * Project 4
 * CS 2334 - Section 010
 * 4/20/15
 *
 */

public class AddSeasonView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private TeamMateModel model;
	private Team team;
	private JList<City> jlSelectedCity;
	private JList<Person> jlSelectedPlayers;
	private JPanel buttonPanel = new JPanel();
	private JButton jbtOK = new JButton("OK");
	private JButton jbtCancel = new JButton("Cancel");
	private JButton jbtSelectYear = new JButton("Select Year");
	
	/**
	 * Main Constructor.
	 */
	public AddSeasonView(){
		//"Add Season View"
		Person[] personArray = null;
		
		JFrame addSeasonView = new JFrame("Add Season");
		JPanel jpSeasonPanel = new JPanel(new BorderLayout());
		JPanel jpSeasonScrolls = new JPanel(new GridLayout(2,1));
		JLabel jlName = new JLabel("Enter Name: ");
		JLabel jlPlayers = new JLabel("Select Players: ");
		jlSelectedPlayers = new JList<Person>(model.getPeople().toArray(personArray)); //TODO: list of people who are NOT on a team yet, if time
		JTextField jtfName = new JTextField();
		JScrollPane jspPlayerScroll = new JScrollPane(jlSelectedPlayers); 
		JPanel jpNamePanel = new JPanel(new GridLayout(2,1));
		JPanel jpPlayersPanel = new JPanel(new BorderLayout());

		jpNamePanel.add(jlName);
		jpNamePanel.add(jtfName);
		jpPlayersPanel.add(jlPlayers, BorderLayout.NORTH);
		jpPlayersPanel.add(jspPlayerScroll, BorderLayout.CENTER);

		jpSeasonScrolls.add(jpNamePanel); //adds labeled scrolling City panel
		jpSeasonScrolls.add(jpPlayersPanel); //adds labeled scrolling Players panel

		jbtOK.setActionCommand("OK");
		jbtCancel.setActionCommand("Cancel");
		jbtSelectYear.setActionCommand("Select Year");
		buttonPanel.add(jbtOK);
		buttonPanel.add(jbtCancel);
		jpSeasonPanel.add(jbtSelectYear, BorderLayout.NORTH);
		jpSeasonPanel.add(jpSeasonScrolls, BorderLayout.CENTER);
		jpSeasonPanel.add(buttonPanel, BorderLayout.SOUTH);
		addSeasonView.add(jpSeasonPanel);
		addSeasonView.pack();
		addSeasonView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addSeasonView.setVisible(true);

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
	 * Accessors below
	 * @return
	 */
	public JButton getJBTOK(){
		return this.jbtOK;
	}
	
	public JButton getJBTCancel(){
		return this.jbtCancel;
	}
	
	public JButton getJBTSelectYear(){
		return this.jbtSelectYear;
	}
	
	public JList<City> getJLSelectedCity(){
		return this.jlSelectedCity;
	}
	
	public JList<Person> getJLSelectedPlayers(){
		return this.jlSelectedPlayers;
	}
	
	public void setTeam(Team team){
		this.team = team;
	}
	
	public Team getTeam(){
		return this.team;
	}
	/**
	 * Mutator.
	 * @param isVisible
	 */
	public void setAsVisible(boolean isVisible) {
		if (isVisible == true)
			this.setVisible(true);
		else{
			this.setVisible(false);
			jlSelectedCity.clearSelection();
			jlSelectedPlayers.clearSelection();
		}
	}
}
