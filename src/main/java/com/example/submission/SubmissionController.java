package com.example.submission;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Class;
import com.example.entity.Submission;
import com.example.form.SubmissionDTO;
import com.example.form.SubmissionList;
import com.example.form.SubmittedChilerenList;
import com.example.service.ClassService;
import com.example.service.LoginUser;

import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("submission")
public class SubmissionController {

	private final SubmissionService submissionService;
	private final ClassService classService;

	private List<SubmittedChilerenList> classSubmittedList;

	@Autowired
	public SubmissionController(SubmissionService submissionService,ClassService classService) {
		this.submissionService = submissionService;
		this.classService = classService;
	}

	//提出物一覧表の表示
	@GetMapping("/index")
	public String showSubmissionList(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Class classInfo = this.classService.findById(loginUser.getUser().getClassId());
		model.addAttribute("classInfo", classInfo);
		//ログイン中のクラスIDの取得
//		int classId = loginUser.getClassId();
		Integer classId = 13;
		//CLASS_IDで提出物を検索
		List<Submission> submissions = this.submissionService.getSubmissionsByClassId(classId);

		//一覧に表示するリストの作成
		List<SubmissionList> submissionList = new ArrayList<SubmissionList>();
		for (Submission submission: submissions) {
			SubmissionList submissionListItem = new SubmissionList();
			submissionListItem.setId(submission.getId());
			submissionListItem.setName(submission.getName());
			submissionListItem.setDue(submission.getDue());
			submissionListItem.setCount(submissionService.countUnsubmittedBySubmissionId(submission.getId()));
			submissionList.add(submissionListItem);
		}

		model.addAttribute("submissionList", submissionList);


		//新規提出物作成処理
		Submission submission = new Submission();
		model.addAttribute("submission", submission);

	    return "submission/submissionIndex";
	}

	//提出物新規作成
	@PostMapping("/createSubmission")
	public String createSubmission(Submission submission,@AuthenticationPrincipal LoginUser loginUser) {

	    this.submissionService.createSubmission(submission,loginUser);
	    return "redirect:/submission/index";
	}

	// 提出物編集画面
	@GetMapping("/edit/{id}")
	public String editData(Model model,@PathVariable Integer id,@AuthenticationPrincipal LoginUser loginUser) {
		Class classInfo = this.classService.findById(loginUser.getUser().getClassId());
		model.addAttribute("classInfo", classInfo);
		
	    // 提出物名、提出期限表示のためSubmissionオブジェクト取得
	    Submission submission = submissionService.submissionFindById(id);
	    // 児童名、提出状況のリストを作成
	    List<SubmittedChilerenList> submittedList = submissionService.mkSubmittedListFindById(id);
	    this.classSubmittedList = submittedList;

	    model.addAttribute("submission", submission);

	    // SubmissionDTO インスタンスを作成し、値をセット
	    SubmissionDTO submissionDTO = new SubmissionDTO();
	    submissionDTO.setSubmittedList(submittedList);
	    submissionDTO.setId(id);

	    model.addAttribute("submissionDTO", submissionDTO);

	    // 児童提出状況リストの作成
	    return "submission/submissionEdit";
	}

	@PostMapping("/submitEditSave/{id}")
	public String saveData(@PathVariable Integer id, Model model,SubmissionDTO submissionDTO) {
//	    List<SubmittedChilerenList> submittedList = submissionDTO.getSubmittedList();

	    submissionService.saveSubmissionAndStatus(submissionDTO, id);
	    return "redirect:/submission/index";
	}
}
