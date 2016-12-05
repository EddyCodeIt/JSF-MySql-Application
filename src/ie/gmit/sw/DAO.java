package ie.gmit.sw;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

	// mysqlDS object 
	private DataSource mysqlDS;

	// DAO() constructor
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/garage"; 
		mysqlDS = (DataSource) context.lookup(jndiName);
	}

	/* Query Database for _____ details */
	public ArrayList<Garage> getManufacturerDetails() throws SQLException{
		
		// create new array list  to store data from obtained from DB. Later we return this ArrayList
		ArrayList<Garage> manufacturers = new ArrayList<>();
		
		// connect to database and prepare statement 
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from manufacturer");
		ResultSet rs = myStmt.executeQuery();
		
		while (rs.next()) {

			String manu_code = rs.getString("manu_code");
			String manu_name = rs.getString("manu_name");
			String manu_details = rs.getString("manu_details");

			manufacturers.add(new Garage(manu_code, manu_name, manu_details));
		}
		
		return manufacturers;
	}
	
	public ArrayList<Garage> getModelDetails() throws SQLException{
		ArrayList<Garage> models = new ArrayList<>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from model");
		ResultSet rs = myStmt.executeQuery();
		
		while(rs.next()) {
			String manu_code = rs.getString("manu_code");
			String model_code = rs.getString("model_code");
			String model_name = rs.getString("model_name");
			String model_desc = rs.getString("model_desc");
			
			models.add(new Garage(manu_code, model_code, model_name, model_desc));
		}
		
		return models;
	}
	
	public ArrayList<Garage> getVehicleDetails() throws SQLException{
		ArrayList<Garage> vehicles = new ArrayList<>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from vehicle");
		ResultSet rs = myStmt.executeQuery();
		
		while(rs.next()) {
			String reg = rs.getString("reg");
			String manu_code = rs.getString("manu_code");
			String model_code = rs.getString("model_code");
			int mileage = rs.getInt("mileage");
			double price = rs.getDouble("price");
			String colour = rs.getString("colour");
			String fuel = rs.getString("fuel");
			
			vehicles.add(new Garage(reg, manu_code, model_code, mileage, price, colour, fuel));
		}
		
		return vehicles;
	}
	
	public void addManufacturer(Garage manufacturer) throws SQLException{
		// connect to database and prepare statement 
		Connection conn;
			conn = mysqlDS.getConnection();
			PreparedStatement myStmt = conn.prepareStatement("INSERT INTO manufacturer values (?, ?, ?)");
			
			myStmt.setString(1, manufacturer.getManu_code());
			myStmt.setString(2, manufacturer.getManu_name());
			myStmt.setString(3, manufacturer.getManu_details());
			
			myStmt.executeUpdate();
	}// END of addManufcaturer
	
	public void addModel(Garage model) throws SQLException{
		// connect to database and prepare statement 
		Connection conn;
		conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO model values (?, ?, ?, ?)");
		
		myStmt.setString(1, model.getManu_code());
		myStmt.setString(2, model.getModel_code());
		myStmt.setString(3, model.getModel_name());
		myStmt.setString(4, model.getModel_desc());
		
		myStmt.executeUpdate();
	}// END of addModel

	public void addVehicle(Garage vehicle) throws SQLException{
		// connect to database and prepare statement 
		Connection conn;
		conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO vehicle values (?, ?, ?, ?, ?, ?, ?)");
		
		myStmt.setString(1, vehicle.getReg());
		myStmt.setString(2, vehicle.getManu_code());
		myStmt.setString(3, vehicle.getModel_code());
		myStmt.setInt(4, vehicle.getMileage());
		myStmt.setDouble(5, vehicle.getPrice());
		myStmt.setString(6, vehicle.getColour());
		myStmt.setString(7, vehicle.getFuel());
		
		myStmt.executeUpdate();
	}// END of addVehicle
}