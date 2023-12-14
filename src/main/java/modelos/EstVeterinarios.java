/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author PC
 */
@Entity
@Table(name="EstVet")
public class EstVeterinarios implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
    private String Nombre;
    @Basic
    private String DiasAtencion;
    @Basic
    private String HorariosAtencion;
    @OneToMany
    @JoinColumn(name="EstVet_id", referencedColumnName="id")
    private List<Mascota> listaMascotas;
    @OneToMany
    @JoinColumn(name="EstVet_id", referencedColumnName = "id")
    private List<Veterinarios> listaVeterinarios;

    public EstVeterinarios() {
    }

    public EstVeterinarios(int id, String Nombre, String DiasAtencion, String HorariosAtencion, List<Mascota> listaMascotas, List<Veterinarios> listaVeterinarios) {
        this.id = id;
        this.Nombre = Nombre;
        this.DiasAtencion = DiasAtencion;
        this.HorariosAtencion = HorariosAtencion;
        this.listaMascotas = listaMascotas;
        this.listaVeterinarios = listaVeterinarios;
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

    public String getDiasAtencion() {
        return DiasAtencion;
    }

    public void setDiasAtencion(String DiasAtencion) {
        this.DiasAtencion = DiasAtencion;
    }

    public String getHorariosAtencion() {
        return HorariosAtencion;
    }

    public void setHorariosAtencion(String HorariosAtencion) {
        this.HorariosAtencion = HorariosAtencion;
    }

    public List<Mascota> getListaMascotas() {
        return listaMascotas;
    }

    public void setListaMascotas(List<Mascota> listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    public List<Veterinarios> getListaVeterinarios() {
        return listaVeterinarios;
    }

    public void setListaVeterinarios(List<Veterinarios> listaVeterinarios) {
        this.listaVeterinarios = listaVeterinarios;
    }

    @Override
    public String toString() {
        return "EstVeterinarios{" + "id=" + id + ", Nombre=" + Nombre + ", DiasAtencion=" + DiasAtencion + ", HorariosAtencion=" + HorariosAtencion + ", listaMascotas=" + listaMascotas + ", listaVeterinarios=" + listaVeterinarios + '}';
    }

  
    
    
}
