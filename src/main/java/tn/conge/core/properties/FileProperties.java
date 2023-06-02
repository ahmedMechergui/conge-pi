package tn.conge.core.properties;

import lombok.Data;
import tn.conge.domain.storage.enums.FileType;

@Data
public class FileProperties {
    private String baseDir;
    private String defaultPath;

    public String getPathByFileType(FileType type) {
        switch (type) {
            default:
                return defaultPath;
        }
    }
}