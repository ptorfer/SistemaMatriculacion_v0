package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Matriculas {

    //Atributos
    private int capacidad;
    private int tamano;
    private Matricula[] coleccionMatriculas;

    //Constructor
    public Matriculas(int capacidad) {

        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        this.capacidad = capacidad;
        this.tamano = 0;
        coleccionMatriculas = new Matricula[capacidad];
    }

    //Métodos

    public Matricula[] get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }

    private Matricula[] copiaProfundaMatriculas() throws OperationNotSupportedException {

        Matricula[] copiaMatriculas = new Matricula[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaMatriculas[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copiaMatriculas;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula, "ERROR: No se puede insertar una matrícula nula.");

        int indice = buscarIndice(matricula);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }

        if (tamanoSuperado(indice)) {
            coleccionMatriculas[indice] = new Matricula(matricula);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }
    }

    private int buscarIndice(Matricula matricula) throws OperationNotSupportedException {

        int indice = 0;
        boolean matriculaEncontrada = false;
        while (!tamanoSuperado(indice) && !matriculaEncontrada) {
            if (get()[indice].equals(matricula)) {
                matriculaEncontrada = true;
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

    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {

        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Matricula(get()[indice]);
        }
    }

    public void borrar (Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula,"ERROR: No se puede borrar una matrícula nula.");

        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)){
            throw new IllegalArgumentException("ERROR: No existe ninguna matrícula como la indicada.");
        }
        else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda (int indice){
        int i;
        for (i=indice; !tamanoSuperado(i);i++){
            coleccionMatriculas[i] = coleccionMatriculas [i+1];
        }
        coleccionMatriculas[i]= null;
        tamano --;
    }

    public Matricula [] get (Alumno alumno) throws OperationNotSupportedException {
        Objects.requireNonNull(alumno,"No se puede operar con un alumno nulo.");

        Matricula[] matriculaAlumno = new Matricula[capacidad];
        int indice = 0;
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null && matricula.getAlumno().equals(alumno)) {
                matriculaAlumno[indice++] = new Matricula(matricula);
            }
        }
        return matriculaAlumno;
    }

    public Matricula [] get (String cursoAcademico) throws OperationNotSupportedException {
        Objects.requireNonNull(cursoAcademico,"No se puede operar con un curso académico nulo.");

        Matricula[] matriculaCursoAcademico = new Matricula[capacidad];
        int indice = 0;
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null && matricula.getCursoAcademico().equals(cursoAcademico)) {
                matriculaCursoAcademico[indice++] = new Matricula(matricula);
            }
        }
        return matriculaCursoAcademico;
    }

    public Matricula [] get (CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        Objects.requireNonNull(cicloFormativo,"No se puede operar con un ciclo formativo nulo.");

        //El ciclo formativo está en Asignatura y para llegar a ello, puedo acceder a través de getCicloFormativo que está dentro de coleccionAsignaturas. Por ese motivo, tengo que realizar dos for, uno con coleccionMatriculas y otro dentro con coleccionAsignaturas para llegar a cicloFormativo.
        Matricula[] matriculaCicloFormativo = new Matricula[capacidad];
        int indice = 0;
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula!= null){
                for (Asignatura asignatura : matricula.getColeccionAsignaturas()) {
                    if (asignatura != null && asignatura.getCicloFormativo().equals(cicloFormativo)) {
                        matriculaCicloFormativo[indice++] = new Matricula(matricula);
                    }
                }
            }
        }
        return matriculaCicloFormativo;
    }
}
