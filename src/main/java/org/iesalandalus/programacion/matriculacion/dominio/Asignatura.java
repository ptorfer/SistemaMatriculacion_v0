package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class Asignatura {

    //Atributos
    public static final int MAX_NUM_HORAS_ANUALES=300;
    public static final int MAX_NUM_HORAS_DESDOBLES=6;
    private static final String ER_CODIGO="[0-9]{4}";
    private String codigo;
    private String nombre;
    private int horasAnuales;
    private Curso curso;
    private int horasDesdoble;
    private EspecialidadProfesorado especialidadProfesorado;
    private CicloFormativo cicloFormativo;

    //Constructores

    public Asignatura(String codigo,String nombre,int horasAnuales,Curso curso,int horasDesdoble,EspecialidadProfesorado especialidadProfesorado,CicloFormativo cicloFormativo){
        setCodigo(codigo);
        setNombre(nombre);
        setHorasAnuales(horasAnuales);
        setCurso(curso);
        setHorasDesdoble(horasDesdoble);
        setEspecialidadProfesorado(especialidadProfesorado);
        setCicloFormativo(cicloFormativo);
    }

    public Asignatura(Asignatura asignatura){
        Objects.requireNonNull(asignatura,"ERROR: No es posible copiar una asignatura nula.");

        setCodigo(asignatura.getCodigo());
        setNombre(asignatura.getNombre());
        setHorasAnuales(asignatura.getHorasAnuales());
        setCurso(asignatura.getCurso());
        setHorasDesdoble(asignatura.getHorasDesdoble());
        setEspecialidadProfesorado(asignatura.getEspecialidadProfesorado());
        setCicloFormativo(asignatura.getCicloFormativo());
    }

    //Métodos

    public CicloFormativo getCicloFormativo() {
        //Utilizo el new para crear una copia y así cotrolar bien cuando se hagan las copias profundas.
        return new CicloFormativo (cicloFormativo);
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        Objects.requireNonNull(cicloFormativo, "ERROR: El ciclo formativo de una asignatura no puede ser nulo.");

        this.cicloFormativo = cicloFormativo;
    }

    public String getCodigo() {
        return codigo;
    }

    private void setCodigo(String codigo) {
        Objects.requireNonNull(codigo,"ERROR: El código de una asignatura no puede ser nulo.");

        if (codigo.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El código de una asignatura no puede estar vacío.");
        }
        if (!codigo.matches(ER_CODIGO)){
            throw new IllegalArgumentException("ERROR: El código de la asignatura no tiene un formato válido.");
        }
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre,"ERROR: El nombre de una asignatura no puede ser nulo.");
        if (nombre.isEmpty()){
            throw new IllegalArgumentException("ERROR: El nombre de una asignatura no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) {
        if (!(horasAnuales<=0 && horasAnuales>MAX_NUM_HORAS_ANUALES)){
            this.horasAnuales = horasAnuales;
        }
        else{
            throw new IllegalArgumentException("ERROR: El número de horas de una asignatura no puede ser menor o igual a 0 ni mayor a " + MAX_NUM_HORAS_ANUALES + ".");
        }
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        Objects.requireNonNull(curso,"ERROR: El curso de una asignatura no puede ser nulo.");
        this.curso = curso;
    }

    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    public void setHorasDesdoble(int horasDesdoble) {
        if (!(horasDesdoble <= 0 && horasDesdoble > MAX_NUM_HORAS_DESDOBLES)) {
            this.horasDesdoble = horasDesdoble;
        } else {
            throw new IllegalArgumentException("ERROR: El número de horas de desdoble de una asignatura no puede ser menor a 0 ni mayor a " + MAX_NUM_HORAS_DESDOBLES + ".");
        }
    }

    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        Objects.requireNonNull(especialidadProfesorado,"ERROR: La especialidad del profesorado de una asignatura no puede ser nula.");

        this.especialidadProfesorado = especialidadProfesorado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asignatura that)) return false;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    public String imprimir(){
        return String.format("Código asignatura=%s, nombre asignatura=%s, ciclo formativo=%s", this.getCodigo(), this.getNombre(), this.getCicloFormativo().imprimir());
    }

    @Override
    public String toString() {

        return String.format("Código=%s, nombre=%s, horas anuales=%d, curso=%s, horas desdoble=%d, ciclo formativo=%s, especialidad profesorado=%s", this.getCodigo(), this.getNombre(),this.getHorasAnuales(),
                this.getCurso(),this.getHorasDesdoble(), this.getCicloFormativo().imprimir(),this.getEspecialidadProfesorado());
    }
}


