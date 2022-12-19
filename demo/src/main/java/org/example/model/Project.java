package org.example.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Project {

    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Employee> getE() {
        return e;
    }

    public void setE(Collection<Employee> e) {
        this.e = e;
    }

    private String name;

    @ManyToMany(mappedBy = "p")
    private Collection<Employee> e;
}
