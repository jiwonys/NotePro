package com.notePro.noteProArtifact;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.tomcat.jni.File;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NoteProModel {

	public List<StickyEntry> load() {
		JSONParser jsonParser = new JSONParser();
		JSONObject obj;
		ArrayList<JSONObject> json = new ArrayList<JSONObject>();
		String line = null;

		List<StickyEntry> loadedStickyEntryList = new ArrayList<StickyEntry>();

		try {

			FileReader reader = new FileReader("C:/Users/jiwon/Desktop/Desktop/eclipse-workspace/NotePro/noteProArtifact/src/main/resources/Map.json");
			BufferedReader bufferedReader = new BufferedReader(reader);
			while ((line = bufferedReader.readLine()) != null) {
				obj = (JSONObject) new JSONParser().parse(line);
				json.add(obj);
				System.out.println((String) obj.get("layer") + ":" + (String) obj.get("xCoord"));
			}

	        bufferedReader.close();         

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return loadedStickyEntryList;
	}

	public String addToSaveFile(List<StickyEntry> stickyEntryList) {

		for (StickyEntry entry : stickyEntryList) {
			String output = entry.getLayer() + "," + entry.getColor() + "," + entry.getX() + "," + entry.getY() + ","
					+ entry.getText();

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("layer", entry.getLayer());
			jsonObject.put("color", entry.getColor());
			jsonObject.put("xCoord", entry.getX());
			jsonObject.put("yCoord", entry.getY());
			jsonObject.put("text", entry.getText());
			try {
				FileWriter fileWriter = new FileWriter(
						"C:/Users/jiwon/Desktop/Desktop/eclipse-workspace/NotePro/noteProArtifact/src/main/resources/Map.json",
						true);
				fileWriter.write(jsonObject.toJSONString());

			} catch (IOException e) {
				return "unsuccessful, error while saving";

			}
		}

		return "successful";

	}

}
