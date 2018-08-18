package com.example.hp.assetsjson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class JsoneAdapter extends RecyclerView.Adapter<JsoneAdapter.MyViewHolder> {
    private Context context;
    private List<JsonModel> list;

    public JsoneAdapter(Context context, List<JsonModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public JsoneAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.json_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JsoneAdapter.MyViewHolder holder, int position) {
        holder.surname.setText(list.get(position).getUsername());
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, surname;

        MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            surname = itemView.findViewById(R.id.user_name);
        }
    }
}
