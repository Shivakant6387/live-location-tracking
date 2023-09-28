package com.example.Transport.service;

import com.example.Transport.model.Location;
import com.example.Transport.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public ResponseEntity<Location> addLocation(Location location) {
        try {
            Location newLocation = locationRepository.save(location);
            return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Location>> getAllLocation() {
        try {
            List<Location> locations = locationRepository.findAll();
            if (locations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Location> getLocationById(@PathVariable("id") Long id) {
        Optional<Location> locationData = locationRepository.findById(id);
        if (locationData.isPresent()) {
            return new ResponseEntity<>(locationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Location> updateLocation(Long id, Location location) {
        Optional<Location> locationData = locationRepository.findById(id);
        if (locationData.isPresent()) {
            Location existingLocation = locationData.get();
            existingLocation.setLatitude(location.getLatitude());
            existingLocation.setLongitude(location.getLongitude());
            existingLocation.setIpAddress(location.getIpAddress());
            existingLocation.setCity(location.getCity());
            return new ResponseEntity<Location>(locationRepository.save(existingLocation), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteLocation(@PathVariable("id") Long id) {
        try {
            locationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
