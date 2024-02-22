package com.onlineexhibition.services;

import com.onlineexhibition.model.Stall;
import com.onlineexhibition.response.StallResponse;
import jakarta.validation.Valid;
import java.util.List;

public interface StallService{

    StallResponse saveStall(@Valid Stall stall);

    List<Stall> fetchStallList();

    Stall fetchStallById(Long stallId);

    void deleteStallById(Long stallId);

    Stall updateStall(Long stallId, Stall stall);


}
