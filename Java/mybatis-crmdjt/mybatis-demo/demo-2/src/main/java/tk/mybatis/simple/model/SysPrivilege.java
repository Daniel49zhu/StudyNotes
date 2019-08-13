package tk.mybatis.simple.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 权限表
 */
@Getter
@Setter
public class SysPrivilege implements Serializable {
	private static final long serialVersionUID = 6315662516417216377L;
	/**
	 * 权限ID
	 */
	private Long id;
	/**
	 * 权限名称
	 */
	private String privilegeName;
	/**
	 * 权限URL
	 */
	private String privilegeUrl;

}
