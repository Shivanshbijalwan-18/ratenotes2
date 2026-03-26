package com.ratenotes.controller;

import com.ratenotes.model.Note;
import com.ratenotes.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin("*")
public class NoteController {

    private final NoteService noteService;
    private final FileStorageService fileService;

    public NoteController(NoteService noteService, FileStorageService fileService) {
        this.noteService = noteService;
        this.fileService = fileService;
    }

    @GetMapping
    public List<Note> all() {
        return noteService.getAll();
    }

    @PostMapping("/upload")
    public Note upload(
            @RequestParam String title,
            @RequestParam String subject,
            @RequestParam String source,
            @RequestParam String studentName,
            @RequestParam String rollNo,
            @RequestParam MultipartFile file
    ) throws Exception {

        var saved = fileService.save(file);

        Note n = new Note();
        n.setTitle(title);
        n.setSubject(subject);
        n.setSource(source);
        n.setStudentName(studentName);
        n.setRollNo(rollNo);
        n.setOriginalFileName(saved.original());
        n.setStoredFileName(saved.stored());
        n.setFileUrl("/uploads/" + saved.stored());

        return noteService.save(n);
    }

    @PostMapping("/{id}/rate")
    public Note rate(@PathVariable String id, @RequestBody Rating r) {
        return noteService.rate(id, r.rating);
    }

    static class Rating {
        public int rating;
    }
}