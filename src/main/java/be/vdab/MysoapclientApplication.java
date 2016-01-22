package be.vdab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.soap.client.SoapFaultClientException;

@SpringBootApplication
public class MysoapclientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MysoapclientApplication.class, args);

	}

	@Autowired
	CountryClient client;

	@Override
	public void run(String... arg0) throws Exception {
		try {
			System.out.println(client.getCountry("NL").getName());
		} catch (SoapFaultClientException ex) {
			System.out.println(ex.getMessage());
		}

	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		// een marshaller zet XML om naar Java objecten en omgekeerd
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("be.vdab.soapmessages");
		return marshaller;
	}

	@Bean
	public CountryClient countryClient(Jaxb2Marshaller marshaller,
			@Value("${countryServiceURL}") String countryServiceURL) {
		CountryClient client = new CountryClient();
		client.setDefaultUri(countryServiceURL);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
