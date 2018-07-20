package com.dxc.services;

import com.dxc.models.Role;

public interface RoleService {
	Role findByName(String name);
}
