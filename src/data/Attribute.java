package data;

abstract class Attribute {
    private final String name;
    private final int index;

    Attribute(String name, int index) {
        this.name = name;
        this.index = index;
    }

    String getName() {
        return name;
    }

    int getIndex() {
        return index;
    }

    public String toString() {
        return this.name + "\n" + this.index + "\n";
    }
}
