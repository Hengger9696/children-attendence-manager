package com.example.submission;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Child;
import com.example.entity.SubStatus;
import com.example.entity.Submission;
import com.example.form.SubmissionDTO;
import com.example.form.SubmittedChilerenList;
import com.example.repository.SubStatusRepository;
import com.example.repository.SubmissionChildRepository;
import com.example.repository.SubmissionRepository;
import com.example.service.LoginUser;

@Service
public class SubmissionService {

	private final SubmissionRepository submissionRepository;
	private final SubStatusRepository subStatusRepository;
	private final SubmissionChildRepository submissionChildRepository;

	@Autowired
	public SubmissionService(
	        SubmissionRepository submissionRepository,
	        SubStatusRepository subStatusRepository,
	        SubmissionChildRepository childRepository) {
	    this.submissionRepository = submissionRepository;
	    this.subStatusRepository = subStatusRepository;
	    this.submissionChildRepository = childRepository;
	}

	//★提出物一覧・新規登録画面用★


	//一覧表示
	public List<Submission> getSubmissionsByClassId(Integer classId) {
		return submissionRepository.findByClassId(classId);
	    }

	//未提出人数取得
	public Integer countUnsubmittedBySubmissionId(Integer submissionId) {
		Integer submitted = 0;
		return subStatusRepository.countBySubmissionIdAndSubmitted(submissionId, submitted);
	}

	//新規提出物作成
	@Transactional
	public void createSubmission(Submission submission, LoginUser loginUser) {
		submission.setClassId(13);
		Submission savedSubmission = this.submissionRepository.save(submission);
		
		Integer savedSubmissionId = savedSubmission.getId();
		//新規提出物作成に伴い、提出状況を作成
		createSubStatus(submission, loginUser, savedSubmissionId);
	}

	//新規提出物作成呼び出し先
	public void createSubStatus(Submission submission, LoginUser loginUser, Integer savedSubmissionId) {
		//int classId = loginUser.getClassId();
		Integer classId = 13;
		//児童のリストを作成
		List<Child> children = getChildrenList(classId);
		//児童一覧で繰り返し処理を行い、新規データを格納。
		for(Child child : children) {
			Integer childId = child.getId();
		SubStatus subStatus = new SubStatus();
		subStatus.setChildrenId(childId);
		subStatus.setSubmissionId(savedSubmissionId);
		//提出状況は未提出(0を格納)
		subStatus.setSubmitted(0);
		this.subStatusRepository.save(subStatus);
		}
	}

	//児童リストの作成
	public List<Child> getChildrenList(Integer classId) {
		return submissionChildRepository.findByClassId(classId);
	}

	//提出状況一覧取得
	public List<SubmittedChilerenList> mkSubmittedListFindById(Integer id) {
	    Optional<Submission> optionalSubmission = this.submissionRepository.findById(id);
	    Submission submission  = optionalSubmission.get();
	    Integer submissionId = submission.getId();
	    List<SubStatus> subStatusList = getSubStatusList(submissionId);

	    //subStatusリストに児童名を追加したSubmittedChilerenListを作成
	    List<SubmittedChilerenList> submittedChilerenList = mkSubmittedChilerenList(subStatusList);

	    return submittedChilerenList;
	}


	//★提出物編集画面用★


	//提出物IDからSubStatusリスト作成
	public List<SubStatus> getSubStatusList(Integer submissionId) {
		return subStatusRepository.findBySubmissionId(submissionId);
	}

	//subStatusリストに児童名を追加したSubmittedChilerenListを作成
	public List<SubmittedChilerenList> mkSubmittedChilerenList(List<SubStatus> subStatusList) {
		List<SubmittedChilerenList> submittedChilerenList = new ArrayList<>();
		for(SubStatus subStatus: subStatusList) {
			SubmittedChilerenList submittedChileren = new SubmittedChilerenList();
			submittedChileren.setId(subStatus.getId());
			//児童のIDを取得
			Integer childId = subStatus.getChildrenId();
			submittedChileren.setChildId(childId);
			//DBから児童オブジェクトを取得
			Optional<Child> optionalChild = submissionChildRepository.findById(childId);
			Child child = optionalChild.get();
			//児童オブジェクトとsubStatusから必要情報をsubmittedChilerenListにset
			submittedChileren.setName(child.getName());
			submittedChileren.setSubmitted(subStatus.getSubmitted());
			//viewに渡すリストに追加
			submittedChilerenList.add(submittedChileren);
		}
		return submittedChilerenList;
	}

	//提出物をIDからオブジェクト取得
	public Submission submissionFindById(Integer id) {
		Optional<Submission> opsionalSubmission =  submissionRepository.findById(id);
		Submission submission = opsionalSubmission.get();
		return submission;
	}

	public void saveSubmissionAndStatus(SubmissionDTO submissionDTO, Integer submissionId) {
		
		List<SubStatus> subStatusList = new ArrayList<SubStatus>();
		//提出状況保存用処理
		//viewから受け取ったSubmittedChilerenListをSubStatusオブジェクトに変換して保存
		for(SubmittedChilerenList data : submissionDTO.getSubmittedList()) {
			Optional<SubStatus> optionalSubStatus =  subStatusRepository.findById(data.getId());
			SubStatus subStatus = optionalSubStatus.get();
			subStatus.setId(data.getId());
			subStatus.setChildrenId(data.getChildId());
			subStatus.setSubmissionId(submissionId);
			subStatus.setSubmitted(data.getSubmitted());

			subStatusList.add(subStatus);
		}
		//保存
		 subStatusRepository.saveAll(subStatusList);
	}
}
