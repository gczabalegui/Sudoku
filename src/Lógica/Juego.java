package Lógica;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que modela el comportamiento interno de un Sudoku.
 * 
 * @author Guadalupe Carreño
 *
 */
public class Juego {
	
	
	/** Representa al tablero del juego.*/
	private Celda [][] tablero;
	
	/** Cantidad de filas del tablero.*/
	private int cantFilas; 
	
	/** Identifica si el archivo inicial cumple o no las propiedades. */
	private boolean cumpleArchivo;

	
	/**
	 * Crea un nuevo juego.
	 * 
	 * @throws FileNotFoundException si no encuentra el archivo inicial
	 * @throws NumberFormatException si el archivo inicial no contiene números únicamente 
	 */	
	public Juego() throws NumberFormatException, FileNotFoundException {
		
		this.cantFilas = 9;
		
		tablero = new Celda[this.cantFilas][this.cantFilas];
		
		cumpleArchivo = solucionValida();
		
		if (cumpleArchivo) {	
			
			this.inicializarJuegoConArchivo();
		}
		
	}
	
	
	/**
	 * Chequea si el archivo inicial contiene una solucion valida.
	 * 
	 * @return TRUE si la solucion del archivo es válida, FALSE caso contrario
	 * @throws FileNotFoundException si no encuentra el archivo inicial
	 * @throws NumberFormatException si el archivo inicial no contiene números únicamente 
	 */	
	public boolean solucionValida() throws FileNotFoundException, NumberFormatException {
		
		boolean rta = true;		
		
		InputStream in = Juego.class.getClassLoader().getResourceAsStream("ArchivoTexto/SolucionValida.txt");	
		
		try (Scanner s = new Scanner(in)) {
			
			String lector = s.next();
			Integer numero;
			Celda c;
			
			for(int i = 0; i<cantFilas && rta; i++) {	
				
				for(int j = 0; j<cantFilas; j++) {	
					
					 if(lector != null) {	
						 
						numero = Integer.parseInt(lector); 
						
						if((1 <= numero) && (numero <= 9)){
							
							c = new Celda(i, j); 
							
							tablero[i][j] = c; 

							c.setValor(numero);
							
							rta = this.cumpleCondiciones(c);
							
							lector = s.hasNext() ? s.next() : null;	
						 }
						 else 
							 rta = false;
			        }
					else
						rta = false; // el archivo tiene menos números de los que debería tener

				}
			}
			
			s.close();
			if(lector != null) //el archivo tiene mas numeros que los que deberia tener
				rta = false;
		}
		
		if(!rta)
			vaciarTablero();
		
		return rta;
	}
	
	/**
	 * Inicializa al juego con el archivo inicial. 
	 *
	 */		
	private void inicializarJuegoConArchivo() {
		
		Random random = new Random();

			for(int i = 0; i<cantFilas ; i++) {
				
				for(int j = 0; j<cantFilas; j++) {			

					int value = random.nextInt(3);
					
					//De acuerdo a value decidir si asignar un estado inicial a la celda o no
					if (value == 0)
						tablero[i][j].setEstadoInicial(false);

				}
			}
	}
	
	/**
	 * Vacía el tablero del juego.  
	 */
	private void vaciarTablero() {
		
		for(int i = 0; i < cantFilas; i++) {
			for(int j = 0; j < cantFilas; j++) {
				
				tablero[i][j] = null;
				
			}
		}		
	}
	
	/**
	 * Verifica que la celda c cumpla todas las condiciones del sudoku. 
	 * 
	 * @param c - celda que se desea verificar
	 * @return TRUE si c cumple todas las condiciones, FALSE caso contrario
	 */
	public boolean cumpleCondiciones(Celda c) {
		
		return (cumpleFila(c) && cumpleColumna(c) && cumplePanel(c));
	
	}
	
