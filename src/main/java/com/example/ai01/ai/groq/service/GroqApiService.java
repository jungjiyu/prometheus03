package com.example.ai01.ai.groq.service;

import com.example.ai01.ai.groq.dto.GroqApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GroqApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${groq.api.key}")
    private String apiKey;

    public String complete(GroqApiRequest request) {
        String url = "https://api.groq.com/openai/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String requestJson = "{\"messages\": [{\"role\": \"user\", \"content\": \"" + request.getPrompt() + "\"}], \"model\": \"" + request.getModelType() + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
    }


    public String enhanceWriting(GroqApiRequest request) {
        String url = "https://api.groq.com/openai/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String requestJson = "{\"messages\": [{\"role\": \"user\", \"content\": \"\'" + request.getPrompt() + "\'이 글을 좀 다듬어서 다시 작성해줘.\"}], \"model\": \"" + request.getModelType() + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
    }

    public String evaluateHarmfulness(GroqApiRequest request) {
        String url = "https://api.groq.com/openai/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String requestJson = "{\"messages\": [{\"role\": \"user\", \"content\": \" \'" + request.getPrompt() + "\' 이 글의 유해성 정도를 별도의 설명없이 0~10 의 숫자로만 반환해줘.\"}], \"model\": \"" + request.getModelType() + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
    }




}