package InterfazGrafica;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Entidades.Estacion;
import Entidades.Tramo;
import Entidades.Trayecto;
import Gestores.GestorEstacion;
import Gestores.GestorTrayecto;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PntFlujoMaximo extends JPanel {

	private JComboBox cb_destino = new JComboBox();
	private JComboBox cb_origen = new JComboBox();
	private List<Estacion> estaciones_actuales = new ArrayList();
	
	private  DefaultTableModel dm1 = new DefaultTableModel();
	private  JTable table1 = new JTable();
	private  DefaultTableModel dm2 = new DefaultTableModel();
	private  JTable table2 = new JTable();


	/**
	 * Create the panel.
	 */
	public PntFlujoMaximo() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Flujo M\u00E1ximo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 0, 713, 28);
		add(lblNewLabel);
		
		cb_origen.setBounds(20, 66, 240, 28);
		add(cb_origen);
		
		JLabel lblNewLabel_1 = new JLabel("Estaci\u00F3n Origen");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_1.setBounds(20, 39, 201, 23);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Estaci\u00F3n Destino");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_2.setBounds(301, 39, 236, 23);
		add(lblNewLabel_2);
		
		cb_destino.setBounds(301, 66, 240, 28);
		add(cb_destino);
		
		JButton btn_flujoMax = new JButton("Flujo M\u00E1ximo");
		btn_flujoMax.setForeground(new Color(0, 0, 128));
		btn_flujoMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cb_origen.getSelectedIndex() != cb_destino.getSelectedIndex()) {
					try {
						mostrar_flujo_maximo();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else {
					VentanaAdmin.mensajeError("El origen debe ser distinto al destino", "Error");
				}
			}
		});
		btn_flujoMax.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_flujoMax.setBounds(574, 66, 148, 28);
		add(btn_flujoMax);
		
		dm1.addColumn("id_trayecto");
		dm1.addColumn("Trayecto");
		dm1.addColumn("Flujo Máximo");
		
		table1.setModel(dm1);
		
		table1.getColumnModel().getColumn(0).setMaxWidth(0);
		table1.getColumnModel().getColumn(0).setMinWidth(0);
		table1.getColumnModel().getColumn(0).setPreferredWidth(0);
		table1.doLayout();
		
		JScrollPane scp_trayectos = new JScrollPane(table1);
		scp_trayectos.setBounds(20, 116, 702, 108);
		add(scp_trayectos);
		
		dm2.addColumn("id_tramo");
		dm2.addColumn("Origen");
		dm2.addColumn("Destino");
		dm2.addColumn("Duracion");
		dm2.addColumn("Costo");
		dm2.addColumn("Distancia");
		dm2.addColumn("Cantidad Max Pasajeros");
		
		table2.setModel(dm2);
		
		table2.getColumnModel().getColumn(0).setMaxWidth(0);
		table2.getColumnModel().getColumn(0).setMinWidth(0);
		table2.getColumnModel().getColumn(0).setPreferredWidth(0);
		table2.doLayout();
		
		JScrollPane scp_tramos_1 = new JScrollPane(table2);
		scp_tramos_1.setBounds(21, 264, 702, 116);
		add(scp_tramos_1);
		
		JLabel lblNewLabel_3 = new JLabel("Detalle");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(20, 239, 703, 23);
		add(lblNewLabel_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 251, 313, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(410, 251, 313, 2);
		add(separator_1);
		
		JButton btn_detalle = new JButton("Ver Detalle");
		btn_detalle.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_detalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table1.getSelectedRow() != -1) {
					try {
						Trayecto t= GestorTrayecto.get_trayecto_by_id(Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString()));
						cargar_detalle(t);
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else {
					VentanaAdmin.mensajeError("Seleccione un Trayecto de la tabla", "Error");
				}
			}
		});
		btn_detalle.setBounds(607, 224, 116, 23);
		add(btn_detalle);
		
		JButton btn_volver = new JButton("\u2190 Volver");
		btn_volver.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pnt_info_gral, VentanaAdmin.n_pntInfoGral);
			}
		});
		btn_volver.setBounds(20, 391, 89, 23);
		add(btn_volver);

		try {
			cargar_estaciones();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void cargar_estaciones () throws Exception {
		List<Estacion> e = GestorEstacion.get_estaciones_de_alta();
		this.estaciones_actuales = e;
		cb_origen.removeAllItems();
		cb_destino.removeAllItems();
		
		for (int i=0; i<e.size(); i++) {
			cb_origen.addItem(e.get(i).getNombre() );
			cb_destino.addItem(e.get(i).getNombre());
			
		}
	}
	
	public void mostrar_flujo_maximo () throws Exception {
		if(table1.getRowCount() !=0) {
		table1.removeRowSelectionInterval(0, table1.getRowCount()-1);
		}
		int index_origen = cb_origen.getSelectedIndex();
		int index_destino = cb_destino.getSelectedIndex();
		
		Estacion origen = estaciones_actuales.get(index_origen);
		Estacion destino = estaciones_actuales.get(index_destino);
		
		List<Trayecto> trayectos = GestorTrayecto.obtener_trayectos_origen_destino(origen.getId_estacion(), destino.getId_estacion());	//Obtiene trayectos que coincian con el origen y el destino
			
		if (table1.getRowCount() >0) {
			table1.removeRowSelectionInterval(0, table1.getRowCount()-1);
			}
			
			int tam = trayectos.size();
			int cantidad_columnas= table1.getColumnCount();
			
			if(cantidad_columnas !=0) {
				dm1= new DefaultTableModel();
				table1.setModel(dm1);
		
			}
			
			if(tam !=0) {
				
			int i=0;
			
			Object[]col0 = new Object[tam];
			Object[]col1= new Object[tam];
			Object[]col2 = new Object[tam];
			
			
			while(i<tam) {
			
				Trayecto t = trayectos.get(i);
				List<Estacion> estaciones = t.getEstaciones();
				String nombre = "";
				for (int j=0; j<estaciones.size(); j++) {
					nombre = nombre + estaciones.get(j).getNombre() + ", ";
				}
				col0[i]= t.getId();
				col1[i]= nombre;
				col2[i]= t.get_flujo_max();
				
				i++;
				
			}
			
			dm1.addColumn("id_trayecto", col0);
			dm1.addColumn("Camino", col1);
			dm1.addColumn("Flujo Máximo", col2);
			
			table1.getColumnModel().getColumn(0).setMaxWidth(0);
			table1.getColumnModel().getColumn(0).setMinWidth(0);
			table1.getColumnModel().getColumn(0).setPreferredWidth(0);
			table1.doLayout();
			}
	}
	
	
	public void cargar_detalle (Trayecto t) throws Exception {
		
		if(table2.getRowCount() !=0) {
			table2.removeRowSelectionInterval(0, table2.getRowCount()-1);
			}	
		List<Tramo>tramos = t.getTramos();
		
			
			int tam = tramos.size();
			int cantidad_columnas= table1.getColumnCount();
			
			if(cantidad_columnas !=0) {
				dm2= new DefaultTableModel();
				table2.setModel(dm2);
		
			}
			
			if(tam !=0) {
				
			int i=0;
			
			Object[]col0 = new Object[tam];
			Object[]col1= new Object[tam];
			Object[]col2 = new Object[tam];
			Object[]col3 = new Object[tam];
			Object[]col4 = new Object[tam];
			Object[]col5 = new Object[tam];
			Object[]col6 = new Object[tam];
			
			
			while(i<tam) {
				
				col0[i]= tramos.get(i).getId();
				col1[i]= tramos.get(i).getEstacion_origen().getNombre();
				col2[i]= tramos.get(i).getEstacion_destino().getNombre();
				col3[i]= tramos.get(i).getDuracion();
				col4[i]= tramos.get(i).getCosto();
				col5[i]= tramos.get(i).getDistancia_km();
				col6[i]= tramos.get(i).getCant_max_pasajeros();
				
				i++;
				
			}
			
			dm2.addColumn("id_tramo", col0);
			dm2.addColumn("Origen", col1);
			dm2.addColumn("Destino", col2);
			dm2.addColumn("Duracion", col3);
			dm2.addColumn("Costo", col4);
			dm2.addColumn("Distancia", col5);
			dm2.addColumn("Cantidad Max Pasajeros", col6);
		
			table2.getColumnModel().getColumn(0).setMaxWidth(0);
			table2.getColumnModel().getColumn(0).setMinWidth(0);
			table2.getColumnModel().getColumn(0).setPreferredWidth(0);
			table2.doLayout();
			}
	
	}
	
}
