package com.example.acer.tarneb;


import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

//why nexues s takes the same as pixel xl
//how to make the cards get a better view in the recycler view
//how to connect two devices via bluetooth
// notic that we use getActivity().runonuithread becuse Only the original thread that created a view hierarchy can touch its views.

/**
 * A simple {@link Fragment} subclass.
 */
public class dak_fragment extends Fragment {
    int icons_numbers[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
            R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o,
            R.drawable.p, R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v, R.drawable.w,
            R.drawable.x, R.drawable.y, R.drawable.z, R.drawable.aa, R.drawable.bb, R.drawable.cc, R.drawable.dd, R.drawable.ee,
            R.drawable.ff, R.drawable.gg, R.drawable.hh, R.drawable.ii, R.drawable.jj, R.drawable.kk, R.drawable.ll, R.drawable.mm,
            R.drawable.nn, R.drawable.oo, R.drawable.pp, R.drawable.qq, R.drawable.rr, R.drawable.ss, R.drawable.tt, R.drawable.uu,
            R.drawable.vv, R.drawable.ww, R.drawable.xx, R.drawable.yy, R.drawable.zz};// null pointer exeption because of these cards
    int max_talba = 0, i, loops_num = 0, num_passed = 0, ltosh = 0, num_ofPlayer_played = 0, player_playing;
    String player_WhoOrdered, player0_tarneb, player1_tarneb, player2_tarneb, tarneb, card_type_each_lotsh = "", team_who_orderd;
    boolean player_passed[] = new boolean[4];
    card c1, c2, c3, c4;
    boolean IsOutOfTarneb = false, Has_ended = false, got_it;
    static boolean Is_AllowedToPlay = false;
    ArrayList<card> arrayList, player2, player3, player4;
    static ArrayList<card> player1;
    RecyclerView rv;
    TextView p0_talba, p1_talba, p2_talba, p3_talba, our_total, thier_total, our_now, thier_now, talba_text, plus_one_p0, plus_one_p1, plus_one_p2, plus_one_p3;
    ImageView p0_card, p1_card, p2_card, p3_card, talba_img;
    static ImageView card_selected;
    static card card_selected_card;

