package uk.co.pfc.mobileclinic.db;

import uk.co.pfc.mobileclinic.comp.PFCObject;
import android.content.Context;

import com.snappydb.DB;
import com.snappydb.SnappyDB;
import com.snappydb.SnappydbException;

public class DBUtil {

	private DB mDB = null;
	
	private Context mCtx;
	
	private String mDBPath;
	
	DBUtil(String db, Context ctx) {
		mDBPath = db;
		mCtx = ctx;
		
		open();
		close();
	}
	
	private void open(){
		try {
			mDB = new SnappyDB.Builder(mCtx)
				.name(mDBPath).build();
		} catch (SnappydbException e) {
			e.printStackTrace();
		}
	}
	
	public void save(PFCObject obj) {
		try {
			open();
			mDB.put(obj.getKey(), obj);
			close();
		} catch (SnappydbException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isExist(PFCObject obj) {
		try {
			open();
			boolean isExist = mDB.exists(obj.getKey());
			close();
			return isExist;
		} catch (SnappydbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void close(){
		try {
			mDB.close();
		} catch (SnappydbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isOpen(){
		try {
			return mDB.isOpen();
		} catch (SnappydbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
}
