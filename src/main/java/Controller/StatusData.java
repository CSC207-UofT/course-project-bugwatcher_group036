package Controller;

import UseCase.Status;

public class StatusData {

    private Status status;

    public StatusData(int numberOfPlayers) {
        this.status = new Status(numberOfPlayers);
    }

    public StatusData(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
