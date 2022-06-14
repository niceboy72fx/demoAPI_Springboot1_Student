package com.example.demo.student.Models;

// cac class dc goi la modal
import javax.persistence.*;
import java.time.LocalDate;
@Entity // xac dinh
@Table // khoi tao 1 table
public class Student {
    @Id // set id primary key // set id increase auto
    private Long id;
    private String name;
    private LocalDate  dob;
    private Integer age;


    public Student(Long id, String name, LocalDate dob, int age) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.age = age;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
