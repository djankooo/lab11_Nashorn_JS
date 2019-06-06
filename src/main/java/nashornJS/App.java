package nashornJS;


import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

public class App
{
    static ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    public static void main( String[] args ) throws Exception {

        System.out.println( "Hello from App" );

        engine.eval(new FileReader("src\\main\\java\\nashornJS\\Window.js"));
        Invocable invocable = (Invocable) engine;
        Object result = invocable.invokeFunction("openWindow");
    }
}
