package com.example.demo;

import com.example.demo.Model.Aeroport;
import com.example.demo.Model.Avion;
import com.example.demo.Model.Vol;
import com.example.demo.controller.DijkstraAlgorithm;
import com.example.demo.repository.AvionRepository;
import com.example.demo.service.AeroportService;
import com.example.demo.service.impl.AeroportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        ArrayList<Avion> avions = new ArrayList<Avion>();
        ArrayList<Vol> vols = new ArrayList<Vol>();



        // Get the list of airports
        ApplicationContext context = SpringApplication.run(Demo1Application.class, args);
        AeroportService aeroportService = context.getBean(AeroportService.class);
        List<Aeroport> aeroports = aeroportService.findAll();

        // Use the list of airports as needed
        for (Aeroport airport : aeroports) {
            System.out.println(airport.getNom());
        }


        Scanner sc = new Scanner(System.in);
        int choix = 0;

        while(choix!= -1){
            System.out.println("============================");
            System.out.println("1/ Afficher la liste des Aeroports");
            System.out.println("2/ Ajouter des Avions");
            System.out.println("3/ Afficher la liste des Avions");
            System.out.println("4/ Ajouter des Vols");
            System.out.println("5/ Choisir des Vols Aleatoire");
            System.out.println("6/ Demarrer la simulation");
            System.out.println("-1/ Quitter");

            while(!sc.hasNextInt()){
                sc.next();
                System.out.println("Tapez un entier SVP!");
            }

            choix = sc.nextInt();

            switch (choix){

                case 1:
                    System.out.println("id  nom");
                    for(Aeroport a:aeroports){
                        System.out.println(a.getId() +" " +  a.getNom() );
                    }
                    break;
                case -1:
                    break;
                default:
                    System.out.println("Entrez un entier Valide");
                    break;
                case 3:
                    System.out.println("Id  Capacite  Consommation");
                    for(Avion av:avions){
                        System.out.println(av.getId() + " " + av.getCapacite() +", " +av.getConsommation() +".");
                    }
                    break;
                case 2:
                    System.out.println("combien d'avions?");
                    int nbAvion;
                    while(!sc.hasNextInt()){
                        sc.next();
                        System.out.println("Tapez un entier SVP!");
                    }

                    nbAvion = sc.nextInt();

                    while(nbAvion<1){
                        System.out.println("Tapez un entier >= 1");
                        nbAvion = sc.nextInt();
                    }

                    int count = 1;

                    while(count<=nbAvion ){
                        Avion a = null;
                        while(a==null){
                            System.out.println("Tapez le Type D'avion " + count);
                            System.out.println("1/ court");
                            System.out.println("2/ moyen");
                            System.out.println("3/ long");
                            int type;

                            while(!sc.hasNextInt()){
                                sc.next();
                                System.out.println("Tapez un entier SVP!");
                            }

                            type = sc.nextInt();

                            switch(type){

                                case 1:
                                    a = new Avion();
                                    a.setType("court");
                                    avions.add(a);
                                    EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
                                    EntityManager em = emf.createEntityManager();

                                    em.getTransaction().begin();
                                    em.persist(a);
                                    em.getTransaction().commit();

//                                    em.close();
//                                    emf.close();

                                    //context.close();
                                    break;
                                case 2:
                                    a = new Avion();
                                    a.setType("moyen");
                                    avions.add(a);
                                    emf = context.getBean(EntityManagerFactory.class);
                                    em = emf.createEntityManager();


                                    em.getTransaction().begin();
                                    em.persist(a);
                                    em.getTransaction().commit();
                                    break;
                                case 3:
                                    a = new Avion();
                                    a.setType("long");
                                    avions.add(a);
                                    emf = context.getBean(EntityManagerFactory.class);
                                    em = emf.createEntityManager();


                                    em.getTransaction().begin();
                                    em.persist(a);
                                    em.getTransaction().commit();
                                    break;
                                default:
                                    System.out.println("***Type non valide***");
                                    break;

                            }
                        }
                        count++;

                    }
                    break;
                case 4:
                    System.out.println("combien de Vols?");
                    int nbVol;
                    while(!sc.hasNextInt()){
                        sc.next();
                        System.out.println("Tapez un entier SVP!");
                    }

                    nbVol = sc.nextInt();

                    while(nbVol<1){
                        System.out.println("Tapez un entier >= 1");
                        nbVol = sc.nextInt();
                    }

                    int count1 = 1;

                    while(count1<=nbVol ){
                        Aeroport aerd= null, aera = null;
                        Avion avion=null;

                        System.out.println("choisissez l'aeroport de depart pour le vol" + count1);
                        sc.nextLine();
                        String nomd = sc.nextLine();

                        while(aerd == null){
                            for(Aeroport a:aeroports){
                                if(a.getNom().equalsIgnoreCase(nomd))
                                    aerd = a;
                            }
                            if (aerd == null){
                                System.out.println("Aeroport non valide");
                                nomd = sc.nextLine();
                            }

                        }

                        System.out.println("choisissez l'aeroport d'arrive pour le vol " + count1);
                        String noma = sc.nextLine();
                        while(aera == null){
                            for(Aeroport a:aeroports){
                                if(a.getNom().equalsIgnoreCase(noma))
                                    aera = a;
                            }
                            if (aera == null){
                                System.out.println("Aeroport non valide");
                                noma = sc.nextLine();
                            }

                        }

                        System.out.println("Choisissez l'avion pour le vol " + count1);
                        int id= sc.nextInt();

                        while(avion == null){
                            for(Avion a: avions){
                                if(a.getId() == id){
                                    avion = a;
                                    break;
                                }
                            }
                            if (avion == null){
                                System.out.println("Avion non valide");
                                id = sc.nextInt();
                            }

                        }



                        int j=1;
                        List<DijkstraAlgorithm.Vertex> vertices = new ArrayList<>();
                        for(Aeroport a: aeroports){
                            DijkstraAlgorithm.Vertex v = new DijkstraAlgorithm.Vertex(a.getId(), a.getLoc().getY(), a.getLoc().getX());
                            vertices.add(v);
                        }

                        //les arrets
                        List<DijkstraAlgorithm.Edge> edges = new ArrayList<>();
                        for(DijkstraAlgorithm.Vertex v1:vertices){
                            for(DijkstraAlgorithm.Vertex v2:vertices)
                                edges.add(new DijkstraAlgorithm.Edge(1, v1, v2));
                        }

                        DijkstraAlgorithm.Graph graph = new DijkstraAlgorithm.Graph(vertices, edges);

                        // run Dijkstra's algorithm on the graph
                        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
                        dijkstra.execute(vertices.get(aerd.getId()-1));

                        // get the shortest path between two nodes
                        LinkedList<DijkstraAlgorithm.Vertex> path = dijkstra.getPath(vertices.get(aera.getId()-1));


                        double dis = dijkstra.getShortestDistance(vertices.get(aera.getId()-1));

                        //System.out.println("le plus court chemin pour ce vols est de distance : " + dis);

                        double disEscal = dis;
                        int ok = 0;
                        if(dis*avion.getConsommation()/100 > avion.getCapacite()){
                            for(DijkstraAlgorithm.Vertex v:vertices){
                                dijkstra.execute(vertices.get(aerd.getId()-1));
                                disEscal = dijkstra.getShortestDistance(v);
                                System.out.println("disEsc("+ aerd.getId() + ","+ v.getId() + "= " + disEscal);
                                dijkstra.execute(v);
                                double disEscal2 = dijkstra.getShortestDistance(vertices.get(aera.getId()-1));
                                System.out.println("disEsc2("+ v.getId() + ","+ aera.getId() + "= " + disEscal2);
                                if(disEscal*avion.getConsommation()/100 < avion.getCapacite() && disEscal2*avion.getConsommation()/100 < avion.getCapacite()){
                                    Vol vol = new Vol();
                                    vol.setAeroportDepart(aerd);
                                    vol.setAeroportArrive(aera);
                                    vol.setAvion(avion);
                                    vol.setEscale(aeroports.get(v.getId()-1));
                                    vols.add(vol);
                                    EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
                                    EntityManager em = emf.createEntityManager();

                                    em.getTransaction().begin();
                                    em.persist(vol);
                                    em.getTransaction().commit();

                                    ok++;
                                    break;
                                }

                            }

                            if (ok==0){
                                System.out.println("le Vol est impossible de le realiser (pas d'escales disponibles");
                            }

                        }
                        else{
                            Vol vol = new Vol();
                            vol.setAeroportDepart(aerd);
                            vol.setAeroportArrive(aera);
                            vol.setAvion(avion);
                            EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
                            EntityManager em = emf.createEntityManager();

                            em.getTransaction().begin();
                            em.persist(vol);
                            em.getTransaction().commit();
                        }

                        System.out.println(vols);
                        count1++;

                    }

                    break;

                case 5:
                    Avion a = new Avion();
                    a.setType("court");
                    avions.add(a);
                    EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
                    EntityManager em = emf.createEntityManager();

                    em.getTransaction().begin();
                    em.persist(a);
                    em.getTransaction().commit();

                    Avion a1 = new Avion();
                    a1.setType("moyen");
                    avions.add(a1);

                    em.getTransaction().begin();
                    em.persist(a1);
                    em.getTransaction().commit();

                    Avion a2 = new Avion();
                    a2.setType("long");
                    avions.add(a2);

                    em.getTransaction().begin();
                    em.persist(a2);
                    em.getTransaction().commit();

                    Avion a3 = new Avion();
                    a3.setType("court");
                    avions.add(a3);

                    em.getTransaction().begin();
                    em.persist(a3);
                    em.getTransaction().commit();


                    Avion a4 = new Avion();
                    a4.setType("moyen");
                    avions.add(a4);

                    em.getTransaction().begin();
                    em.persist(a4);
                    em.getTransaction().commit();

                    Avion a5 = new Avion();
                    a5.setType("long");
                    avions.add(a5);

                    em.getTransaction().begin();
                    em.persist(a5);
                    em.getTransaction().commit();

                    Avion a6 = new Avion();
                    a6.setType("long");
                    avions.add(a6);

                    em.getTransaction().begin();
                    em.persist(a6);
                    em.getTransaction().commit();


                    //ajout des vols
                    Vol vol = new Vol();
                    Aeroport aerd = null;
                    Aeroport aera = null;

                    for(Aeroport ae:aeroports){
                        if(ae.getNom().equalsIgnoreCase("a0"))
                            aerd = ae;
                    }
                    for(Aeroport ae:aeroports){
                        if(ae.getNom().equalsIgnoreCase("a1"))
                            aera = ae;
                    }

                    vol.setAeroportDepart(aerd);
                    vol.setAeroportArrive(aera);
                    vol.setAvion(a);

                    em.getTransaction().begin();
                    em.persist(vol);
                    em.getTransaction().commit();


                    Vol vol1 = new Vol();
                    for(Aeroport ae:aeroports){
                        if(ae.getNom().equalsIgnoreCase("a1"))
                            aerd = ae;
                    }
                    for(Aeroport ae:aeroports){
                        if(ae.getNom().equalsIgnoreCase("a3"))
                            aera = ae;
                    }

                    vol1.setAeroportDepart(aerd);
                    vol1.setAeroportArrive(aera);
                    vol1.setAvion(a3);

                    em.getTransaction().begin();
                    em.persist(vol1);
                    em.getTransaction().commit();


                    Vol vol2 = new Vol();
                    for(Aeroport ae:aeroports){
                        if(ae.getNom().equalsIgnoreCase("a0"))
                            aerd = ae;
                    }
                    for(Aeroport ae:aeroports){
                        if(ae.getNom().equalsIgnoreCase("a4"))
                            aera = ae;
                    }

                    vol2.setAeroportDepart(aerd);
                    vol2.setAeroportArrive(aera);
                    vol2.setAvion(a4);

                    em.getTransaction().begin();
                    em.persist(vol2);
                    em.getTransaction().commit();



                    break;


            }

        }
        System.out.println("Hi");

    }

}
