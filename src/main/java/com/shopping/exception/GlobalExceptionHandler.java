/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        GeneralResponse respuesta = GeneralResponse.builder()
                .numeroLinea(0)
                .tipoMensajeRespuesta("EX")
                .tipoSeveridad("1")
                .codigoMensajeRespuesta("1000")
                .descripcionMensajeRespuesta("Validation error occurred")
                .detalleTecnicoRespuesta(errors.toString())
                .build();

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GeneralResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        GeneralResponse respuesta = GeneralResponse.builder()
                .numeroLinea(0)
                .tipoMensajeRespuesta("EX")
                .tipoSeveridad("1")
                .codigoMensajeRespuesta("1001")
                .descripcionMensajeRespuesta(ex.getMessage())
                .detalleTecnicoRespuesta("Invalid argument provided")
                .build();

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    // Puedes agregar más manejadores de excepciones aquí...
}
