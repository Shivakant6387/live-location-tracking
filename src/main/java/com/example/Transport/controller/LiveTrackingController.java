package com.example.Transport.controller;

import com.example.Transport.model.Location;
import com.example.Transport.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LiveTrackingController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to my live tracking location";
    }

    @PostMapping("/addLocation")
    public ResponseEntity<Location> addLcation(@RequestBody Location location) {
        return locationService.addLocation(location);
    }

    @GetMapping("/allLocation")
    public ResponseEntity<List<Location>> getAllLocatios() {
        return locationService.getAllLocation();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable("id") Long id) {
        return locationService.getLocationById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable("id") Long id, @RequestBody Location location) {
        return locationService.updateLocation(id, location);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus>deleteLocation(@PathVariable("id")Long id){
        return locationService.deleteLocation(id);
    }
}
