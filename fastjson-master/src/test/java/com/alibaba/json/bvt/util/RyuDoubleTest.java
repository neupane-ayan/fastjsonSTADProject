package com.alibaba.json.bvt.util;

import com.alibaba.fastjson.util.RyuDouble;
import junit.framework.TestCase;

import java.util.Random;

public class RyuDoubleTest extends TestCase {
    public void test_for_ryu() throws Exception {
        Random random = new Random();

        for (int i = 0; i < 1000 * 1000 * 10; ++i) {
            double value = random.nextDouble();

            String str1 = Double.toString(value);
            String str2 = RyuDouble.toString(value);

            if (!str1.equals(str2)) {
                System.out.println(str1 + " -> " + str2);
                assertTrue(Double.parseDouble(str1) == Double.parseDouble(str2));
            }
        }
    }

    public void test_0() throws Exception {
        double[] values = new double[] {
                Double.NaN,
                Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY,
                Double.MIN_VALUE,
                Double.MAX_VALUE,

                0,
                0.0d,
                -0.0d,
                Double.longBitsToDouble(0x8000000000000000L),
                Double.NaN,

                Long.MAX_VALUE,
                Long.MIN_VALUE,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                Double.longBitsToDouble(0x0010000000000000L),

                9999999.999999998d,
                0.0009999999999999998d,
                1.0E7d,
                0.001d,
                Double.longBitsToDouble(0x7fefffffffffffffL),

                Double.longBitsToDouble(1),
                -2.109808898695963E16,
                4.940656E-318d,
                1.18575755E-316d,
                2.989102097996E-312d,
                9.0608011534336E15d,
                4.708356024711512E18,
                9.409340012568248E18,
                1.8531501765868567E21,
                -3.347727380279489E33,
                1.9430376160308388E16,
                -6.9741824662760956E19,
                4.3816050601147837E18,

                1.797693134862315E308,
                1.79769313486231E308,
                1.7976931348623E308,
                1.797693134862E308,
                1.79769313486E308,
                1.7976931348E308,
                1.797693134E308,
                1.79769313E308,
                1.7976931E308,
                1.797693E308,
                1.79769E308,
                1.7976E308,
                1.797E308,
                1.79E308,
                1.7E308,
                1E308,

                1.797693134862315,
                1.79769313486231,
                1.7976931348623,
                1.797693134862,
                1.79769313486,
                1.7976931348,
                1.797693134,
                1.79769313,
                1.7976931,
                1.797693,
                1.79769,
                1.7976,
                1.797,
                1.79,
                1.7,
                1,

                -1.797693134862315,
                -1.79769313486231,
                -1.7976931348623,
                -1.797693134862,
                -1.79769313486,
                -1.7976931348,
                -1.797693134,
                -1.79769313,
                -1.7976931,
                -1.797693,
                -1.79769,
                -1.7976,
                -1.797,
                -1.79,
                -1.7,
                -1,

                0.1,
                0.01,
                0.001,
                0.0001,
                0.00001,
                0.000001,
                0.0000001,
                0.00000001,
                0.000000001,
                0.0000000001,
                0.00000000001,
                0.000000000001,
                0.0000000000001,
                0.00000000000001,

                -0.1,
                -0.01,
                -0.001,
                -0.0001,
                -0.00001,
                -0.000001,
                -0.0000001,
                -0.00000001,
                -0.000000001,
                -0.0000000001,
                -0.00000000001,
                -0.000000000001,
                -0.0000000000001,
                -0.00000000000001,

                1.1E1,
                1.1E2,
                1.1E3,
                1.1E4,
                1.1E5,
                1.1E6,
                1.1E7,
                1.1E8,
                1.1E9,
                1.1E10,

                -1.1E1,
                -1.1E2,
                -1.1E3,
                -1.1E4,
                -1.1E5,
                -1.1E6,
                -1.1E7,
                -1.1E8,
                -1.1E9,
                -1.1E10,

                49E10,
                49E100,
                49E200,
                49E300,
                49E301,
                49E302,
                49E303,
                49E304,

                49E-10,
                49E-100,
                49E-200,
                49E-300,
                49E-301,
                49E-302,
                49E-303,
                49E-304,

        };

        for (int i = 0; i < values.length; i++) {
            double value = values[i];
            String str1 = Double.toString(value);
            String str2 = RyuDouble.toString(value);

            if (!str1.equals(str2)) {
                boolean cmp = (Double.parseDouble(str1) == Double.parseDouble(str2));
                System.out.println(str1 + " -> " + str2 + " : " + cmp);
                assertTrue(cmp);
            }
        }
    }

