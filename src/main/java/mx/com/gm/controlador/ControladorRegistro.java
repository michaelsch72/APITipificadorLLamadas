package mx.com.gm.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import mx.com.gm.domain.Registro;
import mx.com.gm.servicios.RegistroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registro")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ControladorRegistro {

    @Autowired
    private RegistroServicio registroServicio;

    @GetMapping("/all")
    public List<Registro> getAll() {
        return registroServicio.getAll();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Registro registro, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "Error en el campo '" + err.getField() + "'. Mensaje de error: " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("mensaje", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        } else {
            return registroServicio.save(registro);
        }

    }

    @PutMapping("/actualizar/{id_registro}")
    public ResponseEntity<?> update(@Valid @RequestBody Registro registro, BindingResult result, @PathVariable("id_registro") int id_Registro) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "Error en el campo '" + err.getField() + "'. Mensaje de error: " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("mensaje", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        } else {
            return registroServicio.update(id_Registro, registro);
        }

    }
    @GetMapping("/all/{id_registro}")
    public ResponseEntity<?> findOne(@PathVariable("id_registro")int id_registro){
       return registroServicio.findOne(id_registro);
    }
    @DeleteMapping("/eliminar/{id_registro}")
    public ResponseEntity<?> delete(@PathVariable int id_registro){
        return registroServicio.delete(id_registro);
    }
    
 
 


}
