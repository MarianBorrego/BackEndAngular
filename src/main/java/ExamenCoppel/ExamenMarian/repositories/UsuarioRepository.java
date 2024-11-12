package ExamenCoppel.ExamenMarian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ExamenCoppel.ExamenMarian.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

}
