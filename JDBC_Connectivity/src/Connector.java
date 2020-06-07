import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Connector {
	public Connector() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS userData(id int NOT NULL AUTO_INCREMENT, username varchar(255),password varchar(255),PRIMARY KEY(id))");
			create.executeUpdate();
			System.out.println("Table created");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public static void insert(String userName, String password) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement insert = con.prepareStatement("INSERT INTO userData(username,password)VALUES('"+userName+"','"+password+"')");
			insert.executeUpdate();
			System.out.println("Inserted");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public static ArrayList<String> select() throws Exception {
		try {
			ArrayList<String> array= new ArrayList<String>();
			Connection con = getConnection();
			PreparedStatement select = con.prepareStatement("SELECT username FROM userData");
			ResultSet result = select.executeQuery();
			while(result.next()) {
				System.out.println(result.getString("username"));
				array.add(result.getString("username"));
			}
			return array;
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;		
	}
	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "root";
			String password = "";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,user,password);
			System.out.println("Connected");
			return con; 
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
