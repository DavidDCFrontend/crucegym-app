package com.crucegym.dtos;

import java.time.LocalDate;
import java.util.List;

public class TrainingRequestDTO {

    private LocalDate date;
    private Integer userId;
    private List<Long> seriesIds;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Long> getSeriesIds() {
        return seriesIds;
    }

    public void setSeriesIds(List<Long> seriesIds) {
        this.seriesIds = seriesIds;
    }
}
