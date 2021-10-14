package no.kristiania.person;

import javax.sql.DataSource;
import java.sql.*;

public class PersonDao {

    private final DataSource dataSorce;


    public PersonDao(DataSource dataSorce) {

        this.dataSorce = dataSorce;
    }

    public void save(Person person) throws SQLException {
        try (Connection connection = dataSorce.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(
                    "insert into people(firstname, lastname) values (?,?)",
                    Statement.RETURN_GENERATED_KEYS

            )) {
                statement.setString(1,person.getFirstname());
                statement.setString(2,person.getLastName());

                statement.executeUpdate();

                try (ResultSet rs  = statement.getGeneratedKeys()) {
                    rs.next();
                    person.setId(rs.getLong("id"));
                }
            }

        }



    }

    public Person retrieve(Long id) throws SQLException {

        try (Connection connection = dataSorce.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(
                    "select * from people where id = ?"
            )) {
                statement.setLong(1,id);
                try (ResultSet rs = statement.executeQuery()) {
                    rs.next();

                    Person person = new Person();
                    person.setId(rs.getLong("id"));
                    person.setFirstName(rs.getString("firstname"));
                    person.setLastName(rs.getString("lastname"));
                    return person;
                }
            }
        }
    }
}
