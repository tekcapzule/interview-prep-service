package com.tekcapzule.interviewprep.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.Command;
import com.tekcapzule.interviewprep.domain.model.*;
import com.tekcapzule.interviewprep.domain.model.PrizingModel;
import com.tekcapzule.interviewprep.domain.model.Promotion;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CreateCommand extends Command {
    private String title;
    private String topicCode;
    private String author;
    private String publisher;
    private String duration;
    private String resourceUrl;
    private String summary;
    private String description;
    private List<Module> modules;
    private PrizingModel prizingModel;
    private String imageUrl;
    private Promotion promotion;
    private int recommendations;

}