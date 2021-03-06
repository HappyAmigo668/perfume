package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Advertisement;
import sample.Animation;
import sample.DBHandler;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Controller{

    public TextField textNamePerfume;
    public ComboBox<String> choiceNameAdv;
    public TextField textPrice;
    public DatePicker datePicker;
    public CheckBox checkPaid;
    public TextField textId;
    public Label labelAll;
    public TextField textSearchByName;

    @FXML
    protected void initialize(){
        String [] array = {"Телевизионная реклама","Радио реклама", "Печатная реклама", "Интернет-реклама"};
        choiceNameAdv.getItems().addAll(array);
        choiceNameAdv.getSelectionModel().selectFirst();
        actionShowAll();
    }
    public void actionAdd() {
        try {
            String name_of_adv = String.valueOf(choiceNameAdv.getValue());
            String name_of_perfume = textNamePerfume.getText();
            if (name_of_perfume.trim().length()==0){
                throw new Exception();
            }
            float price_of_adv = Float.parseFloat(textPrice.getText());
            Date date_of_adv = createDate(String.valueOf(datePicker.getValue()));
            boolean is_paid = checkPaid.isSelected();
            new DBHandler().insertAdvertisement(new Advertisement(name_of_adv, name_of_perfume, price_of_adv, date_of_adv, is_paid));
        } catch (NumberFormatException e) {
            Animation.animateNode(textPrice);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            Animation.animateNode(datePicker);
        } catch (Exception e) {
            Animation.animateNode(textNamePerfume);
        } finally {
            actionShowAll();
        }
    }

    public void actionEdit() {
        try {
            int id = Integer.parseInt(textId.getText());
            String name_of_adv = String.valueOf(choiceNameAdv.getValue());
            String name_of_perfume = textNamePerfume.getText();
            float price_of_adv = Float.parseFloat(textPrice.getText());
            Date date_of_adv = createDate(String.valueOf(datePicker.getValue()));
            boolean is_paid = checkPaid.isSelected();
            if (name_of_perfume.trim().length()==0){
                throw new Exception();
            }
            new DBHandler().updateAdvertisement(new Advertisement(id,name_of_adv, name_of_perfume, price_of_adv, date_of_adv, is_paid));
        } catch (NumberFormatException e) {
            Animation.animateNode(textPrice);
            Animation.animateNode(textId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            Animation.animateNode(datePicker);
        } catch (Exception e) {
            Animation.animateNode(textNamePerfume);
        } finally {
            actionShowAll();
        }
    }
    private void actionShowAll() {
        try {
            labelAll.setText(new DBHandler().selectAdvertisement());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        textNamePerfume.setText("");
        textId.setText("");
        textPrice.setText("");
        textSearchByName.setText("");
    }
    public void actionDelete() {
        try{
            int id = Integer.parseInt(textId.getText());
            Advertisement advertisement = new Advertisement();
            advertisement.setId(id);
            new DBHandler().deleteAdvertisement(advertisement);
        } catch (NumberFormatException e){
            Animation.animateNode(textId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            actionShowAll();
        }
    }
    public void actionShowByName() {
        String name_of_perfume = textSearchByName.getText();
        if (name_of_perfume.trim().length()==0){
            actionShowAll();
        }
        else {
            Advertisement advertisement = new Advertisement();
            advertisement.setName_of_perfume(name_of_perfume);
            try {
                String answer = new DBHandler().selectAdvertisementByName(advertisement);
                labelAll.setText(answer);
            } catch (SQLException e) {
                labelAll.setText(name_of_perfume);
            }
        }
    }
    private Date createDate(String string) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(string);
        return new Date(parsed.getTime());
    }
}
