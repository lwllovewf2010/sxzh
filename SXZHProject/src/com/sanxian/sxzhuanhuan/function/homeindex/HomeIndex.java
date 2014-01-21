package com.sanxian.sxzhuanhuan.function.homeindex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sanxian.sxzhuanhuan.R;
import com.sanxian.sxzhuanhuan.SApplication;
import com.sanxian.sxzhuanhuan.api.JSONParser;
import com.sanxian.sxzhuanhuan.api.TestAPI;
import com.sanxian.sxzhuanhuan.common.BaseFragment;
import com.sanxian.sxzhuanhuan.common.UIHelper;
import com.sanxian.sxzhuanhuan.dialog.DialogConstant;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialog;
import com.sanxian.sxzhuanhuan.dialog.MiddleDialogInfo;
import com.sanxian.sxzhuanhuan.dialog.TopDialogInfo;
import com.sanxian.sxzhuanhuan.dialog.TopRightOrLeftDialog;
import com.sanxian.sxzhuanhuan.entity.Constant;
import com.sanxian.sxzhuanhuan.entity.CreativeInfo;
import com.sanxian.sxzhuanhuan.entity.InterfaceData.IImgs;
import com.sanxian.sxzhuanhuan.entity.ProductInfo;
import com.sanxian.sxzhuanhuan.entity.ProjectInfo;
import com.sanxian.sxzhuanhuan.function.homeindex.project.ProjectAdapter;
import com.sanxian.sxzhuanhuan.util.Util;

/**
 * @Title: HomeIndex.java
 * @Package com.sanxian.sxzhuanhuan.function.homeindex
 * @Description: 首页
 * @author zhangfl@sanxian.com
 * @date 2014-1-15 下午3:27:42
 * @version V1.0
 */
public class HomeIndex extends BaseFragment implements OnClickListener {

	private static int test = 1 ; 
	
	private static int SORTID = 0 ;
	private ImageView imageBut1, imageBut2, imageBut3;
	private List<ImageView> listImgs = null ;
	private String[] publishType = { "创意话题", "集资项目" };

	private Button btnPublish = null ;
	private EditText etSearch = null ;
	private ImageView ivSearch = null ;
	private Button btnCreative = null;
	private Button btnProject = null;
	private Button btnProduct = null;
	private ArrayList<CreativeInfo> creativeInfos = null ;
	private ArrayList<ProjectInfo> projectInfos = null ;
	private ArrayList<ProductInfo> productInfos = null ;
	private ListView lvSortDetail;
	private Context context;

	private TopDialogInfo dialogInfo = null ;
	
