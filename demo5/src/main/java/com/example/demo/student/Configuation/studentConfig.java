package com.example.demo.student.Configuation;

import com.example.demo.student.Models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // goi thu vien logger
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean; //goi thu bien annotation
import com.example.demo.student.Respositories.studentRespository;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

// package Configuation : chứa các hàm khởi tạo, chúng sẽ chạy khi file (Application start)  , đặc điểm (@configuation)
@Configuration
public class studentConfig {
    //Dependency injection
    private static final Logger logger =  LoggerFactory.getLogger(studentConfig.class); // tao 1 logger cho studentConfig class
    @Bean     // dau @ the hien la 1 annotation , o day @Bean la moudle chay cua chuong trinh (hay la 1 ham main)
    CommandLineRunner initDB(studentRespository studentrespository){     // ket noi file studentRespository(file chua cac query DB)
        return new CommandLineRunner() {               // thuc thi ra lenh cac ham khoi chay khi file Demo5Application start
            @Override
            public void run(String... args) throws Exception {      // ham thuc thi khi start
                  Student studentA =  new Student(
                          1L, "maria",
                          LocalDate.of(2000, Month.JANUARY,5),
                          21
                  );

                  Student studentB =  new Student(
                          2L,
                        "maria",
                        LocalDate.of(2000, Month.JANUARY,5),
                        21
                   );
                  // save data to DB
                  studentrespository.save(studentA);
                  studentrespository.save(studentB);
                  // sinh file log
                  logger.info("insert data" + studentA);
                  logger.info("insert data" + studentB);
            }
        };
    }
}
