package org.example.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    String orderId;

    Double orderPrice;

    Date date;

    @ManyToOne(fetch= FetchType.LAZY)
    private locations locality;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public locations getLocality() {
        return locality;
    }

    public void setLocality(locations locality) {
        this.locality = locality;
    }
}
