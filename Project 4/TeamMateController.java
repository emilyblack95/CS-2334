import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Project 4
 * CS 2334 - Section 010
 * 4/20/15
 *
 */
public class TeamMateController 
{
	private TeamMateModel model;
	private SelectionView selectionView;
	private AddView addView;
	private EditView editView;
	private DeleteView deleteView;
	private SelectCityView selectCityView;
	private SelectStateView selectStateView;
	private SelectYearView selectYearView;
	private AddSeasonView addSeasonView;



	/**
	 * Constructor
	 */
	public TeamMateController(){
		
	}

	/**
	 * Mutator.
	 * @param model
	 */
	public void setModel(TeamMateModel model) { 
		this.model = model;
	}
	
	/**
	 * Mutator.
	 * @param selectionView
	 */
	public void setSelectionView(SelectionView selectionView){
		this.selectionView = selectionView;

		this.selectionView.getJBTAddPlace().addActionListener(new SelectionViewAddPlaceButtonListener());
		this.selectionView.getJBTAddPerson().addActionListener(new SelectionViewAddPersonButtonListener());
		this.selectionView.getJBTAddTeam().addActionListener(new SelectionViewAddTeamButtonListener());

		this.selectionView.getJBTEditPlace().addActionListener(new SelectionViewEditPlaceButtonListener());
		this.selectionView.getJBTEditPerson().addActionListener(new SelectionViewEditPersonButtonListener());
		this.selectionView.getJBTEditTeam().addActionListener(new SelectionViewEditTeamButtonListener());

		this.selectionView.getJBTDeletePlace().addActionListener(new SelectionViewDeletePlaceButtonListener());
		this.selectionView.getJBTDeletePerson().addActionListener(new SelectionViewDeletePersonButtonListener());
		this.selectionView.getJBTDeleteTeam().addActionListener(new SelectionViewDeleteTeamButtonListener());

		this.selectionView.getJMILoadTeamMateData().addActionListener(new SelectionViewLoadTeamMateDataMenuItemListener());
		this.selectionView.getJMISaveTeamMateData().addActionListener(new SelectionViewSaveTeamMateDataMenuItemListener());
		this.selectionView.getJMIImportTeamMateData().addActionListener(new SelectionViewImportTeamMateDataMenuItemListener());
		this.selectionView.getJMIExportTeamMateData().addActionListener(new SelectionViewExportTeamMateDataMenuItemListener());

		this.selectionView.getJMIPieChart().addActionListener(new SelectionViewPieChartMenuItemListener());
		this.selectionView.getJMIMap().addActionListener(new SelectionViewMapMenuItemListener());
	}

	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectionViewAddPlaceButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			new AddView("Add Place", model);
			selectionView.getJBTEditPlace().setEnabled(true);
			selectionView.getJBTDeletePlace().setEnabled(true);
			selectionView.getJMIImportTeamMateData().setEnabled(true);
			selectionView.getJMIExportTeamMateData().setEnabled(true);

		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectionViewAddPersonButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			new AddView("Add Person", model);
			selectionView.getJBTEditPerson().setEnabled(true);
			selectionView.getJBTDeletePerson().setEnabled(true);
			selectionView.getJMIImportTeamMateData().setEnabled(true);
			selectionView.getJMIExportTeamMateData().setEnabled(true);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectionViewAddTeamButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			new AddView("Add Team", model);
			selectionView.getJBTEditTeam().setEnabled(true);
			selectionView.getJBTDeleteTeam().setEnabled(true);
			selectionView.getJMIImportTeamMateData().setEnabled(true);
			selectionView.getJMIExportTeamMateData().setEnabled(true);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectionViewEditPlaceButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			new EditView((City) actionEvent.getSource(), model);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectionViewEditPersonButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			new EditView((Person) actionEvent.getSource(), model);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectionViewEditTeamButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			new EditView((Team) actionEvent.getSource(), model);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectionViewDeletePlaceButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			ArrayList<Object> deleteList = new ArrayList<Object>();
			
