package beans.so.config.rules;

import java.io.PrintStream;
import static java.lang.System.getProperty;
import static java.lang.System.out;
import java.lang.reflect.Method;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.compile;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import service.IAttributes;

/**
 *
 * @author savin
 */
public final class RuleUtils {

    private static final Pattern PATTERN_1 = compile("[=]|[+]|[-]|[*]|[/]|[(]|[)]|[\\d]|[\\w\\-\\,\\(\\)\\:]+[\\[\\d\\]]+");
    private static final Pattern PATTERN_2 = compile("[\\w\\-\\,\\(\\)\\:]+([\\[\\d\\]]+)");
    private static final ScriptEngine ENGINE = new ScriptEngineManager().getEngineByName("JS");

    private RuleUtils() {
    }

    public static boolean checking(String formula, IAttributes line) throws Exception {
        String encoding = getProperty("console.encoding", "utf-8");
        PrintStream ps = new PrintStream(out, true, encoding);
        ps.println("Формула: " + formula);

        //1. Преобразуем исходную формулу в атрибут-формулу  
        StringBuilder s = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        Matcher mm = PATTERN_1.matcher(formula);
        while (mm.find()) {
            Matcher mm2 = PATTERN_2.matcher(mm.group());
            if (mm2.matches()) {
                //System.out.println(m2.group(1));
                s2.append("attribute");
                s2.append(mm2.group(1).replaceAll("[\\[]|[\\]]", ""));
                s.append(s2);
                s2.delete(0, s2.length());
            } else {
                //System.out.println(m.group());
                s.append(mm.group());
            }
        }
        System.out.println(s.toString());
//        while (mm.find()) {
//            Matcher mm2 = PATTERN_2.matcher(mm.group());
//            if (mm2.matches()) {
//                s2.append("attribute");
//                s2.append(mm.group().replaceAll("[\\[]|[\\]]", ""));
//                s.append(s2);
//                s2.delete(0, s2.length());
//            } else {
//                s.append(mm.group());
//            }
//        }
        //2. Определим тело формулы и атрибут-результат
        StringTokenizer st = new StringTokenizer(s.toString(), "=");
        String attributeResult = null;
        String attributeFormula = null;
        int i = 0;

        while (st.hasMoreTokens()) {
            i++;
            switch (i) {
                case 1:
                    attributeResult = st.nextToken();
                    break;
                case 2:
                    attributeFormula = st.nextToken();
                    break;
                default:
                    st.nextToken();
                    break;
            }
        }
        if (attributeResult == null || attributeFormula
                == null) {
            throw new RuntimeException("Ошибка в формуле!");
        }

        //3. Проверим есть ли сеттер в строке заказа для атрибут-результата
        StringBuilder methodName = new StringBuilder();
        //Сначала запишем значение
        methodName.append("set");
        methodName.append(attributeResult.substring(0, 1).toUpperCase());
        methodName.append(attributeResult.substring(1));
        Method m = line.getClass().getMethod(methodName.toString(), String.class);
        m.invoke(line, "123");
        methodName.delete(0, methodName.length());
        //А теперь попробуем вернуть значение
        methodName.append("get");
        methodName.append(attributeResult.substring(0, 1).toUpperCase());
        methodName.append(attributeResult.substring(1));
        Method m2 = line.getClass().getMethod(methodName.toString(), (Class<?>[]) null);
        methodName.delete(0, methodName.length());
        if (m2.invoke(line, (Object[]) null).toString().equals("123")) {
            ps.println("Атрибут-результат " + attributeResult + " проверку прошел успешно");
            methodName.delete(0, methodName.length());
        }

        //4. Проверим тело формулы и, если все ОК, значит можно фиксировать в БД
        //Запишем в атрибуты строки заказа 1 - если все сеттеры есть - БОМБА! 
        Pattern p = compile("(attribute)[0-9]+");
        Matcher mm3 = p.matcher(attributeFormula);

        while (mm3.find()) {
            methodName.append("set");
            methodName.append(mm3.group().substring(0, 1).toUpperCase());
            methodName.append(mm3.group().substring(1));
            Method m3 = line.getClass().getMethod(methodName.toString(), String.class);
            m3.invoke(line, "1");
            methodName.delete(0, methodName.length());
        }
        //Теперь считаем эти атрибуты через геттеры строки заказа и запишем в StringBuffer;
        StringBuffer result = new StringBuffer();

        mm3.reset();

        while (mm3.find()) {
            methodName.append("get");
            methodName.append(mm3.group().substring(0, 1).toUpperCase());
            methodName.append(mm3.group().substring(1));
            Method m4 = line.getClass().getMethod(methodName.toString(), (Class<?>[]) null);
            mm3.appendReplacement(result, m4.invoke(line, (Object[]) null).toString());
            methodName.delete(0, methodName.length());
        }

        mm3.appendTail(result);

        ps.println("результат: " + ENGINE.eval(result.toString()));
        if (ENGINE.eval(result.toString()) instanceof Number) {
            ps.println("Формула " + formula + " проверку прошла успешно");
            return true;
        }
        return false;
    }
}
