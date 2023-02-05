package mx.com.gm.repositorio;

import java.util.List;
import java.util.Optional;
import mx.com.gm.dao.UsuarioDao;
import mx.com.gm.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositorio {
    @Autowired
     private UsuarioDao usuarioDao;
    public List<Usuario> getAll(){
        return (List<Usuario>) usuarioDao.findAll();
    }
    public Optional<Usuario> getUsuario(int id_usuarios){
     return usuarioDao.findById(id_usuarios);
    }
    public Usuario save(Usuario usuario){
        return usuarioDao.save(usuario);
    }
    
    public void Delete(int id_usuario){
            usuarioDao.deleteById(id_usuario);
    }
}
