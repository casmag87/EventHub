package model.DTO;

import model.Incident;
import state.State;

import java.time.LocalDateTime;

public class IncidentDTO {

    private Long id;
    private String title;
    private String description;
    private State status;
    private String createdByUserId;
    private String assignedToUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public IncidentDTO() {
    }

    public IncidentDTO(Incident incident) {

        this.id = incident.getId();
        this.title = incident.getTitle();
        this.description = incident.getDescription();
        this.status = incident.getStatus();
        this.createdByUserId = incident.getCreatedByUserId();
        this.assignedToUserId = incident.getAssignedToUserId();
        this.createdAt = incident.getCreatedAt();
        this.updatedAt = incident.getUpdatedAt();
    }

    public IncidentDTO(Long id, String title, String description, State status,
                       String createdByUserId, String assignedToUserId,
                       LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdByUserId = createdByUserId;
        this.assignedToUserId = assignedToUserId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public State getStatus() {
        return status;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public String getAssignedToUserId() {
        return assignedToUserId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(State status) {
        this.status = status;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public void setAssignedToUserId(String assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

