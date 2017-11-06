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
    private PackingListItemClickListener itemClickListener;

    public PackingListItemAdapter(Context context, List<PackingListItem> packingListItems,
                                  PackingListItemClickListener itemClickListener) {
        this.context = context;
        this.packingListItems = packingListItems;
        this.itemClickListener = itemClickListener;
    }

    // Listener interface for click and long click
    public interface PackingListItemClickListener {
        void packingListItemOnClick(PackingListItem item, int pos);
        void packingListItemOnLongClick(PackingListItem item, int pos);
    }

    // Returns the amount of items in the packing list
    @Override
    public int getItemCount() {
        return packingListItems.size();
    }

    // Returns a certain packing list item
    public PackingListItem getItem(int pos) {
        return packingListItems.get(pos);
    }

    // Returns the id of a certain packing list item
    @Override
    public long getItemId(int pos) {
        return packingListItems.get(pos).getId();
    }

    // Updates the list by replacing the old one
    // with a new one
    public void updateList(List<PackingListItem> newList) {
        packingListItems.clear();
        packingListItems.addAll(newList);
    }

    // Creates the view holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.packing_list_item, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final PackingListItem items = packingListItems.get(position);

        holder.populateRow(getItem(position));
        //holder.removeAt(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.packingListItemOnClick(items, position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemClickListener.packingListItemOnLongClick(items, position);
                return true;
            }
        });
    }
    

    /**
     * View Holder Class
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CheckBox checkBox;
        private TextView name;
        private PackingListItem selectedPackingListItem;

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

        public PackingListItem getSelectedPackingListItem() {
            selectedPackingListItem = getItem(getAdapterPosition());
            return selectedPackingListItem;
        }

        // Populates a row in the packing list
        public void populateRow(PackingListItem item) {
            boolean isChecked = item.getIsChecked() == 1;

            checkBox.setChecked(isChecked);
            name.setText(item.getName());
        }

        public void removeAt(int position) {
            packingListItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, packingListItems.size());
        }
    }
}
