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
		
		/* Create query */
		public String preparedQuery(){
			
			
			String price = String.valueOf(vehicle.getPrice());
			String query = "select v.reg, manuf.manu_code, manuf.manu_name, model.model_code, "
					     + "model.model_name, v.mileage, v.price, v.colour, v.fuel "
					     + "FROM vehicle v LEFT JOIN model model "
					     + "ON v.model_code = model.model_code "
					     + "LEFT JOIN manufacturer manuf "
					     + "ON v.manu_code = manuf.manu_code "
					     + "WHERE v.fuel IN('"+ vehicle.getFuel() +"')";
			
			if(!(vehicle.getColour().isEmpty())){
				query += " AND colour IN('" + vehicle.getColour() + "')";
			}
			
			if(vehicle.getPrice() != 0){
				switch(vehicle.getPriceRange()){
					case ">": query += " AND price > " + price;
							  break;
					case "<": query += " AND price < " + price;
					  		  break;
					case "=": query += " AND price = " + price;
					  		  break;
				}
			}
			
			
			return query;
		}
		
		/* Loading Data from DAO */
		public void loadSearchedVehicles(){
			try {
				searchedVehicles = dao.getSearchedVehicleDetails(preparedQuery());
			}catch (CommunicationsException e){
				FacesMessage message = new FacesMessage(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message); 
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
