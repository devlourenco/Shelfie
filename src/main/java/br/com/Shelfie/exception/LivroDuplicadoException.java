package br.com.Shelfie.exception;

public class LivroDuplicadoException extends RuntimeException {

    public LivroDuplicadoException() {
        super("O livro já está cadastrado.");
    }

    public LivroDuplicadoException(String message) {
        super(message);
    }
}
