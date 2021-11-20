package com.example.covid19apps.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.example.covid19apps.Database.CovidData;
import com.example.covid19apps.HomeDetail.HomeDetailActivity;
import com.example.covid19apps.R;
import com.squareup.picasso.Picasso;

class HomeRecyclerViewAdapter extends ListAdapter<CovidData, HomeRecyclerViewAdapter.ViewHolder> {
    private final ItemClickableCallback itemClickableCallback;
    private Context context;
    protected HomeRecyclerViewAdapter(@NonNull ItemClickableCallback userClickableCallback) {
        super(new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<CovidData>() {
            @Override
            public boolean areItemsTheSame(@NonNull CovidData oldItem, @NonNull CovidData newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull CovidData oldItem, @NonNull CovidData newItem) {
                return oldItem.id == newItem.id
                        && oldItem.country.equals(newItem.country)
                        && oldItem.continent.equals(newItem.continent)
                        ;
            }
        }).build());
        this.itemClickableCallback = userClickableCallback;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.data_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCountry.setText(getItem(position).country);
        holder.tvContinent.setText(getItem(position).continent);
        Picasso.get().load(getItem(position).countryFlag).placeholder(R.drawable.flag_place_holder).error(R.drawable.flag_error_place_holder).into(holder.tvFlag);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvCountry;
        TextView tvContinent;
        ImageView tvFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry = itemView.findViewById(R.id.country);
            tvContinent= itemView.findViewById(R.id.continent);
            tvFlag = itemView.findViewById(R.id.flag);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                CovidData covidData = getItem(position);
                Intent intent = new Intent(context, HomeDetailActivity.class);
                intent.putExtra("selectedCountry",covidData.id);
                context.startActivity(intent);
//                itemClickableCallback.onClick(v, covidData);
            }
        }
    }
}
