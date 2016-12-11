package ie.gmit.sw;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

public class SearchController {
	// local objects
		private DAO dao;
		private ArrayList<Garage> searchedVehicles;
 		
		/* Constructor that creates instance of DAO to communicate with database and manipulate data */
		public SearchController() {

			try {
				dao = new DAO();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/* Getter for  ArrayLists of Searched Vehicles */
		public ArrayList<Garage> getSearchedVehicles() {
			return searchedVehicles;
		}
		
		
		/* Loading Data from DAO */
		public void loadSearchedVehicles(Garage vehicle){
			try {
				searchedVehicles = dao.getSearchedVehicleDetails(vehicle);
			}catch (CommunicationsException e){
				FacesMessage message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message); 
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
