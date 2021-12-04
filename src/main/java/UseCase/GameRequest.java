package UseCase;

import java.util.ArrayList;

public class GameRequest {

    private String setColor;

    private String setColorForComputer;

    private ArrayList<String> ids;


    public void setSetColor(String setColor) {
        this.setColor = setColor;
    }

    public String getSetColor() {
        return setColor;
    }

    public void setSetColorForComputer(String setColorForComputer) {
        this.setColorForComputer = setColorForComputer;
    }

    public String getSetColorForComputer() {
        return setColorForComputer;
    }

    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }

    public ArrayList<String> getIds() {
        return ids;
    }

}

