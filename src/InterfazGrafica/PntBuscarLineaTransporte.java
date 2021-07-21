package InterfazGrafica;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import java.util.List;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import DTO.LineaTransporteDTO;
import Entidades.LineaTransporte;
import Entidades.Trayecto;
import Gestores.GestorLineaTransporte;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.Button;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalTime;

public class PntBuscarLineaTransporte extends JPanel {
	private JTextField tf_color;
	
	JComboBox cb_estado = new JComboBox();
	
	public static JTable table = new JTable();
	public static DefaultTableModel dm = new DefaultTableModel(){
		public boolean isCellEditable(int rowIndex, int columnIndex ) {
			return false;
		}
	};
	private JTextField tf_nombre;

	public PntBuscarLineaTransporte() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		JTextPane txtpnLineas = new JTextPane();
		txtpnLineas.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnLineas.setText("L\u00CDNEAS DE TRANSPORTE");
		txtpnLineas.setEditable(false);
		txtpnLineas.setBackground(SystemColor.menu);
		txtpnLineas.setBounds(243, 25, 241, 28);
		add(txtpnLineas);
		
		JLabel lblIdDeLa = new JLabel("Nombre");
		lblIdDeLa.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblIdDeLa.setBounds(22, 111, 176, 14);
		add(lblIdDeLa);
		
		JLabel lblNewLabel_1 = new JLabel("Color");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(22, 155, 64, 14);
		add(lblNewLabel_1);
		
		JButton btn_buscar = new JButton("Buscar");
		btn_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LineaTransporteDTO lTransp = new LineaTransporteDTO();
				
				lTransp.setNombre(tf_nombre.getText());
				lTransp.setColor(tf_color.getText());
				lTransp.setEstado(cb_estado.getSelectedIndex());
				
				try {
					
					restaurarTabla();
					
					List<LineaTransporte> lineasT=GestorLineaTransporte.obtenerLineas(lTransp);
					
					int id;
					String nombre;
					String color;
					String estado;
					String trayecto;
					
					for (int i = 0; i < lineasT.size(); i++) {
						id=lineasT.get(i).getId();
						nombre=lineasT.get(i).getNombre();
						color=lineasT.get(i).getColor();
						estado=GestorLineaTransporte.obtenerEstadoTxt(lineasT.get(i).getEstado());
						trayecto=GestorLineaTransporte.obtenerTrayectoTxt(lineasT.get(i).getTrayecto());
												
						Object[] rowData= {id, nombre, color, estado,trayecto};
						dm.addRow(rowData);
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btn_buscar.setBounds(58, 256, 77, 23);
		add(btn_buscar);
		
		dm.addColumn("Id");
		dm.addColumn("Nombre");
		dm.addColumn("Color");
		dm.addColumn("Estado");
		dm.addColumn("Trayecto");
		
		table.setModel(dm); //table tendra las columnas de dm que agregamos aqui arriba
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
        table.doLayout();
		
		JScrollPane sp_listar_lt = new JScrollPane(table);
		sp_listar_lt.setBounds(214, 83, 492, 212);
		add(sp_listar_lt);
		
		JButton btn_alta_linea = new JButton("Dar de alta una l\u00EDnea");
		btn_alta_linea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pntAltaLineaTransporte, VentanaAdmin.n_pntAltaLineaTransporte);
				limpiarPantalla();
				restaurarTabla();
			}
		});
		btn_alta_linea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_alta_linea.setBounds(22, 349, 158, 46);
		add(btn_alta_linea);
		
		JLabel lblNewLabel_3_1 = new JLabel("Estado");
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(22, 200, 46, 14);
		add(lblNewLabel_3_1);
		
		
		cb_estado.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_estado.setBounds(22, 212, 168, 20);
		cb_estado.setModel(new DefaultComboBoxModel(new String[] {"No activa", "Activa"}));
		add(cb_estado);
		
		JButton btn_baja_linea = new JButton("Dar de baja l\u00EDnea");
		btn_baja_linea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					
					LineaTransporteDTO lTransp= new LineaTransporteDTO();
					
					lTransp.setId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					lTransp.setNombre(table.getValueAt(table.getSelectedRow(), 1).toString());
					lTransp.setColor((table.getValueAt(table.getSelectedRow(), 2).toString()));
					lTransp.setEstado(GestorLineaTransporte.obtenerEstadoInt(table.getValueAt(table.getSelectedRow(), 3).toString()));
					lTransp.setAlta_baja(0);
					
					GestorLineaTransporte.actualizarLineaTransp(lTransp);
					
					VentanaAdmin.mensajeExito("Estacion dada de baja correctamente", "EXITO");
					
					restaurarTabla();
					limpiarPantalla();
					
				}
				else {
					VentanaAdmin.mensajeError("Seleccione una l�nea de transporte de la tabla", "ERROR");
				}
			}
		});
		btn_baja_linea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_baja_linea.setBounds(190, 349, 158, 46);
		add(btn_baja_linea);
		
		JButton btn_editar_linea = new JButton("Editar l\u00EDnea");
		btn_editar_linea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() != -1) {
					
					LineaTransporteDTO lTransp= new LineaTransporteDTO();
					
					lTransp.setId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					lTransp.setNombre(table.getValueAt(table.getSelectedRow(), 1).toString());
					lTransp.setColor((table.getValueAt(table.getSelectedRow(), 2).toString()));
					lTransp.setEstado(GestorLineaTransporte.obtenerEstadoInt(table.getValueAt(table.getSelectedRow(), 3).toString()));
					lTransp.setAlta_baja(1);
					
					try {
						VentanaAdmin.pntEditarLineaTransporte.cargarDatos(lTransp);
						VentanaAdmin.cambiarPantalla(VentanaAdmin.pntEditarLineaTransporte, VentanaAdmin.n_pntEditarLineaTransporte);
						
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				}
				else {
					VentanaAdmin.mensajeError("Seleccione una l�nea de transporte de la tabla", "ERROR");
				}
				
				limpiarPantalla();
				
			}
		});
		btn_editar_linea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_editar_linea.setBounds(358, 349, 158, 46);
		add(btn_editar_linea);
		
		tf_color = new JTextField();
		tf_color.setColumns(10);
		tf_color.setBounds(22, 169, 168, 20);
		add(tf_color);
		
		tf_nombre = new JTextField();
		tf_nombre.setColumns(10);
		tf_nombre.setBounds(22, 123, 168, 20);
		add(tf_nombre);
		
		JButton btn_agregar_trayecto = new JButton("Agregar trayecto");
		btn_agregar_trayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_agregar_trayecto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_agregar_trayecto.setBounds(526, 349, 158, 46);
		add(btn_agregar_trayecto);
		
	}
	
	public static void restaurarTabla() {
		 for( int i = dm.getRowCount() - 1; i >= 0; i-- ) {
	          dm.removeRow(i);
	      }
		}
	
	public void limpiarPantalla() {
		tf_nombre.setText(null);
		tf_color.setText(null);
		cb_estado.setSelectedIndex(0);
	}
}
