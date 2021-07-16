package InterfazGrafica;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import java.util.List;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import DTO.EstacionesDTO;
import Entidades.Estacion;
import Gestores.GestorEstacion;

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

public class PntBuscarEstacion extends JPanel {
	private JTextField tf_id;
	private JTextField tf_nombre;
	private JTextField tf_hs_apertura;
	
	JComboBox cb_estado = new JComboBox();
	
	public static JTable table = new JTable();
	public static DefaultTableModel dm = new DefaultTableModel(){
		public boolean isCellEditable(int rowIndex, int columnIndex ) {
			return false;
		}
	};
	private JTextField tf_min_apertura;
	private JTextField tf_hs_cierre;
	private JTextField tf_min_cierre;

	public PntBuscarEstacion() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		JTextPane txtpnEstaciones = new JTextPane();
		txtpnEstaciones.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnEstaciones.setText("ESTACIONES");
		txtpnEstaciones.setEditable(false);
		txtpnEstaciones.setBackground(SystemColor.menu);
		txtpnEstaciones.setBounds(298, 25, 123, 28);
		add(txtpnEstaciones);
		
		JLabel lblIdDeLa = new JLabel("ID");
		lblIdDeLa.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblIdDeLa.setBounds(22, 64, 176, 14);
		add(lblIdDeLa);
		
