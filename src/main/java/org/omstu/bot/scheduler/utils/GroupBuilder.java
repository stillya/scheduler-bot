package org.omstu.bot.scheduler.utils;

public class GroupBuilder {

    public static Integer setGroup(String group) {
        switch (group) {
            case "PIN-201":
                return 749;
            case "PIN-202":
                return 750;
            default:
                return 000;
        }
    }
}
