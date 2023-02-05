package mx.com.gm.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import mx.com.gm.domain.Usuario;
import mx.com.gm.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> getAll() {
        return usuarioRepositorio.getAll();
    }

    public Optional<Usuario> getUsuario(int id_usuarios) {
        return usuarioRepositorio.getUsuario(id_usuarios);
    }

    public ResponseEntity<?> save(Usuario usuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario nuevoUsuario = usuarioRepositorio.save(usuario);
            response.put("mensaje", "El usuario " + nuevoUsuario.getNombre() + " ha sido creado con éxito");
            response.put("Usuario", nuevoUsuario);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage() + ": " + e.getCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> update(int id, Usuario usuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println(usuario.getNombre());
            Usuario usuarioActual = usuarioRepositorio.getUsuario(id).orElse(new Usuario());
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setCorreo(usuario.getCorreo());
            usuarioActual.setPassword(usuario.getPassword());

            Usuario clienteNew = usuarioRepositorio.save(usuarioActual);
            response.put("mensaje", "El Usuario " + clienteNew.getNombre() + " ha sido actualizado con éxito");
            response.put("Usuario", clienteNew);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage() + ": " + e.getCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<?> findOne(int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = usuarioRepositorio.getUsuario(id).orElse(new Usuario());
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage() + ": " + e.getCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<?> Delete(int id_usuarios) {
        Map<String, Object> response = new HashMap();
        try {
            Usuario usuario = usuarioRepositorio.getUsuario(id_usuarios).orElse(new Usuario());
            usuarioRepositorio.Delete(id_usuarios);
            response.put("mensaje", "El usuario " + usuario.getNombre() + " fue eliminado con éxito");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", "El registro no pudo ser eliminado, porque no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
