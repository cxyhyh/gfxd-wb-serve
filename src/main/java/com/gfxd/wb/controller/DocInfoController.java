/**
 * Author: hyh
 * Date: 2022/8/12 16:21
 * Describe:
 */

package com.gfxd.wb.controller;

import com.alibaba.fastjson.JSONObject;
import com.gfxd.wb.entity.dto.DocInfoDto;
import com.gfxd.wb.entity.model.DocInfo;
import com.gfxd.wb.entity.model.Finance;
import com.gfxd.wb.service.DocInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Author: hyh
 * Date: 2022/8/12 16:21
 * Describe:
 */
@RestController
@RequestMapping("/doInfo")
@Slf4j
public class DocInfoController {

    @Autowired
    private DocInfoService docInfoService;

    /**
     * 查询文档列表
     */
    @PostMapping(value = "/findDocInfoList")
    @ResponseBody
    public JSONObject findDocInfoList(@RequestBody DocInfoDto docInfoDto) {
        JSONObject jsonObject = docInfoService.findDocInfoList(docInfoDto);
        return jsonObject;
    }

    /**
     * OCR识别
     */
    @PostMapping(value = "/ocrProcessFile")
    @ResponseBody
    public JSONObject ocrProcessFile(@RequestBody Finance finance) {
        JSONObject jsonObject = docInfoService.ocrProcessFile(finance);
        return jsonObject;
    }

    /**
     * 删除文件列表
     */
    @PostMapping(value = "/deleteDocInfoById")
    @ResponseBody
    public JSONObject deleteDocInfoById(@RequestBody DocInfoDto docInfoDto) {

        JSONObject jsonObject = docInfoService.deleteDocInfoById(docInfoDto);
        return jsonObject;
    }

    /**
     * 执行文件上传
     */
    @PostMapping(value = "/doUploadFile")
    public HashMap doUploadFile(MultipartFile file,String loginName) {
        HashMap map = docInfoService.doUploadFile(file,loginName);
        return map;
    }

    /**
     * 保存文件列表
     */
    @PostMapping(value = "/saveDocInfo")
    @ResponseBody
    public JSONObject saveDocInfo(@RequestBody DocInfo docInfo) {

        JSONObject jsonObject = docInfoService.saveDocInfo(docInfo);
        return jsonObject;
    }

    /**
     * 下载文件
     */
    @PostMapping(value = "download")
    public void download(@RequestParam("docId") String docId,HttpServletResponse response) throws IOException {
        docInfoService.download(docId,response);
    }

}
