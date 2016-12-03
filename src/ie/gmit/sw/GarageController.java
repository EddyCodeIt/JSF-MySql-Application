package ie.gmit.sw;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class GarageController {
	
	private ArrayList<Garage> manufacturers;
	private DAO dao;
	
	public GarageController() {

		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Garage> getManufacturers() {
		return manufacturers;
	}
	
	public void loadManufacturers() throws Exception{
		manufacturers = dao.getManufacturerDetails();
	}
	
}