    public dak_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dak_fragment, container, false);
        arrayList = new ArrayList<>();
        player1 = new ArrayList<>();
        player2 = new ArrayList<>();
        player3 = new ArrayList<>();
        player4 = new ArrayList<>();
        plus_one_p0 = view.findViewById(R.id.plus_one_p0);
        plus_one_p1 = view.findViewById(R.id.plus_one_p1);
        plus_one_p2 = view.findViewById(R.id.plus_one_p2);
        plus_one_p3 = view.findViewById(R.id.plus_one_p3);
        p0_card = view.findViewById(R.id.p0_card);
        p1_card = view.findViewById(R.id.p1_card);
        p2_card = view.findViewById(R.id.p2_card);
        p3_card = view.findViewById(R.id.p3_card);
        p0_talba = view.findViewById(R.id.p0_talba);
        p1_talba = view.findViewById(R.id.p1_talba);
        p2_talba = view.findViewById(R.id.p2_talba);
        p3_talba = view.findViewById(R.id.p3_talba);
        our_total = view.findViewById(R.id.our_total);
        talba_img = view.findViewById(R.id.talba_img);
        card_selected = view.findViewById(R.id.card_selected);
        card_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Is_AllowedToPlay) {
                    try {
                        p3_card.setImageResource(card_selected_card.getIcon());
                        start_animation(p3_card, R.anim.bottomtotop);
                        card_selected.setImageDrawable(null);
                        player1.remove(card_selected_card);
                        final cards_Adapter lettersUserAnswer = new cards_Adapter(player1, getActivity().getApplicationContext());
                        set_Adapter(lettersUserAnswer);
                        c1 = card_selected_card;
                        ++num_ofPlayer_played;
                        if (num_ofPlayer_played == 4) {
                            if (ltosh == 12) {
                                ++Main_Screen_fagment.talba_turn;
                                last_clicked(true);
                            } else {
                                last_clicked(false);
                            }
                        } else if (num_ofPlayer_played == 1) {
                            card_type_each_lotsh = c1.getCardType();
                            Start_Playing(0);
                        } else {
                            Start_Playing(0);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        });
        thier_total = view.findViewById(R.id.thier_total);
        our_now = view.findViewById(R.id.our_now);
        thier_now = view.findViewById(R.id.thier_now);
        talba_text = view.findViewById(R.id.talba_text);
        start_new_dak();
        rv = view.findViewById(R.id.rv);
        final cards_Adapter lettersUserAnswer = new cards_Adapter(player1, getActivity().getApplicationContext());
        set_Adapter(lettersUserAnswer);
        return view;
    }
    public void set_Adapter(cards_Adapter lettersUserAnswer){
        final LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(horizontalLayoutManagaer);
        rv.setAdapter(lettersUserAnswer);
    }

    public void start_new_dak() {
        if (!Main_Screen_fagment.results.isEmpty()) {
            our_total.setText(Main_Screen_fagment.results.get(Main_Screen_fagment.results.size() - 1).getOur_total() + "");
            thier_total.setText(Main_Screen_fagment.results.get(Main_Screen_fagment.results.size() - 1).getThier_total() + "");
        }
        int e = 0, c = 52;
        for (int t = 14; t > 1; --t) {
            arrayList.add(new card(t, icons_numbers[e], false, "Clubs", c - 39));
            arrayList.add(new card(t, icons_numbers[e + 1], false, "Spade", c - 13));
            arrayList.add(new card(t, icons_numbers[e + 2], false, "Hearts", c - 26));
            arrayList.add(new card(t, icons_numbers[e + 3], false, "Dimonds", c));
            e = e + 4;
            --c;
        }
        Collections.shuffle(arrayList);
        SortCards();
        for (int i = 0; i < 13; i++) {
            player1.add(arrayList.get(i));
        }
        for (int i = 13; i < 26; i++) {
            player2.add(arrayList.get(i));
        }
        for (int i = 26; i < 39; i++) {
            player3.add(arrayList.get(i));
        }
        for (int i = 39; i < 52; i++) {
            player4.add(arrayList.get(i));
        }
        i = Main_Screen_fagment.talba_turn;
        Talba_loop();
    }

    public void SortCards() {// we have sorted the whole arraylist to make sure that the copmuter will chose the best card onec he searches for it.
        for (int i = 0; i < 13; i++) {
            for (int j = i + 1; j < 13; j++) {
                if (arrayList.get(i).getValue() > arrayList.get(j).getValue()) {
                    card c1 = arrayList.get(i);
                    arrayList.set(i, arrayList.get(j));
                    arrayList.set(j, c1);
                }
            }
        }
        for (int i = 13; i < 26; i++) {
            for (int j = i + 1; j < 26; j++) {
                if (arrayList.get(i).getValue() > arrayList.get(j).getValue()) {
                    card c1 = arrayList.get(i);
                    arrayList.set(i, arrayList.get(j));
                    arrayList.set(j, c1);
                }
            }
        }
        for (int i = 26; i < 39; i++) {
            for (int j = i + 1; j < 39; j++) {
                if (arrayList.get(i).getValue() > arrayList.get(j).getValue()) {
                    card c1 = arrayList.get(i);
                    arrayList.set(i, arrayList.get(j));
                    arrayList.set(j, c1);
                }
            }
        }
        for (int i = 39; i < 52; i++) {
            for (int j = i + 1; j < 52; j++) {
                if (arrayList.get(i).getValue() > arrayList.get(j).getValue()) {
                    card c1 = arrayList.get(i);
                    arrayList.set(i, arrayList.get(j));
                    arrayList.set(j, c1);
                }
            }
        }
    }

    public void Talba_loop() {
        if (i == 4) {
            i = 0;
        }
        if ((num_passed == 3 && loops_num >= 4)) {//if this is the first loop and 3 persons passed game will not end, it'll let the last player chack his cards || we check if all players have passed it will bereak.
            Has_Ended();
        } else {
            if (!player_passed[i]) {
                new CountDownTimer(500, 500) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        if (!player_passed[i]) {
                            Talba(i);
                        } else {
                            ++i;
                            Talba_loop();
                        }
                    }
                }.start();
            } else if (player_passed[i]) {
                if (num_passed == 4) {
                    show_resuilt fragment1 = new show_resuilt();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("Ended", false);
                    fragment1.setArguments(bundle);
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.Main_act, fragment1);
                    transaction.commit();
                } else {
                    ++i;
                    Talba_loop();
                }
            }
        }
    }

    public int get_tarneb_img() {
        int img = 0;
        switch (tarneb) {
            case "Dimonds":
                img = R.drawable.dimond;
                break;
            case "Spade":
                img = R.drawable.spade;
                break;
            case "Hearts":
                img = R.drawable.hearts;
                break;
            case "Clubs":
                img = R.drawable.clubs;
                break;
        }
        return img;
    }

    public void Has_Ended() {
        if (!Has_ended) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    switch (player_WhoOrdered) {
                        case "p0":
                            p0_talba.setText(max_talba + "");
                            tarneb = player0_tarneb;
                            set_tarmeb_rank();
                            Start_Playing(0);
                            talba_text.setText(tarneb + "  " + max_talba);
                            talba_img.setImageResource(get_tarneb_img());
                            break;
                        case "p1":
                            p1_talba.setText(max_talba + "");
                            tarneb = player1_tarneb;
                            set_tarmeb_rank();
                            Start_Playing(1);
                            talba_text.setText(tarneb + "  " + max_talba);
                            talba_img.setImageResource(get_tarneb_img());
                            break;
                        case "p2":
                            p2_talba.setText(max_talba + "");
                            tarneb = player2_tarneb;
                            set_tarmeb_rank();
                            Start_Playing(2);
                            talba_text.setText(tarneb + "  " + max_talba);
                            talba_img.setImageResource(get_tarneb_img());
                            break;
                        case "p3":
                            // curent player will chose his tarneb (we will show the chose tarneb dialog)
                            show_current_player_Talba_Dialog();
                            break;
                    }
                }
            });
        }
    }

    public int get_who_ate() {
        int location = 0;// if they are all out of the card_type_each_lotsh
		//the first part of the condition makes sure that in case any player has played a card diffrent from the card_type_each_lotsh the computer will set the rank of it to 0 then it makes sure that non of the cards is tarneb because if so, we will not change the rank of any card the tarneba will eat
        if (!(c1.getCardType().equals(card_type_each_lotsh) && c2.getCardType().equals(card_type_each_lotsh) && c3.getCardType().equals(card_type_each_lotsh) && c4.getCardType().equals(card_type_each_lotsh)) && !(c1.getCardType().equals(tarneb) || c2.getCardType().equals(tarneb) || c3.getCardType().equals(tarneb) || c4.getCardType().equals(tarneb))) { // if they all have the same color or someone played a tarneba
            if (!c1.getCardType().equals(card_type_each_lotsh)) {
                c1.setRank(0);
            }
            if (!c2.getCardType().equals(card_type_each_lotsh)) {
                c2.setRank(0);
            }
            if (!c3.getCardType().equals(card_type_each_lotsh)) {
                c3.setRank(0);
            }
            if (!c4.getCardType().equals(card_type_each_lotsh)) {
                c4.setRank(0);
            }
        } // cards have diffrents types or no one played a tarneb
        if (c1.getRank() > c2.getRank() && c1.getRank() > c3.getRank() && c1.getRank() > c4.getRank()) {
            location = 3;// current player user
        } else if (c2.getRank() > c1.getRank() && c2.getRank() > c3.getRank() && c2.getRank() > c4.getRank()) {
            location = 0;
        } else if (c3.getRank() > c1.getRank() && c3.getRank() > c2.getRank() && c3.getRank() > c4.getRank()) {
            location = 1;
        } else {
            location = 2;
        }
        return location;
    }

    public void last_clicked(final boolean last_lotsh) {// once a lotsh is over
        final int who_ate = get_who_ate();
        final int[] thiers_now = {Integer.parseInt(thier_now.getText().toString())};
        final int[] ours_now = {Integer.parseInt(our_now.getText().toString())};
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (who_ate) {
                    case 1:
                        plus_one_p1.setText("+1");
                        ++ours_now[0];
                        our_now.setText(ours_now[0] + "");
                        break;
                    case 2:
                        plus_one_p2.setText("+1");
                        ++thiers_now[0];
                        thier_now.setText(thiers_now[0] + "");

                        break;
                    case 3:
                        plus_one_p3.setText("+1");
                        ++ours_now[0];
                        our_now.setText(ours_now[0] + "");

                        break;
                    case 0:
                        plus_one_p0.setText("+1");
                        ++thiers_now[0];
                        thier_now.setText(thiers_now[0] + "");
                        break;
                }
            }
        });
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                clear_icons();
                ++ltosh;
                if (!last_lotsh) {
                    Start_Playing(who_ate);
                } else {
                    set_Text_For_Winner_Team();
                }
            }
        }, 800);
    }

    public void clear_icons() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                p0_card.setImageDrawable(null);
                p1_card.setImageDrawable(null);
                p2_card.setImageDrawable(null);
                p3_card.setImageDrawable(null);
                plus_one_p0.setText("");
                plus_one_p1.setText("");
                plus_one_p2.setText("");
                plus_one_p3.setText("");
            }
        });
        if (!IsOutOfTarneb && card_type_each_lotsh.equals(tarneb)) {
            if (!c1.getCardType().equals(tarneb) && !c3.getCardType().equals(tarneb)) {
                IsOutOfTarneb = true;
            } else if (!c2.getCardType().equals(tarneb) && !c4.getCardType().equals(tarneb)) {
                IsOutOfTarneb = true;
            }
        }
        arrayList.remove(c4);
        arrayList.remove(c3);
        arrayList.remove(c2);
        arrayList.remove(c1);
        player2.remove(c2);
        player3.remove(c3);
        player4.remove(c4);
        c1 = null;
        c2 = null;
        c3 = null;
        c4 = null;
        card_type_each_lotsh = "";
        num_ofPlayer_played = 0;
    }


    public card Serch_for_card_toPlay(ArrayList<card> player_array, String player_turn) {
        card c = null;
        if (card_type_each_lotsh.equals("")) {// first open for this lotsh
            if ((player_turn.equals("p0") || player_turn.equals("p2")) && (player_WhoOrdered.equals("p0") || player_WhoOrdered.equals("p2")) && (!IsOutOfTarneb) && !Is_Out_Of_tarneb(player_array)) {
                card mine = get_highest_tarneba(player_array);
                if (Is_makel(get_highest_tarneba(player_array))) {
                    c = mine;
                    card_type_each_lotsh = c.getCardType();
                } else {
                    c = get_smallest_tarneba(player_array);
                    card_type_each_lotsh = c.getCardType();
                }
            } else if ((player_turn.equals("p1") || player_turn.equals("p3")) && (player_WhoOrdered.equals("p1") || player_WhoOrdered.equals("p3")) && (!IsOutOfTarneb) && !Is_Out_Of_tarneb(player_array)) {
                card mine = get_highest_tarneba(player_array);
                if (Is_makel(get_highest_tarneba(player_array))) {
                    c = mine;
                    card_type_each_lotsh = c.getCardType();
                } else {
                    c = get_smallest_tarneba(player_array);
                    card_type_each_lotsh = c.getCardType();
                }
            }
            if (c == null) {
                for (int i = 0; i < player_array.size(); i++) {
                    if (!player_array.get(i).getCardType().equals(tarneb) && Is_makel(player_array.get(i))) {
                        c = player_array.get(i);
                        card_type_each_lotsh = player_array.get(i).getCardType();
                        break;
                    }
                }
            }
            if (c == null) {
                c = get_smallest_card(player_array);
                card_type_each_lotsh = c.getCardType();
            }
        } else {// not first open for this lotsh
            if (num_ofPlayer_played == 1) { // secound player turn
                if (c1 != null) { // Right Side player turn
                    c = make_checks_for_one_player_played(c1, player_array);
                } else if (c2 != null) { // Top Side player turn
                    c = make_checks_for_one_player_played(c2, player_array);
                } else if (c3 != null) { // left Side player turn
                    c = make_checks_for_one_player_played(c3, player_array);
                }
            } else if (num_ofPlayer_played == 2) { // third playerr turn
                if (c1 != null && c2 != null) { //Top Side player turn
                    c = make_check_for_two_player_played(c1, c2, player_array);
                } else if (c2 != null && c3 != null) { //left Side player turn
                    c = make_check_for_two_player_played(c2, c3, player_array);
                } else if (c4 != null && c1 != null) { //Right Side player turn
                    c = make_check_for_two_player_played(c4, c1, player_array);
                }
            } else if (num_ofPlayer_played == 3) {
                if (c2 == null) {
                    if (c4.getRank() > c3.getRank() && c4.getRank() > c1.getRank()) {
                        c = get_smallest_Rank_same_Type(player_array);
                    } else {
                        c = get_higher_card(player_array, c3, c1);
                        if (c == null) {// i dont have a higher card from the same color
                            if (!Is_Out_Of_Color(player_array)) {
                                c = get_smallest_Rank_same_Type(player_array);
                            } else {
                                if (Is_Out_Of_tarneb(player_array)) {
                                    c = get_smallest_card(player_array);
                                } else {
                                    c = get_higher_tarneba(player_array, c3, c1);
                                }
                            }
                        }
                    }
                } else if (c3 == null) {
                    if (c1.getRank() > c2.getRank() && c1.getRank() > c4.getRank()) {
                        c = get_smallest_Rank_same_Type(player_array);
                    } else {
                        c = get_higher_card(player_array, c2, c4);
                        if (c == null) {
                            if (!Is_Out_Of_Color(player_array)) {
                                c = get_smallest_Rank_same_Type(player_array);
                            } else {
                                if (Is_Out_Of_tarneb(player_array)) {
                                    c = get_smallest_card(player_array);
                                } else {
                                    c = get_higher_tarneba(player_array, c2, c4);
                                }
                            }
                        }
                    }
                } else if (c4 == null) {
                    if (c2.getRank() > c3.getRank() && c2.getRank() > c1.getRank()) {
                        c = get_smallest_Rank_same_Type(player_array);
                    } else {
                        c = get_higher_card(player_array, c3, c1);
                        if (c == null) {
                            if (!Is_Out_Of_Color(player_array)) {
                                c = get_smallest_Rank_same_Type(player_array);
                            } else {
                                if (Is_Out_Of_tarneb(player_array)) {
                                    c = get_smallest_card(player_array);
                                } else {
                                    c = get_higher_tarneba(player_array, c3, c1);
                                }
                            }
                        }
                    }
                }
            }
            if (c == null) {
                c = get_smallest_card(player_array);
            }
        }
        return c;
    }

    public void start_animation(final ImageView imageView, final int animation) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.startAnimation(AnimationUtils.loadAnimation(getActivity(), animation));
            }
        });
    }

    public void Start_Playing(int player_turn) {
        Is_AllowedToPlay = false;
        player_playing = player_turn;
        if (player_playing == 0) {//player 0
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    c2 = Serch_for_card_toPlay(player2, "p0");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            p0_card.setImageResource(c2.getIcon());
                            start_animation(p0_card, R.anim.righttoleft);
                        }
                    });
                    ++num_ofPlayer_played;
                    ++player_playing;// what you need to make is to clear the varabiles after evey single lotsh!
                    //p[ltosh + 39].setVisible(false); visibilty for the right sid player cards
                    if (num_ofPlayer_played == 4) {
                        if (ltosh == 12) {
                            ++Main_Screen_fagment.talba_turn;
                            last_clicked(true);
                        } else {
                            last_clicked(false);
                        }
                    } else {
                        Start_Playing(1);
                    }
                }
            }, 400);
        }
        if (player_playing == 1) {//player 1
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    c3 = Serch_for_card_toPlay(player3, "p1");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            p1_card.setImageResource(c3.getIcon());
                            start_animation(p1_card, R.anim.toptobottom);
                        }
                    });
                    ++num_ofPlayer_played;
                    ++player_playing;
                    //p[ltosh + 13].setVisible(false);
                    if (num_ofPlayer_played == 4) {
                        if (ltosh == 12) {
                            ++Main_Screen_fagment.talba_turn;
                            last_clicked(true);
                        } else {
                            last_clicked(false);
                        }
                    } else {
                        Start_Playing(2);
                    }
                }
            }, 400);
        }
        if (player_playing == 2) {//player 2
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    c4 = Serch_for_card_toPlay(player4, "p2");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            p2_card.setImageResource(c4.getIcon());
                            start_animation(p2_card, R.anim.lefttoright);
                        }
                    });
                    ++num_ofPlayer_played;
                    ++player_playing;
                    //p[ltosh + 26].setVisible(false);
                    if (num_ofPlayer_played == 4) {
                        if (ltosh == 12) {
                            ++Main_Screen_fagment.talba_turn;
                            last_clicked(true);
                        } else {
                            last_clicked(false);
                        }
                    } else {
                        Start_Playing(3);
                    }
                }
            }, 400);
        }
        if (player_playing == 3) {//player 3
            Is_AllowedToPlay = true;
            set_cards_enalbed();
        }
    }

    public void set_cards_enalbed() {
        if (!Is_Out_Of_Color(player1)) { // if this player is out of color we will not make any changes
            final boolean[] cards_enabled = new boolean[player1.size()];
            for (int j = 0; j < player1.size(); j++) {
                if (!player1.get(j).getCardType().equals(card_type_each_lotsh)) {
                    cards_enabled[j] = true;
                } else {
                    cards_enabled[j] = false;
                }
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final cards_Adapter lettersUserAnswer = new cards_Adapter(player1, getActivity().getApplicationContext(), cards_enabled);
                    Log.e("cards" , "adapter");
                    set_Adapter(lettersUserAnswer);
                }
            });
        }
    }

    public void set_Text_For_Winner_Team() {
        final boolean[] winOrLost = {false}; // here win or lost means in case the team win or lost the game will end and put this boolean to true becuse wheb wh send it false the computer will understand that all players have passed and the game has not been played
        if (Main_Screen_fagment.talba_turn == 4) {
            Main_Screen_fagment.talba_turn = 0;
        }
        final int thier_scoore = Integer.parseInt(thier_now.getText().toString());
        final int our_scoore = Integer.parseInt(our_now.getText().toString());
        int thier_old_scoore = Integer.parseInt(thier_total.getText().toString());
        int our_old_scoore = Integer.parseInt(our_total.getText().toString());
        if (player_WhoOrdered.equals("p0") || player_WhoOrdered.equals("p2")) { // if they oreded it
            team_who_orderd = "هم";
            if (thier_scoore >= max_talba) {
                thier_old_scoore = thier_old_scoore + thier_scoore;
                got_it = true;
            } else {
                thier_old_scoore = thier_old_scoore - max_talba;
                got_it = false;
                our_old_scoore = our_old_scoore + our_scoore;
            }
        } else {
            team_who_orderd = "نحن";
            if (our_scoore >= max_talba) {
                got_it = true;
                our_old_scoore = our_old_scoore + our_scoore;
            } else {
                our_old_scoore = our_old_scoore - max_talba;
                got_it = false;
                thier_old_scoore = thier_old_scoore + thier_scoore;
            }
        }
        final int finalThier_old_scoore = thier_old_scoore;
        final int finalOur_old_scoore = our_old_scoore;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                thier_total.setText(finalThier_old_scoore + "");
                our_total.setText(finalOur_old_scoore + "");
                if (finalThier_old_scoore >= 31) {
                    talba_text.setText("خسرت");
                    winOrLost[0] = true;
                } else if (finalOur_old_scoore >= 31) {
                    talba_text.setText("فزت");
                    winOrLost[0] = true;
                }
                if (team_who_orderd.equals("هم")) {
                    Main_Screen_fagment.results.add(new Result(tarneb, got_it, team_who_orderd, max_talba, thier_scoore, finalOur_old_scoore, finalThier_old_scoore));
                } else {
                    Main_Screen_fagment.results.add(new Result(tarneb, got_it, team_who_orderd, max_talba, our_scoore, finalOur_old_scoore, finalThier_old_scoore));
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("Ended", winOrLost[0]);
                show_resuilt fragment1 = new show_resuilt();
                fragment1.setArguments(bundle);
                FragmentUtil.replaceFragment(getActivity(), fragment1, R.id.Main_act);
            }
        });
    }


