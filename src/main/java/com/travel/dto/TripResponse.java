package com.travel.dto;

import com.travel.model.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

// DTO de saída: representa o JSON que a API devolve ao cliente
// Separado do modelo Trip para controlar o que é exposto externamente
// Regra: o cliente vê o que você decidir colocar aqui — nada mais
@Data
@AllArgsConstructor
public class TripResponse {

    private String id;
    private String destination;
    private String traveler;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budget;
    private String status;

    // Factory Method estático: converte um Trip (modelo interno) em TripResponse (contrato da API)
    // Centraliza a lógica de conversão — se Trip mudar, só aqui precisa ser atualizado
    public static TripResponse from(Trip trip) {
        return new TripResponse(
                trip.getId(),
                trip.getDestination(),
                trip.getTraveler(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getBudget(),
                trip.getStatus()
        );
    }
}
