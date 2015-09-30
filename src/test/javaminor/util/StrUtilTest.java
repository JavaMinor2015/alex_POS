package javaminor.util;

import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by alex on 9/30/15.
 */
public class StrUtilTest extends TestCase {

    @Test
    public void testTwoDecimal(){
        assertThat(StrUtil.twoDecimal(5.2020),equalTo("5.20"));
        assertThat(StrUtil.twoDecimal(5),equalTo("5.00"));
        assertThat(StrUtil.twoDecimal(-5),equalTo("-5.00"));
        assertThat(StrUtil.twoDecimal(-5.20),equalTo("-5.20"));
        assertThat(StrUtil.twoDecimal(0),equalTo("0.00"));
    }

    public void testRandomString(){
        assertTrue(StrUtil.randomString(5).length() > 0);
    }
}