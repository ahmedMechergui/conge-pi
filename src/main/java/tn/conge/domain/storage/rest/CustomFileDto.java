package tn.conge.domain.storage.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.conge.domain.storage.enums.FileUrlType;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomFileDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String fileUrl;
    private FileUrlType fileUrlType;
}
