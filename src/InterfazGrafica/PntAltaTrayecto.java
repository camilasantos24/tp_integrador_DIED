package InterfazGrafica;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PntAltaTrayecto extends JPanel {
	private JTextField tf_codigoDestino;
	private JTextField tf_distancia;
	private JTextField tf_costo;
	private JTextField tf_codigoOrigen;
	//commit
	private  DefaultTableModel dm = new DefaultTableModel();
	private  JTable table = new JTable();
	
	
	public PntAltaTrayecto() {
		setLayout(null);
		
		dm.addColumn("id_Origen");
		dm.addColumn("id_Destino");
		dm.addColumn("Origen");
		dm.addColumn("Destino");
		dm.addColumn("Distancia");
		dm.addColumn("Duracion");
		dm.addColumn("CantidadPasajeros");
		dm.addColumn("Costo");
		
		table.setModel(dm);
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.doLayout();
		
		table.getColumnModel().getColumn(1).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setMinWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(0);
		table.doLayout();
				
		table.getColumnModel().getColumn(5).setMaxWidth(0);
		table.getColumnModel().getColumn(5).setMinWidth(0);
		table.getColumnModel().getColumn(5).setPreferredWidth(0);
		table.doLayout();
		
		table.getColumnModel().getColumn(6).setMaxWidth(0);
		table.getColumnModel().getColumn(6).setMinWidth(0);
		table.getColumnModel().getColumn(6).setPreferredWidth(0);
		table.doLayout();
		
		JScrollPane scp_tramos = new JScrollPane(table);
		scp_tramos.setBounds(291, 58, 432, 268);
		add(scp_tramos);
		
		JLabel lblNewLabel = new JLabel("NUEVO TRAYECTO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 0, 713, 31);
		add(lblNewLabel);
		
		tf_codigoDestino = new JTextField();
		tf_codigoDestino.setBounds(20, 116, 201, 31);
		add(tf_codigoDestino);
		tf_codigoDestino.setColumns(10);
		
		tf_distancia = new JTextField();
		tf_distancia.setColumns(10);
		tf_distancia.setBounds(20, 172, 241, 31);
		add(tf_distancia);
		
		tf_costo = new JTextField();
		tf_costo.setColumns(10);
		tf_costo.setBounds(20, 327, 241, 31);
		add(tf_costo);
		
		tf_codigoOrigen = new JTextField();
		tf_codigoOrigen.setBounds(20, 60, 201, 31);
		add(tf_codigoOrigen);
		tf_codigoOrigen.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Origen (C\u00F3digo)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 35, 194, 21);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Destino (C\u00F3digo)");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(20, 91, 194, 21);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Distancia (en km)");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(20, 147, 224, 21);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Duraci\u00F3n (en minutos)");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(20, 204, 224, 21);
		add(lblNewLabel_4);
		
		JSpinner sp_duracion = new JSpinner();
		sp_duracion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		sp_duracion.setBounds(20, 224, 241, 31);
		add(sp_duracion);
		
		JLabel lblNewLabel_4_1 = new JLabel("Cantidad m\u00E1xima de pasajeros");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(20, 255, 224, 21);
		add(lblNewLabel_4_1);
		
		JSpinner sp_cantPasajeros = new JSpinner();
		sp_cantPasajeros.setFont(new Font("Tahoma", Font.PLAIN, 13));
		sp_cantPasajeros.setBounds(20, 276, 241, 31);
		add(sp_cantPasajeros);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Costo ($)");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1_1.setBounds(20, 305, 224, 21);
		add(lblNewLabel_4_1_1);
		
		ImageIcon icono_lupa= new ImageIcon(getClass().getResource("/recursos/lupa2.jpg"));

		JButton btn_buscarOrigen = new JButton("");
		btn_buscarOrigen.setBorder(null);
		btn_buscarOrigen.setBackground(new Color(255, 255, 255));
		btn_buscarOrigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameSeleccionarEstacion.getInstance().setVisible(true);
			}
		});
		btn_buscarOrigen.setIcon(icono_lupa);
		btn_buscarOrigen.setBounds(222, 60, 39, 31);
		add(btn_buscarOrigen);
		
		JButton btn_buscarDestino = new JButton("");
		btn_buscarDestino.setBorder(null);
		btn_buscarDestino.setBackground(new Color(255, 255, 255));
		btn_buscarDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameSeleccionarEstacion.getInstance().setVisible(true);

			}
		});
		btn_buscarDestino.setIcon(icono_lupa);
		btn_buscarDestino.setBounds(222, 116, 39, 31);
		add(btn_buscarDestino);
		
		JButton btn_agregarTramo = new JButton("+");
		btn_agregarTramo.setBackground(new Color(50, 205, 50));
		btn_agregarTramo.setForeground(new Color(255, 255, 255));
		btn_agregarTramo.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_agregarTramo.setBounds(291, 327, 52, 31);
		add(btn_agregarTramo);
		
		JButton btn_quitarTramo = new JButton("-");
		btn_quitarTramo.setForeground(Color.WHITE);
		btn_quitarTramo.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_quitarTramo.setBackground(new Color(204, 51, 0));
		btn_quitarTramo.setBounds(355, 327, 52, 31);
		add(btn_quitarTramo);
		
		JButton btn_guardar = new JButton("Guardar");
		btn_guardar.setForeground(new Color(0, 102, 0));
		btn_guardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_guardar.setBounds(469, 382, 109, 31);
		add(btn_guardar);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_cancelar.setBounds(289, 382, 109, 31);
		add(btn_cancelar);
		
		JLabel lblNewLabel_5 = new JLabel("Tramos");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(291, 33, 432, 21);
		add(lblNewLabel_5);

	}
}
