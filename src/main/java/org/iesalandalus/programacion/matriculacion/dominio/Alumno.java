package org.iesalandalus.programacion.matriculacion.dominio;

import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
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

    public void setNia() {
        //En las substring la posición desde no está incluida.
        String parteNombre=getNombre().substring(0,4).toLowerCase();
        String parteDni=getDni().substring(5,8);
        String nia=parteNombre+parteDni;
    }

    public void setNia(String nia) {
        this.nia = nia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
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
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    private boolean comprobarLetraDni (String dni){

        //Vamos a separar el número de la letra del dni.
        Pattern patron;
        Matcher comparador;

        //Compilo la expresión regular en un patrón
        patron = Pattern.compile(ER_DNI);
        do {
            System.out.print("Introduce un DNI: ");
            dni = Entrada.cadena();
            //Creo un objeto Matcher con la cadena (dni) y el patrón para luego poder verificar si la cadena coincide con el patrón.
            comparador = patron.matcher(dni);
            //El metodo matches() verifica si toda la cadena coincide con el patrón. Si lo hace devuelve true.
        } while (!comparador.matches());

        if (comparador.matches()){

            String numeroDni= comparador.group(1);
            String letraDni= comparador.group(2);
            System.out.printf("Número dni: %s%n", numeroDni);
            System.out.printf("Letra dni: %s%n", letraDni);

            //Para calcular si la letra es válida:
            final char[] LETRAS_DNI = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};

            int letraCalculada = Integer.valueOf(letraDni) % 23;

            System.out.println(LETRAS_DNI[letraCalculada]);

            if (letraDni.equals(letraCalculada)){
                return true; //La letra del dni es correcta.
            }
        }
        return false; //la letra del dni no es correcta.
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
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
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", nia='" + nia + '\'' +
                '}';
    }
}
