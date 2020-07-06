package com.notePro.noteProArtifact;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NoteProController {

	@Value("${spring.application.name}")
	String applicationName;

	NoteProModel noteProModel = new NoteProModel();
	List<StickyEntry> stickyEntryList = new ArrayList<StickyEntry>();

	@GetMapping("/")
	@ResponseBody
	public String homePage(Model model) {
		return "home";
	}

	// http://localhost:8080/createSticky/{layer}/{color}/{xCoord}/{yCoord}/{text}
	@GetMapping("/createSticky/{layer}/{color}/{xCoord}/{yCoord}/{text}")
	@ResponseBody
	public String createSticky(@PathVariable int layer, @PathVariable String color, @PathVariable int xCoord,
			@PathVariable int yCoord, @PathVariable String text) {
		String uuid = UUID.randomUUID().toString();

		StickyEntry stick = new StickyEntry(layer, color, xCoord, yCoord, text, uuid);
		stickyEntryList.add(stick);
		return "successful with the UUID:" + uuid;

	}

	// http://localhost:8080/updateText/{stickNoteNumber}/{text}
	@GetMapping("/updateText/{uuid}/{text}")
	@ResponseBody
	public String updateText(@PathVariable String uuid, @PathVariable String text) {
		stickyEntryList = noteProModel.modelUpdateText(stickyEntryList, uuid, text);
		return "successful with text change: " + text + " || stickyNote uuid: " + uuid;

	}

	// http://localhost:8080/updatePosition/{stickNoteNumber}/{x}/{y}
	@GetMapping("/updatePosition/{uuid}/{xCoord}/{yCoord}")
	@ResponseBody
	public String updatePosition(@PathVariable String uuid, @PathVariable int xCoord, @PathVariable int yCoord) {
		stickyEntryList = noteProModel.modelUpdatePos(stickyEntryList, uuid, xCoord, yCoord);
		return "successfully repositioned to {" + xCoord + "," + yCoord + "} || stickyNote uuid: " + uuid;

	}

	@GetMapping("/delete/{uuid}")
	@ResponseBody
	public String deleteSticky(@PathVariable String uuid) {
		stickyEntryList = noteProModel.modelDeleteStick(stickyEntryList, uuid);
		return "successfully removed stickyNote uuid: " + uuid;

	}

	@GetMapping("/deleteAll")
	@ResponseBody
	public String deleteAll() {
		stickyEntryList = noteProModel.deleteAllSticky();
		System.out.println("+++++++++++++++++++++++");
		System.out.println("deleted All StickyNotes");
		System.out.println("+++++++++++++++++++++++");
		return "successfully removed all stickyNotes";

	}

	@GetMapping("/load")
	@ResponseBody
	public List<StickyEntry> load() {
		stickyEntryList = noteProModel.loadStickies();
		
		return stickyEntryList;

	}

	@GetMapping("/save")
	@ResponseBody
	public String save() {
		if (noteProModel.addToSaveFile(stickyEntryList).equals("successful")) {
			return "successfully saved all stickyNotes";
		} else {
			return "failed";
		}
	}

	//

}
