package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Class;
import com.example.repository.ClassRepository;

@Service
public class ClassService {

	private final ClassRepository classRepository;

	@Autowired
	public ClassService(ClassRepository classRepository) {
		this.classRepository = classRepository;
	}
	public Class findById(Integer classId) {
		return this.classRepository.findById(classId).get();
	}

	/*public Integer getCurrentUserClassId() {
	    // Spring Securityのコンテキストから現在の認証情報を取得
	    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    if (userDetails instanceof LoginUser) {
	        // UserDetailsがLoginUserのインスタンスであるかを確認し、classIdを取得
	        LoginUser loginUser = (LoginUser) userDetails;
	        return loginUser.getClassId();
	    } else {
	        // UserDetailsがLoginUserでない場合、適切な処理を行ってください
	        return null; // または例外をスロー
	    }
	}

	public Optional<com.example.entity.Class> findById(Integer classId) {
		return this.classRepository.findById(classId);
	}

	public String getUserClass() {
		Integer classId = this.getCurrentUserClassId();
		String userClass = this.findById(classId).get().getName();
		return userClass;
	}*/
}