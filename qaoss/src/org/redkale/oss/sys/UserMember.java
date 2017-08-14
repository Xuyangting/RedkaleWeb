/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.redkale.oss.sys;

import java.security.*;
import javax.persistence.*;
import org.redkale.convert.*;
import org.redkale.oss.base.*;
import static org.redkale.oss.base.MemberInfo.STATUS_NORMAL;
import org.redkale.util.*;

/**
 * <p>
 * CREATE TABLE `sys_usermember` (
 * `memberid` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
 * `account` varchar(64) NOT NULL DEFAULT '' COMMENT '账号',
 * `membername` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称，通常为员工姓名',
 * `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
 * `type` smallint(5) NOT NULL DEFAULT '0' COMMENT '类型；8192为管理员；1为普通员工；其他类型值需要按位移值来定义:2/4/8/16/32',
 * `status` smallint(5) NOT NULL DEFAULT '0' COMMENT '状态: 10:正常;20:待审批;40:冻结;50：隐藏;60:过期;70:关闭;80:删除;',
 * `mobile` varchar(32) NOT NULL DEFAULT '' COMMENT '手机号码',
 * `email` varchar(128) NOT NULL DEFAULT '' COMMENT '邮箱地址',
 * `weixin` varchar(128) NOT NULL DEFAULT '' COMMENT '微信账号',
 * `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
 * `createtime` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
 * `updatetime` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
 * PRIMARY KEY (`memberid`),
 * UNIQUE KEY `singel` (`account`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=utf8;
 *
 * @author zhangjx
 */
@Entity
@Cacheable
@Table(name = "sys_usermember")
public class UserMember extends BaseEntity {

    private static final MessageDigest md5;

    static {
        MessageDigest d = null;
        try {
            d = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        md5 = d;
    }

    @Id
    @GeneratedValue
    private int memberid;

    @Column(updatable = false)
    private String account = "";

    private String membername = "";

    @ConvertColumn(ignore = true, type = ConvertType.JSON)
    private String password = "";

    @Column(updatable = false)
    private short type;

    @Column(updatable = false)
    private short status = STATUS_NORMAL;

    private String mobile = "";

    private String email = "";

    private String weixin = "";

    @Column(updatable = false)
    private long createtime;

    private long updatetime;

    private String remark = "";

    @Transient
    private int[] roleids;

    public MemberInfo createMemberInfo() {
        MemberInfo info = new MemberInfo();
        info.setAccount(this.account);
        info.setMembername(this.membername);
        info.setPassword(this.password);
        info.setStatus(this.status);
        info.setType(this.type);
        info.setMemberid(this.memberid);
        return info;
    }

    public String passwordForMD5() {
        return md5IfNeed(this.password);
    }

    public static String md5IfNeed(String password) {
        if (password == null || password.trim().isEmpty()) return "";
        if (password.length() == 32) return password; //已经MD5了
        byte[] bytes = password.trim().getBytes();
        synchronized (md5) {
            bytes = md5.digest(bytes);
        }
        return new String(Utility.binToHex(bytes));
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(long updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int[] getRoleids() {
        return roleids;
    }

    public void setRoleids(int[] roleids) {
        this.roleids = roleids;
    }

}
