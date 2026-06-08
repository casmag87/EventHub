package model;


import jakarta.persistence.*;
import model.DTO.IncidentDTO;
import state.State;

import java.time.LocalDateTime;

@Entity
@Table(name = "incident")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private State status;

    private String createdByUserId;

    private String assignedToUserId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Incident() {
    }

    public Incident(String title, String description, State status,
                    String createdByUserId, String assignedToUserId,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdByUserId = createdByUserId;
        this.assignedToUserId = assignedToUserId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Incident(IncidentDTO incidentDTO){
        this.title = incidentDTO.getTitle();
        this.description = incidentDTO.getDescription();
        this.status = incidentDTO.getStatus();
        this.createdByUserId = incidentDTO.getCreatedByUserId();
        this.assignedToUserId = incidentDTO.getAssignedToUserId();
        this.createdAt = incidentDTO.getCreatedAt();
        this.updatedAt = incidentDTO.getUpdatedAt();
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAssignedToUserId() {
        return assignedToUserId;
    }

    public void setAssignedToUserId(String assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

