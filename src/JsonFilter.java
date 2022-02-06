import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonFilter {

    public static void main(String[] args) throws IOException {

        URL url = new URL("https://raw.githubusercontent.com/arcjsonapi/ApiSampleData/master/api/users");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder stringBuilder = new StringBuilder();
            String response;
            while ((response = br.readLine()) != null) {
                stringBuilder.append(response.trim());
            }
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(String.valueOf(stringBuilder));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            List<Integer> op = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                String id = jsonObject.get("id").getAsString();
                String[] s = "username".split("\\.");
                String[] s1 = "Garimag".split("\\,");
                if (s.length > 1) {
                    for (int i = 0; i < s1.length; i++) {
                        if (jsonObject.get("address").getAsJsonObject().get("city").getAsString().equals(s1[i])) {
                            op.add(jsonObject.get("id").getAsInt());
                        }
                    }
                } else {
                    for (int i = 0; i < s1.length; i++) {
                        if (jsonObject.get("address").getAsJsonObject().get("city").getAsString().equals(s1[i])) {
                            op.add(jsonObject.get("id").getAsInt());
                        }
                    }
                }
            }
            System.out.println(op);
        }
    }
}
