package InterfazGrafica;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.Button;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;

public class PntBuscarEstacion extends JPanel {
	private JTextField tf_id;
	private JTextField tf_nombre;
	private JTextField tf_hs_apertura;
	private JTextField tf_hs_cierre;
	
	JComboBox cb_estado = new JComboBox();
	
	public static JTable table = new JTable();
	public static DefaultTableModel dm = new DefaultTableModel(){
		public boolean isCellEditable(int rowIndex, int columnIndex ) {
			return false;
		}
	};

	public PntBuscarEstacion() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		JTextPane txtpnEstaciones = new JTextPane();
		txtpnEstaciones.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnEstaciones.setText("ESTACIONES");
		txtpnEstaciones.setEditable(false);
		txtpnEstaciones.setBackground(SystemColor.menu);
		txtpnEstaciones.setBounds(298, 25, 123, 28);
		add(txtpnEstaciones);
		
		JLabel lblIdDeLa = new JLabel("ID");
		lblIdDeLa.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblIdDeLa.setBounds(22, 64, 176, 14);
		add(lblIdDeLa);
		
		tf_id = new JTextField();
		tf_id.setColumns(10);
		tf_id.setBounds(22, 77, 168, 20);
		add(tf_id);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(22, 108, 64, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Horario de apertura");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(22, 153, 133, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Horario de cierre");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(22, 199, 113, 14);
		add(lblNewLabel_3);
		
		JButton btn_buscar = new JButton("Buscar");
		btn_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_buscar.setBounds(58, 299, 77, 23);
		add(btn_buscar);
		
		dm.addColumn("IDEstacion");
		dm.addColumn("Nombre");
		dm.addColumn("HoraApertura");
		dm.addColumn("HoraCierre");
		dm.addColumn("Estado");
		
		table.setModel(dm); //table tendra las columnas de dm que agregamos aqui arriba
		
		JScrollPane sp_listar_est = new JScrollPane((Component) null);
		sp_listar_est.setBounds(215, 64, 492, 212);
		add(sp_listar_est);
		
		JButton btn_alta_est = new JButton("Dar de alta una estaci\u00F3n");
		btn_alta_est.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_alta_est.setBounds(47, 355, 181, 46);
		add(btn_alta_est);
		
		JLabel lblNewLabel_3_1 = new JLabel("Estado");
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(22, 244, 46, 14);
		add(lblNewLabel_3_1);
		
		
		cb_estado.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_estado.setBounds(22, 256, 168, 20);
		add(cb_estado);
		
		JButton btn_baja_est = new JButton("Dar de baja estaci\u00F3n");
		btn_baja_est.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_baja_est.setBounds(275, 355, 181, 46);
		add(btn_baja_est);
		
		JButton btn_editar_est = new JButton("Editar estaci\u00F3n");
		btn_editar_est.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_editar_est.setBounds(503, 355, 181, 46);
		add(btn_editar_est);
		
		tf_nombre = new JTextField();
		tf_nombre.setColumns(10);
		tf_nombre.setBounds(22, 122, 168, 20);
		add(tf_nombre);
		
		tf_hs_apertura = new JTextField();
		tf_hs_apertura.setColumns(10);
		tf_hs_apertura.setBounds(22, 167, 168, 20);
		add(tf_hs_apertura);
		
		tf_hs_cierre = new JTextField();
		tf_hs_cierre.setColumns(10);
		tf_hs_cierre.setBounds(22, 213, 168, 20);
		add(tf_hs_cierre);
		
	}
}
