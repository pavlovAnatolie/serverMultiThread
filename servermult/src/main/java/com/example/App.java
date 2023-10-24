package com.example;
import java.net.ServerSocket;
import java.util.ArrayList;


public class App {
    public static void main( String[] args )
    {
        try{
            // metto il server in ascolto sulla porta 3000 per poter acquisire e creare la socket
           ServerSocket server = new ServerSocket(3000);
           System.out.println("il server è in ascolto");
           ArrayList<Player> a = new ArrayList();

           while(true){

            //genero un valore casulamente da 1 a 100
           int ranDom = (int)Math.floor(Math.random() *(100- 1 + 1) + 1);
           Player p = new Player(ranDom,server.accept(),a);
           a.add(p);
            p.start();


           
           }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}