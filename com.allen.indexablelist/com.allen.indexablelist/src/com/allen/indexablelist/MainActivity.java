package com.allen.indexablelist;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

/** ��������listview --����ԴList<String>*/
public class MainActivity extends Activity {

	LinearLayout layoutIndex;
	private String[] str = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "U", "V", "W", "X", "Y", "Z" };

	int height;// ����߶�
	private String data[] = { "android", "java", "news", "baidu", "oberser",
			"mary", "next", "ruby", "money", "lucy", "very", "thunder",
			"object", "lily", "jay", "answer", "layout", "demos", "com",
			"collect", "custom", "blog", "round", "redirect", "ground", "gray",
			"blue", "zone", "james", "zhang", "location" };
	String nData[];// ����Դ��������������ĸ
	private ListView listView;
	MyAdapter adapter;
	private TextView tv_show;// �м���ʾ������ı�

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		layoutIndex = (LinearLayout) this.findViewById(R.id.layout);
		layoutIndex.setBackgroundColor(Color.parseColor("#00ffffff"));
		// height =
		// this.getWindowManager().getDefaultDisplay().getHeight()//��ȡ��Ļ�ĸ߶�Ȼ�����������ĸ�ĳ���
		// / str.length;
		sortIndex();
		listView = (ListView) findViewById(R.id.listView1);
		adapter = new MyAdapter(this);
		listView.setAdapter(adapter);

		tv_show = (TextView) findViewById(R.id.tv);
		tv_show.setVisibility(View.INVISIBLE);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// ��oncreate����ִ������Ĵ���û��Ӧ����Ϊoncreate����õ���getHeight=0
		System.out
				.println("layoutIndex.getHeight()=" + layoutIndex.getHeight());
		height = layoutIndex.getHeight() / str.length;
		getIndexView();
	}

	/** ��ȡ������������ */
	public void sortIndex() {
		TreeSet<String> set = new TreeSet<String>();
		// ��ȡ��ʼ������Դ�е�����ĸ����ӵ�set��
		for (String string : data) {
			set.add(String.valueOf(string.charAt(0)));
		}
		// ������ĳ���Ϊԭ���ݼ���set�Ĵ�С
		nData = new String[data.length + set.size()];
		int i = 0;
		for (String string : set) {
			nData[i] = string;
			i++;
		}
		// ��ԭ���ݿ�������������
		System.arraycopy(data, 0, nData, set.size(), data.length);
		Arrays.sort(nData, String.CASE_INSENSITIVE_ORDER);// �Զ���������ĸ����
	}

	/** ���������б� */
	public void getIndexView() {
		LinearLayout.LayoutParams params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, height);
		// params.setMargins(10, 5, 10, 0);
		for (int i = 0; i < str.length; i++) {
			final TextView tv = new TextView(this);
			tv.setLayoutParams(params);
			tv.setText(str[i]);
			// tv.setTextColor(Color.parseColor("#606060"));
			// tv.setTextSize(16);
			tv.setPadding(10, 0, 10, 0);
			layoutIndex.addView(tv);
			layoutIndex.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event)

				{
					float y = event.getY();
					int index = (int) (y / height);
					if (index > -1 && index < str.length) {// ��ֹԽ��
						String key = str[index];
						if (selector.containsKey(key)) {
							int pos = selector.get(key);
							if (listView.getHeaderViewsCount() > 0) {// ��ֹListView�б�������������û�С�
								listView.setSelectionFromTop(
										pos + listView.getHeaderViewsCount(), 0);
							} else {
								listView.setSelectionFromTop(pos, 0);// ��������һ��
							}
							tv_show.setVisibility(View.VISIBLE);
							tv_show.setText(str[index]);
						}
					}
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						layoutIndex.setBackgroundColor(Color
								.parseColor("#606060"));
						break;

					case MotionEvent.ACTION_MOVE:

						break;
					case MotionEvent.ACTION_UP:
						layoutIndex.setBackgroundColor(Color
								.parseColor("#00ffffff"));
						tv_show.setVisibility(View.INVISIBLE);
						break;
					}
					return true;
				}
			});
		}
	}

	private HashMap<String, Integer> selector;// ��ź���������ĸ��λ��

	/** ������ */
	private class MyAdapter extends BaseAdapter {

		Holder holder;
		Context context;

		public MyAdapter(Context context) {
			this.context = context;
			selector = new HashMap<String, Integer>();
			for (int j = 0; j < str.length; j++) {// ѭ����ĸ���ҳ�nData�ж�Ӧ��ĸ��λ��
				for (int i = 0; i < nData.length; i++) {
					if (nData[i].equals(str[j].toLowerCase())) {
						selector.put(str[j], i);
					}
				}

			}
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return nData.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return nData[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isEnabled(int position) {
			// TODO Auto-generated method stub
			if (nData[position].length() == 1)// �������ĸ����
				return false;// ��ʾ���ܵ��
			return super.isEnabled(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			String item = nData[position];
			// TODO Auto-generated method stub

			if (item.length() == 1)
				convertView = getLayoutInflater().inflate(R.layout.index, null);
			else
				convertView = getLayoutInflater().inflate(R.layout.item, null);
			TextView tv = (TextView) convertView.findViewById(R.id.textView1);

			tv.setText(item);
			return convertView;
		}

		class Holder {
			TextView tv;
		}
	}
}
