package com.zjc.demo1;

import com.zjc.demo1.command.LightOffCommand;
import com.zjc.demo1.command.LightOnCommand;
import com.zjc.demo1.command.StereoOffWithCDCommand;
import com.zjc.demo1.command.StereoOnWithCDCommand;
import com.zjc.demo1.executor.RemoteControl;
import com.zjc.demo1.requester.Light;
import com.zjc.demo1.requester.Stereo;

public class CommandPatternTest {

    public static void main(String[] args) {

        RemoteControl remoteControl = new RemoteControl();

        Light light = new Light();
        Stereo stereo = new Stereo();

        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);

        StereoOnWithCDCommand stereoOnWithCDCommand = new StereoOnWithCDCommand(stereo);
        StereoOffWithCDCommand stereoOffWithCDCommand = new StereoOffWithCDCommand(stereo);

        remoteControl.setCommand(lightOnCommand, lightOffCommand);
        remoteControl.onButtonPressed();
        remoteControl.offButtonPressed();

        System.out.println();

        remoteControl.setCommand(stereoOnWithCDCommand,stereoOffWithCDCommand);
        remoteControl.onButtonPressed();
        remoteControl.offButtonPressed();
    }
}