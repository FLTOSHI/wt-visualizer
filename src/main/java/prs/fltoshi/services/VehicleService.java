package prs.fltoshi.services;

import prs.fltoshi.models.dto.VehicleDTO;
import prs.fltoshi.models.types.Vehicle;
import prs.fltoshi.repositories.VehicleRepository;

import java.util.List;

public class VehicleService {
    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public List<VehicleDTO> getVehiclesForDisplay(String country, String type) {
        return repository.findByCountry(country).stream()
                .filter(v -> v.getVehicleType().equals(type))
                .map(this::convertToDto)
                .toList();
    }

    private VehicleDTO convertToDto(Vehicle vehicle) {
        return VehicleDTO.builder()
                .identifier(vehicle.getIdentifier())
                .era(vehicle.getEra())
                .country(vehicle.getCountry())
                .vehicleType(vehicle.getVehicleType())
                // Можно добавить больше полей при необходимости
                .build();
    }
}