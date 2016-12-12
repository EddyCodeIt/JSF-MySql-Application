package ie.gmit.sw;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

@SessionScoped
@ManagedBean

public class GarageController {
	// local objects
	private DAO dao;
	
	private Garage vehicle;
	private Garage current_vehicle;
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
	
	public Garage getVehicle() {
		return vehicle;
	}

	public void setVehicle(Garage vehicle) {
		this.vehicle = vehicle;
	}
	
	public Garage getCurrent_vehicle() {
		return current_vehicle;
	}

	public void setCurrent_vehicle(Garage current_vehicle) {
		this.current_vehicle = current_vehicle;
	}

	/* Loading Data from DAO */
	public void loadManufacturers(){
		try {
			manufacturers = dao.getManufacturerDetails();
		}catch (CommunicationsException e){
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message); 
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadModels() throws Exception{
		try {
			models = dao.getModelDetails();
		}catch (CommunicationsException e){
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message); 
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadVehicles() throws Exception{
		try {
			vehicles = dao.getVehicleDetails();
		}catch (CommunicationsException e){
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message); 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
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
		}catch (MysqlDataTruncation e){ 
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (CommunicationsException e){
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
		}catch (MysqlDataTruncation e){ 
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (CommunicationsException e){
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
		}catch (MysqlDataTruncation e){ 
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (CommunicationsException e){
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (Exception e) {
			
			e.printStackTrace();
		}		
	} // END of addManufacturer()

	// delete manufacturer
	public void deleteManufacturer(Garage manufacturer){
		try{
			dao.deleteManufacturer(manufacturer);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("manage-manufacturers.xhtml");
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/*** FULL VEHICLE DETAILS ***/
	
	public String sendToVehicleDetails(Garage current_vehicle){
		setCurrent_vehicle(current_vehicle);
		return "show-vehicle";
	}
	
	public void loadVehicleDetails() throws Exception {
		try
		{
			vehicle = dao.findVehicle(getCurrent_vehicle());			
		} 
		catch (Exception e) 
		{
			e.getMessage();
		}
	}
}
