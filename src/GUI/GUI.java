package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Lógica.Celda;
import Lógica.Juego;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.Font;

/**
 * Crea una interfaz gráfica.
 * 
 * @author Guadalupe Carreño
 *
 */

public class GUI extends JFrame {

	/** Panel que contiene a todos los paneles.*/	
	private JPanel contentPane;
	
	/** Panel que contiene a las celdas del juego.*/
	private JPanel panelJuego;
	
	/** Panel que contiene los botones y el reloj.*/
	private JPanel panelBotones;
	
	/** Juego.*/
	private Juego juego;
	
	/** Matriz que almacena los graficos del juego.*/
	private JLabel [][] matrizLabels;	
	
	/** Boton para iniciar el juego.*/
	private JButton btnIniciar;
	
	/** Boton de pausado.*/
	private JButton btnPausar;
	
	/** Boton para finalizar.*/
	private JButton btnFinalizar; 
	
	/** Arreglo con la imagen de fondo.*/
	private String[] fondo = new String[] {"/GUI/Fondo.png"}; 
	
	/** Arreglo con las imagenes del reloj.*/
	private String[] reloj = new String[] {"/Reloj/0.png", "/Reloj/1.png", "/Reloj/2.png", "/Reloj/3.png", "/Reloj/4.png", "/Reloj/5.png", "/Reloj/6.png", "/Reloj/7.png", "/Reloj/8.png", "/Reloj/9.png"};
	
	/** Label que contiene a la imagen de fondo.*/
	private JLabel labelFondo;
	
	/** Timer para manejar el reloj.*/
	private Timer timer ;
	
	/**Labels de los segundos, horas y minutos del reloj.*/
	
	private JLabel segundos1;
	private JLabel segundos2; 
	
	private JLabel minutos1;
	private JLabel minutos2; 
	
	private JLabel horas1;
	private JLabel horas2;
	
	/** Color blanco.*/
	private static Color blanco = new Color(255, 255, 255);
	
	/** Enteros que marcan las horas, minutos y segundos.*/	
	private int m, h, s;
	
	/** Indica si es posible jugar o no. */
	private boolean jugar;
	

	/**
	 * Inicia la aplicación
	 * 
	 * @param args - argumento del String
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea un nuevo frame.
	 */
	public GUI() {
		
		setTitle("Super Mario Bros: Sudoku");
	
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 1327, 850);		
		
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		jugar = false;
		
