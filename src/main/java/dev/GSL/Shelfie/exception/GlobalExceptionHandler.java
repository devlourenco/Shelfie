package dev.GSL.Shelfie.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontrado.class)
    public ResponseEntity<ErroResponse> handleNotFound(RecursoNaoEncontrado ex, HttpServletRequest request) {

        ErroResponse erro = new ErroResponse(
                LocalDateTime.now()
                ,404,
                "Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(RegraDeNegocio.class)
    public ResponseEntity<ErroResponse> handleBusiness(RegraDeNegocio ex, HttpServletRequest request){

        ErroResponse erro = new ErroResponse(
                LocalDateTime.now()
                ,409,
                "Conflict",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(409).body(erro);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResponse> handleGeneric(RuntimeException ex, HttpServletRequest request){

        ErroResponse erro = new ErroResponse(
                LocalDateTime.now()
                ,400,
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(400).body(erro);
    }


}
