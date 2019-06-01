package mcu;

public class CommunicationEvent {
    private Communication communication;

    public CommunicationEvent(Communication communication) {
        super();
        this.communication = communication;
    }

    public CommunicationEvent() {
        super();
    }

    public Communication getCommunication() {
        return communication;
    }
    public void setCommunication(Communication communication) {
        this.communication = communication;
    }


}
