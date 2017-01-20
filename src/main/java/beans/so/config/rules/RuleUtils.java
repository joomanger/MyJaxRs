package beans.so.config.rules;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.compile;
import javax.faces.context.FacesContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import service.IAttributes;
import service.LogUtils;

/**
 *
 * @author savin
 */
class RuleUtils {

    private static final Logger LOG = Logger.getLogger(LogUtils.getClassName());
    private static final Pattern PATTERN_1 = compile("(\\$(.+?)\\$)");
    private static final Pattern PATTERN_2 = compile("([\\|\\>\\<\\&\\#\\.\\=\\-\\(\\)\\+\\*\\/\\d\\:\\?])|(\\[([0-9]+)\\])|(Number|toFixed|Math|round)|('(.+?)')");
    private static final ScriptEngine ENGINE = new ScriptEngineManager().getEngineByName("JS");

    private static String attributeResult = null;
    private static String attributeFormula = null;

    private RuleUtils() {
    }

    /*Возвращает строковый массив из двух элементов [attributeResult,attributeFormula]*/
    public static synchronized String[] getFormulaElemets(String formula, IAttributes line) {
        checking(formula, line);
        String[] elems = {attributeResult, attributeFormula};
        return elems;
    }

    private static void checking(String formula, IAttributes line) {
        MDC.put("user_name", FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        LOG.debug("Входная формула: " + formula.replaceAll("\\'", "\""));
        LOG.debug("1. Преобразуем исходную формулу в атрибут-формулу");
        StringBuilder s = new StringBuilder();

        String ss = PATTERN_1.matcher(formula).replaceAll("");
        Matcher mm = PATTERN_2.matcher(ss);
        while (mm.find()) {
            if (mm.group(1) != null) {
                s.append(mm.group(1));
            }

            if (mm.group(3) != null) {
                s.append("attribute").append(mm.group(3));
            }
            if (mm.group(4) != null) {
                s.append(mm.group(4));
            }
            if (mm.group(5) != null) {
                s.append(mm.group(5));
            }
        }

        LOG.debug("Атрибут-формула: " + s.toString().replaceAll("\\'", "\""));

        LOG.debug("2. Определим тело формулы и атрибут-результат");
        StringTokenizer st = new StringTokenizer(s.toString(), "#");

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
            LOG.error("Ошибка в формуле!",new RuntimeException("Ошибка в формуле!"));

            //throw new RuntimeException("Ошибка в формуле!");
        }

        LOG.debug("attributeResult=" + attributeResult.replaceAll("\\'", "\""));
        LOG.debug("attributeFormula=" + attributeFormula.replaceAll("\\'", "\""));

        LOG.debug("3. Проверим есть ли сеттер/геттер в строке заказа для атрибут-результата");
        StringBuilder methodName = new StringBuilder();
        //Сначала запишем значение

        methodName.append("set");
        methodName.append(attributeResult.substring(0, 1).toUpperCase());
        methodName.append(attributeResult.substring(1));
        try {
            Method m = line.getClass().getMethod(methodName.toString(), String.class);
            m.invoke(line, "123");
        } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException ex) {
            LOG.error("Ошибка при проверки сеттера атрибут-результата " + methodName.toString(),ex);

            //throw new RuntimeException("Ошибка при проверки сеттера атрибут-результата " + methodName.toString() + " " + ex.getMessage());
        }

