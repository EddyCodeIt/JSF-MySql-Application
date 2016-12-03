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

}