package com.lernopus.lernopus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.lernopus.lernopus.model.LaLearnAttachments;
import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.model.LaLearnTechnology;
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.payload.LaCourseResponse;
import com.lernopus.lernopus.payload.LaUserSummary;

public class ModelMapper {

    public static LaCourseResponse mapCourseToCourseResponse(LaLearnCourse course, LaLearnUser creator) {
        LaCourseResponse courseResponse = new LaCourseResponse();
        courseResponse.setLearnCourseId(course.getLaCourseId());
        courseResponse.setLaLearnCourseName(course.getLaCourseName());
        courseResponse.setLaCreatedAt(course.getLaCreatedAt());
        courseResponse.setLaCourseContentHtml(course.getLaCourseContentHtml());
        courseResponse.setLaCourseContentText(course.getLaCourseContentText());
        courseResponse.setLaIsNote(course.getLaIsNote());
        Set<LaLearnTechnology> techTagSet = course.getLaTechTag();
        List<String> techTagList = new ArrayList<>();
        techTagSet.stream().forEach(techTag ->{
        	techTagList.add(techTag.getName());
        });
        courseResponse.setLaTechTag(techTagList);
        List<LaLearnAttachments> laLearnAttachmentsList = course.getLaLearnAttachments();
        List<Map<String,Object>> attachParamList = new ArrayList<>();
        laLearnAttachmentsList.stream().forEach(learnAttach->{
        	Map<String,Object> attachParam = new HashMap<>();
        	attachParam.put("laAttachPreview", learnAttach.getLaAttachPreview());
        	attachParam.put("laAttachName", learnAttach.getLaAttachName());
        	attachParam.put("laAttachExtension", learnAttach.getLaAttachExtension());
        	attachParam.put("laAttachFileRefId", learnAttach.getLaAttachFileRefId());
        	attachParamList.add(attachParam);
        });
        courseResponse.setLaLearnAttachments(attachParamList);
        if(Objects.nonNull(creator))
        {
        	LaUserSummary creatorSummary = new LaUserSummary(creator.getLaUserId(), creator.getLaUserName(), creator.getLaUserFullName(),creator.getLaImagePath());
        	courseResponse.setCreatedBy(creatorSummary);
        }
        return courseResponse;
    }

}
