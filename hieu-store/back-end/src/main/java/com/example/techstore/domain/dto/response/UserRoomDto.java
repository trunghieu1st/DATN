package com.example.techstore.domain.dto.response;

import com.example.techstore.domain.dto.common.DateAuditingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRoomDto extends DateAuditingDto {
    private String userId;
    private String roomId;
}