package com.tekcapsule.interviewprep.domain.service;

import com.tekcapsule.interviewprep.domain.command.ApproveCommand;
import com.tekcapsule.interviewprep.domain.command.CreateCommand;
import com.tekcapsule.interviewprep.domain.command.RecommendCommand;
import com.tekcapsule.interviewprep.domain.command.UpdateCommand;
import com.tekcapsule.interviewprep.domain.model.Course;
import com.tekcapsule.interviewprep.domain.model.Status;
import com.tekcapsule.interviewprep.domain.repository.InterviewPrepDynamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class InterviewPrepServiceImpl implements InterviewPrepService {
    private InterviewPrepDynamoRepository interviewPrepDynamoRepository;

    @Autowired
    public InterviewPrepServiceImpl(InterviewPrepDynamoRepository interviewPrepDynamoRepository) {
        this.interviewPrepDynamoRepository = interviewPrepDynamoRepository;
    }

    @Override
    public void create(CreateCommand createCommand) {

        log.info(String.format("Entering create interview prep course service - Module Code :%s", createCommand.getTopicCode()));

        Course course = Course.builder()
                .title(createCommand.getTitle())
                .topicCode(createCommand.getTopicCode())
                .author(createCommand.getAuthor())
                .publisher(createCommand.getPublisher())
                .duration(createCommand.getDuration())
                .resourceUrl(createCommand.getResourceUrl())
                .summary(createCommand.getSummary())
                .description(createCommand.getDescription())
                .prizingModel(createCommand.getPrizingModel())
                .imageUrl(createCommand.getImageUrl())
                .promotion(createCommand.getPromotion())
                .status(Status.SUBMITTED)
                .recommendations(createCommand.getRecommendations())
                .build();

        course.setAddedOn(createCommand.getExecOn());
        course.setAddedBy(createCommand.getExecBy().getUserId());

        interviewPrepDynamoRepository.save(course);
    }

    @Override
    public void update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update interview prep course service - Course ID:%s", updateCommand.getCourseId()));

        Course course = interviewPrepDynamoRepository.findBy(updateCommand.getCourseId());
        if (course != null) {
            course.setTitle(updateCommand.getTitle());
            course.setTopicCode(updateCommand.getTopicCode());
            course.setAuthor(updateCommand.getAuthor());
            course.setPublisher(updateCommand.getPublisher());
            course.setDuration(updateCommand.getDuration());
            course.setResourceUrl(updateCommand.getResourceUrl());
            course.setSummary(updateCommand.getSummary());
            course.setDescription(updateCommand.getDescription());
            course.setPrizingModel(updateCommand.getPrizingModel());
            course.setPromotion(updateCommand.getPromotion());
            course.setImageUrl(updateCommand.getImageUrl());
            course.setUpdatedOn(updateCommand.getExecOn());
            course.setUpdatedBy(updateCommand.getExecBy().getUserId());
            course.setRecommendations(updateCommand.getRecommendations());
            interviewPrepDynamoRepository.save(course);
        }
    }

    @Override
    public void recommend(RecommendCommand recommendCommand) {
        log.info(String.format("Entering recommend InterviewPrep service -  interviewprep code:%s", recommendCommand.getCourseId()));

        Course course = interviewPrepDynamoRepository.findBy(recommendCommand.getCourseId());
        if (course != null) {
            Integer recommendationsCount = course.getRecommendations();
            recommendationsCount += 1;
            course.setRecommendations(recommendationsCount);

            course.setUpdatedOn(recommendCommand.getExecOn());
            course.setUpdatedBy(recommendCommand.getExecBy().getUserId());

            interviewPrepDynamoRepository.save(course);
        }
    }

    @Override
    public List<Course> findAll() {

        log.info("Entering findAll interview prep Course service");

        return interviewPrepDynamoRepository.findAll();
    }

    @Override
    public List<Course> findAllByTopicCode(String topicCode) {

        log.info(String.format("Entering findAllByTopicCode interview prep Course service - Module code:%s", topicCode));

        return interviewPrepDynamoRepository.findAllByTopicCode(topicCode);
    }

    @Override
    public void approve(ApproveCommand approveCommand) {
        log.info(String.format("Entering approve interview prep course service -  courseId:%s", approveCommand.getCourseId()));

        Course course = interviewPrepDynamoRepository.findBy(approveCommand.getCourseId());
        if (course != null) {
            course.setStatus(Status.ACTIVE);

            course.setUpdatedOn(approveCommand.getExecOn());
            course.setUpdatedBy(approveCommand.getExecBy().getUserId());

            interviewPrepDynamoRepository.save(course);
        }
    }


}
