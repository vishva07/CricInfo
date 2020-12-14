package com.vishva.CricInfo.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerOfMatchRequest {

    private String match_type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    private List<String> teams;
}
