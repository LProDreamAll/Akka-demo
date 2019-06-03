package fakepanshi.service.Impl;

import fakepanshi.service.BaiChuanService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2019-2019
 * FileName: BaiChuanServiceImpl
 * Author:   s·D·bs
 * Date:     2019/6/3 16:18
 * Description:
 * Motto: 0.45%
 */

@Service
public class BaiChuanServiceImpl implements BaiChuanService {

    @Override
    public String getOne() {
        return "{\n" +
                "  \"trackInfoList\": [\n" +
                "    {}\n" +
                "  ],\n" +
                "  \"beneficiaryPersonList\": [\n" +
                "    {\n" +
                "      \"idCard\": \"520181199101192117\",\n" +
                "      \"idCardType\": 0,\n" +
                "      \"legal\": false,\n" +
                "      \"misc\": {},\n" +
                "      \"name\": \"大多数\",\n" +
                "      \"sex\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"carOwnerInfo\": {\n" +
                "    \"address\": \"\",\n" +
                "    \"idCard\": \"520181199101192117\",\n" +
                "    \"idCardType\": 0,\n" +
                "    \"misc\": {},\n" +
                "    \"name\": \"大多数\",\n" +
                "    \"sex\": 0\n" +
                "  },\n" +
                "  \"agentInfo\": {\n" +
                "    \"certNumber\": \"\",\n" +
                "    \"email\": \"\",\n" +
                "    \"idCard\": \"2016063003\",\n" +
                "    \"idCardType\": 1,\n" +
                "    \"misc\": {},\n" +
                "    \"mobile\": \"13800138303\",\n" +
                "    \"name\": \"安代通-东莞\",\n" +
                "    \"platformCode\": \"1244000000\",\n" +
                "    \"platformName\": \"广东南枫平台\",\n" +
                "    \"sex\": 1,\n" +
                "    \"teamCode\": \"1244194269\",\n" +
                "    \"teamName\": \"东莞本部保网团队\",\n" +
                "    \"workerID\": \"620562419\"\n" +
                "  },\n" +
                "  \"insComId\": \"2011\",\n" +
                "  \"insuredPersonInfoList\": [\n" +
                "    {\n" +
                "      \"address\": \"\",\n" +
                "      \"idCard\": \"520181199101192117\",\n" +
                "      \"idCardType\": 0,\n" +
                "      \"misc\": {},\n" +
                "      \"name\": \"大多数\",\n" +
                "      \"sex\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"businessId\": \"42079096@20114419\",\n" +
                "  \"subAgentInfo\": {\n" +
                "    \"email\": \"\",\n" +
                "    \"idCard\": \"2016063003\",\n" +
                "    \"idCardType\": 1,\n" +
                "    \"misc\": {},\n" +
                "    \"mobile\": \"13800138303\",\n" +
                "    \"name\": \"安代通-东莞\",\n" +
                "    \"official\": false,\n" +
                "    \"phone\": \"13800138303\",\n" +
                "    \"sex\": 1\n" +
                "  },\n" +
                "  \"insArea\": {\n" +
                "    \"province\": \"440000\",\n" +
                "    \"city\": \"441900\"\n" +
                "  },\n" +
                "  \"singlesite\": \"1244194001\",\n" +
                "  \"providerInfoList\": [\n" +
                "    {\n" +
                "      \"comId\": \"20114419\",\n" +
                "      \"fullName\": \"中国太平洋财产保险股份有限公司东莞分公司\",\n" +
                "      \"id\": \"20114419\",\n" +
                "      \"nickName\": \"太平洋财险东莞分公司\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"orgCode\": \"1244200000\",\n" +
                "  \"specialRisk\": {\n" +
                "    \"suites\": []\n" +
                "  },\n" +
                "  \"deliverInfo\": {\n" +
                "    \"address\": \"广东省广州市市辖区东莞泛华时代本部财险集团直营业务部\",\n" +
                "    \"area\": \"440101\",\n" +
                "    \"city\": \"440100\",\n" +
                "    \"deliveType\": 0,\n" +
                "    \"freightCollect\": false,\n" +
                "    \"insureCoDelive\": true,\n" +
                "    \"invoice\": false,\n" +
                "    \"invoiceTitle\": \"\",\n" +
                "    \"isFreightCollect\": false,\n" +
                "    \"isInsureCoDelive\": true,\n" +
                "    \"isInvoice\": false,\n" +
                "    \"misc\": {},\n" +
                "    \"name\": \"安代通-东莞\",\n" +
                "    \"phone\": \"13800138303\",\n" +
                "    \"postCode\": \"10100\",\n" +
                "    \"province\": \"440000\",\n" +
                "    \"receiveDayType\": 2,\n" +
                "    \"receiveTimeType\": 3,\n" +
                "    \"remark\": \"\"\n" +
                "  },\n" +
                "  \"isrenewal\": \"false\",\n" +
                "  \"applicantPersonInfo\": {\n" +
                "    \"address\": \"\",\n" +
                "    \"idCard\": \"520181199101192117\",\n" +
                "    \"idCardType\": 0,\n" +
                "    \"misc\": {},\n" +
                "    \"name\": \"大多数\",\n" +
                "    \"sex\": 0\n" +
                "  },\n" +
                "  \"configInfo\": {\n" +
                "    \"configMap\": {\n" +
                "      \"needSerchSaler\": \"true\",\n" +
                "      \"stCertificateValidity\": \"2099-12-29\",\n" +
                "      \"vehicleInspection\": \"01\",\n" +
                "      \"default_taxBureauName\": \"东莞市地方税务局南城税务分局\",\n" +
                "      \"login\": \"w_C91064\",\n" +
                "      \"ISCREATEEINVOICE4OrgInsure\": \"false\",\n" +
                "      \"check4pay\": \"true\",\n" +
                "      \"paymentType\": \"2\",\n" +
                "      \"inscomcode\": \"2011\",\n" +
                "      \"repairFactorRate\": \"0.15\",\n" +
                "      \"defaultEmail\": \"1346151432@qq.com\",\n" +
                "      \"needISCREATEEINVOICE\": \"true\",\n" +
                "      \"partner_code\": \"4S635532\",\n" +
                "      \"agent_code\": \"01535\",\n" +
                "      \"defaultAdress\": \"广东省东莞市\",\n" +
                "      \"salerInfo\": \"徐嘉萍\",\n" +
                "      \"globalType\": \"A\",\n" +
                "      \"keepSessionEndHour\": \"23\",\n" +
                "      \"expectSuccessFlag\": \"代理点、终端及经办人选择\",\n" +
                "      \"maxDif\": \"90\",\n" +
                "      \"registryNumber\": \"91441900981970935R\",\n" +
                "      \"keepSessionUrl\": \"http://issue.cpic.com.cn/ecar/view/portal/page/common/partnerselect.html\",\n" +
                "      \"areaComCode\": \"北京\",\n" +
                "      \"keepSessionStartHour\": \"0\",\n" +
                "      \"pwd\": \"QAZqaz123\",\n" +
                "      \"machineCode\": \"05-03-20150316-0003065717\",\n" +
                "      \"cooperant\": \"02\",\n" +
                "      \"defaultAddress\": \"广东省东莞市\",\n" +
                "      \"loginTemplateName\": \"robot-2011-quote-login\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"carInfo\": {\n" +
                "    \"analogyPrice\": 106000,\n" +
                "    \"carBrandName\": \"海南马自达\",\n" +
                "    \"carModelMisc\": {},\n" +
                "    \"carModelName\": \"海马CA7130轿车\",\n" +
                "    \"carPriceType\": \"0\",\n" +
                "    \"carUserType\": 0,\n" +
                "    \"carVehicleOrigin\": 1,\n" +
                "    \"carVehicularApplications\": 0,\n" +
                "    \"displacement\": 1.342,\n" +
                "    \"driverArea\": 0,\n" +
                "    \"engineNum\": \"555555\",\n" +
                "    \"familyName\": \"海马323\",\n" +
                "    \"firstRegDate\": \"2014-07-26 00:00:00\",\n" +
                "    \"fullLoad\": 1020,\n" +
                "    \"isFieldCar\": false,\n" +
                "    \"isJyMatch\": false,\n" +
                "    \"isLoanManyYearsFlag\": false,\n" +
                "    \"isLoansCar\": false,\n" +
                "    \"isRbMatch\": false,\n" +
                "    \"isTransfer\": false,\n" +
                "    \"jgVehicleType\": 13,\n" +
                "    \"jyCode\": \"MZAAAD0001\",\n" +
                "    \"listedYear\": \"2000\",\n" +
                "    \"loanManyYearsFlag\": false,\n" +
                "    \"modelLoad\": 0,\n" +
                "    \"noLicenseFlag\": false,\n" +
                "    \"plateColor\": 1,\n" +
                "    \"plateNum\": \"粤S33333\",\n" +
                "    \"plateType\": 1,\n" +
                "    \"price\": 106000,\n" +
                "    \"rbCode\": \"MZAAAD0001\",\n" +
                "    \"seatCnt\": 5,\n" +
                "    \"syvehicletypecode\": \"KA\",\n" +
                "    \"syvehicletypename\": \"六座以下客车\",\n" +
                "    \"taxAnalogyPrice\": 0,\n" +
                "    \"taxPrice\": 115060,\n" +
                "    \"useProps\": 1,\n" +
                "    \"vin\": \"55555555555525555\"\n" +
                "  },\n" +
                "  \"sq\": {\n" +
                "    \"bizCharge\": 7925.77,\n" +
                "    \"bizProposeNum\": \"ABEJ910Y1419F445046V\",\n" +
                "    \"bussDiscountRate\": 1,\n" +
                "    \"bwcommercialclaimtimes\": \"去年发生一次理赔\",\n" +
                "    \"bwcompulsoryclaimtimes\": \"新保或上年发生一次有责任不涉及死亡理赔\",\n" +
                "    \"compulsoryclaimrate\": 1,\n" +
                "    \"compulsoryclaimratereasons\": \"新保或上年发生一次有责任不涉及死亡理赔\",\n" +
                "    \"discountRates\": {\n" +
                "      \"geniusItem.policyDiscount\": 1\n" +
                "    },\n" +
                "    \"efcCharge\": 2220,\n" +
                "    \"jqclaimrate\": 0,\n" +
                "    \"jqclaims\": \"[{\\\"caseendtime\\\":\\\"2018-08-25 02:44:00\\\",\\\"casestarttime\\\":\\\"2018-08-16 08:28:00\\\",\\\"claimamount\\\":0,\\\"createtime\\\":{\\\"date\\\":6,\\\"day\\\":1,\\\"hours\\\":15,\\\"minutes\\\":47,\\\"month\\\":4,\\\"seconds\\\":16,\\\"time\\\":1557128836000,\\\"timezoneOffset\\\":-480,\\\"year\\\":119},\\\"id\\\":\\\"2b66c9d06fd311e9616d673d92d4c3f0\\\",\\\"inscorpcode\\\":\\\"2005\\\",\\\"inscorpid\\\":\\\"\\\",\\\"inscorpname\\\":\\\"中国人民财产保险股份有限公司\\\",\\\"modifytime\\\":{\\\"date\\\":6,\\\"day\\\":1,\\\"hours\\\":15,\\\"minutes\\\":47,\\\"month\\\":4,\\\"seconds\\\":16,\\\"time\\\":1557128836000,\\\"timezoneOffset\\\":-480,\\\"year\\\":119},\\\"noti\\\":\\\"\\\",\\\"operator\\\":\\\"guizequery\\\",\\\"policyid\\\":\\\"\\\",\\\"policyno\\\":\\\"\\\",\\\"risktype\\\":\\\"1\\\",\\\"taskid\\\":\\\"56381463\\\"}]\",\n" +
                "    \"jqclaimtimes\": 1,\n" +
                "    \"jqenddate\": \"2019-05-25 00:00:00\",\n" +
                "    \"jqpolicyno\": \"PDZA201844070000085671\",\n" +
                "    \"jqstartdate\": \"2018-05-25 00:00:00\",\n" +
                "    \"noclaimdiscountcoefficient\": 1,\n" +
                "    \"noclaimdiscountcoefficientreasons\": \"新保或上年发生1次赔款\",\n" +
                "    \"supplierid\": \"2005\",\n" +
                "    \"syclaimrate\": 0,\n" +
                "    \"syclaims\": \"[{\\\"caseendtime\\\":\\\"2018-08-25 02:44:00\\\",\\\"casestarttime\\\":\\\"2018-08-16 08:28:00\\\",\\\"claimamount\\\":2588.4,\\\"createtime\\\":{\\\"date\\\":6,\\\"day\\\":1,\\\"hours\\\":15,\\\"minutes\\\":47,\\\"month\\\":4,\\\"seconds\\\":16,\\\"time\\\":1557128836000,\\\"timezoneOffset\\\":-480,\\\"year\\\":119},\\\"id\\\":\\\"2b66a2c06fd311e9616d673d92d4c3f0\\\",\\\"inscorpcode\\\":\\\"2005\\\",\\\"inscorpid\\\":\\\"\\\",\\\"inscorpname\\\":\\\"中国人民财产保险股份有限公司\\\",\\\"modifytime\\\":{\\\"date\\\":6,\\\"day\\\":1,\\\"hours\\\":15,\\\"minutes\\\":47,\\\"month\\\":4,\\\"seconds\\\":16,\\\"time\\\":1557128836000,\\\"timezoneOffset\\\":-480,\\\"year\\\":119},\\\"noti\\\":\\\"\\\",\\\"operator\\\":\\\"guizequery\\\",\\\"policyid\\\":\\\"PDAA201844070000078663\\\",\\\"policyno\\\":\\\"\\\",\\\"risktype\\\":\\\"0\\\",\\\"taskid\\\":\\\"56381463\\\"}]\",\n" +
                "    \"syclaimtimes\": 1,\n" +
                "    \"syenddate\": \"2019-06-03 00:00:00\",\n" +
                "    \"sypolicyno\": \"PDAA201844070000078663\",\n" +
                "    \"systartdate\": \"2018-06-03 00:00:00\",\n" +
                "    \"taxCharge\": 197.12,\n" +
                "    \"totalCharge\": 10342.89,\n" +
                "    \"trafficDiscountRate\": 1\n" +
                "  },\n" +
                "  \"carPriceType\": \"不含税类比价\",\n" +
                "  \"driverPersonList\": [],\n" +
                "  \"baseSuiteInfo\": {\n" +
                "    \"bizSuiteInfo\": {\n" +
                "      \"end\": \"2019-05-22 23:59:59\",\n" +
                "      \"start\": \"2018-05-23 00:00:00\",\n" +
                "      \"suites\": [\n" +
                "        {\n" +
                "          \"amount\": 1,\n" +
                "          \"code\": \"NcfVehicleDemageIns\",\n" +
                "          \"name\": \"机动车损失不计免赔险\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"amount\": 106000,\n" +
                "          \"code\": \"VehicleDemageIns\",\n" +
                "          \"name\": \"机动车损失保险\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"amount\": 1,\n" +
                "          \"code\": \"NcfThirdPartyIns\",\n" +
                "          \"name\": \"机动车第三者责任不计免赔保险\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"amount\": 500000,\n" +
                "          \"code\": \"ThirdPartyIns\",\n" +
                "          \"name\": \"机动车第三者责任保险\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"amount\": 1,\n" +
                "          \"code\": \"NcfDriverIns\",\n" +
                "          \"name\": \"司机责任不计免赔险\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"amount\": 10000,\n" +
                "          \"code\": \"DriverIns\",\n" +
                "          \"name\": \"司机责任险\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"amount\": 1,\n" +
                "          \"code\": \"NcfPassengerIns\",\n" +
                "          \"name\": \"乘客责任不计免赔险\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"amount\": 10000,\n" +
                "          \"code\": \"PassengerIns\",\n" +
                "          \"name\": \"乘客责任险\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"efcSuiteInfo\": {\n" +
                "      \"amount\": 1,\n" +
                "      \"end\": \"2019-05-22 23:59:59\",\n" +
                "      \"start\": \"2018-05-23 00:00:00\"\n" +
                "    },\n" +
                "    \"taxSuiteInfo\": {}\n" +
                "  },\n" +
                "  \"supplyParam\": [],\n" +
                "  \"hadRemarked\": 0,\n" +
                "  \"buybusitype\": \"01\",\n" +
                "  \"renewalquoteitem\": [],\n" +
                "  \"name\": \"吴群\",\n" +
                "  \"idcardnum\": \"110105196411087711\",\n" +
                "  \"address\": \"北京市东城区大取灯胡同2号\",\n" +
                "  \"telephone\": \"15899207392\",\n" +
                "  \"gender\": \"0\",\n" +
                "  \"authority\": \"北京市公安局东城分局\",\n" +
                "  \"nation\": \"汉\",\n" +
                "  \"expstartdate\": \"2019-05-05\",\n" +
                "  \"expenddate\": \"2029-05-05\",\n" +
                "  \"pinCode\":\"TR1C8H\"\n" +
                "}\n";
    }
}
