package uk.co.pfc.mobileclinic.comp;

import android.os.Parcel;
import android.os.Parcelable;

public class Patient implements Parcelable, PFCObject {

	public static final Parcelable.Creator<Patient> CREATOR 
						= new Parcelable.Creator<Patient>() {
		public Patient createFromParcel(Parcel in) {
			return new Patient(in);
		}

		public Patient[] newArray(int size) {
			return new Patient[size];
		}
	};

	private static final String PREFIX = "patient:";
	
	private String key = null;
	
	public String fname;
	public String sname;
	public String phone;
	public String mobile; 
	public String email;
	public String house; 
	public String street;
	public String area;
	public String postcode;
	public String comment;
	
	private Patient(Parcel in) {
		fname = in.readString();
		sname = in.readString();
		phone = in.readString();
		mobile = in.readString(); 
		email = in.readString();
		house = in.readString(); 
		street = in.readString();
		area = in.readString();
		postcode = in.readString();
		comment = in.readString();
	}
	
	public Patient(){
		
	}
	
	@Override
	public int describeContents() {
		
		return 0;
	}
	
	@Override
	public String getKey(){
		if (key == null)
			key = PREFIX.concat(sname).concat(":").concat(fname);
		return key;
	}
	
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(fname);
		out.writeString(sname);
		out.writeString(phone);
		out.writeString(mobile);
		out.writeString(email);
		out.writeString(house);
		out.writeString(street);
		out.writeString(area);
		out.writeString(postcode);
		out.writeString(comment);
	}
}
