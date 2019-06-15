package net.marksheehan.doomtowndeckbuilder;

import com.google.gson.annotations.SerializedName;

public class CardModel
{
    @SerializedName("last-modified")
    public String lastModified;

    public String code;
    public String title;
    public String type;

    @SerializedName("type_code")
    public String typeCode;

    public String suit;

    public String keywords;
    public String text;

    public long cost;

    public String gang;
    @SerializedName("gang_code")

    public String gang_code;
    public String gang_letter;

    public long number;
    public long quantity;
    public long rank;
    public long upkeep;
    public long bullets;
    public long influence;
    public long control;
    public long wealth;

    public String url;
    public String imagesrc;
}
