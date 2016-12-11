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
	
	
	public ArrayList<Garage> getSearchedVehicleDetails(Garage vehicle) throws SQLException{
		ArrayList<Garage> vehicles = new ArrayList<>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select v.reg, manuf.manu_code, manuf.manu_name, model.model_code,"
													   + " model.model_name, v.mileage, v.price, v.colour, v.fuel"
													   + "FROM vehicle v LEFT JOIN model model"
													   + "ON v.model_code = model.model_code"
													   + "LEFT JOIN manufacturer manuf"
													   + "ON v.manu_code = manuf.manu_code"
													   + "WHERE v.fuel IN(?)");
		ResultSet rs = myStmt.executeQuery();
		
		while(rs.next()) {
			String reg = rs.getString("reg");
			String manu_code = rs.getString("manu_code");
			String manu_name = rs.getString("manu_name");
			String model_code = rs.getString("model_code");
			String model_name = rs.getString("model_name");
			int mileage = rs.getInt("mileage");
			double price = rs.getDouble("price");
			String colour = rs.getString("colour");
			String fuel = rs.getString("fuel");
			
			vehicles.add(new Garage(reg, manu_code, manu_name, model_code, model_name, mileage, price, colour, fuel));
		}
		
		return vehicles;
	}

	public Garage findUpdatingManufacturer(Garage manufacturer) {
			
			Garage manufacturers = null;
			
			try
			{
			Connection conn = mysqlDS.getConnection();
			PreparedStatement myStmt = conn.prepareStatement("Select * from manufacturer where manu_code = ?");
			myStmt.setString(1, manufacturer.getManu_code());
			ResultSet rs = myStmt.executeQuery();
	
			while (rs.next()) 
			{
				manufacturers = new Garage(rs.getString("manu_code"), rs.getString("manu_name"), rs.getString("manu_details"));
			}
			conn.close();
			myStmt.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return manufacturers;
		}

	public void updateManufacturer(Garage manufacturer) throws SQLException {
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("Update manufacturer"
				+ " Set manu_name = ?,"
				+ " manu_details = ?"
				+ " where manu_code = ?");
	
		myStmt.setString(1, manufacturer.getManu_name());
		myStmt.setString(2, manufacturer.getManu_details());
		myStmt.setString(3, manufacturer.getManu_code());
	
		myStmt.executeUpdate();
		
		conn.close();
		myStmt.close();
	}

	public void deleteManufacturer(Garage manufacturer) throws SQLException{
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("delete from manufacturer where manu_code = ?");
		
		myStmt.setString(1, manufacturer.getManu_code());
		
		myStmt.executeUpdate();
		
		conn.close();
		myStmt.close();
	}

	public Garage findVehicle(Garage v) {
		Garage vehicle = null;
		
		try
		{
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select v.reg, manuf.manu_code, manuf.manu_name, manuf.manu_details, "
													   + "model.model_code, model.model_name, model.model_desc, v.mileage, v.price, "
													   + "v.colour, v.fuel from vehicle v LEFT JOIN manufacturer manuf  "
													   + "ON v.manu_code = manuf.manu_code "
													   + "LEFT JOIN model model "
													   + "ON v.model_code = model.model_code "
													   + "where v.reg  = ?");
		myStmt.setString(1, v.getReg());
		ResultSet rs = myStmt.executeQuery();

		while (rs.next()) 
		{
			String reg = rs.getString("reg");
			String manu_code = rs.getString("manu_code");
			String manu_name = rs.getString("manu_name");
			String manu_details = rs.getString("manu_details");
			String model_code = rs.getString("model_code");
			String model_name = rs.getString("model_name");
			String model_desc = rs.getString("model_desc");
			int mileage = rs.getInt("mileage");
			double price = rs.getDouble("price");
			String colour = rs.getString("colour");
			String fuel = rs.getString("fuel");
			
			System.out.println(reg);
			vehicle = new Garage(reg, manu_code, manu_name, manu_details, model_code, 
								 model_name, model_desc, mileage, price, colour, fuel);
		}
		conn.close();
		myStmt.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return vehicle;
	}
}