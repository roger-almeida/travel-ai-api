package com.travel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

// DTO = Data Transfer Object
// Responsabilidade: representar o JSON que chega no POST /trips
// Ele NÃO é o modelo de domínio (Trip) — é só o contrato da entrada HTTP
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTripRequest {

    // @NotBlank: rejeita null, string vazia "" e string só com espaços "   "
    // A mensagem aparece no JSON de erro quando a validação falha
    @NotBlank(message = "Destination is required")
    private String destination;

    @NotBlank(message = "Traveler name is required")
    private String traveler;

    // @NotNull: rejeita null, mas aceita qualquer valor presente
    // Usado para tipos que não são String (LocalDate, Integer, etc.)
    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    // @Positive: o valor deve ser maior que zero (0.01, 100.0, etc.)
    // Rejeita zero e negativos — faz sentido para orçamento
    @Positive(message = "Budget must be greater than zero")
    private double budget;
}
