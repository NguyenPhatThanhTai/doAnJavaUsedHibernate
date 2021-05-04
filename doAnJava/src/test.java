import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;

public class test {
    public static void main(String[] args) {
        String keyWorld = "Logined";
        String query_url = "https://apimywebsite.000webhostapp.com/APIDoAnJava/sendToken.php";
        String json = "{ \"name\" : \""+keyWorld+"\", " +
                "       \"name1\" : \""+keyWorld+"\"}";
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            // read the response
            BufferedReader br = null;
            if (100 <= conn.getResponseCode() && conn.getResponseCode() <= 399) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String array1 = br.readLine();

            JSONObject json2 = new JSONObject(array1);

            JSONArray jsonArray2 = json2.getJSONArray("token");

            //get token
            String token = jsonArray2.get(0).toString();

            System.out.println(token);

            //get key and typ
            String[] chunks = token.split("\\.");

            Base64.Decoder decoder = Base64.getDecoder();

            String header = new String(decoder.decode(chunks[0]));
            String payload = new String(decoder.decode(chunks[1]));

            String[] strArray = new String[] {payload};

            JSONObject json3 = new JSONObject(strArray[0]);

            System.out.println(json3);

            Object key = json3.get("key");
            Object typ = json3.get("typ");

            System.out.println(key);
            System.out.println(typ);

            br.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
