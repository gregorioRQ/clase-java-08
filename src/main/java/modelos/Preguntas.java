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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author PC
 */    

@Entity
@Table(name="Preguntas")
public class Preguntas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic
    private String Enunciado;
    @OneToMany
    private List<Respuestas> listaRespuestas;

    public Preguntas() {
    }

    public Preguntas(int id, String Enunciado, List<Respuestas> listaRespuestas) {
        this.id = id;
        this.Enunciado = Enunciado;
        this.listaRespuestas = listaRespuestas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEnunciado() {
        return Enunciado;
    }

    public void setEnunciado(String Enunciado) {
        this.Enunciado = Enunciado;
    }

    public List<Respuestas> getListaRespuestas() {
        return listaRespuestas;
    }

    public void setListaRespuestas(List<Respuestas> listaRespuestas) {
        this.listaRespuestas = listaRespuestas;
    }

    @Override
    public String toString() {
        return "Preguntas{" + "id=" + id + ", Enunciado=" + Enunciado + ", listaRespuestas=" + listaRespuestas + '}';
    }


    
    
}
