package com.spring.techpractica.ui.rest.resources.session.statistics;

import lombok.Getter;

import java.util.UUID;

@Getter
public class SessionStatisticsResources {

    private UUID sessionId;
    private long totalTasks;
    private long totalDoneTasks;
    private long duration;

}