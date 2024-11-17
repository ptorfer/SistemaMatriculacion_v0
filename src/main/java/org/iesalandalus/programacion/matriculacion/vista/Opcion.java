package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.Curso;

public enum Opcion {

    SALIR ("SALIR"),
    INSERTAR_ALUMNO ("INSERTAR_ALUMNO"),
    BUSCAR_ALUMNO ("BUSCAR ALUMNO"),
    BORRAR_ALUMNO ("BORRAR ALUMNO"),
    MOSTRAR_ALUMNOS ("MOSTRAR ALUMNOS"),
    INSERTAR_CICLO_FORMATIVO ("INSERTAR CICLO FORMATIVO"),
    BUSCAR_CICLO_FORMATIVO ("BUSCAR CICLO FORMATIVO"),
    BORRAR_CICLO_FORMATIVO ("BORRAR CICLO FORMATIVO"),
    MOSTRAR_CICLOS_FORMATIVOS ("MOSTRAR CICLOS FORMATIVOS"),
    INSERTAR_ASIGNATURA ("INSERTAR ASIGNATURA"),
    BUSCAR_ASIGNATURA ("BUSCAR ASIGNATURA"),
    BORRAR_ASIGNATURA ("BORRAR ASIGNATURA"),
    MOSTRAR_ASIGNATURAS ("MOSTRAR ASIGNATURAS"),
    INSERTAR_MATRICULA ("INSERTAR MATRICULA"),
    BUSCAR_MATRICULA ("BUSCAR MATRICULA"),
    ANULAR_ASIGNATURA ("ANULAR ASIGNATURA"),
    MOSTRAR_MATRICULAS ("MOSTRAR MATRICULAS"),
    MOSTRAR_MATRICULAS_ALUMNO ("MOSTRAR MATRICULAS ALUMNO"),
    MOSTRAR_MATRICULAS_CICLO_FORMATIVO ("MOSTRAR MATRICULAS CICLO FORMATIVO"),
    MOSTRAR_MATRICULAS_CURSO_ACADEMICO ("MOSTRAR MATRICULAS CURSO ACADEMICO");

    //Atributo
    private String cadenaAMostrar;

    //Constructor
    private Opcion (String cadenaAMostrar){
        this.cadenaAMostrar=cadenaAMostrar;
    }

    //Métodos

    @Override
    public String toString() {

        //Utilizo ordinal para que indique la posición ordinal donde está la cadenaAMostrar en el enumerado.
        String cadena;
        cadena = Opcion.valueOf(cadenaAMostrar).ordinal() + ".-" + cadenaAMostrar;
        return String.format("Opción=%s", cadena);
    }
}
