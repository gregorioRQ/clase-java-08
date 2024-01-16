/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import modelos.Preguntas;
import modelos.Respuestas;
import modelos.Veterinarios;
import modelos.EstVeterinarios;
import modelos.Mascota;

/**
 *
 * @author PC
 */
public class Controllers {
    PersistenceController ctrl = new PersistenceController();
    
    //---PREGUNTAS
    public void crearPreguntas(Preguntas pregs){
        ctrl.crearPreguntas(pregs);
    }
    
    public void editarPreguntas(Preguntas pregs){
        ctrl.editarPreguntas(pregs);
    }
    
    public void eliminarPreguntas(int id){
        ctrl.eliminarPreguntas(id);
    }
    
    //---RESPUESTAS
    public void crearRespuesta(Respuestas r){
        ctrl.crearRespuesta(r);
    }
    
    public void editarRespuesta(Respuestas r){
        ctrl.editarRespuestas(r);
    }
    
    public void eliminarRespuesta(int id){
        ctrl.eliminarRespusta(id);
    }
    
    //---VETERINARIO
    public void crearVeterinario(Veterinarios v){
        ctrl.crearVeterinario(v);
    }
    
    public void editarVeterinario(Veterinarios v){
        ctrl.editarVeterinario(v);
    }
    
    public void eliminarVeterinario(int id){
        ctrl.eliminarVeterinario(id);
    }
    
    //---MASCOTA
    public void crearMascota(Mascota m){
        ctrl.crearMascota(m);
    }
    public void editarMascota(Mascota m){
        ctrl.editarMascota(m);
    }
    public void elimitarMascota(int id){
        ctrl.eliminarMascota(id);
    }
    
    
    //---EstVeterinario
    public void crearEvet(EstVeterinarios e){
        ctrl.crearEstVet(e);
    }
    
    public void editarEvet(EstVeterinarios e){
        ctrl.editarEstVet(e);
    }
    
    public void eliminarEvet(int id){
        ctrl.eliminarEstVet(id);
    }
}


