package bookkeep.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author 星灵
 *
 */

@SpringBootApplication
@ComponentScan(basePackages={"bookkeep"})
public class BookKeepApp  
{
    public static void main(String[] args )
    {
    	SpringApplication.run(BookKeepApp.class, args);
    }
}
