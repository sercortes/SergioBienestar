/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.util;


import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author serfin
 */
public class events {

//    public static void main(String[] args) {
//        final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
//        ses.scheduleWithFixedDelay(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Time is "+new Date());
//            }
//        }, 1, 1, TimeUnit.MINUTES);
//    }

    public static void main(String[] args) {
        String nombre = "343454";
        System.out.println(nombre.matches("[a-zA-z]+([ '-][a-zA-Z]+)*"));
                
                
    }
    
}
