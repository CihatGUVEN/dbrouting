package com.cihatguven.dbrouting.master.school.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveSchoolRequest {
    String dbName;
    String code;
    String title;
}
