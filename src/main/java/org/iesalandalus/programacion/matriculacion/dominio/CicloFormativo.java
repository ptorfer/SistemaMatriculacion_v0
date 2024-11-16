package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class CicloFormativo {

    //Atributos
    public static final int MAXIMO_NUMERO_HORAS=2000;
    private int codigo;
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;

    //Constructores
    public CicloFormativo (int codigo,String familiaProfesional,Grado grado,String nombre,int horas){
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }

    public CicloFormativo (CicloFormativo cicloFormativo){
        Objects.requireNonNull("ERROR: No es posible copiar un ciclo formativo nulo.");
        setCodigo(cicloFormativo.getCodigo());
        setFamiliaProfesional(cicloFormativo.getFamiliaProfesional());
        setGrado(cicloFormativo.getGrado());
        setNombre(cicloFormativo.getNombre());
        setHoras(cicloFormativo.getHoras());
    }

    //Métodos


    public int getCodigo() {
        return codigo;
    }

    private void setCodigo(int codigo) {
        if (codigo>=1000 && codigo<=9999){
            this.codigo = codigo;
        }
        else{
            throw new IllegalArgumentException("Código incorrecto");
        }
    }

    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(String familiaProfesional) {
        Objects.requireNonNull("ERROR: La familia profesional de un ciclo formativo no puede ser nula.");
        if (familiaProfesional.isEmpty()){
            throw new IllegalArgumentException("ERROR: La familia profesional no puede estar vacía.");
        }
        if (familiaProfesional.equals("Informática y Comunicaciones")){
            this.familiaProfesional = familiaProfesional;
        }
        else{
            throw new IllegalArgumentException("Familia Profesional incorrecta");
        }

    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        Objects.requireNonNull("ERROR: El grado de un ciclo formativo no puede ser nulo.");
        if(grado == Grado.GDCFGB||grado==Grado.GDCFGM||grado==Grado.GDCFGS){
            this.grado = grado;
        }
        else{
            throw new IllegalArgumentException("Grado incorrecto");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull("ERROR: El nombre de un ciclo formativo no puede ser nulo.");
        if(nombre.isEmpty()){
            throw new IllegalArgumentException("ERROR: El nombre de un ciclo formativo no puede estar vacío.");
        }
        if((nombre.equals("IIM"))||(nombre.equals("SMR"))||(nombre.equals("ASIR"))||(nombre.equals("DAM"))||(nombre.equals("DAW"))){
            this.nombre = nombre;
        }
        else{
            throw new IllegalArgumentException("Nombre incorrecto.");
        }
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        if(!(horas<=0 && horas>MAXIMO_NUMERO_HORAS)){
            this.horas = horas;
        }
        else{
            throw new IllegalArgumentException("ERROR: El número de horas de un ciclo formativo no puede ser menor o igual a 0 ni mayor a " + MAXIMO_NUMERO_HORAS + ".");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CicloFormativo that)) return false;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    public String imprimir(){

        return String.format("Código ciclo formativo=%d, nombre ciclo formativo=%s",this.getCodigo(),this.getNombre());
    }

    @Override
    public String toString() {

        return String.format("Código ciclo formativo=%d, familia profesional=%s, grado=%s, nombre ciclo formativo=%s, horas=%s",this.getCodigo(),this.getFamiliaProfesional(),this.getGrado(),this.getNombre(),this.getHoras());
    }
}
