package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Child;
import com.example.entity.Class;
import com.example.form.AttendanceForm;
import com.example.service.AttendanceService;
import com.example.service.ClassService;
import com.example.service.LoginUser;

@Controller
@RequestMapping("attend")
public class AttendanceController {

	private final AttendanceService attendanceService;
	private final ClassService classService;

	@Autowired
	public AttendanceController(AttendanceService attendanceService,
			ClassService classService) {
		this.attendanceService = attendanceService;
		this.classService = classService;
	}
	
	//児童一覧表の表示
		@GetMapping("/index")
		public String index(Model model, @AuthenticationPrincipal LoginUser loginUser) {
			Class classInfo = this.classService.findById(loginUser.getUser().getClassId());
			model.addAttribute("classInfo", classInfo);

			//ログイン中のクラスIDの取得
			Integer classId = loginUser.getUser().getClassId();
			//クラスIDをもとに担当クラス児童一覧を表示
			List<Child> children = this.attendanceService.getChildrenByClassId(classId);
			model.addAttribute("children", children);
			return "children/main";  //テンプレート名
		}

	//児童一覧変更内容登録 saveメソッド・パス被り⇒toroku　AttendanceServiceも修正
	@PostMapping("/toroku")
	public String toroku(AttendanceForm attendanceForm) {
		List<Child> updateList = attendanceForm.getChildrenList();
		this.attendanceService.saveAll(updateList);
		return "menu"; //レビューのファイル
	}


	@GetMapping("/create")
		
	public String showCreate(Model model ,@AuthenticationPrincipal LoginUser loginUser) {
		Class classInfo = this.classService.findById(loginUser.getUser().getClassId());
		model.addAttribute("classInfo", classInfo);
		//以下コメントアウトを追加されている二行を全てのコントローラーメソッドにコピペする
		Child child = new Child();
		//②class名を画面に渡す
		model.addAttribute("child", child);
		return "children/children_add";
	}

	@PostMapping("/save")
	public String save(Child child) {
		this.attendanceService.save(child);
		return "menu";
	}

	@GetMapping("/detail/{id}")
	public String showDetail(@PathVariable(name = "id") Integer id, Model model,@AuthenticationPrincipal LoginUser loginUser) {
		Class classInfo = this.classService.findById(loginUser.getUser().getClassId());
		model.addAttribute("classInfo", classInfo);
		
		Child child = this.attendanceService.get(id);
		model.addAttribute("child", child);
		return "children/children_detail";
	}

	@GetMapping("/edit/{id}")
	public String showEdit(@PathVariable(name = "id") Integer id, Model model,@AuthenticationPrincipal LoginUser loginUser) {
		Class classInfo = this.classService.findById(loginUser.getUser().getClassId());
		model.addAttribute("classInfo", classInfo);
		
		Child child = this.attendanceService.get(id);
		model.addAttribute("child", child);
		return "children/children_edit";
	}

	@GetMapping("/delete/{id}")
	public String showDelete(@PathVariable(name = "id") Integer id, Model model,@AuthenticationPrincipal LoginUser loginUser) {
		Class classInfo = this.classService.findById(loginUser.getUser().getClassId());
		model.addAttribute("classInfo", classInfo);
		
		Child child = this.attendanceService.get(id);
		model.addAttribute("child", child);
		return "children/children_delete";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") Integer id, Model model) {
		this.attendanceService.delete(id);
		return "menu";
	}
}