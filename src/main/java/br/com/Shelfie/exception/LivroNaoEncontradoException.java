package br.com.Shelfie.exception;

public class LivroNaoEncontradoException extends RuntimeException {

    public LivroNaoEncontradoException() {
        super("Livro não encontrado.");
    }

    public LivroNaoEncontradoException(String message) {
        super(message);
    }


}
