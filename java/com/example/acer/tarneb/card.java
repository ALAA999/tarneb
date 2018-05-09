package com.example.acer.tarneb;

import android.widget.ImageView;

/**
 * Created by acer on 3/29/2018.
 */
public class card  {

    int rank;
    int icon;
    String cardType;
    int value;
    boolean selcted;

    public card( int rank,int icon,boolean selected,String cardType,int value) {
        this.rank = rank;
        this.icon = icon;
        this.cardType = cardType;
        this.value = value;
        this.selcted = selected;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSelcted() {
        return selcted;
    }

    public void setSelcted(boolean selcted) {
        this.selcted = selcted;
    }
}
