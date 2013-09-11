package org.flossk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Day
{
	
	private String title;
	private String timeStart;
	private String timeEnd;
	private String speaker;
	private String description;
	private String type;
	
	
	public Day(String tit, String tS,String tE,String sp,String de, String ty)
	{
		setTitle(tit);
		setTimeStart(tS);
		setTimeEnd(tE);
		setSpeaker(sp);
		setDescription(de);
		setType(ty);
	}
	
	// Getters and Setters for class variables

	
	public String getTitlef() 
	{
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getSpeaker() {
		return speaker;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
