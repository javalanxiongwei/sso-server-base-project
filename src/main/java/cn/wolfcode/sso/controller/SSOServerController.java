package cn.wolfcode.sso.controller;

import cn.wolfcode.sso.util.MockDatabaseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

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
            /**---------------------------阶段三添加的代码start--------------------**/
            //有全局会话
            //取出令牌信息,重定向到redirectUrl,把令牌带上
            // http://www.wms.com:8089/main?token=
            model.addAttribute("token",token);
            /**---------------------------阶段三添加的代码end-----------------------**/
            return "redirect:"+redirectUrl;
        }
    }

    /**
     * 登陆方法
     * @param username      前台登陆的用户名
     * @param password      前台登陆的密码
     * @param redirectUrl   客户端被拦截的地址
     * @param session       服务端会话对象
     * @param model         模型数据
     * @return               响应的视图地址
     */
    @RequestMapping("/login")
    public String login(String username,String password,String redirectUrl,HttpSession session,Model model){
        if("zhangsan".equals(username)&&"666".equals(password)){
            //账号密码匹配
            //1.创建令牌信息,只要保证唯一即可,我们就使用UUID.
            String token = UUID.randomUUID().toString();
            //2.创建全局的会话,把令牌信息放入会话中.
            session.setAttribute("token",token);
            //3.需要把令牌信息放到数据库中.
            MockDatabaseUtil.T_TOKEN.add(token);
            //4.重定向到redirectUrl,把令牌信息带上.  http://www.crm.com:8088/main?token=
            model.addAttribute("token",token);
            return "redirect:"+redirectUrl;
        }
        //如果账号密码有误,重新回到登录页面,还需要把redirectUrl放入request域中.
        model.addAttribute("redirectUrl",redirectUrl);
        return "login";
    }

    /**
     * 校验客户端传过来的令牌信息是否有效
     * @param token 客户端传过来的令牌信息
     * @return
     */
    @RequestMapping("/verify")
    @ResponseBody
    public String verifyToken(String token){
        //在模拟的数据库表t_token中查找是否有这条记录
        if(MockDatabaseUtil.T_TOKEN.contains(token)){
            //说明令牌有效,返回true
            return "true";
        }
        return "false";
    }
}
