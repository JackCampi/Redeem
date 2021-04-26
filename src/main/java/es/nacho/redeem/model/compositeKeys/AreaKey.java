package es.nacho.redeem.model.compositeKeys;

import java.io.Serializable;


public class AreaKey implements Serializable {

    public AreaKey(Long companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    private Long companyId;

    private String name;

}
