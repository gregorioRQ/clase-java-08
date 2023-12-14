/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cadena.veterinarias;

import java.util.ArrayList;
import java.util.Date;
import modelos.EstVeterinarios;
import modelos.Mascota;
import controladores.Controllers;
/**
 *
 * @author PC
 */
public class CadenaVeterinarias {

    public static void main(String[] args) {
        
        Controllers control = new Controllers();
        
        Mascota m1 = new Mascota(2, "patricio", , "perro");
        control.crearMascota(m1);
        
       ArrayList<Mascota>listamascotas = new ArrayList();
       EstVeterinarios ev1 = new EstVeterinarios(3, "Garra", "lun-mier", "7am-20pm", listaMascotas, listaVeterinarios);
    }
}
