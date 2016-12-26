package so.config.rules;

import java.util.regex.Pattern;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 *
 * @author savin
 */
public class RuleUtils {

    public static final Pattern ATTRIBUTE = Pattern.compile("(attribute)[0-9]+");
//    public static final Pattern NUMERIC = Pattern.compile("\\d+(\\.\\d+)?");
//    public static final Pattern OPERATION = Pattern.compile("[+-/*()]");
    public static final ScriptEngine ENGINE = new ScriptEngineManager().getEngineByName("JS");
}
