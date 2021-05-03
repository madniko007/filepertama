/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topsis;

/**
 *
 * @author nicholas
 */
public class Kriteria {
    String kriteria;
    int bobot;
    boolean benefit;
    String kode;
    
    public Kriteria(String kriteria, int bobot, boolean benefit, String kode){
        this.kriteria = kriteria;
        this.bobot = bobot;
        this.benefit = benefit;
        this.kode = kode;
    }
    
}
