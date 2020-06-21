package com.notePro.noteProArtifact;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NoteProModel {

	private String path;
	private boolean append_to_file = false;

	public String load() {
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(
				"C:/Users/jiwon/Desktop/Desktop/eclipse-workspace/NotePro/noteProArtifact/src/main/resources/Map.json")) {
			Object obj = jsonParser.parse(reader);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "unsuccessful";
		} catch (IOException e) {
			e.printStackTrace();
			return "unsuccessful";
		} catch (ParseException e) {
			e.printStackTrace();
			return "unsuccessful";
		}
		return "succesful";
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

	public List<StickyEntry> parseSticky(JSONObject sticky) {
		List<StickyEntry> parsedStickyList = new ArrayList<>();
		StickyEntry parsedSticky = new StickyEntry(0, "null", 0, 0, "null");

		return parsedStickyList;

	}
}