	private TestAPI api = null;
	private Map<String , String> input = null ;
	private List<IImgs> indexImgs = null ;
	
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = null ;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	
	public HomeIndex() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = SApplication.getInstance();
		System.out.println("---- onCreat------");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.home_index_zfl, container, false);
		init(view);
		
		System.out.println("---- onCreateView------");
		
		options = UIHelper.setOption() ;
		
		api.getIndexImgs(HomeIndex.this, Constant.REQUESTCODE.INDEX_IMGS_REQUEST) ;   //首页轮播图
		
		input.put("pmode", "2") ;
		input.put("pagesize", "5") ;
		api.getCPPData(input, HomeIndex.this, Constant.REQUESTCODE.CREATIVE_LIST_REQUEST) ;  //首页创意
		
		return view;
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);

		int flag = ((Integer) param[0]).intValue();
		//imgpath:http://192.168.1.9/mobileapi/3.jpg
		switch (flag) {
			case Constant.REQUESTCODE.INDEX_IMGS_REQUEST:
				if (param.length > 0 && param[1] != null
						&& param[1] instanceof String) {
					String jsondata = param[1].toString();
					System.out.println(jsondata);
					indexImgs = JSONParser.getIndexImgs(jsondata);
					
					//test
//					indexImgs = null ;
//					IImgs img = null ;
//					if(null == indexImgs ) {
//						indexImgs = new ArrayList<IImgs>() ;
//						for(int i = 1 ; i < 4 ; i++) {
//							img = new IImgs() ;
//							img.setImgpath("http://192.168.1.9/mobileapi/" + i + ".jpg") ;
//							indexImgs.add(img) ;
//						}
//					}
					
					for(int i = 0 ; i< 3 ; i++) {
						imageLoader.displayImage(indexImgs.get(i).getImgpath(), listImgs.get(i), options, animateFirstListener);
					}
					
				}
				break;
				
			case Constant.REQUESTCODE.CREATIVE_LIST_REQUEST :
				if (param.length > 0 && param[1] != null
						&& param[1] instanceof String) {
					String jsondata = param[1].toString();
//					System.out.println(jsondata);
					creativeInfos = (ArrayList<CreativeInfo>) JSONParser.getCreativeInfo(jsondata);
					lvSortDetail.setAdapter( new CreativeAdapter(getActivity(), creativeInfos)) ;
				}
				break ;
			case Constant.REQUESTCODE.PROJECT_LIST_REQUEST : 
				if (param.length > 0 && param[1] != null
						&& param[1] instanceof String) {
					String jsondata = param[1].toString();
//					System.out.println(jsondata);
					projectInfos = (ArrayList<ProjectInfo>) JSONParser.getProjectInfo(jsondata);
					
					lvSortDetail.setAdapter( new ProjectAdapter(getActivity(), projectInfos)) ;
				}
				break ;
			case Constant.REQUESTCODE.PRODUCT_LIST_REQUEST :
				if (param.length > 0 && param[1] != null
						&& param[1] instanceof String) {
					String jsondata = param[1].toString();
//					System.out.println(jsondata);
					productInfos = (ArrayList<ProductInfo>) JSONParser.getProductInfo(jsondata);
					
					lvSortDetail.setAdapter( new ProductAdapter(getActivity(), productInfos)) ;
				}
				break ;
				
		}

	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return super.getContext();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.home_index_title_right_btn:
				test++ ;
				if(1 == (test % 2)) {
					showRightDialog() ;
				} else {
					showMidDialog() ;
				}
				break;
			case R.id.home_index_title_search_img :
				Util.toastInfo(getActivity(), "" + etSearch.getText().toString()) ;
				UIHelper.showSortDetailActivity(getActivity(), Constant.Sort.SORTS_NAME[SORTID], etSearch.getText().toString()) ;
				break ;
		}

	}
	
	private void showRightDialog() {
		dialogInfo=new TopDialogInfo(DialogConstant.TRIGHT, publishType);
		Intent intent=new Intent(getActivity(),TopRightOrLeftDialog.class);
		intent.putExtra("info", dialogInfo);
		startActivityForResult(intent, DialogConstant.REQUEST_TOP);
	}
	
	private void showMidDialog() {
		Intent intent = new Intent(getActivity() , MiddleDialog.class);
		MiddleDialogInfo info = new MiddleDialogInfo("提示", "请您先进行实名认证再继续发布项目，以便于客服在您需要的时候提供及时的帮助。", 
				false, "", "", "立即去验证", "取消发布");
		intent.putExtra("info", info);
		startActivityForResult(intent, DialogConstant.REQUEST_MIDDLE);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==DialogConstant.REQUEST_TOP){
			if(resultCode!=DialogConstant.DIALOG_RETURN){
				System.out.println("resultCode=" + resultCode + "    " + dialogInfo.getMenu()[resultCode]); 
				
				Intent publishIntent = null ;
				if(resultCode == Constant.PUBLISH_TOPIC) {
					publishIntent = new Intent(getActivity(),	PublishTopic.class);
					getActivity().startActivity(publishIntent);
				} else if(resultCode == Constant.PUBLISH_ORIGINALITY ) {
					publishIntent = new Intent(getActivity(), PublishOriginality.class);
					getActivity().startActivity(publishIntent);
				}
			}
			
		} else if(resultCode == DialogConstant.MIDDLE_OK) {
			System.out.println("---------ok -------------");
			UIHelper.showRealAuthActivity(context) ;
		} else if(resultCode == DialogConstant.MIDDLE_CANCEL) {
			System.out.println("----cancle-----");
		} else if(resultCode == Constant.RESULT_LOGIN_CODE ) {
			System.out.println("-------login ok ---------"); 
		}
	}

	/**
	 * 初始化数据
	 */
	private void init(View view) {
		api = new TestAPI();
		input = new HashMap<String, String>();
		
		btnPublish = (Button) view.findViewById(R.id.home_index_title_right_btn) ;
		btnPublish.setOnClickListener(this) ;
		etSearch = (EditText) view.findViewById(R.id.home_index_title_search_text) ;
		ivSearch = (ImageView) view.findViewById(R.id.home_index_title_search_img) ;
		ivSearch.setOnClickListener(this) ;
		
		imageBut1 = (ImageView) view.findViewById(R.id.home_index_image1);
		imageBut2 = (ImageView) view.findViewById(R.id.home_index_image2);
		imageBut3 = (ImageView) view.findViewById(R.id.home_index_image3);
		
		listImgs = new ArrayList<ImageView>() ;
		listImgs.add(imageBut1) ;
		listImgs.add(imageBut2) ;
		listImgs.add(imageBut3) ;

		btnCreative = (Button) view
				.findViewById(R.id.home_index_content_btn_creative);
		btnProject = (Button) view
				.findViewById(R.id.home_index_content_btn_project);
		btnProduct = (Button) view
				.findViewById(R.id.home_index_content_btn_product);

		btnCreative.setOnClickListener(sortsBtnClick(btnCreative, 1));
		btnProject.setOnClickListener(sortsBtnClick(btnProject, 2));
		btnProduct.setOnClickListener(sortsBtnClick(btnProduct, 3));

		lvSortDetail = (ListView) view
				.findViewById(R.id.home_index_content_list);

		btnCreative.setEnabled(false);
		initData(Constant.Sort.SORT_CREATIVE);
	}

	private View.OnClickListener sortsBtnClick(final Button btn,
			final int catalog) {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(btn == btnCreative) {
					SORTID = 1 ;
					btnCreative.setEnabled(false) ;
					initData(Constant.Sort.SORT_CREATIVE) ;
				} else {
					btnCreative.setEnabled(true) ;
				}
				
				if(btn == btnProject) {
					SORTID = 2 ;
					btnProject.setEnabled(false) ;
					initData(Constant.Sort.SORT_PROJECT) ;
				} else {
					btnProject.setEnabled(true) ;
				}
				
				if(btn == btnProduct) {
					SORTID = 3 ;
					btnProduct.setEnabled(false) ;
					initData(Constant.Sort.SORT_PRODUCT) ;
				} else {
					btnProduct.setEnabled(true) ;
				}
			}
			
		} ;
	}

	/**
	 * 设置相关分类的数据信息
	 * @param flag  分类的类别ID
	 */
	private void initData(int flag) {
		switch (flag) {
			case Constant.Sort.SORT_CREATIVE:
				if(null == creativeInfos) {
//					creativeInfos = TestData.initCreative() ;
					input.put("pmode", "2") ;
					input.put("pagesize", "10") ;
					api.getCPPData(input, this, Constant.REQUESTCODE.CREATIVE_LIST_REQUEST) ;
				} else {
					lvSortDetail.setAdapter(new CreativeAdapter(getActivity(), creativeInfos)) ;
				}
				break;
			case Constant.Sort.SORT_PROJECT :
				if(null == projectInfos) {
//					projectInfos = TestData.initProject() ;
					input.put("pmode", "1") ;
					input.put("pagesize", "10") ;
					api.getCPPData(input, this, Constant.REQUESTCODE.PROJECT_LIST_REQUEST) ;
				} else {
					lvSortDetail.setAdapter(new ProjectAdapter(getActivity(), projectInfos) ) ;
				}
				break ;
			case Constant.Sort.SORT_PRODUCT :
				if(null == productInfos) {
//					productInfos = TestData.initProduct() ;
					input.put("pmode", "4") ;
					input.put("pagesize", "5") ;
					api.getCPPData(input, this, Constant.REQUESTCODE.PRODUCT_LIST_REQUEST) ;
				} else {
					lvSortDetail.setAdapter( new ProductAdapter(getActivity(), productInfos)) ;
				}
				break ;
			default:
				break;
		}
	}
	
	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("----HomeIndex   onDestroy ------");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("---HomeIndex    -onPause ------");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("----HomeIndex     onStart ------");
	}
	
	
	
}
