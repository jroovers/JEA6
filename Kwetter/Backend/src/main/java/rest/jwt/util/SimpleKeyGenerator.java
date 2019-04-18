package rest.jwt.util;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class SimpleKeyGenerator implements KeyGenerator {

    @Override
    public Key generateKey() {
        String keyString = "MySuperStrongVerySecureKeyString";
        Key key = new SecretKeySpec(keyString.getBytes(), "HS256");
        return key;
    }
}
