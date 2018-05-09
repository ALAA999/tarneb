package com.example.acer.tarneb;


/**
 * Created by acer on 3/31/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class resuilt_Adapter extends RecyclerView.Adapter<resuilt_Adapter.ViewHolder2> {
    Context context;
    List<Result> list;

    public resuilt_Adapter(List<Result> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.resuilt_item, parent, false);
        return new ViewHolder2(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder2 holder, final int position) {
        Result result = list.get(position);
        holder.talba_num.setText(result.getOrder() + "");
        holder.team_who_orderd.setText(result.getTeam_who_ordered() + "");
        int imageView = 0;
        switch (result.getTarneb_type()) {
            case "Clubs":
                imageView = R.drawable.clubs;
                break;
            case "Spade":
                imageView = R.drawable.spade;
                break;
            case "Dimonds":
                imageView = R.drawable.dimond;
                break;
            case "Hearts":
                imageView = R.drawable.hearts;
        }
        holder.tarneb_type.setBackgroundResource(imageView);
        if (result.got_it) {
            holder.result.setBackgroundResource(R.drawable.ic_check_black_24dp);
        } else {
            holder.result.setBackgroundResource(R.drawable.ic_close_black_24dp);
        }
        holder.eaten.setText(result.getGot_number() + "");
        holder.our_score.setText(result.getOur_total() + "");
        holder.thier_score.setText(result.getThier_total() + "");
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView talba_num, team_who_orderd, tarneb_type, eaten, our_score, thier_score;
        ImageView result ;

        public ViewHolder2(View itemView) {
            super(itemView);
            talba_num = itemView.findViewById(R.id.talba_num);
            team_who_orderd = itemView.findViewById(R.id.team_who_orderd);
            tarneb_type = itemView.findViewById(R.id.tarneb_type);
            result = itemView.findViewById(R.id.result);
            eaten = itemView.findViewById(R.id.eaten);
            our_score = itemView.findViewById(R.id.our_score);
            thier_score = itemView.findViewById(R.id.thier_score);
        }
    }
}
