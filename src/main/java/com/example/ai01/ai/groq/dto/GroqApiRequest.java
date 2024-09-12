package com.example.ai01.ai.groq.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class GroqApiRequest {
    private String prompt;
    private String modelType;
}
