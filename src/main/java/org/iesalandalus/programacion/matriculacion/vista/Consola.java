package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Consola {

    //Constructor
    private Consola (){}

    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()){
            System.out.println(opcion.toString());
        }
    }

    public static Opcion elegirOpcion(){
        int opcion;
        do{
            System.out.println("Introduzca una opción del menú.");
            opcion = Entrada.entero();
        }
        while (!(opcion>=0 && opcion<=Opcion.values().length-1));
        return Opcion.values()[opcion];
    }

    public static Alumno leerAlumno(){

        Alumno alumno = null;
        String nombre;
        String telefono;
        String correo;
        String dni;
        LocalDate fechaNacimiento;

        do{
            System.out.println("Introduzca el nombre de un alumno.");
            nombre=Entrada.cadena();
        }
        while (nombre.trim().isEmpty());

        do{
            System.out.println("Introduzca el teléfono de un alumno.");
            telefono = Entrada.cadena();
        }
        while (telefono.trim().isEmpty());

        do{
            System.out.println("Introduzca el correo de un alumno.");
            correo = Entrada.cadena();
        }
        while (correo.trim().isEmpty());

        do{
            System.out.println("Introduzca el dni de un alumno.");
            dni = Entrada.cadena();
        }
        while (dni.trim().isEmpty());


        String mensaje= "Introduzca la fecha de nacimiento de un alumno.";
        fechaNacimiento= leerFecha(mensaje);

        alumno = new Alumno (nombre, telefono, correo, dni, fechaNacimiento);

        //Utilizo new en el return para crear una copia del objeto. Así controlo mejor la copia profunda. Así lo haré en el resto de return cuando devuelva un objeto.
        return new Alumno (alumno);
    }

    public static Alumno getAlumnoPorDni(){

        Alumno alumno = null;
        String nombre = "Alumno Ficticio";
        String telefono = "612345678";
        String correo = "alumnoFicticio@gmail.com";
        String dni = "12345678A";
        LocalDate fechaNacimiento = LocalDate.now();

        do{
            System.out.println("Introduzca el dni de un alumno.");
            dni = Entrada.cadena();
        }
        while (dni.trim().isEmpty());

        alumno = new Alumno (nombre, telefono, correo, dni, fechaNacimiento);

        return new Alumno (alumno);
    }

    public static LocalDate leerFecha (String mensaje){
        LocalDate fecha =null;
        boolean fechaCorrecta= false;

        do{
            try {
                System.out.printf(mensaje, Alumno.FORMATO_FECHA);
                fecha = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
                fechaCorrecta = true;
            }
            catch (DateTimeParseException e){
                fechaCorrecta = false;
            }
        }
        while (!fechaCorrecta);
        return fecha;
    }

    public static Grado leerGrado(){
        int eleccion;
        do{
            System.out.println("Introduzca un Grado");

            for (Grado grado: Grado.values()){
                grado.imprimir();
            }
            eleccion=Entrada.entero();
        }
        while (!(eleccion>=0 && eleccion < Grado.values().length));
        return Grado.values()[eleccion];
    }

    public static CicloFormativo leerCicloFormativo(){

        int codigo;
        String familiaProfesional;
        Grado grado;
        String nombre;
        int horas;

        System.out.println("Introduzca el código del Ciclo Formativo.");
        codigo = Entrada.entero();

        System.out.println("Introduzca la familia profesional del Ciclo Formativo.");
        familiaProfesional = Entrada.cadena();

        System.out.println("Introduzca el grado del Ciclo Formativo.");
        grado = leerGrado();

        do {
            System.out.println("Introduzca el nombre del Ciclo Formativo.");
            nombre = Entrada.cadena();
        }
        while (nombre.isEmpty());

        System.out.println("Introduzca las horas del Ciclo Formativo.");
        horas = Entrada.entero();

        CicloFormativo cicloFormativo = new CicloFormativo(codigo,familiaProfesional,grado,nombre,horas);

        return new CicloFormativo(cicloFormativo);
    }

    public static void mostrarCiclosFormativos (CiclosFormativos ciclosFormativos){
        CicloFormativo [] listaCiclosFormativos = ciclosFormativos.get();
        if (listaCiclosFormativos.length>0){
            for (CicloFormativo cicloFormativo: listaCiclosFormativos){
                System.out.println(cicloFormativo);
            }
        }
    }

    public static CicloFormativo geCcicloFormativoPorCodigo (){

        CicloFormativo cicloFormativo=null;
        int codigo;
        String familiaProfesional="Informática y Comunicaciones";
        Grado grado= Grado.GDCFGS;
        String nombre = "Isabel";
        int horas= 500;

        System.out.println("Introduzca el código del Ciclo Formativo.");
        codigo = Entrada.entero();

        cicloFormativo = new CicloFormativo(codigo,familiaProfesional,grado,nombre,horas);

        return new CicloFormativo(cicloFormativo);
    }

    public static Curso leerCurso(){
        int eleccion;
        do{
            System.out.println("Introduzca un Curso");

            for (Curso curso: Curso.values()){
                curso.imprimir();
            }
            eleccion=Entrada.entero();
        }
        while (!(eleccion>=0 && eleccion < Curso.values().length));
        return Curso.values()[eleccion];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado (){
        int eleccion;
        do{
            System.out.println("Introduzca una Especialidad de Profesorado");

            for (EspecialidadProfesorado especialidadProfesorado: EspecialidadProfesorado.values()){
                especialidadProfesorado.imprimir();
            }
            eleccion=Entrada.entero();
        }
        while (!(eleccion>=0 && eleccion < EspecialidadProfesorado.values().length));

        return EspecialidadProfesorado.values()[eleccion];
    }

    public static Asignatura leerAsignatura (CiclosFormativos ciclosFormativos) {
        String codigo;
        String nombre;
        int horasAnuales;
        Curso curso;
        int horasDesdoble;
        EspecialidadProfesorado especialidadProfesorado;
        CicloFormativo cicloFormativo;
        Asignatura asignatura;

        System.out.println("Introduzca el código de la asignatura.");
        codigo = Entrada.cadena();

        System.out.println("Introduzca el nombre de la asignatura.");
        nombre = Entrada.cadena();

        System.out.println("Introduzca las horas anuales de la asignatura.");
        horasAnuales = Entrada.entero();

        curso=leerCurso();

        System.out.println("Introduzca las horas desdoble de la asignatura.");
        horasDesdoble = Entrada.entero();

        especialidadProfesorado = leerEspecialidadProfesorado();

        cicloFormativo=leerCicloFormativo();

        CicloFormativo cicloFormativoBuscado = ciclosFormativos.buscar(cicloFormativo);

        asignatura = new Asignatura (codigo,nombre,horasAnuales,curso,horasDesdoble,especialidadProfesorado,cicloFormativoBuscado);

        return new Asignatura (asignatura);
    }

    public static Asignatura getAsignaturaPorCodigo(){
        String codigo;
        String nombre="Programación";
        int horasAnuales=256;
        Curso curso=Curso.PRIMERO;
        int horasDesdoble=4;
        EspecialidadProfesorado especialidadProfesorado=EspecialidadProfesorado.INFORMATICA;
        CicloFormativo cicloFormativo= new CicloFormativo(1225,"Informática y Comunicaciones",Grado.GDCFGS,"DAM",1000);
        Asignatura asignatura;

        System.out.println("Introduzca el código de la asignatura.");
        codigo = Entrada.cadena();

        asignatura = new Asignatura (codigo,nombre,horasAnuales,curso,horasDesdoble,especialidadProfesorado,cicloFormativo);

        return new Asignatura (asignatura);
    }

    private static void mostrarAsignaturas (Asignaturas asignaturas){
        Asignatura [] listaAsignaturas = asignaturas.get();
        if (listaAsignaturas.length>0){
            for (Asignatura asignatura: listaAsignaturas){
                System.out.println(asignatura);
            }
        }
    }

    private static boolean asignaturaYaMatriculada (Asignatura [] asignaturasMatricula, Asignatura asignatura){

        if (asignatura !=null) {
            for (int i=0; i<asignaturasMatricula.length;i++) {
                if (asignaturasMatricula[i].equals(asignatura)){
                    return true;
                }
            }
        }
         return false;
    }

    public static Matricula leerMatricula (Alumnos alumnos,Asignaturas asignaturas) throws OperationNotSupportedException {

        int idMatricula;
        String cursoAcademico;
        LocalDate fechaMatriculacion;
        Alumno alumno;
        Matricula matricula;

        System.out.println("Introduzca el id de la Matrícula.");
        idMatricula = Entrada.entero();

        System.out.println("Introduzca el curso Académico.");
        cursoAcademico = Entrada.cadena();

        String mensaje= "Introduzca la fecha de matriculación.";
        fechaMatriculacion = leerFecha(mensaje);

        alumno= leerAlumno();
        Alumno alumnoBuscado=alumnos.buscar(alumno);

        matricula = new Matricula (idMatricula, cursoAcademico, fechaMatriculacion, alumno, asignaturas.get());

        return new Matricula (matricula);
    }

    public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {
        int idMatricula;
        String cursoAcademico="24-25";
        LocalDate fechaMatriculacion=LocalDate.now();
        Alumno alumno = new Alumno ("José Ramón Jiménez Reyes","11223344B","joseramon.jimenez@iesalandalus.org","950112233",LocalDate.of(2002,9,15));

        CicloFormativo cicloFormativo = new CicloFormativo(1225,"Informática y Comunicaciones",Grado.GDCFGS,"DAM",1000);

        Asignatura asignatura = new Asignatura("0100","Programación",256,Curso.PRIMERO,4,EspecialidadProfesorado.INFORMATICA,cicloFormativo);
        Asignatura asignatura2= new Asignatura("0200","Base de Datos",100,Curso.PRIMERO,3,EspecialidadProfesorado.INFORMATICA,cicloFormativo);

        Asignaturas coleccionAsignaturas =new Asignaturas(2);
        coleccionAsignaturas.insertar(asignatura);
        coleccionAsignaturas.insertar(asignatura2);

        Matricula matricula;

        System.out.println("Introduzca el id de la Matrícula.");
        idMatricula = Entrada.entero();

        matricula = new Matricula (idMatricula, cursoAcademico, fechaMatriculacion, alumno, coleccionAsignaturas.get());

        return new Matricula (matricula);
    }





}
