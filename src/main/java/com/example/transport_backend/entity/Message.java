package com.example.transport_backend.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // who is sending
    @Column(length = 50)
    private String role;          // "admin" | "staff" | "student"

    @Column(length = 50)
    private String fromRole;      // duplicate if you want; frontend sends "from"

    @Column(length = 255)
    private String senderEmail;

    // who should receive
    @Column(length = 50)
    private String audience;      // "all" | "staff" | "admin" | "student"
    @Column(length = 50)
    private String target;        // same as audience
    @Column(length = 50)
    private String toDomain;      // maps frontend "domain"/"to" if you need

    @Column(columnDefinition = "text")
    private String content;

    private boolean important = false;

    private LocalDateTime createdAt;

    // --- getters/setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFromRole() { return fromRole; }
    public void setFromRole(String fromRole) { this.fromRole = fromRole; }

    public String getSenderEmail() { return senderEmail; }
    public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }

    public String getAudience() { return audience; }
    public void setAudience(String audience) { this.audience = audience; }

    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }

    public String getToDomain() { return toDomain; }
    public void setToDomain(String toDomain) { this.toDomain = toDomain; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public boolean isImportant() { return important; }
    public void setImportant(boolean important) { this.important = important; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}