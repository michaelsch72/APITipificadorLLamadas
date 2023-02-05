package mx.com.gm.controlador;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import mx.com.gm.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import mx.com.gm.servicios.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Usuario")
public class ControladorUsuario {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/all")
    public List<Usuario> getAll() {
        return usuarioServicio.getAll();
    }

    @GetMapping("{/id_usuarios}")
    public Optional<Usuario> getUsuarios(@PathVariable("id_usuarios") int id_usuario) {
        return usuarioServicio.getUsuario(id_usuario);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Usuario usuario, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "Error en el campo '" + err.getField() + "'. Mensaje de error: " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("mensaje", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        } else {
            return usuarioServicio.save(usuario);
        }
    }

    @PutMapping("/actualizar/{id_usuarios}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable("id_usuarios") int id_usuario) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "Error en el campo '" + err.getField() + "'. Mensaje de error: " + err.getDefaultMessage();
			}).collect(Collectors.toList());

			response.put("mensaje", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}else {
			return usuarioServicio.update(id_usuario, usuario);
		}
	}
        @GetMapping("/all/{id_usuarios}")
	public ResponseEntity<?> findOne(@PathVariable("id_usuarios") int id) {
		return usuarioServicio.findOne(id);
	}
        
	@DeleteMapping("/eliminar/{id_usuarios}")
	public ResponseEntity<?> deleteUsuario(@PathVariable("id_usuarios") int id_usuario){
		return usuarioServicio.Delete(id_usuario);
	}
}
	