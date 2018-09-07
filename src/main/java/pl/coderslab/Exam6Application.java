package pl.coderslab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import pl.coderslab.converter.TagConverter;

@SpringBootApplication
public class Exam6Application {
    public static void main(String[] args) {
        SpringApplication.run(Exam6Application.class, args);

    }
}
