
<%@ page language="java" pageEncoding="GBK"%>
<html>
<body>
<form action="https://www.yeepay.com/app-merchant-proxy/node" method="POST">
    <table align="center" width="600" border="6" cellspacing="0" cellpadding="2">
        <tr>
            <td align="center" colspan="4" bgcolor="#FFD2E9">
                <b>订单号：</b><input type="text" name="orderID">
                <b>应付金额：￥</b><input type="text" name="amount" size="6"><b>元</b>
            </td>
        </tr>
        <tr>
            <td colspan="4"> </td>
        </tr>
        <tr>
            <td colspan="4" bgcolor="#C0C0C0">请选择在线支付银行</td>
        </tr>
        <tr>
            <td height="25" width="24%"><input type="radio" name="pd_FrpId" value="ICBC-NET">工商银行</td>
            <td height="25" width="24%"><input type="radio" name="pd_FrpId" value="CMBCHINA-NET">招商银行</td>
            <td height="25" width="24%"><input type="radio" name="pd_FrpId" value="ABC-NET">农业银行</td>
            <td height="25" width="28%"><input type="radio" name="pd_FrpId" value="CCB-NET">建设银行</td>
        </tr>
        <tr>
            <td height="25"><input type="radio" name="pd_FrpId" value="CEB-NET" >光大银行</td>
            <td height="25"><input type="radio" name="pd_FrpId" value="BOCO-NET">交通银行</td>
            <td height="25"><input type="radio" name="pd_FrpId" value="CMBC-NET">民生银行</td>
            <td height="25"><input type="radio" name="pd_FrpId" value="SDB-NET">深圳发展银行</td>
        </tr>
        <tr>
            <td height="25"><input type="radio" name="pd_FrpId" value="BCCB-NET">北京银行</td>
            <td height="25"><input type="radio" name="pd_FrpId" value="CIB-NET">兴业银行</td>
            <td height="25"><input type="radio" name="pd_FrpId" value="ECITIC-NET">中信银行</td>
            <td height="25"><input type="radio" name="pd_FrpId" value="SPDB-NET">浦东发展银行</td>
        </tr>
        <tr>
            <td colspan="4"> </td>
        </tr>
        <tr>
            <td colspan="4" align="center" bgcolor="#FFDAB5">
                <input type="submit" value="  确认支付  "/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
