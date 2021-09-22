/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 *
 * @author Fernando Kramer
 */
public class ValidaCampo {
    
    public static boolean campoEstaVazio(JTextField campo){        
        String camp = campo.getText().replace(" ", "").replace(".", "").replace("-", "").replace("(", "").replace(")", "").replace("/", "").trim();
        boolean retorno;
        if(camp.equals("") || camp.equals(" ")){
            campo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
            retorno = true;
        }else{
            retorno = false;
        }
        return retorno;   
    }
    
    public static String limpaString(JTextField campo){
        return campo.getText().replace(" ", "").replace(".", "").replace("-", "").replace("(", "").replace(")", "").replace("/", "").trim();
    }
 
}
