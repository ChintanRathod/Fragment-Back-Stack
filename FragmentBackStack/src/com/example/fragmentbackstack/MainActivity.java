package com.example.fragmentbackstack;

import java.util.Stack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.example.fragmentbackstack.HomeListFragment.MyListFragmentListener;

public class MainActivity extends FragmentActivity implements
		MyListFragmentListener {

	private Stack<Fragment> fragmentStack;
	private FragmentManager fragmentManager;
	private HomeListFragment homeListFragment;
	private ResultListFragment resultListFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fragmentStack = new Stack<Fragment>();

		homeListFragment = new HomeListFragment();
		homeListFragment.registerForListener(this);

		fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.add(R.id.container, homeListFragment);
		fragmentStack.push(homeListFragment);
		ft.commit();
	}

	@Override
	public void onItemClickedListener(String valueClicked) {
		Toast.makeText(this, valueClicked, Toast.LENGTH_LONG).show();

		FragmentTransaction ft = fragmentManager.beginTransaction();

		resultListFragment = new ResultListFragment();
		ft.add(R.id.container, resultListFragment);
		fragmentStack.lastElement().onPause();
		ft.hide(fragmentStack.lastElement());
		fragmentStack.push(resultListFragment);
		ft.commit();
	}

	@Override
	public void onBackPressed() {

		if (fragmentStack.size() == 2) {
			FragmentTransaction ft = fragmentManager.beginTransaction();
			fragmentStack.lastElement().onPause();
			ft.remove(fragmentStack.pop());
			fragmentStack.lastElement().onResume();
			ft.show(fragmentStack.lastElement());
			ft.commit();
		} else {
			super.onBackPressed();
		}
	}

}
