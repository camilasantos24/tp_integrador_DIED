package InterfazGrafica;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

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
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PntVentaBoleto extends JPanel {
	
	public static JComboBox cb_est_origen = new JComboBox();
	public static JComboBox cb_est_destino = new JComboBox();
	public static JComboBox cb_filtro = new JComboBox();
	
	JButton btn_cancelar = new JButton("Cancelar");
	
	public static List<Integer> idEstacion= new ArrayList();
	
	public static JTable table = new JTable();
	public static DefaultTableModel dm = new DefaultTableModel(){
		public boolean isCellEditable(int rowIndex, int columnIndex ) {
			return false;
		}
	};
	

	public PntVentaBoleto() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		JTextPane txtpnBoletos = new JTextPane();
		txtpnBoletos.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnBoletos.setText("VENTA DE BOLETOS");
		txtpnBoletos.setEditable(false);
		txtpnBoletos.setBackground(SystemColor.menu);
		txtpnBoletos.setBounds(253, 25, 242, 28);
		add(txtpnBoletos);
		
		JButton btn_ver_caminos = new JButton("Ver caminos");
		btn_ver_caminos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Runnable r =()->{ 
					
					try {
						
					btn_ver_caminos.setEnabled(false);
					btn_cancelar.setEnabled(false);
					SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().iniciarPantalla());
					
				
						restaurarTabla();
						
						int id_o;
						int id_d;
						int filtro=cb_filtro.getSelectedIndex();
						List<Trayecto> trayectos;
						List<Tramo> listaTramos= new ArrayList();
						List<Estacion> listaEstaciones= new ArrayList();
						List<LineaTransporte> listaLineas= new ArrayList();
						List<String>listaLineas_nombres= new ArrayList();
						Grafo grafo=null;
						
						id_o= idEstacion.get(cb_est_origen.getSelectedIndex());
						id_d= idEstacion.get(cb_est_destino.getSelectedIndex());
						
						try {
							trayectos=GestorTrayecto.get_all_trayectos();	
							
							if(!trayectos.isEmpty()) {
		
								grafo= armarGrafo(trayectos, listaTramos, listaEstaciones, listaLineas, listaLineas_nombres, grafo);
								
								Estacion origen = EstacionDAO.getInstance().get_estacion_by_id(id_o);
								Estacion destino =EstacionDAO.getInstance().get_estacion_by_id(id_d);
								

								SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().cargaDatosSinValor());
								
								if(cb_filtro.getSelectedIndex()!=0) {	
									List<Vertice> caminosMenorPeso=GestorVenta.get_camino_de_menor_peso(grafo, cb_filtro.getSelectedIndex(), origen, destino);
									cargarTablaFiltro(grafo,caminosMenorPeso);
									System.out.println(caminosMenorPeso);
								}else {
									List<List<Vertice>> caminos=grafo.paths(origen.getNombre(), destino.getNombre());
									cargarTablaTodos(grafo, caminos);
									System.out.println(caminos);
								}
							}else {
								VentanaAdmin.mensajeError("No existen trayectos para las estaciones seleccionadas.\nPor favor selecciones nuevas.", "ERROR");
							}
							
							btn_ver_caminos.setEnabled(true);
							btn_cancelar.setEnabled(true);
							SwingUtilities.invokeAndWait(() ->PntCarga.getInstance().finalizarPantalla());
								
								
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					} catch (InvocationTargetException | InterruptedException e1) {
						e1.printStackTrace();
					}
				};
				
				new Thread(r).start();
			}
					
		});
		
		dm.addColumn("Grafo");
		dm.addColumn("Caminos");
		dm.addColumn("Costo (pesos)");
		dm.addColumn("Distancia (km)");
		dm.addColumn("Duracion (min)");
		
		table.setModel(dm);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		
	
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.doLayout();
		
		btn_ver_caminos.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_ver_caminos.setBounds(501, 103, 181, 46);
		add(btn_ver_caminos);
		
		JLabel lblNewLabel_3_1 = new JLabel("Estaci\u00F3n origen");
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_3_1.setBounds(78, 76, 120, 14);
		add(lblNewLabel_3_1);
		
		
		cb_est_origen.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_est_origen.setBounds(78, 92, 353, 20);
		add(cb_est_origen);
		
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int respuesta = VentanaAdmin.mensajeConsulta(null, "ATENCION!", "¿Desea cancelar la carga de datos?\nSe perderá toda la información cargada.");
				if(respuesta==JOptionPane.YES_OPTION) {
					VentanaAdmin.cambiarPantalla(VentanaAdmin.pntInicio,VentanaAdmin.n_pntInicio);
					limpiarPantalla();	
					restaurarTabla();
				}
			}
		});
		btn_cancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_cancelar.setBounds(501, 168, 181, 46);
		add(btn_cancelar);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Estaci\u00F3n destino");
		lblNewLabel_3_1_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_3_1_1.setBounds(78, 136, 120, 14);
		add(lblNewLabel_3_1_1);
		
		cb_est_destino.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_est_destino.setBounds(78, 152, 353, 20);
		add(cb_est_destino);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("Filtrar por:");
		lblNewLabel_3_1_2.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_3_1_2.setBounds(78, 194, 120, 14);
		add(lblNewLabel_3_1_2);
		
		cb_filtro.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_filtro.setBounds(78, 212, 353, 20);
		cb_filtro.setModel(new DefaultComboBoxModel(new String[] {"Ninguno", "Camino más rápido", "Camino de menor distancia", "Camino más barato"}));
		add(cb_filtro);
		
		JScrollPane sp_caminos = new JScrollPane(table);
		sp_caminos.setBounds(41, 271, 564, 152);
		add(sp_caminos);
		
		JButton btn_mas_detalles = new JButton("M\u00E1s detalles");
		btn_mas_detalles.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_mas_detalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					if(table.getSelectedRow() != -1) {
					
					BoletoDTO boletoDTO= new BoletoDTO();
					
					boletoDTO.setGrafo((Grafo)table.getValueAt(table.getSelectedRow(), 0));
					boletoDTO.setCaminos((List<Vertice>) table.getValueAt(table.getSelectedRow(), 1));
					boletoDTO.setCosto(Float.parseFloat(table.getValueAt(table.getSelectedRow(), 2).toString().replace(",", ".")));
					boletoDTO.setFechaVenta(LocalDate.now());
					
					VentanaAdmin.pntVentaBoleto2.boletoDTO=boletoDTO;
					VentanaAdmin.pntVentaBoleto2.cargarDatos(boletoDTO);
					VentanaAdmin.cambiarPantalla(VentanaAdmin.pntVentaBoleto2, VentanaAdmin.n_pntVentaBoleto2);
					
				}
				else {
					VentanaAdmin.mensajeError("Seleccione un trayecto de la tabla", "ERROR");
				}
				
			}
		});
		btn_mas_detalles.setBounds(615, 305, 108, 54);
		add(btn_mas_detalles);
		
		JLabel lblNewLabel_3_1_2_1 = new JLabel("Seleccione un trayecto:");
		lblNewLabel_3_1_2_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_3_1_2_1.setBounds(41, 254, 181, 14);
		add(lblNewLabel_3_1_2_1);
		
		JButton btnNewButton = new JButton("\u2190 Atr\u00E1s");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pntInicio, VentanaAdmin.n_pntInicio);
				limpiarPantalla();
				restaurarTabla();
			}
		});
		btnNewButton.setBounds(41, 25, 89, 23);
		add(btnNewButton);
		
	}
	
	public void limpiarPantalla() {
		
		cb_est_origen.setSelectedIndex(0);
		cb_est_destino.setSelectedIndex(0);
		cb_filtro.setSelectedIndex(0);
	}
	
	public static void llenarCBEstaciones() {
		
		List<Estacion> listaEstaciones;
		cb_est_origen.removeAllItems();
		cb_est_destino.removeAllItems();
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
	
	public Grafo generarGrafo(List<Tramo> tramos, List<Estacion> estaciones, List<String> lineas) {
		
		Grafo<String> grafo1 = new Grafo<String>();
		
		for (int i = 0; i < estaciones.size(); i++) {
			grafo1.addNodo(estaciones.get(i).getNombre());
		}
		
		for (int i = 0; i < tramos.size(); i++) {
			grafo1.conectar(tramos.get(i).getEstacion_origen().getNombre(), tramos.get(i).getEstacion_destino().getNombre(),tramos.get(i).getDistancia_km(), tramos.get(i).getDuracion(), tramos.get(i).getCosto(), lineas);
		}
		
		return grafo1;
	}
	
	public boolean existeNodo(Grafo grafo, String nombEst) {
		if(grafo.getNodo(nombEst) != null) {
		if((grafo.getNodo(nombEst).getValue()).equals(nombEst)) {
			return true;
			}else {
				return false;
			}
		}else {return false;}
	}
	
	public void cargarTablaFiltro(Grafo grafo, List<Vertice> camino) {
		DecimalFormat df = new DecimalFormat("###.##");
		
		List<Vertice> caminos= camino;
		double costo;
		double distancia;
		double duracion;
		
		costo= GestorVenta.get_peso_camino(grafo, caminos, 3);
		distancia= GestorVenta.get_peso_camino(grafo, caminos, 2);
		duracion= GestorVenta.get_peso_camino(grafo, caminos, 1);
		
		Object[] rowData= {grafo, caminos, df.format(costo), df.format(distancia), df.format(duracion)};
		dm.addRow(rowData);
		
		
		}
	
	public void cargarTablaTodos(Grafo grafo, List<List<Vertice>> caminos) {
		DecimalFormat df = new DecimalFormat("###.##");
		
		double costo;
		double distancia;
		double duracion;
		
		for (int i = 0; i < caminos.size(); i++) {
			
			costo= GestorVenta.get_peso_camino(grafo, caminos.get(i), 3);
			distancia= GestorVenta.get_peso_camino(grafo, caminos.get(i), 2);
			duracion= GestorVenta.get_peso_camino(grafo, caminos.get(i), 1);
			
			Object[] rowData= {grafo, caminos.get(i), df.format(costo), df.format(distancia), df.format(duracion)};
			dm.addRow(rowData);
		}
	}
	
	public static void restaurarTabla() {
		 for( int i = dm.getRowCount() - 1; i >= 0; i-- ) {
	          dm.removeRow(i);
	      }
		}
	
	public List<String> obtenerNombres(List<LineaTransporte> lineas){
		List<String> nombresLineas= new ArrayList();;
		
		for (int i=0; i<lineas.size(); i++) {
			nombresLineas.add(lineas.get(i).getNombre());
		}
		
		return nombresLineas;
	}
	
	public Grafo armarGrafo(List<Trayecto> trayectos,List<Tramo> listaTramos, List<Estacion> listaEstaciones, List<LineaTransporte> listaLineas, List<String> listaLineas_nombres, Grafo grafo) {
	try {	
		for (int i = 0; i < trayectos.size(); i++) {	// Por cada trayecto buscamos sus tramos, estaciones activas y lineas tambien activas.
			
			listaTramos=trayectos.get(i).getTramos();
			listaEstaciones=trayectos.get(i).getEstaciones();
			listaLineas=GestorLineaTransporte.obtenerLineasPorTrayecto(trayectos.get(i).getId());
			listaLineas_nombres= obtenerNombres(listaLineas);
			
			if (grafo==null) {					// Si no existe grafo lo crea. Si existe compara si los nodos existen, si no existen tampoco los agrega y conecta.
			grafo=generarGrafo(listaTramos, listaEstaciones, listaLineas_nombres);
			
			}else {
				for(int j=0; j<listaTramos.size(); j++) {
					
					if(!existeNodo(grafo, listaTramos.get(j).getEstacion_origen().getNombre())) {	
						grafo.addNodo(listaTramos.get(j).getEstacion_origen().getNombre());
					}
					if(!existeNodo(grafo, listaTramos.get(j).getEstacion_destino().getNombre())) {
						grafo.addNodo(listaTramos.get(j).getEstacion_destino().getNombre());
					}
					
					if(!grafo.validar_conexion_vertices(listaTramos.get(j).getEstacion_origen().getNombre(), listaTramos.get(j).getEstacion_destino().getNombre())) {
					grafo.conectar(listaTramos.get(j).getEstacion_origen().getNombre(), listaTramos.get(j).getEstacion_destino().getNombre(), listaTramos.get(j).getDistancia_km(), listaTramos.get(j).getDuracion(), listaTramos.get(j).getCosto(), listaLineas_nombres);
					}
					
				}
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
		return grafo;
	}
}
