package fakepanshi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("fakepanshi.mapper")
public class FakePanShiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakePanShiApplication.class, args);
    }
}
