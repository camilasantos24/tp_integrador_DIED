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

public class PntInicio extends JPanel {

	public PntInicio() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		JTextPane txtpnBoletos = new JTextPane();
		txtpnBoletos.setFont(new Font("Arial", Font.BOLD, 20));
		txtpnBoletos.setText("INICIO");
		txtpnBoletos.setEditable(false);
		txtpnBoletos.setBackground(SystemColor.menu);
		txtpnBoletos.setBounds(319, 24, 76, 28);
		add(txtpnBoletos);
		
		JButton btn_buscar_est = new JButton("BUSCAR ESTACI\u00D3N");
		btn_buscar_est.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarEstacion, VentanaAdmin.n_pntBuscarEstacion);
								
			}
		});
		

		btn_buscar_est.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_buscar_est.setBounds(103, 82, 545, 46);

		btn_buscar_est.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_buscar_est.setBounds(216, 83, 289, 46);

		add(btn_buscar_est);
		
		JButton btn_buscar_lt = new JButton("BUSCAR L\u00CDNEA DE TRANSPORTE");
		btn_buscar_lt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarLineaTransporte, VentanaAdmin.n_pntBuscarLineaTransporte);

				}
		});

		btn_buscar_lt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_buscar_lt.setBounds(103, 157, 545, 46);

		btn_buscar_lt.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_buscar_lt.setBounds(216, 158, 289, 46);
		add(btn_buscar_lt);
		
		JButton btn_comprar_boleto = new JButton("COMPRAR BOLETO");
		btn_comprar_boleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Runnable r = () -> {
					
					try {
						btn_comprar_boleto.setEnabled(false);
						SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().iniciarPantalla());
				
							PntVentaBoleto.llenarCBEstaciones();
							VentanaAdmin.cambiarPantalla(VentanaAdmin.pntVentaBoleto, VentanaAdmin.n_pntVentaBoleto);
							
						btn_comprar_boleto.setEnabled(true);
						SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().finalizarPantalla());
							
						} catch (InvocationTargetException | InterruptedException e1) {
							e1.printStackTrace();
						}
						
					};
					
					new Thread(r).start();
	
			}
		});
		btn_comprar_boleto.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_comprar_boleto.setBounds(216, 239, 289, 46);
		add(btn_comprar_boleto);
		
		JButton btn_info_general = new JButton("INFORMACI\u00D3N GENERAL");
		btn_info_general.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Runnable r = () -> {
					
					try {
						btn_info_general.setEnabled(false);
						SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().iniciarPantalla());
					
							try {
								VentanaAdmin.pnt_info_gral.cargar_page_rank();
								VentanaAdmin.pnt_info_gral.cargar_proximos_mantenimientos();
								VentanaAdmin.cambiarPantalla(VentanaAdmin.pnt_info_gral, VentanaAdmin.n_pntInfoGral);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
					
					btn_info_general.setEnabled(true);
					SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().finalizarPantalla());
					
					} catch (InvocationTargetException | InterruptedException e1) {
						e1.printStackTrace();
					}
				
				};
				
				new Thread(r).start();
			}
		});
		btn_info_general.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_info_general.setBounds(216, 319, 289, 46);
		add(btn_info_general);
		
	}
}
