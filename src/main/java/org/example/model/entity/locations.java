package org.example.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class locations {

    @Id
    String locationId;

    Double latitude;
    Double longitude;
    String addressLine1;
    String AddressLine2;

//    @OneToOne(fetch= FetchType.EAGER)
//    private Administrator admin;


}
