package ie.gmit.sw;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@ManagedBean
public class GarageController {
	
	// local objects
	private DAO dao;
	private ArrayList<Garage> manufacturers;
	private ArrayList<Garage> models;
	private ArrayList<Garage> vehicles;
	
	/* Constructor that creates instance of DAO to communicate with database and manipulate data */
	public GarageController() {

		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Getting ArrayLists */
	public ArrayList<Garage> getManufacturers() {
		return manufacturers;
	}
	
	public ArrayList<Garage> getModels(){
		return models;
	}
	
	public ArrayList<Garage> getVehicles(){
		return vehicles;
	}
	
	/* Loading Data from DAO */
	public void loadManufacturers() throws Exception{
		manufacturers = dao.getManufacturerDetails();
	}
	
	public void loadModels() throws Exception{
		models = dao.getModelDetails();
	}
	
	public void loadVehicles() throws Exception{
		vehicles = dao.getVehicleDetails();
	}
	
	
	/* Data Manipulations */
	
	public void addManufacturer(Garage manufacturer) {
		try {
			dao.addManufacturer(manufacturer);
			FacesMessage okMessage = new FacesMessage("OK: Manufacturer Sucessfully Added!");
			FacesContext.getCurrentInstance().addMessage(null, okMessage);
			
			/* internal redirection */
			FacesContext.getCurrentInstance().getExternalContext().redirect("manage-manufacturers.xhtml");
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	} // END of addManufacturer()
	
	public void addModel(Garage model) {
		try {
			dao.addModel(model);
			FacesMessage okMessage = new FacesMessage("OK: Model Sucessfully Added!");
			FacesContext.getCurrentInstance().addMessage(null, okMessage);
			
			/* internal redirection */
			FacesContext.getCurrentInstance().getExternalContext().redirect("manage-models.xhtml");
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	} // END of addManufacturer()
	
	public void addVehicle(Garage vehicle) {
		try {
			dao.addVehicle(vehicle);
			FacesMessage okMessage = new FacesMessage("OK: Vehicle Sucessfully Added!");
			FacesContext.getCurrentInstance().addMessage(null, okMessage);
			
			/* internal redirection */
			FacesContext.getCurrentInstance().getExternalContext().redirect("manage-vehicles.xhtml");
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	} // END of addManufacturer()
	
	
	
	
	
	
	
	
	
	
}
