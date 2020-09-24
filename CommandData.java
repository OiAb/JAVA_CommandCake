package com.example.cakecommand;

import java.util.Date;

public class CommandData {
    private int id;
    private String user;
    private String ingredients;
    private int IsConfirmed;

    public CommandData(int id, String user, String ingredients, int isConfirmed) {
        this.id = id;
        this.user = user;
        this.ingredients = ingredients;
        IsConfirmed = isConfirmed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getIsConfirmed() {
        return IsConfirmed;
    }

    public void setIsConfirmed(int isConfirmed) {
        IsConfirmed = isConfirmed;
    }

    @Override
    public String toString() {
        return "-> Command "+id+" by "+user+" with ingredients : \n\n"+ingredients;
    }
}
