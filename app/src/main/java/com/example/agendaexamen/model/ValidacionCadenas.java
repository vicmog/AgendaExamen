package com.example.agendaexamen.model;

public class ValidacionCadenas {

    public static boolean campoVacio(String cad){
        if (cad.length()==0) {
            return true;
        }
        return false;
    }

    public static boolean cadenaEsNumerica(String cad){
        for (int i = 0; i < cad.length(); i++) {
            if(!Character.isDigit(cad.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean cadenaEsTelefono(String cad){

        for (int i = 0; i < cad.length(); i++) {
            if(!Character.isDigit(cad.charAt(i))){
                return false;
            }
        }
        if(cad.length()<9 || cad.length()>9){
            return false;
        }
        return true;
    }




}
