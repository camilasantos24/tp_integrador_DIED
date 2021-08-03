package InterfazGrafica;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

import DTO.EstacionesDTO;
import DTO.LineaTransporteDTO;
import DTO.MantenimientoDTO;
import Entidades.Estacion;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PntEditarLineaTransporte extends JPanel {
	private JTextField tf_color;
	private JTextField tf_nombre;

	JComboBox cb_estado = new JComboBox();
	private JTextField tf_id;
	
	public PntEditarLineaTransporte() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		JTextPane txtpnEstaciones = new JTextPane();
		txtpnEstaciones.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnEstaciones.setText("EDITAR L\u00CDNEA DE TRANSPORTE");
		txtpnEstaciones.setEditable(false);
		txtpnEstaciones.setBackground(SystemColor.menu);
		txtpnEstaciones.setBounds(224, 25, 298, 28);
		add(txtpnEstaciones);
		
		JLabel lblIdDeLa = new JLabel("Nombre");
		lblIdDeLa.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblIdDeLa.setBounds(55, 149, 176, 14);
		add(lblIdDeLa);
		
		tf_color = new JTextField();
		tf_color.setEnabled(true);
		tf_color.setEditable(true);
		tf_color.setColumns(10);
		tf_color.setBounds(55, 207, 353, 20);
		add(tf_color);
		
		JLabel lblNewLabel_1 = new JLabel("Color");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(55, 193, 64, 14);
		add(lblNewLabel_1);
		
		JButton btn_guardar = new JButton("Guardar cambios");
		btn_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				LineaTransporteDTO lTranspDTO = new LineaTransporteDTO();
									
					if (validarNombre()) {
						lTranspDTO.setNombre(tf_nombre.getText());
						
						if (validarColor()) {
							lTranspDTO.setColor(tf_color.getText());
								
							lTranspDTO.setAlta_baja(1);
							lTranspDTO.setEstado(cb_estado.getSelectedIndex());
							lTranspDTO.setId(Integer.parseInt(tf_id.getText()));
							
									
							GestorLineaTransporte.actualizarLineaTransp(lTranspDTO);
									
									VentanaAdmin.mensajeExito("Estacion actualizada correctamente.", "EXITO");
									
									VentanaAdmin.pntBuscarLineaTransporte.restaurarTabla();
									
									VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarLineaTransporte,VentanaAdmin.n_pntBuscarLineaTransporte);
								}
								
								limpiarPantalla();
							}
						}
			});
		btn_guardar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_guardar.setBounds(502, 159, 181, 46);
		add(btn_guardar);
		
		JLabel lblNewLabel_3_1 = new JLabel("Estado");
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(55, 238, 46, 14);
		add(lblNewLabel_3_1);
		
		cb_estado.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_estado.setBounds(55, 250, 353, 20);
		cb_estado.setModel(new DefaultComboBoxModel(new String[] {"No activa", "Activa"}));
		add(cb_estado);
		
		tf_nombre = new JTextField();
		tf_nombre.setColumns(10);
		tf_nombre.setBounds(55, 162, 353, 20);
		add(tf_nombre);
		
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
		
		tf_id = new JTextField();
		tf_id.setEditable(false);
		tf_id.setBounds(55, 118, 46, 20);
		add(tf_id);
		tf_id.setColumns(10);
		
		JLabel lblId = new JLabel("ID ");
		lblId.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblId.setBounds(55, 103, 176, 14);
		add(lblId);
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
		
		
		public void limpiarPantalla() {
			tf_nombre.setText(null);
			tf_color.setText(null);
			cb_estado.setSelectedIndex(0);
		}
	
	public void cargarDatos(LineaTransporteDTO lTranspDTO) {
		
		tf_color.setText(lTranspDTO.getColor());
		tf_nombre.setText(lTranspDTO.getNombre());
		cb_estado.setSelectedIndex(lTranspDTO.getEstado());
		tf_id.setText(Integer.toString(lTranspDTO.getId()));
		
	}
	
}