	/**
	 * Verifica que el valor de la celda c sea el único en su fila. 
	 * 
	 * @param c - celda cuyo valor se desea verificar que cumpla la condición 
	 * @return TRUE si el valor de la celda c cumple con las condiciones, FALSE caso contrario 
	 */
	private boolean cumpleFila(Celda c) {
		
		boolean rta = true;
		
		for(int j = 0; j < cantFilas && rta; j++) {
			
			if((tablero[c.getFila()][j] != null) && !(tablero[c.getFila()][j].equals(c))) {
				
				if(tablero[c.getFila()][j].getValor() == (c.getValor())) 
					rta = false;
			}
		}
			
		return rta;
	}
	
	/**
	 * Verifica que el valor de la celda c sea el único en su columna. 
	 * 
	 * @param c - celda cuyo valor se desea verificar que cumple la condición
	 * @return TRUE si el valor de la celda c cumple con las condiciones, FALSE caso contrario 
	 */	
	private boolean cumpleColumna(Celda c) {
		
		boolean rta = true;
		
		for(int i = 0; i < cantFilas && rta; i++) {
			
			if((tablero[i][c.getColumna()] != null) && !(tablero[i][c.getColumna()].equals(c))) {
				
				if(tablero[i][c.getColumna()].getValor() == (c.getValor())) 
					rta = false;
			}
		}
		
		return rta;			
	}
	
	/**
	 * Verifica que el valor de la celda c sea el único en su panel. 
	 * 
	 * @param c - celda cuyo valor se desea verifica que cumple la condición
	 * @return TRUE si el valor de la celda c cumple las condiciones, FALSE caso contrario 
	 */
	private boolean cumplePanel(Celda c) {
		
		boolean rta = true; 
		
		int filaInicial = (c.getFila()/3)*3;
		int columnaInicial = (c.getColumna()/3)*3;	
		
		for(int i = filaInicial; i <= filaInicial+2 && rta; i++) {
			for(int j = columnaInicial; j <= columnaInicial+2; j++) {
				
				if((tablero[i][j] != null) && !(tablero[i][j].equals(c))) {
					
					if(tablero[i][j].getValor() == (c.getValor()))
						rta = false;
				}
			}
		}
		
		return rta;		
	}
	
	/**
	 * Chequea si queda alguna celda que no cumpla las condiciones. 
	 * 
	 * @return TRUE si el usuario logró terminar el sudoku correctamente, FALSE caso contrario 
	 */
	public boolean terminarJuego() {
		
		boolean gano = true;
		
		for(int i = 0; i < cantFilas && gano ; i++) {
			
			for(int j = 0; j < cantFilas; j++) {
				
				if(tablero[i][j].getValor() == null || !this.cumpleCondiciones(tablero[i][j])) {
					gano = false;
				}	
			}		
		}	

		
		return gano;
	}
	
	/**
	 * Cambia el icono de la celda c (celda que cumple las condiciones necesarias). 
	 * 
	 * @param c - celda cuyo icono se desea actualizar
	 */
	public void accionar(Celda c) {
		
		c.actualizar();
	}
	
	/**
	 * Cambia el icono de la celda c (celda que no cumple las condiciones necesarias).
	 * 
	 * @param c - celda cuyo icono se desea actualizar
	 */
	public void accionarConError(Celda c) {
		
		c.actualizarConError();
	}
	
	/**
	 * Obtiene el tablero del juego.
	 * 	
	 * @return tablero del juego 
	 */
	public Celda[][] getTablero(){
		
		return tablero;
	}
	
	/**
	 * Devuelve la celda que se encuentra en la coordenada (i, j).
	 * 
	 * @param i - coordenada de la fila en la cual se encuentra la celda
	 * @param j - coordenada de la columna en la cual se encuentra la celda 
	 * @return celda con coordenadas i y j 
	 */
	public Celda getCelda(int i, int j) {
		
		return this.tablero[i][j];
	}
	
	/**
	 * Devuelve la cantidad de filas del tablero del juego. 
	 * 
	 * @return cantidad de filas del tablero
	 */
	public int getCantFilas() {
		
		return this.cantFilas;
	}
	
	/**
	 * Indica si el archivo inicial cumple o no las condiciones. 
	 * 
	 * @return TRUE si el archivo inicial cumple con las condiciones, FALSE caso contrario 
	 */
	public boolean getCumpleArchivo() {
		
		return cumpleArchivo;
	}
	
}
