package es.nacho.redeem.web.dto.report;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PendingShipmentsDto {

   private String name;
   private long purchaseId;
   private long totalCost;

}
