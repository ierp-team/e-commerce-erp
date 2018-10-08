package com.ierp.eorder.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ierp.activiti.util.WorkflowVariable;
import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.common.web.SessionUtil;
import com.ierp.eorder.domain.EOrderDTO;
import com.ierp.eorder.service.IEOrderService;


@RestController
@RequestMapping(value="/eordermanage")
public class EOrderManageController {
    
    @Autowired 
    private IEOrderService eOrderService;
    
//    @GetMapping
//    public @ResponseBody Page<EOrderDTO> findTodoTasks(HttpSession session,ExtjsPageRequest pageable) {
//        Page<EOrderDTO> page = new PageImpl<EOrderDTO>(new ArrayList<EOrderDTO>(), pageable.getPageable(), 0);
//        try {
//            page = eOrderService.findTodoTasks(SessionUtil.getUserName(session), pageable.getPageable());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }     
//        return page;
//    }

    /*-------------------------------------流程引擎web层------------------------------------------*/

    /**
     * 启动流程
     * 
     * @param leaveId
     *            请假信息Id
     * @param session
     *            通过会话获取登录用户(请假人)
     * @return
     */
    @RequestMapping(value = "/start")
    public  ExtAjaxResponse start(@RequestParam(name = "id") Long leaveId, HttpSession session) {
        try {
            String userId = SessionUtil.getUserName(session);
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("deptLeader", "financeManager");
            variables.put("applyUserId", userId);
            eOrderService.startWorkflow(userId, leaveId, variables);
            return new ExtAjaxResponse(true, "操作成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ExtAjaxResponse(false, "操作失败!");
        }
    }

    /**
     * 查询待处理流程任务
     * 
     * @param pageable
     *            分页对象
     * @param session
     *            通过会话获取登录用户(请假人)
     * @return
     */
    @RequestMapping(value = "/tasks")
    public  Page<EOrderDTO> findTodoTasks(HttpSession session, ExtjsPageRequest pageable) {
        Page<EOrderDTO> page = new PageImpl<EOrderDTO>(new ArrayList<EOrderDTO>(), pageable.getPageable(), 0);
        try {
            page = eOrderService.findTodoTasks(SessionUtil.getUserName(session), pageable.getPageable());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }

    /**
     * 签收任务
     */
    @RequestMapping(value = "claim/{id}")
    public @ResponseBody ExtAjaxResponse claim(@PathVariable("id") String taskId, HttpSession session) {
        try {
            eOrderService.claim(taskId, SessionUtil.getUserName(session));
            return new ExtAjaxResponse(true, "任务签收成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ExtAjaxResponse(false, "任务签收失败!");
        }
    }

    /**
     * 完成任务
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "complete/{id}")
    public @ResponseBody ExtAjaxResponse complete(@PathVariable("id") String taskId, WorkflowVariable var) {
        try {
            Map<String, Object> variables = var.getVariableMap();
            eOrderService.complete(taskId, variables);
            return new ExtAjaxResponse(true, "任务签收成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ExtAjaxResponse(false, "任务签收失败!");
        }
    }
}
