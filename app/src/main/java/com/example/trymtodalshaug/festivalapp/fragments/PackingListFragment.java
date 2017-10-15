package com.example.trymtodalshaug.festivalapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.trymtodalshaug.festivalapp.R;
import com.example.trymtodalshaug.festivalapp.adapter.PackingListItemAdapter;
import com.example.trymtodalshaug.festivalapp.data.DataSource;
import com.example.trymtodalshaug.festivalapp.model.PackingListItem;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PackingListFragment extends Fragment {

    private List<PackingListItem> packingListItems;
    private RecyclerView packingListRV;
    private Button buttonAddItem;
    private PackingListItemAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_packing_list, container, false);

        packingListRV = (RecyclerView) view.findViewById(R.id.recycler_view_packing_list);
        buttonAddItem = (Button) view.findViewById(R.id.button_add_item);

        // Inflate the layout for this fragment
        return view;
    }

    public static PackingListFragment newInstance() {
        return new PackingListFragment();
    }

    public void addItem(PackingListItem newItem) {
        packingListItems.add(newItem);
    }

    private void updateUI() {
        DataSource dataSource = new DataSource(getContext());

        packingListItems = dataSource.getPackingListItems();
        if (adapter == null) {
            adapter = new PackingListItemAdapter(getContext(), packingListItems);
            packingListRV.setAdapter(adapter);
        } else {
            adapter.updateList(packingListItems);
            adapter.notifyDataSetChanged();
        }
    }
}
