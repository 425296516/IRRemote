package com.etek.remote;

import com.etek.irremote.R;
import com.etek.utils.RemoteApplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WarningPager extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.warning);
		Button back = (Button) findViewById(R.id.warning_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				RemoteApplication.getInstance().quitActivity();
				finish();
			}
		});
	}

}
