package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.EstacionDAO;
import Entidades.Estacion;
import Gestores.GestorEstacion;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Formatter;
import java.util.List;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameSeleccionarEstacion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	private  DefaultTableModel dm = new DefaultTableModel();
	private  JTable table = new JTable();
	
	private List<Estacion> estaciones;

	private static FrameSeleccionarEstacion _INSTANCE;	
	
	public static FrameSeleccionarEstacion getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE= new FrameSeleccionarEstacion();
		}
		try {
			_INSTANCE.estaciones=GestorEstacion.get_estaciones_de_alta();
			_INSTANCE.cargar_estaciones(_INSTANCE.estaciones);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _INSTANCE;
	}

	/**
	 * Create the frame.
	 */
	public FrameSeleccionarEstacion() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 533, 379);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setUndecorated(true);
		
		dm.addColumn("Código");
		dm.addColumn("Nombre");
		
		table.setModel(dm);
		JScrollPane scp_tramos = new JScrollPane(table);
		scp_tramos.setBounds(10, 81, 498, 233);
		contentPane.add(scp_tramos);
		
		textField = new JTextField();
		textField.setBounds(165, 51, 266, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Filtrar");
		btnNewButton.setBounds(430, 51, 78, 26);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Filtre por Nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 51, 208, 26);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Seleccionar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(272, 326, 132, 36);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Cancelar");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameSeleccionarEstacion.getInstance().setVisible(false);

			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_1.setBounds(86, 325, 132, 36);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione una estaci\u00F3n");
		lblNewLabel_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 11, 525, 20);
		contentPane.add(lblNewLabel_1);
		
		
	}
	
	
	public void cargar_estaciones (List<Estacion> estaciones) {
		if (table.getRowCount() >0) {
		table.removeRowSelectionInterval(0, table.getRowCount()-1);
		}
		
		int tam = estaciones.size();
		int cantidad_columnas= table.getColumnCount();
		
		if(cantidad_columnas !=0) {
			dm= new DefaultTableModel();
			table.setModel(dm);
	
		}
		
		if(tam !=0) {
			
		int i=0;
		
		long id;
		String nombre;
		
		Object[]col0 = new Object[tam];
		Object[]col1= new Object[tam];
		
		while(i<tam) {
			
			id= estaciones.get(i).getId_estacion();
			nombre = estaciones.get(i).getNombre();
			
			
			col0[i]= id;
			col1[i]= nombre;
			i++;
			
		}
		
		dm.addColumn("Código", col0);
		dm.addColumn("Nombre", col1);
		
		
		}
	}

}
