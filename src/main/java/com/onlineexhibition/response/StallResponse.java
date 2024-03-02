package com.onlineexhibition.response;

import com.onlineexhibition.model.Stall;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class StallResponse {


    private String responseMessage;

    private Stall stall;

    public StallResponse(String stallUpdatedSuccessfully, boolean b) {

    }
}
