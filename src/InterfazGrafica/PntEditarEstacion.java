package InterfazGrafica;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

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
import java.awt.Component;

public class PntEditarEstacion extends JPanel {
	private JTextField tf_id;
	private JTextField tf_nombre;
	private JTextField tf_hs_apertura;
	private JTextField tf_hs_cierre;

	public PntEditarEstacion() {
		setBounds(100, 100, 733, 434);
		setLayout(null);
		
		JTextPane txtpnEstaciones = new JTextPane();
		txtpnEstaciones.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnEstaciones.setText("EDITAR ESTACI\u00D3N");
		txtpnEstaciones.setEditable(false);
		txtpnEstaciones.setBackground(SystemColor.menu);
		txtpnEstaciones.setBounds(292, 25, 171, 28);
		add(txtpnEstaciones);
		
		JLabel lblIdDeLa = new JLabel("ID");
		lblIdDeLa.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblIdDeLa.setBounds(54, 101, 176, 14);
		add(lblIdDeLa);
		
		tf_id = new JTextField();
		tf_id.setColumns(10);
		tf_id.setBounds(54, 114, 353, 20);
		add(tf_id);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(54, 145, 64, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Horario de apertura");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(54, 190, 133, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Horario de cierre");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(54, 236, 113, 14);
		add(lblNewLabel_3);
		
		JButton btn_guardar = new JButton("Guardar cambios");
		btn_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_guardar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_guardar.setBounds(502, 159, 181, 46);
		add(btn_guardar);
		
		JLabel lblNewLabel_3_1 = new JLabel("Estado");
		lblNewLabel_3_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(54, 281, 46, 14);
		add(lblNewLabel_3_1);
		
		JComboBox cb_estado = new JComboBox();
		cb_estado.setFont(new Font("Calibri", Font.PLAIN, 15));
		cb_estado.setBounds(54, 293, 353, 20);
		add(cb_estado);
		
		tf_nombre = new JTextField();
		tf_nombre.setColumns(10);
		tf_nombre.setBounds(54, 159, 353, 20);
		add(tf_nombre);
		
		tf_hs_apertura = new JTextField();
		tf_hs_apertura.setColumns(10);
		tf_hs_apertura.setBounds(54, 204, 353, 20);
		add(tf_hs_apertura);
		
		tf_hs_cierre = new JTextField();
		tf_hs_cierre.setColumns(10);
		tf_hs_cierre.setBounds(54, 250, 353, 20);
		add(tf_hs_cierre);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_cancelar.setBounds(502, 224, 181, 46);
		add(btn_cancelar);
		
	}
}
