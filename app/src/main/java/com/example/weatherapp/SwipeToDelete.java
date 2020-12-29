package com.example.weatherapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.mylocation.LocationAdapter;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class SwipeToDelete extends ItemTouchHelper.SimpleCallback {

   private final LocationAdapter locationAdapter;

    private final Context context;


    public SwipeToDelete(LocationAdapter adapter, Context context) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        locationAdapter = adapter;
        ContextCompat.getDrawable(locationAdapter.getContext(),
                R.drawable.ic_delete);
        new ColorDrawable(Color.RED);
        this.context = context;
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        locationAdapter.deleteItem(position);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.design_default_color_error))
                .addSwipeLeftActionIcon(R.drawable.ic_delete)
                .addSwipeRightBackgroundColor(ContextCompat.getColor(context, R.color.design_default_color_error))
                .addSwipeRightActionIcon(R.drawable.ic_delete)
                .create()
                .decorate();

        super.onChildDraw(c, recyclerView, viewHolder, dX,dY,actionState, isCurrentlyActive);


    }
}
