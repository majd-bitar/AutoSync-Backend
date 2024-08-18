package com.autosync.autosync.models;


import com.autosync.autosync.utils.JsonConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "diagnostics")
public class DiagnosticModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diagnostic_id",unique = true,nullable = false)
    private UUID diagnosticId;

    @Column(name = "diagnostic_data", columnDefinition = "jsonb")
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> diagnosticData;

    @Column(name = "diagnostic_date")
    private Date diagnosticDate;

}
