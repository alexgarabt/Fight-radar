package eda2223;

/**
 * Almacena la información referente al movimiento de un usuario
 *
 * @author César Vaca
 * @version 1.0
 */
public final class Movimiento {

    /** Identificador del usuario
     */
    public String id;
    /** Posición 2D del usuario (metros)
     */
    public double x, y;

    /**
     * Creación copiando los parámetros de un usuario
     *
     * @param u Usuario del que se toman los datos (id y posición)
     */
    public Movimiento(Usuario u) { id = u.id; x = u.x; y = u.y; }

    /**
     * Creación a partir de una línea de fichero
     *
     * @param lin Línea con los datos del movimiento
     */
    public Movimiento(String lin) {
        String[] trozos = lin.split(" ");
        id = trozos[0];
        x = Double.parseDouble(trozos[1]);
        y = Double.parseDouble(trozos[2]);
    }

    /**
     * Asigna un desplazamiento al azar (-0.5..0.5 en ambas direcciones)
     */
    public void MoverAlAzar() {
        x += Math.random() - 0.5;
        y += Math.random() - 0.5;
    }    
}