//this methode is to make the tarned orderd the strongest card in this game1
    // this method will be called when the other function is not repated

    public void set_tarmeb_rank() {
        for (int i = 0; i < 52; i++) {
            if (arrayList.get(i).getCardType().equals(tarneb)) {
                arrayList.get(i).setRank(arrayList.get(i).getRank() + 60);
            }
        }
        for (int i = 0; i < 13; i++) {
            if (player1.get(i).getCardType().equals(tarneb)) {
                player1.get(i).setRank(player1.get(i).getRank() + 60);
            }
            if (player2.get(i).getCardType().equals(tarneb)) {
                player2.get(i).setRank(player2.get(i).getRank() + 60);
            }
            if (player3.get(i).getCardType().equals(tarneb)) {
                player3.get(i).setRank(player3.get(i).getRank() + 60);
            }
            if (player4.get(i).getCardType().equals(tarneb)) {
                player4.get(i).setRank(player4.get(i).getRank() + 60);
            }
        }
    }

    public void hide_And_clicable(Button button) {
        button.setVisibility(View.INVISIBLE);
        button.setClickable(false);
    }

    public void show_current_player_Talba_Dialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.current_player_talba);
        Button Dimonds, Spade, Clubs, Hearts;
        Dimonds = dialog.findViewById(R.id.dimond);
        Spade = dialog.findViewById(R.id.spade);
        Clubs = dialog.findViewById(R.id.clubs);
        Hearts = dialog.findViewById(R.id.hearts);
        Dimonds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tarneb = "Dimonds";
                current_user_will_start(dialog, R.drawable.dimond);
            }
        });
        Spade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tarneb = "Spade";
                current_user_will_start(dialog, R.drawable.spade);
            }
        });
        Clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tarneb = "Clubs";
                current_user_will_start(dialog, R.drawable.clubs);
            }
        });
        Hearts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tarneb = "Hearts";
                current_user_will_start(dialog, R.drawable.hearts);
            }
        });
        dialog.show();
    }

    public void current_user_will_start(Dialog dialog, int drawable) {
        set_tarmeb_rank();
        talba_text.setText(tarneb + "  " + max_talba);
        talba_img.setImageResource(drawable);
        Start_Playing(3);
        dialog.dismiss();
    }

    public void show_Talba_Dialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.talba_dialog);
        Button seven, eight, nine, ten, eleven, thirteen, pass;
        seven = dialog.findViewById(R.id.seven);
        eight = dialog.findViewById(R.id.eight);
        nine = dialog.findViewById(R.id.nine);
        ten = dialog.findViewById(R.id.ten);
        eleven = dialog.findViewById(R.id.eleven);
        thirteen = dialog.findViewById(R.id.thirteen);
        pass = dialog.findViewById(R.id.pass_button);
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++num_passed;
                p3_talba.setText("Pass");
                player_passed[3] = true;
                ++loops_num;
                ++i;
                Talba_loop();
                dialog.dismiss();
            }
        });
        switch (max_talba) {
            case 7:
                hide_And_clicable(seven);
                break;
            case 8:
                hide_And_clicable(seven);
                hide_And_clicable(eight);
                break;
            case 9:
                hide_And_clicable(seven);
                hide_And_clicable(eight);
                hide_And_clicable(nine);
                break;
            case 10:
                hide_And_clicable(seven);
                hide_And_clicable(eight);
                hide_And_clicable(nine);
                hide_And_clicable(ten);
                break;
            case 11:
                hide_And_clicable(seven);
                hide_And_clicable(eight);
                hide_And_clicable(nine);
                hide_And_clicable(ten);
                hide_And_clicable(eleven);
                break;
            case 13:
                break;
        }
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_talba = 7;
                current_player_talb(dialog);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_talba = 8;
                current_player_talb(dialog);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_talba = 9;
                current_player_talb(dialog);
            }
        });
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_talba = 10;
                current_player_talb(dialog);
            }
        });
        eleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_talba = 11;
                current_player_talb(dialog);
            }
        });
        thirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_talba = 13;
                current_player_talb(dialog);
            }
        });
        dialog.show();
    }

    public void current_player_talb(Dialog dialog) { // when the curent player order sth
        p3_talba.setText(max_talba + "");
        player_WhoOrdered = "p3";
        ++loops_num;
        ++i;
        if (num_passed == 3) {
            show_current_player_Talba_Dialog();
        } else {
            Talba_loop();
        }
        dialog.dismiss();
    }

    //this mathode is to make players order tarneb
    public void Talba(int player_turn) {
        int p1, p2, p3;
        switch (player_turn) {
            case 0:
                p1 = ChenckCardsForTalba(player2);
                if (p1 > max_talba) {
                    max_talba = p1;
                    p0_talba.setText(max_talba + "");
                    player_WhoOrdered = "p0";
                    player0_tarneb = tarneb;
                } else {
                    p0_talba.setText("Pass");
                    player_passed[0] = true;
                    ++num_passed;
                }
                ++loops_num;
                ++i;
                Talba_loop();
                break;
            case 1:
                p2 = ChenckCardsForTalba(player3);
                if (p2 > max_talba) {
                    max_talba = p2;
                    p1_talba.setText(max_talba + "");
                    player_WhoOrdered = "p1";
                    player1_tarneb = tarneb;
                } else {
                    p1_talba.setText("Pass");
                    player_passed[1] = true;
                    ++num_passed;
                }
                ++loops_num;
                ++i;
                Talba_loop();
                break;
            case 2:
                p3 = ChenckCardsForTalba(player4);
                if (p3 > max_talba) {
                    max_talba = p3;
                    p2_talba.setText(max_talba + "");
                    player_WhoOrdered = "p2";
                    player2_tarneb = tarneb;
                } else {
                    p2_talba.setText("Pass");
                    player_passed[2] = true;
                    ++num_passed;
                }
                ++loops_num;
                ++i;
                Talba_loop();
                break;
            case 3:
                show_Talba_Dialog();
                break;
        }
    }

    public int ChenckCardsForTalba(ArrayList<card> ar) {
        int clubs = 0, spade = 0, hearts = 0, dimonds = 0, talba = 0;
        for (card ar1 : ar) {
            switch (ar1.getCardType()) {
                case "Clubs":
                    ++clubs;
                    break;
                case "Spade":
                    ++spade;
                    break;
                case "Hearts":
                    ++hearts;
                    break;
                case "Dimonds":
                    ++dimonds;
                    break;
            }
        }
        if (clubs == 5) {
            talba = 7;
            tarneb = "Clubs";
        } else if (spade == 5) {
            talba = 7;
            tarneb = "Spade";
        } else if (hearts == 5) {
            talba = 7;
            tarneb = "Hearts";
        } else if (dimonds == 5) {
            talba = 7;
            tarneb = "Dimonds";
        }/////////////////////////////
        if (clubs == 6 || clubs == 7) {
            talba = 8;
            tarneb = "Clubs";
        } else if (spade == 6 || spade == 7) {
            talba = 8;
            tarneb = "Spade";
        } else if (hearts == 6 || hearts == 7) {
            talba = 8;
            tarneb = "Hearts";
        } else if (dimonds == 6 || dimonds == 7) {
            talba = 8;
            tarneb = "Dimonds";
        }/////////////////////////////
        if (clubs == 8) {
            talba = 9;
            tarneb = "Clubs";
        } else if (spade == 8) {
            talba = 9;
            tarneb = "Spade";
        } else if (hearts == 8) {
            talba = 9;
            tarneb = "Hearts";
        } else if (dimonds == 8) {
            talba = 9;
            tarneb = "Dimonds";
        }/////////////////////////////
        if (clubs == 9) {
            talba = 10;
            tarneb = "Clubs";
        } else if (spade == 9) {
            talba = 10;
            tarneb = "Spade";
        } else if (hearts == 9) {
            talba = 10;
            tarneb = "Hearts";
        } else if (dimonds == 9) {
            talba = 10;
            tarneb = "Dimonds";
        }/////////////////////////////
        if (clubs == 10 || clubs == 11) {
            talba = 11;
            tarneb = "Clubs";
        } else if (spade == 10 || spade == 11) {
            talba = 11;
            tarneb = "Spade";
        } else if (hearts == 10 || hearts == 11) {
            talba = 11;
            tarneb = "Hearts";
        } else if (dimonds == 10 || dimonds == 10) {
            talba = 11;
            tarneb = "Dimonds";
        }
        return talba;
    }

    public card make_checks_for_one_player_played(card card, ArrayList<card> player_array) {
        card c = null;
        if (Is_Out_Of_Color(player_array)) {
            if (Is_Out_Of_tarneb(player_array)) {
                c = get_smallest_card(player_array);
            } else {
                c = get_smallest_tarneba(player_array);
            }
        } else { // if not out of color
            c = get_higher_card(player_array, card);
            if (c == null) {
                c = get_smallest_Rank_same_Type(player_array);
            }
        }
        return c;
    }

    public card make_check_for_two_player_played(card player_one_card, card player_two_card, ArrayList<card> player_array) {
        card c = null;
        if (player_one_card.getRank() < player_two_card.getRank()) {
            c = get_highest_card_Same_type(player_array);
            if (c != null && c.getRank() < player_two_card.getRank()) {
                c = get_smallest_Rank_same_Type(player_array);
            }
            if (c == null) {
                c = get_higher_tarneba(player_array, player_one_card, player_two_card);
            }
        } else if (player_one_card.getRank() <= 9) {
            c = get_highest_card_Same_type(player_array);
            if (c == null) {
                c = get_smallest_tarneba(player_array);
            }
        } else {
            c = get_smallest_Rank_same_Type(player_array);
        }
        return c;
    }

    public card get_higher_card(ArrayList<card> player_array, card previous_player_card) {
        card c = null;
        for (int i = 0; i < player_array.size(); i++) {
            if (player_array.get(i).getRank() > previous_player_card.getRank() && player_array.get(i).getCardType().equals(card_type_each_lotsh)) {
                c = player_array.get(i);
                break;
            }
        }
        return c;
    }

    public card get_higher_card(ArrayList<card> player_array, card previous_player_card, card secoumf_previous_card) {
        card c = null;
        for (int i = 0; i < player_array.size(); i++) {
            if (player_array.get(i).getRank() > previous_player_card.getRank() && player_array.get(i).getRank() > secoumf_previous_card.getRank() && player_array.get(i).getCardType().equals(card_type_each_lotsh)) {
                c = player_array.get(i);
                break;
            }
        }
        return c;
    }

    public card get_highest_card_Same_type(ArrayList<card> player_array) {
        card c = null;
        for (int i = 0; i < player_array.size(); i++) {
            if (player_array.get(i).getCardType().equals(card_type_each_lotsh)) {
                c = player_array.get(i);
                break;
            }
        }
        if (c != null) {
            for (int i = 0; i < player_array.size(); i++) {
                if (player_array.get(i).getRank() > c.getRank() && player_array.get(i).getCardType().equals(card_type_each_lotsh)) {
                    c = player_array.get(i);
                }
            }
        }
        return c;
    }

    public boolean Is_Out_Of_Color(ArrayList<card> player_array) {
        boolean Is_Out = true;
        for (int i = 0; i < player_array.size(); i++) {
            if (player_array.get(i).getCardType().equals(card_type_each_lotsh)) {
                Is_Out = false;
                break;
            }
        }
        return Is_Out;
    }

    public boolean Is_Out_Of_tarneb(ArrayList<card> player_array) {
        boolean Is_Out = true;
        for (int i = 0; i < player_array.size(); i++) {
            if (player_array.get(i).getCardType().equals(tarneb)) {
                Is_Out = false;
                break;
            }
        }
        return Is_Out;
    }

    public card get_highest_tarneba(ArrayList<card> arrayList) {
        card c = null;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getCardType().equals(tarneb)) {
                c = arrayList.get(i);
                break;
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getRank() > c.getRank()) {
                c = arrayList.get(i);
            }
        }
        return c;
    }

    public card get_higher_tarneba(ArrayList<card> arrayList, card previous_player_card, card secoumf_previous_card) {
        card c = null;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getCardType().equals(tarneb) && arrayList.get(i).getRank() > previous_player_card.getRank() && arrayList.get(i).getRank() > secoumf_previous_card.getRank()) {
                c = arrayList.get(i);
                break;
            }
        }
        return c;
    }

    public card get_smallest_tarneba(ArrayList<card> arrayList) {
        card c = null;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getCardType().equals(tarneb)) {
                c = arrayList.get(i);
                break;
            }
        }
        return c;
    }

    public card get_smallest_card(ArrayList<card> player_array) {
        card c = player_array.get(0);
        for (int i = 1; i < player_array.size(); i++) {
            if (c.getRank() > player_array.get(i).getRank()) {
                c = player_array.get(i);
            }
        }
        return c;
    }

    public card get_smallest_Rank_same_Type(ArrayList<card> player_array) {// if who ate want to play
        card c = null;// here we will check if user have the same card type if not we will go through another metohde
        for (int i = 0; i < player_array.size(); i++) {
            if (player_array.get(i).getCardType().equals(card_type_each_lotsh)) {
                c = player_array.get(i);
                break;
            }
        }
        return c;
    }

    public boolean Is_makel(card card) {
        boolean is_makel = true;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getCardType().equals(card.getCardType())) {
                if (arrayList.get(i).getRank() <= card.getRank()) {
                    is_makel = true;
                } else {
                    is_makel = false;
                    break;
                }
            }
        }
        return is_makel;
    }

}
