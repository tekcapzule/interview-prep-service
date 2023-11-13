package com.tekcapzule.interviewprep.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.interviewprep.domain.model.PrizingModel;
import com.tekcapzule.interviewprep.domain.model.Promotion;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UpdateInput {
    private String courseId;
    private String title;
    private String topicCode;
    private String author;
    private String publisher;
    private String duration;
    private String resourceUrl;
    private String summary;
    private String description;
    private PrizingModel prizingModel;
    private String imageUrl;
    private Promotion promotion;
    private int recommendations;
}
