/**
 * Author: hyh
 * Date: 2022/8/8 17:34
 * Describe:
 */

package com.gfxd.wb.entity.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Author: hyh
 * Date: 2022/8/8 17:34
 * Describe:
 */
@Data
@ToString
public class FinanceVo {

    private String financeId;//财务ID
    private String fiscalYear;//年度
    private String reportType;//季度
    private String auditStatus;//是否审阅
    private String isCumulativeData;//是否累计数据
    private String vcfinanceDate;//填报时间
    private String financeCurrency;//币种
    private String entId; //企业编码
    private String vcEnterpriseName;//企业名称
    private String feedbackState; // 反馈状态
    private String balanceSheetJson; // 资产负债表
    private String incomeStatementJson; // 利润表
    private String cashFlowStatementJson; // 现金流量表
    private Boolean continueSave; // 是否继续保存
    private String reportStatus;
    private String isStampedVersionAudit;
    private String startTime;
    private String endTime;

    private String auditor;
    private String auditorName;
    private String entPerson;
    private String entPersonPhone;
    private String entCommitment;
    private String token;

}
