package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class AppMember extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 会员ID */
    private Long memberId;

    /** 邮箱 */
    private String email;

    /** 验证码 */
    private String code;

    /** 昵称 */
    private String nickName;

    /** 头像 */
    private String avatar;

    /** 状态（0正常 1停用） */
    private String status;
}