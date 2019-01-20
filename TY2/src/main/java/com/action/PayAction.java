package com.action;

import com.opensymphony.xwork2.ActionSupport;
import com.util.PaymentUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("payAction")
@Scope("prototype")
public class PayAction extends ActionSupport {

    private  String orderID;
    private String amount;
    private String pd_FrpId;

    String businessType = "Buy";  //业务类型。Buy为在线支付
    String currency = "CNY";      //交易币种。CNY为人民币
    String productDesc = "";      //商品描述
    String productCategory = "";  //商品种类
    String productID = "";        //商品ID
    String addressFlag = "0";     //送货地址。0为不需要，1为需要
    String accountMoreInfo = "";  //商户扩展信息
    String pr_NeedResponse = "0"; //应答机制

    //测试商户：商户编号
    String accountID = "10000432521";
    //测试商户：密钥
    String keyValue = "8UPp0KE8sq73zVP370vko7C39403rtK1YwX40Td6irH216036H27Eb12792t";
    //测试商户：商户接收支付成功数据的地址
    String accountCallbackURL = "http://127.0.0.1:8088/PaymentResultServlet";
    public void setPd_FrpId(String pd_FrpId) {
        this.pd_FrpId = pd_FrpId;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    String md5hmac = PaymentUtil.buildHmac(
            businessType, accountID, orderID, amount, currency, productID, productCategory,
            productDesc, accountCallbackURL, addressFlag, accountMoreInfo, pd_FrpId,
            pr_NeedResponse, keyValue);






    public String enterPayPage(){
        return SUCCESS;
    }

    public String dealPay(){
        //收集信息
        return SUCCESS;
    }
}