			deleteList.addAll(selectionView.getPersonList().getSelectedValuesList());
			deleteList.addAll(selectionView.getTeamList().getSelectedValuesList());
			deleteList.addAll(selectionView.getPlaceList().getSelectedValuesList());

			new DeleteView(deleteList);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectionViewDeletePersonButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			ArrayList<Object> deleteList = new ArrayList<Object>();
			
			deleteList.addAll(selectionView.getPersonList().getSelectedValuesList());
			deleteList.addAll(selectionView.getTeamList().getSelectedValuesList());
			deleteList.addAll(selectionView.getPlaceList().getSelectedValuesList());

			new DeleteView(deleteList);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectionViewDeleteTeamButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			ArrayList<Object> deleteList = new ArrayList<Object>();
			
			deleteList.addAll(selectionView.getPersonList().getSelectedValuesList());
			deleteList.addAll(selectionView.getTeamList().getSelectedValuesList());
			deleteList.addAll(selectionView.getPlaceList().getSelectedValuesList());

			new DeleteView(deleteList);		
		}
	}
	/**
	 * Menu Button object that extend ActionListener. Has actionPerformed method.
	 * This class asks for file names if they are loading in data from a file.
	 *
	 */
	private class SelectionViewLoadTeamMateDataMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {

			boolean fileFound = false;
			boolean fileFound1 = false;
			boolean fileFound2 = false;

			while (fileFound == false)
			{
				System.out.println();
				//JOptionPane start
				String citiesFileName = JOptionPane.showInputDialog(null, "Please enter the name of the file containing cities (Example: cities.txt): ", "Load TeamMate Data", 
						JOptionPane.PLAIN_MESSAGE); 
				//JOptionPane end
				try
				{
					model.fillCityArrayList(citiesFileName); 
					fileFound = true;
				} catch (IOException FileNotFoundException) //catches bad input if the name of the file isn't found
				{ 
					JOptionPane.showMessageDialog(null, "Error: File not found.", "Load TeamMate Data", JOptionPane.WARNING_MESSAGE); 
					fileFound = false;
				}
			}
			while (fileFound1 == false)
			{
				//JOptionPane start
				String playersFileName = JOptionPane.showInputDialog(null, "Please enter the name of the file containing players (Example: players.txt): ", "Load TeamMate Data", 
						JOptionPane.PLAIN_MESSAGE);
				//JOptionPane end
				System.out.println("");
				try
				{
					model.fillArrayList(playersFileName);
					fileFound1 = true;
				} catch (IOException FileNotFoundException)  //catches bad input if the name of the file isn't found
				{ 
					JOptionPane.showMessageDialog(null, "Error: File not found.", "Load TeamMate Data", JOptionPane.WARNING_MESSAGE); 
					fileFound1 = false;
				}
			}
			while (fileFound2 == false)
			{
				//JOptionPane start
				String teamsFileName = JOptionPane.showInputDialog(null, "Please enter the name of the file containing teams (Example: teams.txt): ", "Load TeamMate Data", 
						JOptionPane.PLAIN_MESSAGE);
				//JOptionPane end
				try
				{
					model.fillTeamMap(teamsFileName); 
					fileFound2 = true;
				} catch (IOException FileNotFoundException)  //catches bad input if the name of the file isn't found
				{ 
					JOptionPane.showMessageDialog(null, "Error: File not found.", "Load TeamMate Data", JOptionPane.WARNING_MESSAGE); 
					fileFound2 = false;
				}
			}
			selectionView.setVisible(false);
			selectionView = new SelectionView(model); //restarts view to update with new data
			
			selectionView.getJBTEditPlace().setEnabled(true);
			selectionView.getJBTDeletePlace().setEnabled(true);
			selectionView.getJBTEditPerson().setEnabled(true);
			selectionView.getJBTDeletePerson().setEnabled(true);
			selectionView.getJBTEditTeam().setEnabled(true);
			selectionView.getJBTDeleteTeam().setEnabled(true);
			selectionView.getJMIImportTeamMateData().setEnabled(true);
			selectionView.getJMIExportTeamMateData().setEnabled(true);
			if (selectionView.getTeamList().getSelectedValuesList().size() == 1
					&& selectionView.getPersonList().getSelectedValuesList().size() == 0
					&& selectionView.getPlaceList().getSelectedValuesList().size() == 0 ) //only one team selectedin JList, nothing else
				selectionView.getJMIPieChart().setEnabled(true);
			else if (selectionView.getPersonList().getSelectedValuesList().size() > 0 &&
					selectionView.getPlaceList().getSelectedValuesList().size() == 0 &&
						selectionView.getTeamList().getSelectedValuesList().size() == 0) //only person objectsa are selected, nothing else
				selectionView.getJMIMap().setEnabled(true);
			
			
		}
		
	}
	/**
	 * Menu Button object that extend ActionListener. Has actionPerformed method.
	 * Saves data into a file
	 *
	 */
	private class SelectionViewSaveTeamMateDataMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			ArrayList<Object> writeOutList = new ArrayList<Object>();

			//add all of the objects currently selected by the user to save

			try{
				if ((selectionView.getPersonList().getSelectedValuesList() != null) 
						|| (selectionView.getTeamList().getSelectedValuesList() != null)
						|| (selectionView.getPlaceList().getSelectedValuesList() != null)) //if there is data somewhere
				{ //only writes out the types that contain data
					if (selectionView.getPersonList().getSelectedValuesList() != null)
						writeOutList.addAll(selectionView.getPersonList().getSelectedValuesList());
					if (selectionView.getTeamList().getSelectedValuesList() != null)
						writeOutList.addAll(selectionView.getTeamList().getSelectedValuesList());
					if (selectionView.getPlaceList().getSelectedValuesList() != null)
						writeOutList.addAll(selectionView.getPlaceList().getSelectedValuesList());
				}
				else //don't write anything out if there's no more data
					JOptionPane.showMessageDialog(null, "ERROR: no data to write out.", "Save TeamMate Data", JOptionPane.WARNING_MESSAGE); 
			}
			catch (NullPointerException e){ //catches a lack of data if the compiler misses it
				JOptionPane.showMessageDialog(null, "ERROR: no data to write out.", "Save TeamMate Data", JOptionPane.WARNING_MESSAGE); 
			}
			String fileName = JOptionPane.showInputDialog(null, "Please enter file name: ", "Save TeamMate Data", JOptionPane.INFORMATION_MESSAGE);
			try {
				model.writeOut(writeOutList, fileName);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error writing out file.", "Save TeamMate Data", JOptionPane.WARNING_MESSAGE); 
			}
		}
	}
	/**
	 * Menu Button object that extend ActionListener. Has actionPerformed method.
	 * Imports data.
	 *
	 */
	private class SelectionViewImportTeamMateDataMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			boolean fileFound = false;
			boolean fileFound1 = false;
			boolean fileFound2 = false;

			while (fileFound == false)
			{
				System.out.println();
				//JOptionPane start
				String citiesFileName = JOptionPane.showInputDialog(null, "Please enter the name of the file containing cities (Example: cities.txt): ", "Import TeamMate Data", 
						JOptionPane.PLAIN_MESSAGE); 
				//JOptionPane end
				try
				{
					model.fillCityArrayList(citiesFileName); 
					fileFound = true;
				} catch (IOException FileNotFoundException) //catches bad input if the name of the file isn't found
				{ 
					JOptionPane.showMessageDialog(null, "Error: File not found.", "Import TeamMate Data", JOptionPane.WARNING_MESSAGE); 
					fileFound = false;
				}
			}
			while (fileFound1 == false)
			{
				//JOptionPane start
				String playersFileName = JOptionPane.showInputDialog(null, "Please enter the name of the file containing players (Example: players.txt): ", "Import TeamMate Data", 
						JOptionPane.PLAIN_MESSAGE);
				//JOptionPane end
				System.out.println("");
				try
				{
					model.fillArrayList(playersFileName);
					fileFound1 = true;
				} catch (IOException FileNotFoundException)  //catches bad input if the name of the file isn't found
				{ 
					JOptionPane.showMessageDialog(null, "Error: File not found.", "Import TeamMate Data", JOptionPane.WARNING_MESSAGE); 
					fileFound1 = false;
				}
			}
			while (fileFound2 == false)
			{
				//JOptionPane start
				String teamsFileName = JOptionPane.showInputDialog(null, "Please enter the name of the file containing teams (Example: teams.txt): ", "Import TeamMate Data", 
						JOptionPane.PLAIN_MESSAGE);
				//JOptionPane end
				try
				{
					model.fillTeamMap(teamsFileName); 
					fileFound2 = true;
				} catch (IOException FileNotFoundException)  //catches bad input if the name of the file isn't found
				{ 
					JOptionPane.showMessageDialog(null, "Error: File not found.", "Import TeamMate Data", JOptionPane.WARNING_MESSAGE); 
					fileFound2 = false;
				}
				
				selectionView.getJBTEditPlace().setEnabled(true);
				selectionView.getJBTDeletePlace().setEnabled(true);
				selectionView.getJBTEditPerson().setEnabled(true);
				selectionView.getJBTDeletePerson().setEnabled(true);
				selectionView.getJBTEditTeam().setEnabled(true);
				selectionView.getJBTDeleteTeam().setEnabled(true);
				selectionView.getJMIImportTeamMateData().setEnabled(true);
				selectionView.getJMIExportTeamMateData().setEnabled(true);
				if (selectionView.getTeamList().getSelectedValuesList().size() == 1
						&& selectionView.getPersonList().getSelectedValuesList().size() == 0
						&& selectionView.getPlaceList().getSelectedValuesList().size() == 0 ) //only one team selectedin JList, nothing else
					selectionView.getJMIPieChart().setEnabled(true);
				else if (selectionView.getPersonList().getSelectedValuesList().size() > 0 &&
						selectionView.getPlaceList().getSelectedValuesList().size() == 0 &&
							selectionView.getTeamList().getSelectedValuesList().size() == 0) //only person objectsa are selected, nothing else
					selectionView.getJMIMap().setEnabled(true);
			}
			selectionView.setVisible(false);
			selectionView = new SelectionView(model); //restarts view to update with new data
		}
	}
	/**
	 * Menu Button object that extend ActionListener. Has actionPerformed method.
	 * Exports data.
	 */
	private class SelectionViewExportTeamMateDataMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: export teammate data - to text file (HOW?)
			ArrayList<Object> writeOutList = new ArrayList<Object>();

			//add all of the objects currently selected by the user to save

			try{
				if ((selectionView.getPersonList().getSelectedValuesList() != null) 
						|| (selectionView.getTeamList().getSelectedValuesList() != null)
						|| (selectionView.getPlaceList().getSelectedValuesList() != null)) //if there is data somewhere
				{ //only writes out the types that contain data
					if (selectionView.getPersonList().getSelectedValuesList() != null)
						writeOutList.addAll(selectionView.getPersonList().getSelectedValuesList());
					if (selectionView.getTeamList().getSelectedValuesList() != null)
						writeOutList.addAll(selectionView.getTeamList().getSelectedValuesList());
					if (selectionView.getPlaceList().getSelectedValuesList() != null)
						writeOutList.addAll(selectionView.getPlaceList().getSelectedValuesList());
				}
				else //don't write anything out if there's no more data
					JOptionPane.showMessageDialog(null, "ERROR: no data to write out.", "Export TeamMate Data", JOptionPane.WARNING_MESSAGE); 
			}
			catch (NullPointerException e){ //catches a lack of data if the compiler misses it
				JOptionPane.showMessageDialog(null, "ERROR: no data to write out.", "Export TeamMate Data", JOptionPane.WARNING_MESSAGE); 
			}
			String fileName = JOptionPane.showInputDialog(null, "Please enter file name: ", "Export TeamMate Data", JOptionPane.INFORMATION_MESSAGE);
			try {
				model.writeOut(writeOutList, fileName);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error writing out file.", "Export TeamMate Data", JOptionPane.WARNING_MESSAGE); 
			}
		}
	}
	/**
	 * Pie chart Button object that extend ActionListener. Has actionPerformed method.
	 * Graphs a pie chart of the data user has picked.
	 *
	 */
	private class SelectionViewPieChartMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			Team graphTeam = selectionView.getTeamList().getSelectedValue();
			ArrayList<Integer> graphAges = new ArrayList<Integer>();
			ArrayList<Season> seasons = graphTeam.getSeasons();
			for (int i = 0; i < seasons.size(); i++){
				ArrayList<Person> seasonRoster = seasons.get(i).getSeasonRoster();
				for (int j=0; j < seasonRoster.size(); j++){
					int age = seasonRoster.get(j).calculateCurrentAge();
					graphAges.add(age);
				}
			}
			PieChart pieChart = new PieChart(graphTeam.getState().toString(), graphAges);
			pieChart.setVisible(true);
		}
	}
	/**
	 * Map Button object that extend ActionListener. Has actionPerformed method.
	 * Graphs a map of the data user has picked.
	 *
	 */
	private class SelectionViewMapMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			ArrayList<Person> selectedPeople = (ArrayList<Person>) selectionView.getPersonList().getSelectedValuesList();
			model.displayMap(selectedPeople, selectionView);
		}
	}
	/**
	 * Mutator.
	 * @param addView
	 */
	public void setAddView(AddView addView){
		this.addView = addView;

		this.addView.getJBTOK().addActionListener(new AddViewOKButtonListener());
		this.addView.getJBTCancel().addActionListener(new AddViewCancelButtonListener());
		this.addView.getJBTSelectState().addActionListener(new AddViewSelectStateButtonListener());
		this.addView.getJBTSelectCity().addActionListener(new AddViewSelectCityButtonListener());

	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class AddViewOKButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			addView.addToModel();
			//TODO: insert OK button commands - launch addSeasonView if team was added
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class AddViewCancelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			getAddView().setAsVisible(false);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class AddViewSelectStateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: pull up StateScrollPane
			selectStateView.setAsVisible(true);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class AddViewSelectCityButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: pull up CityScrollPane
			selectCityView.setAsVisible(true);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class AddViewAddSeasonButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			addSeasonView.setAsVisible(true);
		}
	}

	/**
	 * Mutator.
	 * @param editView
	 */
	public void setEditView(EditView editView){
		this.editView = editView;

		this.editView.getJBTOK().addActionListener(new EditViewOKButtonListener());
		this.editView.getJBTCancel().addActionListener(new EditViewCancelButtonListener());
		this.editView.getJBTEditSeason().addActionListener(new EditViewAddSeasonButtonListener());
		this.editView.getJBTSelectState().addActionListener(new EditViewSelectStateButtonListener());
		this.editView.getJBTSelectCity().addActionListener(new EditViewSelectCityButtonListener());
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class EditViewOKButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: insert OK button commands
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class EditViewCancelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			editView.setAsVisible(false);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class EditViewAddSeasonButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: pull up AddSeasonView
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class EditViewSelectStateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: pull up StateScrollPane
			selectStateView.setAsVisible(true);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class EditViewSelectCityButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: pull up CityScrollPane
			selectCityView.setAsVisible(true);
		}
	} 
	/**
	 * Mutator.
	 * @param addSeason
	 */
	public void setAddSeasonView(AddSeasonView addSeason){
		this.addSeasonView = addSeason;

		this.addSeasonView.getJBTOK().addActionListener(new AddSeasonViewOKButtonListener());
		this.addSeasonView.getJBTCancel().addActionListener(new AddSeasonViewCancelButtonListener());
		this.addSeasonView.getJBTSelectYear().addActionListener(new AddSeasonViewSelectYearButtonListener());
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class AddSeasonViewOKButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: OK button stuff 
			addSeasonView.setAsVisible(false);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class AddSeasonViewCancelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: set as invisible
			addSeasonView.setTeam((Team) actionEvent.getSource());
			addSeasonView.setAsVisible(false);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class AddSeasonViewSelectYearButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: launch selectYearView
			selectYearView.setTeam((Team) actionEvent.getSource());
			selectYearView.setAsVisible(true);
		}
	}
	/**
	 * Mutator.
	 * @param selectYearView
	 */
	public void setSelectYearView(SelectYearView selectYearView){
		this.selectYearView = selectYearView;

		this.selectYearView.getJBTOK().addActionListener(new SelectYearViewOKButtonListener());
		this.selectYearView.getJBTCancel().addActionListener(new SelectYearViewCancelButtonListener());
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectYearViewOKButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: OK button stuff
			selectYearView.setAsVisible(false);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectYearViewCancelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: Cancel button stuff
			selectYearView.getJLSelectedYear().clearSelection(); 
			selectYearView.setAsVisible(false);
		}
	}
	/**
	 * Mutator.
	 * @param deleteView
	 */
	public void setDeleteView(DeleteView deleteView){
		this.deleteView = deleteView;

		this.deleteView.getJBTYes().addActionListener(new DeleteViewYesButtonListener());
		this.deleteView.getJBTNo().addActionListener(new DeleteViewNoButtonListener());
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class DeleteViewYesButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			deleteView.delete();
			deleteView.setAsVisible(false);
			deleteView.setVisible(false);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class DeleteViewNoButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			deleteView.setAsVisible(false);
		}
	}
	/**
	 * Mutator.
	 * @param selectCityView
	 */
	public void setSelectCityView(SelectCityView selectCityView){
		this.selectCityView = selectCityView;

		this.selectCityView.getJBTOK().addActionListener(new SelectCityViewOKButtonListener());
		this.selectCityView.getJBTCancel().addActionListener(new SelectCityViewCancelButtonListener());
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectCityViewOKButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: exit the screen and save the user's city selection
			City selectedCity = selectCityView.getJLSelectedCity();
			//TODO: what do I do with it?
			selectCityView.setAsVisible(false);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectCityViewCancelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			selectCityView.setAsVisible(false);
		}
	}
	/**
	 * Mutator.
	 * @param selectStateView
	 */
	public void setSelectStateView(SelectStateView selectStateView){
		this.selectStateView = selectStateView;

		this.selectStateView.getJBTOK().addActionListener(new SelectStateViewOKButtonListener());
		this.selectStateView.getJBTCancel().addActionListener(new SelectStateViewCancelButtonListener());
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectStateViewOKButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			//TODO: exit the screen and save the user's state selection
			State selected = selectStateView.getJLSelectedState();
			//TODO: What else do I do here?
			selectStateView.setAsVisible(false);
		}
	}
	/**
	 * Button object that extend ActionListener. Has actionPerformed method.
	 *
	 */
	private class SelectStateViewCancelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			selectStateView.setAsVisible(false);
		}
	}

	

	/**
	 * Listeners for controller
	 * 
	 *
	 */
//	private class MenuSaveListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Listeners for controller
	 * 
	 *
	 */
//	private class MenuLoadListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Listeners for controller
	 * 
	 *
	 */
//	private class MenuExitListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Listeners for controller
	 * 
	 *
	 */


//	private class SortSearchInputListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Listeners for controller
	 * 
	 *
	 */
//	private class SortFirstLastInputListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Listeners for controller
	 * 
	 *
	 */
//	private class SearchExactPartialInputListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Listeners for controller
	 * 
	 *
	 */
//	private class PlaceCityStateInputListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Listeners for controller
	 * 
	 *
	 */
//	private class TeamNameInputListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Listeners for controller
	 * 
	 *
	 */
//	private class SaveSkipInputListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Listeners for controller
	 * 
	 *
	 */
//	private class FileNameInputListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Listeners for controller
	 * 
	 *
	 */
//	private class GraphContinueExitListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Listeners for controller
	 * 
	 *
	 */
//	private class GraphAgeLocationInputListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Listeners for controller
	 * 
	 *
	 */
//	private class ContinueExitInputListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//
//		}
//	}

	/**
	 * Returns the controller's addView variable so it can be accessed in private classes
	 * 
	 *
	 */
	public AddView getAddView(){
		return this.addView;
	}

}