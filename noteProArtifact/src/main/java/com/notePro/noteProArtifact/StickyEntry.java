package com.notePro.noteProArtifact;

import java.util.UUID;

public class StickyEntry {
//	public String save (long layer, String color, long x, long y, String text) {
//  serves the purpose of creation of json files
	
	private long layer;
	private String color;
	private long xCoord;
	private long yCoord;
	private String text;
	private String uuid;
	
	public StickyEntry(long layer, String color, long xCoord, long yCoord, String text, String uuid) {
		this.layer = layer;
		this.color = color;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.text = text;
		this.uuid = uuid;
		
	}
	public long getLayer() {
		return layer;
	}
	public void setLayer(long layer) {
		this.layer = layer;
	}
	public String getUUID() {
		return uuid;
	}
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public long getX() {
		return xCoord;
	}
	public void setX(long xCoord) {
		this.xCoord = xCoord;
	}
	public long getY() {
		return yCoord;
	}
	public void setY(long yCoord) {
		this.yCoord = yCoord;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
