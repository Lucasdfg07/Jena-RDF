package encoder;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class Encoder {
	
	public boolean charsetEncoder(String uri) {
        CharsetEncoder enc = Charset.forName("ISO-8859-1").newEncoder();
        if ( enc.canEncode(uri) ) {
            return true;
        } else {
            return false;
        }
    }
}
