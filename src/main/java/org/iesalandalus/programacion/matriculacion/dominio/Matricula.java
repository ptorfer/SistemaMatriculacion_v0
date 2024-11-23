package org.iesalandalus.programacion.matriculacion.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


public class Matricula {

    //Atributos
    public static final int MAXIMO_MESES_ANTERIOR_ANULACION=6;
    public static final int MAXIMO_DIAS_ANTERIOR_MATRICULA=15;
    public static final int MAXIMO_NUMERO_HORAS_MATRICULA=1000;
    public static final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA=10;
    private static final String ER_CURSO_ACADEMICO="\\d{2}-\\d{2}";
    public static final String FORMATO_FECHA= "dd/MM/YYYY";
    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;
    private Alumno alumno;
    private Asignatura[] coleccionAsignaturas;

    //Constructores

    public Matricula (int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura[] coleccionAsignaturas) throws OperationNotSupportedException {

        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaAnulacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(coleccionAsignaturas);
    }

    public Matricula (Matricula matricula) throws OperationNotSupportedException {

        Objects.requireNonNull(matricula,"ERROR: No es posible copiar una matrícula nula.");

        setIdMatricula(matricula.getIdMatricula());
        setCursoAcademico(matricula.getCursoAcademico());
        setFechaMatriculacion(matricula.getFechaMatriculacion());
        setAlumno(matricula.getAlumno());
        setColeccionAsignaturas(matricula.getColeccionAsignaturas());
    }

    //Métodos

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        if (idMatricula <= 0) {
            this.idMatricula = idMatricula;
        } else {
            throw new IllegalArgumentException("ERROR: El identificador de una matrícula no puede ser menor o igual a 0.");
        }
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        Objects.requireNonNull(cursoAcademico,"ERROR: El curso académico de una matrícula no puede ser nulo.");

        if (cursoAcademico.isEmpty()){
            throw new IllegalArgumentException("ERROR: El curso académico de una matrícula no puede estar vacío.");
        }
        if (cursoAcademico.matches(ER_CURSO_ACADEMICO)){
            this.cursoAcademico = cursoAcademico;
        }
        else{
            throw new IllegalArgumentException("ERROR: El formato del curso académico no es correcto.");
        }
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {

        Objects.requireNonNull(fechaMatriculacion,"ERROR: La fecha de matriculación de una mátricula no puede ser nula.");

        if (fechaMatriculacion.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser posterior a hoy.");
        }

        if (fechaMatriculacion.isBefore(fechaMatriculacion.minusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA))){
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a " + MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.");
        }

        this.fechaMatriculacion = fechaMatriculacion;
    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {

        if (fechaAnulacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación de una matrícula no puede ser posterior a hoy.");
        }

        if (fechaAnulacion.isBefore(fechaMatriculacion)){
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");
        }

        if (fechaAnulacion.isBefore(fechaAnulacion.minusMonths(MAXIMO_MESES_ANTERIOR_ANULACION))){
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a " + MAXIMO_MESES_ANTERIOR_ANULACION + " meses.");
        }

        this.fechaAnulacion = fechaAnulacion;
    }

    public Alumno getAlumno() {
        //Utilizo el new para crear una copia y así cotrolar bien cuando se hagan las copias profundas.
        return new Alumno (alumno);
    }

    public void setAlumno(Alumno alumno) {
        Objects.requireNonNull(alumno,"ERROR: El alumno de una matrícula no puede ser nulo.");
        this.alumno = alumno;
    }

    public Asignatura[] getColeccionAsignaturas() {
        return coleccionAsignaturas;
    }

    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas) throws OperationNotSupportedException {
        Objects.requireNonNull(coleccionAsignaturas,"ERROR: La lista de asignaturas de una matrícula no puede ser nula.");

        if (!(superaMaximoNumeroHorasMatricula(coleccionAsignaturas))){
            this.coleccionAsignaturas = coleccionAsignaturas;
        }
        else{
            throw new OperationNotSupportedException("ERROR: No se puede realizar la matrícula ya que supera el máximo de horas permitidas (" + MAXIMO_NUMERO_HORAS_MATRICULA + " horas).");
        }
    }

    private boolean superaMaximoNumeroHorasMatricula (Asignatura[] asignaturasMatricula){

        int totalHoras=0;

        for (Asignatura asignatura : asignaturasMatricula) {
            totalHoras = totalHoras + asignatura.getHorasAnuales() + asignatura.getHorasDesdoble();
        }

        if (totalHoras > MAXIMO_NUMERO_HORAS_MATRICULA) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matricula matricula)) return false;
        return idMatricula == matricula.idMatricula;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idMatricula);
    }

    private String asignaturasMatricula(){

        //Me fijo cómo está definido en el test.
        StringBuilder resultado= new StringBuilder();

        for(Asignatura asignatura : coleccionAsignaturas)
        {
            if (asignatura!=null)
                resultado.append(asignatura.imprimir());
        }

        return resultado.toString();
    }

    public String imprimir(){
        return String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s, alumno={%s}",
                this.getIdMatricula(), this.getCursoAcademico(),
                this.getFechaMatriculacion().format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)),
                this.getAlumno().imprimir());
    }

    @Override
    public String toString() {

        if (this.getFechaAnulacion()==null){
            return String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s, alumno=%s, Asignaturas={ %s}",
                    this.getIdMatricula(), this.getCursoAcademico(),
                    this.getFechaMatriculacion().format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)),
                    this.getAlumno().imprimir(), asignaturasMatricula());
        }
        else{
            return String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s, fecha anulación=%s, alumno=%s, Asignaturas={ %s}",
                    this.getIdMatricula(), this.getCursoAcademico(),
                    this.getFechaMatriculacion().format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)),
                    this.getFechaAnulacion().format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)),
                    this.getAlumno().imprimir(), asignaturasMatricula());
        }
    }
}
