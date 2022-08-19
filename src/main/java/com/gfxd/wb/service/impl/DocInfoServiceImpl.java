/**
 * Author: hyh
 * Date: 2022/8/12 16:32
 * Describe:
 */

package com.gfxd.wb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gfxd.wb.constant.CommonApi;
import com.gfxd.wb.entity.dto.DocInfoDto;
import com.gfxd.wb.entity.model.DocInfo;
import com.gfxd.wb.entity.model.Finance;
import com.gfxd.wb.service.DocInfoService;
import com.gfxd.wb.service.UserInfoService;
import com.gfxd.wb.utils.CommonInputStreamResource;
import com.gfxd.wb.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Author: hyh
 * Date: 2022/8/12 16:32
 * Describe:
 */
@Service
@Slf4j
public class DocInfoServiceImpl implements DocInfoService {

    /**服务器ip*/
    @Value("${http.hostname}")
    private String hostname;
    /**token*/
    @Value("${http.token}")
    private String token;
    @Resource
    HttpUtils httpUtils;
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public JSONObject findDocInfoList(DocInfoDto docInfoDto) {
        docInfoDto.setCurrentUserId(userInfoService.getCurrentUser().get("currentUserId").toString());
        docInfoDto.setToken(token);
        JSONObject result = httpUtils.postForDocInfoDto(hostname+ CommonApi.FIND_DOC_URL,docInfoDto);
        return result;
    }

    @Override
    public JSONObject ocrProcessFile(Finance finance) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        String gfDocId = finance.getGfDocId();
        map.add("gfDocId", gfDocId);
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.OCR_URL,map);
        return result;
    }

    @Override
    public JSONObject deleteDocInfoById(DocInfoDto docInfoDto) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        String docId = docInfoDto.getDocId();
        map.add("docId", docId);
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.DELETE_DOC_URL,map);
        return result;
    }

    @Override
    public HashMap doUploadFile(MultipartFile file, String loginName) {
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        CommonInputStreamResource commonInputStreamResource = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        try {
            commonInputStreamResource = new CommonInputStreamResource(file.getInputStream(), file.getSize(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            log.info("文件输入流转换错误");
        }
        requestBody.add("files", commonInputStreamResource);
        requestBody.add("loginName", loginName);
        requestBody.add("token", token);
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, headers);
        HashMap result = httpUtils.postForFileMap(hostname+CommonApi.UPLOAD_DOC_URL,requestEntity);
        return result;
    }

    @Override
    public JSONObject saveDocInfo(DocInfo docInfo) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        docInfo.setUserid(userInfoService.getCurrentUser().get("currentUserId").toString());
        map.add("docType", docInfo.getDocType());
        map.add("doctype", docInfo.getFileType());
        map.add("filePath", docInfo.getFilePath());
        map.add("fileSuffix", docInfo.getFileSuffix());
        map.add("fileSize", docInfo.getFileSize());
        map.add("newFileName", docInfo.getNewFileName());
        map.add("projectid", docInfo.getProjectid());
        map.add("vcdocname", docInfo.getVcdocname());
        map.add("vcdocpath", docInfo.getVcdocpath());
        map.add("userid", docInfo.getUserid());
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.SAVE_DOC_URL,map);
        return result;
    }

    @Override
    public void download(String docId, HttpServletResponse response) throws IOException {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("docId", docId);
        httpUtils.postForDownload(hostname+CommonApi.DOWNLOAD_DOC_URL,map,response);
    }
}
