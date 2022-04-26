package stadTests;

import com.alibaba.fastjson.parser.JSONScanner;
import com.diffblue.deeptestutils.Reflector;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Locale;

import java.util.Locale;

public class testJSONLexerBase {

    @Test
    public void testTest() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Arrange
        String src = "";
        int offset = 7;
        char[] dest = { '\u0000' };

        // Act
        Class<?> c = Reflector.forName("com.alibaba.fastjson.parser.JSONScanner");
        Method m = c.getDeclaredMethod("charArrayCompare", Reflector.forName("java.lang.String"), Reflector.forName("int"), Reflector.forName("char []"));
        m.setAccessible(true);
        boolean retval = (Boolean)m.invoke(null, src, offset, dest);

        // Assert result
        Assert.assertEquals(false, retval);
    }
}
