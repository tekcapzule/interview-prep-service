package com.tekcapzule.interviewprep.domain.service;

import com.tekcapzule.interviewprep.domain.command.ApproveCommand;
import com.tekcapzule.interviewprep.domain.command.CreateCommand;
import com.tekcapzule.interviewprep.domain.command.RecommendCommand;
import com.tekcapzule.interviewprep.domain.command.UpdateCommand;
import com.tekcapzule.interviewprep.domain.model.Course;

import java.util.List;


public interface InterviewPrepService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    List<Course> findAll();

    List<Course> findAllByTopicCode(String code);
    void approve(ApproveCommand approveCommand);
    void recommend(RecommendCommand recommendCommand);
}
