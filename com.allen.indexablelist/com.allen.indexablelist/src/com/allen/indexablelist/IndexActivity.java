package com.allen.indexablelist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @package��com.allen.indexablelist
 * @author��Allen
 * @email��jaylong1302@163.com
 * @data��2013-4-13 ����1:51:17
 * @description����ʵ�������Դ
 */
public class IndexActivity extends Activity {

	LinearLayout layoutIndex;
	/** ��ĸ������ */
	private String[] str = { "#", "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "U", "V", "W", "X", "Y",
			"Z" };

	int height;// ����߶�
	List<NoteBookItem> listData;
	private ListView listView;
	NoteBookadapter adapter;
	private TextView tv_show;// �м���ʾ������ı�

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		layoutIndex = (LinearLayout) this.findViewById(R.id.layout);
		layoutIndex.setBackgroundColor(Color.parseColor("#00ffffff"));

		getData();
		listView = (ListView) findViewById(R.id.listView1);
		adapter = new NoteBookadapter(this, listData, this.str);
		listView.setAdapter(adapter);

		tv_show = (TextView) findViewById(R.id.tv);
		tv_show.setVisibility(View.INVISIBLE);

	}

	public void getData() {
		listData = new ArrayList<NoteBookItem>();

		NoteBookItem n1 = new NoteBookItem();
		n1.call = "����";
		n1.name = "allen";
		n1.mobile = "18217594856";
		n1.index = String.valueOf(Pinyin4j.getHanyuPinyin(n1.name).charAt(0));		
		listData.add(n1);
		
		NoteBookItem n2 = new NoteBookItem();
		n2.call = "����ʦ";
		n2.name = "android";
		n2.mobile = "13658974521";
		n2.index = String.valueOf(Pinyin4j.getHanyuPinyin(n2.name).charAt(0));
		listData.add(n2);
		
		NoteBookItem n3 = new NoteBookItem();
		n3.call = "����";
		n3.name = "�ܿ�";
		n3.mobile = "13658974521";
		n3.index = String.valueOf(Pinyin4j.getHanyuPinyin(n3.name).charAt(0));
		listData.add(n3);
		
		NoteBookItem n4 = new NoteBookItem();
		n4.call = "��ʦ";
		n4.name = "��ǿ";
		n4.number = "021-25635784";
		n4.index = String.valueOf(Pinyin4j.getHanyuPinyin(n4.name).charAt(0));
		listData.add(n4);
		
		NoteBookItem n5 = new NoteBookItem();
		n5.call = "�ͷ�";
		n5.name = "����";
		n5.number = "010-25635784";
		n5.index = String.valueOf(Pinyin4j.getHanyuPinyin(n5.name).charAt(0));
		listData.add(n5);
		
		NoteBookItem n6 = new NoteBookItem();
		n6.call = "�ͷ�";
		n6.name = "bruth";
		n6.number = "010-25635784";
		n6.index = String.valueOf(Pinyin4j.getHanyuPinyin(n6.name).charAt(0));
		listData.add(n6);
		
		NoteBookItem n7 = new NoteBookItem();
		n7.call = "����";
		n7.name = "������";
		n7.number = "010-25635784";
		n7.index = String.valueOf(Pinyin4j.getHanyuPinyin(n7.name).charAt(0));
		listData.add(n7);
		
		NoteBookItem n8 = new NoteBookItem();
		n8.call = "�ͷ�";
		n8.name = "mary";
		n8.number = "010-25635784";
		n8.index = String.valueOf(Pinyin4j.getHanyuPinyin(n8.name).charAt(0));
		listData.add(n8);
		
		NoteBookItem n9 = new NoteBookItem();
		n9.call = "�ͷ�";
		n9.name = "����";
		n9.number = "010-25635784";
		n9.index = String.valueOf(Pinyin4j.getHanyuPinyin(n9.name).charAt(0));
		listData.add(n9);
		
		NoteBookItem n10 = new NoteBookItem();
		n10.call = "�ͷ�";
		n10.name = "����";
		n10.number = "010-25635784";
		n10.index = String.valueOf(Pinyin4j.getHanyuPinyin(n10.name).charAt(0));
		listData.add(n10);
		
		NoteBookItem n11 = new NoteBookItem();
		n11.call = "�ͷ�";
		n11.name = "����";
		n11.number = "010-25635784";
		n11.index = String.valueOf(Pinyin4j.getHanyuPinyin(n11.name).charAt(0));
		listData.add(n11);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// ��oncreate����ִ������Ĵ���û��Ӧ����Ϊoncreate����õ���getHeight=0
		System.out
				.println("layoutIndex.getHeight()=" + layoutIndex.getHeight());
		height = layoutIndex.getHeight() / str.length;
		getIndexView();
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
						if (adapter.getSelector().containsKey(key)) {
							int pos = adapter.getSelector().get(key);
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

}
