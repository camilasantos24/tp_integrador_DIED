package InterfazGrafica;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

import DTO.EstacionesDTO;
import DTO.LineaTransporteDTO;
import Entidades.Estacion;
import Entidades.LineaTransporte;
import Gestores.GestorEstacion;
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
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PntAltaLineaTransporte extends JPanel {
	private JTextField tf_nombre;
	private JTextField tf_color;
	
	JComboBox cb_estado = new JComboBox();

	public PntAltaLineaTransporte() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		JTextPane txtpnEstaciones = new JTextPane();
		txtpnEstaciones.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnEstaciones.setText("ALTA L\u00CDNEA DE TRANSPORTE");
		txtpnEstaciones.setEditable(false);
		txtpnEstaciones.setBackground(SystemColor.menu);
		txtpnEstaciones.setBounds(253, 25, 278, 28);
		add(txtpnEstaciones);
		
		JLabel lblIdDeLa = new JLabel("Nombre");
		lblIdDeLa.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblIdDeLa.setBounds(54, 152, 176, 14);
		add(lblIdDeLa);
		
		tf_nombre = new JTextField();
		tf_nombre.setColumns(10);
		tf_nombre.setBounds(54, 165, 353, 20);
		add(tf_nombre);
		
		JLabel lblNewLabel_1 = new JLabel("Color");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(54, 196, 64, 14);
		add(lblNewLabel_1);
		
		JButton btn_alta = new JButton("Dar de alta");
		btn_alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				LineaTransporteDTO lTranspDTO = new LineaTransporteDTO();
					
					if (validarNombre()) {
						
						if (validarColor()) {
							
							if(validarNombreColor()) {
								lTranspDTO.setNombre(tf_nombre.getText());
								lTranspDTO.setColor(tf_color.getText());
								
								
							lTranspDTO.setAlta_baja(1);
							lTranspDTO.setEstado(cb_estado.getSelectedIndex());
								
							GestorLineaTransporte.crearLineaTransp(lTranspDTO);
								
								VentanaAdmin.mensajeExito("Linea de transporte agregada correctamente.", "EXITO");
								
								VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarLineaTransporte,VentanaAdmin.n_pntBuscarLineaTransporte);
								limpiarPantalla();
							}
						}
					}
			}
		});
		
		btn_alta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_alta.setBounds(502, 159, 181, 46);
		add(btn_alta);
		
		JLabel lblNewLabel_3_1 = new JLabel("Estado");
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(54, 241, 46, 14);
		add(lblNewLabel_3_1);
		
		
		cb_estado.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_estado.setBounds(54, 253, 353, 20);
		cb_estado.setModel(new DefaultComboBoxModel(new String[] {"No activa", "Activa"}));
		add(cb_estado);
		
		tf_color = new JTextField();
		tf_color.setColumns(10);
		tf_color.setBounds(54, 210, 353, 20);
		add(tf_color);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int respuesta = VentanaAdmin.mensajeConsulta(null, "ATENCION!", "¿Desea cancelar la carga de datos?\nSe perderá toda la información cargada.");
				if(respuesta==JOptionPane.YES_OPTION) {
					VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarLineaTransporte,VentanaAdmin.n_pntBuscarLineaTransporte);
					limpiarPantalla();		
				}
			}
		});
		btn_cancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_cancelar.setBounds(502, 224, 181, 46);
		add(btn_cancelar);
		
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
	
	// Valida que se haya ingresado un nombre
		public boolean validarColor() {
			if(tf_color.getText().length()==0) {
				VentanaAdmin.mensajeError("Por favor ingrese un COLOR", "ERROR");
				return false;
			}else {
				return true;
			}
		}
		
		public boolean validarNombreColor() {
			if(NombreColorUnico(tf_nombre.getText(), tf_color.getText())) {
				return true;
			}else {
				VentanaAdmin.mensajeError("Ya existe una línea con ese NOMBRE y COLOR.\nPor favor ingrese nuevos valores.", "ERROR");
				return false;
			}
		}
		
		public boolean NombreColorUnico(String nombre, String color) {
			LineaTransporte lineaT= new LineaTransporte();
			
			try {
				lineaT=GestorLineaTransporte.obtenerLineaPorNombreColor(nombre, color);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(lineaT==null) {
				return true;
			}else {
				return false;
			}
		}
	
	
	public void limpiarPantalla() {
		tf_nombre.setText(null);
		tf_color.setText(null);
		cb_estado.setSelectedIndex(0);
	}
}
