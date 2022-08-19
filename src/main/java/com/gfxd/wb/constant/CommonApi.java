/**
 * Author: hyh
 * Date: 2022/8/12 14:13
 * Describe:
 */

package com.gfxd.wb.constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Author: hyh
 * Date: 2022/8/12 14:13
 * Describe:
 */
public class CommonApi {

    /**登录*/
    public static final String LOGIN_URL = "/s/wb/api/login.json";

    /**初始化用户列表页面*/
    public static final String INIT_USER_URL = "/s/wb/api/initListUserPage.json";

    /**保存用户*/
    public static final String SAVE_USER_URL = "/s/wb/api/saveUser.json";

    /**初始化编辑用户页面*/
    public static final String INIT_EDIT_USER_URL = "/s/wb/api/initEditUser.json";

    /**初始化修改用户密码*/
    public static final String INIT_USER_PWD_URL = "/s/wb/api/initEditUserPwd.json";

    /**修改用户密码*/
    public static final String EDIT_USER_PWD_URL = "/s/wb/api/editUserPwd.json";

    /**获取用户头像*/
    public static final String USER_AVATAR_URL = "/s/wb/api/getUserAvatar.json";

    /**财务列表*/
    public static final String FIND_FINANCE_URL = "/s/wb/api/findFinance.json";

    /**进入财务查询页面*/
    public static final String INIT_FINANCE_URL = "/s/wb/api/initListFinance.json";

    /**初始化编辑财务页面*/
    public static final String INIT_EDIT_FINANCE_URL = "/s/wb/api/initEditFinance.json";

    /**保存财务数据*/
    public static final String SAVE_FINANCE_URL = "/s/wb/api/saveFinance.json";

    /**上报前校验*/
    public static final String CHECK_URL = "/s/wb/api/checkDocExit.json";

    /**删除财务数据*/
    public static final String DELETE_FINANCE_URL = "/s/wb/api/deleteFinanceMain.json";

    /**更新财务基本信息*/
    public static final String UPDATE_FINANCE_URL = "/s/wb/api/updateFinanceBaseInfo.json";

    /**导入被投公司财务管理信息*/
    public static final String IMPORT_FINANCE_URL = "/s/wb/api/importExcelAboutFinance.json";

    /**报表导出*/
    public static final String EXPORT_FINANCE_URL = "/s/wb/api/exportFinanceReport.json";

    /**获取下载文件名称*/
    public static final String EXPORT_FINANCE_NAME_URL = "/s/wb/api/getExportFinanceName.json";

    /**批量报表导出*/
    public static final String EXPORT_BATCH_FINANCE_URL = "/s/wb/api/exportFinanceReportBatch.json";

    /**导出被投公司财务管理信息*/
    public static final String EXPORT_COLLECTION_URL = "/s/wb/api/exportCollectionExcel.json";

    /**调用OCR进行识别文件内容*/
    public static final String OCR_URL = "/s/wb/api/ocrProcessFile.json";

    /**查询文件列表*/
    public static final String FIND_DOC_URL = "/s/wb/api/findDocInfoList.json";

    /**删除文件列表*/
    public static final String DELETE_DOC_URL = "/s/wb/api/deleteDocInfoById.json";

    /**执行文件上传*/
    public static final String UPLOAD_DOC_URL = "/s/wb/api/doUploadFile.json";

    /**保存文件列表*/
    public static final String SAVE_DOC_URL = "/s/wb/api/saveDocInfo.json";

    /**下载文件*/
    public static final String DOWNLOAD_DOC_URL = "/s/wb/api/download";

    
}
