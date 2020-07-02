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
	List stickyEntryList = new ArrayList<StickyEntry>();
	
	@GetMapping("/")
	@ResponseBody
	public String homePage(Model model) {
		return "home";
	}

	// flag will be replaced with error checks in the future

	@GetMapping("/load")
	@ResponseBody
	public String load() {
		boolean flag = true;
		noteProModel.load();
		if (flag) {
			return "successfully loaded";
		} else {
			return "failed";
		}
	}

	@GetMapping("/createSticky/{layer}/{color}/{xCoord}/{yCoord}/{text}")
	@ResponseBody
	public String createSticky(@PathVariable int layer, @PathVariable String color, @PathVariable int xCoord,
			@PathVariable int yCoord, @PathVariable String text) {
		UUID uuid = UUID.randomUUID();
		StickyEntry stick = new StickyEntry(layer, color, xCoord, yCoord, text, uuid);
		stickyEntryList.add(stick);
		// create a number after loading from text file
		boolean flag = true;
			return "successful with the UUID:" + uuid;
		
	}

	// http://localhost:8080/updateText/{stickNoteNumber}/{text}
	@GetMapping("/updateText/{stickyNoteNum}/{text}")
	@ResponseBody
	public String updateText(@PathVariable int stickyNoteNum, @PathVariable String text) {
		boolean flag = true;
		if (flag) {
			return "successful with text: " + text + " || stickyNote number: " + stickyNoteNum;
		} else {
			return "failed";
		}
	}

	// http://localhost:8080/updatePosition/{stickNoteNumber}/{x}/{y}
	@GetMapping("/updatePosition/{stickyNoteNum}/{x}/{y}")
	@ResponseBody
	public String updatePosition(@PathVariable int stickyNoteNum, @PathVariable int x, @PathVariable int y) {
		boolean flag = true;
		if (flag) {
			return "successfully repositioned to {" + x + "," + y + "} || stickyNote number: " + stickyNoteNum;
		} else {
			return "failed";
		}
	}

	@GetMapping("/delete/{stickyNoteNum}")
	@ResponseBody
	public String deleteSticky(@PathVariable int stickyNoteNum) {
		boolean flag = true;
		if (flag) {
			return "successfully removed stickyNote number: " + stickyNoteNum;
		} else {
			return "failed";
		}
	}

	@GetMapping("/deleteAll")
	@ResponseBody
	public String deleteAll() {
		boolean flag = true;
		if (flag) {
			return "successfully removed all stickyNotes";
		} else {
			return "failed";
		}
	}
	
	@GetMapping("/save")
	@ResponseBody
	public String save() {
		boolean flag = true;
		if (noteProModel.addToSaveFile(stickyEntryList).equals("successful")) {
			return "successfully saved all stickyNotes";
		} else {
			return "failed";
		}
	}
	
	//

}
