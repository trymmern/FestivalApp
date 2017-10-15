package com.example.trymtodalshaug.festivalapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private EditText input;
    private PackingListItemAdapter adapter;
    private DataSource dataSource;
    private LinearLayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_packing_list, container, false);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        dataSource = new DataSource(getContext());

        packingListItems = dataSource.getPackingListItems();
        packingListRV = (RecyclerView) view.findViewById(R.id.recycler_view_packing_list);
        buttonAddItem = (Button) view.findViewById(R.id.button_add_item);
        input = (EditText) view.findViewById(R.id.edit_text_new_item);

        packingListRV.setLayoutManager(layoutManager);
        packingListRV.setAdapter(adapter);

        buttonAddItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addItem(input.getText().toString());
            }
        });

        buttonAddItem.setEnabled(false);
        buttonAddItem.setAlpha(0.5f);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    buttonAddItem.setEnabled(false);
                    buttonAddItem.setAlpha(0.5f);
                } else {
                    buttonAddItem.setEnabled(true);
                    buttonAddItem.setAlpha(1f);
                }
            }
        });

        updateUI();

        // Inflate the layout for this fragment
        return view;
    }

    public static PackingListFragment newInstance() {
        return new PackingListFragment();
    }

    public void addItem(String name) {
        DataSource dataSource = new DataSource(getContext());
        PackingListItem pli = new PackingListItem(name);

        dataSource.insertItem(pli);

        updateUI();

        Toast.makeText(getContext(), "Item added to list", Toast.LENGTH_SHORT).show();
    }

    private void updateUI() {
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
