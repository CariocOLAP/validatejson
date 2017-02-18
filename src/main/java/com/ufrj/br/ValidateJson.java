package com.ufrj.br;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

public class ValidateJson {

	public static void main(String[] args) {
		new ValidateJson(args);
	}

	public ValidateJson(String[] args) {		
		if (args.length != 1 ){
			System.out.println("Usage validate_json FOLDER");
			return;
		}
		if (args[0].contains("//")){
			args[0] = args[0].split("//")[1];
		}
		
		if (!new File(args[0]).isDirectory()){
			System.out.println("Need to pass a valid folder as argument. "+args[0]+" not valid.");
			return;
		}
		
		String path = args[0];
		System.out.println("Starting validating .JSON in "+path+" ...");
		Gson gson = new Gson();		
		String[] extensions = new String[1];
		extensions[0] = "json";
		boolean isRecursive = true;
			
			
			Long countValidados = 0L;
			Long countRemovidos = 0L;
			Long countTotal = 0L;
			Long countStartTime = System.currentTimeMillis();
			Long countEndTime;
			Long totalTime;
			
			Iterator<File> iterateFiles = FileUtils.iterateFiles(new File(path), extensions, isRecursive);
			while (iterateFiles.hasNext()){
				countTotal++;
				File file = iterateFiles.next();
				if (file.length()==0){
					System.out.println(file.getName() + " removido por tamanho inválido");
					file.delete();
				}else{
					FileReader fr;
					try {
						fr = new FileReader(file);
						BufferedReader br = new BufferedReader(fr);
						Record r = gson.fromJson(br, Record.class);	
						r.generateRealValues();
						r.checkIfNumberOfValuesMatch();
						countValidados++;
					} catch  (Exception e){
						countRemovidos++;
						System.out.println(file.getName() + " removido, erro não conhecido");
						file.delete();
					}
					
				}

			}
			countEndTime = System.currentTimeMillis();
			totalTime = countEndTime - countStartTime;
			
			System.out.println("--- Total:     "+countTotal+" arquivos");
			System.out.println("--- Validados: "+countValidados+" arquivos");
			System.out.println("--- Removidos: "+countRemovidos+" arquivos");
			System.out.println("--- Tempo:     "+totalTime+"ms");
			System.out.println("Done.");		

	}
	
	
	public static void process(String path){
		String[] args = new String[1];
		args[0] = path;
		new ValidateJson(args);
	}

}
