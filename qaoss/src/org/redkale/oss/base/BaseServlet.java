/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.redkale.oss.base;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.logging.*;
import javax.annotation.Resource;
import org.redkale.convert.json.JsonConvert;
import org.redkale.net.http.*;
import org.redkale.oss.sys.LogRecordService;
import org.redkale.oss.sys.UserMemberService;
import org.redkale.service.RetResult;

/**
 * boostrap 中dataTable需要设置才能获取flipper:
 *
 * <blockquote><pre>
 * $.extend($.fn.dataTable.defaults, {
 *
 *      preDrawCallback: function (settings) {
 *          $(settings.nTable).on('preXhr.dt', function (e, settings, data) {
 *              delete data.columns;
 *              delete data.search;
 *              if (data.length) {
 *                  var flipper = {limit: data.length, offset: data.start || 0};
 *                  if (data.sort) flipper.sort = data.sort + (data.order ? (" " + data.order) : "");
 *                  data.flipper = JSON.stringify(flipper);
 *              }
 *          });
 *          $(settings.nTable).on('xhr.dt', function (e, settings, json) {
 *              if (json) {
 *                  json.data = json.rows || [];
 *                  if (json.total &lt; 0) json.total = 0;
 *                  json.recordsTotal = json.total;
 *                  json.recordsFiltered = json.recordsTotal;
 *                  json.draw = new Date().getTime();
 *              }
 *          });
 *    }
 *
 * }
 * </pre></blockquote>
 *
 * @author zhangjx
 */
public class BaseServlet extends org.redkale.net.http.RestHttpServlet<MemberInfo> {

    protected final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    protected final boolean fine = logger.isLoggable(Level.FINE);

    protected final boolean finer = logger.isLoggable(Level.FINER);

    protected final boolean finest = logger.isLoggable(Level.FINEST);

    protected static final RetResult RET_UNLOGIN = OssRetCodes.retResult(OssRetCodes.RET_USER_UNLOGIN);

    protected static final RetResult RET_AUTHILLEGAL = OssRetCodes.retResult(OssRetCodes.RET_USER_AUTH_ILLEGAL);

    @Resource
    protected JsonConvert convert;

    @Resource
    private UserMemberService service;
    
    @Resource
    private LogRecordService logRecordService;

    @Override
    public boolean authenticate(int module, int actionid, HttpRequest request, HttpResponse response) throws IOException {
        MemberInfo info = currentUser(request);
        if (info == null) {
            response.finishJson(RET_UNLOGIN);
            return false;
        } else if (!info.checkAuth(module, actionid)) {
            response.finishJson(RET_AUTHILLEGAL);
            return false;
        }
        return true;
    }

    @Override
    protected final MemberInfo currentUser(HttpRequest req) throws IOException {
        final String sessionid = req.getSessionid(false);
        if (sessionid == null) return null;
        MemberInfo user = (MemberInfo) req.getAttribute("$_CURRENT_MEMBER");
        if (user == null) {
            user = service.current(sessionid);
            req.setAttribute("$_CURRENT_MEMBER", user);
        }
        return user;
    }
//
//    protected Flipper findFlipper(HttpRequest request) {  //bootstrap datatable
//        return findFlipper(request, Flipper.DEFAULT_LIMIT);
//    }
//
//    protected Flipper findFlipper(HttpRequest request, int defaultLimit) {  //bootstrap datatable
//        int pageSize = request.getIntParameter("length", defaultLimit > 0 ? defaultLimit : Flipper.DEFAULT_LIMIT);
//        if (pageSize < 1) pageSize = defaultLimit > 0 ? defaultLimit : Flipper.DEFAULT_LIMIT;
//        int offset = request.getIntParameter("start", 0);
//        String sort = request.getParameter("sort");
//        String order = request.getParameter("order");
//        String sortColumn = (sort == null ? "" : ((order == null ? sort : (sort + " " + order.toUpperCase()))));
//        return new Flipper(pageSize, offset, sortColumn);
//    }
    
    @Override
    public boolean preExecute(final HttpRequest req, final HttpResponse resp) throws IOException {
        org.redkale.oss.sys.LogRecord record = new org.redkale.oss.sys.LogRecord();
        MemberInfo user = currentUser(req);
        if(user == null || req.getRequestURI().contains("/js/"))
            return true;
        record.setMemberid(user.getMemberid());
        record.setMembername(user.getMembername());
        record.setRequri(req.getRequestURI());
        record.setReqparams(req.getMethod().equals("GET") ? req.getRequstURILastPath() : URLDecoder.decode(req.getBody(Charset.forName("UTF-8")), "UTF-8"));
        record.setCreatetime(System.currentTimeMillis());
        logRecordService.create(record);
        return true;
    }

}
