import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Project 5
 * CS 2334 - Section 010
 * 4/29/15
 */
public class DeleteView extends JFrame{

	private static final long serialVersionUID = 1L;
	private TeamMateModel model;
	private JLabel jlConfirmDelete;
	private JScrollPane jspDelete;
	private ArrayList<Object> deleteArrayList = new ArrayList<Object>();
	private JList<Object> deleteList;
	private JFrame frame;
	private JPanel panel;
	private JPanel buttonPanel = new JPanel(new GridLayout(1,2));
	private JButton jbtYes = new JButton("Yes, Delete");
	private JButton jbtNo = new JButton("No, Cancel");
	
	/**
	 * Main Constructor.
	 * @param objects
	 */
	public DeleteView(ArrayList<Object> objects){ //where objects is obtained via jlist.getSelectedValuesList();
		deleteArrayList = objects; 

		frame = new JFrame("Confirm Delete");
		panel = new JPanel(new BorderLayout());
		jlConfirmDelete = new JLabel("Are you sure you want to delete the following object(s)? ");

		deleteList = new JList<Object>(deleteArrayList.toArray()); //puts objects from the array list into the JList
		jspDelete = new JScrollPane(deleteList);

		jbtYes.setActionCommand("Yes");
		jbtNo.setActionCommand("No");
		buttonPanel.add(jbtYes);
		buttonPanel.add(jbtNo);
		panel.add(jlConfirmDelete, BorderLayout.NORTH);
		panel.add(jspDelete, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Secondary Constructor
	 */
	public DeleteView() 
	{
		
	}

	/**
	 * Removes all instances of the data that the user has selected and confirmed to delete.
	 * No return type.
	 */
	public void delete(){
		ArrayList<Object> toDelete = this.getDeleteArrayList();
		ArrayList<Person> people = this.model.getPeople();
		ArrayList<City> cities = this.model.getCities();
		ArrayList<Team> teams = this.model.getTeams();

		for(int i=0; i < people.size(); i++){

			for (int j=0; j < toDelete.size(); j++){
				if (toDelete.get(j).equals(people.get(i))){
					people.remove(i);
					i++; //increment twice because size was decreased as well
				}
			}
		}

		for(int k=0; k < cities.size(); k++){

			for (int m=0; m < toDelete.size(); m++){
				if (toDelete.get(m).equals(cities.get(k))){
					cities.remove(k);
					k++; //increment twice because size was decreased as well
				}
			}
		}

		for(int n=0; n < teams.size(); n++){

			for (int p=0; p < toDelete.size(); p++){
				if (toDelete.get(p).equals(people.get(n))){
					teams.remove(n);
					n++; //increment twice because size was decreased as well
				}
			}
		}
		toDelete.clear(); 
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
	 * Accessor - returns the array list of objects of type Person, City, and/or Team that the user 
	 * has selected to delete.
	 * @return	list of objects to be deleted
	 */
	public ArrayList<Object> getDeleteArrayList(){
		return this.deleteArrayList;
	}

	/**
	 * Accessor for Yes button
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTYes(){
		return this.jbtYes;
	}

	/**
	 * Accessor for No button
	 * @return the JButton that can be pressed by the user
	 */
	public JButton getJBTNo(){
		return this.jbtNo;
	}
	
	/**
	 * Sets visible if visible is true, if not, clears the selection.
	 * @param visible
	 */
	public void setAsVisible(boolean visible){
		if(visible == true)
			this.setVisible(true);
		else{
			this.setVisible(false);
			deleteList.clearSelection();
		}
	}

}
