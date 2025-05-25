package io.github.venkat1701;

public class Main {

    public static void main(String[] args) {
        var parser = new FileParser("C:\\Users\\krish\\IdeaProjects\\laudalang-core\\src\\main\\resources\\simplelauda.lauda");
        parser.executeCode();
    }
}