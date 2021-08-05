package InterfazGrafica;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalTime;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Entidades.Estacion;
import Gestores.GestorEstacion;

import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PntInformacionGeneral extends JPanel {

	private  DefaultTableModel dmPG = new DefaultTableModel();
	private  JTable tablePG = new JTable();
	private  DefaultTableModel dmPM = new DefaultTableModel();
	private  JTable tablePM = new JTable();
	
	public PntInformacionGeneral() {
		setLayout(null);
		
		JLabel lbl_titulo = new JLabel("Informacion General ");
		lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_titulo.setBounds(10, 0, 713, 26);
		add(lbl_titulo);
		
		JLabel lblPageRank = new JLabel("Page Rank");
		lblPageRank.setForeground(new Color(0, 0, 51));
		lblPageRank.setHorizontalAlignment(SwingConstants.CENTER);
		lblPageRank.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPageRank.setBounds(10, 26, 713, 20);
		add(lblPageRank);
		
		JLabel lblPrximo = new JLabel("Pr\u00F3ximo Mantenimiento");
		lblPrximo.setForeground(new Color(0, 0, 51));
		lblPrximo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrximo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrximo.setBounds(10, 202, 713, 26);
		add(lblPrximo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 202, 713, 2);
		add(separator);
		
		dmPG.addColumn("Código Estacion");
		dmPG.addColumn("Nombre");
		dmPG.addColumn("Page Rank");
		
		tablePG.setModel(dmPG);
		
		JScrollPane scp_pg = new JScrollPane(tablePG);
		scp_pg.setBounds(10, 46, 713, 149);
		add(scp_pg);
		
		
		dmPM.addColumn("Código Estacion");
		dmPM.addColumn("Nombre");
		dmPM.addColumn("Hs Apertura");
		dmPM.addColumn("Hs Cierre");
		dmPM.addColumn("Estado");
		dmPM.addColumn("Último Mantenimiento");
		tablePM.setModel(dmPM);
		
		JScrollPane scp_proxMant = new JScrollPane(tablePM);
		scp_proxMant.setBounds(10, 226, 713, 149);
		add(scp_proxMant);
		
		JButton btnNewButton = new JButton("\u2190 Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pntInicio, VentanaAdmin.n_pntInicio);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(10, 386, 119, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Flujo M\u00E1ximo \u2192");
		btnNewButton_1.setForeground(new Color(0, 0, 102));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin.cambiarPantalla(VentanaAdmin.pnt_flujo_max, VentanaAdmin.n_pntFlujoMax);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(567, 379, 156, 30);
		add(btnNewButton_1);
		
		try {
			cargar_page_rank();
			cargar_proximos_mantenimientos();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void cargar_page_rank () throws Exception {
		List<Object[]> pg = GestorEstacion.get_page_rank();
		if (tablePG.getRowCount() >0) {
			tablePG.removeRowSelectionInterval(0, tablePG.getRowCount()-1);
			}
			
			int tam = pg.size();
			int cantidad_columnas= tablePG.getColumnCount();
			
			if(cantidad_columnas !=0) {
				dmPG= new DefaultTableModel();
				tablePG.setModel(dmPG);
		
			}
			
			if(tam !=0) {
				
			int i=0;
			
			int codigo_estacion;
			String nombre_estacion;
			int page_rank;
			
			
			Object[]col0 = new Object[tam];
			Object[]col1= new Object[tam];
			Object[]col2 = new Object[tam];
			
			
			while(i<tam) {
				Estacion e = (Estacion) pg.get(i)[0];
				codigo_estacion = e.getId_estacion();
				nombre_estacion = e.getNombre();
				page_rank = Integer.parseInt(pg.get(i)[1].toString());
				
				col0[i]= codigo_estacion;
				col1[i]= nombre_estacion;
				col2[i]= page_rank;
				
				i++;
				
			}
			
			
			dmPG.addColumn("Código Estacion", col0);
			dmPG.addColumn("Nombre", col1);
			dmPG.addColumn("Page Rank", col2);
		
			
			}

		
	}
	
	public void cargar_proximos_mantenimientos() throws Exception {
		List<Object[]> e = GestorEstacion.get_lista_proximo_mantenimiento();
		
		if (tablePM.getRowCount() >0) {
			tablePM.removeRowSelectionInterval(0, tablePM.getRowCount()-1);
			}
			
			int tam = e.size();
			int cantidad_columnas= tablePM.getColumnCount();
			
			if(cantidad_columnas !=0) {
				dmPM= new DefaultTableModel();
				tablePM.setModel(dmPM);
		
			}
			
			if(tam !=0) {
				
			int i=0;
			
			int codigo_estacion;
			String nombre_estacion;
			LocalTime hs_apertura;
			LocalTime hs_cierre;
			String estado;
			
			
			
			Object[]col0 = new Object[tam];
			Object[]col1= new Object[tam];
			Object[]col2 = new Object[tam];
			Object[]col3 = new Object[tam];
			Object[]col4 = new Object[tam];
			Object[]col5 = new Object[tam];
			
			
			
			while(i<tam) {
				Estacion est= (Estacion) e.get(i)[0];
				codigo_estacion =est.getId_estacion();
				nombre_estacion =est.getNombre();
				hs_apertura =est.getHs_apertura();
				hs_cierre = est.getHs_cierre();
				
				if(est.getEstado() == 1) {
					estado= "Activa";
				}else {
					estado = "En Mantenimiento";
				}
				
				
				col0[i]= codigo_estacion;
				col1[i]= nombre_estacion;
				col2[i]= hs_apertura;
				col3[i]= hs_cierre;
				col4[i]= estado;
				

				if(e.get(i)[1] != null) {
					col5[i] =e.get(i)[1];
				}else {
					col5[i]= "Nunca";
				}
				
				
				i++;
				
			}
			
			
			dmPM.addColumn("Código Estacion", col0);
			dmPM.addColumn("Nombre", col1);
			dmPM.addColumn("Hs Apertura", col2);
			dmPM.addColumn("Hs Cierre", col3);
			dmPM.addColumn("Estado", col4);
			dmPM.addColumn("Último Mantenimiento", col5);
		
			
			}
	}

}
