package DAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import Entidades.ConsultaGenerica;

public class Conexion {

	
	private  static String server= "motty.db.elephantsql.com";
	private  static String port= "5432";
	private  static String BDD= "chkefqkd";
	private  static String user= "chkefqkd";
	private  static String password= "mqmgOgGuZLWxjN5AH1l1_9Fr7AA1dx5j";
	
	private  static String connectionUrl= "jdbc:postgresql://" + server + ":" + port + "/" + BDD;
		
	public static Connection connection;
	
	private static BasicDataSource dataSource = null;
	 
    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(connectionUrl);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
 
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(25);
        
        connection= conectarBD();
 
    }
 
	public static Connection conectarBD () {
		 try {
			try {
	            Class.forName("org.postgresql.Driver");
	        } catch (ClassNotFoundException ex) {
	            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
	        }
			
			 Connection connection = null;
			//connection = DriverManager.getConnection(connectionUrl,user, password);
			 connection = dataSource.getConnection();
			 System.out.println("CONEXION EXITOSA");
			return connection;
		} catch (SQLException e) {
			 System.out.println("Error al conectar con la base de datos de PostgreSQL (" + connectionUrl + "): " + e);
			 return null;
		
		}
		
		
	}
	
	//Metodo que envia una consulta a la BDD 
	
		public static ArrayList<Object> consultar(String query, Class<? extends Object> ob) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
			ArrayList<Object> result = new ArrayList<Object>();
		    Statement statement = null;
	        ResultSet rs = null; 
	        
	        statement = connection.createStatement();
	        connection.setAutoCommit(false);
	        statement.setFetchSize(100000);
	        rs = statement.executeQuery(query);
	        try {
	        	 String objeto;	 
	        while (rs.next()) {
	        	ResultSetMetaData md = rs.getMetaData();
	        	objeto = "";
	            if(ob == ConsultaGenerica.class){
	                for(int i=1; i<=md.getColumnCount(); i++){
	                    objeto += md.getColumnName(i) + " -- " + rs.getString(i) + "\t";
	                }
	            }
	            else{
	            	int i=1;
	            	int r = md.getColumnCount();
	            	while(i<=r)
	                {
	                    objeto += rs.getString(i) + "\t";
	                    i++;
	                }
	            }
	            Object obj = ob.getConstructor(String.class).newInstance(objeto);
	            result.add(obj);
	        }
	   
	        
	        }
	            finally {
	            	 
	                rs.close();
	                statement.close();
	              
	            }
			return result;
	    }
		
		// Método que ejecuta una acción en la BDD (insertar, eliminar etc.)
		
		public static void ejecutar(String query) throws SQLException{
			 
		        try {
		            connection.createStatement().executeUpdate(query);
		        } catch (Exception ex) {
		            throw ex;
		        }
		      
	    }
		



}
