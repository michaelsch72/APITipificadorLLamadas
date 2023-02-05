package mx.com.gm.dao;

import mx.com.gm.domain.Registro;
import org.springframework.data.repository.CrudRepository;

public interface RegistroDao extends CrudRepository<Registro,Integer> {
    
}
