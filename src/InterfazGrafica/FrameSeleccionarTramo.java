package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.TrayectoDAO;
import Entidades.Tramo;
import Gestores.GestorEstacion;
import Gestores.GestorTrayecto;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameSeleccionarTramo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private  DefaultTableModel dm = new DefaultTableModel();
	private  JTable table = new JTable();

	private static FrameSeleccionarTramo _INSTANCE;	
	
	public static FrameSeleccionarTramo getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE= new FrameSeleccionarTramo();
			
		}
		return _INSTANCE;
	}

	
	public FrameSeleccionarTramo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 373);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dm.addColumn("Código");
		dm.addColumn("id_Origen");
		dm.addColumn("id_Destino");
		dm.addColumn("Origen");
		dm.addColumn("Destino");
		dm.addColumn("Distancia");
		dm.addColumn("Duracion");
		dm.addColumn("CantidadPasajeros");
		dm.addColumn("Costo");
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table.setModel(dm);
		
		table.getColumnModel().getColumn(2).setMaxWidth(0);
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(2).setPreferredWidth(0);
		table.doLayout();
		
		table.getColumnModel().getColumn(1).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setMinWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(0);
		table.doLayout();
		JScrollPane scp_tramos = new JScrollPane(table);
		scp_tramos.setBounds(10, 70, 564, 207);
		contentPane.add(scp_tramos);
		
		textField = new JTextField();
		textField.setBounds(280, 40, 204, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Filtrar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(494, 40, 80, 26);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Filtre por c\u00F3digo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 40, 284, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione un tramo");
		lblNewLabel_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 11, 564, 18);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Seleccionar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(317, 288, 136, 26);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Cancelar");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameSeleccionarTramo.getInstance().setVisible(false);
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_1.setBounds(115, 288, 136, 26);
		contentPane.add(btnNewButton_1_1);
		
		try {
			cargar_tramos(GestorTrayecto.get_all_tramos());
		} catch (Exception e) {			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void cargar_tramos(List<Tramo> t) {
		
		if (table.getRowCount() >0) {
			table.removeRowSelectionInterval(0, table.getRowCount()-1);
			}
			
			int tam = t.size();
			int cantidad_columnas= table.getColumnCount();
			
			if(cantidad_columnas !=0) {
				dm= new DefaultTableModel();
				table.setModel(dm);
		
			}
			
			if(tam !=0) {
				
			int i=0;
			
			int id;
			int id_o;
			int id_d;
			String origen;
			String destino;
			float distancia;
			int duracion;
			int cant_pas;
			float costo; 
			
			Object[]col0 = new Object[tam];
			Object[]col1= new Object[tam];
			Object[]col2 = new Object[tam];
			Object[]col3 = new Object[tam];
			Object[]col4 = new Object[tam];
			Object[]col5 = new Object[tam];
			Object[]col6 = new Object[tam];
			Object[]col7 = new Object[tam];
			Object[]col8 = new Object[tam];
			
			while(i<tam) {
				
				id= t.get(i).getId();
				id_o = t.get(i).getEstacion_origen().getId_estacion();
				id_d = t.get(i).getEstacion_destino().getId_estacion();
				origen= t.get(i).getEstacion_origen().getNombre();
				destino= t.get(i).getEstacion_destino().getNombre();
				distancia= t.get(i).getDistancia_km();
				duracion= t.get(i).getDuracion();
				cant_pas= t.get(i).getCant_max_pasajeros();
				costo= t.get(i).getCosto();
				
				
				col0[i]= id;
				col1[i]= id_o;
				col2[i]= id_d;
				col3[i]= origen;
				col4[i]= destino;
				col5[i]= distancia;
				col6[i]= duracion;
				col7[i]= cant_pas;
				col8[i]= costo;
				i++;
				
			}
			
			dm.addColumn("Código", col0);
			dm.addColumn("id_Origen", col1);
			dm.addColumn("id_Destino", col2);
			dm.addColumn("Origen", col3);
			dm.addColumn("Destino", col4);
			dm.addColumn("Distancia", col5);
			dm.addColumn("Duracion", col6);
			dm.addColumn("CantidadPasajeros", col7);
			dm.addColumn("Costo", col8);
			
			table.getColumnModel().getColumn(2).setMaxWidth(0);
			table.getColumnModel().getColumn(2).setMinWidth(0);
			table.getColumnModel().getColumn(2).setPreferredWidth(0);
			table.doLayout();
			
			table.getColumnModel().getColumn(1).setMaxWidth(0);
			table.getColumnModel().getColumn(1).setMinWidth(0);
			table.getColumnModel().getColumn(1).setPreferredWidth(0);
			table.doLayout();
			
			
			}
	}
}
