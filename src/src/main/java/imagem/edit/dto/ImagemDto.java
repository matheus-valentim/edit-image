package imagem.edit.dto;


import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ImagemDto(@NotBlank String nome,@NotNull ObjectNode transformations) {
}
