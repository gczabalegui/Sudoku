package Lógica;

import javax.swing.ImageIcon;

/**
 * Clase que modela el comportamiento de las entidades graficas de la celda.
 * 
 * @author Guadalupe Carreño
 *
 */
public class EntidadGrafica {
	
	/** Icono de una celda.*/
	private ImageIcon grafico;
	
	/** Arreglo que almacena los posibles iconos de una celda que cumple con las condiciones.*/
	private String[] imagenes; 
	
	/** Arreglo que almacena los posibles iconos de una celda que no cumple con las condiciones.*/
	private String[] imagenesMal;
	
	/** Arreglo que contiene al icono que simboliza una celda en la que se puede jugar.*/
	private String[] pregunta;
	 
	/**
	 * Crea una entidad gráfica.
	 */
	public EntidadGrafica() {
		
		this.grafico = new ImageIcon();
		this.imagenes = new String[]{"/Img/1.jpg", "/Img/2.jpg", "/Img/3.jpg", "/Img/4.jpg", "/Img/5.jpg", "/Img/6.jpg", "/Img/7.jpg", "/Img/8.jpg", "/Img/9.jpg"};
		this.imagenesMal = new String[]{"/ImgMal/1.jpg", "/ImgMal/2.jpg", "/ImgMal/3.jpg", "/ImgMal/4.jpg", "/ImgMal/5.jpg", "/ImgMal/6.jpg", "/ImgMal/7.jpg", "/ImgMal/8.jpg", "/ImgMal/9.jpg"};   
		this.pregunta = new String [] {"/Img/pregunta.jpg"};
	}

	/**
	 * Actualiza el icono de la celda que cumple las condiciones con el icono ubicado en el indice dado. 
	 * 
	 * @param indice - posición en la que se encuentra el nuevo icono
	 */
	public void actualizar(int indice) {
		
		if (indice>0 && indice <= this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice-1]));
			this.grafico.setImage(imageIcon.getImage());
		}
		
	}
	
	/**
	 * Asigna el icono para que indica que se puede jugar. 
	 */
	public void inicial() {
		
		ImageIcon pregunta = new ImageIcon(this.getClass().getResource(this.pregunta[0]));
		this.grafico.setImage(pregunta.getImage());
	}
	
	/**
	 * Actualiza el icono de la celda que no cumple las condiciones con el incono ubicado en el índice dado.
	 * 
	 * @param indice - posición en la que se encuentra el nuevo icono
	 */
	public void actualizarConError(int indice) {
		
		if (indice>0 && indice <= this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenesMal[indice-1]));
			this.grafico.setImage(imageIcon.getImage());
		}
		
	}
	
	/**
	 * Retorna el icono de la celda.
	 * 
	 * @return icono - icono de la celda
	 */
	public ImageIcon getGrafico() {
		return this.grafico;
	}
	
	/**
	 * Setea un nuevo icono a la celda.
	 * 
	 * @param grafico - nuevo icono
	 */
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	
	/**
	 * Retorna los iconos de las celdas que cumplen las condiciones.
	 * 
	 * @return arreglo que almacena las imágenes
	 */
	public String[] getImagenes() {
		return this.imagenes;
	}
	
	/**
	 * Setea los iconos de las celdas en un arreglo.
	 * 
	 * @param imagenes - arreglo de imágenes a setear como iconos 
	 */
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
	
}
