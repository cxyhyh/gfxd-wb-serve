/**
 * Author: hyh
 * Date: 2022/8/11 13:19
 * Describe:
 */

package com.gfxd.wb.entity.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Author: hyh
 * Date: 2022/8/11 13:19
 * Describe:
 */
@Data
@ToString
public class FinanceIndex {

    public static final Integer BALANCE_SHEET_TYPE = 1;//资产负债表
    public static final Integer INCOME_STATEMENT_TYPE = 2;//利润表
    public static final Integer CASH_FLOW_STATEMENT_TYPE = 3;//现金流量表

    private String financeId;  // 财务标识
    private Integer indexId;    // 指标标识
    private Integer indexType;  // 指标类型
    private String index1Name;  // 指标名称
    private String index1NameEn;
    private String index1SourceName;
    private BigDecimal index1QuarterValue;  // 指标期末值
    private BigDecimal index1YearValue; // 指标年初值
    private String index2Name;  // 指标名称
    private String index2NameEn;
    private String index2SourceName;
    private BigDecimal index2QuarterValue;  // 指标期末值
    private BigDecimal index2YearValue; // 指标年初度值
    private Integer indexOrder; // 指标排序
    private BigDecimal index1QuarterValueInteger;  // 指标期末值  整数
    private BigDecimal index1YearValueInteger; // 指标年末值  整数
    private BigDecimal index2QuarterValueInteger;  // 指标期末值  整数
    private BigDecimal index2YearValueInteger; // 指标年度值  整数
}
