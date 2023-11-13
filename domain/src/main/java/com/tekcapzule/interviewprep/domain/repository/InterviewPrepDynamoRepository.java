package com.tekcapzule.interviewprep.domain.repository;

import com.tekcapzule.core.domain.CrudRepository;
import com.tekcapzule.interviewprep.domain.model.Course;

import java.util.List;

public interface InterviewPrepDynamoRepository extends CrudRepository<Course, String> {

    List<Course> findAllByTopicCode(String topicCode);
}
