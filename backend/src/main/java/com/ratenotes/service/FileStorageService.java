package com.ratenotes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path root;

    public FileStorageService(@Value("${app.upload.dir}") String dir) throws Exception {
        root = Paths.get(dir).toAbsolutePath();
        Files.createDirectories(root);
    }

    public StoredFile save(MultipartFile file) throws Exception {
        String original = file.getOriginalFilename();
        String ext = original.substring(original.lastIndexOf("."));
        String stored = UUID.randomUUID() + ext;

        Files.copy(file.getInputStream(), root.resolve(stored), StandardCopyOption.REPLACE_EXISTING);

        return new StoredFile(original, stored);
    }

    public record StoredFile(String original, String stored) {}
}