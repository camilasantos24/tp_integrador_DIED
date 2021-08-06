package InterfazGrafica;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

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
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTextArea;

public class PntMantenimiento extends JPanel {
	private JTextField tf_fechaMant;
	
	JTextArea txtArea_obs = new JTextArea();
	
	JTextPane txtTituloMant = new JTextPane();
	
	JButton btn_cancelar = new JButton("Cancelar");
	
	LocalDate fecha = LocalDate.now();
	private JTextField tf_id_estacion;
	
	public MantenimientoDTO mantDTO = new MantenimientoDTO();
	public static EstacionesDTO estDTO;

	
	public PntMantenimiento() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		
		txtTituloMant.setFont(new Font("Arial", Font.BOLD, 18));
		txtTituloMant.setText("INICIAR MANTENIMIENTO");
		txtTituloMant.setEditable(false);
		txtTituloMant.setBackground(SystemColor.menu);
		txtTituloMant.setBounds(254, 23, 248, 28);
		add(txtTituloMant);
		
		JLabel lblFechaMant = new JLabel("Fecha");
		lblFechaMant.setFont(new Font("Calibri", Font.BOLD, 16));
		lblFechaMant.setBounds(54, 126, 176, 14);
		add(lblFechaMant);
		
		tf_fechaMant = new JTextField();
		tf_fechaMant.setEditable(false);
		tf_fechaMant.setText(fecha.toString());
		tf_fechaMant.setColumns(10);
		tf_fechaMant.setBounds(54, 139, 353, 20);
		add(tf_fechaMant);
		
		JLabel lblNewLabel_1 = new JLabel("Observaciones");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_1.setBounds(54, 224, 130, 14);
		add(lblNewLabel_1);
		
		JButton btn_guardar = new JButton("Guardar cambios");
		btn_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Runnable r =()->{ 
					
					try {
						
					btn_guardar.setEnabled(false);
					btn_cancelar.setEnabled(false);
					SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().guardadoDeDatos());
				
						cargarMantDTO();
						if(estDTO.getEstado()==0) {
							try {
								GestorEstacion.crearMantenimiento(mantDTO);
								GestorEstacion.actualizarEstacion(estDTO);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else {
							try {
								GestorEstacion.finalizarMantenimiento(mantDTO);
								GestorEstacion.actualizarEstacion(estDTO);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						
						VentanaAdmin.mensajeExito("Estacion actualizada correctamente.", "EXITO");
						
						limpiarPantalla();		
						
						VentanaAdmin.pntBuscarEstacion.restaurarTabla();
						
						VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarEstacion,VentanaAdmin.n_pntBuscarEstacion);
						
					btn_guardar.setEnabled(true);
					btn_cancelar.setEnabled(true);
					SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().finalizarPantalla());
					
					
			} catch (InvocationTargetException | InterruptedException e1) {
				e1.printStackTrace();
			}
					
			};

			new Thread(r).start();
		}
				
	});
		btn_guardar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_guardar.setBounds(502, 159, 181, 46);
		add(btn_guardar);
		
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
		txtArea_obs.setFont(new Font("Monospaced", Font.ITALIC, 13));
		
		
		txtArea_obs.setBounds(54, 238, 353, 112);
		add(txtArea_obs);
		
		JLabel lblIdEstacion = new JLabel("ID Estacion");
		lblIdEstacion.setFont(new Font("Calibri", Font.BOLD, 16));
		lblIdEstacion.setBounds(54, 170, 176, 14);
		add(lblIdEstacion);
		
		tf_id_estacion = new JTextField();
		tf_id_estacion.setEditable(false);
		tf_id_estacion.setColumns(10);
		tf_id_estacion.setBounds(54, 183, 353, 20);
		add(tf_id_estacion);
	}
	
	public void limpiarPantalla() {
		txtArea_obs.setText(null);
	}
	
	public void cargarDatos() {
		tf_id_estacion.setText(Integer.toString(estDTO.getId()));
		if(estDTO.getEstado() == 0) {
			txtTituloMant.setText("INICIAR MANTENIMIENTO");
		}else {
			txtTituloMant.setText("FINALIZAR MANTENIMIENTO");
		}
	}
	
	public void cargarMantDTO() {
		
		if(estDTO.getEstado()==0) {
			mantDTO.setFecha_inicio(fecha);
			mantDTO.setFecha_fin(null);
			mantDTO.setId_estacion(estDTO.getId());
			mantDTO.setObserv(txtArea_obs.getText());
		}else {
			mantDTO.setFecha_fin(fecha);
			mantDTO.setId_estacion(estDTO.getId());
			mantDTO.setObserv(txtArea_obs.getText());
		}
		
	}
}
