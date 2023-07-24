package clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;
/**
 * Clase que gestiona todos los accesos a la base de datos
 */
public class EmpleadosDAO
{
	private ConexionBD conexion;

	private Connection con;
	private java.sql.Statement stmt;
	private ResultSet rs;
	
	/**
	 * Constructor de la clase que instancia la conexión que usará cada método
	 */
	public EmpleadosDAO()
	{
		this.conexion = new ConexionBD();
	}

	public void setConexion(ConexionBD conexion)
	{
		this.conexion = conexion;
	}
	
	public ArrayList <Empleado> getEmpleados()
	{
		ArrayList <Empleado> lista = new ArrayList <Empleado>();
		
		//Realizar la conexión
		con = this.conexion.getConexion();
		
		String consulta = "SELECT * FROM empleados;";
		
		try {
			//Creamos el objeto Statement con el que se pueden lanzar consultas
			stmt = con.createStatement();
			//Ejecutamos la consulta y recogemos el resultset
			rs = stmt.executeQuery(consulta);
			
			//Bucle para recorrer todo el resultset, el cursor con los resultados
			//Next devuelve true mientras haya datos, false si no hay más
			while (rs.next())
			{
				int codEmpleado = rs.getInt("cod_empleado");
				int codDepartamento = rs.getInt("cod_departamento");
				int telefono = rs.getInt("telefono");
				LocalDate fechaNacimiento = LocalDate.parse(rs.getString("fecha_nacimiento"));
				LocalDate fechaIngreso = LocalDate.parse(rs.getString("fecha_ingreso"));
				double salario = rs.getDouble("salario");
				double comision = rs.getDouble("comision");
				int numHijos = rs.getInt("num_Hijos");
				String nombre = rs.getString("nombre");
				
				//Instancioamos un objeto empleado por cada línea
				lista.add(new Empleado(codEmpleado, codDepartamento, telefono,
				fechaNacimiento, fechaIngreso, salario, comision, numHijos, nombre));
			}
		} catch (SQLException e)
		{
			System.out.println("Error en la consulta");
		}
		finally
		{
			try
			{
				rs.close();
				stmt.close();
				conexion.desconectar();
			}
			catch (SQLException e)
			{
				System.out.println("Error liberando recursos");
			}
		}
		return lista;
		
	}
}
