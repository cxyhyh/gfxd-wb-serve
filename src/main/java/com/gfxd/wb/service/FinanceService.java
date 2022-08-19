package com.gfxd.wb.service;

import com.alibaba.fastjson.JSONObject;
import com.gfxd.wb.entity.dto.DocInfoVo;
import com.gfxd.wb.entity.dto.FinanceVo;
import com.gfxd.wb.entity.model.Finance;
import com.gfxd.wb.entity.model.LoginUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author: hyh
 * Date: 2022/8/12 16:01
 * Describe:
 */
public interface FinanceService {

    JSONObject findListFinance(Finance finance);

    JSONObject initListFinance();

    JSONObject initEditFinance(Finance finance);

    JSONObject saveFinance(FinanceVo financeVo);

    JSONObject updateFinanceBaseInfo(Finance finance);

    JSONObject checkDocExit(String projectId, String docType);

    JSONObject deleteFinanceMain(Finance finance);

    JSONObject importExcelAboutFinance(DocInfoVo docInfo);

    void exportFinanceReport(String id, HttpServletRequest request,HttpServletResponse response) throws IOException;

    void exportCollectionExcel(HttpServletRequest request,HttpServletResponse response) throws IOException;

    JSONObject getExportFinanceName(String id);
}
