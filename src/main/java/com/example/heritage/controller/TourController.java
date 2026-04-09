package com.example.heritage.controller;

import com.example.heritage.entity.Tour;
import com.example.heritage.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tour")
@CrossOrigin(origins = "http://localhost:3000")
public class TourController {

    @Autowired
    private TourService service;

    @GetMapping
    public List<Tour> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Tour add(@RequestBody Tour tour) {
        return service.save(tour);
    }

    @PutMapping("/{id}")
    public Tour update(@PathVariable Long id, @RequestBody Tour tour) {
        tour.setId(id);
        return service.save(tour);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }
}