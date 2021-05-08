drop table "XG_JYCY_DWD_BYXSJYQK" cascade constraints;
create table "XG_JYCY_DWD_BYXSJYQK" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "XH"        VARCHAR(100),
 "XM"        VARCHAR(100),
 "XYDM"        VARCHAR(100),
 "XYMC"        VARCHAR(100),
 "ZYDM"        VARCHAR(100),
 "ZYMC"        VARCHAR(100),
 "NJ"        VARCHAR(100),
 "XB"        VARCHAR(100),
 "BYQX"        VARCHAR(100),
 "JYFXSFZYXG"        VARCHAR(100),
 "JYJGSFMY"        VARCHAR(100),
 "XZSP"        VARCHAR(100),
 "XY"        VARCHAR(100),
 "GW"        VARCHAR(100)
);
comment on table "XG_JYCY_DWD_BYXSJYQK" is '毕业学生就业情况';
comment on column "XG_JYCY_DWD_BYXSJYQK"."TJXN" is '统计学年';
comment on column "XG_JYCY_DWD_BYXSJYQK"."TJSJ" is '统计时间';
comment on column "XG_JYCY_DWD_BYXSJYQK"."XH" is '学号';
comment on column "XG_JYCY_DWD_BYXSJYQK"."XM" is '姓名';
comment on column "XG_JYCY_DWD_BYXSJYQK"."XYDM" is '学院代码';
comment on column "XG_JYCY_DWD_BYXSJYQK"."XYMC" is '学院名称';
comment on column "XG_JYCY_DWD_BYXSJYQK"."ZYDM" is '专业代码';
comment on column "XG_JYCY_DWD_BYXSJYQK"."ZYMC" is '专业名称';
comment on column "XG_JYCY_DWD_BYXSJYQK"."NJ" is '年级';
comment on column "XG_JYCY_DWD_BYXSJYQK"."XB" is '性别';
comment on column "XG_JYCY_DWD_BYXSJYQK"."BYQX" is '毕业去向';
comment on column "XG_JYCY_DWD_BYXSJYQK"."JYFXSFZYXG" is '就业方向是否专业相关';
comment on column "XG_JYCY_DWD_BYXSJYQK"."JYJGSFMY" is '就业结果是否满意';
comment on column "XG_JYCY_DWD_BYXSJYQK"."XZSP" is '薪资水平';
comment on column "XG_JYCY_DWD_BYXSJYQK"."XY" is '行业';
comment on column "XG_JYCY_DWD_BYXSJYQK"."GW" is '岗位';

drop table "XG_JYCY_DWS_XSJYPPFX" cascade constraints;
create table "XG_JYCY_DWS_XSJYPPFX" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "XYDM"        VARCHAR(100),
 "XYMC"        VARCHAR(100),
 "ZYDM"        VARCHAR(100),
 "ZYMC"        VARCHAR(100),
 "JYL"        VARCHAR(100),
 "ZYXGD"        VARCHAR(100),
 "JYMYD"        VARCHAR(100)
);
comment on table "XG_JYCY_DWS_XSJYPPFX" is '学生就业匹配分析';
comment on column "XG_JYCY_DWS_XSJYPPFX"."TJXN" is '统计学年';
comment on column "XG_JYCY_DWS_XSJYPPFX"."TJSJ" is '统计时间';
comment on column "XG_JYCY_DWS_XSJYPPFX"."XYDM" is '学院代码';
comment on column "XG_JYCY_DWS_XSJYPPFX"."XYMC" is '学院名称';
comment on column "XG_JYCY_DWS_XSJYPPFX"."ZYDM" is '专业代码';
comment on column "XG_JYCY_DWS_XSJYPPFX"."ZYMC" is '专业名称';
comment on column "XG_JYCY_DWS_XSJYPPFX"."JYL" is '就业率';
comment on column "XG_JYCY_DWS_XSJYPPFX"."ZYXGD" is '专业相关度';
comment on column "XG_JYCY_DWS_XSJYPPFX"."JYMYD" is '就业满意度';

