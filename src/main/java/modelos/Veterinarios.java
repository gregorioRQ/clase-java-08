/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PC
 */
@Entity
@Table(name="Veterinario")
public class Veterinarios implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
    private String Nombre;
    @Basic
    private String Especialidad;
    @Temporal(TemporalType.DATE)
    private Date FechaNac;
    @OneToMany
    @JoinColumn(name="Veterinario_Id", referencedColumnName="id")
    private List<Mascota> listaMascotas;

    public Veterinarios() {
    }

    public Veterinarios(int id, String Nombre, String Especialidad, Date FechaNac, List<Mascota> listaMascotas) {
        this.id = id;
        this.Nombre = Nombre;
        this.Especialidad = Especialidad;
        this.FechaNac = FechaNac;
        this.listaMascotas = listaMascotas;
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

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }

    public Date getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(Date FechaNac) {
        this.FechaNac = FechaNac;
    }

    public List<Mascota> getListaMascotas() {
        return listaMascotas;
    }

    public void setListaMascotas(List<Mascota> listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    @Override
    public String toString() {
        return "Veterinarios{" + "id=" + id + ", Nombre=" + Nombre + ", Especialidad=" + Especialidad + ", FechaNac=" + FechaNac + ", listaMascotas=" + listaMascotas + '}';
    }

    
    
}
