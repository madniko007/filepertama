/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topsis;

import java.util.*;


/**
 *
 * @author nicholas
 */
public class topsis {
    
    List<Alternatif> listAlnternatif = new ArrayList<Alternatif>();
    List<Kriteria> listKriteria = new ArrayList<Kriteria>();
    
    List<ArrayList<AlternatifKriteria>> ListAlternatifKriteria = new ArrayList<ArrayList<AlternatifKriteria>>();
    
    double[][] NormalizedAlternatifKriteria;
    double [][] TerbobotkanAlternatifKriteria;
    
    double [][] solusipositifnegatif;
    
    double [] datapositif;
    double [] datanegatif;
    
    double [] datapreferensi;

    Double [] rankedpreferensi;
    
    int inputKriteria[][];
    double pembagi[];
    
    double ternormalisasi[][];
    
    public topsis(){
                
        this.listAlnternatif.add(new Alternatif("Alvin","A1"));
        this.listAlnternatif.add(new Alternatif("Nugroho","A2"));
        this.listAlnternatif.add(new Alternatif("Yafis","A3"));
        this.listAlnternatif.add(new Alternatif("Shinta","A4"));
        this.listAlnternatif.add(new Alternatif("Nurmala","A5"));
        this.listAlnternatif.add(new Alternatif("Dinan","A6"));
        this.listAlnternatif.add(new Alternatif("Shelfi","A7"));
        
        this.listKriteria.add(new Kriteria("Tes Pengetahuan", 5, true, "C1"));
        this.listKriteria.add(new Kriteria("Praktek Instalasi Jaringan", 4, true, "C2"));
        this.listKriteria.add(new Kriteria("Tes Kepribadian", 4, true, "C3"));
        this.listKriteria.add(new Kriteria("Tes Manajemen Server", 3, true, "C4"));

        this.inputKriteria = new int[this.listAlnternatif.size()][this.listKriteria.size()];
        
        printAlternatif();
        printKriteria();

        
        int [] [] listdata = new int [this.listAlnternatif.size()][this.listKriteria.size()];
        listdata[0][0] = 4;
        listdata[0][1] = 5;
        listdata[0][2] = 4;
        listdata[0][3] = 4;

        listdata[1][0] = 5;
        listdata[1][1] = 4;
        listdata[1][2] = 4;
        listdata[1][3] = 5;
        
        listdata[2][0] = 4;
        listdata[2][1] = 4;
        listdata[2][2] = 4;
        listdata[2][3] = 4;
        
        listdata[3][0] = 4;
        listdata[3][1] = 5;
        listdata[3][2] = 5;
        listdata[3][3] = 4;
        
        listdata[4][0] = 5;
        listdata[4][1] = 4;
        listdata[4][2] = 4;
        listdata[4][3] = 4;

        listdata[5][0] = 5;
        listdata[5][1] = 4;
        listdata[5][2] = 4;
        listdata[5][3] = 4;
        
        listdata[6][0] = 4;
        listdata[6][1] = 5;
        listdata[6][2] = 5;
        listdata[6][3] = 4;
        
        
        for ( int i = 0; i < this.listAlnternatif.size();i++){
            ArrayList<AlternatifKriteria> specAlternatifKriteria = new ArrayList<AlternatifKriteria>();
                        
            for(int j=0;j<this.listKriteria.size();j++){
               specAlternatifKriteria.add(new AlternatifKriteria(this.listAlnternatif.get(i),this.listKriteria.get(j),listdata[i][j]));
//               System.out.println("Loop i="+i+" j="+j+" :"+listdata[i][j]);
            }
            this.ListAlternatifKriteria.add(specAlternatifKriteria);
            
//            System.out.println("Simpan list alternatif kriteria i="+i);
        }
        
        calcPembagi();
        
        normalisasi();
        
        bobotkan();
        
        solusikan();
        
        hitungdata();
        
        hitungpreferensi();
        
        urutkan();
    }
    
    void printAlternatif(){
        int i = 0;
        for(Alternatif a : listAlnternatif){
            i++;
            System.out.println("Alternatif "+i);
            System.out.print("Nama Alternatif : "+a.alternatif);
            System.out.println(", Kode Alternatif : "+a.kode);
            System.out.println();
        }
        System.out.println("Jumlah Alternatif : "+this.listAlnternatif.size());
    }
    
    void printKriteria(){
        int i = 0;
        for(Kriteria a : listKriteria){
            i++;
            System.out.println("Kriteria "+i);
            System.out.print("Kriteria : "+a.kriteria);
            System.out.print(", Bobot : "+a.bobot);
            System.out.print(", Benefit : "+a.benefit);
            System.out.println(", Kode: "+a.kode);
            System.out.println();
        }
        System.out.println("Jumlah Kriteria : "+this.listKriteria.size());
    }
    
    void calcPembagi(){
        this.pembagi = new double[this.listKriteria.size()];
        
        for(int i=0; i<this.listKriteria.size(); i++){
            double temPembagi = 0;
            
            for(int j=0; j<this.listAlnternatif.size(); j++){
                
               temPembagi += Math.pow(this.ListAlternatifKriteria.get(j).get(i).value, 2);
               
            }
            
            this.pembagi[i] = Math.sqrt(temPembagi);
        }
        
        System.out.println("Pembagi : "+Arrays.toString(this.pembagi));
    }
    

    
    public static void main(String[] args){
        topsis topsis = new topsis();
    }
    
