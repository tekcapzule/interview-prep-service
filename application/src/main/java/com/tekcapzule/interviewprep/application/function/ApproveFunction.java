package com.tekcapzule.interviewprep.application.function;

import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.PayloadUtil;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.interviewprep.application.config.AppConfig;
import com.tekcapzule.interviewprep.application.function.input.ApproveCourseInput;
import com.tekcapzule.interviewprep.application.mapper.InputOutputMapper;
import com.tekcapzule.interviewprep.domain.command.ApproveCommand;
import com.tekcapzule.interviewprep.domain.service.InterviewPrepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
@Slf4j
public class ApproveFunction implements Function<Message<ApproveCourseInput>, Message<Void>> {

    private final InterviewPrepService interviewPrepService;

    private final AppConfig appConfig;

    public ApproveFunction(final InterviewPrepService interviewPrepService, final AppConfig appConfig) {
        this.interviewPrepService = interviewPrepService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Void> apply(Message<ApproveCourseInput> approveCourseInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            ApproveCourseInput approveCourseInput = approveCourseInputMessage.getPayload();
            log.info(String.format("Entering approve interview prep course Function -  course Id:%s", approveCourseInput.getCourseId()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(approveCourseInputMessage.getHeaders());
            ApproveCommand approveCommand = InputOutputMapper.buildApproveCommandFromApproveCourseInput.apply(approveCourseInput, origin);
            interviewPrepService.approve(approveCommand);
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            payload = PayloadUtil.composePayload(Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
            payload = PayloadUtil.composePayload(Outcome.ERROR);
        }
        return new GenericMessage(payload, responseHeaders);

    }
}