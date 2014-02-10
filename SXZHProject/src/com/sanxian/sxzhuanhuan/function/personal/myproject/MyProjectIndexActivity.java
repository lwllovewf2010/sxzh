package com.sanxian.sxzhuanhuan.function.personal.myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseActivity;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.TopDialogInfo;
import com.sanxian.sxzhuanhuan.dialog.TopRightOrLeftDialog;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;
import com.sanxian.sxzhuanhuan.function.personal.myproject.adapter.MyProjectIndexAdapter;
/**
 * 我的项目首页
 * @author joe
 *
 */
public class MyProjectIndexActivity extends BaseActivity {
    private LinearLayout project_left_layout,project_right_layout;
    private TextView project_left_tv,project_right_tv;
    private ListView project_list;
    private String[] rightdialog = new String[]{"全部","预热中的创意","进行中的项目","已成功的项目","已失败的项目"};
    private String[] liftdialog = new String[]{"全部","我发起","我参与"};
    private final int REQUEST_TOP_RIGHT = 15;
    private final int REQUEST_TOP_LEFT = 20;
    private TestAPI api = null;
    private Map<String,String> input = null;
    private final int PROJECT_LIST_REQUEST = 25;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myproject_index);
		initView();
		initListener();
		initData();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("我的项目");
		displayRight();
		project_left_layout = (LinearLayout)findViewById(R.id.project_left_layout);
		project_right_layout = (LinearLayout)findViewById(R.id.project_right_layout);
		project_left_tv = (TextView)findViewById(R.id.project_left_tv);
		project_right_tv = (TextView)findViewById(R.id.project_right_tv);
		project_list = (ListView)findViewById(R.id.project_list);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		button_left.setOnClickListener(this);
		project_left_layout.setOnClickListener(this);
		project_right_layout.setOnClickListener(this);
	}
 
	private void initData(){
		api = new TestAPI();
		input = new HashMap<String, String>();
		input.put("pmode", "1") ;
		input.put("pagesize", "10") ;
		api.getCPPData(input, this,PROJECT_LIST_REQUEST) ;
	}
	
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
		int flag = ((Integer) param[0]).intValue();
		switch (flag) {
		case PROJECT_LIST_REQUEST:
			if (param.length > 0 && param[1] != null
			&& param[1] instanceof String) {
		String jsondata = param[1].toString();
//		System.out.println(jsondata);
		List<ProjectInfo> projectInfos = (ArrayList<ProjectInfo>) JSONParser.getProjectInfo(jsondata);
		project_list.setAdapter( new MyProjectIndexAdapter(MyProjectIndexActivity.this, projectInfos)) ;
	}
			break;

		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.project_left_layout:
			showLeftDialog();
			break;
		case R.id.project_right_layout:
			showRightDialog();
			break;
		default:
			break;
		}
	}

	/**
	 * 右上角对话框
	 */
	private void showRightDialog() {
		TopDialogInfo dialogInfo = new TopDialogInfo(DialogConstant.TRIGHT,rightdialog);
		Intent intent = new Intent(this,TopRightOrLeftDialog.class);
		intent.putExtra("info", dialogInfo);
		startActivityForResult(intent,REQUEST_TOP_RIGHT);
	}
	
	/**
	 * 左上角对话框
	 */
	private void showLeftDialog() {
		TopDialogInfo dialogInfo = new TopDialogInfo(DialogConstant.TLEFT,liftdialog);
		Intent intent = new Intent(this,TopRightOrLeftDialog.class);
		intent.putExtra("info", dialogInfo);
		startActivityForResult(intent, REQUEST_TOP_LEFT);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_TOP_LEFT){
			if(resultCode!=DialogConstant.DIALOG_RETURN){
				if(resultCode == 0){
					project_left_tv.setText(liftdialog[0]);
				}else if(resultCode == 1){
					project_left_tv.setText(liftdialog[1]);
				}else if(resultCode == 2){
					project_left_tv.setText(liftdialog[2]);
				}
			}
			
		}else if(requestCode == REQUEST_TOP_RIGHT){
            if(resultCode!=DialogConstant.DIALOG_RETURN){
                if(resultCode == 0){
                    project_right_tv.setText(rightdialog[0]);	
				}else if(resultCode == 1){
					project_right_tv.setText(rightdialog[1]);	
				}else if(resultCode == 2){
					project_right_tv.setText(rightdialog[2]);	
				}else if(resultCode == 3){
					project_right_tv.setText(rightdialog[3]);
				}else if(resultCode == 4){
					project_right_tv.setText(rightdialog[4]);
				}
			}
		}
	}
}
