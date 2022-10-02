import java.util.ArrayList;

public abstract class ConfigFactory {

//    The configuration reader must use the Factory Method pattern with a factory for each section of theJSON file.
    public ConfigFactory(){}

    public abstract ArrayList<Object> readFile(String path);
}
