package com.allen.indexablelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @package£ºcom.allen.indexablelist
 * @author£ºAllen
 * @email£ºjaylong1302@163.com
 * @data£º2013-4-13 ÏÂÎç3:32:22
 * @description£ºThe class is for...
 */
public class ExampleActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example);
		findViewById(R.id.btn1).setOnClickListener(this);
		findViewById(R.id.btn2).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn1:
			startActivity(new Intent(this, MainActivity.class));
			break;
		case R.id.btn2:
			startActivity(new Intent(this, IndexActivity.class));
			break;
		}
	}

}
