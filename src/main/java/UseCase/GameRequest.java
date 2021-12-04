package UseCase;

import java.util.ArrayList;

public class GameRequest {

    private String setColor;

    private String setColorForComputer;

    private ArrayList<String> ids;

    /**
     * setter method for setColor
     */
    public void setSetColor(String setColor) {
        this.setColor = setColor;
    }
    /**
     * getter method for setColor
     */
    public String getSetColor() {
        return setColor;
    }
    /**
     * setter method for setColorForComputer
     */
    public void setSetColorForComputer(String setColorForComputer) {
        this.setColorForComputer = setColorForComputer;
    }
    /**
     * setter method for setColorForComputer
     */
    public String getSetColorForComputer() {
        return setColorForComputer;
    }
    /**
     * setter method for ids
     */
    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }
    /**
     * getter method for ids
     */
    public ArrayList<String> getIds() {
        return ids;
    }

}

