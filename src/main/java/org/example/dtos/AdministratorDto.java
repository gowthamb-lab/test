package org.example.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdministratorDto implements Serializable {
    String AdminId;

    String username;

    String email;

    private locationsDto locality;

}
