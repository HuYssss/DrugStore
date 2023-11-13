package gr2.clc.drugstore.tool;

public class message {

    public static String saveMessage(String id) {
        return "Create successful object " + id + " !!!";
    }

    public static String deleteMessage(String id) {
        return "Delete successful object " + id + " !!!";
    }

    public static String editMessage(String id) {
        return "Edit successful object " + id + " !!!";
    }

    public static String saveMessageError(String id) {
        return "Create failed object " + id + " !!!";
    }

    public static String deleteMessageError(String id) {
        return "Delete failed object " + id + " !!!";
    }

    public static String editMessageError(String id) {
        return "Edit failed object " + id + " !!!";
    }

    public static String saveMessageNull() { return "Create failed because object null !!!"; }

    public static String deleteMessageNull() { return "Delete failed because id object null !!!"; }

    public static String editMessageNull() { return "Edit failed because id object null !!!"; }

    public static String noParamMessage() { return "Id object invalid !!!"; }
}
