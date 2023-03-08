package src.main.java.com.bank.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "информация об id таблиц")
public class HistoryDTO {

    @Schema(description = "Первичный ключ")
    private Long id;

    @Schema(description = "id таблицы трансфера")
    private Long transfer_audit_id;

    @Schema(description = "id таблицы профиля")
    private Long profile_audit_id;

    @Schema(description = "id таблицы аккаунта")
    private Long account_audit_id;

    @Schema(description = "id таблицы анти-фрода")
    private Long anti_fraud_audit_id;

    @Schema(description = "id таблицы банка")
    private Long public_bank_info_audit_id;

    @Schema(description = "id таблицы авторизации профиля")
    private Long authorization_audit_id;
}
