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
	JSONArray stickyList = new JSONArray();
	File file = new File(
			"C:/Users/jiwon/Desktop/Desktop/eclipse-workspace/NotePro/noteProArtifact/src/main/resources/Map.json");
	
	public List<StickyEntry> loadStickies() {
		List<StickyEntry> loadedStickyEntryList = new ArrayList<StickyEntry>();
		StickyEntry ent;

		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(
				"C:/Users/jiwon/Desktop/Desktop/eclipse-workspace/NotePro/noteProArtifact/src/main/resources/Map.json")) {
			stickyList = (JSONArray) jsonParser.parse(reader);

			for (Object jsonObj : stickyList) {
				ent = parseStickyObject((JSONObject) jsonObj);
				loadedStickyEntryList.add(ent);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("=============================");
		stickyList.clear();
		System.out.println("StickyList has been cleared");
		if(file.delete())
		System.out.println("original save file deleted");
		System.out.println("=============================");
		
		return loadedStickyEntryList;
	}

	private StickyEntry parseStickyObject(JSONObject sticky) {
		JSONObject stickyObject = (JSONObject) sticky.get("Sticky");

		long layer = ((long) stickyObject.get("layer"));
		String color = (String) stickyObject.get("color");
		long xCoord = (long) stickyObject.get("xCoord");
		long yCoord = (long) stickyObject.get("yCoord");
		String text = (String) stickyObject.get("text");
		String uuid = (String) stickyObject.get("UUID");
		StickyEntry entry = new StickyEntry(layer, color, xCoord, yCoord, text, uuid);
		System.out.println("layer:" + layer);
		System.out.println("color:" + color);
		System.out.println("xCoord:" + xCoord);
		System.out.println("yCoord:" + yCoord);
		System.out.println("text:" + text);
		System.out.println("UUID:" + uuid);

		return entry;

		/// createSticky/{layer}/{color}/{xCoord}/{yCoord}/{text}
	}

	public String addToSaveFile(List<StickyEntry> stickyEntryList) {
		

		for (StickyEntry entry : stickyEntryList) {
			JSONObject StickyDetail = new JSONObject();
			StickyDetail.put("layer", entry.getLayer());
			StickyDetail.put("color", entry.getColor());
			StickyDetail.put("xCoord", entry.getX());
			StickyDetail.put("yCoord", entry.getY());
			StickyDetail.put("text", entry.getText());
			StickyDetail.put("UUID", entry.getUUID());
			JSONObject sticky = new JSONObject();
			sticky.put("Sticky", StickyDetail);
			stickyList.add(sticky);

		}
		try {
			FileWriter fileWriter = new FileWriter(
					"C:/Users/jiwon/Desktop/Desktop/eclipse-workspace/NotePro/noteProArtifact/src/main/resources/Map.json",
					true);

			fileWriter.write(stickyList.toJSONString());
			fileWriter.flush();

		} catch (IOException e) {
			return "unsuccessful, error while saving";

		}
		stickyList.clear();
		return "successful";
		

	}

}
