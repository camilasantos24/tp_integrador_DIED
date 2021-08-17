package InterfazGrafica;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Entidades.Estacion;
import Entidades.Trayecto;
import Gestores.GestorLineaTransporte;
import Gestores.GestorTrayecto;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class PntSeleccionarTrayecto extends JPanel {

	
	JButton btnNewButton = new JButton("Cancelar");
	JButton btnSeleccionar = new JButton("Seleccionar");
	JButton btnTrayecto = new JButton("+ Trayecto");
	
	public  JTable table = new JTable();
	public static  DefaultTableModel dm = new DefaultTableModel(){
		public boolean isCellEditable(int rowIndex, int columnIndex ) {
			return false;
		}
	};
	
	public int id_linea_transporte_actual; 
	/**
	 * Create the panel.
	 */
	public PntSeleccionarTrayecto() {
		setLayout(null);
		
		dm.addColumn("idTrayecto");
		dm.addColumn("Camino");
		dm.addColumn("Duracion (min)");
		dm.addColumn("Costo");
		dm.addColumn("Distancia (km)");
		
		table.setModel(dm);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.doLayout();
		
		JScrollPane sp_listar_lt = new JScrollPane(table);
		sp_listar_lt.setBounds(10, 76, 713, 218);
		add(sp_listar_lt);
		
		JLabel lblNewLabel = new JLabel("Seleccione un trayecto:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 713, 27);
		add(lblNewLabel);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarLineaTransporte, VentanaAdmin.n_pntBuscarLineaTransporte);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(222, 338, 141, 33);
		add(btnNewButton);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					int id_t = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					seleccionar_trayecto(id_t);
				}else {
					VentanaAdmin.mensajeError("Seleccione una línea de transporte en la tabla", "Error");
				}
			}
		});
		btnSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSeleccionar.setBounds(431, 338, 123, 33);
		add(btnSeleccionar);
		
		JButton btnTrayecto = new JButton("+ Trayecto");
		btnTrayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin.pntAltaTrayecto.id_lina_de_transporte_actual = id_linea_transporte_actual;
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pntAltaTrayecto, VentanaAdmin.n_pntAltaTrayecto);
			}
		});
		btnTrayecto.setForeground(new Color(0, 100, 0));
		btnTrayecto.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTrayecto.setBounds(611, 293, 112, 27);
		add(btnTrayecto);
		
		
		try {
			cargarTrayectos();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public void seleccionar_trayecto (int id_trayecto) {
		if (GestorLineaTransporte.agregar_trayecto(id_linea_transporte_actual, id_trayecto) == true) {
			VentanaAdmin.mensajeExito("Trayecto Agregado", "ÉXITO");
			VentanaAdmin.pntBuscarLineaTransporte.limpiarPantalla();
			VentanaAdmin.pntBuscarLineaTransporte.restaurarTabla();
			VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarLineaTransporte, VentanaAdmin.n_pntBuscarLineaTransporte);
			restaurarTabla();
		}
	} 
	
	public void cargarTrayectos() throws Exception {
		
		DecimalFormat df = new DecimalFormat("###.##");
		
		Runnable r =()->{ 
			
			try {
				
			btnNewButton.setEnabled(false);
			btnSeleccionar.setEnabled(false);
			btnTrayecto.setEnabled(false);
			VentanaAdmin.pntBuscarLineaTransporte.btn_agregar_trayecto.setEnabled(false);
		//	SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().iniciarPantalla());
		
				List<Trayecto> t = GestorTrayecto.get_all_trayectos();
				
				
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
					float costo; 
					float distancia; 
					int duracion;
					List<Estacion> estaciones;
					
					
					Object[]col0 = new Object[tam];
					Object[]col1= new Object[tam];
					Object[]col2 = new Object[tam];
					Object[]col3 = new Object[tam];
					Object[]col4 = new Object[tam];
					
					while(i<tam) {
						String camino="";
						id= t.get(i).getId();
						duracion = t.get(i).get_duracion();
						distancia = t.get(i).get_distancia();
						costo = t.get(i).get_costo();
						estaciones=t.get(i).getEstaciones();
						
						for(int j=0; j<estaciones.size()-1; j++) {
							camino +=  estaciones.get(j).getNombre() + ", ";
						}
						
						camino +=  estaciones.get(estaciones.size()-1).getNombre();
						
						col0[i]= id;
						col1[i]= camino;
						col2[i]= df.format(duracion);
						col3[i]= df.format(costo);
						col4[i]= df.format(distancia);
						
						i++;
						int x=i;
					//	SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().cargaDatos(x));
						
					}
							dm.addColumn("idTrayecto", col0);
							dm.addColumn("Camino", col1);
							dm.addColumn("Duracion", col2);
							dm.addColumn("Costo", col3);
							dm.addColumn("Distancia", col4);
				
					}
					
			btnNewButton.setEnabled(true);
			btnSeleccionar.setEnabled(true);
			btnTrayecto.setEnabled(true);
			VentanaAdmin.pntBuscarLineaTransporte.btn_agregar_trayecto.setEnabled(true);
					
		//	SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().finalizarPantalla());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
				
		};

		new Thread(r).start();
	}
	
	public static void restaurarTabla() {
		 for( int i = dm.getRowCount() - 1; i >= 0; i-- ) {
	          dm.removeRow(i);
	      }
		}
}
