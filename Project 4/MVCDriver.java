/**  Project 4
 *   CS 2334 - Section 010
 *   4/20/15
 *	 Driver class
 */

public class MVCDriver {
	
	static TeamMateModel theModel = new TeamMateModel();
	static SelectionView theSelectionView;
	static TeamMateController theController = new TeamMateController();
	//static AddSeasonView theAddSeasonView = new AddSeasonView();
	//static SelectStateView theSelectStateView = new SelectStateView();
	//static DeleteView theDeleteView = new DeleteView();
	//static EditView theEditView = new EditView();
	//static SelectCityView theSelectCityView = new SelectCityView();
	//static AddView theAddView = new AddView();

	/**
	 * Main method. Sets up MVC.
	 * @param args
	 */
	public static void main(String[] args) {
		theSelectionView = new SelectionView(theModel);
		theSelectionView.setVisible(true);
		theController.setModel(theModel);
		theController.setSelectionView(theSelectionView);
		//theAddSeasonView.setModel(theModel);
		
	}
}
