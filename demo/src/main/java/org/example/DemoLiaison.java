package org.example;

import org.example.model.Employee;
import org.example.model.ParkingSpace;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DemoLiaison {

    public static void main(String[] args){

        //Demo One to one bidirectionnel

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Demojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transac = em.getTransaction();


        // Creation et enregistrement d'un employee (avec id = 5)
        transac.begin();

        Employee employee = new Employee();

        employee.setId(5);

        em.persist(employee);

        transac.commit();

        // Creation d'un parkingSpace

        transac.begin();
        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setId(6);
        parkingSpace.setLocation("emplacement 6");
        parkingSpace.setEmp(employee);
        employee.setSpace(parkingSpace);
        em.persist(parkingSpace);
        transac.commit();

        // Recuperation des informations

        Employee employee1 = em.find(Employee.class,5);
        System.out.println("Employe avec l'ID : "+employee1.getId()+" et a la place de parking située à "+employee1.getSpace().getLocation());

        ParkingSpace parkingSpace1 = em.find(ParkingSpace.class,6);
        System.out.println("Emplacement parking avec l'id : "+parkingSpace1.getId()+" attribue à l'employée avec l'id : "+parkingSpace1.getEmp().getId());



    }
}
