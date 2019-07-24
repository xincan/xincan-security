package cn.xincan.security.browser.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @Description: 用户对应角色实体对象
 * @ClassName: Role
 * @Date: 2019-07-2323 18:50:34
 * @Author Xincan Jiang
 * @Version 1.0
 */
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getAuthority() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
