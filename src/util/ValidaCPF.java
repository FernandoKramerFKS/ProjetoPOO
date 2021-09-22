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
public class ValidaCPF {
    
    public static boolean verificaCpfValido(JFormattedTextField campo){
        boolean retorno = true;
        String cpf = campo.getText().replace(" ", "").replace(".", "").replace("-", "").trim();
        System.out.println(cpf);
        if(cpf.length() == 11){
            if(cpf.equals("00000000000") || cpf.equals("11111111111")
                    || cpf.equals("22222222222") || cpf.equals("33333333333")
                    || cpf.equals("44444444444") || cpf.equals("55555555555")
                    || cpf.equals("66666666666") || cpf.equals("77777777777")
                    || cpf.equals("88888888888") || cpf.equals("99999999999")){
                campo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow));
                JOptionPane.showMessageDialog(null, "O Campo CPF está invalido, por favor verifique!!!");
                retorno = false;
            }
        }
        return retorno;
    }
}
