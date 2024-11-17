package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Asignaturas {

    //Atributos
    private int capacidad;
    private int tamano;
    private Asignatura[] coleccionAsignaturas;

    //Constructor
    public Asignaturas(int capacidad) {

        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        this.capacidad = capacidad;
        this.tamano = 0;
        coleccionAsignaturas = new Asignatura[capacidad];
    }

    //Métodos

    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    private Asignatura[] copiaProfundaAsignaturas() {

        Asignatura[] copiaAsignaturas = new Asignatura[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaAsignaturas[i] = new Asignatura(coleccionAsignaturas[i]);
        }
        return copiaAsignaturas;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        Objects.requireNonNull(asignatura, "ERROR: No se puede insertar una asignatura nula.");

        int indice = buscarIndice(asignatura);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
        }

        if (tamanoSuperado(indice)) {
            coleccionAsignaturas[indice] = new Asignatura(asignatura);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
    }

    private int buscarIndice(Asignatura asignatura) {

        int indice = 0;
        boolean asignaturaEncontrada = false;
        while (!tamanoSuperado(indice) && !asignaturaEncontrada) {
            if (get()[indice].equals(asignatura)) {
                asignaturaEncontrada = true;
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

    public Asignatura buscar(Asignatura asignatura) {

        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Asignatura(get()[indice]);
        }
    }

    public void borrar (Asignatura asignatura) {
        Objects.requireNonNull(asignatura,"ERROR: No se puede borrar una asignatura nula.");

        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)){
            throw new IllegalArgumentException("ERROR: No existe ninguna asignatura como la indicada.");
        }
        else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        int i;
        for (i=indice; !tamanoSuperado(i);i++){
            coleccionAsignaturas[i] = coleccionAsignaturas [i+1];
        }
        coleccionAsignaturas[i]= null;
        tamano --;
    }
}
