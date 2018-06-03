package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class DBHandler implements Configuration{
    private PreparedStatement preparedStatement;
    private ArrayList<Advertisement> advertisements = new ArrayList<>();
    private ArrayList<Advertisement> selectedAdvertisements = new ArrayList<>();
    private Connection getDBConnection() throws SQLException {
        String string = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + " ?useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        return DriverManager.getConnection(string, DB_USER, "12345");
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

    public void insertAdvertisement(Advertisement advertisement) throws SQLException{
        String insert = "INSERT INTO " + USER_TABLE + "("+ NAME_OF_ADV + ", "
                + NAME_OF_PERFUME + "," + PRICE_OF_ADV + "," + DATE_OF_ADV + "," + IS_PAID + ")"
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

    public void updateAdvertisement(Advertisement advertisement) throws SQLException{
        String update = "UPDATE " + USER_TABLE + " SET " + NAME_OF_ADV + "=?, " + NAME_OF_PERFUME + "=?, " +
                PRICE_OF_ADV + "=?, " + DATE_OF_ADV + "=?, " + IS_PAID + "=? WHERE " +  ID + "=?;";
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

    public void deleteAdvertisement(Advertisement advertisement) throws SQLException{
        String delete = "DELETE FROM " + USER_TABLE + " WHERE " + ID + "= ?";
        preparedStatement = getDBConnection().prepareStatement(delete);
        preparedStatement.setInt(1, advertisement.getId());
        preparedStatement.execute();
        closePreparedStatement();
    }

    public String selectAdvertisement() throws SQLException{
        StringBuilder answer = new StringBuilder();
        String select = "SELECT * FROM " + USER_TABLE;
        preparedStatement = getDBConnection().prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(ID);
            String name_of_adv = resultSet.getString(NAME_OF_ADV);
            String name_of_perfume = resultSet.getString(NAME_OF_PERFUME);
            float price_of_adv = resultSet.getFloat(PRICE_OF_ADV);
            java.sql.Date date_of_adv = resultSet.getDate(DATE_OF_ADV);
            boolean is_paid = resultSet.getBoolean(IS_PAID);
            advertisements.add(new Advertisement(id,name_of_adv,name_of_perfume,price_of_adv,date_of_adv,is_paid));
        }
        for (int i = 1; i < advertisements.size(); i++){
            if (advertisements.get(i).getId()==advertisements.get(i-1).getId()){
                advertisements.remove(i);
            }
        }
        advertisements.sort(Comparator.comparing(Advertisement::getName_of_adv));
        for (Advertisement a: advertisements){
            answer.append(a.toString()).append("\n");
        }
        try {
            saveIntoFile(answer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer.toString();
    }
    public String selectAdvertisementByName(Advertisement advertisement) throws SQLException{
        StringBuilder answer = new StringBuilder();
        String select = "SELECT * FROM " + USER_TABLE + " WHERE " + NAME_OF_PERFUME + "=?;";
        preparedStatement = getDBConnection().prepareStatement(select);
        preparedStatement.setString(1, advertisement.getName_of_perfume());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(ID);
            String name_of_adv = resultSet.getString(NAME_OF_ADV);
            String name_of_perfume = resultSet.getString(NAME_OF_PERFUME);
            float price_of_adv = resultSet.getFloat(PRICE_OF_ADV);
            java.sql.Date date_of_adv = resultSet.getDate(DATE_OF_ADV);
            boolean is_paid = resultSet.getBoolean(IS_PAID);
            selectedAdvertisements.add(new Advertisement(id,name_of_adv,name_of_perfume,price_of_adv,date_of_adv,is_paid));
        }
        for (int i = 1; i < selectedAdvertisements.size(); i++){
            if (selectedAdvertisements.get(i).getId()==selectedAdvertisements.get(i-1).getId()){
                selectedAdvertisements.remove(i);
            }
        }
        selectedAdvertisements.sort(Comparator.comparing(Advertisement::getName_of_adv));
        for (Advertisement a: selectedAdvertisements){
            answer.append(a.toString()).append("\n");
        }
        if (answer.toString().equals("")){
            throw new SQLException();
        }
        return answer.toString();
    }
    private void saveIntoFile(String string) throws IOException {
        FileWriter fileWriter = new FileWriter(new File("perfume.txt"),false);
        fileWriter.write(string);
        fileWriter.close();
    }
}