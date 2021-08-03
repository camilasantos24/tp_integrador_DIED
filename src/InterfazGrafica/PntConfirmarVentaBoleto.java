package InterfazGrafica;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import DAO.EstacionDAO;
import DTO.BoletoDTO;
import DTO.EstacionesDTO;
import DTO.UsuarioDTO;
import Entidades.Estacion;
import Entidades.LineaTransporte;
import Entidades.Tramo;
import Entidades.Trayecto;
import Gestores.GestorEstacion;
import Gestores.GestorLineaTransporte;
import Gestores.GestorTrayecto;
import Gestores.GestorVenta;
import Grafo.Grafo;
import Grafo.Vertice;

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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PntConfirmarVentaBoleto extends JPanel {
	
	private JTextField tf_nro_boleto;
	
	public BoletoDTO boletoDTO= new BoletoDTO();
	private JTextField tf_fecha;
	private JTextField tf_camino;
	private JTextField tf_origen;
	private JTextField tf_destino;
	private JTextField tf_costo;
	private JTextField tf_nombre_cliente;
	private JTextField tf_correo;
	
	JButton btn_comprar_boleto = new JButton("Finalizar compra");
	JButton btn_continuar = new JButton("Continuar");
	

	public PntConfirmarVentaBoleto() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		btn_continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pntInicio, VentanaAdmin.n_pntInicio);
				limpiarPantalla();
			}
		});
		
		
		btn_continuar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_continuar.setBounds(107, 377, 525, 46);
		btn_continuar.setVisible(false);
		add(btn_continuar);
		
		JTextPane txtpnBoletos = new JTextPane();
		txtpnBoletos.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnBoletos.setText("VENTA DE BOLETOS");
		txtpnBoletos.setEditable(false);
		txtpnBoletos.setBackground(SystemColor.menu);
		txtpnBoletos.setBounds(253, 25, 242, 28);
		add(txtpnBoletos);
		
		
		btn_comprar_boleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(validarNombre()) {
					if(validarCorreo()) {
						UsuarioDTO usuDTO= new UsuarioDTO();
						Vertice inicio= boletoDTO.getCaminos().get(0);
						Vertice fin = boletoDTO.getCaminos().get(boletoDTO.getCaminos().size()-1);
						
						boletoDTO.setInicio(inicio);
						boletoDTO.setFin(fin);
						
						usuDTO.setCorreo(tf_correo.getText());
						usuDTO.setNombre(tf_nombre_cliente.getText());
						
						GestorVenta.cargar_compra(boletoDTO, usuDTO);
						VentanaAdmin.mensajeExito("Boleto adquirido correctamente", "ÉXITO");
						recargarPantalla(boletoDTO);
						
					}else {
						VentanaAdmin.mensajeError("Por favor ingrese un correo electrónico.", "ERROR");
					}
				}else {
					VentanaAdmin.mensajeError("Por favor ingrese un nombre.", "ERROR");
				}
				
			}
		});
		
		btn_comprar_boleto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_comprar_boleto.setBounds(451, 377, 181, 46);
		add(btn_comprar_boleto);
		
		JButton btn_atras = new JButton("Atr\u00E1s");
		btn_atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pntVentaBoleto2, VentanaAdmin.n_pntVentaBoleto2);	
				limpiarPantalla();
			}
		});
		
		btn_atras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_atras.setBounds(107, 377, 181, 46);
		add(btn_atras);
		
		JLabel lblNewLabel_3_1 = new JLabel("N\u00FAmero de boleto");
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(44, 74, 127, 14);
		add(lblNewLabel_3_1);
		
		tf_nro_boleto = new JTextField();
		tf_nro_boleto.setEnabled(false);
		tf_nro_boleto.setEditable(false);
		tf_nro_boleto.setBounds(182, 71, 199, 20);
		add(tf_nro_boleto);
		tf_nro_boleto.setColumns(10);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Fecha");
		lblNewLabel_3_1_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1_1.setBounds(44, 105, 127, 14);
		add(lblNewLabel_3_1_1);
		
		tf_fecha = new JTextField();
		tf_fecha.setEnabled(false);
		tf_fecha.setEditable(false);
		tf_fecha.setColumns(10);
		tf_fecha.setBounds(182, 102, 199, 20);
		add(tf_fecha);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("Camino");
		lblNewLabel_3_1_2.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1_2.setBounds(44, 133, 127, 14);
		add(lblNewLabel_3_1_2);
		
		tf_camino = new JTextField();
		tf_camino.setEnabled(false);
		tf_camino.setEditable(false);
		tf_camino.setColumns(10);
		tf_camino.setBounds(182, 130, 199, 20);
		add(tf_camino);
		
		JLabel lblNewLabel_3_1_3 = new JLabel("Estacion origen");
		lblNewLabel_3_1_3.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1_3.setBounds(44, 161, 127, 14);
		add(lblNewLabel_3_1_3);
		
		tf_origen = new JTextField();
		tf_origen.setEnabled(false);
		tf_origen.setEditable(false);
		tf_origen.setColumns(10);
		tf_origen.setBounds(182, 158, 199, 20);
		add(tf_origen);
		
		JLabel lblNewLabel_3_1_4 = new JLabel("Estacion destino");
		lblNewLabel_3_1_4.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1_4.setBounds(44, 189, 127, 14);
		add(lblNewLabel_3_1_4);
		
		tf_destino = new JTextField();
		tf_destino.setEnabled(false);
		tf_destino.setEditable(false);
		tf_destino.setColumns(10);
		tf_destino.setBounds(182, 186, 199, 20);
		add(tf_destino);
		
		JLabel lblNewLabel_3_1_5 = new JLabel("Costo total");
		lblNewLabel_3_1_5.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1_5.setBounds(44, 217, 127, 14);
		add(lblNewLabel_3_1_5);
		
		tf_costo = new JTextField();
		tf_costo.setEnabled(false);
		tf_costo.setEditable(false);
		tf_costo.setColumns(10);
		tf_costo.setBounds(182, 214, 199, 20);
		add(tf_costo);
		
		JLabel lblNewLabel_3_1_6 = new JLabel("Nombre");
		lblNewLabel_3_1_6.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1_6.setBounds(44, 307, 127, 14);
		add(lblNewLabel_3_1_6);
		
		JLabel lblNewLabel_3_1_7 = new JLabel("Correo electr\u00F3nico");
		lblNewLabel_3_1_7.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1_7.setBounds(44, 333, 127, 14);
		add(lblNewLabel_3_1_7);
		
		tf_nombre_cliente = new JTextField();
		tf_nombre_cliente.setBounds(182, 304, 199, 20);
		add(tf_nombre_cliente);
		tf_nombre_cliente.setColumns(10);
		
		tf_correo = new JTextField();
		tf_correo.setColumns(10);
		tf_correo.setBounds(182, 330, 199, 20);
		add(tf_correo);
		
		JTextPane txtpnDatosDelCliente = new JTextPane();
		txtpnDatosDelCliente.setText("DATOS DEL CLIENTE");
		txtpnDatosDelCliente.setFont(new Font("Arial", Font.BOLD, 16));
		txtpnDatosDelCliente.setEditable(false);
		txtpnDatosDelCliente.setBackground(SystemColor.menu);
		txtpnDatosDelCliente.setBounds(46, 259, 242, 28);
		add(txtpnDatosDelCliente);
		
	}
	
	public void cargarDatos(BoletoDTO btoDTO) {
		List<Vertice> camino= btoDTO.getCaminos();
		LocalDate fechaVenta= btoDTO.getFechaVenta();
		float costo = btoDTO.getCosto();
		Vertice inicio= btoDTO.getCaminos().get(0);
		Vertice fin = btoDTO.getCaminos().get(btoDTO.getCaminos().size()-1);
		
		tf_nro_boleto.setText("AÚN NO DISPONIBLE");
		tf_camino.setText(camino.toString());
		tf_fecha.setText(fechaVenta.toString());
		tf_costo.setText(Float.toString(costo));
		tf_origen.setText(inicio.toString());
		tf_destino.setText(fin.toString());
	}
	
	public void limpiarPantalla() {
		tf_nro_boleto.setText(null);
		tf_camino.setText(null);
		tf_origen.setText(null);
		tf_destino.setText(null);
		tf_fecha.setText(null);
		tf_costo.setText(null);
		tf_nombre_cliente.setText(null);
		tf_correo.setText(null);
		tf_nombre_cliente.setEditable(true);
		tf_correo.setEditable(true);
		btn_comprar_boleto.setEnabled(true);
		btn_continuar.setVisible(false);
	}
	
	public boolean validarNombre() {
		if(tf_nombre_cliente.getText()==null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean validarCorreo() {
		if(tf_correo.getText()==null) {
			return false;
		}else {
			return true;
		}
	}
	
	public void recargarPantalla(BoletoDTO bolDTO) {
		int nro_boleto=0;
		
		nro_boleto=GestorVenta.obtenerNumeroBoleto(bolDTO);
		tf_nro_boleto.setText(Integer.toString(nro_boleto));
		tf_correo.setEditable(false);
		tf_nombre_cliente.setEditable(false);
		btn_continuar.setVisible(true);
	}
}
