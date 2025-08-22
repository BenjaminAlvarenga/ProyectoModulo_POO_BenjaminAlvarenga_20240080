package ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BenjaminAlvarenga20240080Application {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach( entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);
		SpringApplication.run(BenjaminAlvarenga20240080Application.class, args);
	}

}
