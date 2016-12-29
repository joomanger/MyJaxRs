package so.config.rules;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptException;
import service.IAttributes;

/**
 *
 * @author savin
 */
public class NumericRule implements IRule {

    private final IAttributes attributes;
    private final String formula;
    private final String attributeResult;
    private StringBuffer methodName;
    private StringBuffer result;

    public NumericRule(IAttributes attributes, String formula, String attributeResult) {
        if (attributes == null || formula == null || attributeResult == null) {
            throw new RuntimeException("NumericRuleException! Parameters are not validated");
        }
        this.attributes = attributes;
        this.formula = formula;
        this.attributeResult = attributeResult;
    }

    public void test() {
        StringBuilder s = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        String formula = "((12-5)*Толщина, мм[1]+Ширина, мм[5])/Длина, мм[12]*(400*10000000)+(1+2-9999)";
        Pattern pp = Pattern.compile("(\\+|\\-|\\/|\\*|\\(|\\)|\\%)|[\\[\\d\\]]+");
        Pattern pp2 = Pattern.compile("[\\[][\\d]+[\\]]");
        Matcher mm = pp.matcher(formula);

        while (mm.find()) {
            Matcher mm2 = pp2.matcher(mm.group());
            if (mm2.matches()) {
                s2.append("attribute");
                s2.append(mm.group().replaceAll("[\\[]|[\\]]", ""));
                s.append(s2);
                s2.delete(0, s2.length());
            } else {
                s.append(mm.group());
            }
        }
        System.out.println("FORMULA: " + s.toString());
    }

    @Override
    public void calculation() {
        try {
            methodName = new StringBuffer();
            result = new StringBuffer();
            Matcher mm = RuleUtils.ATTRIBUTE.matcher(formula);
            while (mm.find()) {
                methodName.append("get");
                methodName.append(mm.group().substring(0, 1).toUpperCase());
                methodName.append(mm.group().substring(1));
                Method m = attributes.getClass().getMethod(methodName.toString(), (Class<?>[]) null);
                try {
                    mm.appendReplacement(result, m.invoke(attributes, (Object[]) null).toString());
                } catch (java.lang.NullPointerException ex) {
                    mm.appendReplacement(result, "0");
                }

                methodName.delete(0, methodName.length());
            }
            mm.appendTail(result);
            //System.out.println("formula: " + result.toString());
            /*Зафиксируем результат*/
            methodName.append("set");
            methodName.append(attributeResult.substring(0, 1).toUpperCase());
            methodName.append(attributeResult.substring(1));
            //System.out.println(me thodName.toString());
            Method m = attributes.getClass().getMethod(methodName.toString(), String.class);
            m.invoke(attributes, RuleUtils.ENGINE.eval(result.toString()).toString());
            methodName.delete(0, methodName.length());

            //return RuleUtils.ENGINE.eval(result.toString()).toString();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | RuntimeException | ScriptException ex) {
            throw new RuntimeException("NumericRuleException! Formula: " + result.toString() + " " + ex.toString());
        }
    }
}
