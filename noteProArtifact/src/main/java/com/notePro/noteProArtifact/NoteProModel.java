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
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NoteProModel {

	boolean debug = false;
	
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
		File file = new File("C:/Users/jiwon/Desktop/Desktop/eclipse-workspace/NotePro/noteProArtifact/src/main/resources/Map.json");
		file.delete();
		
		for (StickyEntry entry : stickyEntryList) {
			String output = entry.getLayer() + "," + entry.getColor() + "," + entry.getX() + "," + entry.getY() + ","
					+ entry.getText();

			JSONObject StickyDetail = new JSONObject();
			StickyDetail.put("layer", entry.getLayer());
			StickyDetail.put("color", entry.getColor());
			StickyDetail.put("xCoord", entry.getX());
			StickyDetail.put("yCoord", entry.getY());
			StickyDetail.put("text", entry.getText());
			StickyDetail.put("UUID", entry.getUUID());
			JSONObject sticky = new JSONObject();
			sticky.put("Sticky", StickyDetail);
			JSONArray stickyList = new JSONArray();
			stickyList.add(sticky);
			
			try {
				FileWriter fileWriter = new FileWriter(
						"C:/Users/jiwon/Desktop/Desktop/eclipse-workspace/NotePro/noteProArtifact/src/main/resources/Map.json",
						true);
				fileWriter.write(stickyList.toJSONString());
				if(debug)System.out.println("Successful");
				fileWriter.close();

			} catch (IOException e) {
				return "unsuccessful, error while saving";

			}
		}

		return "successful";

	}

}
