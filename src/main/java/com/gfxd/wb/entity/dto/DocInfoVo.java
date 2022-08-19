package com.gfxd.wb.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocInfoVo implements Serializable{

    private String docRelatedId;
    private String docName;
    private String docPath;
    private String fileSize;
    private String fileSuffix;
    private String fileType;
    private String financeId;
    private String newFileName;
    private String realPath;
}
