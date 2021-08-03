package InterfazGrafica;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

import DTO.EstacionesDTO;
import DTO.MantenimientoDTO;
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
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PntEditarEstacion extends JPanel {
	private JTextField tf_id;
	private JTextField tf_nombre;
	private JTextField tf_hs_apertura;
	private JTextField tf_min_apertura;
	private JTextField tf_min_cierre;
	private JTextField tf_hs_cierre;

	JComboBox cb_estado = new JComboBox();
	
	public PntEditarEstacion() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		JTextPane txtpnEstaciones = new JTextPane();
		txtpnEstaciones.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnEstaciones.setText("EDITAR ESTACI\u00D3N");
		txtpnEstaciones.setEditable(false);
		txtpnEstaciones.setBackground(SystemColor.menu);
		txtpnEstaciones.setBounds(292, 25, 171, 28);
		add(txtpnEstaciones);
		
		JLabel lblIdDeLa = new JLabel("ID");
		lblIdDeLa.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblIdDeLa.setBounds(54, 101, 176, 14);
		add(lblIdDeLa);
		
		tf_id = new JTextField();
		tf_id.setEditable(false);
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
		tf_id.setBounds(54, 114, 353, 20);
		add(tf_id);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(54, 145, 64, 14);
		add(lblNewLabel_1);
		
		JButton btn_guardar = new JButton("Guardar cambios");
		btn_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EstacionesDTO estDTO= new EstacionesDTO();
				
				LocalTime hsApertura=null;
				LocalTime hsCierre=null;
									
					if (validarNombre()) {
						estDTO.setNombre(tf_nombre.getText());
						
						if (validarHoraApertura()) {
							hsApertura= LocalTime.parse(tf_hs_apertura.getText()+":"+tf_min_apertura.getText()+":00");
							estDTO.setHs_apertura(hsApertura);
							
							if(validarHoraCierre()) {
								hsCierre= LocalTime.parse(tf_hs_cierre.getText()+":"+tf_min_cierre.getText()+":00");
								estDTO.setHs_cierre(hsCierre);
								
								estDTO.setId(Integer.parseInt(tf_id.getText()));
								estDTO.setAlta_baja(1);
								estDTO.setEstado(cb_estado.getSelectedIndex());
								
								if(cambioEstado(estDTO.getEstado())) {
									
									PntMantenimiento.estDTO=estDTO;
									VentanaAdmin.pntMantenimiento.cargarDatos();
									VentanaAdmin.cambiarPantalla(VentanaAdmin.pntMantenimiento, VentanaAdmin.n_pntMantenimiento);
																	
								}else {
									
									GestorEstacion.actualizarEstacion(estDTO);
									
									VentanaAdmin.mensajeExito("Estacion actualizada correctamente.", "EXITO");
									
									VentanaAdmin.pntBuscarEstacion.restaurarTabla();
									
									VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarEstacion,VentanaAdmin.n_pntBuscarEstacion);
								}
								
								limpiarPantalla();
							}
						}
					}
				}
				
		});
		btn_guardar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_guardar.setBounds(502, 159, 181, 46);
		add(btn_guardar);
		
		JLabel lblNewLabel_3_1 = new JLabel("Estado");
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(54, 281, 46, 14);
		add(lblNewLabel_3_1);
		
		cb_estado.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_estado.setBounds(54, 293, 353, 20);
		cb_estado.setModel(new DefaultComboBoxModel(new String[] {"En mantenimiento", "Operativa"}));
		add(cb_estado);
		
		tf_nombre = new JTextField();
		tf_nombre.setColumns(10);
		tf_nombre.setBounds(54, 159, 353, 20);
		add(tf_nombre);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int respuesta = VentanaAdmin.mensajeConsulta(null, "ATENCION!", "¿Desea cancelar la carga de datos?\nSe perderá toda la información cargada.");
				if(respuesta==JOptionPane.YES_OPTION) {
					VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarEstacion,VentanaAdmin.n_pntBuscarEstacion);
					limpiarPantalla();		
				}
				
			}
		});
		btn_cancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_cancelar.setBounds(502, 224, 181, 46);
		add(btn_cancelar);
		
		JLabel lblNewLabel_2 = new JLabel("Horario de apertura");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(54, 190, 133, 14);
		add(lblNewLabel_2);
		
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
		tf_hs_apertura.setBounds(54, 205, 57, 20);
		add(tf_hs_apertura);
		
		JLabel lblNewLabel_2_1 = new JLabel(" :");
		lblNewLabel_2_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(116, 208, 12, 14);
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
		tf_min_apertura.setBounds(130, 205, 57, 20);
		add(tf_min_apertura);
		
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
		tf_min_cierre.setBounds(130, 250, 57, 20);
		add(tf_min_cierre);
		
		JLabel lblNewLabel_3 = new JLabel("Horario de cierre");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(54, 236, 113, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_2_1_1 = new JLabel(" :");
		lblNewLabel_2_1_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(116, 253, 12, 14);
		add(lblNewLabel_2_1_1);
		
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
		tf_hs_cierre.setBounds(54, 250, 57, 20);
		add(tf_hs_cierre);
	}
	
	public void limpiarPantalla() {
		tf_hs_apertura.setText(null);
		tf_hs_cierre.setText(null);
		tf_id.setText(null);
		tf_min_apertura.setText(null);
		tf_min_cierre.setText(null);
		tf_nombre.setText(null);
		cb_estado.setSelectedIndex(0);
	}
	
	// Valida que se haya ingresado un nombre
	public boolean validarNombre() {
		if(tf_nombre.getText().length()==0) {
			VentanaAdmin.mensajeError("Por favor ingrese un NOMBRE", "ERROR");
			return false;
		}else {
			return true;
		}
	}
	
	// Valida que la hora ingresada no sea vacia y que sea correcta
	public boolean validarHoraApertura() {
		if (!tf_hs_apertura.getText().isEmpty() && !tf_min_apertura.getText().isEmpty()) {
			if ((Integer.parseInt(tf_hs_apertura.getText())<=23 && Integer.parseInt(tf_hs_apertura.getText())>=0) && 
				(Integer.parseInt(tf_min_apertura.getText())<60 && Integer.parseInt(tf_min_apertura.getText())>=0)){
					return true;
			}else {
				VentanaAdmin.mensajeError("Ingrese hora y minutos correctos en el horario de APERTURA","ERROR");
				return false;
			}
	}else {
		VentanaAdmin.mensajeError("Ingrese hora y minutos en el horario de APERTURA","ERROR");
		return false;
	}
	}
	
	// Valida que la hora ingresada no sea vacia y que sea correcta
	public boolean validarHoraCierre() {
	if(!tf_hs_cierre.getText().isEmpty() && !tf_min_cierre.getText().isEmpty()) {
		if ((Integer.parseInt(tf_hs_cierre.getText())<=23 && Integer.parseInt(tf_hs_cierre.getText())>=0) && 
			(Integer.parseInt(tf_min_cierre.getText())<60 && Integer.parseInt(tf_min_cierre.getText())>=0)){
				return true;
		}else {
			VentanaAdmin.mensajeError("Ingrese hora y minutos correctos en el horario de CIERRE", "ERROR");
			return false;
		}
	}else {
		VentanaAdmin.mensajeError("Ingrese hora y minutos en el horario de CIERRE","ERROR");
		return false;
	}
	}
	
	public void cargarDatos(EstacionesDTO estDTO) {
		int hs_apertura=estDTO.getHs_apertura().getHour();
		int hs_cierre=estDTO.getHs_cierre().getHour();
		int min_apertura=estDTO.getHs_apertura().getMinute();
		int min_cierre=estDTO.getHs_cierre().getMinute();
		
		tf_id.setText(Integer.toString(estDTO.getId()));
		tf_nombre.setText(estDTO.getNombre());
		cb_estado.setSelectedIndex(estDTO.getEstado());
		
		if(hs_apertura>=0 && hs_apertura<=9) {
		tf_hs_apertura.setText("0"+Integer.toString(hs_apertura));
		}else {
			tf_hs_apertura.setText(Integer.toString(hs_apertura));
		}
		
		if(hs_cierre>=0 && hs_cierre<=9) {
		tf_hs_cierre.setText("0"+Integer.toString(hs_cierre));
		}else {
			tf_hs_cierre.setText(Integer.toString(hs_cierre));
		}
		
		if(min_apertura>=0 && min_apertura<=9) {
		tf_min_apertura.setText("0"+Integer.toString(min_apertura));
		}else {
			tf_min_apertura.setText(Integer.toString(min_apertura));
		}
		
		if(min_cierre>=0 && min_cierre<=9) {
		tf_min_cierre.setText("0"+Integer.toString(min_cierre));
		}else {
			tf_min_cierre.setText(Integer.toString(min_cierre));
		}
		
		
	}
	
	public boolean cambioEstado(int estadoDTO) {
		int estadoTabla=(GestorEstacion.obtenerEstadoInt(PntBuscarEstacion.table.getValueAt(PntBuscarEstacion.table.getSelectedRow(), 4).toString()));
		int estadoNuevoSeleccionado=cb_estado.getSelectedIndex();
		
		if(estadoTabla!=estadoNuevoSeleccionado) {
			return true;
		}else {
			return false;
		}
	}
}
