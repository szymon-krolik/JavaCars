/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cars;

public class Osobowy extends Pojazd{
    public Osobowy(String m, String md, int p, int r)
    {
        super(m, md, p, r);
    }
    
    public Osobowy(String linia)
    {
        super(linia);
    }
    
    public String toString()
    {
        return "Samochód osobowy " + this.marka + " " + this.model + ", rok produkcji " + this.rokProdukcji; 
    }
}
