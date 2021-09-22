/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando Kramer
 */
public class ValidaCEP {
    
    public static boolean validarCep(JFormattedTextField cepp){
        String cep = cepp.getText().replace(" ", "").replace(".", "").replace("-", "").trim();
        
        boolean retorno;
        
        if(cep.length() == 8){
            if(cep.equals("00000000") || cep.equals("00000000")
                || cep.equals("00000000") || cep.equals("00000000")
                || cep.equals("00000000") || cep.equals("00000000")
                || cep.equals("00000000") || cep.equals("00000000")
                || cep.equals("00000000") || cep.equals("00000000")){
                cepp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow));
                JOptionPane.showMessageDialog(null, "CEP está em um formato incorreto!!!");
                retorno = false;
            }else{
                retorno = true;
            }
        }else{
            cepp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow));
            JOptionPane.showMessageDialog(null, "CEP está em um formato incorreto!!!");
            retorno = false;
        }
        return retorno;
    }
    
}
