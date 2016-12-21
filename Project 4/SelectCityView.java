import java.awt.GridLayout;
import javax.swing.*;

/**
 * Project 4
 * CS 2334 - Section 010
 * 4/20/15
 *
 */
public class SelectCityView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JList<City> jlSelectedCity;
	private JPanel buttonPanel = new JPanel();
	private JButton jbtOK = new JButton("OK");
	private JButton jbtCancel = new JButton("Cancel");
	private TeamMateModel model;

	/**
	 * Main Constructor. Sets up everything for select city view
	 */
	public SelectCityView(){
		City[] cityArray = null; 
		
		JFrame cityFrame = new JFrame("Select City");
		JPanel cityPanel = new JPanel(new GridLayout(2,1));
		jlSelectedCity = new JList<City>(model.getCities().toArray(cityArray)); 
		jlSelectedCity.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jspCities = new JScrollPane(jlSelectedCity);
		jbtOK.setActionCommand("OK");
		jbtCancel.setActionCommand("Cancel");
		buttonPanel.add(jbtOK);
		buttonPanel.add(jbtCancel);
		cityPanel.add(jspCities);
		cityPanel.add(buttonPanel);
		cityFrame.add(cityPanel);
		cityFrame.pack();
		cityFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cityFrame.setVisible(true);
	}
	
	/**
	 * Accessor.
	 * @return
	 */
	public City getJLSelectedCity(){
		return this.jlSelectedCity.getSelectedValue();
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
	 * if visible is true, it sets select city view visible. If not, it clears the selection.
	 * @param visible
	 */
	public void setAsVisible(boolean visible){
		if(visible == true)
			this.setVisible(true);
		else
		{
			this.setVisible(false);
			jlSelectedCity.clearSelection();
		}
	}
	
}
