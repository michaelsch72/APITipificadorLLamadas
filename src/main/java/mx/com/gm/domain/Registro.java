package mx.com.gm.domain;

import javax.persistence.Entity;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "registro")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_registro;
    private String documento;
    private String nombre;
    private String direccion;
    private String telefono;
    private String gestion;
    private String canal;
    private  String Observaciones;
    
    
}
