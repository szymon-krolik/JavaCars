/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cars;

import java.io.Serializable;


public abstract class Pojazd implements Serializable{
    protected String marka;
    protected String model;
    protected int przebieg;
    protected int rokProdukcji;
    
    public Pojazd(String m, String md, int p, int r)
    {
        this.marka = m;
        this.model = md;
        this.przebieg = p;
        this.rokProdukcji = r;
    }
    
    public Pojazd(String linia)
    {
        String[] parts = linia.split("`");
        if(parts.length != 4)
        {
            throw new IllegalArgumentException("Line " + linia + " is not valid");
        }
        
        int przebieg = 0;
        int rok = 0;
        
        try{
            przebieg = Integer.parseInt(parts[2]);
            rok = Integer.parseInt(parts[3]);
        } catch(NumberFormatException e)
        {
            przebieg = -1;
            rok = -1;
        }
        
        if(przebieg < 0 || rok < 1886 || rok > 2020)
        {
           throw new IllegalArgumentException("Line " + linia + " is not valid");
        }
        
        this.marka = parts[0];
        this.model = parts[1];
        this.przebieg = przebieg;
        this.rokProdukcji = rok;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public int getPrzebieg() {
        return przebieg;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrzebieg(int przebieg) {
        this.przebieg = przebieg;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }
    
}
