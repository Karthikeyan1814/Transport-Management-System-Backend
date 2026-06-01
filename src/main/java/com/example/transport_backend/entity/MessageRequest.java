package com.example.transport_backend.entity;

public class MessageRequest {

    private String role;
    private String from;
    private String senderEmail;

    private String audience;
    private String target;
    private String domain;

    private String content;
    private String message;

    private Boolean important;

    // getters/setters

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getSenderEmail() { return senderEmail; }
    public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }

    public String getAudience() { return audience; }
    public void setAudience(String audience) { this.audience = audience; }

    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }

    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Boolean getImportant() { return important; }
    public void setImportant(Boolean important) { this.important = important; }
}