package sample.Controllers;

import javafx.scene.control.*;
import sample.Advertisement;
import sample.DBHandler;
import java.sql.Date;
import java.sql.SQLException;

public class Controller extends DBHandler{

    public TextField textNamePerfume;
    public ComboBox choiceNameAdv;
    public TextField textPrice;
    public DatePicker date;
    public CheckBox checkPaid;
    public TextField textId1;
    public TextField textId2;
    public Label labelAll;

    public void actionAdd() {
        try{
            int id;
            String name_of_adv = "Example";
            String name_of_perfume = textNamePerfume.getText();
            float price_of_adv = Float.parseFloat(textPrice.getText());
            Date date_of_adv = new Date(1999,10,11);
            boolean is_paid = checkPaid.isSelected();
            new DBHandler().insertAdvertisment(new Advertisement(name_of_adv,name_of_perfume,price_of_adv,date_of_adv,is_paid));
            actionShowAll();
        } catch (NumberFormatException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void actionEdit() {
    }
    public void actionShowAll() {
        try {
            labelAll.setText("");
            labelAll.setText(new DBHandler().selectAdvertisment());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionDelete() {
        try{
            int id = Integer.parseInt(textId1.getText());
            Advertisement advertisement = new Advertisement();
            advertisement.setId(id);
            new DBHandler().deleteAdvertisment(advertisement);
        } catch (NumberFormatException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
