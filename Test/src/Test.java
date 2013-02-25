import java.sql.*;  
public class Test {

	/**
	 * @author 庄辉
	 */
	private String dbURL = "jdbc:odbc:MyODBC";
	private String user = "zhuang0";
	private String password = "123";
	public Test(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection(dbURL, user, password);
			System.out.println(con.getCatalog());
			System.out.println("连接成功");
			con.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Test();

	}

}
