package tk.mybatis.simple.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色关联表
 */
@Getter
@Setter
public class SysUserRole {
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 角色ID
	 */
	private Long roleId;

}
