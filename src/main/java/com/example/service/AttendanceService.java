package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Child;
import com.example.repository.AttendanceRepository;

@Service
public class AttendanceService {

	private final AttendanceRepository attendanceRepository;

	@Autowired
	public AttendanceService(AttendanceRepository attendanceRepository) {
			this.attendanceRepository = attendanceRepository;
	}
	
	//繰り返し保存用のメソッドを定義(AttendanceController) save⇒torokuメソッド名変更
	public Child toroku(Child child) {
		return this.attendanceRepository.save(child);
	}


	//特定クラスの児童一覧を表示
	public List<Child> getChildrenByClassId(Integer classId) {
		return attendanceRepository.findByClassId(classId);
	}

	//更新後リスト保存
	public void saveAll(List<Child> updateList) {
		updateList.forEach(Child -> this.save(Child));

	}		


	public Child save(Child child) {
		return this.attendanceRepository.save(child);
	}

	public Child get(Integer id) {
        return this.attendanceRepository.findById(id).get();
    }

	public void delete(Integer id) {
		this.attendanceRepository.deleteById(id);
	}
}