package tn.conge.domain.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.conge.core.properties.CongeProperties;
import tn.conge.domain.storage.enums.FileUrlType;
import tn.conge.domain.storage.exceptions.FileUploadException;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalStorageServiceImpl implements StorageService {

    private final CongeProperties congeProperties;
    private final CustomFileRepository customFileRepository;
    @Value("${server.servlet.context-path}")
    private String contextPath = "";

    @Override
    public CustomFile uploadFile(MultipartFile file, String path) {
        String relativeFilePath = path + UUID.randomUUID() + file.getOriginalFilename();
        String completeFilePath = this.congeProperties.getFile().getBaseDir() + relativeFilePath;
        File myFile = new File(completeFilePath);
        saveToFileSystem(file, myFile);
        CustomFile customFile = new CustomFile(contextPath + relativeFilePath, completeFilePath, FileUrlType.RELATIVE);
        return this.customFileRepository.save(customFile);
    }


    @Override
    @SafeVarargs
    public final CustomFile uploadFile(MultipartFile file, String path, @NotNull Class<? extends Enum<?>>... enumFileExtensions) {
        checkIfFileCompliantToFileType(file.getOriginalFilename(), this.enumExtensionToString(enumFileExtensions));
        return this.uploadFile(file, path);
    }

    @Override
    public CustomFile uploadFile(MultipartFile file, String path, @NotNull String... fileExtensions) {
        checkIfFileCompliantToFileType(file.getOriginalFilename(), fileExtensions);
        return this.uploadFile(file, path);
    }

    @Override
    public CustomFile uploadFile(MultipartFile file) {
        return this.uploadFile(file, this.congeProperties.getFile().getDefaultPath());
    }

    private void saveToFileSystem(MultipartFile file, File myFile) throws FileUploadException {
        try {
            //noinspection ResultOfMethodCallIgnored
            myFile.createNewFile(); //NOSONAR
            FileOutputStream fos = new FileOutputStream(myFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException exception) {
            log.warn(exception.getMessage());
            throw new FileUploadException();
        }
    }

    @SafeVarargs
    private String[] enumExtensionToString(@NotNull Class<? extends Enum<?>>... enumFileExtensions) {
        List<String> extensions = new ArrayList<>();
        Arrays.stream(enumFileExtensions).forEach(enumFileExtensionClass -> Arrays.stream(enumFileExtensionClass.getEnumConstants()).forEach(enumFileExtensionAttribute -> extensions.add(enumFileExtensionAttribute.name())));
        return extensions.toArray(String[]::new);
    }
}
