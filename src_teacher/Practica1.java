package eda2223;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * Implementación de referencia de la primera práctica
 * Estructuras de Datos y Algoritmos
 * Curso 2022-23, UVa
 * 
 * @author Cesar Vaca
 * @version 1.0
 */
public class Practica1 {

    /**
     * Lee un fichero de estado inicial.
     *
     * @param fich Ruta del fichero
     * @param n Número de usuarios
     * @return La lista de n objetos Usuario leidos del fichero
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Usuario> LeeFicheroIni(String fich, int n) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(fich)));
        ArrayList<Usuario> usrs = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            usrs.add(new Usuario(br.readLine()));
        }
        return usrs;
    }

    /**
     * Lee un fichero de movimientos de usuarios.
     *
     * @param fich Ruta del fichero
     * @param n Número de movimientos (igual al de usuarios)
     * @return La lista de n objetos Movimiento leidos del fichero
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Movimiento> LeeFicheroMovs(String fich, int n) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(fich)));
        ArrayList<Movimiento> movs = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            movs.add(new Movimiento(br.readLine()));
        }
        return movs;
    }

    /**
     * Busca el usuario que tiene ese identificador (siempre existe y es único).
     * <p>Eficiencia: O(n)
     *
     * @param usrs Lista de usuarios donde se busca
     * @param id Identificador del usuario
     * @return El usuario con ese identificador
     */
    public static Usuario Buscar(ArrayList<Usuario> usrs, String id) {
        for (Usuario u : usrs) {
            if (u.id.equals(id)) { return u; }
        }
        throw new Error("No existe ningún usuario con ese identificador");
    }

    /**
     * Devuelve los usuarios que están en la zona de control de un usuario.
     * <p>Eficiencia: O(n)
     *
     * @param u El usuario de referencia
     * @param usrs Lista de todos los usuarios
     * @return Lista de identificadores de usuarios en su zona de control (salvo
     * el mismo)
     */
    public static ArrayList<String> Vecinos(Usuario u, ArrayList<Usuario> usrs) {
        ArrayList<String> vecs = new ArrayList<>();
        for (Usuario otro : usrs) { // n iteraciones
            if (otro != u && u.EnZonaControl(otro)) {
                vecs.add(otro.id); // O(1)
            }
        }
        return vecs;
    }

    /**
     * Procesa el movimiento del usuario u, generando los mensajes adecuados
     * y actualizando las listas de vecinos de u y otros usuarios afectados.
     * <p>Eficiencia: O(n*d + d^2)
     * 
     * @param u Usuario cuya posición ha sido actualizada
     * @param vec_new Vecinos actuales del usuario u
     * @param usrs Lista de todos los usuarios
     * @param msgs Lista de mensajes (se actualiza añadiendo nuevos al final)
     */
    public static void Actualizar(Usuario u, ArrayList<String> vec_new, ArrayList<Usuario> usrs, ArrayList<String> msgs) {
        ArrayList<String> vec_old = u.vecinos;
        // Añadir los nuevos usuarios
        for (String id : vec_new) { // O(d) iteraciones
            if (!vec_old.contains(id)) {       // Condición: O(d)
                Usuario v = Buscar(usrs, id);  // O(n)
                msgs.add(u.Mensaje(v, true));  // O(1)
                msgs.add(v.Mensaje(u, true));  // O(1)
                v.vecinos.add(u.id);           // O(1)
                // No hacemos aquí vec_old.add(v.id): Ver comentario
                // del bucle siguiente
                // Cada iteración, peor caso: O(n+d).
            }
        }
        // Eliminar los que han desaparecido
        for (String id : vec_old) { // O(d) iteraciones
            if (!vec_new.contains(id)) {       // Condición: O(d)
                Usuario v = Buscar(usrs, id);  // O(n)
                msgs.add(u.Mensaje(v, false)); // O(1)
                msgs.add(v.Mensaje(u, false)); // O(1)
                v.vecinos.remove(u.id);        // O(d)
                // No puedes hacer aquí lo siguiente: vec_old.remove(v.id)
                // porque cambias la lista que esta recorriendo el bucle
                // Cada iteración, peor caso: O(n+d).
            }
        }
        // Actualizar lista de vecinos del usuario u
        u.vecinos = vec_new;  // O(1)
    }
    
