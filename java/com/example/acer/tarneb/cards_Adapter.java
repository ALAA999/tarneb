package com.example.acer.tarneb;

/**
 * Created by acer on 3/29/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by acer on 3/12/2018.
 */

public class cards_Adapter extends RecyclerView.Adapter<cards_Adapter.ViewHolder2> {
    Context context;
    List<card> list;
    boolean[] cards_enabled;

    public cards_Adapter(List<card> list, Context context) {
        this.context = context;
        this.list = list;
    }

    public cards_Adapter(List<card> list, Context context, boolean[] cards_enabled) {
        this.context = context;
        this.list = list;
        this.cards_enabled = cards_enabled;
    }

    @Override
    public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new ViewHolder2(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder2 holder, final int position) {
        final card i = list.get(position);
        holder.setIsRecyclable(false);
        holder.imageView.setImageResource(i.getIcon());
        if (cards_enabled == null){
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dak_fragment.Is_AllowedToPlay) {
                        dak_fragment.card_selected_card = i;
                        dak_fragment.card_selected.setImageResource(i.getIcon());
                    }
                }
            });
        } else if (cards_enabled != null && !cards_enabled[position]) {
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dak_fragment.Is_AllowedToPlay) {
                        dak_fragment.card_selected_card = i;
                        dak_fragment.card_selected.setImageResource(i.getIcon());
                    }
                }
            });
        } else if (cards_enabled[position]){
            holder.imageView.setColorFilter(R.color.colorAccent);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

public static class ViewHolder2 extends RecyclerView.ViewHolder {
    ImageView imageView;

    public ViewHolder2(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.adapter_img);
    }
}
}
