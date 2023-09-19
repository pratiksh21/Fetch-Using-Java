import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        String apiUrl = "https://api.chucknorris.io/jokes/random";

        try {
            // Create a URL object with the API endpoint
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check for a successful HTTP response (status code 200)
            if (connection.getResponseCode() == 200) {
                // Read the response from the API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder jsonResponse = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    jsonResponse.append(line);
                }
                reader.close();

                // Parse the JSON response
                JSONObject jsonObject = new JSONObject(jsonResponse.toString());

                // Extract and print the Chuck Norris joke
                String joke = jsonObject.getString("value");
                System.out.println("Chuck Norris Joke: " + joke);
            } else {
                System.out.println("Failed to fetch data. HTTP Response Code: " + connection.getResponseCode());
            }

            // Close the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
