//package org.think2.cms.controller.system;
//
//import com.idea4j.cms.controller.BaseController;
//import com.idea4j.cms.support.ModulePermission;
//import com.idea4j.cms.support.ViewContext;
//import com.idea4j.cms.support.view.View;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.think4jframework.context.Think4jContext;
//import org.think4jframework.jdbc.support.JdbcModel;
//import org.think4jframework.jdbc.support.model.Filter;
//import org.think4jframework.jdbc.support.model.FilterType;
//import org.think4jframework.jdbc.support.model.Join;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by zhoubin on 15/10/8.
// * 模块控制器，用于获取模块的配置信息和表创建sql语句
// */
//@Controller
//@RequestMapping(value = "/module")
//public class ModuleController extends BaseController {
//
//    /**
//     * 获取所有表的创建sql
//     *
//     * @param mid     模块id
//     * @param model   页面model
//     * @param request 页面请求
//     * @return 返回页面地址
//     */
//    @RequestMapping(value = "/all_sql{mid}")
//    public String getAllSql(@PathVariable String mid, Model model, HttpServletRequest request) {
//        try {
//            getModulePermission(mid, request);
//            List<String> list = Think4jContext.getAllCreateSql();
//            model.addAttribute("list", list);
//            return "system/module/createSql";
//        } catch (Exception e) {
//            model.addAttribute("msg", "错误[" + e + "]");
//            return "/system/template/error";
//        }
//    }
//
//    /***
//     * 获取模块对应数据的表的创建sql，包括model的关联表
//     *
//     * @param mid     模块id
//     * @param id      数据的id
//     * @param model   页面model
//     * @param request 页面请求
//     * @return 返回页面地址
//     */
//    @RequestMapping(value = "/sql{mid}-{id}")
//    public String getSql(@PathVariable String mid, @PathVariable String id, Model model, HttpServletRequest request) {
//        try {
//            ModulePermission modulePermission = getModulePermission(mid, request);
//            List<Filter> filters = new ArrayList<>();
//            filters.add(new Filter(modulePermission.getPkName(), FilterType.Eq, id));
//            Map<String, Object> map = modulePermission.getJdbcModel().selectMap(filters);
//            List<String> list = new ArrayList<>();
//            String viewCode = String.valueOf(map.get("view_code"));
//            if (StringUtils.isBlank(viewCode)) {
//                list.add("模块没有对应的view_code");
//            } else {
//                View view = ViewContext.getView(viewCode);
//                if (null != view) {
//                    JdbcModel jdbcModel = Think4jContext.getJdbcModel(view.getModelName());
//                    list.add(Think4jContext.getCreateSql(jdbcModel.getTableName()));
//                    List<Join> joins = jdbcModel.getModel().getJoins();
//                    if (null != joins) {
//                        for (Join join : joins) {
//                            if (StringUtils.isNotBlank(join.getTable())) {
//                                list.add(Think4jContext.getCreateSql(join.getTable()));
//                            }
//                        }
//                    }
//                } else {
//                    list.add("View[" + viewCode + "]没有设置model");
//                }
//            }
//            model.addAttribute("list", list);
//            return "system/module/createSql";
//        } catch (Exception e) {
//            model.addAttribute("msg", "错误[" + e + "]");
//            return "/system/template/error";
//        }
//    }
//}