    public void test_1() throws Exception {
        double[] values = new double[]{
                0.1,
                0.01,
                0.001,
                0.0001,
                0.00001,
                0.000001,
                0.0000001,
                0.00000001,
                0.000000001,
                0.0000000001,
                0.00000000001,
                0.000000000001,
                0.0000000000001,
                0.00000000000001,
                0.000000000000001,
                0.0000000000000001,
                0.00000000000000001,
                0.000000000000000001,
                0.0000000000000000001,
                0.00000000000000000001,
                0.000000000000000000001,
                0.0000000000000000000001,
                0.00000000000000000000001,
                0.000000000000000000000001,
                0.0000000000000000000000001,
                0.00000000000000000000000001,
                0.000000000000000000000000001,
                0.0000000000000000000000000001,
                0.00000000000000000000000000001,
                0.000000000000000000000000000001,
                0.0000000000000000000000000000001,
                0.00000000000000000000000000000001,
                0.000000000000000000000000000000001,
                0.0000000000000000000000000000000001,
                0.00000000000000000000000000000000001,
                0.000000000000000000000000000000000001,
                0.0000000000000000000000000000000000001,
                0.00000000000000000000000000000000000001,
                0.000000000000000000000000000000000000001,
                0.0000000000000000000000000000000000000001,
                0.00000000000000000000000000000000000000001,
                0.000000000000000000000000000000000000000001,
                0.0000000000000000000000000000000000000000001,
                0.00000000000000000000000000000000000000000001,
                0.000000000000000000000000000000000000000000001,
                0.0000000000000000000000000000000000000000000001,
                0.00000000000000000000000000000000000000000000001,
                0.000000000000000000000000000000000000000000000001,
                0.0000000000000000000000000000000000000000000000001,
                0.00000000000000000000000000000000000000000000000001,
                0.000000000000000000000000000000000000000000000000001,
                0.0000000000000000000000000000000000000000000000000001,
                0.00000000000000000000000000000000000000000000000000001,
                0.000000000000000000000000000000000000000000000000000001,
                0.0000000000000000000000000000000000000000000000000000001,
                0.00000000000000000000000000000000000000000000000000000001,
                0.000000000000000000000000000000000000000000000000000000001,
                0.000000000000000000000000000000000000000000000000000000001,

                -0.1,
                -0.01,
                -0.001,
                -0.0001,
                -0.00001,
                -0.000001,
                -0.0000001,
                -0.00000001,
                -0.000000001,
                -0.0000000001,
                -0.00000000001,
                -0.000000000001,
                -0.0000000000001,
                -0.00000000000001,
                -0.000000000000001,
                -0.0000000000000001,
                -0.00000000000000001,
                -0.000000000000000001,
                -0.0000000000000000001,
                -0.00000000000000000001,
                -0.000000000000000000001,
                -0.0000000000000000000001,
                -0.00000000000000000000001,
                -0.000000000000000000000001,
                -0.0000000000000000000000001,
                -0.00000000000000000000000001,
                -0.000000000000000000000000001,
                -0.0000000000000000000000000001,
                -0.00000000000000000000000000001,
                -0.000000000000000000000000000001,
                -0.0000000000000000000000000000001,
                -0.00000000000000000000000000000001,
                -0.000000000000000000000000000000001,
                -0.0000000000000000000000000000000001,
                -0.00000000000000000000000000000000001,
                -0.000000000000000000000000000000000001,
                -0.0000000000000000000000000000000000001,
                -0.00000000000000000000000000000000000001,
                -0.000000000000000000000000000000000000001,
                -0.0000000000000000000000000000000000000001,
                -0.00000000000000000000000000000000000000001,
                -0.000000000000000000000000000000000000000001,
                -0.0000000000000000000000000000000000000000001,
                -0.00000000000000000000000000000000000000000001,
                -0.000000000000000000000000000000000000000000001,
                -0.0000000000000000000000000000000000000000000001,
                -0.00000000000000000000000000000000000000000000001,
                -0.000000000000000000000000000000000000000000000001,
                -0.0000000000000000000000000000000000000000000000001,
                -0.00000000000000000000000000000000000000000000000001,
                -0.000000000000000000000000000000000000000000000000001,
                -0.0000000000000000000000000000000000000000000000000001,
                -0.00000000000000000000000000000000000000000000000000001,
                -0.000000000000000000000000000000000000000000000000000001,
                -0.0000000000000000000000000000000000000000000000000000001,
                -0.00000000000000000000000000000000000000000000000000000001,
                -0.000000000000000000000000000000000000000000000000000000001,
                -0.000000000000000000000000000000000000000000000000000000001,
        };

        for (int i = 0; i < values.length; i++) {
            double value = values[i];
            String str1 = Double.toString(value);
            String str2 = RyuDouble.toString(value);

            if (!str1.equals(str2)) {
                boolean cmp = (Double.parseDouble(str1) == Double.parseDouble(str2));
                System.out.println(str1 + " -> " + str2 + " : " + cmp);
                assertTrue(cmp);
            }
        }
    }

