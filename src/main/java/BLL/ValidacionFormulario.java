/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.util.regex.*;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class ValidacionFormulario {
    //valida campo nombre. Acepta compuestos
    public boolean validaNombresCompuestos (String nombre){
        boolean ok =false;
        //Pattern pat = Pattern.compile("/^([a-zA-Z0-9áéíóúñ][/\\s\\][a-zA-Z0-9áéíóúñ]{2,30})$/");
        //Pattern pat = Pattern.compile("[a-zA-Z0-9áéíóúñ]{2,30}");
       //Pattern pat = Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$");
       Pattern pat = Pattern.compile("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}");
        Matcher mat = pat.matcher(nombre);
        if(mat.matches()){
            ok=true;
        }
        return ok;
    }
    
    //valida email
    public boolean validaEmail(String email){
        boolean ok =false;
        //Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        //Pattern pat = Pattern.compile("/[\\w-\\.]{3,}@([\\w-]{2,}\\.)*([\\w-]{2,}\\.)[\\w-]{2,4}/");//de cliente
        Pattern pat = Pattern.compile("[\\w-\\.]{3,}@([\\w-]{2,}\\.)*([\\w-]{2,}\\.)[\\w-]{2,4}");//de cliente sin barra
        Matcher mat = pat.matcher(email);
        if(mat.find()){
            ok=true;
        }
        return ok;
    }
            
    //valida teléfono→ 9 dígitos (móvil o fijo)
    public boolean validaTfn(String telefono){
        boolean ok =false;
        Pattern pat = Pattern.compile("^[6|7|9]{1}[\\d]{8}$");
        Matcher mat = pat.matcher(telefono);
        if(mat.matches()){
            ok=true;
        }
        return ok;
    }
    
    //valida contraseña
    //debe contener una letra y un número y tener longitud mínima de 6 caractres
    public boolean validaPass (String pass){
        boolean ok =false;
        Pattern pat = Pattern.compile("^(?=[\\w]{6,})(?=.*[a-zA-Z])(?=.*[0-9]).*$");
        Matcher mat = pat.matcher(pass);
        if(mat.matches()){
            ok=true;
        }
        return ok;
    }
}
