package ExamenCoppel.ExamenMarian.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ExamenCoppel.ExamenMarian.models.UsuarioModel;
import ExamenCoppel.ExamenMarian.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios
    @GetMapping
    public List<UsuarioModel> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    // Crear un nuevo usuario
    @PostMapping
    public UsuarioModel crearUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public UsuarioModel obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Actualizar un usuario
    @PutMapping("/{id}")
    public UsuarioModel actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuarioDetalles) {
        UsuarioModel usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setNombre(usuarioDetalles.getNombre());
            usuario.setEmail(usuarioDetalles.getEmail());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}