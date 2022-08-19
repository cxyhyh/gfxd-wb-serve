/**
 * Author: hyh
 * Date: 2022/8/12 16:01
 * Describe:
 */

package com.gfxd.wb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gfxd.wb.constant.CommonApi;
import com.gfxd.wb.entity.dto.DocInfoVo;
import com.gfxd.wb.entity.dto.FinanceVo;
import com.gfxd.wb.entity.model.Finance;
import com.gfxd.wb.entity.model.FinanceIndex;
import com.gfxd.wb.entity.model.LoginUser;
import com.gfxd.wb.service.FinanceService;
import com.gfxd.wb.service.UserInfoService;
import com.gfxd.wb.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Author: hyh
 * Date: 2022/8/12 16:01
 * Describe:
 */
@Service
public class FinanceServiceImpl implements FinanceService {

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
    public JSONObject findListFinance(Finance finance) {

        finance.setUserId(userInfoService.getCurrentUser().get("currentUserId").toString());
        finance.setToken(token);
        JSONObject result = httpUtils.postForFinance(hostname+ CommonApi.FIND_FINANCE_URL,finance);
        return result;
    }

    @Override
    public JSONObject initListFinance() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("userId", userInfoService.getCurrentUser().get("currentUserId").toString());
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.INIT_FINANCE_URL,map);
        return result;
    }

    @Override
    public JSONObject initEditFinance(Finance finance) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        String id = finance.getFinanceId();
        map.add("id", id);
        map.add("userId", userInfoService.getCurrentUser().get("currentUserId").toString());
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.INIT_EDIT_FINANCE_URL,map);
        return result;
    }

    @Override
    public JSONObject saveFinance(FinanceVo financeVo) {
        financeVo.setToken(token);
        financeVo.setBalanceSheetJson(listToJson(financeVo.getBalanceSheetJson(), FinanceIndex.BALANCE_SHEET_TYPE));
        financeVo.setIncomeStatementJson(listToJson(financeVo.getIncomeStatementJson(), FinanceIndex.INCOME_STATEMENT_TYPE));
        financeVo.setCashFlowStatementJson(listToJson(financeVo.getCashFlowStatementJson(), FinanceIndex.CASH_FLOW_STATEMENT_TYPE));
        JSONObject result = httpUtils.postForFinanceVo(hostname+ CommonApi.SAVE_FINANCE_URL,financeVo);
        return result;
    }

    @Override
    public JSONObject updateFinanceBaseInfo(Finance finance) {
        finance.setToken(token);
        JSONObject result = httpUtils.postForFinance(hostname+ CommonApi.UPDATE_FINANCE_URL,finance);
        return result;
    }

    @Override
    public JSONObject checkDocExit(String projectId, String docType) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("projectId", projectId);
        map.add("docType", docType);
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.CHECK_URL,map);
        return result;
    }

    @Override
    public JSONObject deleteFinanceMain(Finance finance) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        String id = finance.getId();
        map.add("id", id);
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.DELETE_FINANCE_URL,map);
        return result;
    }

    @Override
    public JSONObject importExcelAboutFinance(DocInfoVo docInfo) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("fileType", docInfo.getFileType());
        map.add("docPath", docInfo.getDocPath());
        map.add("fileSuffix", docInfo.getFileSuffix());
        map.add("fileSize", docInfo.getFileSize());
        map.add("newFileName", docInfo.getNewFileName());
        map.add("docRelatedId", "");
        map.add("docName", docInfo.getDocName());
        map.add("realPath", docInfo.getRealPath());
        map.add("financeId", docInfo.getFinanceId());
        map.add("token", token);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.IMPORT_FINANCE_URL,map);
        return result;
    }

    @Override
    public void exportFinanceReport(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = this.getExportFinanceName(id).get("data").toString();
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("id", id);
        httpUtils.postForFile(hostname+CommonApi.EXPORT_FINANCE_URL,map,fileName,request,response);
    }

    @Override
    public void exportCollectionExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String today = LocalDate.now().toString();
        String fileName ="财务信息管理表-"+today;
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        httpUtils.postForFile(hostname+CommonApi.EXPORT_COLLECTION_URL,map,fileName,request,response);
    }

    @Override
    public JSONObject getExportFinanceName(String id) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("id", id);
        JSONObject result = httpUtils.postForMap(hostname+CommonApi.EXPORT_FINANCE_NAME_URL,map);
        return result;
    }

    public String listToJson(String jsonStr, Integer type) {
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        List<FinanceIndex> balanceSheetList = JSONObject.parseArray(jsonArray.toJSONString(), FinanceIndex.class);
        for (FinanceIndex balanceSheetIndex : balanceSheetList) {
            if (balanceSheetIndex.getFinanceId() == null) {
                balanceSheetIndex.setFinanceId(StrUtil.nullToEmpty(balanceSheetIndex.getFinanceId()));
            }
            if (balanceSheetIndex.getIndex1Name() == null) {
                balanceSheetIndex.setIndex1Name(StrUtil.nullToEmpty(balanceSheetIndex.getIndex1Name()));
            }
            if (balanceSheetIndex.getIndex1NameEn() == null) {
                balanceSheetIndex.setIndex1NameEn(StrUtil.nullToEmpty(balanceSheetIndex.getIndex1NameEn()));
            }
            if (balanceSheetIndex.getIndex1SourceName() == null) {
                balanceSheetIndex.setIndex1SourceName(StrUtil.nullToEmpty(balanceSheetIndex.getIndex1SourceName()));
            }
            if (balanceSheetIndex.getIndex2Name() == null) {
                balanceSheetIndex.setIndex2Name(StrUtil.nullToEmpty(balanceSheetIndex.getIndex2Name()));
            }
            if (balanceSheetIndex.getIndex2NameEn() == null) {
                balanceSheetIndex.setIndex2NameEn(StrUtil.nullToEmpty(balanceSheetIndex.getIndex2NameEn()));
            }
            if (balanceSheetIndex.getIndex2SourceName() == null) {
                balanceSheetIndex.setIndex2SourceName(StrUtil.nullToEmpty(balanceSheetIndex.getIndex2SourceName()));
            }


            balanceSheetIndex.setIndexType(type);
        }
        String result = JSONArray.parseArray(JSON.toJSONString(balanceSheetList)).toString();
        return result;

    }
}
