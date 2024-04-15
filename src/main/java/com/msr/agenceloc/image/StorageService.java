package com.msr.agenceloc.image;

import com.msr.agenceloc.automobile.AutomobilRepository;
import com.msr.agenceloc.automobile.Automobile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    private final AutomobilRepository automobilRepository;
    private final FileDataRepository fileDataRepository;
    private final String FOLDER_PATH="/Users/esprit/www_java/projet_personnel_b3/agence-location-voiture/src/main/resources/upload/";

    public StorageService(AutomobilRepository automobilRepository, FileDataRepository fileDataRepository) {
        this.automobilRepository = automobilRepository;
        this.fileDataRepository = fileDataRepository;
    }

    public String uploadImageToFileSystem(MultipartFile file, Long automobileId) throws IOException {

        Automobile automobile = this.automobilRepository.getReferenceById(automobileId);



        String filePath=FOLDER_PATH+file.getOriginalFilename();

        FileData fileData=fileDataRepository.save(FileData.builder()
                .automobile(automobile)
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        //Récupère le chemin du fichier
        String filePath=fileData.get().getFilePath();
        //lis le ficher se trouvant dans le chemin
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public List<byte[]> downloadImagesFromFileSystem(Automobile automobile) throws IOException {
        List<FileData> fileDatas = fileDataRepository.findAllByAutomobile(automobile);
        List<byte[]> stringList = new ArrayList<>();

       for(FileData fileData: fileDatas){
           String filePath=fileData.getFilePath();
           byte[] image = Files.readAllBytes(new File(filePath).toPath());
           stringList.add(image);
       }

       return stringList;

    }
}
