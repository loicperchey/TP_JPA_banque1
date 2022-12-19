package org.example.model;


import javax.persistence.*;

@Entity
@Table(name= "EMP")
public class Employee {

    @Id
    private int id;

    @OneToOne
    @JoinColumn(name="P_SPACE")
    private ParkingSpace space;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingSpace getSpace() {
        return space;
    }

    public void setSpace(ParkingSpace space) {
        this.space = space;
    }
}
