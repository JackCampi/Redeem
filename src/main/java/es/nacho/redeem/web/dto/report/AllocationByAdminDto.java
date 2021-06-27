package es.nacho.redeem.web.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllocationByAdminDto {

    private Collection<String> adminNames;
    private Collection<Integer> allocationAmounts;

}
