package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Utilities;
import javax.swing.JLabel;
import java.awt.Label;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Color;

public class PntCarga extends JFrame {

	private JPanel contentPane;
	JLabel lbl_Carga = new JLabel("CARGANDO");
	JLabel lbl_img1 = new JLabel("");
	JLabel lbl_img_2 = new JLabel("");
	
	private static PntCarga _INSTANCE;	
	
	public static PntCarga getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE= new PntCarga();
			
		}
		return _INSTANCE;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PntCarga frame = new PntCarga();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PntCarga() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 319);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbl_Carga.setFont(new Font("Tekton Pro", Font.PLAIN, 22));
		lbl_Carga.setHorizontalAlignment(SwingConstants.CENTER);
		
		lbl_Carga.setBounds(210, 212, 358, 66);
		contentPane.add(lbl_Carga);
		
		
		lbl_img1.setIcon(new ImageIcon(PntCarga.class.getResource("/recursos/hombre_reloj_1.png")));
		lbl_img1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_img1.setVisible(false);
		lbl_img1.setBounds(22, 21, 227, 269);
		contentPane.add(lbl_img1);
		
		
		lbl_img_2.setIcon(new ImageIcon(PntCarga.class.getResource("/recursos/hombre_reloj_2.png")));
		lbl_img_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_img_2.setVisible(false);
		lbl_img_2.setBounds(22, 21, 227, 269);
		contentPane.add(lbl_img_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(PntCarga.class.getResource("/recursos/loading-11.gif")));
		lblNewLabel.setBounds(301, 74, 180, 139);
		contentPane.add(lblNewLabel);
		this.setLocationRelativeTo(null);

		
	}
	
	public void iniciarPantalla() {
		lbl_Carga.setText("BUSCANDO INFORMACIÓN");
		this.setVisible(true);
		lbl_img1.setVisible(true);
		lbl_img_2.setVisible(false);
	}
	
	public void finalizarPantalla() {
		lbl_Carga.setText("FINALIZADO");
		this.setVisible(false);
		this.repaint();
	}
	
	public void cargaDatos(int valor) {
		lbl_Carga.setText("CARGANDO DATOS: "+valor);
		lbl_img_2.setVisible(true);
		lbl_img1.setVisible(false);
	}
	
	public void cargaDatosSinValor() {
		lbl_Carga.setText("CARGANDO DATOS...");
		lbl_img_2.setVisible(true);
		lbl_img1.setVisible(false);
		this.repaint();
	}
	
}
