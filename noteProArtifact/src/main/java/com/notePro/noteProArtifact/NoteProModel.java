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
import org.springframework.beans.factory.annotation.Value;

public class NoteProModel {



	JSONArray stickyList = new JSONArray();

	public List<StickyEntry> loadStickies(String filePathName) throws IOException {
		List<StickyEntry> loadedStickyEntryList = new ArrayList<StickyEntry>();
		StickyEntry ent;

		JSONParser jsonParser = new JSONParser();
		File file1 = new File(filePathName);
		file1.createNewFile();
		System.out.println("created a new file for map");
		try (FileReader reader = new FileReader(file1)) {
			File file = new File(
					filePathName);
			if (file.length() == 0) {
				System.out.println("EMPTY list is loaded");
				return loadedStickyEntryList;
			} else {
				stickyList = (JSONArray) jsonParser.parse(reader);
			}

			System.out.println("============Loaded these===========");
			for (Object jsonObj : stickyList) {
				ent = parseStickyObject((JSONObject) jsonObj);
				loadedStickyEntryList.add(ent);
			System.out.println("---------------------------------");
			}
			System.out.println("=================================");


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

	}

	public String addToSaveFile(List<StickyEntry> stickyEntryList, String filePathName) {
		stickyList.clear();
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
					filePathName);

			fileWriter.write(stickyList.toJSONString());
			fileWriter.flush();

		} catch (IOException e) {
			return "unsuccessful, error while saving";

		}
		return "successful";

	}

	public List<StickyEntry> deleteAllSticky(String filePathName) {
		stickyList.clear();
		try {
			FileWriter fileWriter = new FileWriter(
					filePathName);

			fileWriter.write("");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<StickyEntry> newList = new ArrayList<StickyEntry>();
		return newList;

	}

	public List<StickyEntry> modelUpdateText(List<StickyEntry> stickyEntryList, String uuid, String text) {
		List<StickyEntry> stickList = new ArrayList<StickyEntry>();
		for (StickyEntry stick : stickyEntryList) {

			if (stick.getUUID().equals(uuid)) {
				stick.setText(text);
				System.out.println("Sticky has been update with text: " + text);
			}
			stickList.add(stick);

		}
		return stickList;
	}

	public List<StickyEntry> modelUpdatePos(List<StickyEntry> stickyEntryList, String uuid, int xCoord, int yCoord) {
		List<StickyEntry> stickList = new ArrayList<StickyEntry>();
		for (StickyEntry stick : stickyEntryList) {
			if (stick.getUUID().equals(uuid)) {
				stick.setX(xCoord);
				stick.setY(yCoord);
				System.out.println("Sticky has been update with pos: {" + xCoord + "," + yCoord + "}");

			}

			stickList.add(stick);
		}
		return stickList;
	}

	public List<StickyEntry> modelDeleteStick(List<StickyEntry> stickyEntryList, String uuid) {
		List<StickyEntry> stickList = new ArrayList<StickyEntry>();
		for (int i = 0; i < stickyEntryList.size(); i++) {
			if (stickyEntryList.get(i).getUUID().equals(uuid)) {
				stickyEntryList.remove(i);
			}
			stickList.add(stickyEntryList.get(i));
		}
		return stickList;
	}

}