        methodName.delete(0, methodName.length());
        //А теперь попробуем вернуть значение
        methodName.append("get");
        methodName.append(attributeResult.substring(0, 1).toUpperCase());
        methodName.append(attributeResult.substring(1));
        try {
            Method m2 = line.getClass().getMethod(methodName.toString(), (Class<?>[]) null);
            methodName.delete(0, methodName.length());
            if (m2.invoke(line, (Object[]) null).toString().equals("123")) {
                LOG.debug("Атрибут-результат " + attributeResult + " проверку прошел успешно");
                methodName.delete(0, methodName.length());
            }
        } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException ex) {
            LOG.error("Ошибка при проверки геттера атрибут-результата " + methodName.toString());
            throw new RuntimeException("Ошибка при проверки геттера атрибут-результата " + methodName.toString() + " " + ex.getMessage());
        }

        LOG.debug("4. Проверим тело формулы и, если все ОК, значит можно фиксировать в БД");
        //Запишем в атрибуты строки заказа 1 - если все сеттеры есть - БОМБА! 
        Pattern p = compile("(attribute)[0-9]+");
        Matcher mm3 = p.matcher(attributeFormula);

        while (mm3.find()) {
            methodName.append("set");
            methodName.append(mm3.group().substring(0, 1).toUpperCase());
            methodName.append(mm3.group().substring(1));
            try {
                Method m3 = line.getClass().getMethod(methodName.toString(), String.class);
                m3.invoke(line, "1");
            } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException ex) {
                LOG.error("Ошибка при проверки cеттера атрибут-формулы " + methodName.toString());

                throw new RuntimeException("Ошибка при проверки сеттера атрибут-формулы " + methodName.toString() + " " + ex.getMessage());
            }
            methodName.delete(0, methodName.length());
        }
        //Теперь считаем эти атрибуты через геттеры строки заказа и запишем в StringBuffer;
        StringBuffer result = new StringBuffer();

        mm3.reset();

        while (mm3.find()) {
            methodName.append("get");
            methodName.append(mm3.group().substring(0, 1).toUpperCase());
            methodName.append(mm3.group().substring(1));
            try {
                Method m4 = line.getClass().getMethod(methodName.toString(), (Class<?>[]) null);
                mm3.appendReplacement(result, m4.invoke(line, (Object[]) null).toString());
            } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException ex) {
                LOG.error("Ошибка при проверки геттера атрибут-формулы " + methodName.toString());

                throw new RuntimeException("Ошибка при проверки геттера атрибут-формулы " + methodName.toString() + " " + ex.getMessage());
            }
            methodName.delete(0, methodName.length());
        }

        mm3.appendTail(result);

        LOG.debug("выражение для JS: " + result.toString().replaceAll("\\'", "\""));
        try {
            LOG.debug("Результат: " + ENGINE.eval(result.toString()));
        } catch (ScriptException ex) {
            LOG.error("Ошибка выполнения формулы. " + ex.getMessage());

            throw new RuntimeException("Ошибка выполнения формулы. " + ex.getMessage());
        }

        LOG.debug("Формула " + formula.replaceAll("\\'", "\"") + " проверку прошла успешно");
    }
}
//public final class RuleUtils {
//
//    private static final Pattern PATTERN_1 = compile("(\\$(.+?)\\$)");
//    private static final Pattern PATTERN_2 = compile("([\\|\\>\\<\\&\\#\\.\\=\\-\\(\\)\\+\\*\\/\\d\\:\\?])|(\\[([0-9]+)\\])|(Number|toFixed|Math|round)|('(.+?)')");
//    private static final ScriptEngine ENGINE = new ScriptEngineManager().getEngineByName("JS");
//    
//    private static String attributeResult;
//    private static String attributeFormula;
//    
//    private RuleUtils() {
//    }
//    
//    
//
//    private static void ttt(String formula, IAttributes line) throws Exception{
//        String encoding = getProperty("console.encoding", "utf-8");
//        PrintStream ps = new PrintStream(out, true, encoding);
//        LOG.debug("Формула: " + formula);
//
//        LOG.debug("1. Преобразуем исходную формулу в атрибут-формулу");
//        StringBuilder s = new StringBuilder();
//
//        String ss = PATTERN_1.matcher(formula).replaceAll("");
//        Matcher mm = PATTERN_2.matcher(ss);
//        while (mm.find()) {
//            if (mm.group(1) != null) {
//                s.append(mm.group(1));
//            }
//
//            if (mm.group(3) != null) {
//                s.append("attribute").append(mm.group(3));
//            }
//            if (mm.group(4) != null) {
//                s.append(mm.group(4));
//            }
//            if (mm.group(5) != null) {
//                s.append(mm.group(5));
//            }
//        }
//
//        LOG.debug("Атрибут-формула: " + s.toString());
//
//        LOG.debug("2. Определим тело формулы и атрибут-результат");
//
//        StringTokenizer st = new StringTokenizer(s.toString(), "#");
//        
//        int i = 0;
//
//        while (st.hasMoreTokens()) {
//            i++;
//            switch (i) {
//                case 1:
//                    attributeResult = st.nextToken();
//                    break;
//                case 2:
//                    attributeFormula = st.nextToken();
//                    break;
//                default:
//                    st.nextToken();
//                    break;
//            }
//        }
//
//        LOG.debug("attributeResult=" + attributeResult);
//        LOG.debug("attributeFormula=" + attributeFormula);
//    }
//
//    public static synchronized boolean checking(String formula, IAttributes line) throws Exception {
//        String encoding = getProperty("console.encoding", "utf-8");
//        PrintStream ps = new PrintStream(out, true, encoding);
//        LOG.debug("Формула: " + formula);
//
//        LOG.debug("1. Преобразуем исходную формулу в атрибут-формулу");
//        StringBuilder s = new StringBuilder();
//
//        String ss = PATTERN_1.matcher(formula).replaceAll("");
//        Matcher mm = PATTERN_2.matcher(ss);
//        while (mm.find()) {
//            if (mm.group(1) != null) {
//                s.append(mm.group(1));
//            }
//
//            if (mm.group(3) != null) {
//                s.append("attribute").append(mm.group(3));
//            }
//            if (mm.group(4) != null) {
//                s.append(mm.group(4));
//            }
//            if (mm.group(5) != null) {
//                s.append(mm.group(5));
//            }
//        }
//
//        LOG.debug("Атрибут-формула: " + s.toString());
//
//        LOG.debug("2. Определим тело формулы и атрибут-результат");
//
//        StringTokenizer st = new StringTokenizer(s.toString(), "#");
////        String attributeResult = null;
////        String attributeFormula = null;
//        int i = 0;
//
//        while (st.hasMoreTokens()) {
//            i++;
//            switch (i) {
//                case 1:
//                    attributeResult = st.nextToken();
//                    break;
//                case 2:
//                    attributeFormula = st.nextToken();
//                    break;
//                default:
//                    st.nextToken();
//                    break;
//            }
//        }
//
//        LOG.debug("attributeResult=" + attributeResult);
//        LOG.debug("attributeFormula=" + attributeFormula);
//
//        if (attributeResult == null || attributeFormula
//                == null) {
//            throw new RuntimeException("Ошибка в формуле!");
//        }
//
//        LOG.debug("3. Проверим есть ли сеттер/геттер в строке заказа для атрибут-результата");
//        StringBuilder methodName = new StringBuilder();
//        //Сначала запишем значение
//        methodName.append("set");
//        methodName.append(attributeResult.substring(0, 1).toUpperCase());
//        methodName.append(attributeResult.substring(1));
//        Method m = line.getClass().getMethod(methodName.toString(), String.class);
//        m.invoke(line, "123");
//        methodName.delete(0, methodName.length());
//        //А теперь попробуем вернуть значение
//        methodName.append("get");
//        methodName.append(attributeResult.substring(0, 1).toUpperCase());
//        methodName.append(attributeResult.substring(1));
//        Method m2 = line.getClass().getMethod(methodName.toString(), (Class<?>[]) null);
//        methodName.delete(0, methodName.length());
//        if (m2.invoke(line, (Object[]) null).toString().equals("123")) {
//            LOG.debug("Атрибут-результат " + attributeResult + " проверку прошел успешно");
//            methodName.delete(0, methodName.length());
//        }
//
//        LOG.debug("4. Проверим тело формулы и, если все ОК, значит можно фиксировать в БД");
//        //Запишем в атрибуты строки заказа 1 - если все сеттеры есть - БОМБА! 
//        Pattern p = compile("(attribute)[0-9]+");
//        Matcher mm3 = p.matcher(attributeFormula);
//
//        while (mm3.find()) {
//            methodName.append("set");
//            methodName.append(mm3.group().substring(0, 1).toUpperCase());
//            methodName.append(mm3.group().substring(1));
//            Method m3 = line.getClass().getMethod(methodName.toString(), String.class);
//            m3.invoke(line, "1");
//            methodName.delete(0, methodName.length());
//        }
//        //Теперь считаем эти атрибуты через геттеры строки заказа и запишем в StringBuffer;
//        StringBuffer result = new StringBuffer();
//
//        mm3.reset();
//
//        while (mm3.find()) {
//            methodName.append("get");
//            methodName.append(mm3.group().substring(0, 1).toUpperCase());
//            methodName.append(mm3.group().substring(1));
//            Method m4 = line.getClass().getMethod(methodName.toString(), (Class<?>[]) null);
//            mm3.appendReplacement(result, m4.invoke(line, (Object[]) null).toString());
//            methodName.delete(0, methodName.length());
//        }
//
//        mm3.appendTail(result);
//
//        LOG.debug("expression for JS: " + result.toString());
//
//        LOG.debug("результат: " + ENGINE.eval(result.toString()));
//
//        LOG.debug("Формула " + formula + " проверку прошла успешно");
//
//        return true;
//
//    }
//}