    /**
     * Algoritmo de la práctica: Procesa los movimientos de los usuarios (uno a
     * uno) generando los mensajes de entrada y salida de usuarios en zona de
     * control.
     * <p>Eficiencia: O(d*n^2 + n*d^2)
     *
     * @param usrs Lista de todos los usuarios, con su posición anterior
     * @param movs Lista de los movimientos de todos los usuarios a su nueva
     * posición
     * @return Lista de mensajes que se generan
     */
    public static ArrayList<String> Algoritmo(ArrayList<Usuario> usrs, ArrayList<Movimiento> movs) {
        ArrayList<String> msgs = new ArrayList<>();
        for (Movimiento m : movs) { // n iteraciones
            // Para cada actualización de movimiento..
            Usuario u = Buscar(usrs, m.id); // O(n)
            // Mover el usuario
            u.Mover(m); // O(1)
            // Encontrar los otros usuarios en su zona de control
            ArrayList<String> vecs = Vecinos(u, usrs); // O(n)
            // Genera mensajes y actualiza vecinos
            Actualizar(u, vecs, usrs, msgs); // O(n*d + d^2)
            msgs.add("---");
            // Cada iteración: O(n*d + d^2)
        }
        // Total algoritmo: O(d*n^2 + n*d^2).
        // Cuando n >> d, la fórmula es cte*d*n^2 + O(n*d^2)
        return msgs;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Usuario> usrs;    // Lista de usuarios
        ArrayList<Movimiento> movs; // Lista de movimientos
        ArrayList<String> msgs;     // Lista de mensajes
        int n;                      // Número de usuarios
        double d;                   // Densidad (persona/metro^2)

        Scanner in = new Scanner(System.in);
        System.out.println("PRACTICA EDA 2022-23 (Referencia)");
        System.out.print("Fichero de estado: ");
        String fich_ini = in.nextLine();
        String[] trozos = fich_ini.split("_");
        n = Integer.parseInt(trozos[1]);
        d = Double.parseDouble(trozos[2]);
        System.out.printf("Parámetros: n = %d, d = %.1f\n", n, d);
        usrs = LeeFicheroIni(fich_ini, n);      
        System.out.print("Modo [D]epuración o [M]edición? ");
        String modo = in.nextLine().toUpperCase();
        if (modo.startsWith("D")) {
            // Modo depuración
            System.out.print("Fichero de movimientos: ");
            String fich_mov = in.nextLine();
            if (fich_mov.length() == 0) { // Autocompletar nombre fichero
                fich_mov = "movs_" + trozos[1] + "_" + trozos[2] + "_" + trozos[3];
            }
            movs = LeeFicheroMovs(fich_mov, n);
            System.out.println("Procesando...");
            long t0 = System.nanoTime();
            msgs = Algoritmo(usrs, movs);
            long t1 = System.nanoTime();
            System.out.printf("Tiempo: %.6f seg.\n", 1e-9 * (t1 - t0));
            // Se salvan los mensajes a un fichero en vez de mostrarlos por pantalla
            Path file = Paths.get("msgs_" + trozos[1] + "_" + trozos[2] + "_" + trozos[3]);
            Files.write(file, msgs, StandardCharsets.UTF_8);
        } else {
            // Modo medición
            System.out.print("Número de ciclos: ");
            int num_ciclos = Integer.parseInt(in.nextLine());
            System.out.println("Procesando...");
            movs = new ArrayList<>(n);
            for (Usuario u : usrs) { movs.add(new Movimiento(u)); }
            double tpo_total = 0;
            for (int i = 0; i < num_ciclos; i++) {
                // Asignar movimientos al azar
                for (Movimiento mov : movs) { mov.MoverAlAzar(); }
                long t0 = System.nanoTime();
                Algoritmo(usrs, movs);
                long t1 = System.nanoTime();
                tpo_total += 1e-9 * (t1 - t0);
                System.out.printf("%.5f\n", 1e-9 * (t1 - t0));
            }
            System.out.printf("Promedio: %.5f\n", tpo_total / num_ciclos);
        }
    }
}
