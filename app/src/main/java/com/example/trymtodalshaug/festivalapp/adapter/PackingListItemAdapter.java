package com.example.trymtodalshaug.festivalapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.trymtodalshaug.festivalapp.R;
import com.example.trymtodalshaug.festivalapp.model.PackingListItem;

import java.util.List;

/**
 * Created by Trym Todalshaug on 15/10/2017.
 */

public class PackingListItemAdapter extends RecyclerView.Adapter<PackingListItemAdapter.ViewHolder> {

    final Context context;
    private final List<PackingListItem> packingListItems;

    public PackingListItemAdapter(Context context, List<PackingListItem> packingListItems) {
        this.context = context;
        this.packingListItems = packingListItems;
    }

    @Override
    public int getItemCount() {
        return packingListItems.size();
    }

    private PackingListItem getItem(int pos) {
        return packingListItems.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return packingListItems.get(pos).getid();
    }

    public void updateList(List<PackingListItem> newList) {
        packingListItems.clear();
        packingListItems.addAll(newList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.packing_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populateRow(getItem(position));
    }

    /**
     * View Holder Class
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CheckBox checkBox;
        private TextView name;
        PackingListItem selectedPackingListItem;

        public ViewHolder(View view) {
            super(view);
            checkBox = (CheckBox) view.findViewById(R.id.packing_list_item_check_box);
            name = (TextView) view.findViewById(R.id.packing_list_item_name);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();

            selectedPackingListItem = getItem(getAdapterPosition());

            // Get the correct game based on which listitem got clicked, and put it as parameter in the intent
            intent.putExtra("selectedGame", selectedPackingListItem);

            // Open GameDetailsActivity
            context.startActivity(intent);
        }

        public void populateRow(PackingListItem item) {
            name.setText(item.getName());
        }
    }
}
