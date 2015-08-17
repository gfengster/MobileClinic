package uk.co.pfc.mobileclinic.db;

import android.content.Context;

public enum DBName {
	PATIENT("patient"),
	APPOINTMENT("appointment"),
	RECORD("record"),
	POSTCODE("postcode");
	
	private String mName;
	private DBUtil mDB = null;
	private DBName(String name) {
		mName = name;
	}
	
	public void create(Context ctx) {
		if (mDB == null) {
			mDB = new DBUtil(mName, ctx);
		}
	}
	
	public DBUtil getDB(){
		return mDB;
	}
	
	@Override
	public String toString(){
		return mName;
	}
}
