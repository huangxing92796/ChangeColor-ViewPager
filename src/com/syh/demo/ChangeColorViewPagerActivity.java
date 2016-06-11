package com.syh.demo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChangeColorViewPagerActivity extends Activity {
    /** Called when the activity is first created. */
	private static final int NUM = 4;
	
	private ViewPager viewPager;
	private DrawableChangeView darwableView;
	
	private View[] views = new View[NUM];
	private Drawable[] drawables = new Drawable[NUM];
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        darwableView = (DrawableChangeView) this.findViewById(R.id.drawableChangeView);
        
        getViews();
        viewPager.setAdapter(new pagerAdapter());
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				System.out.println("当前现实的view"+arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				//arg1 显示的view前一个view所占屏幕的比例
				//arg2 viewpager的总宽度
				System.out.println("arg0所占屏幕的比例"+arg1);
				darwableView.setPosition(arg0);
				darwableView.setDegree(arg1);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
        
        darwableView.setDrawables(drawables);
    }
    
    
    private void getViews() {
		for(int i=0; i<NUM; i++){
			RelativeLayout layout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.text, null);
			TextView text = (TextView) layout.findViewById(R.id.tvText);
			text.setText("view of "+i);
			text.setTextColor(Color.RED);
			text.setTextSize(24);
			views[i] = layout;
			
			if(i%2 == 0){
				drawables[i] = this.getResources().getDrawable(R.drawable.lx_bg);
			}else{
				drawables[i] = this.getResources().getDrawable(R.drawable.tk_bg);
			}
		}
	}


	private class pagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return views.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}


		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			System.out.println("destroyItem"+position);
			((ViewPager)container).removeView(views[position]);
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			// TODO Auto-generated method stub
			System.out.println(position);
			((ViewPager)container).addView(views[position]);
			System.out.println(((TextView)views[position].findViewById(R.id.tvText)).getText());
			return views[position];
		}
		
		
    	
		
    }
}