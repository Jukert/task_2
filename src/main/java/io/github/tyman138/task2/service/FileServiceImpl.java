package io.github.tyman138.task2.service;

import io.github.tyman138.task2.entity.DataBaseInfoFile;
import io.github.tyman138.task2.fileWriter.CustomFileWriter;
import io.github.tyman138.task2.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;
    @Value("${DbFile.path}")
    private String path;

    @Override
    public DataBaseInfoFile findById(Long id) {
        return fileRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public void save(MultipartFile file) {
        new CustomFileWriter().writeToFile(file, path);
        fileRepository.save(new DataBaseInfoFile(path, file.getOriginalFilename()));
    }
}
