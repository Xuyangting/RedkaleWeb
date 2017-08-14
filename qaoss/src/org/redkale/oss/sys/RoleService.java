/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.redkale.oss.sys;

import java.lang.reflect.*;
import java.util.*;
import javax.annotation.Resource;
import org.redkale.net.http.RestService;
import org.redkale.oss.base.Services;
import org.redkale.oss.base.Services.ActionName;
import org.redkale.oss.base.Services.ModuleName;
import org.redkale.oss.base.MemberInfo;
import org.redkale.source.FilterBean;
import org.redkale.source.FilterExpress;
import org.redkale.source.FilterNode;
import org.redkale.source.Flipper;
import org.redkale.util.AnyValue;
import org.redkale.util.Sheet;

/**
 *
 * @author zhangjx
 */
@RestService(ignore = true)
public class RoleService extends BaseService {

    @Resource
    private UserMemberService userService;

    public RoleInfo findRoleInfo(int roleid) {
        return source.find(RoleInfo.class, roleid);
    }

    public int createRoleInfo(MemberInfo admin, RoleInfo info) {
        if (admin != null) {
            info.setCreatetime(System.currentTimeMillis());
            info.setCreator(admin.getMembername());
        }
        source.insert(info);
        return 1;
    }

    public void updateRoleInfo(RoleInfo info) {
        source.update(info);
    }

    public Sheet<RoleInfo> queryRoleInfo(Flipper flipper, FilterBean bean) {
        return source.querySheet(RoleInfo.class, flipper, bean);
    }

    private static final List<ModuleInfo> moduleinfos = new ArrayList<>();

    private static final List<ActionInfo> actioninfos = new ArrayList<>();

    static {
        for (Field field : Services.class.getFields()) {
            if (!Modifier.isStatic(field.getModifiers())) continue;
            int value;
            try {
                value = field.getInt(null);
            } catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
            ModuleName m = field.getAnnotation(ModuleName.class);
            if (m != null) {
                moduleinfos.add(new ModuleInfo(value, m.value()));
                continue;
            }
            ActionName a = field.getAnnotation(ActionName.class);
            if (a != null) {
                actioninfos.add(new ActionInfo(value, a.value()));
            }
        }

    }

    @Override
    public void init(AnyValue conf) {
        moduleinfos.addAll(source.queryList(ModuleInfo.class, (FilterNode) null));
        actioninfos.addAll(source.queryList(ActionInfo.class, (FilterNode) null));
    }

    public List<ModuleInfo> queryModuleInfo() {
        return moduleinfos;
    }

    public List<ActionInfo> queryActionInfo() {
        return actioninfos;
    }

    public int createUserToRole(MemberInfo admin, UserToRole info) {
        if (admin != null) {
            info.setCreatetime(System.currentTimeMillis());
            info.setCreator(admin.getMembername());
        }
        source.insert(info);
        //userService.updateCache(info.getMemberid());
        return 0;
    }

    public void deleteUserToRole(int seqid) {
        UserToRole utr = source.find(UserToRole.class, seqid);
        if (utr == null) return;
        source.delete(utr);
        //userService.updateCache(utr.getMemberid());
    }

    public Sheet<UserToRole> queryUserToRole(Flipper flipper, FilterBean bean) {
        return source.querySheet(UserToRole.class, flipper, bean);
    }

    public int[] updateUserToRole(MemberInfo admin, int delmemberid, int[] delroleids, UserToRole... infos) {
        final boolean deled = delmemberid > 0 && delroleids != null && delroleids.length > 0;
        if (deled) source.delete(UserToRole.class, FilterNode.create("roleid", FilterExpress.IN, delroleids).and("memberid", delmemberid));
        if (infos.length == 0) return new int[0];
        if (admin != null) {
            long now = System.currentTimeMillis();
            for (UserToRole info : infos) {
                if (info.getMemberid() < 1 || info.getRoleid() < 1) throw new RuntimeException(UserToRole.class.getSimpleName() + "(" + info + ") is illegal");
                info.setCreatetime(now);
                info.setCreator(admin.getMembername());
            }
        }
        int[] rs = new int[infos.length];
        source.insert(infos);
        for (int i = 0; i < rs.length; i++) {
            rs[i] = infos[i].getSeqid();
        }
        //if (rs.length > 0 || deled) userService.updateCache();
        return rs;
    }

    public int[] updateRoleToOption(MemberInfo admin, int[] delseqids, RoleToOption... infos) {
        final boolean deled = delseqids != null && delseqids.length > 0;
        if (deled) source.delete(RoleToOption.class, FilterNode.create("seqid", FilterExpress.IN, delseqids));
        if (infos.length == 0) return new int[0];
        if (admin != null) {
            long now = System.currentTimeMillis();
            for (RoleToOption info : infos) {
                if (info.getRoleid() < 1 || info.getOptionid() < 1) throw new RuntimeException(RoleToOption.class.getSimpleName() + "(" + info + ") is illegal");
                info.setCreatetime(now);
                info.setCreator(admin.getMembername());
            }
        }
        int[] rs = new int[infos.length];
        source.insert(infos);
        for (int i = 0; i < rs.length; i++) {
            rs[i] = infos[i].getSeqid();
        }
        //if (rs.length > 0 || deled) userService.updateCache();
        return rs;
    }

    public void deleteRoleToOption(int seqid) {
        RoleToOption rto = source.find(RoleToOption.class, seqid);
        if (rto == null) return;
        source.delete(rto);
        List<UserToRole> utrs = source.queryList(UserToRole.class, FilterNode.create("roleid", rto.getRoleid()));
        if (utrs.isEmpty()) return;
        int[] memberids = new int[utrs.size()];
        int index = -1;
        for (int i = 0; i < memberids.length; i++) {
            memberids[++index] = utrs.get(index).getMemberid();
        }
        //userService.updateCache(memberids);
    }

    public List<RoleToOption> queryRoleToOption(FilterBean bean) {
        return source.queryList(RoleToOption.class, bean);
    }

    public int[] queryOptionidsByMemberid(int memberid) {
        Set<Integer> list = source.queryColumnSet("optionid", RoleToOption.class, "roleid", queryRoleidByMemberid(memberid));
        return format(list);
    }

    public int[] queryRoleidByMemberid(int memberid) {
        List<Integer> list = source.queryColumnList("roleid", UserToRole.class, "memberid", memberid);
        return format(list);
    }

    private static int[] format(Collection<Integer> list) {
        if (list == null || list.isEmpty()) return new int[0];
        int[] rs = new int[list.size()];
        int i = -1;
        for (int v : list) {
            rs[++i] = v;
        }
        return rs;
    }

}
