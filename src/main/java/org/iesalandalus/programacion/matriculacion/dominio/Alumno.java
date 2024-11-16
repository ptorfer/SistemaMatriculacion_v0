package org.iesalandalus.programacion.matriculacion.dominio;

import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {

    //Atributos
    private static final String ER_TELEFONO = "[968]?[0-9]{8}";
    private static final String ER_CORREO = "\\w+[\\.\\w]*@\\w+[\\.\\w]*\\.\\w{2,5}\\b\\s?";
    private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
    public static final String FORMATO_FECHA = "dd/MM/YYYY";
    private static final String ER_NIA = "([a-z]{4})([0-9]{3})";
    private static final int MIN_EDAD_ALUMNADO = 16;
    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;

    //Constructores
    public Alumno(String nomobre, String dni, String correo, String telefono, LocalDate fechaNacimiento){
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }

    public Alumno(Alumno alumno){
        Objects.requireNonNull(alumno,"ERROR: No es posible copiar un alumno nulo.");
        setNombre(alumno.getNombre());
        setDni(alumno.getDni());
        setCorreo(alumno.getCorreo());
        setTelefono(alumno.getTelefono());
        setFechaNacimiento(alumno.getFechaNacimiento());
    }

    //Métodos

    public String getNia() {
        return nia;
    }

    private void setNia() {

        if(nia.matches(ER_NIA)){
            //En las substring la posición desde no está incluida.
            String parteNombre=getNombre().substring(0,4).toLowerCase();
            String parteDni=getDni().substring(5,8);
            this.nia=parteNombre+parteDni;
        }
        else{
            throw new IllegalArgumentException("El cálculo del Número de Identificación del Alumno no es el correcto.");
        }
    }

    private void setNia(String nia) {
        if (nia.matches(ER_NIA)) {
            this.nia = nia;
        }
        else{
            throw new IllegalArgumentException("Nia incorrecto.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre,"ERROR: El nombre de un alumno no puede ser nulo.");
        if (nombre.isEmpty()){
            throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");
        }
        this.nombre = formateaNombre(nombre);
    }

    private String formateaNombre (String nombre){

        //Utilizo trim para quitar los espacios en blanco al principio y final de la palabra
        nombre=nombre.trim();

        //Divido el nombre en palabras
        String palabras [] = nombre.split("\\s+");

        //Utilizo StringBuilder ya que voy a crear varias veces el objeto String nombre
        StringBuilder nombreNormalizado = new StringBuilder();

        for (String palabra : palabras){
            if (palabra.length()>0){
                nombreNormalizado.append(Character.toUpperCase(palabra.charAt(0)));
                nombreNormalizado.append(palabra.substring(1).toLowerCase());
                nombreNormalizado.append(" ");
            }
        }

        return nombreNormalizado.toString().trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono,"ERROR: El teléfono de un alumno no puede ser nulo.");
        if (telefono.matches(ER_TELEFONO)) {
            this.telefono = telefono;
        }
        else {
            throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        Objects.requireNonNull(correo,"ERROR: El correo de un alumno no puede ser nulo.");
        if (!correo.matches(ER_CORREO)){
            throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");
        }
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        Objects.requireNonNull(dni,"ERROR: El dni de un alumno no puede ser nulo.");
        if(dni.matches(ER_DNI)){
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        }
        if(!comprobarLetraDni(dni)){
            throw new IllegalArgumentException("ERROR: La letra del dni del alumno no es correcta.");
        }
        this.dni = dni;
    }

    private boolean comprobarLetraDni (String dni){

        //Vamos a separar el número de la letra del dni.
        Pattern patron;
        Matcher comparador;

        //Compilo la expresión regular en un patrón
        patron = Pattern.compile(ER_DNI);

        //Creo un objeto Matcher con la cadena (dni) y el patrón para luego poder verificar si la cadena coincide con el patrón.
        comparador = patron.matcher(dni);

        //El metodo matches() verifica si toda la cadena coincide con el patrón. Si lo hace devuelve true.
        if (comparador.matches()){

            String numeroDni= comparador.group(1);
            char letraDni= comparador.group(2).charAt(0);

            //Para calcular si la letra es válida:
            final char[] LETRAS_DNI = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};

            int indice = Integer.valueOf(letraDni) % 23;

            char letraCalculada=LETRAS_DNI[indice];

            if(letraDni==letraCalculada){
                return true;
            }
            else{
                return false;
            }
        }
        return false; //No toda la cadena coincide con el patrón.
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {

        Objects.requireNonNull(fechaNacimiento,"La fecha de nacimiento de un alumno no puede ser nula.");
        //Voy a calcular los años desde el nacimiento hasta la fecha actual.
        Period periodo=Period.between(fechaNacimiento,LocalDate.now());
        int anio = periodo.getYears();
        if (!(anio>=MIN_EDAD_ALUMNADO)){
            throw new IllegalArgumentException ("ERROR: La edad del alumno debe ser mayor o igual a 16 años.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    private String getIniciales(){

        String palabras [] = getNombre().split("\\s+");

        StringBuilder iniciales = new StringBuilder();

        for (String palabra : palabras){
            if (palabra.length()>0){
                iniciales.append(palabra.charAt(0));
            }
        }
        return iniciales.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno alumno)) return false;
        return Objects.equals(dni, alumno.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    @Override
    public String toString() {

        return String.format("Número de Identificación del Alumnado (NIA)=%s " + "nombre=%s (%s), DNI=%s, correo=%s, teléfono=%s, fecha nacimiento=%s", this.getNia(), this.getNombre(), this.getIniciales(), this.getDni(), this.getCorreo(), this.getTelefono(), this.getFechaNacimiento().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)));
    }
}
