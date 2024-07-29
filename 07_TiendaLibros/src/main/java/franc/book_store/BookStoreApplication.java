package franc.book_store;

import franc.book_store.view.BookForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext springContext = new SpringApplicationBuilder(BookStoreApplication.class)
				.headless(false)
				.web(WebApplicationType.NONE)
				.run(args);
		EventQueue.invokeLater(() -> {
			BookForm bookForm = springContext.getBean(BookForm.class);
			bookForm.setVisible(true);
		});
	}

}
