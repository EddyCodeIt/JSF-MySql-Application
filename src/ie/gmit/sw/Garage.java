package ie.gmit.sw;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Garage {
	private String manu_code;
	private String manu_name;
	private String manu_details;
	private String model_code;
	private String model_name;
	private String model_desc;
	private String reg;
	private int mileage;
	private double price;
	private String colour;
	private enum fuel {PETROL, DIESEL}
	
	public Garage() {
		super();
	}

	
	public Garage(String manu_code, String manu_name, String manu_details) {
		super();
		this.manu_code = manu_code;
		this.manu_name = manu_name;
		this.manu_details = manu_details;
	}

	public String getManu_code() {
		return manu_code;
	}

	public void setManu_code(String manu_code) {
		this.manu_code = manu_code;
	}

	public String getManu_name() {
		return manu_name;
	}

	public void setManu_name(String manu_name) {
		this.manu_name = manu_name;
	}

	public String getManu_details() {
		return manu_details;
	}

	public void setManu_details(String manu_details) {
		this.manu_details = manu_details;
	}

	public String getModel_code() {
		return model_code;
	}

	public void setModel_code(String model_code) {
		this.model_code = model_code;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getModel_desc() {
		return model_desc;
	}

	public void setModel_desc(String model_desc) {
		this.model_desc = model_desc;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	};
	
	
	
}
