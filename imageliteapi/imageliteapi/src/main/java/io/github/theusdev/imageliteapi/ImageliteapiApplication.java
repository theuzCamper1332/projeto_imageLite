package io.github.theusdev.imageliteapi;

import io.github.theusdev.imageliteapi.domain.entity.Image;
import io.github.theusdev.imageliteapi.domain.enums.ImageExtension;
import io.github.theusdev.imageliteapi.infra.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ImageliteapiApplication {

	/*@Bean
	public CommandLineRunner commandLineRunner(@Autowired ImageRepository imageRepository) {
		return args -> {
			Image image = Image.builder()
					.extenseion(ImageExtension.PNG)
					.name("myImage")
					.tags("teste")
					.size(1000L)
					.build();

			imageRepository.save(image);
		};
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(ImageliteapiApplication.class, args);
	}

}
