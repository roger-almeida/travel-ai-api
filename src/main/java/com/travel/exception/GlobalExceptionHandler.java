package com.travel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

// @RestControllerAdvice: intercepta exceções lançadas em qualquer @RestController
// Em vez de try/catch espalhado por todos os endpoints, tudo cai aqui
// "Advice" = conselheiro — ele "aconselha" o Spring sobre o que fazer com cada exceção
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura TripNotFoundException e devolve HTTP 404 com JSON de erro
    // @ExceptionHandler diz: "quando essa exceção ocorrer, execute este método"
    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTripNotFound(TripNotFoundException ex) {
        // Map.of() cria um mapa imutável com 1 entrada — vira {"error": "Trip not found with id: xyz"}
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

    // Captura erros de validação do @Valid (campos @NotBlank, @NotNull, @Positive etc.)
    // MethodArgumentNotValidException é lançada automaticamente pelo Spring
    // quando um campo anotado viola a regra de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {

        // getBindingResult() contém todos os erros de validação da requisição
        // getFieldErrors() retorna a lista de campos que falharam
        // Para cada erro, montamos "nomeDoCampo: mensagem" (ex: "destination: Destination is required")
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();

        // HTTP 400 Bad Request: o cliente enviou dados inválidos
        // {"errors": ["destination: Destination is required", "budget: Budget must be greater than zero"]}
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("errors", errors));
    }
}
