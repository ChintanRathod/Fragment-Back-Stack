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

public class ResultListFragment extends Fragment {

	View view;

	ListView listView;

	private ListAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.result_fragment, null);

		initViews();

		return view;
	}

	private void initViews() {
		listView = (ListView) view.findViewById(R.id.listView1);

		adapter = listView.getAdapter();

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Toast.makeText(getActivity(),
						adapter.getItem(position).toString(), Toast.LENGTH_LONG)
						.show();
			}
		});
	}
}
