package com.example.heritage.service;

import com.example.heritage.entity.Tour;
import com.example.heritage.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    @Autowired
    private TourRepository repo;

    public List<Tour> getAll() {
        return repo.findAll();
    }

    public Tour save(Tour tour) {
        return repo.save(tour);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}