package com.travel.exception;

// Exceção customizada para quando uma viagem não é encontrada pelo ID
// Estende RuntimeException: não precisa ser declarada no método com "throws"
// (diferente de Exception checked, que obrigaria try/catch em todo lugar)
public class TripNotFoundException extends RuntimeException {

    // Construtor recebe o ID que não foi encontrado e gera uma mensagem clara
    public TripNotFoundException(String id) {
        super("Trip not found with id: " + id);
    }
}
