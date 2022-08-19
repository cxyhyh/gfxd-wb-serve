package com.gfxd.wb.service;

import com.alibaba.fastjson.JSONObject;
import com.gfxd.wb.entity.dto.DocInfoDto;
import com.gfxd.wb.entity.model.DocInfo;
import com.gfxd.wb.entity.model.Finance;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Author: hyh
 * Date: 2022/8/12 16:21
 * Describe:
 */
public interface DocInfoService {
    
    JSONObject findDocInfoList(DocInfoDto docInfoDto);

    JSONObject ocrProcessFile(Finance finance);

    JSONObject deleteDocInfoById(DocInfoDto docInfoDto);

    HashMap doUploadFile(MultipartFile file, String loginName);

    JSONObject saveDocInfo(DocInfo docInfo);

    void download(String docId,HttpServletResponse response) throws IOException;
}
