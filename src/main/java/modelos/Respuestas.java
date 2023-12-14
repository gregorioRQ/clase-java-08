/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author PC
 */
@Entity
@Table(name="respuestas")
public class Respuestas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic
    private String Respuestas;
    @ManyToOne
    @JoinColumn(name="pregunta_id", referencedColumnName = "id")
    private Preguntas pregunta;

    public Respuestas() {
    }

    public Respuestas(int id, String Respuestas, Preguntas pregunta) {
        this.id = id;
        this.Respuestas = Respuestas;
        this.pregunta = pregunta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public String getRespuestas() {
        return Respuestas;
    }

    public void setRespuestas(String Respuestas) {
        this.Respuestas = Respuestas;
    }

    public Preguntas getPregunta() {
        return pregunta;
    }

    public void setPregunta(Preguntas pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public String toString() {
        return "Respuestas{" + "id=" + id + ", Respuestas=" + Respuestas + ", pregunta=" + pregunta + '}';
    }

}
