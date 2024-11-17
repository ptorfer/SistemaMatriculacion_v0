package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Alumnos {

    //Atributos
    private int capacidad;
    private int tamano;
    private Alumno[] coleccionAlumnos;

    //Constructor
    public Alumnos(int capacidad) {

        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        this.capacidad = capacidad;
        this.tamano = 0;
        coleccionAlumnos = new Alumno[capacidad];
    }

    //Métodos

    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    private Alumno[] copiaProfundaAlumnos() {

        Alumno[] copiaAlumnos = new Alumno[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copiaAlumnos;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        Objects.requireNonNull(alumno, "ERROR: No se puede insertar un alumno nulo.");

        int indice = buscarIndice(alumno);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }

        if (tamanoSuperado(indice)) {
            coleccionAlumnos[indice] = new Alumno(alumno);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
    }

    private int buscarIndice(Alumno alumno) {

        int indice = 0;
        boolean alumnoEncontrado = false;
        while (!tamanoSuperado(indice) && !alumnoEncontrado) {
            if (get()[indice].equals(alumno)) {
                alumnoEncontrado = true;
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

    public Alumno buscar(Alumno alumno) {

        int indice = buscarIndice(alumno);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Alumno(get()[indice]);
        }
    }

    public void borrar (Alumno alumno) {
        Objects.requireNonNull(alumno,"ERROR: No se puede borrar un alumno nulo.");

        int indice = buscarIndice(alumno);
        if (tamanoSuperado(indice)){
            throw new IllegalArgumentException("ERROR: No existe ningún alumno como el indicado.");
        }
        else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        int i;
        for (i=indice; !tamanoSuperado(i);i++){
            coleccionAlumnos[i] = coleccionAlumnos [i+1];
        }
        coleccionAlumnos[i]= null;
        tamano --;
    }
}
