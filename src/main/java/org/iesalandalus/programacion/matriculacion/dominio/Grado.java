package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {
    GDCFGB ("GDCFGB"),
    GDCFGM ("GDCFGM"),
    GDCFGS ("GDCFGS");

    private String cadenaAMostrar;

    private Grado (String cadenaAMostrar){
        this.cadenaAMostrar=cadenaAMostrar;
    }

    public String imprimir() {
        //Utilizo ordinal para que indique la posición ordinal donde está la cadenaAMostrar en el enumerado.
        String cadena;
        cadena = Grado.valueOf(cadenaAMostrar).ordinal() + ".-" + cadenaAMostrar;
        return cadena;
    }

    @Override
    public String toString() {
        return String.format("Grado=%s",cadenaAMostrar);
    }
}
