package com.example.acer.tarneb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class show_resuilt extends Fragment {

    RecyclerView recyclerView;

    public show_resuilt() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_resuilt, container, false);
        recyclerView = view.findViewById(R.id.resuilt_rv);
        final resuilt_Adapter adapter = new resuilt_Adapter(Main_Screen_fagment.results, getActivity());
        RecyclerView.LayoutManager mlayout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mlayout);
        recyclerView.setAdapter(adapter);
        view.findViewById(R.id.new_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main_Screen_fagment fragment1 = new Main_Screen_fagment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.Main_act, fragment1);
                transaction.commit();
            }
        });
        Bundle bundle = this.getArguments();
        boolean Ended = bundle.getBoolean("Ended", false);
        if (Ended) {
            view.findViewById(R.id.next).setVisibility(View.INVISIBLE);
        } else {
            view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dak_fragment fragment1 = new dak_fragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.Main_act, fragment1);
                    transaction.commit();
                }
            });
        }
        return view;
    }

}
