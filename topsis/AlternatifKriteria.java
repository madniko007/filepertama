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
public class AlternatifKriteria {
    Alternatif alternatif;
    Kriteria kriteria;
    int value;
    
    AlternatifKriteria(Alternatif alternatif, Kriteria kriteria, int value){
        this.alternatif = alternatif ;
        this.kriteria = kriteria;
        this.value = value;
    }
}
