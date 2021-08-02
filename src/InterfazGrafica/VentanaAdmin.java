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
	public static PntMantenimiento pntMantenimiento = new PntMantenimiento();
	public static PntBuscarLineaTransporte pntBuscarLineaTransporte = new PntBuscarLineaTransporte();
	public static PntAltaLineaTransporte pntAltaLineaTransporte = new PntAltaLineaTransporte();
	public static PntEditarLineaTransporte pntEditarLineaTransporte = new PntEditarLineaTransporte();
	public static PntAltaTrayecto pntAltaTrayecto = new PntAltaTrayecto();
	public static PntVentaBoleto pntVentaBoleto = new PntVentaBoleto();
	public static PntVentaBoleto2 pntVentaBoleto2 = new PntVentaBoleto2();
	public static PntInicio pntInicio = new PntInicio();
	public static PntInformacionGeneral pnt_info_gral = new PntInformacionGeneral(); 
	
	public static String n_pntBuscarEstacion = "n_pntBuscarEstacion";
	public static String n_pntAltaEstacion = "n_pntAltaEstacion";
	public static String n_pntEditarEstacion = "n_pntEditarEstacion";
	public static String n_pntMantenimiento = "n_pntMantenimiento";
	public static String n_pntBuscarLineaTransporte = "n_pntBuscarLineaTransporte";
	public static String n_pntAltaLineaTransporte = "n_pntAltaLineaTransporte";
	public static String n_pntEditarLineaTransporte = "n_pntEditarLineaTransporte";
	public static String n_pntAltaTrayecto = "n_AltaTrayecto";
	public static String n_pntVentaBoleto = "n_pntVentaBoleto";
	public static String n_pntVentaBoleto2 = "n_pntVentaBoleto2";
	public static String n_pntInicio = "n_pntInicio";
	public static String n_pntInfoGral = "n_pntInfoGral";


	
	
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
		setTitle("TP INTEGRADOR");
		contentPane.setLayout(cl); // pasa al contentPane el CardLayout que creamos
	
		sizePnt = contentPane.getSize() ;
		
		contentPane.add(n_pntBuscarEstacion, pntBuscarEstacion);
		contentPane.add(n_pntAltaEstacion, pntAltaEstacion);
		contentPane.add(n_pntEditarEstacion, pntEditarEstacion);
		contentPane.add(n_pntMantenimiento, pntMantenimiento);
		contentPane.add(n_pntBuscarLineaTransporte, pntBuscarLineaTransporte);
		contentPane.add(n_pntAltaLineaTransporte, pntAltaLineaTransporte);
		contentPane.add(n_pntEditarLineaTransporte, pntEditarLineaTransporte);
		contentPane.add(n_pntAltaTrayecto, pntAltaTrayecto);
		contentPane.add(n_pntVentaBoleto, pntVentaBoleto);
		contentPane.add(n_pntVentaBoleto2, pntVentaBoleto2);
		contentPane.add(n_pntInicio, pntInicio);
		contentPane.add(n_pntInfoGral, pnt_info_gral);

		
		cambiarPantalla(pnt_info_gral, n_pntInfoGral);
		//cambiarPantalla(pntInicio, n_pntInicio);
		//cambiarPantalla(pntAltaTrayecto, n_pntAltaTrayecto);
	
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