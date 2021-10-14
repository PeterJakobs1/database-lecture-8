package no.kristiania.person;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonDaoTest {

    @Test
    void shouldRetriveSavedPersonFromDatabase () throws SQLException {
        PersonDao dao = new PersonDao(createDataSorce());

        Person person = examplePerson();
        dao.save(person);

        assertThat(dao.retrieve(person.getId()))
                .usingRecursiveComparison()
                .isEqualTo(person);

    }

    private Person examplePerson() {
        return new Person();
    }

    private DataSource createDataSorce() {
        return null;
    }
}
