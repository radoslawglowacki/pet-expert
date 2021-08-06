package com.radekdawid.petexpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetExpertApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetExpertApplication.class, args);

    }

//	@Component
//	public class Monitor {
//		@Autowired
//		private RegistrationService registrationService;
//		@Autowired
//		private DataSource dataSource;
//
//		@PostConstruct
//		public void init(){
//			ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("data-postgres.sql"));
//			resourceDatabasePopulator.execute(dataSource);
//
//			ProviderRegistrationRequest providerRadek = new ProviderRegistrationRequest("Radek", "Radek",
//					"Admin1234#", "radek@petexpert.pl",987654321L, 1234567890L, "Warszawa",
//					"Wiejska", "1", "1","00-111", "Company one",
//					"Warszawa", "Wiejska", "1","1", "00-111",
//					List.of(1L, 2L, 3L, 4L), "Nothing");
//
//			ProviderRegistrationRequest providerDawid = new ProviderRegistrationRequest("Dawid", "Dawid",
//					"Admin1234#", "dawid@petexpert.pl",987654321L, 1234567890L, "Warszawa",
//					"Wiejska", "2", "2","00-222", "Company two",
//					"Warszawa", "Wiejska", "2","2", "00-222",
//					List.of(1L, 2L, 3L, 4L), "Nothing");
//
//			ProviderRegistrationRequest providerAdmin = new ProviderRegistrationRequest("Admin", "Admin",
//					"Admin1234#", "admin@petexpert.pl",987654321L, 1234567890L, "Warszawa",
//					"Wiejska", "3", "3","00-333", "Company three",
//					"Warszawa", "Wiejska", "3","3", "00-333",
//					List.of(1L, 2L, 3L, 4L), "Nothing");
//
//			registrationService.registerProvider(providerAdmin);
//			registrationService.registerProvider(providerRadek);
//			registrationService.registerProvider(providerDawid);
//		}
//	}

}
