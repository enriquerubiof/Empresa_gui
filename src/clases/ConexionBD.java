package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConexionBD
{
	// datos de la conexión
	private static String database="empresa";
	private static String user="enrique";
	private static String pass="1234";
	private static String url="jdbc:mysql://localhost:3306/" + database;

	// Objeto Connection
	private Connection conexion = null; 

	public Connection getConexion()
	{
		
		//Si la conexión ya se ha creado anteriormente, devuelve la ya creada
		if (conexion != null)
		{
			return this.conexion;
		}
		try
		{
			// REgistramos el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Pedir un objeto Connection
			this.conexion = DriverManager.getConnection(url, user, pass);
			System.out.println("Conexión a BD realizada correctamente");
			
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("No se ha podido registrar el driver."
					+ "Consulte con el adminsitrador");
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			System.out.println("No se ha podido conectar. " + e.getMessage());
			e.printStackTrace();
		}
		return this.conexion;
	}

	public void desconectar()
	{
		try
		{
			this.conexion.close();
		}
		catch (SQLException e)
		{
			System.out.println("Error liberando la conexión.");
			e.printStackTrace();
		}
	}
}