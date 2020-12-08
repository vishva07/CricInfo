package com.vishva.CricInfo.dto.info;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {

    private String city;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private List<Date> dates;

    @SerializedName("gender")
    private String category;

    @SerializedName("match_type")
    private String matchType;

    private Outcome outcome;

    private float overs;

    @SerializedName("player_of_match")
    private List<String> playerOfMatch;

    private List<String> teams;

    private Toss toss;

    private List<String> umpires;

    private String venue;
}
