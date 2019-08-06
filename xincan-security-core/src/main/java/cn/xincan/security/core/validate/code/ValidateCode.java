package cn.xincan.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description: 验证码类
 * @className: SmsCode
 * @date: 2019/8/3 20:24
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class ValidateCode implements Serializable {


    private static final long serialVersionUID = -9121928684493797414L;

    // 图片上的随机数（存入session）
    private String code;

    // 验证码过期时间
    private LocalDateTime expireTime;

    public ValidateCode(){}

    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn); // 设置过期时间点
    }

    public ValidateCode(String code, LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
