package br.com.Shelfie.infra;

import br.com.Shelfie.exception.LivroDuplicadoException;
import br.com.Shelfie.exception.LivroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler{

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<String> LivroNaoEncontradoHandler(LivroNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(LivroDuplicadoException.class)
    public ResponseEntity<String> LivroDuplicado(LivroDuplicadoException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }


}
