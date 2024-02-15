package it.epicode.U5W2D2.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("ERROR 404 elemento: " + id + " non trovato!");
    }
}