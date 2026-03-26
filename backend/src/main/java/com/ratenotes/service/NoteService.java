package com.ratenotes.service;

import com.ratenotes.model.Note;
import com.ratenotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository repo;

    public NoteService(NoteRepository repo) {
        this.repo = repo;
    }

    public Note save(Note note) {
        return repo.save(note);
    }

    public List<Note> getAll() {
        return repo.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Note::getAverageRating).reversed())
                .toList();
    }

    public Note rate(String id, int rating) {
        Note n = repo.findById(id).orElseThrow();
        n.addRating(rating);
        return repo.save(n);
    }
}