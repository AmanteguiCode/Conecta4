/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conecta4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borja
 */
public class Tablero {

    List tablero;

    public Tablero() {
        this.tablero = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            tablero.add(new Pila());
        }
    }

    public List getTablero() {
        return tablero;
    }

    @Override
    public String toString() {
        String stringTablero = "* * * * * * *\n";
        for (int i = 6; i >= 0; i--) {
            for (Object Pilai : tablero) {
                Pila pila = (Pila) Pilai;
                stringTablero = stringTablero + pila.getElementoEnPosicion(i) + " ";
            }
            stringTablero = stringTablero + "\n";
        }
        stringTablero = stringTablero + "* * * * * * *\n";
        return stringTablero;
    }

    public void addJugada(int jugador, int columna) {
        Pila pila = (Pila) tablero.get(columna);
        try {
            pila.push(jugador);
        } catch (ColumnaLlenaExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int ganador(int columnaInsertada) {
        Pila pila;
        int contador = 0;
        int valorPosicion = 0;

        //comprobar columnas
        valorPosicion = comprobarColumna(columnaInsertada);
        if (valorPosicion != 0) {
            return valorPosicion;
        }

        //comprobar filas
        valorPosicion = comprobarFila(columnaInsertada);
        if (valorPosicion != 0) {
            return valorPosicion;
        }

        //comprobar diagonales
        for (int x = 0; x < tablero.size(); x++) {
            for (int y = 0; y < 6; y++) {
                valorPosicion = comprobar4EnLineaDiagonal(x, y);
                if (valorPosicion != 0) {
                    return valorPosicion;
                }
            }

        }
        return 0;
    }

    private int comprobar4EnLineaDiagonal(int x, int y) {
        int contador = 0;

        Pila pila = (Pila) tablero.get(x);
        Pila pilaAux;

        //DIAGONAL DERECHA
        for (int i = 1; i < 4; i++) {
            if ((x - i) < 0) {
                break;
            }
            if ((y - i) < 0) {
                break;
            }
            pilaAux = (Pila) tablero.get(x - i);
            if (pilaAux.getElementoEnPosicion(y - i) == pila.getElementoEnPosicion(y)) {
                contador++;
            }
            if (contador == 3) {
                return pila.getElementoEnPosicion(y);
            }
        }
        //DIAGONAL IZQUIERDA
        contador = 0;
        for (int i = 1; i < 4; i++) {
            if ((x + i) >= tablero.size()) {
                break;
            }
            if ((y - i) < 0) {
                break;
            }
            pilaAux = (Pila) tablero.get(x + i);
            if (pilaAux.getElementoEnPosicion(y - i) == pila.getElementoEnPosicion(y)) {
                contador++;
            }
            if (contador == 3) {
                return pila.getElementoEnPosicion(y);
            }
        }
        return 0;
    }

    private int comprobarFila(int columnaInsertada) {
        int contador = 0;
        int valorPosicion = 0;
        Pila pila = (Pila) tablero.get(columnaInsertada);
        int fila = pila.talla() - 1;

        for (int j = 0; j < tablero.size(); j++) {
            pila = (Pila) tablero.get(j);
            if (pila.getElementoEnPosicion(fila) == valorPosicion) {
                contador++;
            }
            if (pila.getElementoEnPosicion(fila) != valorPosicion) {
                contador = 1;
                valorPosicion = pila.getElementoEnPosicion(fila);
            }
            if ((contador == 4) && (valorPosicion != 0)) {
                return valorPosicion;
            }
        }

        return 0;
    }

    private int comprobarColumna(int columnaInsertada) {
        int contador = 0;
        int valorPosicion = 0;
        Pila pila = (Pila) tablero.get(columnaInsertada);

        for (int j = 0; j < 6; j++) {
            if (pila.getElementoEnPosicion(j) == valorPosicion) {
                contador++;
            }
            if (pila.getElementoEnPosicion(j) != valorPosicion) {
                contador = 1;
                valorPosicion = pila.getElementoEnPosicion(j);
            }
            if ((contador == 4) && (valorPosicion != 0)) {
                return valorPosicion;
            }

        }
        return 0;
    }
}
