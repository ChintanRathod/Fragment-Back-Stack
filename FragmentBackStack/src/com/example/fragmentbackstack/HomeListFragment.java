package com.example.fragmentbackstack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HomeListFragment extends Fragment {

	View view;
	ListView listView;
	private ListAdapter adapter;
	private MyListFragmentListener listener;

	public interface MyListFragmentListener {
		public void onItemClickedListener(String valueClicked);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.home_fragment, null);

		initViews();

		return view;
	}

	public void registerForListener(MyListFragmentListener listener) {
		this.listener = listener;
	}

	private void initViews() {
		listView = (ListView) view.findViewById(R.id.listView1);

		adapter = listView.getAdapter();

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				
				if (listener != null) {
					listener.onItemClickedListener(adapter.getItem(position)
							.toString());
				} else {
					throw new IllegalArgumentException("Please Pass Listener");
				}
			}
		});
	}

}
