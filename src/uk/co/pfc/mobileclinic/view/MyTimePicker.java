package uk.co.pfc.mobileclinic.view;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class MyTimePicker extends TimePicker {
	private final static int TIME_PICKER_INTERVAL = 15;
	
	public MyTimePicker(Context context) {
		super(context);
		customise();
	}

	public MyTimePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		customise();
	}
	
	public MyTimePicker(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs,defStyle);
		customise();
	}
	
	private void customise(){
		
		setCurrentHour(9);
		
		try {
            Class<?> classForid = Class.forName("com.android.internal.R$id");
                        
            Field field = classForid.getField("minute");

            NumberPicker spinner = (NumberPicker)findViewById(field.getInt(null));
            spinner.setMinValue(0);
            spinner.setMaxValue((60 / TIME_PICKER_INTERVAL) - 1);
            List<String> displayedValues = new ArrayList<String>();
            for (int i = 0; i < 60; i += TIME_PICKER_INTERVAL) {
                displayedValues.add(String.format("%02d", i));
            }
            spinner.setDisplayedValues(displayedValues
                    .toArray(new String[0]));
            spinner.setValue(2);
                       
            field = classForid.getField("amPm");

            spinner = (NumberPicker)findViewById(field.getInt(null));
            spinner.setValue(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