drop table "XG_XGDW_DWD_XSGZRYXX" cascade constraints;
create table "XG_XGDW_DWD_XSGZRYXX" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "GH"        VARCHAR(100),
 "XM"        VARCHAR(100),
 "XYDM"        VARCHAR(100),
 "XYMC"        VARCHAR(100),
 "ZYDM"        VARCHAR(100),
 "ZYMC"        VARCHAR(100),
 "XGDWLX"        VARCHAR(100),
 "XB"        VARCHAR(100),
 "NL"        VARCHAR(100),
 "ZCLX"        VARCHAR(100),
 "XWLX"        VARCHAR(100),
 "SFJZFDY"        VARCHAR(100)
);
comment on table "XG_XGDW_DWD_XSGZRYXX" is '学生工作人员信息';
comment on column "XG_XGDW_DWD_XSGZRYXX"."TJXN" is '统计学年';
comment on column "XG_XGDW_DWD_XSGZRYXX"."TJSJ" is '统计时间';
comment on column "XG_XGDW_DWD_XSGZRYXX"."GH" is '工号';
comment on column "XG_XGDW_DWD_XSGZRYXX"."XM" is '姓名';
comment on column "XG_XGDW_DWD_XSGZRYXX"."XYDM" is '学院代码';
comment on column "XG_XGDW_DWD_XSGZRYXX"."XYMC" is '学院名称';
comment on column "XG_XGDW_DWD_XSGZRYXX"."ZYDM" is '专业代码';
comment on column "XG_XGDW_DWD_XSGZRYXX"."ZYMC" is '专业名称';
comment on column "XG_XGDW_DWD_XSGZRYXX"."XGDWLX" is '学工队伍类型';
comment on column "XG_XGDW_DWD_XSGZRYXX"."XB" is '性别';
comment on column "XG_XGDW_DWD_XSGZRYXX"."NL" is '年龄';
comment on column "XG_XGDW_DWD_XSGZRYXX"."ZCLX" is '职称类型';
comment on column "XG_XGDW_DWD_XSGZRYXX"."XWLX" is '学位类型';
comment on column "XG_XGDW_DWD_XSGZRYXX"."SFJZFDY" is '是否兼职辅导员';

drop table "XG_XGDW_DWS_XSXSYFDYBL" cascade constraints;
create table "XG_XGDW_DWS_XSXSYFDYBL" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "XYDM"        VARCHAR(100),
 "XYMC"        VARCHAR(100),
 "ZYDM"        VARCHAR(100),
 "ZYMC"        VARCHAR(100),
 "XSYFDYBL"        VARCHAR(100),
 "JZFDYYQZFDYBL"        VARCHAR(100)
);
comment on table "XG_XGDW_DWS_XSXSYFDYBL" is '学生与辅导员比例';
comment on column "XG_XGDW_DWS_XSXSYFDYBL"."TJXN" is '统计学年';
comment on column "XG_XGDW_DWS_XSXSYFDYBL"."TJSJ" is '统计时间';
comment on column "XG_XGDW_DWS_XSXSYFDYBL"."XYDM" is '学院代码';
comment on column "XG_XGDW_DWS_XSXSYFDYBL"."XYMC" is '学院名称';
comment on column "XG_XGDW_DWS_XSXSYFDYBL"."ZYDM" is '专业代码';
comment on column "XG_XGDW_DWS_XSXSYFDYBL"."ZYMC" is '专业名称';
comment on column "XG_XGDW_DWS_XSXSYFDYBL"."XSYFDYBL" is '学生与辅导员比例';
comment on column "XG_XGDW_DWS_XSXSYFDYBL"."JZFDYYQZFDYBL" is '兼职辅导员与全职辅导员比例';

drop table "XG_ZSJC_DIM_GKFSX" cascade constraints;
create table "XG_ZSJC_DIM_GKFSX" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "SF"        VARCHAR(100),
 "KB"        VARCHAR(100),
 "LKX"        VARCHAR(100),
 "XX"        VARCHAR(100)
);
comment on table "XG_ZSJC_DIM_GKFSX" is '高考分数线';
comment on column "XG_ZSJC_DIM_GKFSX"."TJXN" is '统计学年';
comment on column "XG_ZSJC_DIM_GKFSX"."TJSJ" is '统计时间';
comment on column "XG_ZSJC_DIM_GKFSX"."SF" is '省份';
comment on column "XG_ZSJC_DIM_GKFSX"."KB" is '科别';
comment on column "XG_ZSJC_DIM_GKFSX"."LKX" is '录控线';
comment on column "XG_ZSJC_DIM_GKFSX"."XX" is '校线';

