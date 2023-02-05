package mx.com.gm.repositorio;

import java.util.List;
import java.util.Optional;
import mx.com.gm.dao.RegistroDao;
import mx.com.gm.domain.Registro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegistroRepositorio {

    @Autowired
    private RegistroDao registroDao;

    public List<Registro> getAll() {
        return (List<Registro>) registroDao.findAll();
    }

    public Optional<Registro> getRegistro(int id_registro) {
        return registroDao.findById(id_registro);
    }

    public Registro save(Registro registro) {
        return registroDao.save(registro);
    }

    public void Delete(int id_registro) {
        registroDao.deleteById(id_registro);
    }
}
