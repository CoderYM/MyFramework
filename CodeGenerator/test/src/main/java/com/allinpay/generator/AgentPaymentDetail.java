package com.allinpay.generator;

import java.util.Date;

public class AgentPaymentDetail {
	/**
	 * 开户行行号
	 */
	private String bankNo;

	/**
	 * 商户订单号
	 */
	private String merchantOrderNo;

	/**
	 * 创建时间
	 */
	private Date createdDatetime;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 手续费结算方式(参见FeeMode)
	 */
	private Integer feeFlag;

	/**
	 * 支付批次号
	 */
	private Integer payBatchId;

	/**
	 * 账号
	 */
	private String accountNo;

	/**
	 * 创建人
	 */
	private String createdBy;

	/**
	 * 开户行所在市
	 */
	private String city;

	/**
	 * 证件号
	 */
	private String idNo;

	/**
	 * 金额
	 */
	private Long amount;

	/**
	 * 银行批次号
	 */
	private String bankBatchNo;

	/**
	 * 通联会员号
	 */
	private String aipMemberId;

	/**
	 * 开户行编码(系统内定，目前由bank_name匹配得到，便于银行拆分，也可提供人工修正)
	 */
	private Integer bankCode;

	/**
	 * 状态(EPaymentBatchDetailStatus PROCESSING:处理中；SUCCESS:成功；FAIL：失败)
	 */
	private String status;

	/**
	 * 银行协议号
	 */
	private String bankProtocolNo;

	/**
	 * 交易批次号
	 */
	private Integer transactionBatchId;

	/**
	 * 最后更新人
	 */
	private String lastUpdatedBy;

	/**
	 * 证件类型(参见EIdentifyType)
	 */
	private String idType;

	/**
	 * 开户行名称
	 */
	private String bankName;

	/**
	 * 最后更新时间
	 */
	private Date lastUpdatedDatetime;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 币种(参见CurrencyType)
	 */
	private String currency;

	/**
	 * 手续费
	 */
	private Long fee;

	/**
	 * 创建日期
	 */
	private Date createdDate;

	/**
	 * 名称
	 */
	private String accountName;

	/**
	 * 账号类别(EAccountCategory BANK_CARD：银行卡 PASSBOOK：存折 CREDIT_CARD：信用卡)
	 */
	private String accoutCategory;

	/**
	 * 商户号
	 */
	private String merchantId;

	/**
	 * 开户行所在省
	 */
	private String province;

	/**
	 * 账户类型(EAccountType PERSONAL:个人 ENTERPRISE:企业)
	 */
	private String accountType;

	/**
	 * 通联协议号
	 */
	private String tlProtocolNo;

	/**
	 * 商户批次号
	 */
	private String mchtBatchNo;

	/**
	 * 支付业务类型(EPaymentBusinessType: AGENCY_PAY：代付)
	 */
	private String paymentBusinessType;

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFeeFlag() {
		return feeFlag;
	}

	public void setFeeFlag(Integer feeFlag) {
		this.feeFlag = feeFlag;
	}

	public Integer getPayBatchId() {
		return payBatchId;
	}

	public void setPayBatchId(Integer payBatchId) {
		this.payBatchId = payBatchId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getBankBatchNo() {
		return bankBatchNo;
	}

	public void setBankBatchNo(String bankBatchNo) {
		this.bankBatchNo = bankBatchNo;
	}

	public String getAipMemberId() {
		return aipMemberId;
	}

	public void setAipMemberId(String aipMemberId) {
		this.aipMemberId = aipMemberId;
	}

	public Integer getBankCode() {
		return bankCode;
	}

	public void setBankCode(Integer bankCode) {
		this.bankCode = bankCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBankProtocolNo() {
		return bankProtocolNo;
	}

	public void setBankProtocolNo(String bankProtocolNo) {
		this.bankProtocolNo = bankProtocolNo;
	}

	public Integer getTransactionBatchId() {
		return transactionBatchId;
	}

	public void setTransactionBatchId(Integer transactionBatchId) {
		this.transactionBatchId = transactionBatchId;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getLastUpdatedDatetime() {
		return lastUpdatedDatetime;
	}

	public void setLastUpdatedDatetime(Date lastUpdatedDatetime) {
		this.lastUpdatedDatetime = lastUpdatedDatetime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccoutCategory() {
		return accoutCategory;
	}

	public void setAccoutCategory(String accoutCategory) {
		this.accoutCategory = accoutCategory;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getTlProtocolNo() {
		return tlProtocolNo;
	}

	public void setTlProtocolNo(String tlProtocolNo) {
		this.tlProtocolNo = tlProtocolNo;
	}

	public String getMchtBatchNo() {
		return mchtBatchNo;
	}

	public void setMchtBatchNo(String mchtBatchNo) {
		this.mchtBatchNo = mchtBatchNo;
	}

	public String getPaymentBusinessType() {
		return paymentBusinessType;
	}

	public void setPaymentBusinessType(String paymentBusinessType) {
		this.paymentBusinessType = paymentBusinessType;
	}

}
