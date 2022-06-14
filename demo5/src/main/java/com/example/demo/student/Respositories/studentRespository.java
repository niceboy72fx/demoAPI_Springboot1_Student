package com.example.demo.student.Respositories;

import com.example.demo.student.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface studentRespository extends JpaRepository<Student, Long> {

}// dung de giao  API của JPA(Database)
