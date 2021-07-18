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
	
	public static PntBuscarEstacion pntBuscarEstacion= new PntBuscarEstacion();
	public static PntAltaEstacion pntAltaEstacion = new PntAltaEstacion();
	public static PntEditarEstacion pntEditarEstacion = new PntEditarEstacion();
	
	public static String n_pntBuscarEstacion = "n_pntBuscarEstacion";
	public static String n_pntAltaEstacion = "n_pntAltaEstacion";
	public static String n_pntEditarEstacion = "n_pntEditarEstacion";
	
	
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
		
		contentPane.add(n_pntBuscarEstacion, pntBuscarEstacion);
		contentPane.add(n_pntAltaEstacion, pntAltaEstacion);
		contentPane.add(n_pntEditarEstacion, pntEditarEstacion);
		
		cambiarPantalla(pntBuscarEstacion, n_pntBuscarEstacion);
	
	}
	
	public static void cambiarPantalla(JPanel panel, String nombrePnt) {
		panel.setSize(sizePnt);
		cl.show(contentPane, nombrePnt); // show: muestra esa Pnt en ese contenedor
		contentPane.revalidate(); // "limpiar el contenedor o VentanaAdmin"
		contentPane.repaint(); // "repintar el contenedor"		
	}
	
	//Ventana emergente de error
		public static void mensajeError(String error, String titulo) {
			// TODO Auto-generated method stub
			if (JOptionPane.showConfirmDialog(null, error, titulo, 
				JOptionPane.PLAIN_MESSAGE, 
				JOptionPane.ERROR_MESSAGE)==0);
		}
		
	//Ventana emergente de exito
		public static void mensajeExito(String texto, String titulo) {
			if (JOptionPane.showConfirmDialog(null, texto, titulo, JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE)==0);
		}
		
	//Ventana emergente de consulta
		public static int mensajeConsulta(String[] args, String titulo, String mensaje) {
	        int seleccion = JOptionPane.showConfirmDialog(null,
	                mensaje, titulo,JOptionPane.YES_NO_OPTION);
		// 0=yes, 1=no
		System.out.println(seleccion);
		return seleccion;
	    }
	
}