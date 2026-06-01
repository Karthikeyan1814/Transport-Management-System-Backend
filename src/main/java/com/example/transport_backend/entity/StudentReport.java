package com.example.transport_backend.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_reports")
public class StudentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // who is creating the report (student)
    @Column(length = 50)
    private String role;          // e.g. "student"

    @Column(length = 50)
    private String fromRole;      // duplicate of role / for flexibility

    @Column(length = 255)
    private String senderEmail;

    // who should receive it: "admin", "staff", "both"
    @Column(length = 50)
    private String target;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private boolean resolved = false;

    private LocalDateTime createdAt;

    // Store image as bytes (simple option). You can later switch to disk path/URL if you prefer.
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @Column(length = 100)
    private String imageContentType;

    // --- getters & setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFromRole() { return fromRole; }
    public void setFromRole(String fromRole) { this.fromRole = fromRole; }

    public String getSenderEmail() { return senderEmail; }
    public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }

    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }

    public String getImageContentType() { return imageContentType; }
    public void setImageContentType(String imageContentType) { this.imageContentType = imageContentType; }
}