package org.codechallenges.jsonparser;

public class jp {
    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();
        if (jsonParser.isValidJson(args[0])) {
            System.exit(0);
        } else {
            System.err.println("Invalid JSON Provided");
            System.exit(1);
        }
    }
}
