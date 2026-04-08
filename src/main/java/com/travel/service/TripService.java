package com.travel.service;

import com.travel.model.Trip;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// @Service diz ao Spring: "gerencie essa classe, crie uma instância e disponibilize para injeção"
// Você nunca vai chamar "new TripService()" manualmente — o Spring faz isso
@Service
public class TripService {

    // Por enquanto, guardamos as viagens na memória (List)
    // Na Semana 2 vamos substituir pelo DynamoDB
    private final List<Trip> trips = new ArrayList<>();

    // Retorna todas as viagens cadastradas
    public List<Trip> findAll() {
        return trips;
    }

    // Busca uma viagem pelo ID
    // Optional<Trip>: pode retornar uma Trip ou "vazio" (sem NullPointerException)
    public Optional<Trip> findById(String id) {
        return trips.stream()
                .filter(trip -> trip.getId().equals(id))
                .findFirst();
    }

    // Cria uma nova viagem e salva na lista
    public Trip create(String destination, String traveler,
                       LocalDate startDate, LocalDate endDate,
                       double budget) {
        Trip trip = Trip.create(destination, traveler, startDate, endDate, budget);
        trips.add(trip);
        return trip;
    }
}
