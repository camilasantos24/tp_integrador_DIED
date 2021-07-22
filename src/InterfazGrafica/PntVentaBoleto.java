package InterfazGrafica;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

import DTO.EstacionesDTO;
import Entidades.Estacion;
import Entidades.Tramo;
import Gestores.GestorEstacion;
import Gestores.GestorTrayecto;

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

public class PntVentaBoleto extends JPanel {
	
	public static JComboBox cb_est_origen = new JComboBox();
	public static JComboBox cb_est_destino = new JComboBox();
	public static JComboBox cb_filtro = new JComboBox();
	
	public static List<Integer> idEstacion= new ArrayList();
	

	public PntVentaBoleto() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		JTextPane txtpnBoletos = new JTextPane();
		txtpnBoletos.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnBoletos.setText("VENTA DE BOLETOS");
		txtpnBoletos.setEditable(false);
		txtpnBoletos.setBackground(SystemColor.menu);
		txtpnBoletos.setBounds(292, 25, 190, 28);
		add(txtpnBoletos);
		
		JButton btn_ver_caminos = new JButton("Ver caminos");
		btn_ver_caminos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int id_o;
				int id_d;
				List<Tramo> listaTramos= new ArrayList();
				
				id_o= idEstacion.get(cb_est_origen.getSelectedIndex());
				id_d= idEstacion.get(cb_est_destino.getSelectedIndex());
				
				try {
					listaTramos=GestorTrayecto.obtener_tramos_origen_destino(id_o, id_d);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				for (int j = 0; j < listaTramos.size(); j++) {
					System.out.println(listaTramos.get(j).getId());		//CONTROLAR
				}
				
								
			}
		});
		
		btn_ver_caminos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_ver_caminos.setBounds(505, 91, 181, 46);
		add(btn_ver_caminos);
		
		JLabel lblNewLabel_3_1 = new JLabel("Estaci\u00F3n origen");
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(82, 64, 120, 14);
		add(lblNewLabel_3_1);
		
		
		cb_est_origen.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_est_origen.setBounds(82, 80, 353, 20);
		add(cb_est_origen);
		
		JButton btn_cancelar = new JButton("Cancelar");
		/*btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int respuesta = VentanaAdmin.mensajeConsulta(null, "ATENCION!", "¿Desea cancelar la carga de datos?\nSe perderá toda la información cargada.");
				if(respuesta==JOptionPane.YES_OPTION) {
					VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarEstacion,VentanaAdmin.n_pntBuscarEstacion);
					limpiarPantalla();		
				}
			}
		});*/
		btn_cancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_cancelar.setBounds(505, 156, 181, 46);
		add(btn_cancelar);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Estaci\u00F3n destino");
		lblNewLabel_3_1_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1_1.setBounds(82, 124, 120, 14);
		add(lblNewLabel_3_1_1);
		
		cb_est_destino.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_est_destino.setBounds(82, 140, 353, 20);
		add(cb_est_destino);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("Filtrar por:");
		lblNewLabel_3_1_2.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1_2.setBounds(82, 182, 120, 14);
		add(lblNewLabel_3_1_2);
		
		cb_filtro.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_filtro.setBounds(82, 200, 353, 20);
		cb_filtro.setModel(new DefaultComboBoxModel(new String[] {"Ninguno", "Camino más rápido", "Camino de menor distancia", "Camino más barato"}));
		add(cb_filtro);
		
	}
	
	public void limpiarPantalla() {
		
		cb_est_origen.setSelectedIndex(0);
		cb_est_destino.setSelectedIndex(0);
		cb_filtro.setSelectedIndex(0);
	}
	
	public static void llenarCBEstaciones() {
		
		List<Estacion> listaEstaciones;
		try {
			
			listaEstaciones = GestorEstacion.obtenerEstacionesBoleto();
			
			String nombre;
			int id;
			
			for (int i = 0; i < listaEstaciones.size(); i++) {
				nombre=listaEstaciones.get(i).getNombre();
				id=listaEstaciones.get(i).getId_estacion();
				idEstacion.add(id);
				cb_est_origen.addItem(nombre);
				cb_est_destino.addItem(nombre);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
