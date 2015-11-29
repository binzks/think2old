//package org.think2.cms.controller.system;
//
//import com.google.gson.Gson;
//import com.idea4j.cms.controller.AbstractController;
//import com.idea4j.cms.support.ModulePermission;
//import com.idea4j.cms.support.ViewContext;
//import com.idea4j.cms.support.view.View;
//import com.idea4j.cms.support.view.ViewColumn;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.think4jframework.context.Think4jContext;
//import org.think4jframework.jdbc.support.JdbcModel;
//import org.think4jframework.jdbc.support.model.Filter;
//import org.think4jframework.jdbc.support.model.FilterType;
//import org.think4jframework.utils.EncryptUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//@Controller
//@RequestMapping(value = "/user")
//public class UserController extends AbstractController {
//
//    private String modulePrefix = "@m@";
//    private String columnPrefix = "@c@";
//    private String rowValuePrefix = "@r@";
//
//    @RequestMapping(value = "/chg_psw{mid}-{id}") // 密码修改
//    public String changePassword(@PathVariable String mid, @PathVariable String id, Model model, HttpServletRequest request) {
//        try {
//            ModulePermission modulePermission = getModulePermission(mid, request);
//            List<Filter> filters = new ArrayList<>();
//            filters.add(new Filter(modulePermission.getPkName(), FilterType.Eq, id));
//            Map<String, Object> map = modulePermission.getJdbcModel().selectMap(filters);
//            model.addAttribute("mid", mid);
//            model.addAttribute("id", id);
//            model.addAttribute("baseUrl", this.getClass().getAnnotation(RequestMapping.class).value()[0]);
//            model.addAttribute("code", map.get("code"));
//            model.addAttribute("name", map.get("name"));
//            return "system/user/chgPsw";
//        } catch (Exception e) {
//            model.addAttribute("msg", "错误[" + e + "]");
//            return "/system/template/error";
//        }
//    }
//
//    @RequestMapping(value = "/save_psw{mid}-{id}")
//    public String savePassword(@PathVariable String mid, @PathVariable String id, Model model,
//                               HttpServletRequest request) {
//        try {
//            getModulePermission(mid, request);
//            String pwd = request.getParameter("password");
//            String password = EncryptUtils.md5(EncryptUtils.md5(pwd));
//            Map<String, Object> userMap = new HashMap<>();
//            userMap.put("id", id);
//            userMap.put("password", password);
//            userMap.put("modify_time", System.currentTimeMillis() / 1000);
//            Think4jContext.getJdbcModel("model_sys_user").update(userMap);
//            String baseUrl = this.getClass().getAnnotation(RequestMapping.class).value()[0];
//            return "redirect:" + baseUrl + "/list" + mid + ".idea";
//        } catch (Exception e) {
//            model.addAttribute("msg", "错误[" + e + "]");
//            return "/system/template/error";
//        }
//    }
//
//    @RequestMapping(value = "/chg_psw-{id}") // 登录用户修改自己密码
//    public String changeSelfPassword(@PathVariable String id, Model model, HttpServletRequest request) {
//        try {
//            if (!getUserSession(request).getId().equals(id)) {
//                throw new Exception("没有修改密码操作权限，请联系管理员");
//            }
//            List<Filter> filters = new ArrayList<>();
//            filters.add(new Filter("id", FilterType.Eq, id));
//            Map<String, Object> map = Think4jContext.getJdbcModel("model_sys_user").selectMap(filters);
//            model.addAttribute("id", id);
//            model.addAttribute("baseUrl", this.getClass().getAnnotation(RequestMapping.class).value()[0]);
//            model.addAttribute("code", map.get("code"));
//            model.addAttribute("name", map.get("name"));
//            return "system/user/chgPsw";
//        } catch (Exception e) {
//            model.addAttribute("msg", "错误[" + e + "]");
//            return "/system/template/error";
//        }
//    }
//
//    @RequestMapping(value = "/save_psw-{id}") //登录用户修改自己密码
//    public String saveSelfPassword(@PathVariable String id, Model model, HttpServletRequest request) {
//        try {
//            if (!getUserSession(request).getId().equals(id)) {
//                throw new Exception("没有修改密码操作权限，请联系管理员");
//            }
//            String pwd = request.getParameter("password");
//            String password = EncryptUtils.md5(EncryptUtils.md5(pwd));
//            Map<String, Object> userMap = new HashMap<>();
//            userMap.put("id", id);
//            userMap.put("password", password);
//            userMap.put("modify_time", System.currentTimeMillis() / 1000);
//            Think4jContext.getJdbcModel("model_sys_user").update(userMap);
//            String baseUrl = this.getClass().getAnnotation(RequestMapping.class).value()[0];
//            return "redirect:/sys/home.idea";
//        } catch (Exception e) {
//            model.addAttribute("msg", "错误[" + e + "]");
//            return "/system/template/error";
//        }
//    }
//
//    @RequestMapping(value = "/row_filter{mid}-{id}")
//    public String rowFilter(@PathVariable String mid, @PathVariable String id, Model model,
//                            HttpServletRequest request) {
//        try {
//            ModulePermission modulePermission = getModulePermission(mid, request);
//            List<Filter> filters = new ArrayList<>();
//            filters.add(new Filter(modulePermission.getPkName(), FilterType.Eq, id));
//            Map<String, Object> map = modulePermission.getJdbcModel().selectMap(filters);
//            model.addAttribute("mid", mid);
//            model.addAttribute("id", id);
//            model.addAttribute("baseUrl", this.getClass().getAnnotation(RequestMapping.class).value()[0]);
//            model.addAttribute("code", map.get("code"));
//            model.addAttribute("name", map.get("name"));
//            return "system/user/rowFilter";
//        } catch (Exception e) {
//            model.addAttribute("msg", "错误[" + e + "]");
//            return "/system/template/error";
//        }
//    }
//
//    @RequestMapping(value = "/save_row_filter{mid}-{id}")
//    public String savePower(@PathVariable String mid, @PathVariable String id, Model model, HttpServletRequest request) {
//        try {
//            getModulePermission(mid, request);
//            // 删除原来的过滤权限
//            List<Filter> filters = new ArrayList<>();
//            filters.add(new Filter("parent_id", FilterType.Eq, id));
//            JdbcModel dataModel = Think4jContext.getJdbcModel("model_sys_user_row_filter");
//            dataModel.delete(filters);
//            // 添加新的过滤权限
//            String rowValChecked = request.getParameter("rowValChecked");
//            if (StringUtils.isNotBlank(rowValChecked)) {
//                String[] rowList = rowValChecked.split(",");
//                List<Map<String, Object>> list = new ArrayList<>();
//                String lastModuleId = null; //上一个模块id
//                String lastColumn = null;  //上一个列的名称
//                StringBuilder values = new StringBuilder();
//                // 循环取出每一个勾选的行
//                for (int i = 0; i < rowList.length; i++) {
//                    String row = rowList[i];
//                    // 获取勾选的模块id、列和勾选值
//                    int moduleIndex = row.indexOf(modulePrefix);
//                    int columnIndex = row.indexOf(columnPrefix);
//                    int valueIndex = row.indexOf(rowValuePrefix);
//                    String moduleId = StringUtils.substring(row, moduleIndex + modulePrefix.length(), columnIndex);
//                    String column = StringUtils.substring(row, columnIndex + columnPrefix.length(), valueIndex);
//                    String value = StringUtils.substring(row, valueIndex + rowValuePrefix.length(), row.length());
//                    // 如果上一个模块为null，则设置当前模块为上一个模块
//                    if (null == lastModuleId) {
//                        lastModuleId = moduleId;
//                    }
//                    // 如果上一个列为null，则设置当前的模块的列为上一个列
//                    if (null == lastColumn) {
//                        lastColumn = column;
//                    }
//                    // 如果当前的模块id和列名与上一个都相同，表示是同一个列勾选的不同值，加入values，各勾选值,隔开
//                    if (lastModuleId.equals(moduleId) && lastColumn.equals(column)) {
//                        if (values.length() <= 0) {
//                            values.append(",");
//                        }
//                        values.append(value).append(",");
//                    } else {
//                        // 如果模块id或者列不同表示已经是另一个列，则把上一个列加入待新增的list
//                        list.add(getRowFilterMap(id, lastModuleId, lastColumn, values.toString()));
//                        // 设置当前模块id和列为上一个列，并将选中值加入values
//                        lastModuleId = moduleId;
//                        lastColumn = column;
//                        values.setLength(0);
//                        values.append(",").append(value).append(",");
//                    }
//                    // 如果当前已经到最后一个节点，则加入待新增的list
//                    if (i == rowList.length - 1 && values.length() > 0) {
//                        list.add(getRowFilterMap(id, lastModuleId, lastColumn, values.toString()));
//                    }
//                }
//                dataModel.batchInsert(list);
//            }
//            String baseUrl = this.getClass().getAnnotation(RequestMapping.class).value()[0];
//            return "redirect:" + baseUrl + "/list" + mid + ".idea";
//        } catch (Exception e) {
//            model.addAttribute("msg", "错误[" + e + "]");
//            return "/system/template/error";
//        }
//    }
//
//    private Map<String, Object> getRowFilterMap(String id, String moduleId, String column, String value) {
//        Map<String, Object> rowFilter = new HashMap<>();
//        rowFilter.put("parent_id", id);
//        rowFilter.put("module_id", moduleId);
//        rowFilter.put("column", column);
//        rowFilter.put("value", value);
//        return rowFilter;
//    }
//
//    @RequestMapping(value = "/getTree.do")
//    public void getRowFilterTree(HttpServletRequest request, HttpServletResponse response) {
//        // JSON树
//        String userId = request.getParameter("id");
//        List<Filter> filters = new ArrayList<>();
//        filters.add(new Filter("id", FilterType.Eq, userId));
//        Map<String, Object> userMap = Think4jContext.getJdbcModel("model_sys_user").selectMap(filters);
//        filters.clear();
//        filters.add(new Filter("id", FilterType.Eq, userMap.get("role_id")));
//        Map<String, Object> roleMap = Think4jContext.getJdbcModel("model_sys_role").selectMap(filters);
//        String moduleIds = (String) roleMap.get("module_ids");
//        List<Map<String, Object>> result = initRowFilterTree(userId, moduleIds);
//        Gson gson = new Gson();
//        writeResponse(response, gson.toJson(result));
//    }
//
//    /**
//     * 根据用户的模块权限，生成模块权限对应的列的行级过滤树，只显示有行级过滤列的模块
//     *
//     * @param userId    登录用户id
//     * @param moduleIds 模块id
//     * @return 行级过滤树
//     */
//    private List<Map<String, Object>> initRowFilterTree(String userId, String moduleIds) {
//        if (StringUtils.isBlank(moduleIds)) {
//            return null;
//        }
//        List<Map<String, Object>> result = new ArrayList<>();
//        List<Filter> filters = new ArrayList<>();
//        filters.add(new Filter("parent_id", FilterType.Eq, userId));
//        List<Map<String, Object>> rowFilterMaps = Think4jContext.getJdbcModel("model_sys_user_row_filter").selectMaps(filters);
//        filters.clear();
//        filters.add(new Filter("id", FilterType.In, moduleIds.substring(1, moduleIds.length())));
//        List<Map<String, Object>> moduleMaps = Think4jContext.getJdbcModel("model_login_module").selectMaps(filters);
//        for (Map<String, Object> map : moduleMaps) {
//            String viewCode = (String) map.get("view_code");
//            if (StringUtils.isBlank(viewCode)) {
//                continue;
//            }
//            String mid = map.get("id").toString();
//            boolean checked = false;
//            boolean isAdd = false;
//            View view = ViewContext.getView(viewCode);
//            if (null == view) {
//                continue;
//            }
//            List<ViewColumn> list = view.getColumns();
//            if (null == list) {
//                continue;
//            }
//            for (ViewColumn viewColumn : list) {
//                if (viewColumn.isRowFilter()) {
//                    isAdd = true;
//                    String cid = viewColumn.getName();
//                    for (Entry<String, String> entry : viewColumn.getItems().entrySet()) {
//                        boolean rowChecked = getChecked(rowFilterMaps, entry.getKey(), mid, cid);
//                        if (rowChecked) {
//                            checked = true;
//                        }
//                        result.add(getTreeNode(modulePrefix + mid + columnPrefix + cid + rowValuePrefix + entry.getKey(),
//                                mid + columnPrefix + cid, entry.getValue(), rowChecked));
//                    }
//                    result.add(getTreeNode(mid + columnPrefix + cid, modulePrefix + mid, viewColumn.getDescribe(), checked));
//                }
//            }
//            if (isAdd) {
//                result.add(getTreeNode(modulePrefix + mid, modulePrefix + map.get("parent_id").toString(),
//                        map.get("name").toString(), checked));
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 获取行级过滤值是否需要选中
//     *
//     * @param list     待检查数据
//     * @param value    检查值
//     * @param moduleId 模块id
//     * @param column   model列
//     * @return 是否选中
//     */
//    private boolean getChecked(List<Map<String, Object>> list, String value, String moduleId, String column) {
//        boolean result = false;
//        for (Map<String, Object> map : list) {
//            if (moduleId.equals(map.get("module_id").toString()) && column.equals(map.get("column").toString())) {
//                if (StringUtils.contains(map.get("value").toString(), "," + value + ",")) {
//                    result = true;
//                }
//                break;
//            }
//        }
//        return result;
//    }
//
//}
