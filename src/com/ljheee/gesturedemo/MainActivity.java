package com.ljheee.gesturedemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView textView;
	GestureDetector detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.textView);

		detector = new GestureDetector(this, new MyGestureHandler());
	}

	/**
	 * Antivity中的回调方法 监听TouchEvent
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return detector.onTouchEvent(event);
	}

	/**
	 * 创建选项菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 选项菜单--点击事件处理
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_exit:
			finish();
			break;
		case R.id.action_settings:
			Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 手势识别监听 具体手势识别交给GestureDetector完成，对应的事件处理调用一下方法
	 * 
	 * @author ljheee
	 * 
	 */
	class MyGestureHandler implements OnGestureListener {

		final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;

		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}

		/**
		 * 手势滑动（飞速滑动）
		 */
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
					&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
				// Fling left
				Toast.makeText(MainActivity.this, "Fling Left",
						Toast.LENGTH_SHORT).show();
			} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
					&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
				// Fling right
				Toast.makeText(MainActivity.this, "Fling Right",
						Toast.LENGTH_SHORT).show();
				
				//手势侧滑：向右滑动时，启动新Activity
				Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
				startActivity(intent);
			}

			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {

		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}
	}

}
