package Lógica;

/**
 * Clase que modela el comportamiento de una celda. 
 * 
 * @author Guadalupe Carreño
 *
 */
public class Celda {
	
	/** Fila en la que se encuentra la celda.*/
	private int fila;
	
	/** Columna en la que se encuentra la celda.*/
	private int columna;
	
	/** Valor que almacena la celda.*/
	private Integer valor;
	
	/** Representa el icono de la celda.*/
	private EntidadGrafica entidadGrafica;
	
	/** Indica si la celda tiene o no estado inicial fijo. */
	private boolean estadoInicial;

	/**
	 * Crea una nueva celda.
	 * 
	 * @param fila - fila en la que se encuentra la celda
	 * @param columna - columna en la que se encuentra la celda
	 */
	public Celda(int fila, int columna) {
			
		this.fila = fila;
		this.columna = columna;
		this.valor = null;
		this.entidadGrafica = new EntidadGrafica();
		this.estadoInicial = true;
	}
	
	/**
	 * Crea una nueva celda. 
	 */
	public Celda() {
		
		this.estadoInicial = true;
		this.valor = null;
		this.entidadGrafica = new EntidadGrafica();
	}
	
	/**
	 * Setea el valor de la celda.
	 * 
	 * @param valor - valor que se asigna a la celda 
	 */
	public void setValor(Integer valor) {
		
		if (valor!=null && valor <= this.getCantElementos()) {
			
			this.valor = valor;
			this.entidadGrafica.actualizar(this.valor);
			
		}
		else {
			
			this.valor = 0;
		}
	}
	
	/**
	 * Setea el icono de la celda.
	 * 
	 * @param g - icono que se asigna a la celda 
	 */
	public void setGrafica(EntidadGrafica g) {
		
		this.entidadGrafica = g;
	}
	
	/**
	 * Setea la fila en la que se encuentra la celda. 
	 * 
	 * @param fila - fila de la celda
	 */
	public void setFila(int fila) {
		
		this.fila = fila;
	}
	
	/**
	 * Setea la columna en la que se encuentra la celda. 
	 * 
	 * @param columna - columna de la celda
	 */
	public void setColumna(int columna) {
		
		this.columna = columna;
	}
	
	/** 
	 * Setea si la celda tiene un estado inicial o no fijo.
	 * 
	 * @param inicial - estado inicial fijo en la celda
	 */ 
	
	public void setEstadoInicial(boolean inicial) {

        this.estadoInicial = inicial;
        
        if (!estadoInicial) {
        	
            this.valor = null;
            entidadGrafica.inicial();
        }
    }
	
	/**
	 * Retonar el valor que almacena la celda. 
	 * 
	 * @return valor de la celda
	 */
	public Integer getValor() {
		
		return this.valor;
	}
	
	/**
	 * Retorna el icono de la celda.
	 * 
	 * @return icono que almacena la celda
	 */
	public EntidadGrafica getEntidadGrafica() {
		
		return this.entidadGrafica;
	}	

	/**
	 * Devuelve la fila en la cual se encuentra la celda. 
	 * 
	 * @return fila
	 */
	public int getFila() {
		
		return fila;
	}
	
	/**
	 * Retorna la columna en la cual se encuentra la celda. 
	 * 
	 * @return columna
	 */
	public int getColumna() {
		
		return columna;
	}
	
	/**
	 * Actualiza el icono de una celda que cumple las condiciones del juego.
	 */
	public void actualizar() {
		
		entidadGrafica.actualizar(this.valor);
	}
	
	/**
	 * Actualiza el icono de una celda que no cumple las condiciones del juego.
	 */
	public void actualizarConError() {		
	
		entidadGrafica.actualizarConError(this.valor);
	}
	
	/**
	 * Devuelve la cantidad de valores posibles de la celda.
	 * 
	 * @return cantidad de valores
	 */
	public int getCantElementos() {
		
		return this.entidadGrafica.getImagenes().length;
	}
	
	/**
	 * Devuelve si al celda tiene o no estado inicial fijo. 
	 * 
	 * @return TRUE si a la celda se le asignó un estado inicial, FALSE caso contrario.
	 */
	public boolean getEstadoInicial() {
		
		return this.estadoInicial;
	}
	
	/**
	 * Actualiza el valor de la celda teniendo en cuenta la cantidad de valores posibles. 
	 * 
	 */
	public void actualizarValor() {
		
		if((this.getValor() == null)|| (this.getValor() == (this.getCantElementos()))) // si es nulo o es un 8
			this.valor = 1;
		else
			this.valor++;
		
	}
}


