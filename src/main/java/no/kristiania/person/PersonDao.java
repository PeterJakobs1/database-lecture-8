package no.kristiania.person;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonDao {

    private final DataSource dataSorce;
    private Person person;

    public PersonDao(DataSource dataSorce) {

        this.dataSorce = dataSorce;
    }

    public void save(Person person) throws SQLException {
        try (Connection connection = dataSorce.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(
                    "insert into people(firstname, lastname) values (?,?)"

            )) {
                statement.setString(1,person.getFirstname());
                statement.setString(2,person.getLastName());

                statement.executeUpdate();
            }

        }
        this.person = person;


    }

    public Person retrieve(Object id) {
        return person;
    }
}
