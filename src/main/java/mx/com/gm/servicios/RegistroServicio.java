package mx.com.gm.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import mx.com.gm.domain.Registro;
import mx.com.gm.repositorio.RegistroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegistroServicio {
    @Autowired
    
    private RegistroRepositorio registroRepositorio;
    
    public List<Registro> getAll(){
        return registroRepositorio.getAll();
    }
    
    public Optional<Registro> getRegistro(int id_registro){
        return registroRepositorio.getRegistro(id_registro);
    }
    public ResponseEntity<?> save(Registro registro){
        Map<String,Object> response= new HashMap<>();
        try{
            Registro nuevoRegistro = registroRepositorio.save(registro);
            response.put("mensaje","el nuevo registro "+nuevoRegistro.getNombre()+" ha sido creado con exito");
            response.put("registro", nuevoRegistro);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
        }catch(Exception e){
            response.put("mensaje", "Error al insertar el registro en la base de datos");
            response.put("Error",e.getMessage()+":"+e.getCause().getMessage());
            return new ResponseEntity<Map<String ,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> update(int id_registro, Registro registro){
        Map<String ,Object>response=new HashMap<>();
        try{
            Registro registroActual = registroRepositorio.getRegistro(id_registro).orElse(new Registro()); 
            registroActual.setDocumento(registro.getDocumento());
            registroActual.setNombre(registro.getNombre());
            registroActual.setDireccion(registro.getDireccion());
            registroActual.setTelefono(registro.getTelefono());
            registroActual.setGestion(registro.getGestion());
            registroActual.setCanal(registro.getCanal());
            registroActual.setObservaciones(registro.getObservaciones());
            
            Registro nuevoRegistro=registroRepositorio.save(registroActual);
            response.put("mensaje","el registro"+nuevoRegistro.getNombre()+"ha sido actualizado con exito");
            response.put("Registro",nuevoRegistro);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
               
        }
        catch(Exception e){
            response.put("mensaje", "error al actualizar el registro");
            response.put("mensaje", e.getMessage()+":"+e.getCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> findOne(int id_registro){
        Map<String,Object> response=new HashMap<>();
        try{
            Registro registro=registroRepositorio.getRegistro(id_registro).orElse(new Registro());
            return new ResponseEntity<Registro>(registro, HttpStatus.OK);
        }catch(Exception e){
            response.put("mensaje","error al realizar la consulta en la base de datos");
            response.put("Erro",e.getMessage()+":"+e.getCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        
    }
    public ResponseEntity<?> delete(int id_registro){
        Map<String,Object> response =new HashMap<>();
        try{
            Registro registro =registroRepositorio.getRegistro(id_registro).orElse(new Registro());
            registroRepositorio.Delete(id_registro);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch(Exception e){
            response.put("mensaje", "el registro no pudo ser eliminado");
            response.put("mensaje",e.getMessage()+e.getCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }
}
