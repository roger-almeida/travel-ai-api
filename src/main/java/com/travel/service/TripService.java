package com.travel.service;

import com.travel.exception.TripNotFoundException;
import com.travel.model.Trip;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {

    // Banco em memória — será substituído pelo DynamoDB na Semana 2
    private final List<Trip> trips = new ArrayList<>();

    // Retorna todas as viagens cadastradas
    public List<Trip> findAll() {
        return trips;
    }

    // Busca uma viagem pelo ID
    // Antes retornava Optional<Trip> — agora lança TripNotFoundException se não encontrar
    // Por quê? O GlobalExceptionHandler captura a exceção e devolve HTTP 404 automaticamente
    // O Controller fica mais simples: só chama findById() sem se preocupar com "e se não existir?"
    public Trip findById(String id) {
        return trips.stream()
                .filter(trip -> trip.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TripNotFoundException(id));
        // orElseThrow: se o Optional estiver vazio, lança a exceção fornecida
    }

    // Cria e salva uma nova viagem
    public Trip create(String destination, String traveler,
                       LocalDate startDate, LocalDate endDate,
                       double budget) {
        Trip trip = Trip.create(destination, traveler, startDate, endDate, budget);
        trips.add(trip);
        return trip;
    }
}
