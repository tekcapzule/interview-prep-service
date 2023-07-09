package com.tekcapsule.interviewprep.application.function;

import com.tekcapsule.core.domain.EmptyFunctionInput;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.interviewprep.application.config.AppConfig;
import com.tekcapsule.interviewprep.domain.model.Course;
import com.tekcapsule.interviewprep.domain.service.InterviewPrepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
@Slf4j
public class GetAllFunction implements Function<Message<EmptyFunctionInput>, Message<List<Course>>> {

    private final InterviewPrepService interviewPrepService;

    private final AppConfig appConfig;

    public GetAllFunction(final InterviewPrepService interviewPrepService, final AppConfig appConfig) {
        this.interviewPrepService = interviewPrepService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<List<Course>> apply(Message<EmptyFunctionInput> getAllInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        List<Course> courses = new ArrayList<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            log.info("Entering get all courses Function");
            courses = interviewPrepService.findAll();
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage<>(courses, responseHeaders);
    }
}