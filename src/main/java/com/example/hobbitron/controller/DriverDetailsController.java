package com.example.hobbitron.controller;

import com.example.hobbitron.model.DriverDetails;
import com.example.hobbitron.service.HobbitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DriverDetailsController {

    private final HobbitService service;

    public DriverDetailsController(HobbitService service) {
        this.service = service;
    }

    @GetMapping("/driverdetails")
    List<DriverDetails> getAll() {
        return service.getAll();
    }

    @PostMapping("/driverdetails")
    DriverDetails save(@RequestBody DriverDetails driverDetails) {
        return service.save(driverDetails);
    }
}
