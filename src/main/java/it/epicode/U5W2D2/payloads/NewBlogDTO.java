package it.epicode.U5W2D2.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record NewBlogDTO(
        String categoria,
        @NotNull(message = "Titolo obbligatorio")
        @Size(min = 3, message = "Il titolo deve essere almeno di 15 caratteri")
        String titolo,
        @NotNull(message = "Cover obbligatoria")
        String cover,
        @NotNull
        String contenuto,
        int tempoDiLettura,
        String avatar,
        List<String> errorsList
) {
}
