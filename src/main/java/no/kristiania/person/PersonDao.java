package no.kristiania.person;

import javax.sql.DataSource;

public class PersonDao {

    public PersonDao(DataSource dataSorce) {

    }

    public void save(Person person) {
        this.person = person;


    }

    public Person retrieve(Object id) {
        return person;
    }
}
