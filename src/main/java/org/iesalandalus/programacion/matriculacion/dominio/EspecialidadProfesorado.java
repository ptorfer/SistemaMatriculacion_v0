package org.iesalandalus.programacion.matriculacion.dominio;

public enum EspecialidadProfesorado {
    INFORMATICA ("INFORMATICA"),
    SISTEMAS ("SISTEMAS"),
    FOL ("FOL");

    //Atributo
    private String cadenaAMostrar;

    //Constructor
    private EspecialidadProfesorado(String cadenaAMostrar){
        this.cadenaAMostrar=cadenaAMostrar;
    }

    //Métodos
    public String imprimir(){
        //Utilizo ordinal para que indique la posición ordinal donde está la cadenaAMostrar en el enumerado.
        String cadena;
        cadena = EspecialidadProfesorado.valueOf(cadenaAMostrar).ordinal() + ".-" + cadenaAMostrar;
        return cadena;
    }

    @Override
    public String toString() {
        return String.format("Especialidad Profesorado=%s",cadenaAMostrar);
    }
}
