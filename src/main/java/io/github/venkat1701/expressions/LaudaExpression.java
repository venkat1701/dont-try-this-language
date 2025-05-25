package io.github.venkat1701.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import io.github.venkat1701.exceptions.LundLock;

public class LaudaExpression {

    private final String expressionLine;
    private final int lineNumber;

    public LaudaExpression(String expressionLine, int lineNumber) {
        this.expressionLine = expressionLine;
        this.lineNumber = lineNumber;
    }


    public String parseAndExecute(Map<String, Integer> variableStore) {
        try{
            if(expressionLine.isEmpty() || expressionLine.startsWith("cum") || expressionLine.split(" ")[0].equals("cum")) {
                return "";
            }

            if(expressionLine.startsWith("lauda yeh hai")) {
                return handleDeclaration(expressionLine, variableStore);
            }

            if(expressionLine.startsWith("gawk")) {
                return handlePrint(expressionLine, variableStore);
            }

            return new LundLock(""+lineNumber).getError();
        } catch(Exception e){
            return new LundLock(""+lineNumber).getError();
        }
    }

    public String handleDeclaration(String expressionLine, Map<String, Integer> store) {
        String[] tokens = expressionLine.replace("lauda yeh hai", "").trim().split("=");
        if(tokens.length != 2) {
            return new LundLock(""+lineNumber).getError();
        }
        String variableName = tokens[0].trim();
        int variableValue = Integer.parseInt(tokens[1].trim());
        store.put(variableName, variableValue);
        return "";
    }

    public String handlePrint(String expressionLine, Map<String, Integer> variableStore) {
        int start = expressionLine.indexOf("(");
        int end = expressionLine.lastIndexOf(")");
        if(start == -1 || end == -1 || start > end) {
            return new LundLock(""+lineNumber).getError();
        }

        String expr = expressionLine.substring(start+1, end);
        return String.valueOf(evaluateExpression(expr, variableStore));
    }

    public int evaluateExpression(String expression, Map<String, Integer> variableStore) {
        String[] tokens = expression.split("\\+");
        int sum = 0;
        for(String token : tokens) {
            token = token.trim();
            if(variableStore.containsKey(token)) {
                sum += variableStore.get(token);
            } else {
                sum += Integer.parseInt(token);
            }
        }
        return sum;
    }

}
