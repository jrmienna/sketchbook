package ov1.calculator;

import javafx.scene.input.KeyEvent;

public class Controller {

    public void clear() {
        System.out.println("clear");
    }

    public void divide() {
        System.out.println("divide");
    }

    public void multiply() {
        System.out.println("multiply");
    }

    public void subtract() {
        System.out.println("subtract");
    }

    public void add() {
        System.out.println("add");
    }

    public void getResult() {
        System.out.println("result");
    }

    public void handleKeyTyped(KeyEvent event) {
        if ("+".equals(event.getCharacter())) {
            add();
        } else if ("-".equals(event.getCharacter())) {
            subtract();
        } else if ("*".equals(event.getCharacter())) {
            multiply();
        } else if ("/".equals(event.getCharacter())) {
            divide();
        } else if ("=".equals(event.getCharacter())) {
            getResult();
        } else if ("c".equals(event.getCharacter())) {
            clear();
        }

    }
}
