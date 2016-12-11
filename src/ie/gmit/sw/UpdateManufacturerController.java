package ie.gmit.sw;

import java.util.Map;

import javax.faces.application.FacesMessage;
/* http://stackoverflow.com/questions/7031885/how-to-choose-the-right-bean-scope */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


@ManagedBean
@SessionScoped 
@ViewScoped
public class UpdateManufacturerController {

    
	// local objects
	private DAO dao;
	
	/* Constructor that creates instance of DAO to communicate with database and manipulate data */
	public UpdateManufacturerController() {

		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String loadUpdatingManufacturer(Garage manufacturer) throws Exception {
		
		try
		{
			Garage manu_fac = dao.findUpdatingManufacturer(manufacturer);
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("garage", manu_fac);
		} 
		catch (Exception e) 
		{
			e.getMessage();
			return null;
		}
		return "update-manufacturer";
	}
	
	public String updateManufacturer(Garage manufacturer){
		
		try {
			dao.updateManufacturer(manufacturer);
			return "manage-manufacturers";
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
