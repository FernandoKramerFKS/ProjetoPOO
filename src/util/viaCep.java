/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Endereco;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Fernando Kramer
 */
public class viaCep {
    
    public entity.Endereco buscarCep(String cep) {
        String json;
        try {
            URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));

            json = jsonSb.toString();
            
            Map<String, String> mapa = new HashMap<>();
            System.out.println("oi");
            Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(json);
            
            while (matcher.find()) {
                String[] group = matcher.group().split(":");
                mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
            }
            
            Endereco end = new Endereco();
            
            if(!mapa.isEmpty()){
                String cepp = mapa.get("cep").replace("-", "");
                String uff = mapa.get("uf");
                String estado = retornaEstado(uff);
                String cidadee = mapa.get("localidade");
                String bairro = mapa.get("bairro");
                String rua = mapa.get("logradouro");
                
                end.setCep(cepp);
                end.setPais("Brasil");
                end.setEstado(estado);
                end.setUf(uff);
                end.setCidade(cidadee);
                end.setBairro(bairro);  
                end.setRua(rua);
            }else{
                end = null;
            }
            return end;
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }       
    }
        
    public String retornaEstado(String UF){
        
        String estado = null;
        
         switch(UF){
                case "AC":
                    estado = "Acre";
                    break;
                case "AL":
                    estado = "Alagoas";
                    break;
                case "AP":
                    estado = "Amapá";
                    break;
                case "AM":
                    estado = "Amazonas";
                    break;
                case "BA":
                    estado = "Bahia";
                    break;
                case "CE":
                    estado = "Ceará";
                    break;
                case "ES":
                    estado = "Espírito Santo";
                    break;
                case "GO":
                    estado = "Goiás";
                    break;
                case "MA":
                    estado = "Maranhão";
                    break;
                case "MT":
                    estado = "Mato Grosso";
                    break;
                case "MS":
                    estado = "Mato Grosso do Sul";
                    break;
                case "MG":
                    estado = "Minas Gerais";
                    break;
                case "PA":
                    estado = "Pará";
                    break;
                case "PB":
                    estado = "Paraíba";
                    break;
                case "PR":
                    estado = "Paraná";
                    break;
                case "PE":
                    estado = "Pernambuco";
                    break;
                case "PI":
                    estado = "Piauí";
                    break;
                case "RJ":
                    estado = "Rio de Janeiro";
                    break;
                case "RN":
                    estado = "Rio Grande do Norte";
                    break;
                case "RS":
                    estado = "Rio Grande do Sul";
                    break;
                case "RO":
                    estado = "Rondônia";
                    break;
                case "RR":
                    estado = "Roraima";
                    break;
                case "SC":
                    estado = "Santa Catarina";
                    break;
                case "SP":
                    estado = "São Paulo";
                    break;
                case "SE":
                    estado = "Sergipe";
                    break;
                case "TO":
                    estado = "Tocantins";
                    break;
                case "DF":
                    estado = "Distrito Federal";
                    break;
            }
        return estado;
    }
    
}
