package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class CiclosFormativos {

    //Atributos
    private int capacidad;
    private int tamano;
    private CicloFormativo[] coleccionCiclosFormativos;

    //Constructor
    public CiclosFormativos(int capacidad) {

        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        this.capacidad = capacidad;
        this.tamano = 0;
        coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    //Métodos

    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos();
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos() {

        CicloFormativo[] copiaCiclosFormativos = new CicloFormativo[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaCiclosFormativos[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }
        return copiaCiclosFormativos;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        Objects.requireNonNull(cicloFormativo, "ERROR: No se puede insertar un ciclo formativo nulo.");

        int indice = buscarIndice(cicloFormativo);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más ciclos formativos.");
        }

        if (tamanoSuperado(indice)) {
            coleccionCiclosFormativos[indice] = new CicloFormativo(cicloFormativo);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }
    }

    private int buscarIndice(CicloFormativo cicloFormativo) {

        int indice = 0;
        boolean cicloFormativoEncontrado = false;
        while (!tamanoSuperado(indice) && !cicloFormativoEncontrado) {
            if (get()[indice].equals(cicloFormativo)) {
                cicloFormativoEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= getCapacidad();
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {

        int indice = buscarIndice(cicloFormativo);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new CicloFormativo(get()[indice]);
        }
    }

    public void borrar (CicloFormativo cicloFormativo) {
        Objects.requireNonNull(cicloFormativo,"ERROR: No se puede borrar un ciclo formativo nulo.");

        int indice = buscarIndice(cicloFormativo);
        if (tamanoSuperado(indice)){
            throw new IllegalArgumentException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }
        else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        int i;
        for (i=indice; !tamanoSuperado(i);i++){
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos [i+1];
        }
        coleccionCiclosFormativos[i]= null;
        tamano --;
    }
}
