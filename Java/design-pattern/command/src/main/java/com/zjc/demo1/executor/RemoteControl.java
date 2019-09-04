package com.zjc.demo1.executor;

import com.zjc.demo1.command.Command;

public class RemoteControl {

    private Command onCommand;
    private Command offCommand;

    public RemoteControl() {
    }

    public void setCommand(Command onCommand, Command offCommand) {
        this.onCommand = onCommand;
        this.offCommand = offCommand;
    }

    public void onButtonPressed() {
        if (this.onCommand != null) {
            this.onCommand.execute();
        }
    }

    public void offButtonPressed() {
        if (this.offCommand != null) {
            this.offCommand.execute();
        }
    }
}