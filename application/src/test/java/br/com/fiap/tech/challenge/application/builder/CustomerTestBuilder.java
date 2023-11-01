package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.enterprise.valueobject.EmailRegistration;

import java.util.UUID;

public class CustomerTestBuilder {

    public static final UUID CUSTOMER_UUID = UUID.randomUUID();

    public static class Builder {

        private static final Customer.CustomerBuilder builder = Customer.builder();

        public Builder withUUID() {
            builder.uuid(CUSTOMER_UUID);
            return this;
        }

        public Builder withName() {
            builder.name("Ronald McDonald");
            return this;
        }

        public Builder withEmail() {
            builder.email(EmailRegistration.of("ceo@mcdonalds.com"));
            return this;
        }

        public Builder withDocument() {
            builder.document(Document.of("33104138028"));
            return this;
        }

        public Builder enableTrue() {
            builder.enabled(Boolean.TRUE);
            return this;
        }

        public Builder enableFalse() {
            builder.enabled(Boolean.FALSE);
            return this;
        }

        public Customer build() {
            return builder.build();
        }

        public Customer fullFields() {
            withUUID();
            withName();
            withEmail();
            withDocument();
            enableTrue();
            return builder.build();
        }

        public Customer buildDisable() {
            withUUID();
            withName();
            withEmail();
            withDocument();
            enableFalse();
            return builder.build();
        }
    }
}
