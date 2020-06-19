package com.notePro.noteProArtifact;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Autowired;

public class NoteProModel {
	
	private String path;
	private boolean append_to_file = false;
	public String load() {
		
		return "succesful";
	}
	
	public String save (int layer, String color, int x, int y, String text) {
		String output = layer + "," + color  + "," + x  + "," +  y  + "," +  text + "%%%";
		
		
		try {
			
			FileWriter fileWriter = new FileWriter("C:/Users/jiwon/Desktop/Desktop/eclipse-workspace/NotePro/noteProArtifact/src/main/resources/Map.txt", true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println(output);
			printWriter.close();
			
		}catch(IOException e) {
			return "unsuccessful, error while saving";
			
		}
		
		
		return "successful";
		
	}
}
