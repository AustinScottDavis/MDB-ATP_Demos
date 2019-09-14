package com.example.demo2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* Every recycler view needs an adapter (you can reuse adapters!)
*
* The purpose of an adapter is literally bind our app-data to the views that are displayed within a RecyclerView object, this class will tell our recycler how to populate the "sub-views", view in each row, with our data
 */
public class Adapter extends RecyclerView.Adapter<Adapter.CustomViewHolder> {

    // It is helpful to have variable for context because `this` really only works when calling stuff from within Activities
    private Context context;
    List<Integer> data;

    // Adapter construtor, whenever we make a new adapter from this class we need to pass in a context and the data that we want to bind
    Adapter(Context context, ArrayList<Integer> data) {
        this.context = context;
        this.data = data;
    }


    // Pro-tip look-up @NonNull its a
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_view, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        customViewHolder.number.setText((data.get(i)).toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView number;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.number);
        }
    }
}
