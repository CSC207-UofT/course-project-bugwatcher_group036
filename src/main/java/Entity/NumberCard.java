package Entity;

public class NumberCard extends Card {

    private int number;

    /**
     *
     * @param color
     * @param number   The card's number <0~9, -1>
     * @param id
     */
    public NumberCard(String color, int number, String id) {
        super(color, id);
        this.number = number;
    }

    public NumberCard() {
        super();
        this.number = -1;
    }

    public int getNumber(){
        return number;
    }

    @Override
    public String toString() {
        return this.getColor()  + number;
    }
}
