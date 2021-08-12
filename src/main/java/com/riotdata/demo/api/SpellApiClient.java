package com.riotdata.demo.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class SpellApiClient {
    private final String spellUrl = "http://ddragon.leagueoflegends.com/cdn/11.11.1/data/ko_KR/summoner.json";
    private final WebClient webclient = WebClient.create();
    public JSONObject spellFind(){
        JSONObject jsonObject = null;
        JSONParser jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonParser.parse(webclient
                    .method(HttpMethod.GET)
                    .uri(spellUrl)
                    .retrieve()
                    .bodyToMono(String.class).block());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (JSONObject) jsonObject.get("data");
    }

    public JSONObject checkSpell(){
        HttpURLConnection conn = null;
        JSONObject responseJson = null;

        try {
            URL url = new URL(spellUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 400 || responseCode == 401 || responseCode == 500 ) {
                System.out.println(responseCode + " Error!");
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                JSONParser jsonParser = new JSONParser();
                responseJson = (JSONObject) jsonParser.parse(sb.toString());
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (JSONObject) responseJson.get("data");
    }
}
