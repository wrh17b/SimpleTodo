package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


//Responsible for taking the data at a certain position and putting it into a viewholder.
//Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{


    public interface OnClickListener{
        void OnItemClicked(int position);
    }



    List<String> items;     //member data
    OnClickListener ClickListener; // member data -- used for editing an item after click


    public ItemsAdapter(List<String> items, OnClickListener ClickListener) {       //Constructor
        this.items=items;
        this.ClickListener = ClickListener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use Layout inflator to inflate a view

        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        //wrap it inside a View Holder and return it
       View rowItemView =LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);




        return new ViewHolder(rowItemView);
    }


    //Responsible for binding data to a particular Viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Grab the item at the position
        String item = items.get(position);


        //Bind the item into the specified viewholder

        holder.bind(item); //.bind is a method we will create inside the viewholder class



    }



    //Tells the recycler view how many items are in the list.
    @Override
    public int getItemCount() {
        return items.size();
    }

    //Container to provide easy access to views that represent each row of the List

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;
        CheckBox cbItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItem = itemView.findViewById(R.id.tvRowItem);
            cbItem = itemView.findViewById(R.id.cbRowItem);

        }


        //Update the view inside the holder with this data
        public void bind(String item) {
            tvItem.setText(item);

            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickListener.OnItemClicked(getAdapterPosition());


                }
            });

        }
    }

}



