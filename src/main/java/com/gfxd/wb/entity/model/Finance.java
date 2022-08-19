package com.gfxd.wb.entity.model;

import lombok.Data;
import lombok.ToString;

/**
 * 财务实体
 * @author lion
 */
@Data
@ToString
public class Finance {

	private String id;
	private String financeId;//财务ID
	private String fiscalYear;//年度
	private String reportType;//季度
	private String auditStatus;//是否审阅
	private String isCumulativeData;//是否累计数据
	private String vcfinanceDate;//填报时间
	private String financeCurrency;//币种
	private String entId; //企业编码
	private String vcEnterpriseName;//企业名称
	private String feedbackState;// 反馈状态
	
	private String enterpriseShort;//企业简称
	 
	private String enterpriseShortEn;//企业简称(EN)
	
	private String operateinfId;//运营信息id
	private String reportStatus;//反馈状态运营信息
	private String operateDate;//运营信息填报时间
    public static final String FINANCE = "Finance"; // 用于存储资源文档类型
    private String unit;
    private String reportStage;//上报阶段
    private String reportedStatus; //为1 为上报数据
    private String sourceId; //来源id
    private String appearOperaction; //上报操作状态
	private String isStampedVersionAudit;
	private String currentUserId;//当前登录人

	private String startTime;
	private String endTime;

	private String auditor;
	private String auditorName;
	private String entPerson;
	private String entPersonPhone;
	private String entCommitment;
	private String teamId;

	private String fundId;
	private String projectLeaderName;
	private String stageId;
	private String userId;
	private String ids;
	private String gfDocId;
	private String token;
}
