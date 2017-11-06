package com.example.trymtodalshaug.festivalapp.fragments;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trymtodalshaug.festivalapp.R;
import com.example.trymtodalshaug.festivalapp.adapter.PackingListItemAdapter;
import com.example.trymtodalshaug.festivalapp.data.DataSource;
import com.example.trymtodalshaug.festivalapp.model.PackingListItem;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PackingListFragment extends Fragment implements PackingListItemAdapter.PackingListItemClickListener{

    // Components from xml
    private RecyclerView packingListRV;
    private Button buttonAddItem;
    private EditText input;
    private CheckBox checkBox;
    private TextView listItem;

    private List<PackingListItem> packingListItems;
    private PackingListItemAdapter adapter;
    private DataSource dataSource;
    private LinearLayoutManager layoutManager;
    private ItemTouchHelper itemTouchHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this view
        View view = inflater.inflate(R.layout.fragment_packing_list, container, false);

        // Define layout manager and data source.
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        dataSource = new DataSource(getContext());

        // Get packing list items from database
        packingListItems = dataSource.getPackingListItems();

        // Tie local variables to xml elements
        packingListRV = view.findViewById(R.id.recycler_view_packing_list);
        buttonAddItem = view.findViewById(R.id.button_add_item);
        input = view.findViewById(R.id.edit_text_new_item);
        checkBox = view.findViewById(R.id.packing_list_item_check_box);
        listItem = view.findViewById(R.id.packing_list_item);

        // Set layout manager and adapter for recycler view
        packingListRV.setLayoutManager(layoutManager);
        packingListRV.setAdapter(adapter);

        // Functionality for deleting items by swiping to either side
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(getContext(), "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(getContext(), "on Swiped ", Toast.LENGTH_SHORT).show();

                //Remove swiped item from list and notify the RecyclerView
                final int position = viewHolder.getAdapterPosition();
                deleteItem(adapter.getItem(position));
                adapter.notifyItemRemoved(position);
            }
        };

        itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(packingListRV);

        // Button listener for adding an item to the list
        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(input.getText().toString());

                // Empty text field
                input.setText("");
            }
        });

        // Disable button before text field is changed
        buttonAddItem.setEnabled(false);
        buttonAddItem.setAlpha(0.5f);

        // Enable/disable button
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

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

        return view;
    }

    // Returns a new instance of the PackingListFragment object
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

    public void updateItem(PackingListItem pli) {
        DataSource dataSource = new DataSource(getContext());
        dataSource.updateItem(pli);
        updateUI();
        Toast.makeText(getContext(), "Item updated", Toast.LENGTH_SHORT).show();
    }

    public void deleteItem(PackingListItem pli) {
        DataSource dataSource = new DataSource(getContext());
        dataSource.deleteItem(pli);
        updateUI();
        Toast.makeText(getContext(), "Item deleted", Toast.LENGTH_SHORT).show();
    }

    private void updateUI() {
        packingListItems = dataSource.getPackingListItems();

        if (adapter == null) {
            adapter = new PackingListItemAdapter(getContext(), packingListItems, this);
            packingListRV.setAdapter(adapter);
        } else {
            adapter.updateList(packingListItems);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void packingListItemOnClick(PackingListItem item, int pos) {
        System.out.println("Clicked on item at pos " + pos);
        if (item.getIsChecked() != 1) {
            item.setIsChecked(1);
        } else {
            item.setIsChecked(0);
        }
        updateItem(item);
        updateUI();
        //item.setPaintFlags(listItem.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public void packingListItemOnLongClick(PackingListItem item, int pos) {

    }
}