drop table "XG_ZSJC_DWD_XSBDXX" cascade constraints;
create table "XG_ZSJC_DWD_XSBDXX" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "ZKZH"        VARCHAR(100),
 "XM"        VARCHAR(100),
 "XB"        VARCHAR(100),
 "JG"        VARCHAR(100),
 "GKFS"        VARCHAR(100),
 "BDZYDM"        VARCHAR(100),
 "BDZYMC"        VARCHAR(100),
 "BDXYDM"        VARCHAR(100),
 "BDXYMC"        VARCHAR(100),
 "SFBD"        VARCHAR(100)
);
comment on table "XG_ZSJC_DWD_XSBDXX" is '学生报到信息';
comment on column "XG_ZSJC_DWD_XSBDXX"."TJXN" is '统计学年';
comment on column "XG_ZSJC_DWD_XSBDXX"."TJSJ" is '统计时间';
comment on column "XG_ZSJC_DWD_XSBDXX"."ZKZH" is '准考证号';
comment on column "XG_ZSJC_DWD_XSBDXX"."XM" is '姓名';
comment on column "XG_ZSJC_DWD_XSBDXX"."XB" is '性别';
comment on column "XG_ZSJC_DWD_XSBDXX"."JG" is '籍贯';
comment on column "XG_ZSJC_DWD_XSBDXX"."GKFS" is '高考分数';
comment on column "XG_ZSJC_DWD_XSBDXX"."BDZYDM" is '报到专业代码';
comment on column "XG_ZSJC_DWD_XSBDXX"."BDZYMC" is '报到专业名称';
comment on column "XG_ZSJC_DWD_XSBDXX"."BDXYDM" is '报到学院代码';
comment on column "XG_ZSJC_DWD_XSBDXX"."BDXYMC" is '报到学院名称';
comment on column "XG_ZSJC_DWD_XSBDXX"."SFBD" is '是否报到';

drop table "XG_ZSJC_DWD_XSBKXX" cascade constraints;
create table "XG_ZSJC_DWD_XSBKXX" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "ZKZH"        VARCHAR(100),
 "XM"        VARCHAR(100),
 "XB"        VARCHAR(100),
 "JG"        VARCHAR(100),
 "GKFS"        VARCHAR(100),
 "BKZYDM"        VARCHAR(100),
 "BKZYMC"        VARCHAR(100),
 "BKXYDM"        VARCHAR(100),
 "BKXYMC"        VARCHAR(100),
 "SFLQ"        VARCHAR(100)
);
comment on table "XG_ZSJC_DWD_XSBKXX" is '学生报考信息';
comment on column "XG_ZSJC_DWD_XSBKXX"."TJXN" is '统计学年';
comment on column "XG_ZSJC_DWD_XSBKXX"."TJSJ" is '统计时间';
comment on column "XG_ZSJC_DWD_XSBKXX"."ZKZH" is '准考证号';
comment on column "XG_ZSJC_DWD_XSBKXX"."XM" is '姓名';
comment on column "XG_ZSJC_DWD_XSBKXX"."XB" is '性别';
comment on column "XG_ZSJC_DWD_XSBKXX"."JG" is '籍贯';
comment on column "XG_ZSJC_DWD_XSBKXX"."GKFS" is '高考分数';
comment on column "XG_ZSJC_DWD_XSBKXX"."BKZYDM" is '报考专业代码';
comment on column "XG_ZSJC_DWD_XSBKXX"."BKZYMC" is '报考专业名称';
comment on column "XG_ZSJC_DWD_XSBKXX"."BKXYDM" is '报考学院代码';
comment on column "XG_ZSJC_DWD_XSBKXX"."BKXYMC" is '报考学院名称';
comment on column "XG_ZSJC_DWD_XSBKXX"."SFLQ" is '是否录取';

drop table "XG_ZSJC_DWD_XZXSRS" cascade constraints;
create table "XG_ZSJC_DWD_XZXSRS" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "XYDM"        VARCHAR(100),
 "XYMC"        VARCHAR(100),
 "ZYDM"        VARCHAR(100),
 "ZYMC"        VARCHAR(100),
 "XZXSRS"        VARCHAR(100)
);
comment on table "XG_ZSJC_DWD_XZXSRS" is '需招学生人数';
comment on column "XG_ZSJC_DWD_XZXSRS"."TJXN" is '统计学年';
comment on column "XG_ZSJC_DWD_XZXSRS"."TJSJ" is '统计时间';
comment on column "XG_ZSJC_DWD_XZXSRS"."XYDM" is '学院代码';
comment on column "XG_ZSJC_DWD_XZXSRS"."XYMC" is '学院名称';
comment on column "XG_ZSJC_DWD_XZXSRS"."ZYDM" is '专业代码';
comment on column "XG_ZSJC_DWD_XZXSRS"."ZYMC" is '专业名称';
comment on column "XG_ZSJC_DWD_XZXSRS"."XZXSRS" is '需招学生人数';

