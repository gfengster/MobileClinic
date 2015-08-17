package uk.co.pfc.mobileclinic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MobileClinic extends Activity {
	GridView grid;

	private final ContentButton[] contents = {
			new ContentButton(R.string.new_patient, R.drawable.sample_0, PatientActivity.class),
			new ContentButton(R.string.patient_search,R.drawable.sample_1, PatientActivity.class),
			new ContentButton(R.string.schedual, R.drawable.sample_2, PatientActivity.class),
			new ContentButton(R.string.about, R.drawable.sample_3, PatientActivity.class),
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mobile_clinic);

		CustomGrid adapter = new CustomGrid(MobileClinic.this, contents);
		grid = (GridView) findViewById(R.id.grid);
		grid.setAdapter(adapter);
	}

	private static class CustomGrid extends BaseAdapter {
		private Context mContext;
		private final ContentButton[] mContents;
		
		public CustomGrid(Context c, ContentButton[] contents ) {
			mContext = c;
			mContents = contents;
		}

		@Override
		public int getCount() {
			return mContents.length;
		}

		@Override
		public Object getItem(int position) {
			return mContents[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (convertView == null) {
				convertView = new View(mContext);
				convertView = inflater.inflate(R.layout.content_button, parent, false);
				ContentButton cb = mContents[position];
				TextView textView = (TextView) convertView
						.findViewById(R.id.grid_text);

				ImageView imageView = (ImageView) convertView
						.findViewById(R.id.grid_image);
				textView.setText(cb.textId);
				imageView.setImageResource(cb.imageId);
				
				convertView.setOnClickListener(cb);
				
				convertView.setTag(cb);
			} 
			
			return convertView;
		}
	}
	
	private static class ContentButton implements OnClickListener{
		int textId;
		int imageId;
		Class<?> activity;
		
		private ContentButton(int textId, int imageId, Class<?> activity){
			this.textId = textId;
			this.imageId = imageId;
			this.activity = activity;
		}
		
		@Override
		public void onClick(View v) {
			Context c = v.getContext();
			Intent intent = new Intent(c, activity);
			c.startActivity(intent);
			
//			Toast.makeText(c,
//					"You Clicked at " + v.getResources().getString(textId), 
//					Toast.LENGTH_SHORT)
//					.show();
		}
	}
	
}
