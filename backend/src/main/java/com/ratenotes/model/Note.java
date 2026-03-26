package com.ratenotes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notes")
public class Note {

    @Id
    private String id;

    private String title;
    private String subject;
    private String source;
    private String studentName;
    private String rollNo;

    private String originalFileName;
    private String storedFileName;
    private String fileUrl;

    private int ratingSum;
    private int ratingCount;
    private double averageRating;

    private LocalDateTime uploadedAt = LocalDateTime.now();

    // ===== GETTERS & SETTERS =====

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }

    public String getOriginalFileName() { return originalFileName; }
    public void setOriginalFileName(String originalFileName) { this.originalFileName = originalFileName; }

    public String getStoredFileName() { return storedFileName; }
    public void setStoredFileName(String storedFileName) { this.storedFileName = storedFileName; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public int getRatingSum() { return ratingSum; }
    public void setRatingSum(int ratingSum) { this.ratingSum = ratingSum; }

    public int getRatingCount() { return ratingCount; }
    public void setRatingCount(int ratingCount) { this.ratingCount = ratingCount; }

    public double getAverageRating() { return averageRating; }
    public void setAverageRating(double averageRating) { this.averageRating = averageRating; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    // ===== LOGIC =====
    public void addRating(int rating) {
        this.ratingSum += rating;
        this.ratingCount++;
        this.averageRating = (double) ratingSum / ratingCount;
    }
}s