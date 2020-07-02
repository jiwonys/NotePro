package com.notePro.noteProArtifact;

import java.util.UUID;

public class StickyEntry {
//	public String save (int layer, String color, int x, int y, String text) {
//  serves the purpose of creation of json files
	
	public int layer;
	public String color;
	public int xCoord;
	public int yCoord;
	public String text;
	public UUID uuid;
	
	public StickyEntry(int layer, String color, int xCoord, int yCoord, String text, UUID uuid) {
		this.layer = layer;
		this.color = color;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.text = text;
		this.uuid = uuid;
		
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	public UUID getUUID() {
		return uuid;
	}
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getX() {
		return xCoord;
	}
	public void setX(int xCoord) {
		this.xCoord = xCoord;
	}
	public int getY() {
		return yCoord;
	}
	public void setY(int yCoord) {
		this.yCoord = yCoord;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
