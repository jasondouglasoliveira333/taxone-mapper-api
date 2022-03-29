package br.com.lkm.taxone.mapper.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtil {
	public static void main(String... args) {
		try {
//			System.out.println(new BCryptPasswordEncoder().encode("DEUSjason"));
			System.out.println(new BCryptPasswordEncoder().encode("teste2"));
		}catch (Exception  e) {
			e.printStackTrace();
		}
	}
}
