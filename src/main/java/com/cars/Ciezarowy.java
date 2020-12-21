/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cars;

public class Ciezarowy extends Pojazd{

    public Ciezarowy(String m, String md, int p, int r) {
        super(m, md, p, r);
    }

    public Ciezarowy(String linia) {
        super(linia);
    }
    
    public String toString()
    {
        return "Samochód ciężarowy " + this.marka + " " + this.model + ", przebieg " + this.przebieg;
    }
}
