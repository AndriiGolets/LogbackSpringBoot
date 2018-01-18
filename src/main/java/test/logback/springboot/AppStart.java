package test.logback.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AppStart implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run (AppStart.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String s =" TT  \n T " ;

        log.info ("Info \n log !! {} --", s);
        log.debug ("Debug log !!");
        log.error ("Error log !!");
    }
}
