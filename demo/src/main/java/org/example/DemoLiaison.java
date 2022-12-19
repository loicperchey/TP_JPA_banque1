package org.example;

import org.example.model.Department;
import org.example.model.Employee;
import org.example.model.ParkingSpace;
import org.example.model.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;

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

        // Demo Many to One et One to Many
            //creation d'un employe supplementaire
        transac.begin();

        Employee employee2 = new Employee();
        employee2.setId(6);
        em.persist(employee2);
        transac.commit();

                // creation d'un departement
        transac.begin();

        Department department = new Department();
        department.setId(1);
        department.setDname("Science");

                // creation d'un collection d'employee
        Collection<Employee> list = new ArrayList<>();
        list.add(employee);
        list.add(employee2);

                //ajout de cette collection d'employee a mon departement
        department.setEmps(list);

                // attribution des departements aux employees
            employee.setD(department);
            employee2.setD(department);

                // persistence
        em.persist(department);
        em.persist(employee);
        em.persist(employee2);

        transac.commit();
        transac.begin();

        Employee employee3 = em.find(Employee.class,5);
        Employee employee4 = em.find(Employee.class,6);
        System.out.println("Employe avec l'ID : "+employee3.getId()+" travail au departement "+employee3.getD().getDname());
        System.out.println("Employe avec l'ID : "+employee4.getId()+" travail au departement "+employee4.getD().getDname());

        Department department1 = em.find(Department.class,1);
        Collection<Employee> emps = department1.getEmps();
        System.out.println("Liste des employées du departement "+department1.getDname()+" : ");
        for(Employee emp : emps){
            System.out.println(emp.getId());
        }

        // Many To Many


        // Creation de 2 projets
        Project project = new Project();
        project.setId(1);
        project.setName("ProjetA");
        em.persist(project);

        Project project1 = new Project();
        project1.setId(2);
        project1.setName("ProjetB");
        em.persist(project1);

        // Creation d'une collection de projets
        Collection<Project> listProjet = new ArrayList<>();
        listProjet.add(project1);
        listProjet.add(project);

        // recuperation de plusieurs employees
        Employee employee5 = em.find(Employee.class,5);
        Employee employee6 = em.find(Employee.class,6);
        // que je met dans une liste
        Collection<Employee> mesemps = new ArrayList<>();
        mesemps.add(employee5);
        mesemps.add(employee6);

        // j'attribue des projets à mes employees
        employee5.setP(listProjet);
        employee6.setP(listProjet);

        em.persist(employee5);
        em.persist(employee6);

        project.setE(mesemps);
        project1.setE(mesemps);

        transac.commit();

       // transac.begin();
        Employee employee7 = em.find(Employee.class,5);
        Employee employee8 = em.find(Employee.class,6);
        Project monprojet1 = em.find(Project.class,1);
        Project monprojet2 = em.find(Project.class,2);

        System.out.println("Liste des projets de l'employee avec l'ID : "+employee7.getId());
        for(Project p : employee7.getP()){
            System.out.println(p.getName());
        }
        System.out.println("Liste des projets de l'employee avec l'ID : "+employee8.getId());
        for(Project p : employee8.getP()){
            System.out.println(p.getName());
        }
        System.out.println("Liste des employee sur le projet : "+monprojet1.getName());
        for(Employee e : monprojet1.getE()){
            System.out.println(e.getId());
        }
        System.out.println("Liste des employee sur le projet : "+monprojet2.getName());
        for(Employee e : monprojet2.getE()){
            System.out.println(e.getId());
        }


      //  transac.commit();
        em.close();
        emf.close();



    }
}
