package com.alibaba.fastjson.util;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.parser.ParserConfig;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertThrows;

@SuppressWarnings("rawtypes")
public class TypeUtilsTest extends TestCase {

    @Test
    public void testIsMapJavaBean() throws Exception {
        HashMap map = new HashMap();
        Assert.assertTrue(map == TypeUtils.castToJavaBean(map, Map.class));
    }

    @Test
    public void testIsJSONJavaBean() throws Exception {
        JSONObject map = new JSONObject();
        Assert.assertTrue(map == TypeUtils.castToJavaBean(map, Map.class));
    }

    @Test
    public void testCastBeanWithKeyVal() throws Exception {
        JSONObject map = new JSONObject();
        map.put("id", 1);
        map.put("name", "connor");
        User user = TypeUtils.castToJavaBean(map, User.class);
        Assert.assertEquals(1L, user.getId());
        Assert.assertEquals("connor", user.getName());
    }

    @Test
    public void testCastInteger() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1L);
        Assert.assertEquals(new Integer(1), json.getObject("id", int.class));
    }

    @Test
    public void testCastIntegerObject() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1L);
        Assert.assertEquals(new Integer(1), json.getObject("id", Integer.class));
    }

    @Test
    public void testCastLong() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new Long(1), json.getObject("id", long.class));
    }

    @Test
    public void testCastLongObject() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new Long(1), json.getObject("id", Long.class));
    }

    @Test
    public void testCastShort() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new Short((short) 1), json.getObject("id", short.class));
    }

    @Test
    public void testCastShortObject() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new Short((short) 1), json.getObject("id", Short.class));
    }

    @Test
    public void testCastByte() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new Byte((byte) 1), json.getObject("id", byte.class));
    }

    @Test
    public void testCastByteObject() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new Byte((byte) 1), json.getObject("id", Byte.class));
    }

    @Test
    public void testCastBigInt() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new BigInteger("1"), json.getObject("id", BigInteger.class));
    }

    @Test
    public void testCastBigDecimal() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new BigDecimal("1"), json.getObject("id", BigDecimal.class));
    }

    @Test
    public void testCastBool() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(Boolean.TRUE, json.getObject("id", boolean.class));
    }

    @Test
    public void testCastBoolObject() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(Boolean.TRUE, json.getObject("id", Boolean.class));
    }

    @Test
    public void testCastNull() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", null);
        Assert.assertEquals(null, json.getObject("id", Boolean.class));
    }

    @Test
    public void testCastString() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals("1", json.getObject("id", String.class));
    }

    @Test
    public void testCastDate() throws Exception {
        long millis = System.currentTimeMillis();
        JSONObject json = new JSONObject();
        json.put("date", millis);
        Assert.assertEquals(new Date(millis), json.getObject("date", Date.class));
    }

    @Test
    public void testCastSQLDate() throws Exception {
        long millis = System.currentTimeMillis();
        JSONObject json = new JSONObject();
        json.put("date", millis);
        Assert.assertEquals(new java.sql.Date(millis), json.getObject("date", java.sql.Date.class));
    }

    @Test
    public void testCastSQLDateCastString() throws Exception {
        long millis = System.currentTimeMillis();
        JSONObject json = new JSONObject();
        json.put("date", Long.toString(millis));
        Assert.assertEquals(new java.sql.Date(millis), json.getObject("date", java.sql.Date.class));
    }

    @Test
    public void testCastSQLDatePutNull() throws Exception {
        JSONObject json = new JSONObject();
        json.put("date", null);
        Assert.assertEquals(null, json.getObject("date", java.sql.Date.class));
    }

    @Test
    public void testCastSQLDateAllNull() throws Exception {
        Assert.assertEquals(null, TypeUtils.castToSqlDate(null));
    }

    @Test
    public void testCastSQLUtilDate() throws Exception {
        long millis = System.currentTimeMillis();
        JSONObject json = new JSONObject();
        json.put("date", new Date(millis));
        Assert.assertEquals(new java.sql.Date(millis), json.getObject("date", java.sql.Date.class));
    }

    @Test
    public void testCastSQLCalendar() throws Exception {
        long millis = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        JSONObject json = new JSONObject();
        json.put("date", calendar);
        Assert.assertEquals(new java.sql.Date(millis), json.getObject("date", java.sql.Date.class));
    }

    @Test
    public void testCastToSQLDateException() throws Exception {
        JSONObject json = new JSONObject();
        json.put("date", 0);
        Exception exception = assertThrows(JSONException.class, () -> {
            json.getObject("date", java.sql.Date.class);
        });
    }

    @Test
    public void testCastToJavaTimeStamp() throws Exception {
        long millis = System.currentTimeMillis();
        JSONObject json = new JSONObject();
        json.put("date", millis);
        Assert.assertEquals(new Timestamp(millis), json.getObject("date", Timestamp.class));
    }

    @Test
    public void testCastToJavaTimeStampString() throws Exception {
        long millis = System.currentTimeMillis();
        JSONObject json = new JSONObject();
        json.put("date", Long.toString(millis));
        Assert.assertEquals(new Timestamp(millis), json.getObject("date", Timestamp.class));
    }

    @Test
    public void testCastToJavaTimeStampWithNumber() throws Exception {
        long millis = System.currentTimeMillis();
        JSONObject json = new JSONObject();
        json.put("date", new BigDecimal(Long.toString(millis)));
        Assert.assertEquals(new Timestamp(millis), json.getObject("date", Timestamp.class));
    }

    @Test
    public void testCastToJavaTimeStampNull() throws Exception {
        JSONObject json = new JSONObject();
        json.put("date", null);
        Assert.assertEquals(null, json.getObject("date", Timestamp.class));
    }

    @Test
    public void testCastToJavaTimeStampAllNull() throws Exception {
        Assert.assertEquals(null, TypeUtils.castToTimestamp(null));
    }

    @Test
    public void testCastToTimeStampTheStartOfTime() throws Exception {
        JSON.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        Assert.assertEquals(new Timestamp(0), TypeUtils.castToTimestamp("1970-01-01 08:00:00"));
    }

    @Test
    public void castToBigDecimalEqual() throws Exception {
        BigDecimal value = new BigDecimal("123");
        Assert.assertEquals(true, value == TypeUtils.castToBigDecimal(value));
    }

    @Test
    public void castToBigIntegerEqual() throws Exception {
        BigInteger value = new BigInteger("123");
        Assert.assertEquals(true, value == TypeUtils.castToBigInteger(value));
    }

    @Test
    public void testCastArray() throws Exception {
        Assert.assertEquals(Integer[].class, TypeUtils.cast(new ArrayList(), Integer[].class, null).getClass());
    }

    @Test
    public void testCastTimeStampUtil() throws Exception {
        long millis = System.currentTimeMillis();

        JSONObject json = new JSONObject();
        json.put("date", new Date(millis));
        Assert.assertEquals(new Timestamp(millis), json.getObject("date", Timestamp.class));
    }

    @Test
    public void testCastTimestampSQLDate() throws Exception {
        long millis = System.currentTimeMillis();

        JSONObject json = new JSONObject();
        json.put("date", new java.sql.Date(millis));
        Assert.assertEquals(new Timestamp(millis), json.getObject("date", Timestamp.class));
    }

    @Test
    public void testCastSQLTimeStamp() throws Exception {
        long millis = System.currentTimeMillis();

        Timestamp date = new Timestamp(millis);
        Assert.assertEquals(date, TypeUtils.castToTimestamp(date));
    }

    @Test
    public void testCastTimeStampSQLWithKey() throws Exception {
        long millis = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        JSONObject json = new JSONObject();
        json.put("date", calendar);
        Assert.assertEquals(new Timestamp(millis), json.getObject("date", Timestamp.class));
    }

    @Test
    public void testCastNoError() throws Exception {
        JSONObject json = new JSONObject();
        json.put("date", -1);

        JSONException error = null;
        try {
            json.getObject("date", Timestamp.class);
        } catch (JSONException e) {
            error = e;
        }
        Assert.assertNull(error);
        Assert.assertEquals(new Timestamp(-1L), (Timestamp) json.getObject("date", Timestamp.class));
    }

    @Test
    public void testCastCustomObjectAB() throws Exception {
        B b = new B();

        JSONObject json = new JSONObject();
        json.put("value", b);
        Assert.assertEquals(b, json.getObject("value", A.class));
    }

    @Test
    public void testCastCustomObjectIAClass() throws Exception {
        B b = new B();

        JSONObject json = new JSONObject();
        json.put("value", b);
        Assert.assertEquals(b, json.getObject("value", IA.class));
    }

    @Test
    public void testCastCustomABException() throws Exception {
        A a = new A();

        JSONObject json = new JSONObject();
        json.put("value", a);

        JSONException error = null;
        try {
            json.getObject("value", B.class);
        } catch (JSONException e) {
            error = e;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void testJSONCastJavaBeanExcept() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);

        JSONException error = null;
        try {
            TypeUtils.castToJavaBean(json, C.class, ParserConfig.getGlobalInstance());
        } catch (JSONException e) {
            error = e;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void testCastParserError() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);

        Method method = TypeUtilsTest.class.getMethod("f", List.class);

        Throwable error = null;
        try {
            TypeUtils.cast(json, method.getGenericParameterTypes()[0], ParserConfig.getGlobalInstance());
        } catch (JSONException ex) {
            error = ex;
        }
        assertNotNull(error);
    }

    @Test
    public void testPutIsCorrect() throws Exception {
        JSONObject map = new JSONObject();
        map.put("id", 1);
        map.put("name", "connor");

        User user = JSON.toJavaObject(map, User.class);
        Assert.assertEquals(1L, user.getId());
        Assert.assertEquals("connor", user.getName());
    }

    @Test
    public void testParsFloatWithLoop() throws Exception {

        Random r = new Random();

        for (int i = 0; i < 1000 * 1000; ++i) {
            String str = Float.toString(r.nextFloat());
            assertEquals(Double.parseDouble(str), TypeUtils.parseDouble(str));
        }
    }

    @Test
    public void testParseDoubleWithLoop() throws Exception {
        Random r = new Random();

        for (int i = 0; i < 1000 * 1000; ++i) {
            String str = Double.toString(r.nextDouble());
            assertEquals(Double.parseDouble(str), TypeUtils.parseDouble(str));
        }
    }


    @Test
    public void testParseIntAsDoubleLoop() throws Exception {
        Random r = new Random();

        for (int i = 0; i < 1000 * 1000; ++i) {
            String str = Integer.toString(r.nextInt());
            assertEquals(Double.parseDouble(str), TypeUtils.parseDouble(str));
        }
    }

    @Test
    public void testParseLargerIntInLoop() throws Exception {
        Random r = new Random();

        for (int i = 0; i < 1000 * 1000; ++i) {
            String str = Integer.toString(r.nextInt(1000000000));
            assertEquals(Double.parseDouble(str), TypeUtils.parseDouble(str));
        }
    }

    @Test
    public void testParseLongAsDouble() throws Exception {
        Random r = new Random();

        for (int i = 0; i < 1000 * 1000; ++i) {
            String str = Long.toString(r.nextLong());
            assertEquals(Double.parseDouble(str), TypeUtils.parseDouble(str));
        }
    }

    @Test
    public void testParseCustomDoubles() throws Exception {
        String[] array = new String[] {
                "0.34856254",
                "1",
                ".1"
                ,"1.1"
                , "0.1"
                , "0.12"
                , "0.123"
                , "0.1234"
                , "0.12345"
        };

        for (String str : array) {
            assertEquals(Double.parseDouble(str), TypeUtils.parseDouble(str));
        }
    }

    public void setUp() throws Exception {
        JSON.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        JSON.defaultLocale = Locale.CHINA;
    }

    @Test
    public void testCastToDate() throws Exception {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
        Date date = TypeUtils.castToDate("2012-07-15 12:12:11");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(JSON.defaultTimeZone);
        Assert.assertEquals(format.parseObject("2012-07-15 12:12:11"), date);
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    }

    @Test
    public void testCastToDateError() throws Exception {
        Exception error = null;
        try {
            TypeUtils.castToDate("你妈你妈-MM-dd");
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void testCastToDateZero() throws Exception {
        Assert.assertEquals(new Date(0), TypeUtils.castToDate("0"));
    }

    @Test
    public void testCastToDateNegative() throws Exception {
        Assert.assertEquals(new Date(-1), TypeUtils.castToDate(-1));
    }

    @Test
    public void testBigDecimal() {
        BigDecimal bd = new BigDecimal(20220101);
        Date date = new Date(20220101);
        Assert.assertEquals(date, TypeUtils.castToDate(bd));
    }

    @Test
    public void testBigDecimal_F() {
        BigDecimal bd = new BigDecimal(20220101);
        Date date = new Date(20220101);
        Assert.assertEquals(date, TypeUtils.castToDate(bd,"yyyy-MM-dd"));
    }
    public void testStrF1() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", JSON.defaultLocale);
        dateFormat.setTimeZone(JSON.defaultTimeZone);
        Date date = dateFormat.parse("2022-02-02 10:00:00");
        String bd = "2022-02-02 10:00:00";
        Assert.assertEquals(date, TypeUtils.castToDate(bd,null));
    }

    @Test
    public void testStrF2() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", JSON.defaultLocale);
        dateFormat.setTimeZone(JSON.defaultTimeZone);
        Date date = dateFormat.parse("2020-08-24T21:49:31.702+04:00");
        String bd = "2020-08-24T21:49:31.702+04:00";
        Assert.assertEquals(date, TypeUtils.castToDate(bd,null));
    }

    @Test
    public void testStrF3() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", JSON.defaultLocale);
        dateFormat.setTimeZone(JSON.defaultTimeZone);
        Date date = dateFormat.parse("2013-07-29 06:35:40.622");
        String bd = "2013-07-29 06:35:40.622";
        Assert.assertEquals(date, TypeUtils.castToDate(bd,null));
    }

    @Test
    public void testStrF4() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS", JSON.defaultLocale);
        dateFormat.setTimeZone(JSON.defaultTimeZone);
        Date date = dateFormat.parse("2013-07-29 06:35:40,622");
        String bd = "2013-07-29 06:35:40,622";
        Assert.assertEquals(date, TypeUtils.castToDate(bd,null));
    }

    @Test
    public void testStrF5Unix() throws ParseException {
        Date date = new Date(20220000);
        Number bd = 20220.000;
        Assert.assertEquals(date.getTime(), TypeUtils.castToDate(bd,"unixtime").getTime());
    }

    @Test
    public void testCastBigIntDoubleNanInfinite() throws Exception {
        Assert.assertNull(TypeUtils.castToBigInteger(1.0d / 0.0d));
        Assert.assertNull(TypeUtils.castToBigInteger(-1.0d / 0.0d));
        Assert.assertNull(TypeUtils.castToBigInteger(0.0d / 0.0d));
    }

    @Test
    public void testCastBigIntFloatNanInfinite() throws Exception {
        Assert.assertNull(TypeUtils.castToBigInteger(1.0f / 0.0f));
        Assert.assertNull(TypeUtils.castToBigInteger(-1.0f / 0.0f));
        Assert.assertNull(TypeUtils.castToBigInteger(0.0f / 0.0f));
    }

    @Test
    public void testCastBytesToDateByteArrayEqual() throws Exception {
        Assert.assertArrayEquals(new byte[0], TypeUtils.castToBytes(new byte[0]));
    }

    @Test
    public void testCastBigIntToDateThrowsError() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            TypeUtils.castToBytes(new int[0]);
        });
    }

    @Test
    public void testCastBigDecFloatNanInfinite_BigDecimal() throws Exception {
        assertNull(TypeUtils.castToBigDecimal(1.0f / 0.0f));
        assertNull(TypeUtils.castToBigDecimal(-1.0f / 0.0f));
        assertNull(TypeUtils.castToBigDecimal(0.0f / 0.0f));
    }

    @Test
    public void testCastBigDecDoubleNanInfinite_BigDecimal() throws Exception {
        assertNull(TypeUtils.castToBigDecimal(1.0d / 0.0d));
        assertNull(TypeUtils.castToBigDecimal(-1.0d / 0.0d));
        assertNull(TypeUtils.castToBigDecimal(0.0d / 0.0d));
    }

    @Test
    public void testCastBigIntFloatNanInfinite_BigDecimal_INT() throws Exception {
        assertNull(TypeUtils.castToBigInteger(1.0f / 0.0f));
        assertNull(TypeUtils.castToBigInteger(-1.0f / 0.0f));
        assertNull(TypeUtils.castToBigInteger(0.0f / 0.0f));
    }

    @Test
    public void testCastBigIntNanInfinite_BigDecimal_INT() throws Exception {
        assertNull(TypeUtils.castToBigInteger(1.0d / 0.0d));
        assertNull(TypeUtils.castToBigInteger(-1.0d / 0.0d));
        assertNull(TypeUtils.castToBigInteger(0.0d / 0.0d));
    }

    @Test
    public void testNullString_BigDecimal_INT() throws Exception {
        assertNull(TypeUtils.castToBigInteger(""));
        assertNull(TypeUtils.castToBigInteger("null"));
    }

    @Test
    public void testNullString_BigDecimal() throws Exception {
        assertNull(TypeUtils.castToBigDecimal(""));
        assertNull(TypeUtils.castToBigDecimal("null"));
    }

    @Test
    public void testCastByteArray() throws Exception {
        Assert.assertArrayEquals(new byte[0], TypeUtils.cast(new byte[0], byte[].class, null));
    }

    @Test
    public void testCastRefList() throws Exception {
        ParameterizedType parameterizedType = (ParameterizedType) new TypeReference<List<?>>() {}.getType();
        Type type = parameterizedType.getActualTypeArguments()[0];
        Assert.assertNull(TypeUtils.cast("", type, null));
    }

    @Test
    public void testCastThrowsErrorFromUnknownNullClass() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            TypeUtils.cast(0, (Class<?>) null, null);
        });
    }

    @Test
    public void testParameterizedType() throws Exception {
        Assert.assertEquals(Integer.valueOf(123),
                ((ArrayList<Object>) TypeUtils.cast(Collections.singleton(123),
                        new TypeReference<ArrayList<Object>>() {
                        }.getType(), null)).get(0));
    }

    @Test
    public void testParameterizedTypeWithKeyVal() throws Exception {
        Assert.assertEquals("123",
                ((HashMap<Object, String>) TypeUtils.cast(Collections.singletonMap("name", 123),
                        new TypeReference<HashMap<Object, String>>() {
                        }.getType(), null)).get("name"));
    }

    @Test
    public void testParameterizedTypeException() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            TypeUtils.cast(Collections.singleton(123), new TypeReference<HashMap<Object, String>>() {
            }.getType(), null);
        });
    }

    @Test
    public void testCastErrorIncorrectClass() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            TypeUtils.cast("xxx", Object[].class, null);
        });
    }

    @Test
    public void testCastErrorIncorrectObject() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            TypeUtils.cast(123, new TypeReference<Object[]>() {
            }.getType(), null);
        });
    }

    @Test
    public void testExceptionClassRuntime() throws Exception {
        JSONObject object = (JSONObject) JSON.toJSON(new RuntimeException());
        object.put("class", "xxx");
        Assert.assertEquals(Exception.class, JSON.parseObject(object.toJSONString(), Exception.class).getClass());
    }

    @Test
    public void testCastNullReferenceObject() throws Exception {
        Assert.assertEquals(null, TypeUtils.cast(null, new TypeReference<Object>() {
        }.getType(), null));
    }

    @Test
    public void testCastNullReferenceObjectList() throws Exception {
        Assert.assertEquals(null, TypeUtils.cast("", new TypeReference<List<Object>>() {
        }.getType(), null));
    }

    @Test
    public void testCastNullReferenceObjectArray() throws Exception {
        Assert.assertEquals(null, TypeUtils.cast("", new TypeReference<Object[]>() {
        }.getType(), null));
    }

    @Test
    public void testCastThrowExceptionObjectListIncorrectType() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            TypeUtils.cast("abc", new TypeReference<Object[]>() {
            }.getType(), null);
        });
    }

    @Test
    public void testTypeUtilsCastAllTypes() throws Exception {
        Assert.assertNull(TypeUtils.cast("", Byte.class, null));
        Assert.assertNull(TypeUtils.cast("", Short.class, null));
        Assert.assertNull(TypeUtils.cast("", Integer.class, null));
        Assert.assertNull(TypeUtils.cast("", Long.class, null));
        Assert.assertNull(TypeUtils.cast("", Float.class, null));
        Assert.assertNull(TypeUtils.cast("", Double.class, null));
        Assert.assertNull(TypeUtils.cast("", Character.class, null));
        Assert.assertNull(TypeUtils.cast("", java.util.Date.class, null));
        Assert.assertNull(TypeUtils.cast("", java.sql.Date.class, null));
        Assert.assertNull(TypeUtils.cast("", java.sql.Timestamp.class, null));
        Assert.assertNull(TypeUtils.castToChar(""));
        Assert.assertNull(TypeUtils.castToChar(null));
        Assert.assertEquals('A', TypeUtils.castToChar('A').charValue());
        Assert.assertEquals('A', TypeUtils.castToChar("A").charValue());
        Assert.assertNull(TypeUtils.castToBigDecimal(""));
        Assert.assertNull(TypeUtils.castToBigInteger(""));
        Assert.assertNull(TypeUtils.castToBoolean(""));
    }

    @Test
    public void assertExpectedNullCastUtilsError() throws Exception {
        Assert.assertEquals(null, TypeUtils.cast("", new TypeReference<List<Object>>() {
        }.getType(), null));
    }

    @Test
    public void testCastErrorExpectNullException() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            Assert.assertEquals(null, TypeUtils.cast("a", new TypeReference<List<Object>>() {
            }.getType(), null));
        });
    }

    @Test
    public void testThrowsErrorCastToTypeRefList() throws Exception {
        Exception error = null;
        try {
            Assert.assertEquals(null, TypeUtils.cast("a", ((ParameterizedType) new TypeReference<List<?>>() {

            }.getType()).getActualTypeArguments()[0], null));
        } catch (JSONException e) {
            error = e;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void testThrowsErrorCastToCharIncorrectString() throws Exception {
        Exception error = null;
        try {
            TypeUtils.castToChar("abc");
        } catch (JSONException e) {
            error = e;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void testThrowsErrorCastToCharIncorrectBool() throws Exception {
        Exception error = null;
        try {
            TypeUtils.castToChar(true);
        } catch (JSONException e) {
            error = e;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void testExceptionStackTraceToJSON() throws Exception {
        RuntimeException ex = new RuntimeException();
        JSONObject object = (JSONObject) JSON.toJSON(ex);
        JSONArray array = object.getJSONArray("stackTrace");
        array.getJSONObject(0).put("lineNumber", null);

        JSON.parseObject(object.toJSONString(), Exception.class);
    }

    //CLASS STUFF:
    public static class User {

        private long id;
        private String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class A implements IA {

    }

    public static interface IA {

    }

    public static class B extends A {

    }

    public static class C extends B {

        public int getId() {
            throw new UnsupportedOperationException();
        }

        public void setId(int id) {
            throw new UnsupportedOperationException();
        }
    }

    public static void f(List<?> list) {

    }
}
