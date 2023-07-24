package gui;

import java.util.ArrayList;

import clases.Empleado;
import clases.EmpleadosDAO;

public class pruebaDAO {

	public static void main(String[] args)
	{
		System.out.println("Probando DAO");
		EmpleadosDAO daoEmp = new EmpleadosDAO();

		ArrayList<Empleado> listaEmpleados = daoEmp.getEmpleados();
		System.out.println(listaEmpleados);
	}

}
