package tk.mybatis.simple.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色权限关联表
 */
@Getter
@Setter
public class SysRolePrivilege {
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 权限ID
	 */
	private Long privilegeId;
}
