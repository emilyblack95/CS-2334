import java.awt.*;
import javax.swing.*;

/**
 * Project 4
 * CS 2334 - Section 010
 * 4/20/15
 *
 */
public class SelectYearView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Team team;
	private JPanel buttonPanel = new JPanel();
	private JButton jbtOK = new JButton("OK");
	private JButton jbtCancel = new JButton("Cancel");
	//private TeamMateModel model;
	private JList<Integer> jlSelectedYear;
	
	/**
	 * Main Constructor.
	 */
	public SelectYearView(){
		Integer[] yearArray = null;
		
		JFrame stateFrame = new JFrame("Select State");
		JPanel statePanel = new JPanel(new GridLayout(2,1));
		jlSelectedYear = new JList<Integer>(team.getAvailableYears().toArray(yearArray)); 
		jlSelectedYear.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jspYears = new JScrollPane(jlSelectedYear);
		jbtOK.setActionCommand("OK");
		jbtCancel.setActionCommand("Cancel");
		buttonPanel.add(jbtOK);
		buttonPanel.add(jbtCancel);
		statePanel.add(jspYears);
		statePanel.add(buttonPanel);
		stateFrame.add(statePanel);
		stateFrame.pack();
		stateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stateFrame.setVisible(true);
	}
	
	/**
	 * Mutator
	 * @param team
	 */
	public void setTeam(Team team){
		this.team = team;
	}
	
	/**
	 * Accessor.
	 * @return
	 */
	public JList<Integer> getJLSelectedYear(){
		return this.jlSelectedYear;
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
	 * Sets visible if visible is true, else clears the selection.
	 * @param visible
	 */
	public void setAsVisible(boolean visible){
		if(visible == true)
			this.setVisible(true);
		else{
			this.setVisible(false);
			jlSelectedYear.clearSelection();
		}
	}

}
