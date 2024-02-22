package com.onlineexhibition.controllers;
import com.onlineexhibition.model.Stall;
import com.onlineexhibition.response.StallResponse;
import com.onlineexhibition.services.StallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stall")
public class StallController{

    @Autowired
    private StallService stallService;

    @PostMapping(value = "/add")
    public ResponseEntity<StallResponse> saveStall(@Valid @RequestBody Stall stall) {
        StallResponse response =stallService.saveStall(stall);
        return new ResponseEntity<StallResponse>(response, HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/all")
    public List<Stall> fetchStallList() {
        return stallService.fetchStallList();
    }

    @GetMapping(value = "/{id}")
    public Stall fetchStallById(@PathVariable("id") Long stallId) {
        return stallService.fetchStallById(stallId);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteStallById(@PathVariable("id") Long stallId){
        stallService.deleteStallById(stallId);
        return "Stall Deleted Sucessfully";
    }

    @PutMapping("/{id}")
    public Stall updateStall(@PathVariable("id") Long stallId, @RequestBody Stall stall){
        return stallService.updateStall(stallId,stall);
    }
}

