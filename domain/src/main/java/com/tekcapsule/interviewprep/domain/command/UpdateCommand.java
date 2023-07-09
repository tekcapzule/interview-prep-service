package com.tekcapsule.interviewprep.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.interviewprep.domain.model.*;
import com.tekcapsule.interviewprep.domain.model.Module;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UpdateCommand extends Command {
    private String courseId;
    private String title;
    private String topicCode;
    private String author;
    private String publisher;
    private String duration;
    private String courseUrl;
    private String summary;
    private String description;
    private List<Module> modules;
    private PrizingModel prizingModel;
    private DeliveryMode deliveryMode;
    private LearningMode learningMode;
    private String imageUrl;
    private Promotion promotion;
}