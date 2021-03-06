package com.example.helptipsactivity;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class MainActivity extends Activity implements OnChildClickListener{

	private ExpandableListView mListView = null; 
    private ExpandAdapter mAdapter = null; 
    private List<List<Item>> mData = new ArrayList<List<Item>>(); //表示用户信息的列表
    
    //表示定义的四个分组
    private String[] mGroupArrays = new String[] {  
            "健康类Tips", 
            "生活类Tips",  
            "安全类Tips",
    }; 
    
    //每个分组下的子项
    private String[][] mGroupArraysChild = new String[][] {
    	{"心肺复苏的正确做法？", "过敏性休克的急救常识？","心绞痛或心肌梗死急救？","脑出血或脑梗塞急救？"}, 
    	{"规律作息的益处？","坚持锻炼的益处？","烫伤如何处理","流鼻血如何处理？","外伤出血的急救措施？"}, 
    	{"家用热水采暖炉的防护措施？","防盗安全常识？","液化气的使用注意事项？","灭火的注意事项？"},
    };
 
//    //针对每个子项的描述，类似于个性签名
//    private String[][] mDetail = new String[][] {  
//    		{"美丽的兴义", "漂亮的遵义"}, 
//    		{"下雨的珠海", "知名的中山"}, 
//        	{"热辣的长沙", "未知的娄底"},
//        	{"我真的是周杰伦", "我是奥巴马"}
//    }; 
// 
    //设置每个子项的图片
    private int[][] mImageIds = new int[][] { 
            { R.drawable.img01,    
              R.drawable.img02,
              R.drawable.img03,
              R.drawable.img04}, 
            { R.drawable.img05,   
              R.drawable.img06,
              R.drawable.img07,
              R.drawable.img08,
              R.drawable.img09}, 
            { R.drawable.img10, 
              R.drawable.img11,
              R.drawable.img12,
              R.drawable.img13},
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initData(); 
        mListView = new ExpandableListView(this); 
        mListView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT)); 
        setContentView(mListView); 
         
        //Adapter把前端和数据联系起来，并监听事件
        //mListView.setGroupIndicator(getResources().getDrawable(R.drawable.portrait)); 
        mAdapter = new ExpandAdapter(this, mData, mGroupArrays); 
        mListView.setAdapter(mAdapter); 
        mListView.setDescendantFocusability(ExpandableListView.FOCUS_AFTER_DESCENDANTS); 
        mListView.setOnChildClickListener(this); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//每个子项的监听，给出一个对话框的提示信息
	@Override
	public boolean onChildClick(ExpandableListView parent, View v, 
            int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		Item item = mAdapter.getChild(groupPosition, childPosition); 
        new AlertDialog.Builder(this) 
                .setTitle(item.getName()) 
//                .setMessage(item.getDetail()) 
                .setIcon(android.R.drawable.ic_menu_more) 
                .setNegativeButton("取消", 
                        new OnClickListener() { 
                            @Override 
                            public void onClick(DialogInterface dialog, 
                                    int which) { 
                                // TODO Auto-generated method stub 
                            } 
                        }).create().show(); 
        return true; 
	}
	
	//初始化mData的内容
	private void initData() {     
        for(int i = 0; i < mGroupArrays.length; i++)
        {
        	List<Item> list = new ArrayList<Item>(); 
        	for(int j = 0; j < 4; j++)
        	{
        		Item item = new Item(mImageIds[i][j], mGroupArraysChild[i][j]);
        		list.add(item);
        	}
        	mData.add(list);
        }
    } 
 


}
