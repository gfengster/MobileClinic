package uk.co.pfc.mobileclinic;

import java.util.logging.Logger;

import uk.co.pfc.mobileclinic.db.DBName;
import android.app.Application;

public class MCApplication extends Application {
	
	Logger logger = Logger.getLogger(MCApplication.class.getSimpleName());
	
	@Override
	public void onCreate(){
		super.onCreate();
				
		for(DBName d : DBName.values()) {
			d.create(this);
		}
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		
		for(DBName d : DBName.values()) {
			if (d.getDB().isOpen())
				d.getDB().close();
		}
	}
}
