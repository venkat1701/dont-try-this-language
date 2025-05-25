package io.github.venkat1701;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import io.github.venkat1701.exceptions.LundLock;
import io.github.venkat1701.expressions.LaudaExpression;

public class FileParser {

    private String fileName;

    public FileParser(String fileName) {
        this.fileName = fileName;
    }

    public void executeCode() {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int linenumber = 1;
            boolean flag = false;
            Map<String, Integer> variableStore = new HashMap<>();
            List<String> result = new ArrayList<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.startsWith("lauda start")) {
                    flag = true;
                    linenumber++;
                    continue;
                } else if(line.startsWith("lauda end")) {
                    flag = false;
                    linenumber++;
                    continue;
                } else if(line.equals("\\n"))
                    continue;
                else
                    result.add(new LaudaExpression(line, linenumber).parseAndExecute(variableStore));
                linenumber++;
            }

            for(String res : result) {
                if(res.equals("")) {
                    continue;
                } else {
                    if(res.startsWith("Lundlock")) {
                        System.out.println(res);
                        break;
                    }
                    System.out.println(res);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(new LundLock(""+0).getError());
            return;
        }

    }


}
