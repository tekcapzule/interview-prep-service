package com.tekcapsule.interviewprep.domain.repository;

import com.tekcapsule.core.domain.CrudRepository;
import com.tekcapsule.interviewprep.domain.model.Course;

import java.util.List;

public interface InterviewPrepDynamoRepository extends CrudRepository<Course, String> {

    List<Course> findAllByTopicCode(String topicCode);
}
