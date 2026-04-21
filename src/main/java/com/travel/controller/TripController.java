package com.travel.controller;

import com.travel.dto.CreateTripRequest;
import com.travel.dto.TripResponse;
import com.travel.model.Trip;
import com.travel.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    // GET /trips → retorna todas as viagens como lista de TripResponse
    // .stream().map(TripResponse::from): converte cada Trip da lista em TripResponse
    // TripResponse::from é uma "method reference" — equivale a trip -> TripResponse.from(trip)
    @GetMapping
    public ResponseEntity<List<TripResponse>> getAllTrips() {
        List<TripResponse> response = tripService.findAll()
                .stream()
                .map(TripResponse::from)
                .toList();
        return ResponseEntity.ok(response);
    }

    // GET /trips/{id} → busca uma viagem pelo ID
    // Se não existir, TripService lança TripNotFoundException
    // GlobalExceptionHandler captura e devolve HTTP 404 automaticamente
    // Por isso o método fica simples: sem try/catch, sem Optional
    @GetMapping("/{id}")
    public ResponseEntity<TripResponse> getTripById(@PathVariable String id) {
        Trip trip = tripService.findById(id);
        return ResponseEntity.ok(TripResponse.from(trip));
    }

    // POST /trips → cria uma nova viagem
    // @Valid: ativa a validação do DTO — sem essa anotação, @NotBlank/@Positive são ignorados
    // @RequestBody: deserializa o JSON recebido para um objeto CreateTripRequest
    // Se algum campo inválido, MethodArgumentNotValidException é lançada → GlobalExceptionHandler retorna 400
    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@Valid @RequestBody CreateTripRequest request) {
        Trip trip = tripService.create(
                request.getDestination(),
                request.getTraveler(),
                request.getStartDate(),
                request.getEndDate(),
                request.getBudget()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(TripResponse.from(trip));
    }
}
