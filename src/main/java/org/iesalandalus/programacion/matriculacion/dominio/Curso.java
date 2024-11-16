package org.iesalandalus.programacion.matriculacion.dominio;

public enum Curso {

    PRIMERO ("PRIMERO"),
    SEGUNDO ("SEGUNDO");

    private String cadenaAMostrar;

    private Curso (String cadenaAMostrar){
        this.cadenaAMostrar=cadenaAMostrar;
    }

    public String imprimir() {
        //Utilizo ordinal para que indique la posición ordinal donde está la cadenaAMostrar en el enumerado.
        String cadena;
        cadena = Curso.valueOf(cadenaAMostrar).ordinal() + ".-" + cadenaAMostrar;
        return cadena;
    }

    @Override
    public String toString() {
        return String.format("Curso=%s",cadenaAMostrar);
    }
}
