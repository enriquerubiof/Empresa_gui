package clases;

import java.time.LocalDate;
import java.util.Objects;

public class Empleado
{
	private int codEmpleado;
	private int codDepartamento;
	private int telefono;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIngreso;
	private double salario;
	private double comision;
	private int numHijos;
	private String nombre;

	public Empleado()
	{
		this.setCodEmpleado(0);
		this.setCodDepartamento(0);
		this.setTelefono(0);
		this.setFechaNacimiento(null);
		this.setFechaIngreso(null);
		this.setSalario(0);
		this.setComision(0);
		this.setNumHijos(0);
		this.setNombre("");
	}

	public Empleado(int codEmpleado, int codDepartamento, int telefono, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, double salario, double comision, int numHijos, String nombre)
	{
		this.setCodEmpleado(codEmpleado);
		this.setCodDepartamento(codDepartamento);
		this.setTelefono(telefono);
		this.setFechaNacimiento(fechaNacimiento);
		this.setFechaIngreso(fechaIngreso);
		this.setSalario(salario);
		this.setComision(comision);
		this.setNumHijos(numHijos);
		this.setNombre(nombre);
	}

	public Empleado(int codEmpleado, int codDepartamento, int telefono,
			int diaNacimiento, int mesNacimiento, int anioNacimiento, 
			int diaIngreso, int mesIngreso, int anioIngreso,
			double salario, double comision, int numHijos, String nombre)
	{
		this.setCodEmpleado(codEmpleado);
		this.setCodDepartamento(codDepartamento);
		this.setTelefono(telefono);
		this.setFechaNacimiento(LocalDate.of(anioNacimiento, mesNacimiento, diaNacimiento));
		this.setFechaIngreso(LocalDate.of(anioIngreso, mesIngreso, diaIngreso));
		this.setSalario(salario);
		this.setComision(comision);
		this.setNumHijos(numHijos);
		this.setNombre(nombre);
	}
	
	public int getCodEmpleado() {
		return codEmpleado;
	}
	public void setCodEmpleado(int codEmpleado) {
		this.codEmpleado = codEmpleado;
	}
	public int getCodDepartamento() {
		return codDepartamento;
	}
	public void setCodDepartamento(int codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getFechaNacimiento() {
		return String.valueOf(this.fechaNacimiento);
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getFechaIngreso() {
		return String.valueOf(this.fechaIngreso);
	}
	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public double getComision() {
		return comision;
	}
	public void setComision(double comision) {
		this.comision = comision;
	}
	public int getNumHijos() {
		return numHijos;
	}
	public void setNumHijos(int numHijos) {
		this.numHijos = numHijos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Empleado\n"
				+ "[codEmpleado: " + codEmpleado
				+ "\ncodDepartamento: " + codDepartamento
				+ "\ntelefono: " + telefono
				+ "\nfechaNacimiento: " + fechaNacimiento
				+ "\nfechaIngreso: " + fechaIngreso
				+ "\nsalario: " + salario
				+ "\ncomision: " + comision
				+ "\nnumHijos: " + numHijos
				+ "\nnombre: " + nombre + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codEmpleado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return codEmpleado == other.codEmpleado;
	}
	
	
}
