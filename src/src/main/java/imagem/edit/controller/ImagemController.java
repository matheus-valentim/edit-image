package imagem.edit.controller;

import imagem.edit.Services.ImagemService;
import imagem.edit.dto.ImagemDto;
import imagem.edit.repository.ImagemRepository;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.function.EntityResponse;

@Controller
public class ImagemController {

    @Autowired
    ImagemRepository imagemRepository;

    @PostMapping("/imagem")
    public ResponseEntity<Object> getImagem(@RequestBody @Valid ImagemDto imagemDto){
        return ResponseEntity.status(HttpStatus.OK).body(ImagemService.cloudnaryConfig(imagemDto));
    }
}
