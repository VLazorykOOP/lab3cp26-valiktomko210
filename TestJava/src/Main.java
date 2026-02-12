import java.util.ArrayList;
import java.util.List;

// =======================
// Component (Composite)
// =======================
interface Component {
    void show(String indent);
}

// =======================
// File (Prototype)
// =======================
class MyFile implements Component, Cloneable {

    private String name;

    public MyFile(String name) {
        this.name = name;
    }

    @Override
    public void show(String indent) {
        System.out.println(indent + "File: " + name);
    }

    @Override
    public MyFile clone() {
        try {
            return (MyFile) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

// =======================
// Folder (Composite)
// =======================
class Folder implements Component {

    private String name;
    private List<Component> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void show(String indent) {
        System.out.println(indent + "Folder: " + name);
        for (Component component : components) {
            component.show(indent + "   ");
        }
    }
}

// =======================
// Template Method
// =======================
abstract class AbstractPrinter {

    public final void print(Component component) {
        start();
        component.show("");
        end();
    }

    protected abstract void start();
    protected abstract void end();
}

// =======================
// Concrete Template
// =======================
class ConsolePrinter extends AbstractPrinter {

    @Override
    protected void start() {
        System.out.println("=== Start Printing Structure ===");
    }

    @Override
    protected void end() {
        System.out.println("=== End Printing Structure ===");
    }
}

// =======================
// Main
// =======================
public class Main {

    public static void main(String[] args) {

        // Prototype
        MyFile file1 = new MyFile("document.txt");
        MyFile file2 = file1.clone(); // клон
        MyFile file3 = new MyFile("image.png");

        // Composite
        Folder root = new Folder("Root");
        Folder subFolder = new Folder("SubFolder");

        subFolder.add(file1);
        subFolder.add(file2);

        root.add(subFolder);
        root.add(file3);

        // Template Method
        ConsolePrinter printer = new ConsolePrinter();
        printer.print(root);
    }
}
