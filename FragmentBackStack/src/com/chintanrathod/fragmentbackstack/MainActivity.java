/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chintanrathod.fragmentbackstack;

import java.util.Stack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.chintanrathod.fragmentbackstack.HomeListFragment.MyListFragmentListener;

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
