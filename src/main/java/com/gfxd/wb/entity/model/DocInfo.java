package com.gfxd.wb.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocInfo implements Serializable{

    private String docId;
    private String lcpyId;
    private String docType;
    private String docname;
    private String operateUser;
    private String operateTime;
    private Integer actiontype;
    private String filePath;
    private String realPath;
    private String newFileName;
    private String fileSize;
    private String fileSuffix;
    private String fileType;
    private String doctypeid;// 文档类型
    private String projectid;// 项目ID
    private String projectCode;//项目编码
    private String projectName;//项目全称
    private String projectShort;//项目简称
    private String proName;//项目代称
    private String vcdocname;// 文档名称
    private String lastvcdocname;// 修改前的名称，主要用于保存历史文件使用
    private String year;//对应年份
    private String lastdocname;// 上一个文件实际名称
    private String havesupplyed;// 是否已提供
    private String supplytype;// 提供方式
    private String sourcetype;// 来源方式
    private String docstatus;// 文档状态
    private String docsize;// 文档大小
    private String lastdocsize;// 未修改前文档的大小
    private String userid;// 用户ID
    private String updateuserid;// 更新用户
    private String canmodift;// 是否可修改//1可以修改，2不可以修改
    private String remark;// 备注信息
    private String orderstr;// 用于排序
    private String vcdocpath;
    private String lastvcdocpath;// 未修改前的文件路径
    private String delFlag;// 是否删除 默认为0 删除为1
    private String duetypeId;// 尽调方式
    private String havedoneId;
    private String firstName;// VC_FIRST_TYPENAME
    private String secondName;// VC_SEC_TYPENAME
    private String proLeader;//项目主负责人
    private String depId;//管理平台
    private String isPush;//是否推送
    private String pushDate;//推送时间
    private String pushMan;//推送人
    private String depName;//管理平台
    private String requestId;
    private String dataType;
    private String type;
    private String otherType;
    private String userId;
    private String resource; // 驳回理由
    private String vcmodule;
    private String sourceid;
    //广发微服务文件id
    private String fileId;
    private String currentUser;
    private String financeId;
    private String docpath;
    private String docRelatedId;
}