    public void test_2() throws Exception {
        double[] values = new double[]{
                9.223372036854799E18,
                9.223372036854798E18,
                9.223372036854797E18,
                9.223372036854796E18,
                9.223372036854795E18,
                9.223372036854794E18,
                9.223372036854793E18,
                9.223372036854792E18,
                9.223372036854791E18,
                9.223372036854790E18,

                9.223372036854789E18,
                9.223372036854788E18,
                9.223372036854787E18,
                9.223372036854786E18,
                9.223372036854785E18,
                9.223372036854784E18,
                9.223372036854783E18,
                9.223372036854782E18,
                9.223372036854781E18,
                9.223372036854780E18,

                9.223372036854779E18,
                9.223372036854778E18,
                9.223372036854777E18,
                9.223372036854776E18,
                9.223372036854775E18,
                9.223372036854774E18,
                9.223372036854773E18,
                9.223372036854772E18,
                9.223372036854771E18,
                9.223372036854770E18,

                9.223372036854769E18,
                9.223372036854768E18,
                9.223372036854767E18,
                9.223372036854766E18,
                9.223372036854765E18,
                9.223372036854764E18,
                9.223372036854763E18,
                9.223372036854762E18,
                9.223372036854761E18,
                9.223372036854760E18,

                9.223372036854759E18,
                9.223372036854758E18,
                9.223372036854757E18,
                9.223372036854756E18,
                9.223372036854755E18,
                9.223372036854754E18,
                9.223372036854753E18,
                9.223372036854752E18,
                9.223372036854751E18,
                9.223372036854750E18,

                9.223372036854749E18,
                9.223372036854748E18,
                9.223372036854747E18,
                9.223372036854746E18,
                9.223372036854745E18,
                9.223372036854744E18,
                9.223372036854743E18,
                9.223372036854742E18,
                9.223372036854741E18,
                9.223372036854740E18,

                9.223372036854739E18,
                9.223372036854738E18,
                9.223372036854737E18,
                9.223372036854736E18,
                9.223372036854735E18,
                9.223372036854734E18,
                9.223372036854733E18,
                9.223372036854732E18,
                9.223372036854731E18,
                9.223372036854730E18,

                9.223372036854729E18,
                9.223372036854728E18,
                9.223372036854727E18,
                9.223372036854726E18,
                9.223372036854725E18,
                9.223372036854724E18,
                9.223372036854723E18,
                9.223372036854722E18,
                9.223372036854721E18,
                9.223372036854720E18,

                9.223372036854719E18,
                9.223372036854718E18,
                9.223372036854717E18,
                9.223372036854716E18,
                9.223372036854715E18,
                9.223372036854714E18,
                9.223372036854713E18,
                9.223372036854712E18,
                9.223372036854711E18,
                9.223372036854710E18,

                9.223372036854709E18,
                9.223372036854708E18,
                9.223372036854707E18,
                9.223372036854706E18,
                9.223372036854705E18,
                9.223372036854704E18,
                9.223372036854703E18,
                9.223372036854702E18,
                9.223372036854701E18,
                9.223372036854700E18,
        };

        for (int i = 0; i < values.length; i++) {
            double value = values[i];
            String str1 = Double.toString(value);
            String str2 = RyuDouble.toString(value);

            if (!str1.equals(str2)) {
                boolean cmp = (Double.parseDouble(str1) == Double.parseDouble(str2));
                System.out.println(str1 + " -> " + str2 + " : " + cmp);
                assertTrue(cmp);
            }
        }
    }

