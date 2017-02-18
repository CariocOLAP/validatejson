package com.ufrj.br;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Record
{
    private String[] COLUMNS;

    private String[][] DATA;
   
    private ArrayList<Timestamp> datahora = new ArrayList<Timestamp>();
    private ArrayList<String> ordem = new ArrayList<String>();
    private ArrayList<String> linha = new ArrayList<String>();
    private ArrayList<Double> latitude = new ArrayList<Double>();
    private ArrayList<Double> longitude = new ArrayList<Double>();
    private ArrayList<Double> velocidade = new ArrayList<Double>();
    

    public String[] getCOLUMNS ()
    {
        return COLUMNS;
    }

    public void setCOLUMNS (String[] COLUMNS)
    {
        this.COLUMNS = COLUMNS;
    }

    public String[][] getDATA ()
    {
        return DATA;
    }

    public void setDATA (String[][] DATA)
    {
        this.DATA = DATA;
    }

    @Override
    public String toString()
    {
        return "[COLUMNS = "+COLUMNS+", DATA = "+DATA+"]";
    }    
    
    
    public void generateRealValues() throws ParseException{
		int i=0;
		for (int k=0; k< getDATA().length; k++){
			for (int j=0; j < getDATA()[k].length; j++){
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			    Date parsedDate = dateFormat.parse(getDATA()[k][0]);
			    datahora.add(new Timestamp(parsedDate.getTime()));
				ordem.add(getDATA()[k][1]);
				linha.add(getDATA()[k][2]);
				latitude.add(Double.parseDouble(getDATA()[k][3]));
				longitude.add(Double.parseDouble(getDATA()[k][4]));
				velocidade.add(Double.parseDouble(getDATA()[k][5]));
			}
		}
    }
    
    public void checkIfNumberOfValuesMatch() throws Exception{
    	if (datahora.size() != ordem.size() || datahora.size() != linha.size() || datahora.size() !=
    			latitude.size() || datahora.size() != longitude.size() || 
    			datahora.size() != velocidade.size()){
    		System.out.println("Tamanho datahora: "+datahora.size());
        	System.out.println("Tamanho ordem: "+ordem.size());
        	System.out.println("Tamanho linha: "+linha.size());
        	System.out.println("Tamanho latitude: "+latitude.size());
        	System.out.println("Tamanho longitude: "+longitude.size());
        	System.out.println("Tamanho velocidade: "+velocidade.size());
        	throw new Exception();
    	}    	
    }   
    
}