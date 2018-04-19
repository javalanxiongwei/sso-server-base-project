package cn.wolfcode.sso.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wolfcode-lanxw
 */
@Setter@Getter
public class ClientInfoVo {
    private String clientUrl;//客户端登出地址
    private String jsessionid;//客户端会话id
}
