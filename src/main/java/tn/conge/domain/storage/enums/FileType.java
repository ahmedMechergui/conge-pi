package tn.conge.domain.storage.enums;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public enum FileType {

    ANY(AnyExtension.class);

    public static final String DEFAULT_VALUE = "ANY";
    private final Set<String> extensionSet;
    private final Class<? extends Enum<?>> extensionClass;

    FileType(Class<? extends Enum<?>> extensionClass) {
        this.extensionClass = extensionClass;
        this.extensionSet = new HashSet<>();
        Enum<?>[] enumExtensions = extensionClass.getEnumConstants();
        for (Enum<?> ext : enumExtensions) {
            extensionSet.add(ext.name().toLowerCase());
        }
    }
}
