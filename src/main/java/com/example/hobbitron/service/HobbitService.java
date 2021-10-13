package com.example.hobbitron.service;

import com.example.hobbitron.model.DriverDetails;
import com.example.hobbitron.repository.DriverDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbitService {

    private final DriverDetailsRepository driverDetailsRepository;

    public HobbitService(DriverDetailsRepository driverDetailsRepository) {
        this.driverDetailsRepository = driverDetailsRepository;
    }

    public List<DriverDetails> getAll() {
        return driverDetailsRepository.findAll();
    }

    public DriverDetails save(DriverDetails driverDetails) {
        return driverDetailsRepository.save(driverDetails);
    }
}
