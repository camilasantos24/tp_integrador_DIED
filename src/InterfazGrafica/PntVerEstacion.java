package InterfazGrafica;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JButton;

public class PntVerEstacion extends JPanel {

	
	public PntVerEstacion() {
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
		
		JScrollPane scp_tramos = new JScrollPane((Component) null);
		scp_tramos.setBounds(10, 46, 713, 149);
		add(scp_tramos);
		
		JScrollPane scp_tramos_1 = new JScrollPane((Component) null);
		scp_tramos_1.setBounds(10, 226, 713, 149);
		add(scp_tramos_1);
		
		JButton btnNewButton = new JButton("<< Volver");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(10, 386, 119, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Flujo M\u00E1ximo");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(604, 387, 119, 37);
		add(btnNewButton_1);

	}

}
