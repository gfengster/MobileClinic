package uk.co.pfc.mobileclinic.comp;

import java.util.ArrayList;
import java.util.Collection;


public class Appointment {
	public int id;
	public String aptDate;
	public String aptTime;
	public String comment;
	public int patient;
	public Collection<String> images = new ArrayList<String>();
}
