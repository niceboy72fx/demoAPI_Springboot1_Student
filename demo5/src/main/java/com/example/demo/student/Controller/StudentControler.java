package com.example.demo.student.Controller;

import com.example.demo.student.Models.RespondObject;
import com.example.demo.student.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.student.Respositories.studentRespository;

import java.util.List;
import java.util.Optional;

// file controller dung cho CRUD
@RestController // kich hoat control
@EnableAutoConfiguration // khoi chay config
@RequestMapping(path = "/student") // kich hoat path
public class StudentControler {


    @Autowired // tu dong tim cac @bean tuong ung
    private studentRespository repository;

    @GetMapping("")  //Get du lieu
    public List<Student> getAllStudent(){
        return repository.findAll(); // lay du lieu tu DB
    }

    @GetMapping("/{id}")
    public Student getStudentbyid(@PathVariable Long id){
        return repository.findById(id).orElseThrow(    // neu ma id ko ngoai du lieu thi nem 1 excepttion
                ()-> new RuntimeException("Error id not found id : " + id)   // tao 1 exception
        );
    }

    @GetMapping("/object/{id}") // vd : http://localhost:8080/object/{id}  vs id la 1 so bat ky
     public ResponseEntity<RespondObject> getStudentbyId(@PathVariable Long id){ // @PathVariable chi bien duong dan
          Optional <Student> foundStudentId = repository.findById(id);
          if (foundStudentId.isPresent()){
              return ResponseEntity.status(HttpStatus.OK).body(
                      new RespondObject("ok","Query complete",foundStudentId)
              );
          } else {
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                      new RespondObject("Error","couldn't not find ID !!"+id,"")
              );
          }
    }

    /*  output from vd : http://localhost:8080/object/{id}
        {
    "status": "ok",                   "status","message" lay tu ResponndOject
    "message": "Query complete",
    "data": {
        "id": 1,
        "name": "maria",
        "dob": "2000-01-05",
        "age": 21
    }
}
    */

    @PostMapping("/post") //POST du lieu
    public ResponseEntity<RespondObject> postStudentId(@RequestBody Student newStudent){
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","insert new student successfully !!",repository.save(newStudent))
        );
    }

    /*
    {
    "status": "ok",
    "message": "insert new student successfully !!",
    "data": {
        "id": 3,
        "name": "matria okimaza",
        "dob": "2002-01-05",
        "age": 20
      }
    }
    */

    @PutMapping("/put/{id}") //Put du lieu (hay sua du lieu)
    public Student changeStudentId(@RequestBody Student newStudent, @PathVariable Long id){
        return repository.findById(id).map(student -> {
            student.setId(newStudent.getId()); // set lai Id cua get ("cai nay em ko ro lam ")
            student.setName(newStudent.getName());
            student.setAge(newStudent.getAge());
            student.setDob(newStudent.getDob());
            return repository.save(newStudent);
        }).orElseGet(() -> {
            newStudent.setId(id);
            return repository.save(newStudent);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudentId(@PathVariable Long id){
          repository.deleteById(id); // delete bang id
    }
}
