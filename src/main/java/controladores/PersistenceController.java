/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.EstVeterinarios;
import modelos.Mascota;
import modelos.Preguntas;
import modelos.Veterinarios;
import modelos.Respuestas;
/**
 *
 * @author PC
 */
public class PersistenceController {
    PreguntasJpaController preguntasjpa = new PreguntasJpaController();
    EstVeterinariosJpaController estvetjpa = new EstVeterinariosJpaController();
    MascotaJpaController masjpa = new MascotaJpaController();
    VeterinariosJpaController vetjpa = new VeterinariosJpaController();
    RespuestasJpaController resjpa = new RespuestasJpaController();
    
    //---PREGUNTAS
    
    void crearPreguntas(Preguntas pregs) {
        preguntasjpa.create(pregs);
    }

    void editarPreguntas(Preguntas pregs) {
        try {
            preguntasjpa.edit(pregs);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void eliminarPreguntas(int id) {
        try {
            preguntasjpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //---RESPUESTAS
    public void crearRespuesta(Respuestas r){
        resjpa.create(r);
    }
    
    public void editarRespuestas(Respuestas r){
        try {
            resjpa.edit(r);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarRespusta(int id){
        try {
            resjpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //---ESTABLECIMIENTOS VET
    
    public void crearEstVet(EstVeterinarios ev){
        estvetjpa.create(ev);
    }
    
    public void editarEstVet(EstVeterinarios ev){
        try {
            estvetjpa.edit(ev);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarEstVet(int id){
        try {
            estvetjpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //---MASCOTA
    public void crearMascota(Mascota m){
        masjpa.create(m);
    }
    
    public void editarMascota(Mascota m){
        try {
           masjpa.edit(m);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarMascota(int id){
        try {
           masjpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //---VETERINARIO
    public void crearVeterinario(Veterinarios v){
        vetjpa.create(v);
    }
    
    public void editarVeterinario(Veterinarios v){
        try {
            vetjpa.edit(v);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarVeterinario(int id){
        try {
            vetjpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
