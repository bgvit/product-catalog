package br.com.productms;

import br.com.productms.domain.model.Product;
import br.com.productms.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ProductMsApplication {

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProductMsApplication.class, args);
	}

	@PostConstruct
	private void initWithSampleData() {
		List<Product> products = List.of(
				new Product(1L, "Bicicleta Ksw 24", "A KSW é uma bicicleta Aro 29 com freio a disco desenvolvida para passeios e um bom começo nas primeiras trilhas da categoria MTB.", BigDecimal.valueOf(1375.14)),
				new Product(2L, "Conjunto 2 Mesas Lateral e Apoio Retrô Cissa", "A sala é o primeiro ambiente a ser visitado em sua casa.", BigDecimal.valueOf(190.62)),
				new Product(3L, "Forno Elétrico 48l Grand Family 127v - Mondial", "Cozinhar, tostar, gratinar, agora tudo é mais fácil e rápido com o Forno Elétrico Grand Family 48L da Mondial. Você pode fazer pizzas, sanduíches, torradas e tudo que sua imaginação criar em grandes porções para a família toda.", BigDecimal.valueOf(399.99)),
				new Product(4L, "Smartphone Samsung Galaxy S20 Fe 128GB 4G Wi-Fi Tela 6.5'' Dual Chip 6GB RAM Câmera Tripla + Selfie 32MP - Cloud Orange", "Smartphone Samsung Galaxy S20 FE, 128GB, 6GB RAM, Tela Infinita de 6.5', Câmera Tripla Traseira 12MP+12MP (UW) + 8MP (T), Câmera Frontal 10MP com Autofoco, IP68", BigDecimal.valueOf(2330))
		);
		productRepository.saveAll(products);
	}

	}
