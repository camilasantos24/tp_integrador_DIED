package InterfazGrafica;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

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
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PntAltaEstacion extends JPanel {
	private JTextField tf_id;
	private JTextField tf_nombre;
	private JTextField tf_hs_apertura;
	private JTextField tf_min_apertura;
	private JTextField tf_hs_cierre;
	private JTextField tf_min_cierre;
	
	JComboBox cb_estado = new JComboBox();
	
	JButton btn_cancelar = new JButton("Cancelar");

	public PntAltaEstacion() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		JTextPane txtpnEstaciones = new JTextPane();
		txtpnEstaciones.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnEstaciones.setText("ALTA ESTACI\u00D3N");
		txtpnEstaciones.setEditable(false);
		txtpnEstaciones.setBackground(SystemColor.menu);
		txtpnEstaciones.setBounds(292, 25, 152, 28);
		add(txtpnEstaciones);
		
		JLabel lblIdDeLa = new JLabel("ID");
		lblIdDeLa.setFont(new Font("Calibri", Font.BOLD, 16));
		lblIdDeLa.setBounds(54, 101, 176, 14);
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
		tf_id.setBounds(54, 114, 353, 20);
		add(tf_id);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_1.setBounds(54, 145, 64, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Horario de apertura");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_2.setBounds(54, 190, 165, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Horario de cierre");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_3.setBounds(54, 236, 143, 14);
		add(lblNewLabel_3);
		
		JButton btn_alta = new JButton("Dar de alta");
		btn_alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Runnable r =()->{ 
					
					try {
						
					btn_alta.setEnabled(false);
					btn_cancelar.setEnabled(false);
					SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().guardadoDeDatos());
				
						EstacionesDTO estDTO= new EstacionesDTO();
						
						LocalTime hsApertura=null;
						LocalTime hsCierre=null;
						
						if (validarID()) {
							estDTO.setId(Integer.parseInt(tf_id.getText()));
							
							if (validarNombre()) {
								estDTO.setNombre(tf_nombre.getText());
								
								if (validarHoraApertura()) {
									hsApertura= LocalTime.parse(tf_hs_apertura.getText()+":"+tf_min_apertura.getText()+":00");
									estDTO.setHs_apertura(hsApertura);
									
									if(validarHoraCierre()) {
										hsCierre= LocalTime.parse(tf_hs_cierre.getText()+":"+tf_min_cierre.getText()+":00");
										estDTO.setHs_cierre(hsCierre);
										
										estDTO.setAlta_baja(1);
										estDTO.setEstado(cb_estado.getSelectedIndex());
										
										GestorEstacion.crearEstacion(estDTO);
										
										VentanaAdmin.mensajeExito("Estacion agregada correctamente.", "EXITO");
										
										VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarEstacion,VentanaAdmin.n_pntBuscarEstacion);
										limpiarPantalla();
									}
								}
							}
						}
						btn_alta.setEnabled(true);
						btn_cancelar.setEnabled(true);
						SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().finalizarPantalla());
						
					} catch (InvocationTargetException | InterruptedException e1) {
						e1.printStackTrace();
					}
						
					};

					new Thread(r).start();
			}
		});
		
		btn_alta.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_alta.setBounds(502, 159, 181, 46);
		add(btn_alta);
		
		JLabel lblNewLabel_3_1 = new JLabel("Estado");
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_3_1.setBounds(54, 281, 74, 14);
		add(lblNewLabel_3_1);
		
		
		cb_estado.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_estado.setBounds(54, 293, 353, 20);
		cb_estado.setModel(new DefaultComboBoxModel(new String[] {"En mantenimiento", "Operativa"}));
		add(cb_estado);
		
		tf_nombre = new JTextField();
		tf_nombre.setColumns(10);
		tf_nombre.setBounds(54, 159, 353, 20);
		add(tf_nombre);
		
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int respuesta = VentanaAdmin.mensajeConsulta(null, "ATENCION!", "¿Desea cancelar la carga de datos?\nSe perderá toda la información cargada.");
				if(respuesta==JOptionPane.YES_OPTION) {
					VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarEstacion,VentanaAdmin.n_pntBuscarEstacion);
					limpiarPantalla();		
				}
			}
		});
		btn_cancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_cancelar.setBounds(502, 224, 181, 46);
		add(btn_cancelar);
		
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
		
		JLabel lblNewLabel_2_1 = new JLabel(" :");
		lblNewLabel_2_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(116, 208, 12, 14);
		add(lblNewLabel_2_1);
		
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
		
		JLabel lblNewLabel_2_1_1 = new JLabel(" :");
		lblNewLabel_2_1_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(116, 253, 12, 14);
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
		tf_min_cierre.setBounds(130, 250, 57, 20);
		add(tf_min_cierre);
		
	}
	
	
	public boolean IDunico(int id) {
		Estacion idEst= null;
		
		try {
			idEst=GestorEstacion.obtenerEstacionPorID(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			if(idEst == null) {
				return true;
			}else {
				return false;
			}
		}
	
	// Valida que se haya ingresado un id y que este sea unico
	public boolean validarID () {
		if(tf_id.getText().length()==0) {
			VentanaAdmin.mensajeError("Por favor ingrese un ID", "ERROR");
			return false;
		}else {
			if(IDunico(Integer.parseInt(tf_id.getText()))) {
			return true;
			}else {
				VentanaAdmin.mensajeError("El ID ya existe.\nPor favor ingrese uno nuevo.", "ERROR");
				return false;
			}
		}
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
	
	public void limpiarPantalla() {
		tf_hs_apertura.setText(null);
		tf_hs_cierre.setText(null);
		tf_id.setText(null);
		tf_min_apertura.setText(null);
		tf_min_cierre.setText(null);
		tf_nombre.setText(null);
		cb_estado.setSelectedIndex(0);
	}
}
