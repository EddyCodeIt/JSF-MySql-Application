package ie.gmit.sw;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

@SessionScoped
@ManagedBean

public class SearchController {
	// local objects
		private DAO dao;
		private ArrayList<Garage> searchedVehicles;
		private Garage vehicle;
 		
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
		
		public Garage getVehicle() {
			return vehicle;
		}

		public void setVehicle(Garage vehicle) {
			this.vehicle = vehicle;
		}
		
		
		/* Capture object from view */
		public String vehiclesToFind(Garage conditions){
			setVehicle(conditions);
			return "found-vehicles";
		}
		/* Loading Data from DAO */
		public void loadSearchedVehicles(){
			try {
				searchedVehicles = dao.getSearchedVehicleDetails(getVehicle());
			}catch (CommunicationsException e){
				FacesMessage message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message); 
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
