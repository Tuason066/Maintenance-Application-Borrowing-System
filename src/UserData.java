
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class UserData {
    private static UserData instance = null;
    
    private ArrayList<String> checkBoxItems = new ArrayList<>();
    
    // Private constructor to prevent creating instances outside this class
    private UserData() {
    }

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }

    public ArrayList<String> getCheckBoxItems() {
        return checkBoxItems;
    }

    public void setCheckBoxItems(ArrayList<String> checkBoxItems) {
        this.checkBoxItems = checkBoxItems;
    }
    
    public void addCheckBoxItems(String item) {
        // checkBoxItems.add(chk.getText());
        this.checkBoxItems.add(item);
    }
    
    public void removeCheckBoxItems(String item) {
        // checkBoxItems.add(chk.getText());
        this.checkBoxItems.remove(item);
    }

    public void resetCheckBoxItems() {
        this.checkBoxItems = new ArrayList<>();
    }
}
