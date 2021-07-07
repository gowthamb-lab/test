package org.example.model.entity;


import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

import javax.persistence.*;

@Entity
//@Getter
//@Setter
public class Administrator {
    @Id
    String AdminId;

    String username;

    @Column(unique=true)
    String email;

    @OneToOne(fetch= FetchType.EAGER)
    private locations locality;


    public String getAdminId() {
        return AdminId;
    }

    public void setAdminId(String adminId) {
        AdminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public locations getLocality() {
        return locality;
    }

    public void setLocality(locations locality) {
        this.locality = locality;
    }
}
