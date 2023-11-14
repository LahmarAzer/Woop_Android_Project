package com.esprit.lastwooptravelproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {
    private Context context;
    private List<Trip> wishlistItems;

    public WishlistAdapter(Context context, List<Trip> wishlistItems) {
        this.context = context;
        this.wishlistItems = wishlistItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wishlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trip trip = wishlistItems.get(position);

        // Bind trip data to views in the wishlist_item layout
        holder.titleTextView.setText(trip.getTitre());
        holder.locationTextView.setText(trip.getLocation());

        // Set click listener for removing the trip from the wishlist
        holder.removeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement logic to remove the trip from the wishlist
                // You can use dbHelper or any other method to remove it from storage
                removeItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wishlistItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView locationTextView;
        public ImageView removeIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.wishlistItemTitle);
            locationTextView = itemView.findViewById(R.id.wishlistItemLocation);
            removeIcon = itemView.findViewById(R.id.wishlistRemoveIcon);
        }
    }

    // Method to remove an item from the wishlist
    private void removeItem(int position) {
        // Implement logic to remove the item at the given position
        // You can use dbHelper or any other method to remove it from storage

        wishlistItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, wishlistItems.size());
    }
}
