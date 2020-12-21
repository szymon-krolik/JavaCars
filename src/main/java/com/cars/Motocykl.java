/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cars;

public class Motocykl extends Pojazd{

    public Motocykl(String m, String md, int p, int r) {
        super(m, md, p, r);
    }

    public Motocykl(String linia) {
        super(linia);
    }
    
    public String toString()
    {
        return "Motocykl " + this.marka + " " + this.model;
    }
}
