package javaminor.SOAPclient;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Random;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by alex on 9/30/15.
 */
public class PaymentClientTest extends TestCase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private PaymentClient paymentClient;
    private String validIdentifier;
    private String invalidIdentifier;
    private double validAmountForValidIdentifier;
    private double invalidAmountForValidIdentifier;

    @Before
    public void setUp(){
        paymentClient = new PaymentClient();
        validIdentifier = "123456789";
        validAmountForValidIdentifier = new Random().nextDouble() * 1500;
        invalidAmountForValidIdentifier = 1500.01;
        invalidIdentifier = "invalid";
    }

    @Test
    public void testCheckValidity() throws Exception {
        // should not throw an error if connection unavailable, however assertions below will fail

        // wrong id, positive valid amount = false
        assertThat(paymentClient.checkValidity(invalidIdentifier,validAmountForValidIdentifier),is(false));

        // wrong id, positive invalid amount = false
        assertThat(paymentClient.checkValidity(invalidIdentifier,invalidAmountForValidIdentifier),is(false));

        // wrong id, 0 amount = false
        assertThat(paymentClient.checkValidity(invalidIdentifier,0),is(false));

        // wrong id, negative amount = false
        assertThat(paymentClient.checkValidity(invalidIdentifier,-1),is(false));

        // proper id, positive valid amount = true
        assertThat(paymentClient.checkValidity(validIdentifier,validAmountForValidIdentifier),is(true));

        // proper id, positive invalid amount = false
        assertThat(paymentClient.checkValidity(validIdentifier,invalidAmountForValidIdentifier),is(false));

        // proper id, 0 amount = true
        assertThat(paymentClient.checkValidity(validIdentifier,0),is(true));

        // proper id, negative amount = false
        assertThat(paymentClient.checkValidity(validIdentifier,-1),is(false));

        // null id, positive valid amount = false
        assertThat(paymentClient.checkValidity(null,validAmountForValidIdentifier),is(false));

        // null id, positive invalid amount = false
        assertThat(paymentClient.checkValidity(null,invalidAmountForValidIdentifier),is(false));

        // null id, 0 amount = false
        assertThat(paymentClient.checkValidity(null,0),is(false));

        // null id, negative amount = false
        assertThat(paymentClient.checkValidity(null,-1),is(false));
    }

    @Test
    public void testPay() throws Exception {
        // should not throw an error if connection unavailable, however assertions below will fail
        // also, if the repository is not reset, values will diminish and assertions made might fail

        // wrong id, positive valid amount = false
        assertThat(paymentClient.pay(invalidIdentifier, 1),is(false));

        // wrong id, positive invalid amount = false
        assertThat(paymentClient.pay(invalidIdentifier, 1000000),is(false));

        // wrong id, 0 amount = false
        assertThat(paymentClient.pay(invalidIdentifier, 0),is(false));

        // wrong id, negative amount = false
        assertThat(paymentClient.pay(invalidIdentifier, -1),is(false));

        // proper id, positive valid amount = true
        assertThat(paymentClient.pay(validIdentifier, 1),is(true));

        // proper id, positive invalid amount = false
        assertThat(paymentClient.pay(validIdentifier, 10000000),is(false));

        // proper id, 0 amount = true
        assertThat(paymentClient.pay(validIdentifier, 0),is(true));

        // proper id, negative amount = false
        assertThat(paymentClient.pay(validIdentifier, -1),is(false));

        // null id, positive valid amount = false
        assertThat(paymentClient.pay(null, 1),is(false));

        // null id, positive invalid amount = false
        assertThat(paymentClient.pay(null, 1000000000),is(false));

        // null id, 0 amount = false
        assertThat(paymentClient.pay(null, 0),is(false));

        // null id, negative amount = false
        assertThat(paymentClient.pay(null,-1),is(false));
    }
}