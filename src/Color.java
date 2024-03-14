public enum Color {//  This class enumarates all the colors with the string to get them.
    red ("red"),
    blue("blue"),
    green("green"),
    yellow("yellow"),
    orange("orange");

    private final String color;

    Color (String string){ // Constructor of color.
        this.color = string;
    }
}