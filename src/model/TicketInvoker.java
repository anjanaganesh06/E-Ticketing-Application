// model/TicketInvoker.java
package model;

public class TicketInvoker {
    private TicketCommand command;

    public void setCommand(TicketCommand command) {
        this.command = command;
    }

    public void run() {
        if (command != null) {
            command.execute();
        }
    }
}
