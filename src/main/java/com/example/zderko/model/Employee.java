package com.example.zderko.model;

import javax.persistence.*;

@Entity()
@Table(name = "employees")
public class Employee  {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "em_id")
    private int id;

    @Column(name = "em_name")
    private String name;

    @Column(name = "em_active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "em_dp_id")
    private Department department;


    public Employee() {
    }

    public Employee(String name, boolean active, Department department) {
        this.name = name;
        this.active = active;
        this.department = department;
    }

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
