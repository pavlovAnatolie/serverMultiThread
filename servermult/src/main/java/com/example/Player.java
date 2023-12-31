package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Player extends Thread{
    private int numero;
    Socket s;
    ArrayList<Player> numThr;

    public Player(int numero,Socket s,ArrayList<Player> a){
        this.numero = numero;
        this.s = s; 
        numThr = new ArrayList<Player>(a);
    }

    public void run(){
        try {
            //apertura del stream input e output
           BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));//input
           DataOutputStream out = new DataOutputStream(s.getOutputStream());//output

            System.out.println("il dispositivo è stato collegato");
            boolean indovinato= false;

            System.out.println(numero);
            //metto un contatore per tracciare i tentativi
            int tentativi = 0;
            do {
                tentativi++;

                //prendo il numero inserito dal utente sullo stream e lo converto
                String num = in.readLine();
                int choice = Integer.parseInt(num);
                System.out.println("cliet->"+choice);

                //controllo il vaolore e ritorno il resoconto del resultato relativo
                if(choice < numero){
                    out.writeBytes(1+"\n");
                    System.out.println("server->"+1);
                }else if(choice > numero){
                    out.writeBytes(2+"\n");
                    System.out.println("server->"+2);
                }else{
                    out.writeBytes(3+"\n");
                    out.writeBytes(tentativi+"\n");
                    System.out.println("server->"+3);
                    System.out.println("server->"+tentativi);
                    indovinato = true;

                    //messaggio in brodcast
                    for(int  i = 0 ; i < numThr.size(); i++){
                        numThr.get(i).advert();
                    }

                }



            } while (!indovinato);
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

public void advert(){
        try {
            DataOutputStream out = new DataOutputStream(s.getOutputStream());//output
            out.writeBytes("!");
        } catch (Exception e) {
            e.getMessage();
        }
    }

}

    
