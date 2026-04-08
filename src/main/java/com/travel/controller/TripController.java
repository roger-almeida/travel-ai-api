package com.travel.controller;

import com.travel.model.Trip;
import com.travel.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// @RestController = @Controller + @ResponseBody
// Significa: "essa classe responde requisições HTTP e retorna JSON automaticamente"
@RestController
@RequestMapping("/trips")  // todas as rotas dessa classe começam com /trips
public class TripController {

    // @Autowired: o Spring injeta automaticamente uma instância de TripService
    // Você não precisa fazer "new TripService()" — o Spring gerencia isso
    @Autowired
    private TripService tripService;

    // GET /trips → retorna todas as viagens
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.findAll();
        return ResponseEntity.ok(trips);  // HTTP 200 + JSON com a lista
    }

    // GET /trips/{id} → retorna uma viagem específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable String id) {
        return tripService.findById(id)
                .map(trip -> ResponseEntity.ok(trip))       // encontrou: HTTP 200
                .orElse(ResponseEntity.notFound().build()); // não encontrou: HTTP 404
    }

    // POST /trips → cria uma nova viagem
    // @RequestBody: lê o JSON que veio no corpo da requisição e transforma em Map
    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Map<String, String> body) {
        Trip trip = tripService.create(
                body.get("destination"),
                body.get("traveler"),
                LocalDate.parse(body.get("startDate")),  // "2024-06-15" → LocalDate
                LocalDate.parse(body.get("endDate")),
                Double.parseDouble(body.get("budget"))
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(trip); // HTTP 201
    }
}