drop table "XG_XSJC_DWD_XSSSRZXX" cascade constraints;
create table "XG_XSJC_DWD_XSSSRZXX" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "SSMC"        VARCHAR(100),
 "SSDS"        VARCHAR(100),
 "SSQY"        VARCHAR(100),
 "SSCWH"        VARCHAR(100),
 "CWSFFPRZ"        VARCHAR(100),
 "RZRXH"        VARCHAR(100),
 "RZRXM"        VARCHAR(100),
 "RZRXB"        VARCHAR(100),
 "RZRNJ"        VARCHAR(100)
);
comment on table "XG_XSJC_DWD_XSSSRZXX" is '学生宿舍入住信息';
comment on column "XG_XSJC_DWD_XSSSRZXX"."TJXN" is '统计学年';
comment on column "XG_XSJC_DWD_XSSSRZXX"."TJSJ" is '统计时间';
comment on column "XG_XSJC_DWD_XSSSRZXX"."SSMC" is '宿舍名称';
comment on column "XG_XSJC_DWD_XSSSRZXX"."SSDS" is '宿舍栋数';
comment on column "XG_XSJC_DWD_XSSSRZXX"."SSQY" is '宿舍区域';
comment on column "XG_XSJC_DWD_XSSSRZXX"."SSCWH" is '宿舍床位号';
comment on column "XG_XSJC_DWD_XSSSRZXX"."CWSFFPRZ" is '床位是否分配入住';
comment on column "XG_XSJC_DWD_XSSSRZXX"."RZRXH" is '入住人学号';
comment on column "XG_XSJC_DWD_XSSSRZXX"."RZRXM" is '入住人姓名';
comment on column "XG_XSJC_DWD_XSSSRZXX"."RZRXB" is '入住人性别';
comment on column "XG_XSJC_DWD_XSSSRZXX"."RZRNJ" is '入住人年级';

drop table "XG_XSJC_DWD_XSTSTSXX" cascade constraints;
create table "XG_XSJC_DWD_XSTSTSXX" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "XH"        VARCHAR(100),
 "XM"        VARCHAR(100),
 "XB"        VARCHAR(100),
 "NJ"        VARCHAR(100),
 "SFDS"        VARCHAR(100),
 "DSSJ"        VARCHAR(100),
 "DSXQ"        VARCHAR(100),
 "SFTS"        VARCHAR(100),
 "TSSJ"        VARCHAR(100),
 "TSXQ"        VARCHAR(100)
);
comment on table "XG_XSJC_DWD_XSTSTSXX" is '学生调宿退宿信息';
comment on column "XG_XSJC_DWD_XSTSTSXX"."TJXN" is '统计学年';
comment on column "XG_XSJC_DWD_XSTSTSXX"."TJSJ" is '统计时间';
comment on column "XG_XSJC_DWD_XSTSTSXX"."XH" is '学号';
comment on column "XG_XSJC_DWD_XSTSTSXX"."XM" is '姓名';
comment on column "XG_XSJC_DWD_XSTSTSXX"."XB" is '性别';
comment on column "XG_XSJC_DWD_XSTSTSXX"."NJ" is '年级';
comment on column "XG_XSJC_DWD_XSTSTSXX"."SFDS" is '是否调宿';
comment on column "XG_XSJC_DWD_XSTSTSXX"."DSSJ" is '调宿时间';
comment on column "XG_XSJC_DWD_XSTSTSXX"."DSXQ" is '调宿详情';
comment on column "XG_XSJC_DWD_XSTSTSXX"."SFTS" is '是否退宿';
comment on column "XG_XSJC_DWD_XSTSTSXX"."TSSJ" is '退宿时间';
comment on column "XG_XSJC_DWD_XSTSTSXX"."TSXQ" is '退宿详情';

