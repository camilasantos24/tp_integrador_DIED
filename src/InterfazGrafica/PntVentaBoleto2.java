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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PntVentaBoleto2 extends JPanel {
	
	public static List<Integer> idEstacion= new ArrayList();
	
	public static JTable table = new JTable();
	public static DefaultTableModel dm = new DefaultTableModel(){
		public boolean isCellEditable(int rowIndex, int columnIndex ) {
			return false;
		}
	};
	private JTextField tf_trayecto;
	
	public BoletoDTO boletoDTO= new BoletoDTO();
	

	public PntVentaBoleto2() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		JTextPane txtpnBoletos = new JTextPane();
		txtpnBoletos.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnBoletos.setText("VENTA DE BOLETOS");
		txtpnBoletos.setEditable(false);
		txtpnBoletos.setBackground(SystemColor.menu);
		txtpnBoletos.setBounds(253, 25, 242, 28);
		add(txtpnBoletos);
		
		JButton btn_comprar_boleto = new JButton("Comprar");
		btn_comprar_boleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaAdmin.pntConfirmarVentaBoleto.boletoDTO=boletoDTO;
				VentanaAdmin.pntConfirmarVentaBoleto.cargarDatos(boletoDTO);
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pntConfirmarVentaBoleto, VentanaAdmin.n_pntConfirmarVentaBoleto);
			}
		});
		
		dm.addColumn("Origen");
		dm.addColumn("Destino");
		dm.addColumn("Costo (pesos)");
		dm.addColumn("Distancia (km)");
		dm.addColumn("Duración (min)");
		dm.addColumn("Línea");
		
		table.setModel(dm);
		
		btn_comprar_boleto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_comprar_boleto.setBounds(451, 377, 181, 46);
		add(btn_comprar_boleto);
		
		JButton btn_cancelar = new JButton("Atr\u00E1s");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pntVentaBoleto, VentanaAdmin.n_pntVentaBoleto);
				restaurarTabla();
			}
		});
		
		btn_cancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_cancelar.setBounds(107, 377, 181, 46);
		add(btn_cancelar);
		
		JScrollPane sp_boletos = new JScrollPane(table);
		sp_boletos.setBounds(44, 102, 648, 257);
		add(sp_boletos);
		
		JLabel lblNewLabel_3_1 = new JLabel("Detalles del trayecto:");
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(44, 74, 141, 14);
		add(lblNewLabel_3_1);
		
		tf_trayecto = new JTextField();
		tf_trayecto.setEditable(false);
		tf_trayecto.setBounds(182, 71, 199, 20);
		add(tf_trayecto);
		tf_trayecto.setColumns(10);
		
	}
	
	public void cargarDatos(BoletoDTO boletoDTO) {
		Grafo grafo= boletoDTO.getGrafo();
		List<Vertice> camino= boletoDTO.getCaminos();
		List<String> lineas;
		float costo;
		float distancia;
		float duracion;
		Vertice inicio;
		Vertice fin;
		
		for (int i = 1; i < camino.size(); i++) {
			inicio=camino.get(i-1);
			fin= camino.get(i);
			costo= grafo.findAristas(inicio.toString(), fin.toString()).getCosto();
			distancia= grafo.findAristas(inicio.toString(), fin.toString()).getDistancia();
			duracion=grafo.findAristas(inicio.toString(), fin.toString()).getDuracion();
			lineas=grafo.findAristas(inicio.toString(), fin.toString()).getLineaTransp();
			
			Object[] rowData= {inicio, fin, costo, distancia, duracion, lineas};
			dm.addRow(rowData);
			
		}
		
		tf_trayecto.setText(camino.toString());
	}
	
	public static void restaurarTabla() {
		 for( int i = dm.getRowCount() - 1; i >= 0; i-- ) {
	          dm.removeRow(i);
	      }
		}
}
