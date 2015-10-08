package conecta4;

import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Borja
 */
public class Conecta4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Tablero tablero = new Tablero();
        int columnaAInsertar;
        int jugador = 0;
        int ganador = 0;
        Scanner scannerConsola = new Scanner(System.in).useLocale(Locale.US);

        while (ganador == 0) {
            System.out.println("Le toca al jugador " + (jugador % 2 + 1) + " \n¿En qué columna desea insertar ficha:");
            try {
                columnaAInsertar = scannerConsola.nextInt();
                tablero.addJugada(jugador % 2 + 1, columnaAInsertar - 1);
                System.out.println(tablero);
                ganador = tablero.ganador(columnaAInsertar - 1);
                jugador++;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
                if (ganador != 0) {
                    break;
                }
            }
        }
        System.out.println("Ha ganado el jugador: " + ganador);

    }
}
