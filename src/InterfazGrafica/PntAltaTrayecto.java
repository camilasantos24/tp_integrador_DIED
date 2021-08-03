package InterfazGrafica;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import DTO.EstacionesDTO;
import DTO.TramoDTO;
import Entidades.Tramo;
import Entidades.Trayecto;
import Gestores.GestorEstacion;
import Gestores.GestorTrayecto;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Cursor;
import javax.swing.SpinnerNumberModel;

public class PntAltaTrayecto extends JPanel {
	private JTextField tf_codigoDestino;
	private JTextField tf_distancia;
	private JTextField tf_costo;
	private JTextField tf_codigoOrigen;
	private  DefaultTableModel dm = new DefaultTableModel();
	private  JTable table = new JTable();
	
	private JSpinner sp_cantPasajeros = new JSpinner();
	private JSpinner sp_duracion = new JSpinner();
	private	JButton btn_buscarOrigen = new JButton("");
	
	public int id_lina_de_transporte_actual;
	

	
	
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
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
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
		tf_codigoDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_codigoDestino.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

			      // Verificar si la tecla pulsada no es un digito
			      if(((caracter < '0') ||
			         (caracter > '9')) &&
			         (caracter != '\b' /*corresponde a BACK_SPACE*/ ))
			      {
			         e.consume();  // ignorar el evento de teclado
				getToolkit().beep();
				
			      }

			}
		});
		tf_codigoDestino.setBounds(20, 116, 201, 31);
		add(tf_codigoDestino);
		tf_codigoDestino.setColumns(10);
		
		tf_distancia = new JTextField();
		tf_distancia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_distancia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

			      // Verificar si la tecla pulsada no es un digito
			      if(((caracter < '0') ||
			         (caracter > '9')) &&
			         (caracter != '\b' /*corresponde a BACK_SPACE*/ && caracter !='.'))
			      {
			         e.consume();  // ignorar el evento de teclado
				getToolkit().beep();
				
			      }

			}
		});
		tf_distancia.setColumns(10);
		tf_distancia.setBounds(20, 172, 241, 31);
		add(tf_distancia);
		
		tf_costo = new JTextField();
		tf_costo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_costo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

			      // Verificar si la tecla pulsada no es un digito
			      if(((caracter < '0') ||
			         (caracter > '9')) &&
			         (caracter != '\b' /*corresponde a BACK_SPACE*/ && caracter !='.'))
			      {
			         e.consume();  // ignorar el evento de teclado
				getToolkit().beep();
				
			      }

			}
		});
		tf_costo.setColumns(10);
		tf_costo.setBounds(20, 327, 241, 31);
		add(tf_costo);
		
		tf_codigoOrigen = new JTextField();
		tf_codigoOrigen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_codigoOrigen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

			      // Verificar si la tecla pulsada no es un digito
			      if(((caracter < '0') ||
			         (caracter > '9')) &&
			         (caracter != '\b' /*corresponde a BACK_SPACE*/))
			      {
			         e.consume();  // ignorar el evento de teclado
				getToolkit().beep();
				
			      }

			}
		});
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
		sp_duracion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

			      // Verificar si la tecla pulsada no es un digito
			      if(((caracter < '0') ||
			         (caracter > '9')) &&
			         (caracter != '\b' /*corresponde a BACK_SPACE*/))
			      {
			         e.consume();  // ignorar el evento de teclado
				getToolkit().beep();
				
			      }

			}
		});
		
		sp_duracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sp_duracion.setBounds(20, 224, 241, 31);
		add(sp_duracion);
		
		JLabel lblNewLabel_4_1 = new JLabel("Cantidad m\u00E1xima de pasajeros");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(20, 255, 224, 21);
		add(lblNewLabel_4_1);
		
		sp_cantPasajeros.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

			      // Verificar si la tecla pulsada no es un digito
			      if(((caracter < '0') ||
			         (caracter > '9')) &&
			         (caracter != '\b' /*corresponde a BACK_SPACE*/ ))
			      {
			         e.consume();  // ignorar el evento de teclado
				getToolkit().beep();
				
			      }

			}
		});
		sp_cantPasajeros.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		sp_cantPasajeros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sp_cantPasajeros.setBounds(20, 276, 241, 31);
		add(sp_cantPasajeros);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Costo ($)");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1_1.setBounds(20, 305, 224, 21);
		add(lblNewLabel_4_1_1);
		
		ImageIcon icono_lupa= new ImageIcon(getClass().getResource("/recursos/lupa2.jpg"));
		btn_buscarOrigen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btn_buscarOrigen.setBorder(null);
		btn_buscarOrigen.setBackground(new Color(255, 255, 255));
		btn_buscarOrigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameSeleccionarEstacion.getInstance().modo = 1;
				FrameSeleccionarEstacion.getInstance().setVisible(true);
			}
		});
		btn_buscarOrigen.setIcon(icono_lupa);
		btn_buscarOrigen.setBounds(222, 60, 39, 31);
		add(btn_buscarOrigen);
		
		JButton btn_buscarDestino = new JButton("");
		btn_buscarDestino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_buscarDestino.setBorder(null);
		btn_buscarDestino.setBackground(new Color(255, 255, 255));
		btn_buscarDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameSeleccionarEstacion.getInstance().modo = 2;
				FrameSeleccionarEstacion.getInstance().setVisible(true);

			}
		});
		btn_buscarDestino.setIcon(icono_lupa);
		btn_buscarDestino.setBounds(222, 116, 39, 31);
		add(btn_buscarDestino);
		
		JButton btn_agregarTramo = new JButton("+");
		btn_agregarTramo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tf_codigoDestino.getText().length()>0 && tf_codigoOrigen.getText().length()>0 && tf_costo.getText().length()>0 && tf_distancia.getText().length()>0) {
					try {
						agregar_tramo();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else {
					VentanaAdmin.mensajeError("Datos Incompletos", "Error");
				}
			}
		});
		btn_agregarTramo.setBackground(new Color(50, 205, 50));
		btn_agregarTramo.setForeground(new Color(255, 255, 255));
		btn_agregarTramo.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_agregarTramo.setBounds(291, 327, 52, 31);
		add(btn_agregarTramo);
		
		JButton btn_quitarTramo = new JButton("-");
		btn_quitarTramo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitar_ultimo_tramo();
			}
		});
		btn_quitarTramo.setForeground(Color.WHITE);
		btn_quitarTramo.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_quitarTramo.setBackground(new Color(204, 51, 0));
		btn_quitarTramo.setBounds(355, 327, 52, 31);
		add(btn_quitarTramo);
		
		JButton btn_guardar = new JButton("Guardar");
		btn_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getRowCount() >0) {
					try {
						registrar_trayecto();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					VentanaAdmin.mensajeError("Ingrese los Tramos", "Error");
				}
			}
		});
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
		
		JLabel lblNewLabel_6 = new JLabel("Ingrese los tramos:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(20, 11, 241, 21);
		add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("+ Tramo Existente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getRowCount()>0 && tf_codigoOrigen.getText().length()>0) {
					try {
						FrameSeleccionarTramo.getInstance().cargar_tramos(GestorTrayecto.get_tramos_by_origen(Integer.parseInt(tf_codigoOrigen.getText())));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else {
						try {
						FrameSeleccionarTramo.getInstance().cargar_tramos(GestorTrayecto.get_all_tramos());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				}
				
				FrameSeleccionarTramo.getInstance().setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(50, 205, 50));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(575, 327, 148, 31);
		add(btnNewButton);
		
		restaurar_pantalla_completa();

	}
	
	public void restaurar_pantalla_completa() {
		btn_buscarOrigen.setEnabled(true);
		tf_codigoOrigen.setEditable(true);
		tf_codigoDestino.setText("");
		tf_costo.setText("");
		tf_distancia.setText("");
		tf_codigoOrigen.setText("");
		
		if(table.getRowCount()>0) {
			table.removeRowSelectionInterval(0, table.getRowCount()-1);
		}
	}
	
	public void restaurar_ingreso_tramo() {
		tf_codigoDestino.setText("");
		tf_costo.setText("");
		tf_distancia.setText("");
		
	}
	
	public void quitar_ultimo_tramo() {
		if(table.getRowCount()>0) {
			int codigoOrigen = Integer.parseInt(table.getValueAt(table.getRowCount()-1, 0).toString());
			dm.removeRow(table.getRowCount()-1);
			tf_codigoOrigen.setText(codigoOrigen + "");
			restaurar_ingreso_tramo();
			
			if(table.getRowCount() ==0) {
				restaurar_pantalla_completa();
			}
		
		}
	}
	
	public void agregar_tramo () throws Exception {
		
		
		int cod_origen;
		int cod_destino;
		String origen;
		String destino;
		float distancia;
		int duracion;
		int cant_pas;
		float costo;
		
		
		cod_origen = Integer.parseInt(tf_codigoOrigen.getText());
		cod_destino =Integer.parseInt(tf_codigoDestino.getText());
		origen = GestorEstacion.obtenerEstacionPorID(cod_origen).getNombre();
		destino =GestorEstacion.obtenerEstacionPorID(cod_destino).getNombre();
		distancia = Float.parseFloat(tf_distancia.getText());
		duracion = (int) sp_duracion.getValue();
		cant_pas = (int) sp_cantPasajeros.getValue();
		costo= Float.parseFloat(tf_costo.getText());
		
		TramoDTO t = new TramoDTO();
		t.setCod_destino(cod_destino);
		t.setCod_origen(cod_origen);
		
		if (GestorTrayecto.validar_tramo(t)) {
			Object[] rowData = {cod_origen, cod_destino, origen, destino, distancia, duracion, cant_pas, costo};
			dm.addRow(rowData);
			tf_codigoOrigen.setText(cod_destino + "");
			tf_codigoOrigen.setEditable(false);
			btn_buscarOrigen.setEnabled(false);
			restaurar_ingreso_tramo();
		}else {
			VentanaAdmin.mensajeError("Origen y/o Destino incorrectos", "Error");
		}
		
	
	}
	
	public void registrar_trayecto() throws SQLException {
		List<TramoDTO>tramos= new ArrayList();
		

		/*
		 * dm.addColumn("id_Origen");
		dm.addColumn("id_Destino");
		dm.addColumn("Origen");
		dm.addColumn("Destino");
		dm.addColumn("Distancia");
		dm.addColumn("Duracion");
		dm.addColumn("CantidadPasajeros");
		dm.addColumn("Costo");*/
		for(int i=0; i<table.getRowCount(); i++) {
			TramoDTO t = new TramoDTO();
			
			t.setCant_pas(Integer.parseInt(table.getValueAt(i, 6).toString()));
			t.setCod_destino(Integer.parseInt(table.getValueAt(i, 1).toString()));
			t.setCod_origen(Integer.parseInt(table.getValueAt(i, 0).toString()));
			t.setCosto(Float.parseFloat(table.getValueAt(i, 7).toString()));
			t.setDuracion(Integer.parseInt(table.getValueAt(i, 5).toString()));
			t.setDistancia(Float.parseFloat(table.getValueAt(i, 4).toString()));
			
			tramos.add(t);
		}
		
		if(GestorTrayecto.alta_trayecto(tramos, id_lina_de_transporte_actual)) {
			VentanaAdmin.mensajeExito("¡Trayecto Registrado!", "ÉXITO");
			restaurar_pantalla_completa();
			VentanaAdmin.pntBuscarLineaTransporte.limpiarPantalla();
			VentanaAdmin.pntBuscarLineaTransporte.restaurarTabla();
			VentanaAdmin.cambiarPantalla(VentanaAdmin.pntBuscarLineaTransporte, VentanaAdmin.n_pntBuscarLineaTransporte);
		
		}
		
	}
	
	public void insertar_origen_seleccionado(int id) {
		tf_codigoOrigen.setText(id + "");
	}
	
	public void insertar_destino_seleccionado(int id) {
		tf_codigoDestino.setText(id + "");
	}
	
}
