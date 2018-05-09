package com.example.acer.tarneb;

/**
 * Created by acer on 3/30/2018.
 */

public class Result {
    String tarneb_type;
    boolean got_it;
    String team_who_ordered;
    int order;
    int got_number;
    int our_total;
    int thier_total;

    public Result(String tarneb_type, boolean got_it, String team_who_ordered, int order, int got_number, int our_total, int thier_total) {
        this.tarneb_type = tarneb_type;
        this.got_it = got_it;
        this.team_who_ordered = team_who_ordered;
        this.order = order;
        this.got_number = got_number;
        this.our_total = our_total;
        this.thier_total = thier_total;
    }

    public String getTarneb_type() {
        return tarneb_type;
    }

    public void setTarneb_type(String tarneb_type) {
        this.tarneb_type = tarneb_type;
    }

    public boolean isGot_it() {
        return got_it;
    }

    public void setGot_it(boolean got_it) {
        this.got_it = got_it;
    }

    public String getTeam_who_ordered() {
        return team_who_ordered;
    }

    public void setTeam_who_ordered(String team_who_ordered) {
        this.team_who_ordered = team_who_ordered;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getGot_number() {
        return got_number;
    }

    public void setGot_number(int got_number) {
        this.got_number = got_number;
    }

    public int getOur_total() {
        return our_total;
    }

    public void setOur_total(int our_total) {
        this.our_total = our_total;
    }

    public int getThier_total() {
        return thier_total;
    }

    public void setThier_total(int thier_total) {
        this.thier_total = thier_total;
    }
}
