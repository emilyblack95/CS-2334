import java.awt.*;
import javax.swing.*;

/**
 * Project 5
 * CS 2334 - Section 010
 * 4/29/15
 * This class creates the SELECTSTATEVIEW BOX. 
 * OBJECT MUST BE CREATED SOMEWHERE ELSE
 */
public class SelectStateView extends JFrame
{
	private static final long serialVersionUID = 1477290803959030275L;
	private JPanel buttonPanel = new JPanel();
	private JButton jbtOK = new JButton("OK");
	private JButton jbtCancel = new JButton("Cancel");
	private TeamMateModel model;
	private JList<State> jlSelectedState;
	
	/**
	 * Main Constructor. Sets everything up for select state view.
	 */
	public SelectStateView()
	{
		State[] stateArray = null;
		
		JFrame stateFrame = new JFrame("Select State");
		JPanel statePanel = new JPanel(new GridLayout(2,1));
		jlSelectedState = new JList<State>(model.getStates().toArray(stateArray)); 
		jlSelectedState.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jspStates = new JScrollPane(jlSelectedState);
		jbtOK.setActionCommand("OK");
		jbtCancel.setActionCommand("Cancel");
		buttonPanel.add(jbtOK);
		buttonPanel.add(jbtCancel);
		statePanel.add(jspStates);
		statePanel.add(buttonPanel);
		stateFrame.add(statePanel);
		stateFrame.pack();
		stateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stateFrame.setVisible(true);
	}
	
	/**
	 * Accessor.
	 * @return
	 */
	public State getJLSelectedState()
	{
		return this.jlSelectedState.getSelectedValue();
	}
	
	/**
	 * Accessor for OK button
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTOK()
	{
		return this.jbtOK;
	}
	
	/**
	 * Accessor for Cancel button
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTCancel()
	{
		return this.jbtCancel;
	}
	
	/**
	 * Sets visible if visible is true, if not, clears the selection.
	 * @param visible
	 */
	public void setAsVisible(boolean visible)
	{
		if(visible == true)
			this.setVisible(true);
		else
		{
			this.setVisible(false);
			jlSelectedState.clearSelection();
		}
	}

}
