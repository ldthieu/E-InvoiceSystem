package com.dxc.config;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dxc.models.Role;
import com.dxc.models.Service;
import com.dxc.models.User;
import com.dxc.repository.RoleRepository;
import com.dxc.repository.ServiceRepository;
import com.dxc.repository.UserRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// Roles
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}

		if (roleRepository.findByName("ROLE_MEMBER") == null) {
			roleRepository.save(new Role("ROLE_MEMBER"));
		}

		// Admin account
		if (userRepository.findByEmail("admin@gmail.com") == null) {
			User admin = new User();
			admin.setEmail("admin@gmail.com");
			admin.setPassword(passwordEncoder.encode("123456"));
			admin.setFullname("admin");
			admin.setActive(true);
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			admin.setRoles(roles);
			userRepository.save(admin);

			Service service = new Service();
			service.setMonthly(true);
			service.setServiceName("Electric");
			service.setUser(admin);
			serviceRepository.save(service);
			
			Service service1 = new Service();
			service1.setServiceName("Internet");
			service1.setUser(admin);
			serviceRepository.save(service1);
			
			Service service2 = new Service();
			service2.setServiceName("Telephone");
			service2.setUser(admin);
			serviceRepository.save(service2);
			
			Service service3 = new Service();
			service3.setServiceName("Water");
			service3.setUser(admin);
			serviceRepository.save(service3);
		}

		// Member account
		if (userRepository.findByEmail("bvn@gmail.com") == null) {
			User user = new User();
			user.setEmail("bvn@gmail.com");
			user.setPassword(passwordEncoder.encode("123456"));
			user.setFullname("member");
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			user.setRoles(roles);
			userRepository.save(user);
			
		}
		
		if (userRepository.findByEmail("ldth@gmail.com") == null) {
			User user = new User();
			user.setEmail("ldth@gmail.com");
			user.setPassword(passwordEncoder.encode("123456"));
			user.setFullname("member");
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			user.setRoles(roles);
			userRepository.save(user);		
		}
		
	}

}
