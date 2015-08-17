package uk.co.pfc.mobileclinic.comp;

public class PostCode implements PFCObject {
	
	public static final String PREFIX = "postcode:";
	private String key = null;
	
	public String postcode;
	public String area;
	
	public PostCode(){
		
	}
	
	@Override
	public String getKey() {
		if (key == null)
			key = PREFIX.concat(postcode);
		
		return key;
	}

}
