package mcu;

public interface CommunicationListener {
    public void doReceiveQmNumber(CommunicationEvent event);
    public void doBrokenQmNumber(CommunicationEvent event);
}
