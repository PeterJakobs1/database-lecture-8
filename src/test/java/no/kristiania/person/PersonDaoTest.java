package no.kristiania.person;


import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonDaoTest {

    private PersonDao dao;

    @Test
    void shouldRetriveSavedPersonFromDatabase () throws SQLException {
        PersonDao dao = new PersonDao(createDataSorce());

        Person person = examplePerson();
        dao.save(person);

        assertThat(dao.retrieve((Long) person.getId()))
                .usingRecursiveComparison()
                .isEqualTo(person);

    }
    @Test
    void shouldListPeopleByLastName() throws SQLException {
        Person matchingPerson =  examplePerson();
        matchingPerson.setLastName("TestPerson");
        dao.save(matchingPerson);

        Person anotherMatchingPerson =  examplePerson();
        anotherMatchingPerson.setLastName(matchingPerson.getLastName());
        dao.save(anotherMatchingPerson);


        Person nonMatchingPerson = examplePerson();
        dao.save(nonMatchingPerson);

        assertThat(dao.listByLastName(matchingPerson.getLastName()))
                .extracting(Person::getId)
                .contains(matchingPerson.getId(), anotherMatchingPerson.getId())
                .doesNotContain(nonMatchingPerson.getId());


    }

    private Person examplePerson() {
        Person person = new Person();
        person.setFirstName(pickOne("Peter", "Gunnar", "Fredrik", "Johannes"));
        person.setLastName(pickOne("Jonsen", "Kleiven", "Moe", "Vik"));
        return person;
    }

    private String pickOne(String...alternatives) {
        return alternatives[new Random().nextInt(alternatives.length)];
    }


    private DataSource createDataSorce() {
        PGSimpleDataSource dataSorce = new PGSimpleDataSource();
        dataSorce.setUrl("jdbc:postgresql://localhost:5432/person_db");
        dataSorce.setUser("person_dbuser");
        dataSorce.setPassword("=7[yE;e,Z6QU{:$-");
        return dataSorce;
    }
}
