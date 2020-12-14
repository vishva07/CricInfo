package com.vishva.CricInfo.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutcomeRequest {

    private String venue;

    private String matchType;
}
