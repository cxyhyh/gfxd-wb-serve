/**
 * Author: hyh
 * Date: 2022/8/3 9:39
 * Describe:
 */

package com.gfxd.wb.controller;

import com.alibaba.fastjson.JSONObject;
import com.gfxd.wb.entity.dto.DocInfoVo;
import com.gfxd.wb.entity.dto.FinanceVo;
import com.gfxd.wb.entity.model.*;
import com.gfxd.wb.service.FinanceService;;
import com.gfxd.wb.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;


/**
 * Author: hyh
 * Date: 2022/8/3 9:39
 * Describe:
 */
@RestController
@RequestMapping("/finance")
@Slf4j
public class FinanceController {

    /**服务器ip*/
    @Value("${http.hostname}")
    private String hostname;
    @Autowired
    private FinanceService financeService;


    /**
     * 被投公司财务管理列表
     */
    @PostMapping(value = "/findListFinance")
    @ResponseBody
    public JSONObject findListFinance(@RequestBody Finance finance) {

        JSONObject jsonObject = financeService.findListFinance(finance);
        return jsonObject;
    }

    /**
     * 初始化被投公司财务管理列表
     */
    @PostMapping(value = "/initListFinance")
    @ResponseBody
    public Map<String, Object> initListFinance() {
        JSONObject jsonObject = financeService.initListFinance();
        return jsonObject;
    }

    /**
     * 初始化财务新增、编辑
     */
    @PostMapping(value = "/initEditFinance")
    @ResponseBody
    public JSONObject initEditFinance(@RequestBody Finance finance) {

        JSONObject jsonObject = financeService.initEditFinance(finance);
        return jsonObject;
    }

    /**
     * 保存财务数据
     */
    @PostMapping(value = "/saveFinance")
    @ResponseBody
    public JSONObject saveFinance(@RequestBody FinanceVo financeVo) {
        JSONObject jsonObject = financeService.saveFinance(financeVo);
        return jsonObject;
    }

    /**
     * 更新财务基本信息
     */
    @PostMapping(value = "/updateFinanceBaseInfo")
    public JSONObject updateFinanceBaseInfo(@RequestBody Finance finance) {

        JSONObject jsonObject = financeService.updateFinanceBaseInfo(finance);
        return jsonObject;
    }

    /**
     * 上报前校验
     */
    @PostMapping(value = "/checkDocExit")
    @ResponseBody
    public JSONObject checkDocExit(@RequestBody String projectId, String docType) {

        JSONObject jsonObject = financeService.checkDocExit(projectId,docType);
        return jsonObject;
    }

    /**
     * 批量报表导出
     */
    @PostMapping(value = "exportFinanceReportBatch")
    public void exportFinanceReportBatch(String ids, HttpServletResponse response) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/octet-stream;charset=UTF-8");
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("ids", ids);
        ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(hostname + "/s/financeWb/exportFinanceReportBatch", map, byte[].class);
        byte[] body = responseEntity.getBody();
        try {
            // 这三行是对文件名编码，与内容编码，可以不写
            response.setContentType("application/octet-stream;charset=UTF-8");
            // 把boby数据写入输出流输出
            response.getOutputStream().write(body);
        } catch (IOException e) {
            log.error("下载失败", e);
        }
    }

    /**
     * 删除财务数据
     */
    @PostMapping(value = "/deleteFinanceMain")
    @ResponseBody
    public JSONObject deleteFinanceMain(@RequestBody Finance finance) {
        JSONObject jsonObject = financeService.deleteFinanceMain(finance);
        return jsonObject;
    }

    /**
     * 导入被投公司财务管理信息
     */
    @PostMapping(value = "/importExcelAboutFinance")
    @ResponseBody
    public JSONObject importExcelAboutFinance(@RequestBody DocInfoVo docInfo) {
        JSONObject jsonObject = financeService.importExcelAboutFinance(docInfo);
        return jsonObject;
    }

    /**
     * 报表导出
     */
    @PostMapping(value = "exportFinanceReport", produces = {"application/vnd.ms-excel;charset=UTF-8"})
    public void exportFinanceReport(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        financeService.exportFinanceReport(id,request,response);
    }

    /**
     * 获取下载文件名称
     */
    @PostMapping(value = "getExportFinanceName")
    public JSONObject getExportFinanceName(String id){
        JSONObject jsonObject = financeService.getExportFinanceName(id);
        return jsonObject;
    }

    /**
     * 下载Excel模板
     */
    @PostMapping(value = "exportCollectionExcel", produces = {"application/vnd.ms-excel;charset=UTF-8"})
    public void exportCollectionExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
        financeService.exportCollectionExcel(request,response);
    }

}
