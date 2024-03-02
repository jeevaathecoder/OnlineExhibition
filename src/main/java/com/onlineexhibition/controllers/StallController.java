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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/stall")
public class StallController{

    @Autowired
    private StallService stallService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> saveStall(@Valid @RequestBody Stall stall) {
        StallResponse response =stallService.saveStall(stall);
        return  ResponseEntity.ok("Stall Added Sucessfully");
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<StallResponse> updateStall(@PathVariable("id") Long stallId, @Valid @RequestBody Stall stall) {
        StallResponse response =stallService.updateStall(stallId, stall);
        return new ResponseEntity<StallResponse>(response, HttpStatus.ACCEPTED);
    }
}

