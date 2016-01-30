package io.github.jwolff52.redgit.util.reddit;

import io.github.jwolff52.redgit.Main;

import java.util.Timer;
import java.util.TimerTask;

public class RenewOAUthTimer extends TimerTask {

    public RenewOAUthTimer() {
        new Timer().schedule(this, 3600);
    }

    @Override
    public void run() {
        Main.renewOAuth();
    }
}