		inicializarComponentes();		
		
	}
	
	/**
	 * Inicializa las componentes de la GUI.
	 */
	private void inicializarComponentes() {	
		
			
		/** Panel del juego.*/
		panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, 811, 811);
		panelJuego.setLayout(new GridLayout(9, 1, 1, 1));
		panelJuego.setBackground(blanco);
		contentPane.add(panelJuego);
		this.inicializarJuego();
		
		/** Panel botones y reloj.*/		
		panelBotones = new JPanel();
		panelBotones.setBounds(810, 0, 500, 811);
		panelBotones.setLayout(null);
		contentPane.add(panelBotones);
		
		/** Label que contiene a la imagen de fondo.*/
		labelFondo = new JLabel(); 
		labelFondo.setBounds(0, 0, 500, 811);
		ImageIcon imagenFondo = new ImageIcon(this.getClass().getResource(this.fondo[0]));
		labelFondo.setIcon(imagenFondo);
		reDimensionar(labelFondo, imagenFondo);
		panelBotones.add(labelFondo);
		
		/** Inicializa los labels del reloj.*/
		
		ImageIcon imagenInicial = new ImageIcon(this.getClass().getResource(this.reloj[0]));
		
		horas1 = new JLabel(); 
		horas1.setIcon(imagenInicial);
		horas1.setBounds(110, 400, 40, 70);
		labelFondo.add(horas1);
		
		horas2 = new JLabel(); 
		horas2.setIcon(imagenInicial);
		horas2.setBounds(153, 400, 40, 70);
		labelFondo.add(horas2);
		
		minutos1 = new JLabel(); 
		minutos1.setIcon(imagenInicial);
		minutos1.setBounds(210, 400, 40, 70);
		labelFondo.add(minutos1);
		
		minutos2 = new JLabel(); 
		minutos2.setIcon(imagenInicial);
		minutos2.setBounds(253, 400, 40, 70);
		labelFondo.add(minutos2);
		
		segundos1 = new JLabel(); 
		segundos1.setIcon(imagenInicial);
		segundos1.setBounds(310, 400, 40, 70);
		labelFondo.add(segundos1);
		
		segundos2 = new JLabel(); 
		segundos2.setIcon(imagenInicial);
		segundos2.setBounds(353, 400, 40, 70);
		labelFondo.add(segundos2);		
	
		
		/** Boton iniciar.*/	
		btnIniciar = new JButton("INICIAR");
		btnIniciar.setFont(new Font("Impact", Font.PLAIN, 20));
		btnIniciar.setBounds(150, 575, 175, 23);
		labelFondo.add(btnIniciar);
		
		/** Boton pausar.*/
		btnPausar = new JButton("PAUSAR");
		btnPausar.setEnabled(false);
		btnPausar.setFont(new Font("Impact", Font.PLAIN, 20));
		btnPausar.setBounds(150, 625, 175, 23);
		labelFondo.add(btnPausar);
		
		/** Boton finalizar.*/
		btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.setEnabled(false);
		btnFinalizar.setFont(new Font("Impact", Font.PLAIN, 20));
		btnFinalizar.setBounds(150, 675, 175, 23);
		labelFondo.add(btnFinalizar);
		
		/** Labels que indican la separación entre horas, minutos y segundos.*/
		JLabel lblNewLabel1 = new JLabel(":");		
		lblNewLabel1.setFont(new Font("Impact", Font.PLAIN, 65));
		lblNewLabel1.setBounds(195, 300, 150, 250);
		labelFondo.add(lblNewLabel1);
		
		JLabel lblNewLabel2 = new JLabel(":");		
		lblNewLabel2.setFont(new Font("Impact", Font.PLAIN, 65));
		lblNewLabel2.setBounds(295, 300, 150, 250);
		labelFondo.add(lblNewLabel2);		
		
		this.inicializarReloj();
		
	}			
	
	
	/**
	 * Inicializa el juego con los labels de los graficos.
	 * 
	 */	
	private void inicializarJuego() {
		
		try {
			
			juego = new Juego();
			
		} catch (NumberFormatException | FileNotFoundException e1) {
			
			JOptionPane.showMessageDialog(null, "Error en el archivo: no se pudo inicializar el juego.");
	        System.exit(0);
		}
		
		if(!juego.getCumpleArchivo()){
			
			JOptionPane.showMessageDialog(null, "Error en el archivo: no se pudo inicializar el juego.");
	        System.exit(0);
		}
		
		
		matrizLabels = new JLabel[juego.getCantFilas()][juego.getCantFilas()];	
		
		for (int i = 0; i <juego.getCantFilas(); i++) {				
		
			for(int j =0; j<juego.getCantFilas(); j++) {
				
				Celda c = juego.getCelda(i,j);						
				
				if(c != null) {
					
					ImageIcon grafico = c.getEntidadGrafica().getGrafico();				
					JLabel label = new JLabel(); 				
					matrizLabels[i][j] = label;				
					panelJuego.add(label);
					label.setEnabled(false);
					
					// Marcado de los bordes para que se visualicen los 9 paneles. 
					
					if((i == 2 || i ==  5) && (j == 2 || j == 5))
						label.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 5, blanco));					

					else {	
						if(i == 2 || i == 5)
							label.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, blanco));
						
						if(j == 2 || j == 5)
							label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, blanco));
					}
					// Fin del marcado. 
					
					label.addComponentListener((ComponentListener) new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							reDimensionar(label, grafico);
							label.setIcon(grafico);
						}
					});
					
					if(c.getValor() == null || !c.getEstadoInicial()) {				
					
						label.addMouseListener(new MouseAdapter() {
							@Override
							
							public void mouseClicked(MouseEvent e) {
								
								if(jugar) {
									c.actualizarValor();
									
									juego.accionar(c);	
									
									actualizarJuego();	
									
									reDimensionar(label, c.getEntidadGrafica().getGrafico());
								}	
							}
						});
					}
				}
			}	
		}		
	}		
		
	
	/**
	 * Actualiza el panel del juego teniendo en cuenta las celdas que cumplen y no cumplen las condiciones.
	 * 
	 */
	private void actualizarJuego() {
		
		Celda recorro;
		
		for(int i = 0; i < juego.getCantFilas(); i++) {
			
			for(int j = 0; j < juego.getCantFilas(); j++) {

				recorro = juego.getCelda(i, j); 
				
				if(recorro.getValor() != null) {
					if(juego.cumpleCondiciones(recorro) == false) {
					
						juego.accionarConError(recorro);
					}	
					else
						juego.accionar(recorro);
					
					reDimensionar(matrizLabels[i][j], recorro.getEntidadGrafica().getGrafico());
				}
			}
		}
		
	}
	
	
	/**
	 * Redimensiona los graficos de los labels.
	 * 
	 * @param label --> label que se desea redimensionar
	 * @param grafico --> icono a redimensionar
	 */
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		
		Image image = grafico.getImage();
		
		if (image != null) {
 
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}	
	
	
	/** Inicializa el reloj y los botones. */
	
	private void inicializarReloj() {
		
		ActionListener acciones = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
	            ++s;
	            if(s==60) 
	            {
	                s = 0;
	                ++m;
	            }
	            if(m==60)
	            {
	                m = 0;
	                ++h;
	            }
	            actualizarLabel();
	        }
	        
	    };
		timer = new Timer(1000, acciones);
	
		btnIniciar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				timer.start();
				btnIniciar.setEnabled(false);
				btnIniciar.setText("REANUDAR");
				btnPausar.setEnabled(true);
				btnFinalizar.setEnabled(true);
				for(int i = 0; i < juego.getCantFilas(); i++) {
					for(int j = 0; j < juego.getCantFilas(); j++) {
						
						if(matrizLabels[i][j] != null)
							matrizLabels[i][j].setEnabled(true);
					}
				}
				
				jugar = true;
				
			}
		});
		
		btnPausar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(btnPausar.isEnabled()) {				
					
					jugar = false;
					timer.stop();
					btnPausar.setEnabled(false);
					btnIniciar.setEnabled(true);			
					btnFinalizar.setEnabled(true);
				}	
			}
		});
		
		
		btnFinalizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				
				if(timer.isRunning())					
					timer.stop();
				
				btnIniciar.setText("INICIAR");
				btnIniciar.setEnabled(false);
				btnPausar.setEnabled(false);
				btnFinalizar.setEnabled(false);	
				h = 0;
				m = 0; 
				s = 0;
				actualizarLabel();				
					
				if(!juego.terminarJuego()) {					
					
					ImageIcon marioTriste = new ImageIcon(this.getClass().getResource("/GUI/MarioTriste.jpg"));
					JOptionPane.showMessageDialog(null, "¡UPS!  HA PERDIDO.", "PERDIÓ", JOptionPane.INFORMATION_MESSAGE, marioTriste);
					System.exit(0);
				}
				else {
					
					ImageIcon marioFeliz = new ImageIcon(this.getClass().getResource("/GUI/MarioFeliz.png"));
					JOptionPane.showMessageDialog(null, "¡GANÓ!  SU JUEGO FUE EXITOSO", "GANÓ", JOptionPane.INFORMATION_MESSAGE, marioFeliz);
					System.exit(0);
				}
			}
			
		});	
	}
	
	
	/** Actualiza los labels de horas, minutos y segundos.*/
	
	private void actualizarLabel() {
		
		ImageIcon numero; 
			
		numero = new ImageIcon(this.getClass().getResource(this.reloj[s/10]));
		segundos1.setIcon(numero);
		numero = new ImageIcon(this.getClass().getResource(this.reloj[s%10]));
		segundos2.setIcon(numero);

			
		numero = new ImageIcon(this.getClass().getResource(this.reloj[m/10]));
		minutos1.setIcon(numero);
		numero = new ImageIcon(this.getClass().getResource(this.reloj[m%10]));
		minutos2.setIcon(numero);
		
		if(h>0) {
			
			numero = new ImageIcon(this.getClass().getResource(this.reloj[h/10]));
			horas1.setIcon(numero);
			numero = new ImageIcon(this.getClass().getResource(this.reloj[h%10]));
			horas2.setIcon(numero);
		}
		
	}	
}
