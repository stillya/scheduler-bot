package org.omstu.bot.scheduler.utils;

public class GroupBuilder {

    public static boolean isValidSubGroup(String subGroup) {
        switch (subGroup) {
            case "1":
            case "2":
                return true;
            default:
                return false;
        }
    }

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