		tf_id = new JTextField();
		tf_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char val= arg0.getKeyChar();
				if(val<'0' || val>'9') {
					arg0.consume();
				}
			}
		});
		tf_id.setColumns(10);
		tf_id.setBounds(22, 77, 168, 20);
		add(tf_id);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(22, 108, 64, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Horario de apertura");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(22, 153, 133, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Horario de cierre");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(22, 199, 113, 14);
		add(lblNewLabel_3);
		
		JButton btn_buscar = new JButton("Buscar");
		btn_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EstacionesDTO obtEstDTO= new EstacionesDTO();
								
				// Si se ingresa hora y minuto se tiene en cuenta, si se ingresa uno de los 2 valores
				// se toma como null, es decir, no se tiene en cuenta.
				
				LocalTime hsApertura=null;
				LocalTime hsCierre=null;
				
				if (!tf_hs_apertura.getText().isEmpty() && !tf_min_apertura.getText().isEmpty()) {
						if ((Integer.parseInt(tf_hs_apertura.getText())<=23 && Integer.parseInt(tf_hs_apertura.getText())>=0) && 
							(Integer.parseInt(tf_min_apertura.getText())<60 && Integer.parseInt(tf_min_apertura.getText())>=0)){
								hsApertura= LocalTime.parse(tf_hs_apertura.getText()+":"+tf_min_apertura.getText()+":00");
						}else {
							VentanaAdmin.mensajeError("Ingrese hora y minutos correctos en el horario de APERTURA","ERROR");
						}
				}
				if(!tf_hs_cierre.getText().isEmpty() && !tf_min_cierre.getText().isEmpty()) {
					if ((Integer.parseInt(tf_hs_cierre.getText())<=23 && Integer.parseInt(tf_hs_cierre.getText())>=0) && 
						(Integer.parseInt(tf_min_cierre.getText())<60 && Integer.parseInt(tf_min_cierre.getText())>=0)){
							hsCierre= LocalTime.parse(tf_hs_cierre.getText()+":"+tf_min_cierre.getText()+":00");
					}else {
						VentanaAdmin.mensajeError("Ingrese hora y minutos correctos en el horario de CIERRE", "ERROR");
					}
				}	
				
				// Validamos si se ingreso id. En caso que no se haya ingresado
				// se devuelve un -1 para no tener en cuenta al momento de la busqueda.
				if(tf_id.getText().length()==0) {
					obtEstDTO.setId(-1);
				}else {
					obtEstDTO.setId(Integer.parseInt(tf_id.getText()));
				}
				
				obtEstDTO.setNombre(tf_nombre.getText());
				obtEstDTO.setHs_apertura(hsApertura);
				obtEstDTO.setHs_cierre(hsCierre);
				obtEstDTO.setEstado(cb_estado.getSelectedIndex());
				
				try {
					
					restaurarTabla();
					
					List<Estacion> estaciones=GestorEstacion.obtenerEstaciones(obtEstDTO);
					
					int idEst;
					String nombre;
					LocalTime hsApert;
					LocalTime hsCie;
					int estado;
					
					for (int i = 0; i < estaciones.size(); i++) {
						idEst=estaciones.get(i).getId_estacion();
						nombre=estaciones.get(i).getNombre();
						hsApert=estaciones.get(i).getHs_apertura();
						hsCie=estaciones.get(i).getHs_cierre();
						estado=estaciones.get(i).getEstado();
						
						Object[] rowData= {idEst, nombre, hsApert, hsCie, estado};
						dm.addRow(rowData);
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btn_buscar.setBounds(58, 299, 77, 23);
		add(btn_buscar);
		
		dm.addColumn("ID Estacion");
		dm.addColumn("Nombre");
		dm.addColumn("Hora Apertura");
		dm.addColumn("Hora Cierre");
		dm.addColumn("Estado");
		
		table.setModel(dm); //table tendra las columnas de dm que agregamos aqui arriba
		
		JScrollPane sp_listar_est = new JScrollPane(table);
		sp_listar_est.setBounds(215, 64, 492, 212);
		add(sp_listar_est);
		
		JButton btn_alta_est = new JButton("Dar de alta una estaci\u00F3n");
		btn_alta_est.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_alta_est.setBounds(47, 355, 181, 46);
		add(btn_alta_est);
		
		JLabel lblNewLabel_3_1 = new JLabel("Estado");
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(22, 244, 46, 14);
		add(lblNewLabel_3_1);
		
		
		cb_estado.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_estado.setBounds(22, 256, 168, 20);
		cb_estado.setModel(new DefaultComboBoxModel(new String[] {"En mantenimiento", "Operativa"}));
		add(cb_estado);
		
		JButton btn_baja_est = new JButton("Dar de baja estaci\u00F3n");
		btn_baja_est.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_baja_est.setBounds(275, 355, 181, 46);
		add(btn_baja_est);
		
		JButton btn_editar_est = new JButton("Editar estaci\u00F3n");
		btn_editar_est.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_editar_est.setBounds(503, 355, 181, 46);
		add(btn_editar_est);
		
		tf_nombre = new JTextField();
		tf_nombre.setColumns(10);
		tf_nombre.setBounds(22, 122, 168, 20);
		add(tf_nombre);
		
		tf_hs_apertura = new JTextField();
		tf_hs_apertura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char val= e.getKeyChar();
				if(val<'0' || val>'9') {
					e.consume();
				}
				
				if (tf_hs_apertura.getText().length()== 2) {
			         e.consume();
				}
			}
			
		});
		tf_hs_apertura.setColumns(10);
		tf_hs_apertura.setBounds(22, 168, 39, 20);
		add(tf_hs_apertura);
		
		JLabel lblNewLabel_2_1 = new JLabel(" :");
		lblNewLabel_2_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(61, 170, 19, 14);
		add(lblNewLabel_2_1);
		
		tf_min_apertura = new JTextField();
		tf_min_apertura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char val= e.getKeyChar();
				if(val<'0' || val>'9') {
					e.consume();
				}
				
				if (tf_min_apertura.getText().length()== 2) {
			         e.consume();
				}
			}
		});
		tf_min_apertura.setColumns(10);
		tf_min_apertura.setBounds(71, 168, 39, 20);
		add(tf_min_apertura);
		
		tf_hs_cierre = new JTextField();
		tf_hs_cierre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char val= e.getKeyChar();
				if(val<'0' || val>'9') {
					e.consume();
				}
				
				if (tf_hs_cierre.getText().length()== 2) {
			         e.consume();
			}
			}
		});
		tf_hs_cierre.setColumns(10);
		tf_hs_cierre.setBounds(22, 213, 39, 20);
		add(tf_hs_cierre);
		
		JLabel lblNewLabel_2_1_1 = new JLabel(" :");
		lblNewLabel_2_1_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(61, 215, 19, 14);
		add(lblNewLabel_2_1_1);
		
		tf_min_cierre = new JTextField();
		tf_min_cierre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char val= e.getKeyChar();
				if(val<'0' || val>'9') {
					e.consume();
				}
				
				if (tf_min_cierre.getText().length()== 2) {
			         e.consume();
				}
			}
		});
		tf_min_cierre.setColumns(10);
		tf_min_cierre.setBounds(71, 213, 39, 20);
		add(tf_min_cierre);
		
	}
	
	public static void restaurarTabla() {
		 for( int i = dm.getRowCount() - 1; i >= 0; i-- ) {
	          dm.removeRow(i);
	      }
		}
}
