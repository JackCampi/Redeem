package es.nacho.redeem.web.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Comparable<ProductDto>{

    private String imageUrl;
    private String name;
    private int sales;

    @Override
    public int compareTo(ProductDto o) {

        if(this.getSales() > o.getSales()) return 1;
        else if(this.getSales() < o.getSales()) return -1;
        else return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