    public void test_3() throws Exception {
        double[] values = new double[]{
                9.223372036854799E18,
                9.223372036854798E18,
                9.223372036854797E18,
                9.223372036854796E18,
                9.223372036854795E18,
                9.223372036854794E18,
                9.223372036854793E18,
                9.223372036854792E18,
                9.223372036854791E18,
                9.223372036854790E18,

                9.223372036854789E18,
                9.223372036854788E18,
                9.223372036854787E18,
                9.223372036854786E18,
                9.223372036854785E18,
                9.223372036854784E18,
                9.223372036854783E18,
                9.223372036854782E18,
                9.223372036854781E18,
                9.223372036854780E18,

                9.223372036854779E18,
                9.223372036854778E18,
                9.223372036854777E18,
                9.223372036854776E18,
                9.223372036854775E18,
                9.223372036854774E18,
                9.223372036854773E18,
                9.223372036854772E18,
                9.223372036854771E18,
                9.223372036854770E18,

                9.223372036854769E18,
                9.223372036854768E18,
                9.223372036854767E18,
                9.223372036854766E18,
                9.223372036854765E18,
                9.223372036854764E18,
                9.223372036854763E18,
                9.223372036854762E18,
                9.223372036854761E18,
                9.223372036854760E18,

                9.223372036854759E18,
                9.223372036854758E18,
                9.223372036854757E18,
                9.223372036854756E18,
                9.223372036854755E18,
                9.223372036854754E18,
                9.223372036854753E18,
                9.223372036854752E18,
                9.223372036854751E18,
                9.223372036854750E18,

                9.223372036854749E18,
                9.223372036854748E18,
                9.223372036854747E18,
                9.223372036854746E18,
                9.223372036854745E18,
                9.223372036854744E18,
                9.223372036854743E18,
                9.223372036854742E18,
                9.223372036854741E18,
                9.223372036854740E18,

                9.223372036854739E18,
                9.223372036854738E18,
                9.223372036854737E18,
                9.223372036854736E18,
                9.223372036854735E18,
                9.223372036854734E18,
                9.223372036854733E18,
                9.223372036854732E18,
                9.223372036854731E18,
                9.223372036854730E18,

                9.223372036854729E18,
                9.223372036854728E18,
                9.223372036854727E18,
                9.223372036854726E18,
                9.223372036854725E18,
                9.223372036854724E18,
                9.223372036854723E18,
                9.223372036854722E18,
                9.223372036854721E18,
                9.223372036854720E18,

                9.223372036854719E18,
                9.223372036854718E18,
                9.223372036854717E18,
                9.223372036854716E18,
                9.223372036854715E18,
                9.223372036854714E18,
                9.223372036854713E18,
                9.223372036854712E18,
                9.223372036854711E18,
                9.223372036854710E18,

                9.223372036854709E18,
                9.223372036854708E18,
                9.223372036854707E18,
                9.223372036854706E18,
                9.223372036854705E18,
                9.223372036854704E18,
                9.223372036854703E18,
                9.223372036854702E18,
                9.223372036854701E18,
                9.223372036854700E18,
        };

        for (int i = 0; i < values.length; i++) {
            double value = values[i];
            String str1 = Double.toString(value);
            String str2 = RyuDouble.toString(value);

            if (!str1.equals(str2)) {
                boolean cmp = (Double.parseDouble(str1) == Double.parseDouble(str2));
                System.out.println(str1 + " -> " + str2 + " : " + cmp);
                assertTrue(cmp);
            }
        }
    }
}
