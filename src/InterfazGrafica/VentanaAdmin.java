package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;

public class VentanaAdmin extends JFrame {

	public static JPanel contentPane;
	
	private static Dimension sizePnt;
	private static CardLayout cl= new CardLayout();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdmin frame = new VentanaAdmin();
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
	public VentanaAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setTitle("Estación");
		contentPane.setLayout(cl); // pasa al contentPane el CardLayout que creamos
	
		sizePnt = contentPane.getSize() ;
	
	}
	
	public static void cambiarPantalla(JPanel panel, String nombrePnt) {
		panel.setSize(sizePnt);
		cl.show(contentPane, nombrePnt); // show: muestra esa Pnt en ese contenedor
		contentPane.revalidate(); // "limpiar el contenedor o VentanaAdmin"
		contentPane.repaint(); // "repintar el contenedor"		
	}
	
	
}