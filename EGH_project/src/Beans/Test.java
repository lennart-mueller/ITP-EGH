package Beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Test implements Serializable {

    private String addressFlag;

    @PostConstruct
    public void init() {
        addressFlag = "0";
    }

    public String getAddressFlag() {
        return addressFlag;
    }

    public void setAddressFlag(String addressFlag) {
        this.addressFlag = addressFlag;
    }
}