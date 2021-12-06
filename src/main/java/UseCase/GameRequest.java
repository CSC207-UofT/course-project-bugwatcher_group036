package UseCase;

/**
 * THe GameRequest.
 */
public class GameRequest implements IRequest{

    private String setColor;

    private String setColorForComputer;

    public GameRequest() {}

    /**
     * setter method for setColor
     */
    public void setSetColor(String setColor) {
        this.setColor = setColor;
    }
    /**
     * getter method for setColor
     * @return the color user/computer want to set
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
     * @return the color that the computer set
     */
    public String getSetColorForComputer() {
        return setColorForComputer;
    }

}

