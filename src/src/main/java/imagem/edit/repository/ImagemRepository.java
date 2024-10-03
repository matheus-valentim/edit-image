package imagem.edit.repository;

import imagem.edit.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagemRepository extends JpaRepository<ImageModel, UUID> {
}
