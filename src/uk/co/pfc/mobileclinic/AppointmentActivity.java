package uk.co.pfc.mobileclinic;

import java.lang.reflect.Field;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class AppointmentActivity extends Activity {
	private static final Format sdf = new SimpleDateFormat("EEEE MMM d, yyyy", 
											Locale.getDefault());
	
	public Date mDate;
	
	EditText mDateBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointment);

		mDateBox = (EditText)findViewById(R.id.date);
		
		final Calendar c = Calendar.getInstance();
		setDisplayDate(c.getTime());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.appointment_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.save) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onDatePick(View view) {
		DialogFragment fragment = new SelectDateFragment();
		fragment.show(getFragmentManager(), "Date Picker");
	}
	
	public void onTimePick(View view) {
		DialogFragment fragment = 
				new SelectTimeFragment();
		fragment.show(getFragmentManager(), "Date Picker");
	}
	
	private void setDisplayDate(Date date) {
		mDateBox.setText(sdf.format(date));
	}

	private class SelectDateFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		public Dialog onCreateDialog(Bundle savedInstanceState) {

			System.out.println("entrering on create dialog");

			final Calendar c = Calendar.getInstance();
			mDate = c.getTime();

			final DatePickerDialog dialog = new DatePickerDialog(getActivity(),
					this, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DATE));

			DatePicker p = dialog.getDatePicker();

			p.setMinDate(c.getTimeInMillis() - 1000);

			c.add(Calendar.MONTH, c.get(Calendar.MONTH) + 6);
			p.setMaxDate(c.getTimeInMillis());

			return dialog;
		}

		@Override
		public void onDateSet(DatePicker view, int year, int month, int day) {
			final Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month);
			c.set(Calendar.DATE, day);

			mDate = c.getTime();

			setDisplayDate(c.getTime());
		}
	}

	private class SelectTimeFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {

		public Dialog onCreateDialog(Bundle savedInstanceState) {

			System.out.println("entrering on create dialog");

			final Calendar c = Calendar.getInstance();
			mDate = c.getTime();

			final TimePickerDialog dialog = new CustomTimePickerDialog(getActivity(),
					this, 0, 0, true);

			return dialog;
		}

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub

		}
	}
	
	private class CustomTimePickerDialog extends TimePickerDialog {

	    private final static int TIME_PICKER_INTERVAL = 30;
	    private TimePicker timePicker;
	    private final OnTimeSetListener callback;

	    public CustomTimePickerDialog(Context context, OnTimeSetListener callBack,
	            int hourOfDay, int minute, boolean is24HourView) {
	        super(context, callBack, hourOfDay, minute / TIME_PICKER_INTERVAL,
	                is24HourView);
	        this.callback = callBack;
	    }

	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        if (callback != null && timePicker != null) {
	            timePicker.clearFocus();
	            callback.onTimeSet(timePicker, timePicker.getCurrentHour(),
	                    timePicker.getCurrentMinute() * TIME_PICKER_INTERVAL);
	        }
	    }

	    @Override
	    protected void onStop() {
	    }

	    @Override
	    public void onAttachedToWindow() {
	        super.onAttachedToWindow();
	        try {
	            Class<?> classForid = Class.forName("com.android.internal.R$id");
	            Field timePickerField = classForid.getField("timePicker");
	            this.timePicker = (TimePicker) findViewById(timePickerField
	                    .getInt(null));
	            Field field = classForid.getField("minute");

	            NumberPicker mMinuteSpinner = (NumberPicker) timePicker
	                    .findViewById(field.getInt(null));
	            mMinuteSpinner.setMinValue(0);
	            mMinuteSpinner.setMaxValue((60 / TIME_PICKER_INTERVAL) - 1);
	            List<String> displayedValues = new ArrayList<String>();
	            for (int i = 0; i < 60; i += TIME_PICKER_INTERVAL) {
	                displayedValues.add(String.format("%02d", i));
	            }
	            mMinuteSpinner.setDisplayedValues(displayedValues
	                    .toArray(new String[0]));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	}

}
