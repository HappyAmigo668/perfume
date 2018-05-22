package sample;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler implements Configuration{
    private PreparedStatement preparedStatement;

    private Connection getDBConnection() throws SQLException {
        String string = "jdbc:mysql://" + DBHOST + ":" + DBPORT + "/" + DBNAME + " ?useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        return DriverManager.getConnection(string, DBUSER, DBPASS);
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
        String insert = "INSERT INTO " + USER_TABLE + "("+ NAME_OF_ADV + ", "
                + NAME_OF_PARFUME + "," + PRICE_OF_ADV + "," + DATE_OF_ADV + "," + IS_PAID + ")"
                + "VALUES (?,?,?,?,?)";
        preparedStatement = getDBConnection().prepareStatement(insert);
        preparedStatement.setString(1, advertisement.getName_of_adv());
        preparedStatement.setString(2, advertisement.getName_of_perfume());
        preparedStatement.setFloat(3, advertisement.getPrice_of_adv());
        preparedStatement.setDate(4, advertisement.getDate_of_adv());
        preparedStatement.setBoolean(5, advertisement.isIs_paid());
        preparedStatement.execute();
        closePreparedStatement();
    }

    protected void updateAdvertisment(Advertisement advertisement) throws SQLException{
        String update = "UPDATE " + USER_TABLE + " SET " + NAME_OF_ADV + "=?, " + NAME_OF_PARFUME + "=?, " +
                PRICE_OF_ADV + "=?, " + DATE_OF_ADV + "=?, " + IS_PAID + "=? WHERE" +  ID + "=?;";
        preparedStatement = getDBConnection().prepareStatement(update);
        preparedStatement.setString(1, advertisement.getName_of_adv());
        preparedStatement.setString(2, advertisement.getName_of_perfume());
        preparedStatement.setFloat(3, advertisement.getPrice_of_adv());
        preparedStatement.setDate(4, advertisement.getDate_of_adv());
        preparedStatement.setBoolean(5, advertisement.isIs_paid());
        preparedStatement.setInt(6, advertisement.getId());
        preparedStatement.execute();
        closePreparedStatement();
    }

//    protected ResultSet selectAdvertisment(Advertisement advertisement) throws SQLException {
//        String select = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_USERNAME
//                + "=?";
//        preparedStatement = getDBConnection().prepareStatement(select);
//        preparedStatement.setInt(1, advertisement.getId());
//        return preparedStatement.executeQuery();
//    }

    protected void deleteAdvertisment(Advertisement advertisement) throws SQLException {
        String delete = "DELETE FROM " + USER_TABLE + " WHERE " + ID + "= ?";
        preparedStatement = getDBConnection().prepareStatement(delete);
        preparedStatement.setInt(1, advertisement.getId());
        preparedStatement.execute();
        closePreparedStatement();
    }

    protected ArrayList<Advertisement> selectUser(Advertisement advertisement) throws SQLException {
        ArrayList<Advertisement> advertisementArrayList = new ArrayList<>();
        String select = "SELECT * FROM " + USER_TABLE + " WHERE " + ID + "=?";
        preparedStatement = getDBConnection().prepareStatement(select);
        preparedStatement.setInt(1,advertisement.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(ID);
            String name_of_adv = resultSet.getString(NAME_OF_ADV);
            String name_of_perfume = resultSet.getString(NAME_OF_PARFUME);
            float price_of_adv = resultSet.getFloat(PRICE_OF_ADV);
            Date date_of_adv = resultSet.getDate(DATE_OF_ADV);
            boolean is_paid = resultSet.getBoolean(IS_PAID);
            advertisementArrayList.add(new Advertisement(name_of_adv,name_of_perfume,price_of_adv,date_of_adv,is_paid));
        }
        return advertisementArrayList;
    }
}
