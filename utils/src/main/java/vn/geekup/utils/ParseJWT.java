package vn.geekup.utils;

import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/*
 * Created by darkpiv on 12/4/18.
 */
public class ParseJWT {

    private static final String ENCODING_UTF_8 = "UTF-8";
    private final String token;

    private Map<String, String> header;
    private JSONObject payload;
    private String signature;

    /**
     * Decode a given string JWT token.
     *
     * @param token the string JWT token.
     * @throws Exception if the token cannot be decoded
     */
    public ParseJWT(@NonNull String token) {
        decode(token);
        this.token = token;
    }

    public JSONObject getPayload() {
        return payload;
    }


    // =====================================
    // ===========Private Methods===========
    // =====================================

    private void decode(String token) {
        final String[] parts = splitToken(token);
        payload = parseJson(base64Decode(parts[1]));
        signature = parts[2];
    }

    private String[] splitToken(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            Log.e("ParseJWT", "splitToken: This is not jwt token" );
        }
        return parts;
    }

    private String base64Decode(String string) {
        String decoded = "";
        try {
            byte[] bytes = Base64.decode(string, Base64.URL_SAFE | Base64.NO_WRAP | Base64.NO_PADDING);
            decoded = new String(bytes, ENCODING_UTF_8);
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            Log.e("ParseJWT", "base64Decode: ", e);
        }
        return decoded;
    }

    private JSONObject parseJson(String json) {
        JSONObject payload = null;
        try {
            payload = new JSONObject(json);
        } catch (Exception e) {
            Log.e("ParseJWT", "parseJson: ", e);
        }
        return payload;
    }

}

