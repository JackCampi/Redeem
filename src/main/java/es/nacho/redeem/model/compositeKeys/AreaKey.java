package es.nacho.redeem.model.compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class AreaKey implements Serializable {

    public AreaKey(String name, Long companyId) {
        this.companyId = companyId;
        this.name = name;
    }

    public AreaKey() {
    }

    @Column(name = "company_comp_id", nullable = false)
    private Long companyId;

    @Column(name="area_name", nullable = false)
    private String name;

    public Long getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}