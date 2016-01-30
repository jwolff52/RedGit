package io.github.jwolff52.redgit.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogHelper {
    private static final Logger logger = Logger.getLogger("RedGit");

    private static void log(Level level, String message) {
        logger.log(level, message);
    }

    public static void off(String message) {
        log(Level.OFF, message);
    }

    public static void severe(String message) {
        log(Level.SEVERE, message);
    }

    public static void warning(String message) {
        log(Level.WARNING, message);
    }

    public static void info(String message) {
        log(Level.INFO, message);
    }

    public static void config(String message) {
        log(Level.CONFIG, message);
    }

    public static void fine(String message) {
        log(Level.FINEST, message);
    }

    public static void finer(String message) {
        log(Level.FINER, message);
    }

    public static void finest(String message) {
        log(Level.FINEST, message);
    }

    public static void all(String message) {
        log(Level.ALL, message);
    }
}
