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

/** 带索引的listview --数据源List<String>*/
public class MainActivity extends Activity {

	LinearLayout layoutIndex;
	private String[] str = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "U", "V", "W", "X", "Y", "Z" };

	int height;// 字体高度
	private String data[] = { "android", "java", "news", "baidu", "oberser",
			"mary", "next", "ruby", "money", "lucy", "very", "thunder",
			"object", "lily", "jay", "answer", "layout", "demos", "com",
			"collect", "custom", "blog", "round", "redirect", "ground", "gray",
			"blue", "zone", "james", "zhang", "location" };
	String nData[];// 数据源，整合了索引字母
	private ListView listView;
	MyAdapter adapter;
	private TextView tv_show;// 中间显示标题的文本

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		layoutIndex = (LinearLayout) this.findViewById(R.id.layout);
		layoutIndex.setBackgroundColor(Color.parseColor("#00ffffff"));
		// height =
		// this.getWindowManager().getDefaultDisplay().getHeight()//获取屏幕的高度然后除以索引字母的长度
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
		// 在oncreate里面执行下面的代码没反应，因为oncreate里面得到的getHeight=0
		System.out
				.println("layoutIndex.getHeight()=" + layoutIndex.getHeight());
		height = layoutIndex.getHeight() / str.length;
		getIndexView();
	}

	/** 获取排序后的新数据 */
	public void sortIndex() {
		TreeSet<String> set = new TreeSet<String>();
		// 获取初始化数据源中的首字母，添加到set中
		for (String string : data) {
			set.add(String.valueOf(string.charAt(0)));
		}
		// 新数组的长度为原数据加上set的大小
		nData = new String[data.length + set.size()];
		int i = 0;
		for (String string : set) {
			nData[i] = string;
			i++;
		}
		// 将原数据拷贝到新数据中
		System.arraycopy(data, 0, nData, set.size(), data.length);
		Arrays.sort(nData, String.CASE_INSENSITIVE_ORDER);// 自动按照首字母排序
	}

	/** 绘制索引列表 */
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
					if (index > -1 && index < str.length) {// 防止越界
						String key = str[index];
						if (selector.containsKey(key)) {
							int pos = selector.get(key);
							if (listView.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。
								listView.setSelectionFromTop(
										pos + listView.getHeaderViewsCount(), 0);
							} else {
								listView.setSelectionFromTop(pos, 0);// 滑动到第一项
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

	private HashMap<String, Integer> selector;// 存放含有索引字母的位置

	/** 适配器 */
	private class MyAdapter extends BaseAdapter {

		Holder holder;
		Context context;

		public MyAdapter(Context context) {
			this.context = context;
			selector = new HashMap<String, Integer>();
			for (int j = 0; j < str.length; j++) {// 循环字母表，找出nData中对应字母的位置
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
			if (nData[position].length() == 1)// 如果是字母索引
				return false;// 表示不能点击
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
