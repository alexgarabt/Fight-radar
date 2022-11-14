package eda2223;


import java.util.*;

/**
 * Almacena la información referente a un usuario
 *
 * @author César Vaca
 * @version 1.0
 */
public class Usuario {

    /** Radio de la Zona de Control
     */
    public static final double RZC = 2.0;
    private static final double RZC2 = RZC * RZC;

    /** Identificador (único) del usuario
     */
    public String id;
    /** Posición 2D del usuario (metros)
     */
    public double x, y;
    /** Grupo del usuario (binario)
     */
    public boolean grupo;
    /** Lista de ids de otros usuarios en su zona de control
     */
    public ArrayList<String> vecinos;

    /**
     * Crea un usuario a partir de la información de una línea del fichero
     *
     * @param lin Linea con los datos del usuario
     */
    public Usuario(String lin) {
        String[] trozos = lin.split(" ");
        id = trozos[0];
        x = Double.parseDouble(trozos[1]);
        y = Double.parseDouble(trozos[2]);
        grupo = trozos[3].startsWith("S");
        vecinos = new ArrayList<>();
        for (int i = 4; i < trozos.length; i++) {
            vecinos.add(trozos[i]);
        }
    }
       
    /**
     * Comprueba si otro usuario se encuentra en la zona de control
     *
     * @param otro Referencia al otro usuario
     * @return true si otro está en la zona de control de éste usuario
     */
    public boolean EnZonaControl(Usuario otro) {
        double dx = x - otro.x;
        double dy = y - otro.y;
        return dx * dx + dy * dy < RZC2;
    }

    /**
     * Mueve al usuario a la posición indicada en el movimiento
     *
     * @param mov Referencia al movimiento
     */
    public void Mover(Movimiento mov) {
        x = mov.x;
        y = mov.y;
    }

    /**
     * Genera el mensaje adecuado cuando otro usuario entra (entra = true) o
     * sale (entra = false) de la zona de control de éste.
     *
     * @param otro Referencia al otro usuario
     * @param entra <code>true</code> si el otro usuario ha entrado en la zona,
     * <code>false</code> si ha salido de la zona
     * @return El mensaje según el formato indicado en el enunciado
     */
    public String Mensaje(Usuario otro, boolean entra) {
        if (entra) {
            return id + (grupo == otro.grupo ? "+=" : "+/") + otro.id;
        } else {
            return id + "-" + otro.id;
        }
    }
}
