package com.tekcapsule.interviewprep.domain.service;

import com.tekcapsule.interviewprep.domain.command.ApproveCommand;
import com.tekcapsule.interviewprep.domain.command.CreateCommand;
import com.tekcapsule.interviewprep.domain.command.RecommendCommand;
import com.tekcapsule.interviewprep.domain.command.UpdateCommand;
import com.tekcapsule.interviewprep.domain.model.Course;

import java.util.List;


public interface InterviewPrepService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    List<Course> findAll();

    List<Course> findAllByTopicCode(String code);
    void approve(ApproveCommand approveCommand);
    void recommend(RecommendCommand recommendCommand);
}
