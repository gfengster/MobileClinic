package uk.co.pfc.mobileclinic;

import uk.co.pfc.mobileclinic.comp.Patient;
import uk.co.pfc.mobileclinic.comp.PostCode;
import uk.co.pfc.mobileclinic.db.DBName;
import uk.co.pfc.mobileclinic.db.DBUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class PatientActivity extends Activity {

	private Patient p;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.patient_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		switch (id) {
			case R.id.save:
				savePatient();
				
				Toast.makeText(this, "You Clicked at " + item.getTitle(), Toast.LENGTH_SHORT)
						.show();
				return true;
			case R.id.appointment:
				Intent intent = new Intent(this, AppointmentActivity.class);
				intent.putExtra("patient", p);
				
				startActivity(intent);
				return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void savePatient(){
		 
		if (p == null)
			p = new Patient();
		
		p.fname = getUIValue(R.id.firstname);
		p.sname = getUIValue(R.id.surname);
		p.phone = getUIValue(R.id.phone);
		p.mobile = getUIValue(R.id.mobilenumber);
		p.email = getUIValue(R.id.email);
		p.house = getUIValue(R.id.house);
		p.street = getUIValue(R.id.street);
		p.area = getUIValue(R.id.area);
		p.postcode = getUIValue(R.id.postcode);
		p.comment = getUIValue(R.id.comment);
				
		DBUtil patientDB = DBName.PATIENT.getDB();
		
		if (patientDB.isExist(p)) {
			patientDB.save(p);
		} else
			patientDB.save(p);
		
		PostCode pc = new PostCode();
		pc.postcode = p.postcode;
		pc.area = p.area;
		
		DBUtil postcodeDB = DBName.POSTCODE.getDB();
		postcodeDB.save(pc);
	}
	
	private String getUIValue(int id) {
		return ((EditText)findViewById(id)).getEditableText().toString();
	}
}
