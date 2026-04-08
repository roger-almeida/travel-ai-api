package com.travel.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

// @Data gera automaticamente: getters, setters, toString, equals, hashCode
// Sem Lombok você teria que escrever tudo isso na mão — são ~50 linhas a mais
@Data
@NoArgsConstructor   // gera: public Trip() {}
@AllArgsConstructor  // gera: public Trip(String id, String destination, ...)
public class Trip {

    private String id;
    private String destination;   // destino da viagem
    private String traveler;      // nome do viajante
    private LocalDate startDate;  // data de ida
    private LocalDate endDate;    // data de volta
    private double budget;        // orçamento em USD
    private String status;        // PLANNED, IN_PROGRESS, COMPLETED

    // Método de fábrica: cria uma Trip nova com ID gerado automaticamente
    // "static" significa que você chama sem criar um objeto: Trip.create(...)
    public static Trip create(String destination, String traveler,
                               LocalDate startDate, LocalDate endDate,
                               double budget) {
        Trip trip = new Trip();
        trip.setId(UUID.randomUUID().toString());  // gera um ID único
        trip.setDestination(destination);
        trip.setTraveler(traveler);
        trip.setStartDate(startDate);
        trip.setEndDate(endDate);
        trip.setBudget(budget);
        trip.setStatus("PLANNED");
        return trip;
    }
}
