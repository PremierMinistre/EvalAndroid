package eu.lmre.baptiste.evalandroid;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Baptiste on 16/01/2018.
 */

public class People {
    @SerializedName("name")
    String name;
    @SerializedName("height")
    String height;
    @SerializedName("mass")
    String mass;
    @SerializedName("hair_color")
    String hair_color;
    @SerializedName("skin_color")
    String skin_color;
    @SerializedName("eye_color")
    String eye_color;
    @SerializedName("birth_year")
    String birth_year;
    @SerializedName("gender")
    String gender;
    @SerializedName("homeworld")
    String homeworld;
    /*
    @SerializedName("films")
    String [] films;
    @SerializedName("species")
    String [] species;
    @SerializedName("vehicles")
    String [] vehicles;
    @SerializedName("starships")
    String [] starships;
    */
    @SerializedName("created")
    String created;
    @SerializedName("edited")
    String edited;
    @SerializedName("url")
    String url;
}