    void normalisasi(){
        this.NormalizedAlternatifKriteria = new double[this.listAlnternatif.size()][this.listKriteria.size()];
        
        System.out.println("Normalisasi : ");
        
        for ( int i = 0; i < this.listAlnternatif.size();i++){
            for(int j=0;j<this.listKriteria.size();j++){
               this.NormalizedAlternatifKriteria[i][j] = this.ListAlternatifKriteria.get(i).get(j).value / this.pembagi[j];
//               System.out.println(this.NormalizedAlternatifKriteria[i][j]);
            }
        }
//        System.out.println("\n");
        display(this.NormalizedAlternatifKriteria);
    }
    
    void bobotkan(){
        

        this.TerbobotkanAlternatifKriteria = new double[this.listAlnternatif.size()][this.listKriteria.size()];
        
        System.out.println("Bobot : ");
        for ( int i = 0; i < this.listAlnternatif.size();i++){
            for(int j=0;j<this.listKriteria.size();j++){
               this.TerbobotkanAlternatifKriteria[i][j] = this.NormalizedAlternatifKriteria[i][j] * this.listKriteria.get(j).bobot;
//               System.out.println(this.TerbobotkanAlternatifKriteria[i][j]);
            }
        }
//        System.out.println("\n");
        display(this.TerbobotkanAlternatifKriteria);
    }
    
    
    void display(double[][] Angkanyaa){
        
        for ( int i  = 0 ; i < Angkanyaa.length; i++){
            for ( int j = 0 ; j < Angkanyaa[i].length ; j++){
                System.out.println(Angkanyaa[i][j] + " ");
            }
            System.out.println("\n");
        }
        
    }
    
    void display(double[] Angkanyaa){
        for ( int i  = 0 ; i < Angkanyaa.length; i++){
            System.out.println(Angkanyaa[i] + " ");
        }
        System.out.println("\n");
    }
    
    void display(Double[] Angkanyaa){
        for ( int i  = 0 ; i < Angkanyaa.length; i++){
            System.out.println(Angkanyaa[i] + " ");
        }
        System.out.println("\n");
    }
    
    void solusikan(){
        this.solusipositifnegatif = new double[2][this.listKriteria.size()];
        
        for(int j=0;j<this.listKriteria.size();j++){
            
            Double[] tempArr = new Double[this.listAlnternatif.size()];
            
            for ( int i = 0; i < this.listAlnternatif.size();i++){
                tempArr[i] = this.TerbobotkanAlternatifKriteria[i][j];
            }
            
            this.solusipositifnegatif[0][j] = Collections.max(Arrays.asList(tempArr));
            this.solusipositifnegatif[1][j] =  Collections.min(Arrays.asList(tempArr));
            
        }
        
        System.out.println("SOLUSI POSITIF NEGATIF :");
        display(this.solusipositifnegatif);
        
    }
    
    void hitungdata(){
        this.datapositif = new double[this.listAlnternatif.size()];
        this.datanegatif = new double[this.listAlnternatif.size()];
        
        
        for ( int i = 0; i < this.listAlnternatif.size();i++){
            
            double yangmaudiakar = 0;
            
            for(int j=0;j<this.listKriteria.size();j++){
               yangmaudiakar += Math.pow((this.solusipositifnegatif[0][j] - this.TerbobotkanAlternatifKriteria[i][j]),2);
            }
            
            this.datapositif[i] = Math.sqrt(yangmaudiakar);
            
//            System.out.println("AAAAA\n"+Math.sqrt(yangmaudiakar));
            
            yangmaudiakar = 0;
            for(int j=0;j<this.listKriteria.size();j++){
               yangmaudiakar += Math.pow((this.TerbobotkanAlternatifKriteria[i][j]- this.solusipositifnegatif[1][j] ),2);
            }
            
            this.datanegatif[i] = Math.sqrt(yangmaudiakar);
        }
        System.out.println("Data Positif :");
        display(this.datapositif);
        
        System.out.println("Data Negatif :");
        display(this.datanegatif);
        
    }
    
    void hitungpreferensi(){
        this.datapreferensi = new double[this.listAlnternatif.size()];
        
        for ( int i = 0; i < this.listAlnternatif.size();i++){
            this.datapreferensi[i] = this.datanegatif[i] / (this.datanegatif[i] + this.datapositif[i]);
        }
        
        System.out.println("Data Preferensi :");
        display(this.datapreferensi);
    }
 
    
    void urutkan(){
        
        this.rankedpreferensi = new Double[this.listAlnternatif.size()];

        for ( int i = 0; i < this.datapreferensi.length ;i++){
            this.rankedpreferensi[i] = new Double(this.datapreferensi[i]);
        }
                
        Arrays.sort(this.rankedpreferensi , Collections.reverseOrder());
        
        System.out.println("Diurutkan :");
        display(this.rankedpreferensi);
    }
}
