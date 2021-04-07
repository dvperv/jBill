package com.example.jbill;

import com.example.jbill.model.Account;
import com.example.jbill.model.Transaction;
import com.example.jbill.repo.AccountRepo;
import com.example.jbill.repo.TransactionRepo;
import com.example.jbill.service.EntryService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@SpringBootApplication
public class JBillApplication {
    @Configuration
    public static class MongoAdditionalConfig extends AbstractMongoClientConfiguration {
        @Value("${spring.data.mongodb.uri}") String curi;

        @Bean
        MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
            return new MongoTransactionManager(dbFactory);
        }

        @Override
        public MongoClient mongoClient() {

            return MongoClients.create(this.curi);
        }

        @Override
        protected String getDatabaseName() {
            return "billing";
        }
    }

    @Bean
    CommandLineRunner commandLineRunner(AccountRepo accountRepo, TransactionRepo transactionRepo, EntryService entryService){
        return args -> {
            accountRepo.deleteAll();transactionRepo.deleteAll();

            Account cash = accountRepo.save(new Account("TCASH"));
            Account erng = accountRepo.save(new Account("TERNG"));
            Account clnt = accountRepo.save(new Account("ООО Ромашка"));

            entryService.post(cash, clnt, 100);// add
            entryService.post(clnt, erng, 10);// spend
            entryService.post(clnt, erng, 10);// spend
            entryService.post(erng, cash, 15);// withdraw
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(JBillApplication.class, args);
    }

}