drop table "XG_XSJC_DWD_XSWGXX" cascade constraints;
create table "XG_XSJC_DWD_XSWGXX" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "XH"        VARCHAR(100),
 "XM"        VARCHAR(100),
 "XB"        VARCHAR(100),
 "NJ"        VARCHAR(100),
 "WGSJ"        VARCHAR(100)
);
comment on table "XG_XSJC_DWD_XSWGXX" is '学生晚归信息';
comment on column "XG_XSJC_DWD_XSWGXX"."TJXN" is '统计学年';
comment on column "XG_XSJC_DWD_XSWGXX"."TJSJ" is '统计时间';
comment on column "XG_XSJC_DWD_XSWGXX"."XH" is '学号';
comment on column "XG_XSJC_DWD_XSWGXX"."XM" is '姓名';
comment on column "XG_XSJC_DWD_XSWGXX"."XB" is '性别';
comment on column "XG_XSJC_DWD_XSWGXX"."NJ" is '年级';
comment on column "XG_XSJC_DWD_XSWGXX"."WGSJ" is '晚归时间';

drop table "XG_XSJC_DWD_XSQJXX" cascade constraints;
create table "XG_XSJC_DWD_XSQJXX" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "XH"        VARCHAR(100),
 "XM"        VARCHAR(100),
 "XB"        VARCHAR(100),
 "NJ"        VARCHAR(100),
 "XYDM"        VARCHAR(100),
 "XYMC"        VARCHAR(100),
 "ZYDM"        VARCHAR(100),
 "ZYMC"        VARCHAR(100),
 "QJSJ"        VARCHAR(100),
 "XJSJ"        VARCHAR(100),
 "QJLX"        VARCHAR(100)
);
comment on table "XG_XSJC_DWD_XSQJXX" is '学生请假信息';
comment on column "XG_XSJC_DWD_XSQJXX"."TJXN" is '统计学年';
comment on column "XG_XSJC_DWD_XSQJXX"."TJSJ" is '统计时间';
comment on column "XG_XSJC_DWD_XSQJXX"."XH" is '学号';
comment on column "XG_XSJC_DWD_XSQJXX"."XM" is '姓名';
comment on column "XG_XSJC_DWD_XSQJXX"."XB" is '性别';
comment on column "XG_XSJC_DWD_XSQJXX"."NJ" is '年级';
comment on column "XG_XSJC_DWD_XSQJXX"."XYDM" is '学院代码';
comment on column "XG_XSJC_DWD_XSQJXX"."XYMC" is '学院名称';
comment on column "XG_XSJC_DWD_XSQJXX"."ZYDM" is '专业代码';
comment on column "XG_XSJC_DWD_XSQJXX"."ZYMC" is '专业名称';
comment on column "XG_XSJC_DWD_XSQJXX"."QJSJ" is '请假时间';
comment on column "XG_XSJC_DWD_XSQJXX"."XJSJ" is '销假时间';
comment on column "XG_XSJC_DWD_XSQJXX"."QJLX" is '请假类型';

drop table "XG_XSJC_DWS_XSQJTJ" cascade constraints;
create table "XG_XSJC_DWS_XSQJTJ" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "XYDM"        VARCHAR(100),
 "XYMC"        VARCHAR(100),
 "ZYDM"        VARCHAR(100),
 "ZYMC"        VARCHAR(100),
 "QJRS"        VARCHAR(100)
);
comment on table "XG_XSJC_DWS_XSQJTJ" is '学生请假统计';
comment on column "XG_XSJC_DWS_XSQJTJ"."TJXN" is '统计学年';
comment on column "XG_XSJC_DWS_XSQJTJ"."TJSJ" is '统计时间';
comment on column "XG_XSJC_DWS_XSQJTJ"."XYDM" is '学院代码';
comment on column "XG_XSJC_DWS_XSQJTJ"."XYMC" is '学院名称';
comment on column "XG_XSJC_DWS_XSQJTJ"."ZYDM" is '专业代码';
comment on column "XG_XSJC_DWS_XSQJTJ"."ZYMC" is '专业名称';
comment on column "XG_XSJC_DWS_XSQJTJ"."QJRS" is '请假人数';

