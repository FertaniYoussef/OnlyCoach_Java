/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import Service.ServiceCategorieEvent;
import Service.ServiceEvent;
import Service.ServiceParticipation;
import entities.CategorieEvent;
import entities.Event;
import entities.Participation;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class testmain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Service.ServiceEvent serE=new ServiceEvent();
        CategorieEvent co=new CategorieEvent( "categ");
        CategorieEvent ca=new CategorieEvent( "categorie");
        Service.ServiceCategorieEvent serCat=new ServiceCategorieEvent();
        //serCat.createCat(ca);
        //co.setId(1);
        //ca.setId(2);
        
        
        Event e=new Event(1, "aaaaaaa", co, new Date(1, 1, 2001), "3h", "description", "lieu", 50, 3.0, "image");
        //Event ee=new Event(1, "abbb", co, new Date(1, 1, 2001), "16h", "description", "lieu", 150, 10.0, "image");
        //serE.createEvent(e);
        
        List<Event> lis=serE.readEvenements();
        System.out.println(lis.get(0).toString());
        ServiceParticipation sp=new ServiceParticipation();
        Participation p=new Participation(sp.readUserById(1), serE.readEvenementsById(1), new Date(1, 1, 2001) );
        Participation pp=new Participation(sp.readUserById(1), serE.readEvenementsById(5), new Date(1, 1, 2001) );
        //sp.createPart(pp);
       p.setId(1);
       List<CategorieEvent> list=serCat.readCategories();
        System.out.println(list.get(0).toString());
        /*List<Event> liste=sp.readEvenementsByUser(1);
        System.out.println(liste.get(0).toString());*/
       serCat.deleteCat(ca);
       sp.deletePart(pp);
    }
    
}
