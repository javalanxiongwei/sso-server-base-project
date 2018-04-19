package cn.wolfcode.sso.util;
import cn.wolfcode.sso.vo.ClientInfoVo;

import java.util.*;
/**
 * Created by wolfcode-lanxw
 */
public class MockDatabaseUtil {
    //模拟数据库中的t_token表
    public static Set<String> T_TOKEN = new HashSet<String>();
    //模拟数据库中的t_client_info表
    public static Map<String,List<ClientInfoVo>> T_CLIENT_INFO =new HashMap<String,List<ClientInfoVo>>();
}
