package com.example.covid19apps.Home.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19apps.Home.ResponseItem;
import com.example.covid19apps.R;

class CovidDataAdapter extends ListAdapter<ResponseItem, CovidDataAdapter.ViewHolder> {
    private final ItemClickableCallback itemClickableCallback;

    protected CovidDataAdapter(@NonNull ItemClickableCallback userClickableCallback) {
        super(new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<ResponseItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull ResponseItem oldItem, @NonNull ResponseItem newItem) {
                return oldItem.getCountryInfo().getId() == newItem.getCountryInfo().getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull ResponseItem oldItem, @NonNull ResponseItem newItem) {
                return oldItem.getCountryInfo().getId() == newItem.getCountryInfo().getId()
                        && oldItem.getCountry().equals(newItem.getCountry())
                        && oldItem.getContinent().equals(newItem.getContinent())
                        ;
            }
        }).build());
        this.itemClickableCallback = userClickableCallback;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.data_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCountry.setText(getItem(position).getCountry());
        holder.tvContinent.setText(getItem(position).getContinent());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvCountry;
        TextView tvContinent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry =itemView.findViewById(R.id.country);
            tvContinent =itemView.findViewById(R.id.continent);
        }
    }
}
