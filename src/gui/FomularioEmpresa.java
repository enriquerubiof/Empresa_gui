package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import clases.ConexionBD;
import clases.Empleado;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FomularioEmpresa extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private ConexionBD conexion;
	private Connection con;
	private String query;

	private ArrayList <Empleado> listaEmpleados;
	private int codEmpleado;
	private int codDepartamento;
	private int telefono;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIngreso;
	private int salario;
	private double comision;
	private int numHijos;
	private String nombre;
	private Empleado empleado;
	
	private java.sql.Statement stmt;
	private ResultSet rs;
	int eliminado;
	private DefaultTableModel modelo;
	private Empleado e;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FomularioEmpresa frame = new FomularioEmpresa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FomularioEmpresa()
	{
		listaEmpleados = new ArrayList <Empleado>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow,fill][:600:800,grow][:600:800,grow][grow,fill]", "[grow,fill][40.00][grow][46.00][grow,fill]"));
		
		JLabel lblNewLabel = new JLabel("Lista de Empleados");
		lblNewLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 64, 128));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(lblNewLabel, "cell 1 1 2 1,growx");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(0, 64, 128));
		scrollPane.setBackground(new Color(235, 215, 255));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.add(scrollPane, "cell 1 2 2 1,grow");
		
		table = new JTable();
		table.setSelectionBackground(new Color(0, 0, 160));
		table.setBackground(new Color(235, 215, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cod. Empl.", "Cod. Dep.", "Tel\u00E9fono", "Fecha Nac.", "Fecha Ingr.", "Salario", "Comisi\u00F3n", "N\u00FAm. hijos", "Nombre"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, Integer.class, String.class, String.class, Integer.class, Double.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(66);
		table.getColumnModel().getColumn(1).setPreferredWidth(65);
		table.getColumnModel().getColumn(2).setPreferredWidth(58);
		table.getColumnModel().getColumn(3).setPreferredWidth(67);
		table.getColumnModel().getColumn(4).setPreferredWidth(71);
		table.getColumnModel().getColumn(5).setPreferredWidth(49);
		table.getColumnModel().getColumn(6).setPreferredWidth(57);
		table.getColumnModel().getColumn(7).setPreferredWidth(63);
		table.getColumnModel().getColumn(8).setPreferredWidth(80);
		table.setAlignmentX(Component.LEFT_ALIGNMENT);
		table.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Mostrar mpleados");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					conectarBD("mostrar");
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				rellenarTabla();
			}
		});
		contentPane.add(btnNewButton, "cell 1 3,alignx center");
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					conectarBD("eliminar");
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				rellenarTabla();
			}
		});
		contentPane.add(btnNewButton_1, "cell 2 3,alignx center");
	}

	private void conectarBD(String accion) throws SQLException
	{
		conexion = new ConexionBD();
		con = conexion.getConexion();
		stmt = null;
		rs = null;
		
		con.setCatalog("empresa");
		stmt = con.createStatement();

		// REcoge el modelo de la tabla con sus filas y columnas
		modelo = (DefaultTableModel) table.getModel();
				
		switch (accion)
		{
			case "mostrar":
				guardarEmpleados();
				break;
			case "eliminar":
				eliminar();
				break;
		}
		stmt.close();
		conexion.desconectar();
	}

	private void guardarEmpleados() throws SQLException
	{
		query = "SELECT * FROM empleados;";
		rs = stmt.executeQuery(query);
		while (rs.next())
		{
			codEmpleado = rs.getInt("cod_empleado");
			codDepartamento = rs.getInt("cod_departamento");
			telefono = rs.getInt("telefono");
			fechaNacimiento = LocalDate.parse(rs.getString("fecha_nacimiento"));
			fechaIngreso = LocalDate.parse(rs.getString("fecha_ingreso"));
			salario = rs.getInt("salario");
			comision = rs.getDouble("comision");
			numHijos = rs.getInt("num_Hijos");
			nombre = rs.getString("nombre");
			
			empleado = new Empleado(codEmpleado, codDepartamento, telefono,
					fechaNacimiento, fechaIngreso, salario, comision, numHijos, nombre);
			listaEmpleados.add(empleado);
		}
		rs.close();
	}

	private void rellenarTabla()
	{
		// vacía la JTable
		modelo.setRowCount(0);
		
		// recorremos la lista de personas
		for (Empleado empleado : listaEmpleados) {
			// componemos una fila de la tabla
			Object fila [] = {
				empleado.getCodEmpleado(),
				empleado.getCodDepartamento(),
				empleado.getTelefono(),
				empleado.getFechaNacimiento(),
				empleado.getFechaIngreso(),
				empleado.getSalario(),
				empleado.getComision(),
				empleado.getNumHijos(),
				empleado.getNombre(),
			};
			
			// añade la fila al modelo
			modelo.addRow(fila);
		}	
	}

	protected void eliminar() throws SQLException
	{
		// tomamos el numero de fila seleccionada
		int seleccionada = table.getSelectedRow();
		
		if (seleccionada==-1)
		{
			// no hay ninguna seleccionada
			JOptionPane.showMessageDialog(contentPane, 
					"Debe seleccionar una persona a borrar",
					"No seleccionado", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		e = new Empleado();
		//tomamos el dni de la tabla, de la fila seleccionada y la columna 0 que es el dni
		e.setCodEmpleado((int) modelo.getValueAt(seleccionada, 0));
		
		
		if (!this.listaEmpleados.contains(e)) {
			JOptionPane.showMessageDialog(contentPane, 
					"No existe ninguna persona con ese DNI",
					"Dni no encontrado", JOptionPane.ERROR_MESSAGE);
			return;
		}
		e = this.listaEmpleados.get(this.listaEmpleados.indexOf(e));
		this.listaEmpleados.remove(this.listaEmpleados.indexOf(e));

		query = "DELETE FROM empleados"
				+ " WHERE cod_empleado = " + e.getCodEmpleado() + ";";
		stmt.executeUpdate(query);

		JOptionPane.showMessageDialog(contentPane, 
			"Empleado eliminado:\n" + e, "Borrado", 
			JOptionPane.INFORMATION_MESSAGE);

		// vacía la JTable
		modelo.setRowCount(0);

	}
}
