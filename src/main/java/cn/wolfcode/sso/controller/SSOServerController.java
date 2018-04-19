package cn.wolfcode.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by wolfcode-lanxw
 */
@Controller
public class SSOServerController {
    /**
     * 检查是否有全局会话.
     * @param redirectUrl 客户端被拦截的请求地址
     * @param session      统一认证中心的会话对象
     * @param model        数据模型
     * @return              视图地址
     */
    @RequestMapping("/checkLogin")
    public String checkLogin(String redirectUrl, HttpSession session, Model model){
        //1.判断是否有全局的会话
        //从会话中获取令牌信息,如果取不到说明没有全局会话,如果能取到说明有全局会话
        String token = (String) session.getAttribute("token");
        if(StringUtils.isEmpty(token)){
            //表示没有全局会话
            model.addAttribute("redirectUrl",redirectUrl);
            //跳转到统一认证中心的登陆页面.已经配置视图解析器,
            // 会找/WEB-INF/views/login.jsp视图
            return "login";
        }else{
            //有全局会话
            //目前这段逻辑我们先不写,按着图解流程编写代码
            return "";
        }
    }
}
