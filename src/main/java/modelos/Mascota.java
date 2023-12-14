/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PC
 */
@Entity
@Table(name="mascota")
public class Mascota implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
    private String Nombre;
    @Temporal(TemporalType.DATE)
    private Date FechaNac;
    @Basic
    private String TipoAnimal;

    public Mascota() {
    }

    public Mascota(int id, String Nombre, Date FechaNac, String TipoAnimal) {
        this.id = id;
        this.Nombre = Nombre;
        this.FechaNac = FechaNac;
        this.TipoAnimal = TipoAnimal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Date getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(Date FechaNac) {
        this.FechaNac = FechaNac;
    }

    public String getTipoAnimal() {
        return TipoAnimal;
    }

    public void setTipoAnimal(String TipoAnimal) {
        this.TipoAnimal = TipoAnimal;
    }

    @Override
    public String toString() {
        return "Mascota{" + "id=" + id + ", Nombre=" + Nombre + ", FechaNac=" + FechaNac + ", TipoAnimal=" + TipoAnimal + '}';
    }

   
}
