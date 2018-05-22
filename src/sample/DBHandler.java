package sample;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler {
    private PreparedStatement preparedStatement;

    private Connection getDBConnection() throws SQLException {
        String string = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + " ?useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        return DriverManager.getConnection(string, dbUser, dbPassword);
    }

    private void closePreparedStatement(){
        try {
            if (!preparedStatement.isClosed()){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void insertAdvertisment(Advertisement advertisement) throws SQLException{
        String insert = "INSERT INTO " + USER_TABLE + "(" + USER_FIRSTNAME + "," + USER_LASTNAME + ", "
                + USER_BIRTHDATE + "," + USER_USERNAME + "," + USER_PASSWORD + ")"
                + "VALUES (?,?,?,?,?)";
        preparedStatement = getDBConnection().prepareStatement(insert);
        preparedStatement.setString(1, advertisement.getName_of_adv());
        preparedStatement.setString(2, advertisement.getName_of_perfume());
        preparedStatement.setFloat(3, advertisement.getPrice_of_adv());
        preparedStatement.setDate(4, advertisement.getDate_of_add());
        preparedStatement.setBoolean(5, advertisement.isIs_paid());
        preparedStatement.execute();
        closePreparedStatement();
    }

    protected void updateAdvertisment(Advertisement advertisement) throws SQLException{
        String update = "UPDATE " + USER_TABLE + " SET firstName=?, lastName=?, " +
                "birthdate=?, username=?, password=? WHERE username=?;";
        preparedStatement = getDBConnection().prepareStatement(update);
        preparedStatement.setString(1, advertisement.getName_of_adv());
        preparedStatement.setString(2, advertisement.getName_of_perfume());
        preparedStatement.setFloat(3, advertisement.getPrice_of_adv());
        preparedStatement.setDate(4, advertisement.getDate_of_add());
        preparedStatement.setBoolean(5, advertisement.isIs_paid());
        preparedStatement.execute();
        closePreparedStatement();
    }

    protected ResultSet selectAdvertisment(Advertisement advertisement) throws SQLException {
        String select = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_USERNAME
                + "=?";
        preparedStatement = getDBConnection().prepareStatement(select);
        preparedStatement.setInt(1, advertisement.getId());
        return preparedStatement.executeQuery();
    }

    protected void deleteAdvertisment(Advertisement advertisement) throws SQLException {
        String delete = "DELETE FROM  WHERE username = ?";
        preparedStatement = getDBConnection().prepareStatement(delete);
        preparedStatement.setInt(1, advertisement.getId());
        preparedStatement.execute();
        closePreparedStatement();
    }

    protected ArrayList<Advertisement> selectUser(Advertisement advertisement) throws SQLException {
        ArrayList<Advertisement> advertisementArrayList = new ArrayList<>();
        String select = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_USERNAME + "=?";
        preparedStatement = getDBConnection().prepareStatement(select);
        preparedStatement.setInt(1,advertisement.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name_of_adv;
            String name_of_perfume;
            float price_of_adv;
            Date date_of_adv;
            boolean is_paid;
            String
            String firstname = resultSet.getString("firstName");
            String lastname = resultSet.getString("lastName");
            String birthdate = resultSet.getString("birthdate");
            String password = resultSet.getString("password");
            user = new User(firstname,lastname,birthdate,username,password);
        }
        String answer = "User not found";
        if (user != null) {
            answer = user.toString();
        }
        return answer;
    }
}