drop table "XG_XSJC_DWS_XSQJLXTJ" cascade constraints;
create table "XG_XSJC_DWS_XSQJLXTJ" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "XYDM"        VARCHAR(100),
 "XYMC"        VARCHAR(100),
 "ZYDM"        VARCHAR(100),
 "ZYMC"        VARCHAR(100),
 "QJLX"        VARCHAR(100),
 "QJSL"        VARCHAR(100)
);
comment on table "XG_XSJC_DWS_XSQJLXTJ" is '学生请假类型统计';
comment on column "XG_XSJC_DWS_XSQJLXTJ"."TJXN" is '统计学年';
comment on column "XG_XSJC_DWS_XSQJLXTJ"."TJSJ" is '统计时间';
comment on column "XG_XSJC_DWS_XSQJLXTJ"."XYDM" is '学院代码';
comment on column "XG_XSJC_DWS_XSQJLXTJ"."XYMC" is '学院名称';
comment on column "XG_XSJC_DWS_XSQJLXTJ"."ZYDM" is '专业代码';
comment on column "XG_XSJC_DWS_XSQJLXTJ"."ZYMC" is '专业名称';
comment on column "XG_XSJC_DWS_XSQJLXTJ"."QJLX" is '请假类型';
comment on column "XG_XSJC_DWS_XSQJLXTJ"."QJSL" is '请假数量';

drop table "XG_XSJC_DWS_XSFBQKTJ" cascade constraints;
create table "XG_XSJC_DWS_XSFBQKTJ" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "XYDM"        VARCHAR(100),
 "XYMC"        VARCHAR(100),
 "ZYDM"        VARCHAR(100),
 "ZYMC"        VARCHAR(100),
 "XSNJ"        VARCHAR(100),
 "XSXB"        VARCHAR(100),
 "XSRS"        VARCHAR(100)
);
comment on table "XG_XSJC_DWS_XSFBQKTJ" is '学生分布情况统计';
comment on column "XG_XSJC_DWS_XSFBQKTJ"."TJXN" is '统计学年';
comment on column "XG_XSJC_DWS_XSFBQKTJ"."TJSJ" is '统计时间';
comment on column "XG_XSJC_DWS_XSFBQKTJ"."XYDM" is '学院代码';
comment on column "XG_XSJC_DWS_XSFBQKTJ"."XYMC" is '学院名称';
comment on column "XG_XSJC_DWS_XSFBQKTJ"."ZYDM" is '专业代码';
comment on column "XG_XSJC_DWS_XSFBQKTJ"."ZYMC" is '专业名称';
comment on column "XG_XSJC_DWS_XSFBQKTJ"."XSNJ" is '学生年级';
comment on column "XG_XSJC_DWS_XSFBQKTJ"."XSXB" is '学生性别';
comment on column "XG_XSJC_DWS_XSFBQKTJ"."XSRS" is '学生人数';

drop table "XG_XSJC_DWD_XJYDQK" cascade constraints;
create table "XG_XSJC_DWD_XJYDQK" 
( "TJXN"        VARCHAR(100),
 "TJSJ"        VARCHAR(100),
 "XYDM"        VARCHAR(100),
 "XYMC"        VARCHAR(100),
 "ZYDM"        VARCHAR(100),
 "ZYMC"        VARCHAR(100),
 "XH"        VARCHAR(100),
 "XM"        VARCHAR(100),
 "XB"        VARCHAR(100),
 "NJ"        VARCHAR(100),
 "YDLX"        VARCHAR(100),
 "YDXQ"        VARCHAR(100)
);
comment on table "XG_XSJC_DWD_XJYDQK" is '学籍异动详情';
comment on column "XG_XSJC_DWD_XJYDQK"."TJXN" is '统计学年';
comment on column "XG_XSJC_DWD_XJYDQK"."TJSJ" is '统计时间';
comment on column "XG_XSJC_DWD_XJYDQK"."XYDM" is '学院代码';
comment on column "XG_XSJC_DWD_XJYDQK"."XYMC" is '学院名称';
comment on column "XG_XSJC_DWD_XJYDQK"."ZYDM" is '专业代码';
comment on column "XG_XSJC_DWD_XJYDQK"."ZYMC" is '专业名称';
comment on column "XG_XSJC_DWD_XJYDQK"."XH" is '学号';
comment on column "XG_XSJC_DWD_XJYDQK"."XM" is '姓名';
comment on column "XG_XSJC_DWD_XJYDQK"."XB" is '性别';
comment on column "XG_XSJC_DWD_XJYDQK"."NJ" is '年级';
comment on column "XG_XSJC_DWD_XJYDQK"."YDLX" is '异动类型';
comment on column "XG_XSJC_DWD_XJYDQK"."YDXQ" is '异动详情';

