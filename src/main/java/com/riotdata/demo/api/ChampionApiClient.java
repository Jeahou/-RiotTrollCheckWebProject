package com.riotdata.demo.api;

import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Service
public class ChampionApiClient {
    private final String HOST_URL = "http://ddragon.leagueoflegends.com/cdn/11.11.1/data/ko_KR/champion.json";
    private final WebClient webClient = WebClient.create();

    public JSONObject championFind(){
        JSONObject responseJson = null;
        JSONParser jsonParser = new JSONParser();
        try {
            responseJson = (JSONObject) jsonParser.parse(webClient.method(HttpMethod.GET)
                    .uri(HOST_URL)
                    .retrieve()
                    .bodyToMono(String.class).block());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (JSONObject) responseJson.get("data");
    }

    public JSONObject test() {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;

        try {
            URL url = new URL(HOST_URL);

            conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            //conn.setDoOutput(true);

            JSONObject commands = new JSONObject();

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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return (JSONObject) responseJson.get("data");
    }

}
