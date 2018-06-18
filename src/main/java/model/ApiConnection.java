package main.java.model;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class ApiConnection {




    public String getId(String username) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://curiouscat.me/api/v2/profile?username=" + username + "&count=30&min_timestamp=0")
                .header("Cache-Control", "no-cache")
                .header("Postman-Token", "6d8dea42-5cb0-9774-6ffc-1559f69941c2")
                .asString();
        JSONObject json = new JSONObject(response.getBody());
        int id = (int) json.get("id");

        return Integer.toString(id);
    }

   /* public static void main(String[] args) throws UnirestException, InterruptedException, IOException {

        int numeroDeVeces = 99;
        long sleepTime = 5000;
        String id = getId("Zoerua_");
        String mensaje = "wtf, el 99";
        for (int i = 0; i < numeroDeVeces; i++) {
            enviarPregunta(id, mensaje);
            System.out.println(i);
            Thread.sleep(sleepTime);
        }


    }*/

    public void enviarPregunta(String id, String mensaje) throws UnirestException {

        HttpResponse<String> response = Unirest.post("https://api.curiouscat.me/v2/post/create")
                .header("origin", "https://curiouscat.me")
                .header("accept-language", "es-ES,es;q=0.9")
                .header("content-type", "multipart/form-data; boundary=----WebKitFormBoundaryIjgnBEKGCjnl08oa")
                .header("accept", "application/json, text/plain, */*")
                .header("referer", "https://curiouscat.me/Shikkinami")
                .header("Cache-Control", "no-cache")
                .header("Postman-Token", "3ba5d297-1433-a2a2-5e6e-5372dbc448f9")
                .body("------WebKitFormBoundaryIjgnBEKGCjnl08oa\r\nContent-Disposition: form-data; name=\"addressees\"\r\n\r\n" + id + "\r\n------WebKitFormBoundaryIjgnBEKGCjnl08oa\r\nContent-Disposition: form-data; name=\"anon\"\r\n\r\ntrue\r\n------WebKitFormBoundaryIjgnBEKGCjnl08oa\r\nContent-Disposition: form-data; name=\"question\"\r\n\r\n" + mensaje + "\r\n------WebKitFormBoundaryIjgnBEKGCjnl08oa--")
                .asString();
    }


}
