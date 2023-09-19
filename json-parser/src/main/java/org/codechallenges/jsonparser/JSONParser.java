package org.codechallenges.jsonparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class JSONParser {


    public boolean isValidJson(String jsonString) {
        if (jsonString ==null  || jsonString.length() <=0) return false;

        List<String> tokens = tokenize(jsonString);

        return parseJsonTokens(tokens);
    }

    private List<String> tokenize(String json) {
        List<String> tokens = new ArrayList();
        for (int i=0; i<json.length(); i++) {
            if (json.charAt(i) == TokenConstant.START_OBJECT
                    || json.charAt(i) == TokenConstant.END_OBJECT
                    || json.charAt(i) == TokenConstant.COLLON
                    || json.charAt(i) == TokenConstant.COMMA) {
                tokens.add(String.valueOf(json.charAt(i)));
            } else if (json.charAt(i) == TokenConstant.DOUBLE_QUOTE) {
                tokens.add(json.substring(i+1, json.indexOf(TokenConstant.DOUBLE_QUOTE, i+1)));
                i = json.indexOf(TokenConstant.DOUBLE_QUOTE, i+1);
            }
        }

        return tokens;
    }
    /*
    {"aa": "bb", "cc": "dd",}
    */

    private boolean parseJsonTokens(List<String> tokens) {
        Stack<String> syntaxChecker = new Stack();
        for (String token : tokens) {
            if (!syntaxChecker.isEmpty() && token.equals(String.valueOf(TokenConstant.END_OBJECT))) {
                if (!syntaxChecker.isEmpty() && syntaxChecker.peek().equals(String.valueOf(TokenConstant.COMMA))) {
                    return false;
                }
                boolean lastCommaCheck = true;
                while(!syntaxChecker.isEmpty() && !syntaxChecker.peek().equals(String.valueOf(TokenConstant.START_OBJECT))) {

                    if (!lastCommaCheck) {
                        if (!syntaxChecker.peek().equals(String.valueOf(TokenConstant.COMMA))) {
                            return false;
                        } else {
                            popIfNotEmpty(syntaxChecker);
                        }
                    }

                   String value = popIfNotEmpty(syntaxChecker);
                   String keyValueSep = popIfNotEmpty(syntaxChecker);
                   String key = popIfNotEmpty(syntaxChecker);


                   if (!(key != null && (keyValueSep!=null && TokenConstant.COLLON.equals(keyValueSep.charAt(0))) && value != null)) {
                       return false;
                   }
                    lastCommaCheck = false;
                }
                if (!syntaxChecker.isEmpty()) {
                    syntaxChecker.pop();
                }
            } else {
                syntaxChecker.add(token);
            }
        }

        return syntaxChecker.isEmpty();
    }

    private String popIfNotEmpty(Stack<String> syntaxChecker) {
        return !syntaxChecker.isEmpty()  ? syntaxChecker.pop() : null;
    }



}
