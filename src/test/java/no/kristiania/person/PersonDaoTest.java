package no.kristiania.person;

import org.junit.jupiter.api.Test;

public class PersonDaoTest {

    @Test
    void shouldRetriveSavedPersonFromDatabase (){
        PersonDao dao = new PersonDao(createDataSorce());

        Person person = examplePerson();
        dao.save(person);

        assertThat(dao.retrieve(person.getId()));
        //TODO
        ;
    }
}
